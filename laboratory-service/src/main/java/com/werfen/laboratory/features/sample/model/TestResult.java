package com.werfen.laboratory.features.sample.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.werfen.laboratory.core.model.ModelBase;
import com.werfen.laboratory.features.test.model.Test;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.envers.Audited;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.ZonedDateTime;

@Data
@Entity
@Audited
@NoArgsConstructor
@Table(name = "results")
public class TestResult extends ModelBase {

    @ManyToOne
    @JoinColumn(name = "fk_test", nullable = false)
    private Test test;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fk_sample", updatable = false, nullable = false)
    @Setter(AccessLevel.NONE)
    private Sample sample;

    @CreationTimestamp
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime creationDateTime;

    private String result;

    public TestResult(Sample sample, Test test) {
        this.sample = sample;
        this.test = test;

    }
}
