package br.com.quatty.backend.infra.database;

import br.com.quatty.backend.business.entity.Locale;
import br.com.quatty.backend.business.entity.Sport;
import br.com.quatty.backend.infra.repository.LocaleRepository;
import br.com.quatty.backend.infra.repository.SportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
public class DatabaseInit implements CommandLineRunner {

    protected static final String[] CONSTANTS = {"Campina Grande", "Paraíba", "Erickson"};
    private final LocaleRepository localeRepository;
    private final SportRepository sportRepository;

    @Autowired
    public DatabaseInit(LocaleRepository localeRepository, SportRepository sportRepository) {
        this.localeRepository = localeRepository;
        this.sportRepository = sportRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Locale locale = Locale.builder().street("Antonio cirilo").city(CONSTANTS[0]).number("10").zipCode("1").state(CONSTANTS[1]).build();
        Locale locale1 = Locale.builder().street(CONSTANTS[2]).city(CONSTANTS[0]).number("10").zipCode("1").state(CONSTANTS[1]).build();
        Locale locale2 = Locale.builder().street(CONSTANTS[2]).city("Queimadas").number("10").zipCode("1").state(CONSTANTS[1]).build();
        Locale locale3 = Locale.builder().street(CONSTANTS[2]).city(CONSTANTS[0]).number("10").zipCode("1").state("RN").build();
        localeRepository.saveAll(Arrays.asList(locale,locale1,locale2,locale3));

        Sport sport = Sport.builder().name("Basquete").build();
        Sport sport1 = Sport.builder().name("Futebol").build();
        Sport sport2 = Sport.builder().name("Futsal").build();
        Sport sport3 = Sport.builder().name("Volei").build();
        Sport sport4 = Sport.builder().name("Tenis").build();
        sportRepository.saveAll(Arrays.asList(sport, sport1, sport2, sport3, sport4));
    }
}
