package guru.qa.countries.domain;

import guru.qa.countries.data.CountryEntity;

public record CountryInput(String code, String name) {
    public static CountryInput fromEntity(CountryEntity countryEntity){
        return new CountryInput(countryEntity.getCode(), countryEntity.getName());
    }
}
