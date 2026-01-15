package guru.qa.countries.controller;

import guru.qa.countries.domain.Country;
import guru.qa.countries.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/countries")
public class CountriesController {

    private final CountryService countryService;

    @Autowired
    public CountriesController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/all")
    public List<Country> all() {
        return countryService.allCountries();
    }

    @PostMapping()
    public Country create(@RequestBody Country country) {
        return countryService.createCountry(country);
    }

    @PatchMapping()
    public Country update(@RequestBody Country country) {
        return countryService.updateCountry(country);
    }
}
