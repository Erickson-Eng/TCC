package br.com.quatty.backend.business.service;

import br.com.quatty.backend.api.dto.request.PracticableRequest;
import br.com.quatty.backend.api.dto.response.PracticableResponse;

public interface PracticableService {

    PracticableResponse createPracticable(PracticableRequest practicableRequest);

    PracticableResponse updatePracticableInfo(PracticableRequest practicableRequest);
}
