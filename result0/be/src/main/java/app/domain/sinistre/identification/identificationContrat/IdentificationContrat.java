package app.domain.sinistre.identification.identificationContrat;

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
@Table(name = "identification_contrat")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class IdentificationContrat implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_identification_contrat")
    @SequenceGenerator(name = "seq_identification_contrat", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "categorie_police", nullable = false)
    private String categoriePolice;

    @Column(name = "numero_police")
    private String numeroPolice;

    @Column(name = "numero_serie")
    private String numeroSerie;

    @Column(name = "date_souscription")
    private String dateSouscription;

    @Column(name = "code_intermediaire")
    private String codeIntermediaire;

    @Column(name = "libelle_intermediaire")
    private String libelleIntermediaire;

    public Long getId() {
        return this.id;
    }

    public IdentificationContrat id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayString() {
        return categoriePolice;
    }

    public Long getIdIdentificationContrat() {
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

    public String getNumeroSerie() {
        return this.numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getDateSouscription() {
        return this.dateSouscription;
    }

    public void setDateSouscription(String dateSouscription) {
        this.dateSouscription = dateSouscription;
    }

    public String getCodeIntermediaire() {
        return this.codeIntermediaire;
    }

    public void setCodeIntermediaire(String codeIntermediaire) {
        this.codeIntermediaire = codeIntermediaire;
    }

    public String getLibelleIntermediaire() {
        return this.libelleIntermediaire;
    }

    public void setLibelleIntermediaire(String libelleIntermediaire) {
        this.libelleIntermediaire = libelleIntermediaire;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof IdentificationContrat)) {
            return false;
        }
        return getId() != null && getId().equals(((IdentificationContrat) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}