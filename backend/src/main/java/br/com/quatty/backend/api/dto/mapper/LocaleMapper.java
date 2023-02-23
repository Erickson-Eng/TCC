package br.com.quatty.backend.api.dto.mapper;

import br.com.quatty.backend.api.dto.request.LocaleRequest;
import br.com.quatty.backend.api.dto.response.LocaleResponse;
import br.com.quatty.backend.business.entity.Locale;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LocaleMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    Locale localeRequestToEntity(LocaleRequest localeRequest);


    LocaleResponse entityToLocaleResponse(LocaleResponse localeResponse);
}
