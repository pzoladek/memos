package io.github.pzoladek.memos.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Objects;
import java.util.Set;

@Converter
public class StringSetConverter implements AttributeConverter<Set<String>, String> {

    @Override
    public String convertToDatabaseColumn(final Set<String> list) {
        if (Objects.isNull(list) || list.isEmpty()) {
            return null;
        }
        return String.join(",", list);
    }

    @Override
    public Set<String> convertToEntityAttribute(final String commaSeparatedValues) {
        if (Objects.nonNull(commaSeparatedValues)) {
            return Set.of(commaSeparatedValues.split(","));
        }
        return Set.of();
    }
}