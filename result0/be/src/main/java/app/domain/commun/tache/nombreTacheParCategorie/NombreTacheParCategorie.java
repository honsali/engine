package app.domain.commun.tache.nombreTacheParCategorie;

import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "nombre_tache_par_categorie")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class NombreTacheParCategorie implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_nombre_tache_par_categorie")
    @SequenceGenerator(name = "seq_nombre_tache_par_categorie", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "categorie_tache", nullable = false)
    private String categorieTache;

    @Column(name = "nombre")
    private Integer nombre;

    public Long getId() {
        return this.id;
    }

    public NombreTacheParCategorie id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayString() {
        return categorieTache;
    }

    public Long getIdNombreTacheParCategorie() {
        return this.id;
    }

    public String getCategorieTache() {
        return this.categorieTache;
    }

    public void setCategorieTache(String categorieTache) {
        this.categorieTache = categorieTache;
    }

    public Integer getNombre() {
        return this.nombre;
    }

    public void setNombre(Integer nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NombreTacheParCategorie)) {
            return false;
        }
        return getId() != null && getId().equals(((NombreTacheParCategorie) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}