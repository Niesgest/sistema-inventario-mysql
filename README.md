#  Sistema de Gestión de Inventario (CRUD + MySQL)

Aplicación por consola desarrollada en **Java** conectada a una base de datos relacional **MySQL** mediante **JDBC**. El sistema permite realizar el ciclo completo de operaciones **CRUD** (*Create, Read, Update, Delete*) para administrar un catálogo de productos e inventario en tiempo real.

---

##  Funcionalidades

* **Registrar Producto (Create):** Inserción segura de nuevos registros con nombre, precio unitario y cantidad en stock.
* **Listar Productos (Read):** Consulta y visualización tabular formateada en consola con alineación de columnas y valores monetarios en tiempo real.
* **Actualizar Stock (Update):** Modificación del stock disponible de un producto específico mediante su identificador único (`ID`).
* **Eliminar Producto (Delete):** Borrado físico de registros de la base de datos por `ID`.

---

##  Tecnologías y Conceptos Aplicados

* **Lenguaje:** Java (Java 17 o superior).
* **Motor de Base de Datos:** MySQL / MariaDB (vía XAMPP).
* **Conectividad:** JDBC (`mysql-connector-j`).
* **Seguridad & Buenas Prácticas:**
  * Uso de `PreparedStatement` para prevenir ataques de **Inyección SQL** (*SQL Injection*).
  * Patrón `try-with-resources` para garantizar el cierre automático y seguro de conexiones (`Connection`, `Statement`, `ResultSet`), evitando fugas de memoria.
  * Delegación y control de excepciones SQL con `throws SQLException` y bloques `try-catch`.
* **Formateo Avanzado:** Uso de `System.out.printf` con especificadores de formato para alineación precisa de tablas en terminal.

---

##  Estructura de la Base de Datos

Ejecuta el siguiente script en tu gestor de base de datos (phpMyAdmin / MySQL Workbench):

```sql 
CREATE DATABASE IF NOT EXISTS inventario_db;
USE inventario_db;

CREATE TABLE IF NOT EXISTS productos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    precio DOUBLE NOT NULL,
    stock INT NOT NULL
);
```

---

##  Cómo Ejecutar el Proyecto

Requisitos previos:

Tener instalado el Java Development Kit (JDK 17 o superior).

Tener un servidor MySQL activo (por ejemplo, mediante XAMPP con los servicios de Apache y MySQL iniciados).

Conector `mysql-connector-j` configurado en el proyecto.

##  Instrucciones de ejecución

Clonar el repositorio:

git clone `[https://github.com/Niesgest/sistema-inventario-mysql.git](https://github.com/Niesgest/sistema-inventario-mysql.git)`

Abrir en tu IDE preferido (IntelliJ IDEA recomendado):

Asegúrate de que la dependencia de MySQL Connector esté vinculada al proyecto.

Ejecuta la clase `InventarioApp.java`.

👤 Autor
Desarrollador: Jose Lozada

GitHub: @Niesgest

LinkedIn: https://www.linkedin.com/in/jose-armando-lozada-aguirre-81b6283b2/
