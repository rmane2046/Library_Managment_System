package com.lib.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lib.entity.IssueRecordEntity;

public interface IssueRecordEntityRepository extends JpaRepository<IssueRecordEntity, Integer> {

}
