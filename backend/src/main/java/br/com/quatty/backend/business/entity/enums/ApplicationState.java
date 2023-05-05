package br.com.quatty.backend.business.entity.enums;

import java.util.Arrays;
import java.util.List;

public enum ApplicationState {
    WAITING_FOR_APPROVAL("WAITING_FOR_APPROVAL"),
    APPROVED("APPROVED"),
    CANCELED("CANCELED"),
    DISAPPROVED("DISAPPROVED");

    private final String code;


    ApplicationState(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static ApplicationState getApplicationState(String code){
        for (ApplicationState value: ApplicationState.values()){
            if (value.getCode().equalsIgnoreCase(code))
                return value;
        }
        throw new IllegalArgumentException("Invalid code");
    }

    public static List<String> getAllStatus(){
        return Arrays.stream(ApplicationState.values())
                .map(ApplicationState::getCode).toList();
    }
}
