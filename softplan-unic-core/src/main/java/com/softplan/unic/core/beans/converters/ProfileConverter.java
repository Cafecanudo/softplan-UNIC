package com.softplan.unic.core.beans.converters;

import com.creativedrive.cora.core.beans.enums.ProfileType;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import javax.validation.ValidationException;
import java.io.IOException;
import java.util.Arrays;

public class ProfileConverter extends JsonDeserializer<ProfileType> {

    @Override
    public ProfileType deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String profile = jsonParser.getText();
        return Arrays.stream(ProfileType.values()).filter(st -> st.name().equals(profile)).findFirst()
                .orElseThrow(() -> new ValidationException(String.format("Invalid %s", profile)));
    }
}
