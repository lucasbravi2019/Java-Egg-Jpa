package bravi.jpa.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import bravi.jpa.Dominio.Model.Autor;
import bravi.jpa.Dominio.Model.Editorial;
import bravi.jpa.Dominio.Model.Libro;

public class LibroServicio {

    private Scanner scan = new Scanner(System.in);
    private Libro l = new Libro();
    private String autor = "Julio Verne";
    private String editorial = "Editorial 1";
    private EntityManager em = Persistence.createEntityManagerFactory("LibreriaPU").createEntityManager();

    public void pedirDatos() {
        System.out.println("Ingrese el isbn");
        long isbn = Long.valueOf(scan.nextLine());
        while (buscarRepetido(isbn)) {
            System.out.println("Ingreso un isbn existente, por favor ingrese uno nuevo");
            isbn = Long.valueOf(scan.nextLine());
        }
        System.out.println("Ingrese el titulo");
        String titulo = scan.nextLine();
        while (buscarRepetido(titulo)) {
            System.out.println("Ingreso un titulo existente, por favor ingrese uno nuevo");
            titulo = scan.nextLine();
        }
        System.out.println("Ingrese el anio");
        Integer anio = Integer.valueOf(scan.nextLine());
        System.out.println("Ingrese los ejemplares totales");
        Integer ejemplares = Integer.valueOf(scan.nextLine());
        System.out.println("Ingrese los ejemplares prestados");
        Integer ejemplaresPrestados = Integer.valueOf(scan.nextLine());
        System.out.println("Ingrese los ejemplares restantes");
        Integer ejemplaresRestantes = Integer.valueOf(scan.nextLine());
        System.out.println("Ingrese el alta (Y/N)");
        String verificacion = scan.nextLine();
        boolean alta;
        if (verificacion.equalsIgnoreCase("y")) {
            alta = true;
        } else if (verificacion.equalsIgnoreCase("n")) {
            alta = false;
        } else {
            System.out.println("Ingreso un dato invalido");
            alta = false;
        }
        System.out.println("Ingrese el nombre del autor");
        this.autor = scan.nextLine();
        System.out.println("Ingrese el nombre de la editorial");
        this.editorial = scan.nextLine();
        Query autorQ = em.createQuery("SELECT a FROM Autor a WHERE a.nombre = :nombre");
        autorQ.setParameter("nombre", this.autor);
        Autor autorNuevo = (Autor) autorQ.getSingleResult();
        Query editorialQ = em.createQuery("SELECT e FROM Editorial e WHERE e.nombre = :nombre");
        editorialQ.setParameter("nombre", this.editorial);
        Editorial editorialNueva = (Editorial) editorialQ.getSingleResult();

        l.setIsbn(isbn);
        l.setTitulo(titulo);
        l.setAnio(anio);
        l.setEjemplares(ejemplares);
        l.setEjemplaresPrestados(ejemplaresPrestados);
        l.setEjemplaresRestantes(ejemplaresRestantes);
        l.setAlta(alta);
        l.setAutor(autorNuevo);
        l.setEditorial(editorialNueva);
    }

    public void crearLibro() {
        pedirDatos();
        try {
            em.getTransaction().begin();
            em.persist(l);
            em.getTransaction().commit();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("El libro ya existe");
        }
    }

    public void editarLibro() {
        try {
            System.out.println("Ingrese el titulo del libro a editar");
            String nombre = scan.nextLine();
            Query libro = em.createQuery("SELECT l.id FROM Libro l WHERE l.titulo = :nombre");
            libro.setParameter("nombre", nombre);
            int id = (int) libro.getSingleResult();
            pedirDatos();
            l.setId(id);

            em.getTransaction().begin();
            em.merge(l);
            em.getTransaction().commit();

        } catch (NoResultException e) {
            // TODO: handle exception
            System.out.println("Ingreso un libro no existente");
        }
    }

    public void borrarLibro() {
        try {
            System.out.println("Ingrese el titulo del libro a borrar");
            String nombre = scan.nextLine();

            Query libro = em.createQuery("SELECT l.id FROM Libro l WHERE l.titulo = :nombre");
            libro.setParameter("nombre", nombre);
            int id = (int) libro.getSingleResult();
            Libro libroBorrar = em.find(Libro.class, id);
            em.getTransaction().begin();
            em.remove(libroBorrar);
            em.getTransaction().commit();
        } catch (NoResultException e) {
            // TODO: handle exception
            System.out.println("Ingreso un libro no existente");
        }
    }

    public void verLibros() {
        Query libros = em.createQuery("SELECT l FROM Libro l");
        List<Libro> listaLibros = libros.getResultList();
        for (Libro l : listaLibros) {
            System.out.println("\tLibro: " + l.getTitulo());
            System.out.println(l);
        }
    }

    public void buscarLibroIsbn() {
        try {
            System.out.println("Ingrese el isbn del libro a buscar");
            long isbn = Long.valueOf(scan.nextLine());
            Query libro = em.createQuery("SELECT l FROM Libro l WHERE l.isbn = :isbn");
            libro.setParameter("isbn", isbn);
            Libro libroBuscado = (Libro) libro.getSingleResult();
            System.out.println("\tLibro: " + isbn);
            System.out.println(libroBuscado);
        } catch (NoResultException e) {
            // TODO: handle exception
            System.out.println("Ingreso un isbn no existente");
        }
    }

    public void buscarLibroTitulo() {
        try {
            System.out.println("Ingrese el titulo del libro a buscar");
            String titulo = scan.nextLine();
            Query libro = em.createQuery("SELECT l FROM Libro l WHERE l.titulo = :titulo");
            libro.setParameter("titulo", titulo);
            Libro libroBuscado = (Libro) libro.getSingleResult();
            System.out.println("\tLibro: " + titulo);
            System.out.println(libroBuscado);
        } catch (NoResultException e) {
            // TODO: handle exception
            System.out.println("Ingreso un titulo no existente");
        }
    }

    public void buscarLibroAutor() {
        System.out.println("Ingrese el nombre del autor del libro a buscar");
        String nombre = scan.nextLine();

        Query libro = em.createQuery(
                "SELECT l FROM Libro l JOIN l.autor b WHERE l.autor.id = (SELECT a.id FROM Autor a WHERE a.nombre = :nombre)");
        libro.setParameter("nombre", nombre);
        if (libro.getResultList().isEmpty()) {
            System.out.println("El autor ingresado no existe");
        } else {
            List<Libro> libros = libro.getResultList();
            for (Libro l : libros) {
                System.out.println("\tLibro por autor: " + nombre);
                System.out.println(l);
            }
        }
    }

    public void buscarLibroEditorial() {
        System.out.println("Ingrese el nombre de la editorial del libro a buscar");
        String nombre = scan.nextLine();

        Query libro = em.createQuery(
                "SELECT l FROM Libro l JOIN l.editorial e WHERE l.editorial.id = (SELECT e.id FROM Editorial e WHERE e.nombre = :nombre)");
        libro.setParameter("nombre", nombre);
        if (libro.getResultList().isEmpty()) {
            System.out.println("La editorial ingresada no existe");
        } else {
            List<Libro> libros = libro.getResultList();

            for (Libro l : libros) {
                System.out.println("\tLibros por editorial: " + nombre);
                System.out.println(l);
            }
        }
    }

    public boolean buscarRepetido(long isbn) {
        Query libro = em.createQuery("SELECT l FROM Libro l WHERE l.isbn = :isbn");
        libro.setParameter("isbn", isbn);
        if (libro.getResultList().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public boolean buscarRepetido(String titulo) {
        Query libro = em.createQuery("SELECT l FROM Libro l WHERE l.titulo = :titulo");
        libro.setParameter("titulo", titulo);
        if (libro.getResultList().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

}
