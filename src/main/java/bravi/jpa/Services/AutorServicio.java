package bravi.jpa.Services;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import bravi.jpa.Dominio.Model.Autor;
import bravi.jpa.Dominio.Model.Libro;

public class AutorServicio {

    private Scanner scan = new Scanner(System.in);
    private EntityManager em = Persistence.createEntityManagerFactory("LibreriaPU").createEntityManager();

    public void crearAutores() {
        System.out.println("Ingrese el nombre del nuevo autor");
        Autor autor = new Autor(scan.nextLine());
        while (buscarRepetido(autor.getNombre())) {
            System.out.println("Ingreso un autor existente, por favor indique otro");
            autor.setNombre(scan.nextLine());
        }
        try {
            em.getTransaction().begin();
            em.persist(autor);
            em.getTransaction().commit();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("El autor ya existe");
        }
    }

    public void editarAutor() {
        try {
            System.out.println("Ingrese el nombre del autor a editar");
            String nombre = scan.nextLine();
            Query autor = em.createQuery("SELECT a.id FROM Autor a WHERE a.nombre = :nombre");
            autor.setParameter("nombre", nombre);
            int autorId = (int) autor.getSingleResult();
            System.out.println("Ingrese el nombre del nuevo autor");
            String nombreNuevo = scan.nextLine();
            em.getTransaction().begin();
            Autor nuevoAutor = em.find(Autor.class, autorId);
            nuevoAutor.setNombre(nombreNuevo);
            em.getTransaction().commit();
        } catch (NoResultException e) {
            // TODO: handle exception
            System.out.println("Especifico un nombre no existente");
        }

    }

    public void borrarAutor() {
        try {
            System.out.println("Ingrese el nombre del autor a borrar");
            String nombre = scan.nextLine();
            Query autor = em.createQuery("SELECT a.id FROM Autor a WHERE a.nombre = :nombre");
            autor.setParameter("nombre", nombre);
            int id = (int) autor.getSingleResult();

            List<Libro> libros = borrarAutores(id);
            em.getTransaction().begin();
            for (Libro l : libros) {
                l.setAutor(null);
            }

            Autor autorBorrar = em.find(Autor.class, id);

            em.remove(autorBorrar);
            em.getTransaction().commit();
        } catch (NoResultException e) {
            // TODO: handle exception
            System.out.println("Ingreso un autor inexistente");
        } catch (Exception e) {
            System.out.println("No se puede borrar el autor debido a que esta indexado a algun libro");
        }
    }

    public void verAutores() {
        Query autor = em.createQuery("SELECT a FROM Autor a");
        List<Autor> autores = autor.getResultList();
        for (Autor a : autores) {
            System.out.println("\tAutor " + a.getNombre());
            System.out.println(a);
        }
    }

    public void verAutorNombre() {
        try {
            System.out.println("Ingrese el nombre del autor que desea buscar");
            String nombre = scan.nextLine();
            Query autor = em.createQuery("SELECT a FROM Autor a WHERE a.nombre = :nombre");
            autor.setParameter("nombre", nombre);
            Autor autorBuscado = (Autor) autor.getSingleResult();
            System.out.println("\tAutor " + nombre);
            System.out.println(autorBuscado);
        } catch (NoResultException e) {
            // TODO: handle exception
            System.out.println("Ingreso un autor inexistente");
        }
    }

    public List<Libro> borrarAutores(int id) {
        Query libro = em.createQuery("SELECT l FROM Libro l WHERE l.autor.id = :id");
        libro.setParameter("id", id);
        List<Libro> libros = libro.getResultList();
        return libros;
    }

    public boolean buscarRepetido(String nombre) {
        Query autor = em.createQuery("SELECT a FROM Autor a WHERE a.nombre = :nombre");
        autor.setParameter("nombre", nombre);
        if (autor.getResultList().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

}
