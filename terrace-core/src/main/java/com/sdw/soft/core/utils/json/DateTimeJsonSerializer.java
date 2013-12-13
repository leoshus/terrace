package com.sdw.soft.core.utils.json;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * @author syd
 * @Date 2013年12月2日
 * @version 1.0.0
 * Copyright (c) 2013
 */
public class DateTimeJsonSerializer extends JsonSerializer<Date> {

	public static DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider) throws IOException,
            JsonProcessingException {
        if (value != null) {
            jgen.writeString(DATE_FORMAT.format(value));
        }

    }

    public Class<Date> handledType() {
        return Date.class;
    }
}
