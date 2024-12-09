package controllers;

import entities.produto.Produto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import service.ProductService;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping(value = "/produtos")
public class ProductResource {
    private ProductService service;

    @GetMapping
    public ResponseEntity<List<Produto>> getAll() {
        List<Produto> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<Produto> insert(@RequestBody Produto obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }


    @DeleteMapping(value = "{/id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "{/id}")
    public ResponseEntity<Produto> update(@PathVariable Long id, @RequestBody Produto obj){
        service.update(id,obj);
        return ResponseEntity.ok().body(obj);

    }
}
