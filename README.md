# 📦 StockManager - Backend

API REST pour application de gestion d'inventaire développée avec Spring Boot 3 et Java 17.

## 🛠️ Technologies

- **Java 17**
- **Spring Boot 3.2**
- **Spring Security + JWT**
- **Spring Data JPA**
- **PostgreSQL**
- **Maven**

## 🚀 Installation

### Prérequis
- Java 17 ou supérieur
- Maven 3.6+
- PostgreSQL 16 (ou Docker)

### Base de données

#### Option 1 : Docker (recommandé)
```bash
docker run --name postgres-stock \
  -e POSTGRES_DB=stockmanager \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres \
  -p 5432:5432 \
  -d postgres:15
```

#### Option 2 : PostgreSQL local
Créer une base de données nommée `stockmanager`

### Lancement
```bash
# Cloner le projet
git clone https://github.com/votre-username/stock-manager-backend.git
cd stock-manager-backend

# Configurer la base de données dans src/main/resources/application.properties

# Installer les dépendances
./mvnw clean install

# Lancer l'application
./mvnw spring-boot:run
```

L'API sera disponible sur : http://localhost:8080

## 📚 API Endpoints

### Authentification
- `POST /api/auth/register` - Inscription
- `POST /api/auth/login` - Connexion

### Produits
- `GET /api/products` - Liste des produits
- `GET /api/products/{id}` - Détail d'un produit
- `POST /api/products` - Créer un produit
- `PUT /api/products/{id}` - Modifier un produit
- `DELETE /api/products/{id}` - Supprimer un produit
- `GET /api/products/low-stock` - Produits en stock bas

### Fournisseurs
- `GET /api/suppliers` - Liste des fournisseurs
- (autres endpoints à venir)

## 🧪 Tests
```bash
./mvnw test
```

## 📝 Configuration

Modifier `src/main/resources/application.properties` pour :
- Base de données
- Port du serveur
- Clé secrète JWT
- CORS

## 🔗 Frontend

Frontend Angular disponible ici : [stock-manager-frontend](https://github.com/matthias-gousseau/stock-manager-frontend)

## 👨‍💻 Auteur

**Matthias Gousseau**
