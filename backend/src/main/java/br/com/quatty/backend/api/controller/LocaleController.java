package br.com.quatty.backend.api.controller;

import br.com.quatty.backend.api.dto.filter.LocaleFilterParams;
import br.com.quatty.backend.api.dto.table.LocaleTableResponse;
import br.com.quatty.backend.business.service.LocaleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/locale")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class LocaleController {

    private LocaleService localeService;

    @GetMapping
    public LocaleTableResponse getLocaleByFilter(LocaleFilterParams localeFilterParams){
        return localeService.getLocaleByFilter(localeFilterParams);
    }
}
