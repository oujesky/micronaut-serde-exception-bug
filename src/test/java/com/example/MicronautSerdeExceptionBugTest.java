package com.example;

import io.micronaut.serde.ObjectMapper;
import io.micronaut.serde.exceptions.SerdeException;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import jakarta.inject.Inject;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@MicronautTest
class MicronautSerdeExceptionBugTest {

    private static final String JSON = """
        {
            "unknown": "abc",
            "value": "value"
        }""";

    @Inject
    private ObjectMapper objectMapper;

    @Test
    void testRecord() {
        assertThatCode(() -> objectMapper.readValue(JSON, DeserializableRecord.class))
            .isInstanceOf(SerdeException.class)
            .hasMessage("Unknown property [unknown] encountered during deserialization of type: DeserializableRecord");
    }

    @Test
    void testRecordShouldntReturnTheException() throws IOException {
        Object deserialized = objectMapper.readValue(JSON, DeserializableRecord.class);
        assertThat(deserialized)
            .isNotInstanceOf(SerdeException.class);
    }


    @Test
    void testClass() {
        assertThatCode(() -> objectMapper.readValue(JSON, DeserializableClass.class))
            .isInstanceOf(SerdeException.class)
            .hasMessage("Unknown property [unknown] encountered during deserialization of type: DeserializableClass");
    }


}
