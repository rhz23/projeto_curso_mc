package com.rzaninelli.cursomc.services;

import com.rzaninelli.cursomc.domain.Categoria;
import com.rzaninelli.cursomc.repositories.CategoriaRepository;
import com.rzaninelli.cursomc.services.exceptions.DataIntegrityException;
import com.rzaninelli.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria find(Integer id){

        Optional<Categoria> obj = categoriaRepository.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + "Tipo: " + Categoria.class.getName()));
    }

    public List<Categoria> findAll(){
        return categoriaRepository.findAll();
    }

    public Categoria insert(Categoria obj){
        obj.setId(null);
        return categoriaRepository.save(obj);
    }

    public Categoria update(Categoria obj){
        find(obj.getId());
        return categoriaRepository.save(obj);
    }

    public void delete(Integer id){
        find(id);
        try {
            categoriaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos!");
        }
    }

    public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return categoriaRepository.findAll(pageRequest);
    }
}
