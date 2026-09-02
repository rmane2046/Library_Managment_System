package com.lib.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lib.entity.BookEntity;
import com.lib.entity.IssueRecordEntity;
import com.lib.entity.MemberEntity;
import com.lib.exceptions.BookAlreadyReturnedException;
import com.lib.exceptions.BookNotAvailableException;
import com.lib.exceptions.BookNotFoundException;
import com.lib.exceptions.MemberNotFoundException;
import com.lib.exceptions.RecordNotFoundException;
import com.lib.repository.BookEntityRepository;
import com.lib.repository.IssueRecordEntityRepository;
import com.lib.repository.MemberEntityRepository;
import com.lib.vo.IssueRecordVo;

@Service
public class IssueRecordServices implements IssueRecordService
{

	private IssueRecordEntityRepository issueRepo;
	private BookEntityRepository bookRepo;
	private MemberEntityRepository memberRepo;
	
	public IssueRecordServices(IssueRecordEntityRepository issueRepo, BookEntityRepository bookRepo, MemberEntityRepository memberRepo) 
	{
		this.issueRepo = issueRepo;
		this.bookRepo = bookRepo;
		this.memberRepo = memberRepo;
	}

	@Override
	public String IssueRecord(IssueRecordVo vo) 
	{
		Optional<BookEntity> entity = bookRepo.findById(vo.getBookId());
		Optional<MemberEntity> member = memberRepo.findById(vo.getMemberId());
		if(entity.isEmpty())
		{
			throw new BookNotFoundException("Book Not Found With ID :"+vo.getBookId());
		}
		if(member.isEmpty())
		{
			throw new MemberNotFoundException("Member Not Found with ID : "+vo.getMemberId());
		}
		BookEntity book = entity.get();
		if(book.getAvailableCopies()<=0)
		{
			throw new BookNotAvailableException("Book ID : "+vo.getBookId()+" Is Out Of Stock Now.");
		}
		
		IssueRecordEntity issue = new IssueRecordEntity();
		issue.setBook(book);
		issue.setIssueDate(vo.getIssueDate());
		issue.setMember(member.get());
		issue.setDueDate(vo.getIssueDate().plusDays(7));
		issueRepo.save(issue);
		book.setAvailableCopies(book.getAvailableCopies()-1);
		bookRepo.save(book);
		return "Book Issued Successfully with ID : "+issue.getIssueId();
	}

	@Override
	public String returnRecord(IssueRecordVo vo) {
		Optional<IssueRecordEntity> record = issueRepo.findById(vo.getIssueId());
		if(record.isEmpty())
		{
			throw new RecordNotFoundException("Record ID : "+vo.getIssueId()+" Not Found.");
		}
		IssueRecordEntity rIssue= record.get();
		if(rIssue.getReturnDate() != null)
		{
			throw new BookAlreadyReturnedException("Book Not Found For Returning.");
		}		
		rIssue.setReturnDate(vo.getReturnDate());
		BookEntity book = rIssue.getBook();
		book.setAvailableCopies(book.getAvailableCopies()+1);
		bookRepo.save(book);
		issueRepo.save(rIssue);
		return "Record ID : "+vo.getIssueId()+" Return Successfully";
	}

	@Override
	public List<IssueRecordVo> recordHistory()
	{
		List<IssueRecordEntity> entity = issueRepo.findAll();
		if(entity.isEmpty())
		{
			throw new RecordNotFoundException("No Record Available");
		}
		List<IssueRecordVo> vo = new ArrayList<IssueRecordVo>();
		for(IssueRecordEntity record : entity)
		{
			IssueRecordVo vo1 = new IssueRecordVo();
			vo1.setIssueId(record.getIssueId());
			vo1.setIssueDate(record.getIssueDate());
			vo1.setReturnDate(record.getReturnDate());
			vo1.setDueDate(record.getDueDate());
			vo1.setBookId(record.getBook().getBookId());
			vo1.setMemberId(record.getMember().getMemberId());
			vo.add(vo1);			
		}
		
		return vo;
	}

}
