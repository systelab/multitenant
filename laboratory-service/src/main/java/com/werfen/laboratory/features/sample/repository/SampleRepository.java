package com.werfen.laboratory.features.sample.repository;

import com.werfen.laboratory.features.sample.model.Sample;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SampleRepository extends JpaRepository<Sample, UUID>, JpaSpecificationExecutor<Sample>, RevisionRepository<Sample, UUID, Integer> {
}