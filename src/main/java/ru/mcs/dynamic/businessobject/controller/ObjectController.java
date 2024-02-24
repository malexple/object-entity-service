package ru.mcs.dynamic.businessobject.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.mcs.dynamic.businessobject.dto.request.ObjectEntityRequest;
import ru.mcs.dynamic.businessobject.dto.response.ObjectEntityResponse;
import ru.mcs.dynamic.businessobject.service.ObjectEntityService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/object")
public class ObjectController {

    private final ObjectEntityService objectEntityService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ObjectEntityResponse createProduct(@RequestBody ObjectEntityRequest objectEntityRequest) {
        return objectEntityService.createObjectEntity(objectEntityRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ObjectEntityResponse> getAllProducts() {
        return objectEntityService.getAllObjectEntity();
    }

}
