package ru.amm.ledenev.dto.request;

public sealed interface Request permits GetAttackedRequest, NewGameRequest, ReadPersonagesFromFileRequest {

}
