package com.juliobalbino.estoquerefrigerante.controller;

import com.juliobalbino.estoquerefrigerante.dto.SodaDTO;
import com.juliobalbino.estoquerefrigerante.dto.QuantityDTO;
import com.juliobalbino.estoquerefrigerante.exception.SodaAlreadyRegisteredException;
import com.juliobalbino.estoquerefrigerante.exception.SodaNotFoundException;
import com.juliobalbino.estoquerefrigerante.exception.SodaStockExceededException;
import com.juliobalbino.estoquerefrigerante.service.SodaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/sodas")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SodaController implements SodaControllerDocs {

    private final SodaService sodaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SodaDTO createSoda(@RequestBody @Valid SodaDTO sodaDTO) throws SodaAlreadyRegisteredException {
        return sodaService.createSoda(sodaDTO);
    }

    @GetMapping("/{name}")
    public SodaDTO findByName(@PathVariable String name) throws SodaNotFoundException {
        return sodaService.findByName(name);
    }

    @GetMapping
    public List<SodaDTO> listSodas() {
        return sodaService.listAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws SodaNotFoundException {
        sodaService.deleteById(id);
    }

    @PatchMapping("/{id}/increment")
    public SodaDTO increment(@PathVariable Long id, @RequestBody @Valid QuantityDTO quantityDTO) throws SodaNotFoundException, SodaStockExceededException {
        return sodaService.increment(id, quantityDTO.getQuantity());
    }

    @PatchMapping("/{id}/decrement")
    public SodaDTO decrement(@PathVariable Long id, @RequestBody @Valid QuantityDTO quantityDTO) throws SodaNotFoundException, SodaStockExceededException {
        return sodaService.decrement(id, quantityDTO.getQuantity());
    }
}
