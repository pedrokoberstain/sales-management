package com.smanager.sales_management.service;

import com.smanager.sales_management.entity.Categoria;
import com.smanager.sales_management.exception.BusinessRuleException;
import com.smanager.sales_management.repository.CategoriaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> findById(Long id) {
        return categoriaRepository.findById(id);
    }

    public Categoria save(Categoria categoria) {
        validDuplicateCategoria(categoria);
        return categoriaRepository.save(categoria);
    }

    public Categoria update(Long codigo, Categoria categoria) {
        Categoria categoriaSave = validCategoria(codigo);
        validDuplicateCategoria(categoria);
        BeanUtils.copyProperties(categoria, categoriaSave, "codigo");
        return categoriaRepository.save(categoriaSave);
    }

    public void delete(Long codigo) {
        Categoria categoria = validCategoria(codigo);
        categoriaRepository.delete(categoria);
    }

    private Categoria validCategoria(Long codigo) {
        Optional<Categoria> categoria = findById(codigo);
        if (categoria.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        }
        return categoria.get();
    }

    private void validDuplicateCategoria(Categoria categoria) throws BusinessRuleException {
        Categoria categoriaDuplicate = categoriaRepository.findByName(categoria.getName());
        if (categoriaDuplicate != null && !categoriaDuplicate.getCodigo().equals(categoria.getCodigo())) {
            throw new BusinessRuleException(String.format("A categoria %s já está cadastrada", categoria.getName().toUpperCase()));
        }
    }
}
