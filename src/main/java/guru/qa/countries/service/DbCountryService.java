package guru.qa.countries.service;

import guru.qa.countries.data.CountryEntity;
import guru.qa.countries.data.CountryRepository;
import guru.qa.countries.domain.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static guru.qa.countries.data.CountryEntity.toEntity;

@Component
public class DbCountryService implements CountryService {
    private final CountryRepository countryRepository;

    @Autowired
    public DbCountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }


    @Override
    public List<Country> allCountries() {
        return countryRepository.findAll()
                .stream()
                .map(country ->{
                    return new Country(
                            country.getCode(),
                            country.getName()
                    );
                }).toList();
    }

    @Override
    public Country createCountry(Country country) {
        if (!countryRepository.existsById(country.code())) {
            return Country.fromEntity(countryRepository.save(toEntity(country)));
        }
        else throw new IllegalArgumentException("Country exists");
    }

    @Override
    public Country updateCountry(Country country) {
        if (countryRepository.existsById(country.code())) {
            return Country.fromEntity(countryRepository.save(toEntity(country)));
        }
        else throw new IllegalArgumentException("Country is not exists");
    }
}
