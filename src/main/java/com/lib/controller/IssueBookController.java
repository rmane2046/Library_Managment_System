package com.lib.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lib.services.IssueRecordServices;
import com.lib.vo.IssueRecordVo;

@RestController
@RequestMapping("/library")
public class IssueBookController 
{
	private IssueRecordServices issueService;
	
	public IssueBookController(IssueRecordServices issueService) 
	{
		this.issueService = issueService;
	}

	@PostMapping("/issue-book")
	public ResponseEntity<String> issueRecord(@RequestBody IssueRecordVo vo)
	{
		String result = issueService.IssueRecord(vo);
		return new ResponseEntity<String>(result,HttpStatus.OK);
	}
	
	@PutMapping("/return")
	public ResponseEntity<String> returnBook(@RequestBody IssueRecordVo vo)
	{
		String result = issueService.returnRecord(vo);
		return new ResponseEntity<String>(result,HttpStatus.OK);
	}
	
	@GetMapping("/issue-record")
	public ResponseEntity<List<IssueRecordVo>> issueRecordHistory()
	{
		List<IssueRecordVo> vo = issueService.recordHistory();
		
		return new ResponseEntity<List<IssueRecordVo>>(vo,HttpStatus.OK);
	}

}
