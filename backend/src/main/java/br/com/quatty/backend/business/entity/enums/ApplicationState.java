package br.com.quatty.backend.business.entity.enums;

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
}
