package br.com.quatty.backend.business.service;

import br.com.quatty.backend.api.dto.filter.AthleteFilterParams;
import br.com.quatty.backend.api.dto.request.AthleteRequest;
import br.com.quatty.backend.api.dto.response.AthleteResponse;
import br.com.quatty.backend.api.dto.table.AthleteTableResponse;

public interface AthleteService {


    AthleteResponse createAthlete(AthleteRequest athleteRequest);

    AthleteResponse updateAthleteInfo(Long id, AthleteRequest athleteRequest);

    AthleteTableResponse findAthleteByParams(AthleteFilterParams athleteFilterParams);

    void deleteAthleteInfo(Long id);

}
