package com.lib.services;

import java.time.LocalDate;
import java.util.List;

import com.lib.vo.IssueRecordVo;

import jakarta.transaction.Transactional;

public interface IssueRecordService 
{
	@Transactional
	public String IssueRecord(IssueRecordVo vo);
	@Transactional
	public String returnRecord(IssueRecordVo vo);
	public List<IssueRecordVo> recordHistory();

}
