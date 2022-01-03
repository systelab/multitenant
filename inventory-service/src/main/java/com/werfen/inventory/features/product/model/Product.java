package com.werfen.inventory.features.product.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.werfen.inventory.core.TenantSupport;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@FilterDef(name = "tenantFilter", parameters = {@ParamDef(name = "tenantId", type = "string")})
@Filter(name = "tenantFilter", condition = "tenant_id = :tenantId")
@Table(name = "products")
public class Product implements TenantSupport {

    @Id
    @GeneratedValue
    private Long id;

    @Size(max = 255)
    private String name;

    @Column(name = "tenant_id")
    private String tenantId;

}