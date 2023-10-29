package ru.amm.ledenev.dto.request;

public record ReadPersonagesFromFileRequest (
        String fileName
) implements Request {
}
