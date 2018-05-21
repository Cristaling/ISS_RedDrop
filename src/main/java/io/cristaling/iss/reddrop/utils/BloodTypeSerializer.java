package io.cristaling.iss.reddrop.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class BloodTypeSerializer extends JsonSerializer<BloodType> {

    @Override
    public void serialize(BloodType value, JsonGenerator generator,
                          SerializerProvider provider) throws IOException,
            JsonProcessingException {

        // output the custom Json
        generator.writeStartObject();

        // the type
        generator.writeFieldName("type");
        generator.writeString(value.name());

        // the full name
        generator.writeFieldName("name");
        generator.writeString(value.getName());

        // end tag
        generator.writeEndObject();
    }
}
