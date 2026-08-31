package com.lib.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "Book_Details")
public class BookEntity 
{
	@SequenceGenerator(name = "bookSeq", sequenceName = "Book_Seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator = "bookSeq", strategy = GenerationType.SEQUENCE)
	@Column(name = "Book_ID",insertable = true,updatable = false)
	@Id
	private Integer bookId;
	@NonNull
	@Column(name = "Book_Title")
	private String bookTitle;
	@NonNull
	@Column(name = "Book_Type")
	private String genre;
	@NonNull
	@Column(name = "Total_Copies")
	private int totalCopies;
	@NonNull
	@Column(name = "Available_Copies")
	private int availableCopies;
	
	@ManyToOne
	@JoinColumn(name = "Author_Details")
	private AuthorEntity author;
	@OneToMany(mappedBy = "book")
	private List<IssueRecordEntity> issueRecord;
}
