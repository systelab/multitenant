package com.werfen.laboratory.features.test.repository;

import com.werfen.laboratory.features.test.model.Test;
import org.springframework.data.jpa.domain.Specification;

public class TestSpecs {

    private TestSpecs() {

    }

    public static Specification<Test> isFound(String search) {
        return (test, cq, cb) -> search != null ?
                cb.or(cb.like(cb.lower(test.get("code")), cb.lower(cb.literal("%" + search + "%"))),
                        cb.or(cb.like(cb.lower(test.get("shortName")), cb.lower(cb.literal("%" + search + "%"))))) : cb.isTrue(cb.literal(true));
    }

}
