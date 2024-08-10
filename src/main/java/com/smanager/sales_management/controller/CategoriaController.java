package com.smanager.sales_management.controller;

import com.smanager.sales_management.entity.Categoria;
import com.smanager.sales_management.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<Categoria> findAll() {
        return categoriaService.findAll();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Optional<Categoria>> findById(@PathVariable Long codigo) {
        Optional<Categoria> categoria = categoriaService.findById(codigo);
        if (categoria.isPresent()) {
            return ResponseEntity.ok(categoria);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Categoria> save(@RequestBody Categoria categoria) {
        Categoria savedCategoria = categoriaService.save(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategoria);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Categoria> update(@PathVariable Long codigo, @RequestBody Categoria categoria) {
        return ResponseEntity.ok(categoriaService.update(codigo, categoria));
    }
}
