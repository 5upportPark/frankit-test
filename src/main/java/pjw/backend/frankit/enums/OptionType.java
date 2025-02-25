package pjw.backend.frankit.enums;


public enum OptionType {
    CUSTOM("custom"),
    SELECT("select");
    private String type;

    OptionType(String type){
        this.type = type;
    }


}
