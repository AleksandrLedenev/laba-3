package ru.amm.ledenev.dto.response;

public sealed interface Response permits ErrorResponse, NotFoundResponse, OkResponse, PersonagesResponse, TwoTeamResponse {
}
