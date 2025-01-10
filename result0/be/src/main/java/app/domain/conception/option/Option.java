package app.domain.conception.option;

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
@Table(name = "option")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Option implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_option")
    @SequenceGenerator(name = "seq_option", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "valeur")
    private String valeur;

    @NotNull
    @Column(name = "libelle", nullable = false)
    private String libelle;

    @Column(name = "position")
    private Integer position;

    @ManyToOne(fetch = FetchType.LAZY)
    private Question question;

    public Long getId() {
        return this.id;
    }

    public Option id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayString() {
        return libelle;
    }

    public Long getIdOption() {
        return this.id;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValeur() {
        return this.valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public String getLibelle() {
        return this.libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Integer getPosition() {
        return this.position;
    }

    public void setPosition(Integer position) {
        this.position = position;
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
        if (!(o instanceof Option)) {
            return false;
        }
        return getId() != null && getId().equals(((Option) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}