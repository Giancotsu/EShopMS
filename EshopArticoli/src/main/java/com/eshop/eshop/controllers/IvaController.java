package com.eshop.eshop.controllers;

import com.eshop.eshop.dto.IvaDto;
import com.eshop.eshop.service.IvaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/iva")
public class IvaController {

    private final IvaService ivaService;

    public IvaController(IvaService ivaService) {
        this.ivaService = ivaService;
    }

    @GetMapping
    public ResponseEntity<List<IvaDto>> getAllIva(){
        return new ResponseEntity<>(ivaService.getAllIva(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<IvaDto> getIvaById(@PathVariable("id") long id){

        return new ResponseEntity<>(ivaService.getIvaById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<IvaDto> createIva(@RequestBody IvaDto ivaDto){

        return new ResponseEntity<>(ivaService.createIva(ivaDto), HttpStatus.CREATED);
    }
}
