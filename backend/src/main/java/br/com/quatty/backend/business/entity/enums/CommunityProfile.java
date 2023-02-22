package br.com.quatty.backend.business.entity.enums;

public enum CommunityProfile {
    ADMINISTRATOR("ADMINISTRATOR"),
    ATHLETE("ATHLETE");

    private final String code;

    CommunityProfile(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static CommunityProfile getCommunityProfile(String code){
        for (CommunityProfile value: CommunityProfile.values()
             ) {
            if (value.getCode().equalsIgnoreCase(code))
                return value;
        }
        throw new IllegalArgumentException("Invalid code");
    }
}
