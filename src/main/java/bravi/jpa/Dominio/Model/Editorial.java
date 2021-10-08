package bravi.jpa.Dominio.Model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Editorial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre", unique = true)
    private String nombre;

    @Column(name = "alta")
    private boolean alta;

    public Editorial() {
    }

    public Editorial(String nombre) {
        this.nombre = nombre;
        this.alta = false;
    }

    public Editorial(Integer id, String nombre, boolean alta) {
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

    public Editorial id(Integer id) {
        setId(id);
        return this;
    }

    public Editorial nombre(String nombre) {
        setNombre(nombre);
        return this;
    }

    public Editorial alta(boolean alta) {
        setAlta(alta);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Editorial)) {
            return false;
        }
        Editorial editorial = (Editorial) o;
        return Objects.equals(id, editorial.id) && Objects.equals(nombre, editorial.nombre) && alta == editorial.alta;
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
