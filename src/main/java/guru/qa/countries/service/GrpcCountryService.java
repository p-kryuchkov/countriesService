package guru.qa.countries.service;

import com.google.protobuf.Empty;
import guru.qa.countries.domain.Country;
import guru.qa.grpc.countries.*;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrpcCountryService extends CountriesServiceGrpc.CountriesServiceImplBase {
    private final CountryService countryService;

    @Autowired
    public GrpcCountryService(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public void allCountries(Empty request, StreamObserver<CountriesResponse> responseObserver) {
        List<CountryResponse> countries = countryService.allCountries()
                .stream()
                .map(country -> CountryResponse.newBuilder()
                        .setCode(country.code())
                        .setName(country.name())
                        .build())
                .toList();

        responseObserver.onNext(CountriesResponse.newBuilder()
                .addAllCountries(countries).build());
        responseObserver.onCompleted();
    }

    @Override
    public void updateCountry(CountryRequest request, StreamObserver<CountryResponse> responseObserver) {
        Country country = countryService.updateCountry(new Country(request.getCode(), request.getName()));
        responseObserver.onNext(CountryResponse.newBuilder()
                .setCode(country.code())
                .setName(country.name())
                .build());
    }

    @Override
    public StreamObserver<CountryRequest> addCountry(StreamObserver<AddCountriesResponse> responseObserver) {
        return new StreamObserver<CountryRequest>() {
            int count = 0;

            @Override
            public void onNext(CountryRequest countryRequest) {
                countryService.createCountry(new Country(countryRequest.getCode(), countryRequest.getName()));
                count++;
            }

            @Override
            public void onError(Throwable throwable) {
                responseObserver.onError(throwable);
            }

            @Override
            public void onCompleted() {
                responseObserver.onNext(AddCountriesResponse.newBuilder().setAddedCount(count).build());
                responseObserver.onCompleted();
            }
        };
    }
}
