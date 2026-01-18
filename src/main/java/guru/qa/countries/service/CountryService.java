package guru.qa.countries.service;

import guru.qa.countries.domain.Country;
import guru.qa.countries.domain.CountryInput;

import java.util.List;

public interface CountryService {
    List<Country> allCountries();

    Country createCountry(Country country);

    Country createCountryGql(CountryInput country);

    Country updateCountry(Country country);

    Country updateCountryGql(CountryInput country);
}
