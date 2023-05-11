package br.com.quatty.backend.business.entity.enums;

public enum AttendanceConfirmation {

    CONFIRMED("CONFIRMED"),
    UNCERTAIN("UNCERTAIN"),
    WILL_MISS("WILL_MISS");


    private final String code;

    AttendanceConfirmation(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }


    public static AttendanceConfirmation getAttendanceStatus(String code){
        for (AttendanceConfirmation value : AttendanceConfirmation.values()) {
            if (value.getCode().equalsIgnoreCase(code)){
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid code");
    }
}
