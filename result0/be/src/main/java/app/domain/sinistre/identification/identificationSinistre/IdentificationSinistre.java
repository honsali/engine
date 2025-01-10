package app.domain.sinistre.identification.identificationSinistre;

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
@Table(name = "identification_sinistre")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class IdentificationSinistre implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_identification_sinistre")
    @SequenceGenerator(name = "seq_identification_sinistre", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "numero_sinistre", nullable = false)
    private String numeroSinistre;

    @Column(name = "date_survenance")
    private String dateSurvenance;

    @Column(name = "date_ouverture")
    private String dateOuverture;

    public Long getId() {
        return this.id;
    }

    public IdentificationSinistre id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayString() {
        return numeroSinistre;
    }

    public Long getIdIdentificationSinistre() {
        return this.id;
    }

    public String getNumeroSinistre() {
        return this.numeroSinistre;
    }

    public void setNumeroSinistre(String numeroSinistre) {
        this.numeroSinistre = numeroSinistre;
    }

    public String getDateSurvenance() {
        return this.dateSurvenance;
    }

    public void setDateSurvenance(String dateSurvenance) {
        this.dateSurvenance = dateSurvenance;
    }

    public String getDateOuverture() {
        return this.dateOuverture;
    }

    public void setDateOuverture(String dateOuverture) {
        this.dateOuverture = dateOuverture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof IdentificationSinistre)) {
            return false;
        }
        return getId() != null && getId().equals(((IdentificationSinistre) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}