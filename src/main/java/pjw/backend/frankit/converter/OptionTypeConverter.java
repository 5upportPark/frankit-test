package pjw.backend.frankit.converter;

import io.micrometer.common.util.StringUtils;
import jakarta.persistence.AttributeConverter;
import org.springframework.core.convert.converter.Converter;
import pjw.backend.frankit.enums.OptionType;

import java.util.Objects;

public class OptionTypeConverter implements Converter<String, OptionType>, AttributeConverter<OptionType, String> {
    @Override
    public OptionType convert(String source) {
        if(StringUtils.isEmpty(source)) return OptionType.CUSTOM;
        return OptionType.get(source);
    }

    @Override
    public String convertToDatabaseColumn(OptionType optionType) {
        if(Objects.isNull(optionType)) return OptionType.CUSTOM.getType();
        return optionType.getType();
    }

    @Override
    public OptionType convertToEntityAttribute(String s) {
        if(StringUtils.isEmpty(s)) return OptionType.CUSTOM;
        return OptionType.get(s);
    }
}
