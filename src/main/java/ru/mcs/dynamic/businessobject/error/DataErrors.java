package ru.mcs.dynamic.businessobject.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum DataErrors implements Error {
    CREATE_DATA_ERROR("1", HttpStatus.BAD_REQUEST, "Error create data");

    private final String code;
    private final HttpStatus httpStatus;
    private final String message;
}
