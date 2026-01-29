package guru.qa.countries.soap;

import guru.qa.countries.config.AppConfig;
import guru.qa.countries.service.CountryService;
import guru.qa.xml.countries.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CountriesEndpoint {
    private final CountryService countryService;

    @Autowired
    public CountriesEndpoint(CountryService countryService) {
        this.countryService = countryService;
    }

    @PayloadRoot(namespace = AppConfig.SOAP_NAMESPACE, localPart = "emptyRequest")
    @ResponsePayload
    public CountriesResponse countries() {
        CountriesResponse response = new CountriesResponse();
        response.getCountries().addAll(
                countryService.allCountries().stream().map(country -> {
                    Country xmlCountry = new Country();
                    xmlCountry.setCode(country.code());
                    xmlCountry.setName(country.name());
                    return xmlCountry;
                }).toList()
        );
        return response;
    }

    @PayloadRoot(namespace = AppConfig.SOAP_NAMESPACE, localPart = "createCountryRequest")
    @ResponsePayload
    public CountryResponse createCountry(@RequestPayload CreateCountryRequest request) {
        CountryResponse response = new CountryResponse();
        Country resultCountry = new Country();
        guru.qa.countries.domain.Country domainCountry = countryService.createCountry(guru.qa.countries.domain.Country.fromXmlRequest(request.getCountry()));
        resultCountry.setCode(domainCountry.code());
        resultCountry.setName(domainCountry.name());
        response.setCountry(resultCountry);
        return response;
    }

    @PayloadRoot(namespace = AppConfig.SOAP_NAMESPACE, localPart = "updateCountryRequest")
    @ResponsePayload
    public CountryResponse updateCountry(@RequestPayload UpdateCountryRequest request) {
        CountryResponse response = new CountryResponse();
        Country resultCountry = new Country();
        guru.qa.countries.domain.Country domainCountry = countryService.updateCountry(guru.qa.countries.domain.Country.fromXmlRequest(request.getCountry()));
        resultCountry.setCode(domainCountry.code());
        resultCountry.setName(domainCountry.name());
        response.setCountry(resultCountry);
        return response;
    }
}
