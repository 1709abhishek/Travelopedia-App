package com.travelopedia.fun.itinerary_service.utils;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class TimeParserSerializer extends JsonSerializer<LocalTime> {

	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("h a");

	@Override
	public void serialize(LocalTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		if (value != null) {
			gen.writeString(value.format(FORMATTER));
		}
	}

}
