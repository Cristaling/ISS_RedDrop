package io.cristaling.iss.reddrop.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import io.cristaling.iss.reddrop.utils.enums.BloodBagStatus;

import java.io.IOException;

public class BloodBagStatusSerializer extends JsonSerializer<BloodBagStatus> {

	@Override
	public void serialize(BloodBagStatus value, JsonGenerator generator, SerializerProvider provider) throws IOException, JsonProcessingException
	{
		generator.writeStartObject();
		generator.writeFieldName("type");
		generator.writeString(value.name());
		generator.writeFieldName("name");
		generator.writeString(value.getName());
		generator.writeEndObject();
	}

}
