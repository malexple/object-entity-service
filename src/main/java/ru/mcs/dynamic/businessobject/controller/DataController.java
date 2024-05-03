package ru.mcs.dynamic.businessobject.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.mcs.dynamic.businessobject.dto.request.DataEntityRequest;
import ru.mcs.dynamic.businessobject.service.ObjectEntityService;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/api/data")
public class DataController {

    private final ObjectEntityService objectEntityService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createData(@RequestBody DataEntityRequest value) {
        objectEntityService.createDataEntity(value);
    }
}
