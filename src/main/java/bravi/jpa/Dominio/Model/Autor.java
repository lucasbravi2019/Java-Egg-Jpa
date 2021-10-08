package bravi.jpa.Dominio.Model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre", unique = true)
    private String nombre;

    @Column(name = "alta")
    private boolean alta;

    public Autor() {
    }

    public Autor(String nombre) {
        this.nombre = nombre;
        this.alta = false;
    }

    public Autor(Integer id, String nombre, boolean alta) {
        this.id = id;
        this.nombre = nombre;
        this.alta = alta;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isAlta() {
        return this.alta;
    }

    public boolean getAlta() {
        return this.alta;
    }

    public void setAlta(boolean alta) {
        this.alta = alta;
    }

    public Autor id(Integer id) {
        setId(id);
        return this;
    }

    public Autor nombre(String nombre) {
        setNombre(nombre);
        return this;
    }

    public Autor alta(boolean alta) {
        setAlta(alta);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Autor)) {
            return false;
        }
        Autor autor = (Autor) o;
        return Objects.equals(id, autor.id) && Objects.equals(nombre, autor.nombre) && alta == autor.alta;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, alta);
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", nombre='" + getNombre() + "'" + ", alta='" + isAlta() + "'" + "}";
    }

}
