package app.domain.conception.versionModeleQuestionnaire;

import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import app.domain.conception.etatVersionModeleQuestionnaire.EtatVersionModeleQuestionnaire;
import app.domain.conception.modeleQuestionnaire.ModeleQuestionnaire;
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
@Table(name = "version_modele_questionnaire")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class VersionModeleQuestionnaire implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_version_modele_questionnaire")
    @SequenceGenerator(name = "seq_version_modele_questionnaire", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "version", nullable = false)
    private String version;

    @Column(name = "date_creation")
    private LocalDate dateCreation;

    @Column(name = "date_modification")
    private LocalDate dateModification;

    @Column(name = "date_debut_utilisation")
    private LocalDate dateDebutUtilisation;

    @Column(name = "date_fin_utilisation")
    private LocalDate dateFinUtilisation;

    @ManyToOne(fetch = FetchType.LAZY)
    private ModeleQuestionnaire modele;

    @ManyToOne(fetch = FetchType.LAZY)
    private EtatVersionModeleQuestionnaire etat;

    public Long getId() {
        return this.id;
    }

    public VersionModeleQuestionnaire id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayString() {
        return version;
    }

    public Long getIdVersionModeleQuestionnaire() {
        return this.id;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public LocalDate getDateCreation() {
        return this.dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public LocalDate getDateModification() {
        return this.dateModification;
    }

    public void setDateModification(LocalDate dateModification) {
        this.dateModification = dateModification;
    }

    public LocalDate getDateDebutUtilisation() {
        return this.dateDebutUtilisation;
    }

    public void setDateDebutUtilisation(LocalDate dateDebutUtilisation) {
        this.dateDebutUtilisation = dateDebutUtilisation;
    }

    public LocalDate getDateFinUtilisation() {
        return this.dateFinUtilisation;
    }

    public void setDateFinUtilisation(LocalDate dateFinUtilisation) {
        this.dateFinUtilisation = dateFinUtilisation;
    }

    public ModeleQuestionnaire getModele() {
        return this.modele;
    }

    public void setModele(ModeleQuestionnaire modele) {
        this.modele = modele;
    }

    public EtatVersionModeleQuestionnaire getEtat() {
        return this.etat;
    }

    public void setEtat(EtatVersionModeleQuestionnaire etat) {
        this.etat = etat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VersionModeleQuestionnaire)) {
            return false;
        }
        return getId() != null && getId().equals(((VersionModeleQuestionnaire) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}