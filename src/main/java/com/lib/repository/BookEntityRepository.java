package com.lib.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lib.entity.BookEntity;

public interface BookEntityRepository extends JpaRepository<BookEntity, Integer>
{
	public Optional<BookEntity> findByBookTitle(String tital);

	public List<BookEntity> findByAuthorAuthorId(Integer id);
	
	@Query(value = "SELECT * FROM book_details WHERE available_copies > 0", nativeQuery = true)
	public List<BookEntity> findAvailable();
}
