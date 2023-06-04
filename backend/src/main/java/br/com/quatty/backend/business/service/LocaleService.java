package br.com.quatty.backend.business.service;

import br.com.quatty.backend.api.dto.filter.LocaleFilterParams;
import br.com.quatty.backend.api.dto.request.LocaleRequest;
import br.com.quatty.backend.api.dto.response.LocaleResponse;
import br.com.quatty.backend.api.dto.table.LocaleTableResponse;

public interface LocaleService {

    LocaleResponse createLocale(LocaleRequest localeRequest);

    LocaleResponse updateLocale(Long id, LocaleRequest localeRequest);

    LocaleResponse getLocaleById(Long id);

    LocaleTableResponse getLocaleByFilter(LocaleFilterParams filterParams);

    LocaleResponse getLocaleForGym(Long gymId);


}
