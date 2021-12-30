package com.werfen.laboratory.core.csv;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.stereotype.Component;

@Component
public class CSVGenerator {

    public String generate(Object value, Iterable<String> columns) throws JsonProcessingException {
        return CsvMapper.builder()
                .configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true)
                .build()
                .writer(getSchema(columns))
                .writeValueAsString(value);
    }

    private CsvSchema getSchema(Iterable<String> columns) {
        CsvSchema.Builder schemaBuilder = CsvSchema.builder();
        columns.forEach(schemaBuilder::addColumn);
        return schemaBuilder.build();
    }
}
