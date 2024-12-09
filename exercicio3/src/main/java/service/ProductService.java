package service;

import entities.produto.Produto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import repository.ProdutosRepository;
import service.exceptions.DatabaseException;
import service.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public class ProductService {
    private ProdutosRepository produtosRepository;

    public List<Produto> findAll(){
        return produtosRepository.findAll();
    }
    public Produto findById(Long id){
        Optional<Produto> obj = produtosRepository.findById(id);
        return obj.orElseThrow(()-> new ResourceNotFoundException(id));
    }

    public Produto insert(Produto obj){
        return produtosRepository.save(obj);
    }

    public Produto update(Long id, Produto produto){
       try {
           Produto entity = produtosRepository.getReferenceById(id);
           updateData(entity, produto);
           return produtosRepository.save(entity);
        }catch (EntityNotFoundException e ){
           throw new ResourceNotFoundException(id);
       }
       }

    private void updateData(Produto entity, Produto produto) {
        produto.setNome(entity.getNome());
        produto.setPreco(entity.getPreco());
    }

    public void delete(Long id){
        try{
    produtosRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        }catch(DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }
}
