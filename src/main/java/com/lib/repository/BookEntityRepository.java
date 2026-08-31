package com.lib.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lib.entity.BookEntity;

public interface BookEntityRepository extends JpaRepository<BookEntity, Integer>
{
	public Optional<BookEntity> findByBookTitle(String tital);

	public List<BookEntity> findByAuthorAuthorId(Integer id);
}
