package br.com.quatty.backend.business.service;

import br.com.quatty.backend.api.dto.request.SportRequest;
import br.com.quatty.backend.api.dto.response.SportResponse;
import br.com.quatty.backend.api.dto.table.SportTableResponse;

import java.util.List;

public interface SportService {

    SportResponse createSport(SportRequest sportRequest);

    SportResponse updateSport(Long id, SportRequest sportRequest);

    SportResponse getSportById(Long id);

    SportTableResponse getSportByName(String name);

    SportTableResponse getAllSports();

    List<SportResponse> getSportForGym(Long id);
}
