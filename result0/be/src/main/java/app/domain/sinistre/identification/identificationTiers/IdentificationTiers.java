package app.domain.sinistre.identification.identificationTiers;

import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import app.domain.commun.reference.reference.Reference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "identification_tiers")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class IdentificationTiers implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_identification_tiers")
    @SequenceGenerator(name = "seq_identification_tiers", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "numero_identification", nullable = false)
    private String numeroIdentification;

    @Column(name = "libelle_tiers")
    private String libelleTiers;

    @ManyToOne(fetch = FetchType.LAZY)
    private Reference compagnieAdverse;

    public Long getId() {
        return this.id;
    }

    public IdentificationTiers id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayString() {
        return numeroIdentification;
    }

    public Long getIdIdentificationTiers() {
        return this.id;
    }

    public String getNumeroIdentification() {
        return this.numeroIdentification;
    }

    public void setNumeroIdentification(String numeroIdentification) {
        this.numeroIdentification = numeroIdentification;
    }

    public String getLibelleTiers() {
        return this.libelleTiers;
    }

    public void setLibelleTiers(String libelleTiers) {
        this.libelleTiers = libelleTiers;
    }

    public Reference getCompagnieAdverse() {
        return this.compagnieAdverse;
    }

    public void setCompagnieAdverse(Reference compagnieAdverse) {
        this.compagnieAdverse = compagnieAdverse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof IdentificationTiers)) {
            return false;
        }
        return getId() != null && getId().equals(((IdentificationTiers) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}