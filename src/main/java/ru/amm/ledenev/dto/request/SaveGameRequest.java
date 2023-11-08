package ru.amm.ledenev.dto.request;

public record SaveGameRequest(
        String fileName
) implements Request {
}
