package br.com.quatty.backend.api.controller;

import br.com.quatty.backend.api.dto.filter.LocaleFilterParams;
import br.com.quatty.backend.api.dto.request.LocaleRequest;
import br.com.quatty.backend.api.dto.response.LocaleResponse;
import br.com.quatty.backend.api.dto.table.LocaleTableResponse;
import br.com.quatty.backend.business.service.LocaleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/locale")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class LocaleController {

    private LocaleService localeService;

    @GetMapping
    public ResponseEntity<LocaleTableResponse> getLocaleByFilter(LocaleFilterParams localeFilterParams){
        var locales = localeService.getLocaleByFilter(localeFilterParams);
        return ResponseEntity.ok().body(locales);
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<LocaleResponse> getLocaleById(@PathVariable Long id){
        var locale = localeService.getLocaleById(id);
        return ResponseEntity.ok().body(locale);
    }

    @PostMapping
    public ResponseEntity<LocaleResponse> createLocale(@RequestBody @Valid LocaleRequest localeRequest){
        var locale = localeService.createLocale(localeRequest);
        URI uri = URI.create(locale.getId().toString());
        return ResponseEntity.created(uri).body(locale);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocaleResponse> updateLocale(@PathVariable Long id,
                                                       @RequestBody LocaleRequest localeRequest){
        var locale = localeService.updateLocale(id, localeRequest);
        return ResponseEntity.ok().body(locale);
    }

}
