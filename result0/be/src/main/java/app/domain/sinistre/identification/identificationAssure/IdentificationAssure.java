package app.domain.sinistre.identification.identificationAssure;

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
@Table(name = "identification_assure")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class IdentificationAssure implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_identification_assure")
    @SequenceGenerator(name = "seq_identification_assure", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "libelle_assure", nullable = false)
    private String libelleAssure;

    @Column(name = "numero_identification")
    private String numeroIdentification;

    @Column(name = "numero_vehicule")
    private String numeroVehicule;

    @Column(name = "numero_immatriculation")
    private String numeroImmatriculation;

    public Long getId() {
        return this.id;
    }

    public IdentificationAssure id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayString() {
        return libelleAssure;
    }

    public Long getIdIdentificationAssure() {
        return this.id;
    }

    public String getLibelleAssure() {
        return this.libelleAssure;
    }

    public void setLibelleAssure(String libelleAssure) {
        this.libelleAssure = libelleAssure;
    }

    public String getNumeroIdentification() {
        return this.numeroIdentification;
    }

    public void setNumeroIdentification(String numeroIdentification) {
        this.numeroIdentification = numeroIdentification;
    }

    public String getNumeroVehicule() {
        return this.numeroVehicule;
    }

    public void setNumeroVehicule(String numeroVehicule) {
        this.numeroVehicule = numeroVehicule;
    }

    public String getNumeroImmatriculation() {
        return this.numeroImmatriculation;
    }

    public void setNumeroImmatriculation(String numeroImmatriculation) {
        this.numeroImmatriculation = numeroImmatriculation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof IdentificationAssure)) {
            return false;
        }
        return getId() != null && getId().equals(((IdentificationAssure) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}