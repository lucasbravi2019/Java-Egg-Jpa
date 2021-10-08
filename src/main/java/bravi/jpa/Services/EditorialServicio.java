package bravi.jpa.Services;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.eclipse.persistence.exceptions.DatabaseException;

import bravi.jpa.Dominio.Model.Editorial;
import bravi.jpa.Dominio.Model.Libro;

public class EditorialServicio {

    private Scanner scan = new Scanner(System.in);
    private EntityManager em = Persistence.createEntityManagerFactory("LibreriaPU").createEntityManager();

    public void crearEditoriales() {
        System.out.println("Ingrese el nuevo nombre de la editorial");
        Editorial ed = new Editorial(scan.nextLine());
        while (buscarRepetido(ed.getNombre())) {
            System.out.println("Indico una editorial existente, por favor indique otra");
            ed.setNombre(scan.nextLine());
        }
        try {
            em.getTransaction().begin();
            em.persist(ed);
            em.getTransaction().commit();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("La editorial existe");
        }
    }

    public void editarEditorial() {
        try {
            System.out.println("Ingrese el nombre de la editorial a editar");
            String nombre = scan.nextLine();
            Query editorial = em.createQuery("SELECT e.id FROM Editorial e WHERE e.nombre = :nombre");
            editorial.setParameter("nombre", nombre);
            int id = (int) editorial.getSingleResult();
            System.out.println("Ingrese el nombre nuevo para la editorial");
            String nombreNuevo = scan.nextLine();

            Editorial editorialNueva = em.find(Editorial.class, id);

            em.getTransaction().begin();
            editorialNueva.setNombre(nombreNuevo);
            em.getTransaction().commit();
        } catch (NoResultException e) {
            // TODO: handle exception
            System.out.println("Ingreso una editorial no existente");
        }
    }

    public void borrarEditorial() {
        try {
            System.out.println("Ingrese el nombre de la editorial a borrar");
            String nombre = scan.nextLine();
            Query editorial = em.createQuery("SELECT e.id FROM Editorial e WHERE e.nombre = :nombre");
            editorial.setParameter("nombre", nombre);
            int id = (int) editorial.getSingleResult();
            List<Libro> librosBorrar = borrarEditoriales(id);
            Editorial editorialBorrar = em.find(Editorial.class, id);
            em.getTransaction().begin();
            for (Libro l : librosBorrar) {
                l.setEditorial(null);
            }

            em.remove(editorialBorrar);
            em.getTransaction().commit();
        } catch (NoResultException e) {
            // TODO: handle exception
            System.out.println("Ingreso una editorial inexistente");
        } catch (DatabaseException e) {
            System.out.println("Problemas para borrar en la base de datos");
        } catch (Exception e) {
            System.out.println("No se puede borrar debido a que la editorial esta indexada a algun libro");
        }
    }

    public void verEditoriales() {
        Query editorial = em.createQuery("SELECT e FROM Editorial e");
        List<Editorial> editoriales = editorial.getResultList();
        for (Editorial e : editoriales) {
            System.out.println("\tEditorial " + e.getNombre());
            System.out.println(e);
        }
    }

    public List<Libro> borrarEditoriales(int id) {
        Query libro = em.createQuery("SELECT l FROM Libro l WHERE l.editorial.id = :id");
        libro.setParameter("id", id);
        List<Libro> libros = libro.getResultList();
        return libros;
    }

    public boolean buscarRepetido(String nombre) {
        Query editorial = em.createQuery("SELECT e FROM Editorial e WHERE e.nombre = :nombre");
        editorial.setParameter("nombre", nombre);
        if (editorial.getResultList().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

}
