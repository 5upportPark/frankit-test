package pjw.backend.frankit.enums;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum OptionType {
    CUSTOM("custom"),
    SELECT("select");
    @JsonValue
    private String type;

    OptionType(String type){
        this.type = type;
    }
    @JsonCreator
    public static OptionType get(String str){
        return Arrays.stream(OptionType.values()).filter(op->op.getType().equals(str)).findFirst().orElse(OptionType.CUSTOM);
    }
}
