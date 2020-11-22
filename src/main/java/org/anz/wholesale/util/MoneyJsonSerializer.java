package org.anz.wholesale.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.format.DateTimeParseException;

public class MoneyJsonSerializer extends JsonSerializer<Double> {

    static final String DOUBLE_FORMATTER = "%,.2f";

    @Override
    public void serialize(Double value, JsonGenerator gen, SerializerProvider provider)
            throws IOException {
        try {
            String s = String.format(DOUBLE_FORMATTER, value);
            gen.writeString(s);
        } catch (DateTimeParseException e) {
            gen.writeString("");
        }
    }
}
