package com.werfen.laboratory.features.patient.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.werfen.laboratory.core.model.ModelBase;
import com.werfen.laboratory.features.sample.model.Sample;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Audited
@NoArgsConstructor
@Table(name = "patients")
public class Patient extends ModelBase {

    private ZonedDateTime activationDate;

    @Size(max = 45)
    @Schema(description = "Patient Name", required = true, example = "Peter Smith")
    private String name;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patient", fetch = FetchType.LAZY)
    @OrderBy("barcode ASC")
    private List<Sample> samples = new ArrayList<>();
}