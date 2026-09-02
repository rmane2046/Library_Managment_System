package com.lib.vo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class MemberRecordVo 
{
	private Integer memberId;
	private String memberName;
	private String memberEmail;
	private long memberPhone;
	private String memberAddress;
	private LocalDate membershipDate;
	private List<IssueRecordVo> record;
}
