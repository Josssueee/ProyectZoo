import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;


public class ProyectZoo{

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        while (opcion != 4) {
            mostrarMenuPrincipal();
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    instruccionUno();
                    break;
                case 2:
                    instruccionDos();
                    break;
                case 3:
                    instruccionTres();
                    break;
                case 4:
                    System.out.println("************************");
                    System.out.println("Saliendo del programa...");
                    System.out.println("************************");
                    break;
                default:
                    System.out.println("*********************************************************");
                    System.out.println("Opción inválida. Por favor, selecciona una opción válida.");
                    System.out.println("*********************************************************");
                    break;
            }
        }
    }

    public static void mostrarMenuPrincipal() {

        System.out.println("************************************************************************");
        System.out.println("MMMMMMM    MMMM     MMMM    MMMMMM  MM    MMMM   MM  MMM   MMMMM  MMMMMM");
        System.out.println("    MM    MM  MM   MM  MM     MM    MM   MM      MM MM     MM       MM  ");
        System.out.println("  MM      MM  MM   MM  MM     MM    MM   MM      MMM MM    MMMM     MM  ");
        System.out.println("MM        MM  MM   MM  MM     MM    MM   MM      MM   MM   MM       MM  ");
        System.out.println("MMMMMMM    MMMM     MMMM      MM    MM    MMMM   MM   MM   MMMMM    MM  ");
        System.out.println("************************************************************************");


        System.out.println("***********************************************************************");
        System.out.println("*                    MENÚ PRINCIPAL                                   *");
        System.out.println("***********************************************************************");
        System.out.println("*                 1. Ingresar datos                                   *");
        System.out.println("*                 2. Validar Ticket                                   *");
        System.out.println("*                 3. Modificar o Consultar                            *");
        System.out.println("*                 4. Salir del programa                               *");
        System.out.println("*                   Elige una opción:                                 *");
        System.out.println("\n*********************************************************************");
    }

    public static void instruccionUno() {
        //AREA DE INGRESO DE DATOS
        System.out.println("****************");
        System.out.println("INGRESO DE DATOS");
        System.out.println("****************");
        int precio;
        String nombre, tipo, validez;

        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingresa un nombre: ");
        nombre = scanner.nextLine();

        System.out.println("**********************************");
        System.out.println("¿Qué tipo de ticket desea comprar?");
        System.out.println("1. Individual");
        System.out.println("2. Pareja (2 personas)");
        System.out.println("3. Grupal (3 personas)");
        System.out.println("4. Familiar (5 personas)");
        System.out.println("5. Familiar (10 personas)");
        System.out.println("**********************************");
        System.out.println("");

        int opcionTipo = scanner.nextInt();
        scanner.nextLine(); // Limpiar el scanner

        switch (opcionTipo) {
            case 1:
                tipo = "Individual";
                precio = 50;
                break;
            case 2:
                tipo = "Pareja (2 personas)";
                precio = 75;
                break;
            case 3:
                tipo = "Grupal (3 personas)";
                precio = 100;
                break;
            case 4:
                tipo = "Familiar (5 personas)";
                precio = 175;
                break;
            case 5:
                tipo = "Familiar (10 personas)";
                precio = 300;
                break;
            default:
                System.out.println("Opción inválida. Selecciona un tipo de ticket válido.");
                return; // Regresar al menú principal
        }

        validez = "Válido";
        //MOSTRAR INFORMACIÓN EN PANTALLA
        System.out.println("************************");
        System.out.println("Información a registrar:");
        System.out.println("Nombre: " + nombre);
        System.out.println("Plan de ticket: " + tipo);
        System.out.println("Total: " + precio);
        System.out.println("Validez: " + validez);
        System.out.println("************************");
        System.out.println("¿Qué desea hacer?");
        System.out.println("1. Registrar");
        System.out.println("2. Cancelar");
        System.out.println("");

        int opcionRegistrar = scanner.nextInt();
        scanner.nextLine();

        if (opcionRegistrar == 1) {
            // Conexión a la base de datos
            String url = "jdbc:postgresql://localhost:5432/Zoo";
            String username = "postgres";
            String password = "root";

            try {
                Connection connection = DriverManager.getConnection(url, username, password);

                // Insertar los datos en la tabla infozoo
                String sql = "INSERT INTO infozoo (nombre, tipo, precio, validez) VALUES (?, ?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, nombre);
                statement.setString(2, tipo);
                statement.setInt(3, precio);
                statement.setString(4, validez);

                statement.executeUpdate();

                System.out.println("Guardado con éxito");
                System.out.println("\n\n\n\n");

                // Cerrar la conexión
                statement.close();
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error al conectar con la base de datos: " + e.getMessage());
            }
        } else {
            System.out.println("Operación cancelada");
        }

    }

    public static void instruccionDos() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("******************");
        System.out.println("VALIDACIÓN TICKETS");
        System.out.println("******************");

        int verif_codigo = 0;
        String verif_nombre, verif_tipo, verif_validez;

        System.out.println("Por favor ingresa el código que deseas verificar");
        verif_codigo = scanner.nextInt();


        System.out.println("_______________________________________________________________________");
        System.out.println("Presione 1. Para |BUSCAR| ticket o presione 2. Para |CANCELAR| búsqueda");
        System.out.println("_______________________________________________________________________");
        int opcion = scanner.nextInt();

        if (opcion == 1) {
            try {
                // Establecer la conexión con la base de datos PostgreSQL
                Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost/Zoo", "postgres", "root");
                Statement statement = connection.createStatement();

                // Consulta SQL para obtener la información del ticket
                String query = "SELECT nombre, tipo, validez FROM infozoo WHERE codigo = " + verif_codigo;
                ResultSet resultSet = statement.executeQuery(query);

                if (resultSet.next()) {
                    verif_nombre = resultSet.getString("nombre");
                    verif_tipo = resultSet.getString("tipo");
                    verif_validez = resultSet.getString("validez");

                    System.out.println("*************************");
                    System.out.println("Información");
                    System.out.println("Nombre: " + verif_nombre);
                    System.out.println("Tipo: " + verif_tipo);
                    System.out.println("Ticket: " + verif_validez);
                    System.out.println("*************************");

                    if (verif_validez.equals("Inválido")) {
                        System.out.println("El ticket no puede ser confirmado porque ya fue utilizado");
                        System.out.println("");
                    } else if (verif_validez.equals("Válido")) {
                        System.out.println("________________________________________________________");
                        System.out.println("¿Desea confirmar el uso del ticket " + verif_codigo + "?");
                        System.out.println("1. Confirmar");
                        System.out.println("2. Cancelar");
                        System.out.println();
                        opcion = scanner.nextInt();

                        if (opcion == 1) {
                            // Actualizar el estado del ticket a "Inválido"
                            String updateQuery = "UPDATE infozoo SET validez = 'Inválido' WHERE codigo = " + verif_codigo;
                            statement.executeUpdate(updateQuery);

                            System.out.println("Ticket confirmado exitosamente");
                            System.out.println("\n\n\n\n");
                        }
                    }
                } else {
                    System.out.println("El código ingresado no existe en la base de datos");
                }

                // Cerrar la conexión con la base de datos
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void instruccionTres() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("*********************");
        System.out.println("MODIFICAR O CONSULTAR");
        System.out.println("*********************");

        int verif_codigo2 = 0;
        int verif_total2 = 0;
        String verif_nombre2, verif_tipo2, verif_validez2;

        System.out.println("Por favor ingresa el código que deseas consultar");
        verif_codigo2 = scanner.nextInt();

        System.out.println("_______________________________________________________________________");
        System.out.println("Presione 1. Para |BUSCAR| ticket o presione 2. Para |CANCELAR| búsqueda");
        System.out.println("_______________________________________________________________________");
        int opcion = scanner.nextInt();

        if (opcion == 1) {
            try {
                // Establecer la conexión con la base de datos PostgreSQL
                Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost/Zoo", "postgres", "root");
                Statement statement = connection.createStatement();

                // Consulta SQL para obtener la información del ticket
                String query = "SELECT nombre, tipo, precio, validez FROM infozoo WHERE codigo = " + verif_codigo2;
                ResultSet resultSet = statement.executeQuery(query);

                if (resultSet.next()) {
                    verif_nombre2 = resultSet.getString("nombre");
                    verif_tipo2 = resultSet.getString("tipo");
                    verif_total2 = resultSet.getInt("precio");
                    verif_validez2 = resultSet.getString("validez");

                    System.out.println("************************");
                    System.out.println("Información");
                    System.out.println("Nombre: " + verif_nombre2);
                    System.out.println("Tipo: " + verif_tipo2);
                    System.out.println("Total: " + verif_total2);
                    System.out.println("Ticket: " + verif_validez2);
                    System.out.println("************************");


                    if (verif_validez2.equals("Inválido")) {
                        System.out.println("El ticket fue confirmado y no puede ser modificado");
                        System.out.println("\n\n\n\n");
                    } else if (verif_validez2.equals("Válido")) {
                        String new_plan;
                        int new_tipo, new_precio = 0;

                        System.out.println("**********************************");
                        System.out.println("¿Qué tipo de ticket desea comprar?");
                        System.out.println("1. Individual");
                        System.out.println("2. Pareja (2 personas)");
                        System.out.println("3. Grupal (3 personas)");
                        System.out.println("4. Familiar (5 personas)");
                        System.out.println("5. Familiar (10 personas)");
                        System.out.println("**********************************");
                        System.out.println();
                        opcion = scanner.nextInt();

                        switch (opcion) {
                            case 1:
                                new_plan = "Individual";
                                new_precio = 50;
                                break;
                            case 2:
                                new_plan = "Pareja 2 personas";
                                new_precio = 75;
                                break;
                            case 3:
                                new_plan = "Grupal 3 personas";
                                break;
                            case 4:
                                new_plan = "Familiar 5 personas";
                                new_precio = 175;
                                break;
                            case 5:
                                new_plan = "Familiar 10 personas";
                                new_precio = 300;
                                break;
                            default:
                                System.out.println("Opción inválida");
                                return;
                        }

                        System.out.println("****************************************************************");
                        System.out.println("El nuevo plan es " + new_plan + " y el total es de " + new_precio);
                        System.out.println("________________________________________________________________");
                        System.out.println("Presione 1. Para |MODIFICAR| o 2. Para |CANCELAR|");
                        System.out.println("________________________________________________________________");
                        opcion = scanner.nextInt();

                        if (opcion == 1) {
                            // Actualizar el tipo y precio del ticket
                            String updateQuery = "UPDATE infozoo SET tipo = '" + new_plan + "', precio = " + new_precio + " WHERE codigo = " + verif_codigo2;
                            statement.executeUpdate(updateQuery);

                            System.out.println("Ticket modificado con éxito");
                            System.out.println("\n\n\n\n");
                        }
                    }
                } else {
                    System.out.println("El código ingresado no existe en la base de datos");
                    System.out.println("\n\n\n\n");
                }

                // Cerrar la conexión con la base de datos
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
