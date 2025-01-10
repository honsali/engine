package app.domain.conception.transition;

import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import app.domain.conception.option.Option;
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
@Table(name = "transition")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Transition implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_transition")
    @SequenceGenerator(name = "seq_transition", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "raison", nullable = false)
    private String raison;

    @ManyToOne(fetch = FetchType.LAZY)
    private Option optionChoisie;

    @ManyToOne(fetch = FetchType.LAZY)
    private Question questionCible;

    @ManyToOne(fetch = FetchType.LAZY)
    private Question question;

    public Long getId() {
        return this.id;
    }

    public Transition id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayString() {
        return raison;
    }

    public Long getIdTransition() {
        return this.id;
    }

    public String getRaison() {
        return this.raison;
    }

    public void setRaison(String raison) {
        this.raison = raison;
    }

    public Option getOptionChoisie() {
        return this.optionChoisie;
    }

    public void setOptionChoisie(Option optionChoisie) {
        this.optionChoisie = optionChoisie;
    }

    public Question getQuestionCible() {
        return this.questionCible;
    }

    public void setQuestionCible(Question questionCible) {
        this.questionCible = questionCible;
    }

    public Question getQuestion() {
        return this.question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Transition)) {
            return false;
        }
        return getId() != null && getId().equals(((Transition) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}