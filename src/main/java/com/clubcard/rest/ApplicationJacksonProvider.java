package com.clubcard.rest;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.ext.ContextResolver;
import java.text.SimpleDateFormat;

public class ApplicationJacksonProvider implements ContextResolver<ObjectMapper> {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        MAPPER.setSerializationInclusion(Include.NON_EMPTY);
        MAPPER.disable(MapperFeature.USE_GETTERS_AS_SETTERS);
        MAPPER.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm"));
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public ApplicationJacksonProvider() {
        System.out.println("Instantiate ApplicationJacksonProvider");
    }

//    @Override
    public ObjectMapper getContext(Class<?> type) {
        System.out.println("--------------type-"
                + type.getSimpleName() + "---------------");

        return MAPPER;
    }
}