package ru.amm.ledenev.dto.response;

import ru.amm.ledenev.model.Personage;

import java.util.Collection;

public record TwoTeamResponse(
        Collection<Personage> myTeam,
        Collection<Personage> enemyTeam
) implements Response{
}
