package ru.amm.ledenev.dto.response;

public record ErrorResponse(
        String message
) implements Response {
}
