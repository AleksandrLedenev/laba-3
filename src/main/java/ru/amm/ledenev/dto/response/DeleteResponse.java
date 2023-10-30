package ru.amm.ledenev.dto.response;

import ru.amm.ledenev.model.Personage;

import java.util.Collection;

public record DeleteResponse(
        Collection<Personage> deletePersonages
) implements Response{
}
