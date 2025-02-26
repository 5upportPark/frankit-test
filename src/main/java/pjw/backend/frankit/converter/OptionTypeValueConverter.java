package pjw.backend.frankit.converter;

import io.micrometer.common.util.StringUtils;
import jakarta.persistence.AttributeConverter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

public class OptionTypeValueConverter implements Converter<List<String>, String>, AttributeConverter<List<String>, String> {

    @Override
    public String convert(List<String> source) {
        if(CollectionUtils.isEmpty(source)) return "";
        return String.join(",",source);
    }

    @Override
    public String convertToDatabaseColumn(List<String> strings) {
        if(CollectionUtils.isEmpty(strings)) return "";
        return String.join(",",strings);
    }

    @Override
    public List<String> convertToEntityAttribute(String s) {
        if(StringUtils.isEmpty(s)) return Collections.emptyList();
        return List.of(s.split(","));
    }

}
