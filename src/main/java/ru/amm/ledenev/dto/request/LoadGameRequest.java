package ru.amm.ledenev.dto.request;

public record LoadGameRequest(
        String fileName
) implements Request {
}
