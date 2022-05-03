package com.example.medicine.controller;


import com.example.medicine.dto.MedicineDto;
import com.example.medicine.dto.ReactionsDto;
import com.example.medicine.model.Medicine;
import com.example.medicine.model.Reactions;
import com.example.medicine.service.MedicineService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/medicine")
public class MedicineController {
    @Autowired
    MedicineService medicineService;

    @ApiOperation(value = "Cadastrando medicamentos")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Medicamento cadastrado com sucesso"),
            @ApiResponse(code = 204, message = "Falha ao cadastrar medicamento")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Medicine> createMedicine (@RequestBody MedicineDto.CreateOrUpdate medicineDto) {

        final Medicine medicine = medicineService.createMedicine(medicineDto.toModel());
        return new ResponseEntity<>(medicine,HttpStatus.CREATED);
    }

    @ApiOperation(value = "Listando medicamentos cadastrados")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Medicamentos listados com sucesso"),
            @ApiResponse(code = 404, message = "Não foi possível listar os medicamentos")
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Medicine>> getAllMedicine() {
        final List<Medicine> medicine = medicineService.listAllMedicine();
        return ResponseEntity.ok(medicine);
    }

    @ApiOperation(value = "Listando medicamento cadastrado")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Medicamento listado com sucesso"),
            @ApiResponse(code = 404, message = "Não foi possível listar o medicamento")
    })
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity getMedicineId(@PathVariable (value = "id")Long id) {
        Optional<Medicine> found = medicineService.findMedicineById(id);
        if(found.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(found.get());
    }

    @ApiOperation(value = "Atualizando medicamento")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Medicamento atualizado com sucesso"),
            @ApiResponse(code = 404, message = "Não foi possível atualizar o medicamento")
    })
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity updateMedicineId(@PathVariable (value = "id")Long id,
                                           @RequestBody MedicineDto.CreateOrUpdate medicineDto) {
        medicineService.updateMedicineId(medicineDto.toModel(),id);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Atualizando medicamento")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Medicamento atualizado com sucesso"),
            @ApiResponse(code = 404, message = "Não foi possível atualizar o medicamento")
    })
    @PatchMapping("/{id}/addreactions")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity updateReactionsId(@PathVariable (value = "id")Long id,
                                           @RequestBody MedicineDto.AddRemoveReactions reactionsIds) {
        System.out.println(reactionsIds.reactionsIds);
        System.out.println("AQUI");
        medicineService.addReactions(id,reactionsIds.reactionsIds);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Atualizando medicamento")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Medicamento atualizado com sucesso"),
            @ApiResponse(code = 404, message = "Não foi possível atualizar o medicamento")
    })
    @PatchMapping("/{id}/removereactions")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity removeReactionsId(@PathVariable (value = "id")Long id,
                                            @RequestBody MedicineDto.AddRemoveReactions reactionsIds) {
        medicineService.removeReactions(id,reactionsIds.reactionsIds);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Excluindo medicamento")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Medicamento excluído com sucesso"),
            @ApiResponse(code = 404, message = "Não foi possível excluir o medicamento")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteMedicineById(@PathVariable (value = "id")Long id) {
        medicineService.deleteById(id);
    }
}
