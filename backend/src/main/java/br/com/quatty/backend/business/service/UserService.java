package br.com.quatty.backend.business.service;

import br.com.quatty.backend.api.dto.request.UserRequest;
import br.com.quatty.backend.api.dto.response.UserResponse;

public interface UserService {

    UserResponse createUser(UserRequest userRequest);

    void updateUser(Long id, UserRequest userRequest);



}
