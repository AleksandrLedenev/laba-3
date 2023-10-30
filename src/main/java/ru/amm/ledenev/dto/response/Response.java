package ru.amm.ledenev.dto.response;

public sealed interface Response permits DeleteResponse, ErrorResponse, OkResponse, PersonagesResponse, TwoTeamResponse {
}
