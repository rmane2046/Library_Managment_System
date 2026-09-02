package com.lib.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "Issue_Record_Details")
public class IssueRecordEntity 
{
	@SequenceGenerator(name = "issueSeq", sequenceName = "IssueRecord_Sequence", initialValue = 7000, allocationSize = 1)
	@GeneratedValue(generator = "issueSeq", strategy = GenerationType.SEQUENCE)
	@Column(name = "IssueRecord_ID", insertable = true, updatable = false)
	@Id
	private Integer issueId;
	@NonNull
	@Column(name = "IssueRecord_Date")
	private LocalDate issueDate;
	@NonNull
	@Column(name = "IssueRecord_DueDate")
	private LocalDate dueDate;
	@NonNull
	@Column(name = "IssueRecord_ReturnDate")
	private LocalDate returnDate;
	
	@ManyToOne
	@JoinColumn(name = "Book_Detail")
	private BookEntity book;
	@ManyToOne
	@JoinColumn(name = "Member_Detail")
	private MemberEntity member;
}
