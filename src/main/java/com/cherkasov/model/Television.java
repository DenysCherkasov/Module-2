package com.cherkasov.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class Television extends Device {
    private double diagonal;
    private String country;

    public Television() {
    }

    public Television(final String series, final String screenType,
                      final int price, final double diagonal, final String country) {
        super(series, screenType, price, DeviceType.TELEVISION);
        this.diagonal = diagonal;
        this.country = country;
    }

    @Override
    public String toString() {
        return String.format("Television (Series: %s, Country: %s, " +
                        "Screen type: %s, Diagonal: %s, Price; %s)%n",
                series, country, screenType, diagonal, price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Television that = (Television) o;
        return price == that.price && diagonal == that.diagonal
                && Objects.equals(series, that.series)
                && Objects.equals(screenType, that.screenType)
                && Objects.equals(country, that.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(series, screenType, price, diagonal, country);
    }
}

