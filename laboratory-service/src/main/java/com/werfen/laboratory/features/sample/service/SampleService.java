package com.werfen.laboratory.features.sample.service;

import com.werfen.laboratory.features.patient.service.PatientService;
import com.werfen.laboratory.features.sample.model.Sample;
import com.werfen.laboratory.features.sample.model.TestResult;
import com.werfen.laboratory.features.sample.repository.SampleRepository;
import com.werfen.laboratory.features.test.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SampleService {

    private final SampleRepository sampleRepository;
    private final PatientService patientService;
    private final TestService testService;

    public Page<Sample> getByFilter(Pageable pageable) {
        return sampleRepository.findAll(getPageableWithDefaultsSort(pageable));
    }

    private Pageable getPageableWithDefaultsSort(Pageable pageable) {
        return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                pageable.getSortOr(Sort.by("id").descending()));
    }


    public Sample getSample(UUID sampleId) {
        return this.sampleRepository.findById(sampleId).orElseThrow(() -> new SampleNotFoundException(sampleId));
    }

    public Sample createSample(String barcode, UUID patientId, List<UUID> tests) {
        Sample sample = new Sample(barcode);
        sample.setPatient(patientService.getPatient(patientId));
        sample.setTestOrders(tests.stream()
                .map(testService::getTest)
                .map(test -> new TestResult(sample, test)).collect(Collectors.toList()));
        return this.sampleRepository.save(sample);
    }

    public Sample updateSample(UUID id, Sample sample) {
        return this.sampleRepository.findById(id)
                .map(existing -> {
                    sample.setId(id);
                    return this.sampleRepository.save(sample);
                }).orElseThrow(() -> new SampleNotFoundException(id));
    }

    public Sample removeSample(UUID id) {
        return this.sampleRepository.findById(id)
                .map(existing -> {
                    sampleRepository.delete(existing);
                    return existing;
                }).orElseThrow(() -> new SampleNotFoundException(id));
    }
}
