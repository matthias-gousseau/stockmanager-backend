package com.stockmanager.stockmanager_backend.repository;

import com.stockmanager.stockmanager_backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Trouver tous les produits dont la quantité est inférieure ou égale à une valeur donnée
    List<Product> findByQuantityLessThanEqual(Integer quantity);

    // Trouver tous les produits d'une catégorie spécifique
    List<Product> findByCategoryIgnoreCase(String category);
}
