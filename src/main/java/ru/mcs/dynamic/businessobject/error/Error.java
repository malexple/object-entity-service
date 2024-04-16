package ru.mcs.dynamic.businessobject.error;

import org.springframework.http.HttpStatus;

public interface Error {
    String getCode();

    HttpStatus getHttpStatus();

    String getMessage();
}
