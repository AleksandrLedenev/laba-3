package ru.amm.ledenev.dto.response;

import ru.amm.ledenev.model.Personage;

import java.util.Collection;

public record PersonagesResponse(
        Collection<Personage> personages
) implements Response {
}
