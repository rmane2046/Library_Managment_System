package com.lib.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lib.entity.MemberEntity;

public interface MemberEntityRepository extends JpaRepository<MemberEntity, Integer> {

}
