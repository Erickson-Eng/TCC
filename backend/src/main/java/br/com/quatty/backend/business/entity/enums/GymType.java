package br.com.quatty.backend.business.entity.enums;
public enum GymType {
    PUBLIC("PUBLIC"),
    PRIVATE("PRIVATE");

    private final String code;

    GymType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static GymType getGymType(String code){
        for (GymType value: GymType.values()){
            if (value.getCode().equalsIgnoreCase(code)){
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid code");
    }
}
