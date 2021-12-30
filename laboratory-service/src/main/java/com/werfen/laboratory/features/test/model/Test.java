package com.werfen.laboratory.features.test.model;

import com.werfen.laboratory.core.model.ModelBase;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;

@Data
@Entity
@Audited
@NoArgsConstructor
@Table(name = "tests", uniqueConstraints = {@UniqueConstraint(columnNames = {"code"}),
        @UniqueConstraint(columnNames = {"loincCode"})})
public class Test extends ModelBase {

    private ZonedDateTime activationDate;

    @Size(max = 15)
    @Schema(description = "Test code", required = true, example = "GLU1212")
    private String code;

    @Size(max = 15)
    @Schema(description = "LOINC code", required = true, example = "GLU1212")
    private String loincCode;

    @NotNull
    @Size(min = 1, max = 255)
    @Schema(description = "Test name", required = true, example = "Glucose")
    private String shortName;

}