package guru.qa.countries.domain;

import guru.qa.countries.data.CountryEntity;

public record Country(String code, String name) {
    public static Country fromEntity(CountryEntity countryEntity){
        return new Country(countryEntity.getCode(), countryEntity.getName());
    }
}
