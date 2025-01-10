package app.domain.execution.questionnaire;

import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import app.domain.conception.versionModeleQuestionnaire.VersionModeleQuestionnaire;
import app.domain.execution.etatQuestionnaire.EtatQuestionnaire;
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
@Table(name = "questionnaire")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Questionnaire implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_questionnaire")
    @SequenceGenerator(name = "seq_questionnaire", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "date_creation")
    private LocalDate dateCreation;

    @Column(name = "nombre_question")
    private Integer nombreQuestion;

    @Column(name = "derniere_question")
    private Integer derniereQuestion;

    @ManyToOne(fetch = FetchType.LAZY)
    private VersionModeleQuestionnaire versionModeleQuestionnaire;

    @ManyToOne(fetch = FetchType.LAZY)
    private EtatQuestionnaire etat;

    public Long getId() {
        return this.id;
    }

    public Questionnaire id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayString() {
        return code;
    }

    public Long getIdQuestionnaire() {
        return this.id;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDate getDateCreation() {
        return this.dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Integer getNombreQuestion() {
        return this.nombreQuestion;
    }

    public void setNombreQuestion(Integer nombreQuestion) {
        this.nombreQuestion = nombreQuestion;
    }

    public Integer getDerniereQuestion() {
        return this.derniereQuestion;
    }

    public void setDerniereQuestion(Integer derniereQuestion) {
        this.derniereQuestion = derniereQuestion;
    }

    public VersionModeleQuestionnaire getVersionModeleQuestionnaire() {
        return this.versionModeleQuestionnaire;
    }

    public void setVersionModeleQuestionnaire(VersionModeleQuestionnaire versionModeleQuestionnaire) {
        this.versionModeleQuestionnaire = versionModeleQuestionnaire;
    }

    public EtatQuestionnaire getEtat() {
        return this.etat;
    }

    public void setEtat(EtatQuestionnaire etat) {
        this.etat = etat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Questionnaire)) {
            return false;
        }
        return getId() != null && getId().equals(((Questionnaire) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}