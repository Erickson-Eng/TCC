package br.com.quatty.backend.business.service;

import br.com.quatty.backend.api.dto.request.ProfileRequest;
import br.com.quatty.backend.api.dto.response.ManagerResponse;

public interface ManagerService {

    ManagerResponse createManager(ProfileRequest profileRequest);
}
