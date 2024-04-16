package ru.mcs.dynamic.businessobject.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ObjectError implements Error {
    CREATE_OBJECT_ERROR("1", HttpStatus.BAD_REQUEST, "Error create object");

    private final String code;
    private final HttpStatus httpStatus;
    private final String message;
}
