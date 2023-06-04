package br.com.quatty.backend.business.service;

import br.com.quatty.backend.api.dto.request.CommunityRequest;
import br.com.quatty.backend.api.dto.response.CommunityResponse;
import br.com.quatty.backend.api.dto.table.CommunityTableResponse;

public interface CommunityService {

    CommunityResponse createCommunity(CommunityRequest communityRequest);

    CommunityResponse updateCommunity(Long id, CommunityRequest communityRequest);

    CommunityTableResponse findCommunityByName(String name);

    void deleteCommunity(Long id);

    CommunityTableResponse getAllCommunity();
    CommunityResponse getCommunityById(Long id);
}
