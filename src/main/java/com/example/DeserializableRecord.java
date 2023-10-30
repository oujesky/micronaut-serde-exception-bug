package com.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.micronaut.serde.annotation.Serdeable.Deserializable;

@Deserializable
@JsonIgnoreProperties(ignoreUnknown = false)
record DeserializableRecord(String value) {
}
