package com.lib.entity;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Member_Detailss")
public class MemberEntity 
{
	@SequenceGenerator(name = "memberSeq", sequenceName = "Member_Sequence", initialValue = 1001, allocationSize = 1)
	@GeneratedValue(generator = "memberSeq", strategy = GenerationType.SEQUENCE)
	@Column(name = "Member_ID", insertable = true, updatable = false)
	@Id
	private Integer memberId;
	@NonNull
	@Column(name = "Member_Name")
	private String memberName;
	@NonNull
	@Column(name = "Member_Email")
	@Email
	private String memberEmail;
	@NonNull
	@Column(name = "Member_Phone")
	private long memberPhone;
	@NonNull
	@Column(name = "Member_Address")
	private String memberAddress;
	@NonNull
	@Column(name = "Membership_Date", insertable = true,updatable = false)
	@CreationTimestamp
	private LocalDate membershipDate;
	
	@OneToMany(mappedBy = "member")
	private List<IssueRecordEntity> issueRecord;
}
