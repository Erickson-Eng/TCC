package br.com.quatty.backend.business.service.postgres;

import br.com.quatty.backend.business.entity.Athlete;
import br.com.quatty.backend.business.service.AthleteService;

import br.com.quatty.backend.infra.config.ThreadLocalHolder;
import br.com.quatty.backend.infra.repository.AthleteRepository;
import br.com.quatty.backend.infra.repository.LocaleRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AthleteServicePostgres implements AthleteService {

    private AthleteRepository athleteRepository;
    private LocaleRepository localeRepository;
    @Override
    public void create(Athlete athlete) {

        Authentication authentication = ThreadLocalHolder.getAuthentication();

        athleteRepository.save(athlete);

    }


}
