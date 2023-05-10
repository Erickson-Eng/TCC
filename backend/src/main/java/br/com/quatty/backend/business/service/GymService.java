package br.com.quatty.backend.business.service;

import br.com.quatty.backend.api.dto.filter.GymFilterParams;
import br.com.quatty.backend.api.dto.request.GymRequest;
import br.com.quatty.backend.api.dto.response.GymResponse;
import br.com.quatty.backend.api.dto.table.GymTableResponse;



public interface GymService {


    GymResponse createGym(GymRequest gymRequest);

    GymResponse updateGymInfo(Long id, GymRequest gymRequest);

    GymTableResponse findGymByFilter(GymFilterParams gymFilterParams);

    GymTableResponse getAllGyms();

    void deleteGym(Long id);

}
