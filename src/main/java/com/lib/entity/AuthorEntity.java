package com.lib.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "Author_Details")
public class AuthorEntity 
{
	@SequenceGenerator(name = "authorSeq", sequenceName = "Author_Seq", initialValue = 101, allocationSize = 1)
	@GeneratedValue(generator = "authorSeq", strategy = GenerationType.SEQUENCE)
	@Column(name = "Author_Id", insertable = true, updatable = false)
	@Id
	private Integer authorId;
	@NonNull
	@Column(name = "Author_Name")
	private String authorName;
	@NonNull
	@Column(name = "Author_Phone")
	private long authorPhone;
	
	@OneToMany(mappedBy = "author")
	private List<BookEntity> book;
	
}
