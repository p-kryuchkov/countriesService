package guru.qa.countries.data;

import guru.qa.countries.domain.Country;
import guru.qa.countries.domain.CountryInput;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Entity
@Table(name="countries")
public class CountryEntity {
    @Id
    private String code;

    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy proxy ? proxy.getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy proxy ? proxy.getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        CountryEntity that = (CountryEntity) o;
        return code != null && Objects.equals(code, that.code);
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy proxy ? proxy.getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    public static CountryEntity toEntity(Country country){
        CountryEntity countryEntity = new CountryEntity();
        countryEntity.setCode(country.code());
        countryEntity.setName(country.name());
        return countryEntity;
    }

    public static CountryEntity fromInputToEntity(CountryInput country){
        CountryEntity countryEntity = new CountryEntity();
        countryEntity.setCode(country.code());
        countryEntity.setName(country.name());
        return countryEntity;
    }
}
