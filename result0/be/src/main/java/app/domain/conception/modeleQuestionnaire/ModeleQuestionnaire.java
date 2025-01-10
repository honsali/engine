package app.domain.conception.modeleQuestionnaire;

import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import app.domain.conception.versionModeleQuestionnaire.VersionModeleQuestionnaire;
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
@Table(name = "modele_questionnaire")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ModeleQuestionnaire implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_modele_questionnaire")
    @SequenceGenerator(name = "seq_modele_questionnaire", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "description")
    private String description;

    @Column(name = "date_creation")
    private LocalDate dateCreation;

    @ManyToOne(fetch = FetchType.LAZY)
    private VersionModeleQuestionnaire versionProduction;

    @ManyToOne(fetch = FetchType.LAZY)
    private VersionModeleQuestionnaire versionDeveloppement;

    public Long getId() {
        return this.id;
    }

    public ModeleQuestionnaire id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayString() {
        return nom;
    }

    public Long getIdModeleQuestionnaire() {
        return this.id;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateCreation() {
        return this.dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public VersionModeleQuestionnaire getVersionProduction() {
        return this.versionProduction;
    }

    public void setVersionProduction(VersionModeleQuestionnaire versionProduction) {
        this.versionProduction = versionProduction;
    }

    public VersionModeleQuestionnaire getVersionDeveloppement() {
        return this.versionDeveloppement;
    }

    public void setVersionDeveloppement(VersionModeleQuestionnaire versionDeveloppement) {
        this.versionDeveloppement = versionDeveloppement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ModeleQuestionnaire)) {
            return false;
        }
        return getId() != null && getId().equals(((ModeleQuestionnaire) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}