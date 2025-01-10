package app.domain.conception.regleSuivi;

import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import app.domain.conception.question.Question;
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
@Table(name = "regle_suivi")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RegleSuivi implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_regle_suivi")
    @SequenceGenerator(name = "seq_regle_suivi", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "operateur")
    private String operateur;

    @Column(name = "valeur")
    private String valeur;

    @ManyToOne(fetch = FetchType.LAZY)
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    private Question questionSuivante;

    public Long getId() {
        return this.id;
    }

    public RegleSuivi id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayString() {
        return code;
    }

    public Long getIdRegleSuivi() {
        return this.id;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOperateur() {
        return this.operateur;
    }

    public void setOperateur(String operateur) {
        this.operateur = operateur;
    }

    public String getValeur() {
        return this.valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public Question getQuestion() {
        return this.question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Question getQuestionSuivante() {
        return this.questionSuivante;
    }

    public void setQuestionSuivante(Question questionSuivante) {
        this.questionSuivante = questionSuivante;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RegleSuivi)) {
            return false;
        }
        return getId() != null && getId().equals(((RegleSuivi) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}