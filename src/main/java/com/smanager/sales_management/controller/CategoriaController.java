package com.smanager.sales_management.controller;

import com.smanager.sales_management.entity.Categoria;
import com.smanager.sales_management.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @Operation(summary = "Find all categories")
    @GetMapping
    public List<Categoria> findAll() {
        return categoriaService.findAll();
    }

    @Operation(summary = "Find category by id")
    @GetMapping("/{codigo}")
    public ResponseEntity<Optional<Categoria>> findById(@PathVariable Long codigo) {
        Optional<Categoria> categoria = categoriaService.findById(codigo);
        if (categoria.isPresent()) {
            return ResponseEntity.ok(categoria);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Save a new category")
    @PostMapping
    public ResponseEntity<Categoria> save(@Valid @RequestBody Categoria categoria) {
        Categoria savedCategoria = categoriaService.save(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategoria);
    }

    @Operation(summary = "Delete category by id")
    @PutMapping("/{codigo}")
    public ResponseEntity<Categoria> update(@PathVariable Long codigo, @Valid @RequestBody Categoria categoria) {
        return ResponseEntity.ok(categoriaService.update(codigo, categoria));
    }
}