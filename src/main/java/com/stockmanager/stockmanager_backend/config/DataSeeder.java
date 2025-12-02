package com.stockmanager.stockmanager_backend.config;

import com.stockmanager.stockmanager_backend.model.Product;
import com.stockmanager.stockmanager_backend.model.User;
import com.stockmanager.stockmanager_backend.repository.ProductRepository;
import com.stockmanager.stockmanager_backend.repository.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataSeeder {

  @Bean
  CommandLineRunner initDatabase(ProductRepository productRepository,
      UserRepository userRepository,
      PasswordEncoder passwordEncoder) {
    return args -> {

      if (productRepository.count() == 0) { // Évite de dupliquer
        Product p1 = new Product();
        p1.setName("Clavier mécanique");
        p1.setCategory("Informatique");
        p1.setQuantity(20);
        p1.setPrice(89.99);
        p1.setMinStock(5);
        p1.setDescription("Clavier RGB");

        Product p2 = new Product();
        p2.setName("Souris gamer");
        p2.setCategory("Informatique");
        p2.setQuantity(10);
        p2.setPrice(49.99);
        p2.setMinStock(3);
        p2.setDescription("Souris haute précision");

        productRepository.save(p1);
        productRepository.save(p2);

        System.out.println("📦 Seed produits insérés !");
      }

      // Seed utilisateur admin
      if (userRepository.count() == 0) {
        User admin = new User();
        admin.setUsername("admin");
        admin.setEmail("admin@stockmanager.com");
        admin.setPassword(passwordEncoder.encode("admin123")); // hash du mot de passe
        userRepository.save(admin);

        System.out.println("👤 Utilisateur admin inséré !");
      }
    };
  }
}