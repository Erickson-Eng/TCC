package br.com.quatty.backend.business.entity.enums;

import java.util.Arrays;
import java.util.List;

public enum CommunityProfile {
    ADMINISTRATOR("ADMINISTRATOR", true, true, true, true),
    MODERATOR("MODERATOR", false, true, true, false),
    ATHLETE("ATHLETE", false, false, false, false);

    private final String code;
    private final boolean isCanEditContent;
    private final boolean isCanApproveRecruitment;
    private final boolean isCanScheduleAnAppointment;

    private final boolean isCanEditCommunityProfile;

    CommunityProfile(String code, boolean isCanEditContent, boolean isCanApproveRecruitment, boolean isCanScheduleAnAppointment, boolean isCanEditCommunityProfile) {
        this.code = code;
        this.isCanEditContent = isCanEditContent;
        this.isCanApproveRecruitment = isCanApproveRecruitment;
        this.isCanScheduleAnAppointment = isCanScheduleAnAppointment;
        this.isCanEditCommunityProfile = isCanEditCommunityProfile;
    }

    public boolean isCanEditContent() {
        return isCanEditContent;
    }

    public boolean isCanApproveRecruitment() {
        return isCanApproveRecruitment;
    }

    public boolean isCanScheduleAnAppointment() {
        return isCanScheduleAnAppointment;
    }

    public boolean isCanEditCommunityProfile() {
        return isCanEditCommunityProfile;
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

    public static List<String> getAllCommunityProfiles(){
        return Arrays.stream(CommunityProfile.values())
                .map(CommunityProfile::getCode).toList();
    }
}
