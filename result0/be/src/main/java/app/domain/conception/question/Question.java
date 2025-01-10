package app.domain.conception.question;

import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import app.domain.conception.rubrique.Rubrique;
import app.domain.conception.typeReponse.TypeReponse;
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
@Table(name = "question")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_question")
    @SequenceGenerator(name = "seq_question", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @NotNull
    @Column(name = "titre", nullable = false)
    private String titre;

    @Column(name = "description")
    private String description;

    @Column(name = "position")
    private Integer position;

    @Column(name = "autre_option")
    private Boolean autreOption;

    @Column(name = "autre_libelle")
    private String autreLibelle;

    @Column(name = "min")
    private Double min;

    @Column(name = "max")
    private Double max;

    @Column(name = "precision")
    private Integer precision;

    @ManyToOne(fetch = FetchType.LAZY)
    private TypeReponse typeReponse;

    @ManyToOne(fetch = FetchType.LAZY)
    private Rubrique rubrique;

    public Long getId() {
        return this.id;
    }

    public Question id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayString() {
        return titre;
    }

    public Long getIdQuestion() {
        return this.id;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public Boolean getAutreOption() {
        return this.autreOption;
    }

    public void setAutreOption(Boolean autreOption) {
        this.autreOption = autreOption;
    }

    public String getAutreLibelle() {
        return this.autreLibelle;
    }

    public void setAutreLibelle(String autreLibelle) {
        this.autreLibelle = autreLibelle;
    }

    public Double getMin() {
        return this.min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getMax() {
        return this.max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public Integer getPrecision() {
        return this.precision;
    }

    public void setPrecision(Integer precision) {
        this.precision = precision;
    }

    public TypeReponse getTypeReponse() {
        return this.typeReponse;
    }

    public void setTypeReponse(TypeReponse typeReponse) {
        this.typeReponse = typeReponse;
    }

    public Rubrique getRubrique() {
        return this.rubrique;
    }

    public void setRubrique(Rubrique rubrique) {
        this.rubrique = rubrique;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Question)) {
            return false;
        }
        return getId() != null && getId().equals(((Question) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}