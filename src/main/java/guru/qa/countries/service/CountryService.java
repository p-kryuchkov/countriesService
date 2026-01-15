package guru.qa.countries.service;

import guru.qa.countries.domain.Country;

import java.util.List;

public interface CountryService {
    List<Country> allCountries();
    Country createCountry(Country country);
    Country updateCountry(Country country);
}
