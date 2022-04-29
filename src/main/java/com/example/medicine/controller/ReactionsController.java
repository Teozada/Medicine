package com.example.medicine.controller;

import com.example.medicine.dto.ReactionsDto;
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

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/")
public class ReactionsController {
    @Autowired
    ReactionsService reactionsService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Reactions> createdReactions(@RequestBody ReactionsDto.Create reacionsDto) {
        Reactions reactions = new Reactions();
        reactions.setDescription(reacionsDto.description);
        reactionsService.createReactions(reactions);
       return new ResponseEntity<>(reactions,HttpStatus.CREATED);
    }

    @PostMapping("reactions")
    @ResponseStatus(HttpStatus.CREATED)
    public Reactions createdReactions(@RequestBody Reactions reactions) {
        return reactionsService.createReactions(reactions);
    }

    @ApiOperation(value = "Atualizando descrição")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Reação atualizada com sucesso"),
            @ApiResponse(code = 404, message = "Não foi possível atualizar a tarefa")
    })
    @PutMapping("/reactions/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Reactions> updateReactionsId(@PathVariable (value = "id")Long id, @RequestBody Reactions reactions) {
        return reactionsService.updateReactinosId(reactions,id);
    }


    @ApiOperation(value = "Excluindo descrição")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Descrição excluída com sucesso"),
            @ApiResponse(code = 404,message = "Não foi possível excluir a descrição")
    })
    @DeleteMapping("/reactions/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteReactionsById(@PathVariable (value = "id")Long id) {
        reactionsService.deleteById(id);
    }
}
