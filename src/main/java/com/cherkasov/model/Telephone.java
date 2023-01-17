package com.cherkasov.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class Telephone extends Device {
    private String model;

    public Telephone() {
    }

    public Telephone(String series, String screenType,
                     int price, String model) {
        super(series, screenType, price, DeviceType.TELEPHONE);
        this.model = model;
    }

    @Override
    public String toString() {
        return String.format("Telephone (Series: %s, Model: %s, " +
                        "Screen type: %s, Price; %s)%n",
                series, model, screenType, price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Telephone telephone = (Telephone) o;
        return price == telephone.price && Objects.equals(series, telephone.series)
                && Objects.equals(model, telephone.model)
                && Objects.equals(screenType, telephone.screenType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(series, model, screenType, price);
    }
}
