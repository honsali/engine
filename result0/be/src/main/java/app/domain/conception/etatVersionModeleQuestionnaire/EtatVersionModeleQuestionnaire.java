package app.domain.conception.etatVersionModeleQuestionnaire;

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
@Table(name = "etat_version_modele_questionnaire")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EtatVersionModeleQuestionnaire implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_etat_version_modele_questionnaire")
    @SequenceGenerator(name = "seq_etat_version_modele_questionnaire", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "libelle", nullable = false)
    private String libelle;

    public Long getId() {
        return this.id;
    }

    public EtatVersionModeleQuestionnaire id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdEtatVersionModeleQuestionnaire() {
        return this.id;
    }

    public String getLibelle() {
        return this.libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EtatVersionModeleQuestionnaire)) {
            return false;
        }
        return getId() != null && getId().equals(((EtatVersionModeleQuestionnaire) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}