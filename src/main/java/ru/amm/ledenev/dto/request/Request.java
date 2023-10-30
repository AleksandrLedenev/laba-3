package ru.amm.ledenev.dto.request;

public sealed interface Request permits AddPersonageRequest, DeletePersonageRequest, GetAttackedRequest, NewGameRequest, PrintPersonagesRequest, ReadPersonagesFromFileRequest {

}
