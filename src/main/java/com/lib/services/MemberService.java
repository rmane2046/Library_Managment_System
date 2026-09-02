package com.lib.services;

import java.util.List;

import com.lib.vo.MemberRecordVo;

public interface MemberService 
{
	public String addMember(MemberRecordVo vo);
	public MemberRecordVo getMemberById(Integer id);
	public List<MemberRecordVo> getAllMember();
	public String updateMember(MemberRecordVo vo);
	public String deleteMember(Integer id);
}
