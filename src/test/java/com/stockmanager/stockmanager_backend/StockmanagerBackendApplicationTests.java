package com.stockmanager.stockmanager_backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class StockmanagerBackendApplicationTests {

	// ======================================
	// Test 1 : Vérifie que le contexte Spring Boot se charge
	// ======================================
	@Test
	void contextLoads() {
		// Si l'application démarre sans exception, le test passe
	}

	// ======================================
	// Test 2 : Vérifie la connexion à la base de données
	// ======================================

	@Test
	void testConnection() throws SQLException {
		try (Connection conn = DriverManager.getConnection(
				"jdbc:postgresql://localhost:5432/stockmanager_db",
				"postgres",
				"postgres")) {
			assertFalse(conn.isClosed(), "La connexion à la DB ne doit pas être fermée");
			System.out.println("Connected to DB: " + conn.getMetaData().getURL());
		}
	}
}

// package com.stockmanager.stockmanager_backend;

// import org.junit.jupiter.api.Test;
// import org.springframework.boot.test.context.SpringBootTest;

// @SpringBootTest
// class StockmanagerBackendApplicationTests {

// @Test
// void contextLoads() {
// }

// }