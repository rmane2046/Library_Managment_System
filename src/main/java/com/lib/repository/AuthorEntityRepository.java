package com.lib.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lib.entity.AuthorEntity;

public interface AuthorEntityRepository extends JpaRepository<AuthorEntity, Integer> {

	public Optional<AuthorEntity> findByAuthorPhone(long authorPhone);

}
