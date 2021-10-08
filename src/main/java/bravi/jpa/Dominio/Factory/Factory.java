package bravi.jpa.Dominio.Factory;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import bravi.jpa.Dominio.Model.Autor;
import bravi.jpa.Dominio.Model.Editorial;
import bravi.jpa.Dominio.Model.Libro;
import bravi.jpa.Services.AutorServicio;
import bravi.jpa.Services.EditorialServicio;
import bravi.jpa.Services.LibroServicio;

public class Factory {

    public final LibroServicio l = new LibroServicio();
    public final AutorServicio a = new AutorServicio();
    public final EditorialServicio e = new EditorialServicio();

    public Factory() {
        createFactory();
    }

    public void createFactory() {

        EntityManager em = Persistence.createEntityManagerFactory("LibreriaPU").createEntityManager();
        Autor autor1 = new Autor("Julio Verne");
        Autor autor2 = new Autor("William Faulkner");
        Autor autor3 = new Autor("Oscar Wilde");
        Autor autor4 = new Autor("Franz Kafka");
        Autor autor5 = new Autor("William Shakespeare");
        Autor autor6 = new Autor("James Joyce");
        Editorial editorial1 = new Editorial("Editorial 1");
        Editorial editorial2 = new Editorial("Editorial 2");
        Editorial editorial3 = new Editorial("Editorial 3");
        Editorial editorial4 = new Editorial("Editorial 4");
        Editorial editorial5 = new Editorial("Editorial 5");
        Editorial editorial6 = new Editorial("Editorial 6");
        Libro libro1 = new Libro(1515, "Moby Dick", 1999, 5, 2, 3, false, autor1, editorial1);
        Libro libro2 = new Libro(1414, "Planta naranja lima", 2005, 2, 2, 0, false, autor2, editorial2);
        Libro libro3 = new Libro(1313, "Fantasma canterville", 2014, 10, 2, 8, false, autor3, editorial3);
        Libro libro4 = new Libro(1212, "Harry potter", 2019, 4, 2, 2, false, autor4, editorial4);
        Libro libro5 = new Libro(1111, "Harry potter 2", 2020, 1, 0, 1, false, autor5, editorial5);
        Libro libro6 = new Libro(1000, "50 Shades of gray", 1989, 2, 0, 2, false, autor6, editorial6);
        em.getTransaction().begin();
        em.persist(autor1);
        em.persist(autor2);
        em.persist(autor3);
        em.persist(autor4);
        em.persist(autor5);
        em.persist(autor6);
        em.persist(editorial1);
        em.persist(editorial2);
        em.persist(editorial3);
        em.persist(editorial4);
        em.persist(editorial5);
        em.persist(editorial6);
        em.persist(libro1);
        em.persist(libro2);
        em.persist(libro3);
        em.persist(libro4);
        em.persist(libro5);
        em.persist(libro6);
        em.getTransaction().commit();
    }

}
