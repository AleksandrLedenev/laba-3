package ru.amm.ledenev.dto.request;

public sealed interface Request permits AddPersonageRequest, DeletePersonageRequest, GetAttackedRequest, GetPersonagesRequest, LoadGameRequest, NewGameRequest, ReadPersonagesFromFileRequest, SaveGameRequest {

}
