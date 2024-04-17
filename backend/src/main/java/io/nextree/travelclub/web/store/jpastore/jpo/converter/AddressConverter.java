package io.nextree.travelclub.web.store.jpastore.jpo.converter;

import com.google.gson.Gson;
import io.nextree.travelclub.web.domain.club.vo.Address;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

public class AddressConverter implements AttributeConverter<Address, String> {
    private static final Gson gson = new Gson();

    @Override
    public String convertToDatabaseColumn(Address attribute) {
        if (attribute == null) {
            return null;
        } else {
            return gson.toJson(attribute);
        }
    }

    @Override
    public Address convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        } else {
            return gson.fromJson(dbData, Address.class);
        }
    }
}
