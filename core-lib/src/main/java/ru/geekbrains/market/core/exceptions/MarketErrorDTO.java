package ru.geekbrains.market.core.exceptions;

import lombok.Data;

import java.util.Date;

@Data
public class MarketErrorDTO {
    private final int status;
    private final String message;
    private final Date timeStamp;

    public MarketErrorDTO(int status, String message) {
        this.status = status;
        this.message = message;
        timeStamp = new Date();
    }
}
