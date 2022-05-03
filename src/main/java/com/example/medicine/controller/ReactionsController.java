package com.example.medicine.controller;

import com.example.medicine.dto.MedicineDto;
import com.example.medicine.dto.ReactionsDto;
import com.example.medicine.model.Medicine;
import com.example.medicine.model.Reactions;
import com.example.medicine.service.ReactionsService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/reactions")
public class ReactionsController {
    @Autowired
    ReactionsService reactionsService;

    @ApiOperation(value = "Criando descrição")
    @ApiResponses(value = {
            @ApiResponse(code = 204,
                    message = "Reação criada com sucesso"),
            @ApiResponse(code = 404,
                    message = "Não foi possível criar a tarefa")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Reactions> createdReactions(@RequestBody ReactionsDto.Create reactionsDto) {
       final Reactions reactions = this.reactionsService.createReactions(reactionsDto.toModel());
       return new ResponseEntity<>(reactions,HttpStatus.CREATED);
    }

    @ApiOperation(value = "Buscando descrição")
    @ApiResponses(value = {
            @ApiResponse(code = 204,
                    message = "Reação buscada com sucesso"),
            @ApiResponse(code = 404,
                    message = "Não foi possível buscar a tarefa")
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Reactions>> getAllReactions() {
        final List<Reactions> reactions = reactionsService.listAllReactions();
        return ResponseEntity.ok(reactions);
    }

    @ApiOperation(value = "Listando reação cadastrada")
    @ApiResponses(value = {
            @ApiResponse(code = 204,
                    message = "Reação listada com sucesso"),
            @ApiResponse(code = 404,
                    message = "Não foi possível listar a reação")
    })
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity getReactionsId(@PathVariable (value = "id")Long id) {
        Optional<Reactions> found = reactionsService.findReactionsById(id);
        if(found.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(found.get());
    }

    @ApiOperation(value = "Atualizando descrição")
    @ApiResponses(value = {
            @ApiResponse(code = 204,
                    message = "Reação atualizada com sucesso"),
            @ApiResponse(code = 404,
                    message = "Não foi possível atualizar a tarefa")
    })
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity updateReactionsId(@PathVariable (value = "id")Long id,
                                           @RequestBody ReactionsDto.Update reactionsDto) {
        reactionsService.updateReactinosId(reactionsDto.toModel(),id);
        return ResponseEntity.ok().build();
    }


    @ApiOperation(value = "Excluindo descrição")
    @ApiResponses(value = {
            @ApiResponse(code = 204,
                    message = "Descrição excluída com sucesso"),
            @ApiResponse(code = 404,
                    message = "Não foi possível excluir a descrição")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteReactionsById(@PathVariable (value = "id")Long id) {
        reactionsService.deleteById(id);
    }
}
