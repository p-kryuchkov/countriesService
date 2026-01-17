package guru.qa.countries.controller.graphql;

import guru.qa.countries.domain.Country;
import guru.qa.countries.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CountriesQueryController {

    private final CountryService countryService;

    @Autowired
    public CountriesQueryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @QueryMapping()
    public List<Country> countries() {
        return countryService.allCountries();
    }

    @MutationMapping()
    public Country create(@Argument Country input) {
        return countryService.createCountry(input);
    }

    @MutationMapping()
    public Country update(@Argument Country input) {
        return countryService.updateCountry(input);
    }
}
