package inventario;

import java.sql.*;
import java.util.Scanner;

public class inventarioApp {
    private static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    public static final String URL = "jdbc:mysql://localhost:3306/inventario_db";
    public static final String USER = "root";
    public static final String PASSWORD = "";

    public static void main(String[] args) {
        var consola = new Scanner(System.in);
        var opcion = 0;

        while (opcion != 5) {
            System.out.print("""
                    ---- CONTROL DE INVENTARIO ----
                    1. Registrar producto
                    2. Listar productos
                    3. Actualizar stock de un producto
                    4. Eliminar un producto
                    5. Salir
                    Seleccione una opcion:\s""");

            try {
                opcion = Integer.parseInt(consola.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un numero del 1 al 5");
                continue;
            }
            switch (opcion) {
                case 1 -> {
                    System.out.print("Nombre: ");
                    var nombre = consola.nextLine();
                    System.out.print("Precio: ");
                    var precio = Double.parseDouble(consola.nextLine());
                    System.out.print("Stock: ");
                    var stock = Integer.parseInt(consola.nextLine());

                    insertarProducto(nombre, precio, stock);
                }
                case 2 -> listarProductos();
                case 3 -> {
                    System.out.print("Ingrese el ID del producto: ");
                    var id = Integer.parseInt(consola.nextLine());
                    System.out.print("Ingrese el nuevo stock: ");
                    var nuevoStock = Integer.parseInt(consola.nextLine());
                    actualizarStock(id, nuevoStock);
                }
                case 4 -> {
                    System.out.print("Ingrese el ID del producto a eliminar: ");
                    var id = Integer.parseInt(consola.nextLine());
                    eliminarProducto(id);
                }
                case 5 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opcion invalida. Por favor intentelo nuevamente");
            }
        }
    }

    public static void insertarProducto(String nombre, double precio, int stock){

        var sql = "INSERT INTO productos (nombre, precio, stock) VALUES (?,?,?)";

        try (var con = obtenerConexion();
        var ps = con.prepareStatement(sql)){

            ps.setString(1, nombre);
            ps.setDouble(2, precio);
            ps.setInt(3, stock);

            int filasAfectadas = ps.executeUpdate();

            if(filasAfectadas > 0){
                System.out.println("Producto almacenado con exito");
            }


        } catch (SQLException e) {
            System.out.println("Error al tratar de conectarse a la base: " + e.getMessage());
        }

    }

    public static void listarProductos(){
        var sql = "SELECT id, nombre, precio, stock FROM productos";

        try (var con = obtenerConexion();
        var ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()){

            System.out.println("\n--- LISTADO DE PRODUCTOS EN BD ---");
            System.out.printf("%-5s %-20s %-10s %-8s%n", "ID","NOMBRE","PRECIO","STOCK");
            System.out.println("-----------------------------------------------");

            boolean hayRegistros = false;

            while (rs.next()) {
                hayRegistros = true;
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                double precio = rs.getDouble("precio");
                int stock = rs.getInt("stock");

                System.out.printf("%-5s %-20s %-10s %-8s%n", id, nombre, precio, stock);
            }

            if (!hayRegistros) {
                System.out.println("No hay productos en el inventario");
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar los productos: " + e.getMessage());
        }
    }

    public static void actualizarStock(int id, int nuevoStock) {
        var sql = "UPDATE productos SET stock = ? WHERE id = ?";

        try (var con = obtenerConexion();
        var ps = con.prepareStatement(sql)) {

            ps.setInt(1, nuevoStock);
            ps.setInt(2, id);

            int filas = ps.executeUpdate();
            if(filas > 0) {
                System.out.println("Stock actualizado correctamente");
            } else {
                System.out.println("No se encontró ningun producto con ID: " + id);
            }

        } catch (SQLException e) {
            System.out.println("Error al actualizar: " + e.getMessage());
        }
    }

    public static void eliminarProducto(int id) {
        var sql = "DELETE FROM productos WHERE id = ?";

        try (var con = obtenerConexion();
        var ps = con.prepareStatement(sql)){

            ps.setInt(1, id);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Producto eliminado de la base de datos");
            } else {
                System.out.println("No se encontro ningun producto con ID: " + id);
            }

        } catch (SQLException e) {
            System.out.println("Error al eliminar: " + e.getMessage());
        }
    }



}
