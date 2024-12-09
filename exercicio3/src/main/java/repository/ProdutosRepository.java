package repository;

import entities.produto.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutosRepository extends JpaRepository<Produto , Long > {

}
