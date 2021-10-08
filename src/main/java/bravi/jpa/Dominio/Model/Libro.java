package bravi.jpa.Dominio.Model;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.eclipse.persistence.jpa.config.Cascade;

@Entity
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "isbn", unique = true)
    private long isbn;

    @Column(name = "titulo", unique = true)
    private String titulo;

    @Column(name = "anio")
    private Integer anio;

    @Column(name = "ejemplares")
    private Integer ejemplares;

    @Column(name = "ejemplares_prestados")
    private Integer ejemplaresPrestados;

    @Column(name = "ejemplares_restantes")
    private Integer ejemplaresRestantes;

    @Column(name = "alta")
    private boolean alta;

    @OneToOne(cascade = { CascadeType.DETACH }, orphanRemoval = true)
    @JoinColumn(name = "autor_id", referencedColumnName = "id")
    private Autor autor;

    @OneToOne(cascade = { CascadeType.DETACH }, orphanRemoval = true)
    @JoinColumn(name = "editorial_id", referencedColumnName = "id")
    private Editorial editorial;

    public Libro() {
    }

    public Libro(long isbn, String titulo, Integer anio, Integer ejemplares, Integer ejemplaresPrestados,
            Integer ejemplaresRestantes, boolean alta, Autor autor, Editorial editorial) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.anio = anio;
        this.ejemplares = ejemplares;
        this.ejemplaresPrestados = ejemplaresPrestados;
        this.ejemplaresRestantes = ejemplaresRestantes;
        this.alta = alta;
        this.autor = autor;
        this.editorial = editorial;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getIsbn() {
        return this.isbn;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getAnio() {
        return this.anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Integer getEjemplares() {
        return this.ejemplares;
    }

    public void setEjemplares(Integer ejemplares) {
        this.ejemplares = ejemplares;
    }

    public Integer getEjemplaresPrestados() {
        return this.ejemplaresPrestados;
    }

    public void setEjemplaresPrestados(Integer ejemplaresPrestados) {
        this.ejemplaresPrestados = ejemplaresPrestados;
    }

    public Integer getEjemplaresRestantes() {
        return this.ejemplaresRestantes;
    }

    public void setEjemplaresRestantes(Integer ejemplaresRestantes) {
        this.ejemplaresRestantes = ejemplaresRestantes;
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

    public Autor getAutor() {
        return this.autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Editorial getEditorial() {
        return this.editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

    public Libro isbn(long isbn) {
        setIsbn(isbn);
        return this;
    }

    public Libro titulo(String titulo) {
        setTitulo(titulo);
        return this;
    }

    public Libro anio(Integer anio) {
        setAnio(anio);
        return this;
    }

    public Libro ejemplares(Integer ejemplares) {
        setEjemplares(ejemplares);
        return this;
    }

    public Libro ejemplaresPrestados(Integer ejemplaresPrestados) {
        setEjemplaresPrestados(ejemplaresPrestados);
        return this;
    }

    public Libro ejemplaresRestantes(Integer ejemplaresRestantes) {
        setEjemplaresRestantes(ejemplaresRestantes);
        return this;
    }

    public Libro alta(boolean alta) {
        setAlta(alta);
        return this;
    }

    public Libro autor(Autor autor) {
        setAutor(autor);
        return this;
    }

    public Libro editorial(Editorial editorial) {
        setEditorial(editorial);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Libro)) {
            return false;
        }
        Libro libro = (Libro) o;
        return isbn == libro.isbn && Objects.equals(titulo, libro.titulo) && Objects.equals(anio, libro.anio)
                && Objects.equals(ejemplares, libro.ejemplares)
                && Objects.equals(ejemplaresPrestados, libro.ejemplaresPrestados)
                && Objects.equals(ejemplaresRestantes, libro.ejemplaresRestantes) && alta == libro.alta
                && Objects.equals(autor, libro.autor) && Objects.equals(editorial, libro.editorial);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn, titulo, anio, ejemplares, ejemplaresPrestados, ejemplaresRestantes, alta, autor,
                editorial);
    }

    @Override
    public String toString() {
        return "{" + " isbn='" + getIsbn() + "'" + ", titulo='" + getTitulo() + "'" + ", anio='" + getAnio() + "'"
                + ", ejemplares='" + getEjemplares() + "'" + ", ejemplaresPrestados='" + getEjemplaresPrestados() + "'"
                + ", ejemplaresRestantes='" + getEjemplaresRestantes() + "'" + ", alta='" + isAlta() + "'" + ", autor='"
                + getAutor() + "'" + ", editorial='" + getEditorial() + "'" + "}";
    }

}
