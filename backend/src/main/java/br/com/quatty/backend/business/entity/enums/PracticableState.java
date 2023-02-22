package br.com.quatty.backend.business.entity.enums;

public enum PracticableState {
    IMPRACTICABLE("IMPRACTICABLE"),
    PRACTICABLE("PRACTICABLE");

    private final String code;

    PracticableState(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static PracticableState getPracticableState(String code){
        for (PracticableState value : PracticableState.values()) {
            if (value.getCode().equalsIgnoreCase(code))
                return value;
        }
        throw new IllegalArgumentException("Invalid code");
    }
}
