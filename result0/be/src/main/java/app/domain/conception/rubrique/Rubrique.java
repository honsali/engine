package app.domain.conception.rubrique;

import java.io.Serializable;
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
@Table(name = "rubrique")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Rubrique implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_rubrique")
    @SequenceGenerator(name = "seq_rubrique", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "titre", nullable = false)
    private String titre;

    @Column(name = "description")
    private String description;

    @Column(name = "position")
    private Integer position;

    @ManyToOne(fetch = FetchType.LAZY)
    private VersionModeleQuestionnaire versionModeleQuestionnaire;

    public Long getId() {
        return this.id;
    }

    public Rubrique id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayString() {
        return titre;
    }

    public Long getIdRubrique() {
        return this.id;
    }

    public String getTitre() {
        return this.titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPosition() {
        return this.position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public VersionModeleQuestionnaire getVersionModeleQuestionnaire() {
        return this.versionModeleQuestionnaire;
    }

    public void setVersionModeleQuestionnaire(VersionModeleQuestionnaire versionModeleQuestionnaire) {
        this.versionModeleQuestionnaire = versionModeleQuestionnaire;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Rubrique)) {
            return false;
        }
        return getId() != null && getId().equals(((Rubrique) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}