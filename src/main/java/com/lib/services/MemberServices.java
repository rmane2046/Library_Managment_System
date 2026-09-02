package com.lib.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lib.entity.IssueRecordEntity;
import com.lib.entity.MemberEntity;
import com.lib.exceptions.MemberAlreadyExitException;
import com.lib.exceptions.MemberNotFoundException;
import com.lib.repository.MemberEntityRepository;
import com.lib.vo.IssueRecordVo;
import com.lib.vo.MemberRecordVo;

@Service
public class MemberServices implements MemberService
{
	private MemberEntityRepository memberRepo;
	
	public MemberServices(MemberEntityRepository memberRepo)
	{
		this.memberRepo = memberRepo;
	}

	@Override
	public String addMember(MemberRecordVo vo) 
	{
			Optional<MemberEntity> entity = memberRepo.findByMemberEmail(vo.getMemberEmail());
			if(entity.isPresent())
			{
				throw new MemberAlreadyExitException("Member Already Exist");
			}
			else
			{
				MemberEntity member = new MemberEntity();
				member.setMemberName(vo.getMemberName());
				member.setMemberPhone(vo.getMemberPhone());
				member.setMemberEmail(vo.getMemberEmail());
				member.setMemberAddress(vo.getMemberAddress());
				memberRepo.save(member);
				return "Member Saved with ID : "+member.getMemberId();				
			}
			
		
		}
	
		@Override
		public MemberRecordVo getMemberById(Integer id) {
			
			Optional<MemberEntity> entity = memberRepo.findById(id);
			if(entity.isEmpty())
			{
				throw new MemberNotFoundException("Member Not Found With ID : "+id);
			}
			MemberEntity member = entity.get();
			MemberRecordVo vo = new MemberRecordVo();
			vo.setMemberId(member.getMemberId());
			vo.setMemberName(member.getMemberName());
			vo.setMemberAddress(member.getMemberAddress());
			vo.setMemberEmail(member.getMemberEmail());
			vo.setMemberPhone(member.getMemberPhone());
			vo.setMembershipDate(member.getMembershipDate());
			List<IssueRecordVo> voList = new ArrayList<IssueRecordVo>();
			for(IssueRecordEntity issue : member.getIssueRecord())
			{
				IssueRecordVo vo2 = new IssueRecordVo();
				vo2.setIssueId(issue.getIssueId());
				vo2.setBookId(issue.getBook().getBookId());
				vo2.setDueDate(issue.getDueDate());
				vo2.setIssueDate(issue.getIssueDate());
				vo2.setMemberId(issue.getMember().getMemberId());
				vo2.setReturnDate(issue.getReturnDate());
				voList.add(vo2);
			}
			vo.setRecord(voList);
			return vo;
			
		
		}

		@Override
		public List<MemberRecordVo> getAllMember() 
		{
			List<MemberEntity> entity = memberRepo.findAll();
			if(entity.isEmpty())
			{
				throw new MemberNotFoundException("No Member In DataBase");
			}
			List<MemberRecordVo> vo = new ArrayList<MemberRecordVo>();
			for (MemberEntity member : entity) {

			    MemberRecordVo memberVo = new MemberRecordVo();

			    memberVo.setMemberId(member.getMemberId());
			    memberVo.setMemberName(member.getMemberName());
			    memberVo.setMemberEmail(member.getMemberEmail());
			    memberVo.setMemberPhone(member.getMemberPhone());
			    memberVo.setMemberAddress(member.getMemberAddress());
			    memberVo.setMembershipDate(member.getMembershipDate());
			    
			    List<IssueRecordVo> issueVoList = new ArrayList<>();
			    
			    for(IssueRecordEntity issue : member.getIssueRecord())
			    {
			    	IssueRecordVo issueVo = new IssueRecordVo();

		            issueVo.setIssueId(issue.getIssueId());
		            issueVo.setIssueDate(issue.getIssueDate());
		            issueVo.setDueDate(issue.getDueDate());
		            issueVo.setReturnDate(issue.getReturnDate());
		            issueVo.setBookId(issue.getBook().getBookId());
		            issueVoList.add(issueVo);
			    }
			    	
			    memberVo.setRecord(issueVoList);
			    vo.add(memberVo);
			}			
			return vo;
		}

		@Override
		public String updateMember(MemberRecordVo vo) {
			Optional<MemberEntity> entity = memberRepo.findById(vo.getMemberId());
			if(entity.isEmpty())
			{
				throw new MemberNotFoundException("Member Not Found with ID : "+vo.getMemberId());
			}
			MemberEntity member = entity.get();
			member.setMemberName(vo.getMemberName());
			member.setMemberEmail(vo.getMemberEmail());
			member.setMemberAddress(vo.getMemberAddress());
			member.setMemberPhone(vo.getMemberPhone());
			memberRepo.save(member);
		/*	
			MemberRecordVo memRecord = new MemberRecordVo();
			memRecord.setMemberId(member.getMemberId());
			memRecord.setMemberName(member.getMemberName());
			memRecord.setMemberAddress(member.getMemberAddress());
			memRecord.setMemberEmail(member.getMemberEmail());
			memRecord.setMemberPhone(member.getMemberPhone());
			*/
			return "Member ID : "+vo.getMemberId()+" Update Successfully.";
		}

		@Override
		public String deleteMember(Integer id) {
			Optional<MemberEntity> entity = memberRepo.findById(id);
			if(entity.isEmpty())
			{
				throw new MemberNotFoundException("Member Not Found With ID :"+id);
			}
			memberRepo.deleteById(id);
			return "Member "+id+" Deleted Successfully.";
		}

}
