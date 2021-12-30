package com.werfen.laboratory.features.test.repository;

import com.werfen.laboratory.features.test.model.Test;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<Test, UUID>, JpaSpecificationExecutor<Test>, RevisionRepository<Test, UUID, Integer> {
}