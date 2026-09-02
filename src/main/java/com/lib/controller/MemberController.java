package com.lib.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lib.services.MemberService;
import com.lib.vo.MemberRecordVo;

@RestController
@RequestMapping("/member")
public class MemberController 
{
	private MemberService memberService;
	
	public MemberController(MemberService memberService)
	{
		this.memberService = memberService;
	}

	@PostMapping("/add")
	public ResponseEntity<String> createMember(@RequestBody MemberRecordVo vo)
	{
		String result = memberService.addMember(vo);
		return new ResponseEntity<String>(result,HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<MemberRecordVo> getMember(@PathVariable Integer id)
	{
		MemberRecordVo vo = memberService.getMemberById(id);
		return new ResponseEntity<MemberRecordVo>(vo,HttpStatus.OK);
		
	}
	
	@GetMapping("/all-member")
	public ResponseEntity<List<MemberRecordVo>> getAllMember()
	{
		List<MemberRecordVo> vo = memberService.getAllMember();
		return new ResponseEntity<List<MemberRecordVo>>(vo,HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> updateMember(@RequestBody MemberRecordVo vo)
	{
		String result = memberService.updateMember(vo);
		return new ResponseEntity<String>(result,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteMember(@PathVariable Integer id)
	{
		String result = memberService.deleteMember(id);
		return new ResponseEntity<String>(result,HttpStatus.OK);
	}
}
