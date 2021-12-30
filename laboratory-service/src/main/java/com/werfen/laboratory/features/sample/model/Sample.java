package com.werfen.laboratory.features.sample.model;

import com.werfen.laboratory.core.model.ModelBase;
import com.werfen.laboratory.features.patient.model.Patient;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Audited
@NoArgsConstructor
@Table(name = "samples")
public class Sample extends ModelBase {

    @Size(max = 45)
    @Schema(description = "Barcode", required = true, example = "000000001")
    private String barcode;


    @ManyToOne
    @JoinColumn(name = "fk_patient", updatable = false, nullable = false)
    private Patient patient;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sample", fetch = FetchType.EAGER)
    @OrderBy("creationDateTime ASC, id ASC")
    @ToString.Exclude
    private List<TestResult> testOrders = new ArrayList<>();

    public Sample(String barcode) {
        this.barcode=barcode;
    }

}