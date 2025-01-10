package app.domain.commun.reference.contrat;

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
@Table(name = "contrat")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Contrat implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_contrat")
    @SequenceGenerator(name = "seq_contrat", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "categorie_police", nullable = false)
    private String categoriePolice;

    @Column(name = "numero_police")
    private String numeroPolice;

    public Long getId() {
        return this.id;
    }

    public Contrat id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayString() {
        return categoriePolice;
    }

    public Long getIdContrat() {
        return this.id;
    }

    public String getCategoriePolice() {
        return this.categoriePolice;
    }

    public void setCategoriePolice(String categoriePolice) {
        this.categoriePolice = categoriePolice;
    }

    public String getNumeroPolice() {
        return this.numeroPolice;
    }

    public void setNumeroPolice(String numeroPolice) {
        this.numeroPolice = numeroPolice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Contrat)) {
            return false;
        }
        return getId() != null && getId().equals(((Contrat) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}