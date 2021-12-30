package com.werfen.laboratory.features.test.service;

import com.werfen.laboratory.features.test.model.Test;
import com.werfen.laboratory.features.test.repository.TestRepository;
import com.werfen.laboratory.features.test.repository.TestSpecs;

import java.util.UUID;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestRepository testRepository;

    public Page<Test> getByFilter(Pageable pageable, String search) {
        return testRepository.findAll(TestSpecs.isFound(search), getPageableWithDefaultsSort(pageable));
    }

    private Pageable getPageableWithDefaultsSort(Pageable pageable) {
        return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                pageable.getSortOr(Sort.by("id").descending()));
    }


    public Test getTest(UUID testId) {
        return this.testRepository.findById(testId).orElseThrow(() -> new TestNotFoundException(testId));
    }

    public Test createTest(Test test) {
        return this.testRepository.save(test);
    }

    public Test updateTest(UUID id, Test test) {
        return this.testRepository.findById(id)
                .map(existing -> {
                    test.setId(id);
                    return this.testRepository.save(test);
                }).orElseThrow(() -> new TestNotFoundException(id));
    }

    public Test removeTest(UUID id) {
        return this.testRepository.findById(id)
                .map(existing -> {
                    testRepository.delete(existing);
                    return existing;
                }).orElseThrow(() -> new TestNotFoundException(id));
    }
}
