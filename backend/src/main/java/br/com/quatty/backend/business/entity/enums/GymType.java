package br.com.quatty.backend.business.entity.enums;

import java.util.Arrays;
import java.util.List;

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

    public static List<String> getAllGymType(){
        return Arrays.stream(GymType.values())
                .map(GymType::getCode).toList();
    }
}
