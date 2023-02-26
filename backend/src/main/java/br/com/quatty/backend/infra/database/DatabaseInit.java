package br.com.quatty.backend.infra.database;

import br.com.quatty.backend.business.entity.Locale;
import br.com.quatty.backend.infra.repository.LocaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class DatabaseInit implements CommandLineRunner {

    @Autowired
    private LocaleRepository localeRepository;
    @Override
    public void run(String... args) throws Exception {
        Locale locale = Locale.builder().street("Antonio cirilo").city("Campina Grande").number("10").zipCode("1").state("Paraíba").build();
        Locale locale1 = Locale.builder().street("Erickson").city("Campina Grande").number("10").zipCode("1").state("Paraíba").build();
        Locale locale2 = Locale.builder().street("Erickson").city("Queimadas").number("10").zipCode("1").
                state("Paraíba").build();
        Locale locale3 = Locale.builder().street("Erickson").city("Campina Grande").number("10").zipCode("1").state("RN").build();
        localeRepository.saveAll(Arrays.asList(locale,locale1,locale2,locale3));
    }
}
