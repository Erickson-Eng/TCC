package br.com.quatty.backend.business.entity.enums;

public enum MembershipStatus {
    WAITING_FOR_APPROVAL("WAITING_FOR_APPROVAL"),
    APPROVED("APPROVED"),
    CANCELED("CANCELED"),
    DISAPPROVED("DISAPPROVED");

    private final String code;


    MembershipStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static MembershipStatus getMembershipStatus(String code){
        for (MembershipStatus value: MembershipStatus.values()){
            if (value.getCode().equalsIgnoreCase(code))
                return value;
        }
        throw new IllegalArgumentException("Invalid code");
    }
}
