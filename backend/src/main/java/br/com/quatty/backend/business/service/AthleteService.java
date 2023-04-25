package br.com.quatty.backend.business.service;

import br.com.quatty.backend.api.dto.filter.AthleteFilterParams;
import br.com.quatty.backend.api.dto.request.ProfileRequest;
import br.com.quatty.backend.api.dto.response.AthleteResponse;
import br.com.quatty.backend.api.dto.table.AthleteTableResponse;

public interface AthleteService {


    AthleteResponse createAthlete(ProfileRequest athleteRequest);

    AthleteResponse updateAthleteInfo(Long id, ProfileRequest athleteRequest);

    AthleteTableResponse findAthleteByParams(AthleteFilterParams athleteFilterParams);

    void deleteAthleteInfo(Long id);

}
