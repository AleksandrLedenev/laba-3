package ru.amm.ledenev.dto.request;

public sealed interface Request permits AddPersonageRequest, GetAttackedRequest, InfoRequest, NewGameRequest, PrintPersonagesRequest, ReadPersonagesFromFileRequest {

}
