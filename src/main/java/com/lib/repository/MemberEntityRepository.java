package com.lib.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lib.entity.MemberEntity;

public interface MemberEntityRepository extends JpaRepository<MemberEntity, Integer>
{
	public Optional<MemberEntity> findByMemberEmail(String email);
}
