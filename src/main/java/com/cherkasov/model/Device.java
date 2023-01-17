package com.cherkasov.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Getter
@Setter
public abstract class Device {
    protected DeviceType deviceType;
    protected String series;
    protected String screenType;
    protected double price;
    private final Random random = new Random();


    public Device() {
    }

    public Device(String series, String screenType, double price, DeviceType deviceType) {
        this.series = series;
        this.screenType = screenType;
        this.price = price;
        this.deviceType = deviceType;
    }
}
