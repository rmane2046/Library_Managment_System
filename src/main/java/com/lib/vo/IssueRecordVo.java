package com.lib.vo;

import java.time.LocalDate;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class IssueRecordVo 
{
	private Integer issueId;
	private LocalDate issueDate;
	private LocalDate dueDate;
	private LocalDate returnDate;
	
	private Integer bookId;
	private Integer memberId;
}
