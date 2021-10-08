package bravi.jpa;

import java.util.Scanner;

import bravi.jpa.Dominio.Factory.Factory;

public class App {
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        Factory f = new Factory();
        int op = 0;
        while (op != 18) {
            System.out.println("\tMENU");
            System.out.println("1. Crear libro");
            System.out.println("2. Crear Autor");
            System.out.println("3. Crear Editorial");
            System.out.println("4. Editar libro");
            System.out.println("5. Editar Autor");
            System.out.println("6. Editar Editorial");
            System.out.println("7. Borrar libro");
            System.out.println("8. Borrar Autor");
            System.out.println("9. Borrar Editorial");
            System.out.println("10. Ver libros");
            System.out.println("11. Ver Autores");
            System.out.println("12. Ver Editoriales");
            System.out.println("13. Buscar autor por nombre");
            System.out.println("14. Buscar libro por isbn");
            System.out.println("15. Buscar libro por titulo");
            System.out.println("16. Buscar libros por nombre de autor");
            System.out.println("17. Buscar libros por nombre de editorial");
            System.out.println("18. Salir");
            op = Integer.valueOf(scan.nextLine());
            if (op == 1) {
                f.l.crearLibro();
            }
            if (op == 2) {
                f.a.crearAutores();
            }
            if (op == 3) {
                f.e.crearEditoriales();
            }
            if (op == 4) {
                f.l.editarLibro();
            }
            if (op == 5) {
                f.a.editarAutor();
            }
            if (op == 6) {
                f.e.editarEditorial();
            }
            if (op == 7) {
                f.l.borrarLibro();
            }
            if (op == 8) {
                f.a.borrarAutor();
            }
            if (op == 9) {
                f.e.borrarEditorial();
            }
            if (op == 10) {
                f.l.verLibros();
            }
            if (op == 11) {
                f.a.verAutores();
            }
            if (op == 12) {
                f.e.verEditoriales();
            }
            if (op == 13) {
                f.a.verAutorNombre();
            }
            if (op == 14) {
                f.l.buscarLibroIsbn();
            }
            if (op == 15) {
                f.l.buscarLibroTitulo();
            }
            if (op == 16) {
                f.l.buscarLibroAutor();
            }
            if (op == 17) {
                f.l.buscarLibroEditorial();
            }
            if (op == 18) {
                break;
            }
        }

    }
}
