package app.domain.execution.reponse;

import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import app.domain.conception.question.Question;
import app.domain.conception.typeOptionSansReponse.TypeOptionSansReponse;
import app.domain.execution.questionnaire.Questionnaire;
import app.domain.execution.sessionInterview.SessionInterview;
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
@Table(name = "reponse")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Reponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_reponse")
    @SequenceGenerator(name = "seq_reponse", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "position")
    private Integer position;

    @Column(name = "valeur_texte")
    private String valeurTexte;

    @Column(name = "valeur_numerique")
    private Double valeurNumerique;

    @Column(name = "valeur_numerique_fin")
    private Double valeurNumeriqueFin;

    @Column(name = "valeur_date")
    private LocalDate valeurDate;

    @Column(name = "valeur_date_fin")
    private LocalDate valeurDateFin;

    @Column(name = "valeur_booleen")
    private Boolean valeurBooleen;

    @ManyToOne(fetch = FetchType.LAZY)
    private TypeOptionSansReponse typeOptionSansReponse;

    @Column(name = "autre_option")
    private Boolean autreOption;

    @Column(name = "option_1")
    private Boolean option1;

    @Column(name = "option_2")
    private Boolean option2;

    @Column(name = "option_3")
    private Boolean option3;

    @Column(name = "option_4")
    private Boolean option4;

    @Column(name = "option_5")
    private Boolean option5;

    @Column(name = "option_6")
    private Boolean option6;

    @Column(name = "option_7")
    private Boolean option7;

    @Column(name = "option_8")
    private Boolean option8;

    @Column(name = "option_9")
    private Boolean option9;

    @Column(name = "option_10")
    private Boolean option10;

    @ManyToOne(fetch = FetchType.LAZY)
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    private SessionInterview sessionInterview;

    @ManyToOne(fetch = FetchType.LAZY)
    private Questionnaire questionnaire;

    public Long getId() {
        return this.id;
    }

    public Reponse id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayString() {
        return code;
    }

    public Long getIdReponse() {
        return this.id;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getPosition() {
        return this.position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getValeurTexte() {
        return this.valeurTexte;
    }

    public void setValeurTexte(String valeurTexte) {
        this.valeurTexte = valeurTexte;
    }

    public Double getValeurNumerique() {
        return this.valeurNumerique;
    }

    public void setValeurNumerique(Double valeurNumerique) {
        this.valeurNumerique = valeurNumerique;
    }

    public Double getValeurNumeriqueFin() {
        return this.valeurNumeriqueFin;
    }

    public void setValeurNumeriqueFin(Double valeurNumeriqueFin) {
        this.valeurNumeriqueFin = valeurNumeriqueFin;
    }

    public LocalDate getValeurDate() {
        return this.valeurDate;
    }

    public void setValeurDate(LocalDate valeurDate) {
        this.valeurDate = valeurDate;
    }

    public LocalDate getValeurDateFin() {
        return this.valeurDateFin;
    }

    public void setValeurDateFin(LocalDate valeurDateFin) {
        this.valeurDateFin = valeurDateFin;
    }

    public Boolean getValeurBooleen() {
        return this.valeurBooleen;
    }

    public void setValeurBooleen(Boolean valeurBooleen) {
        this.valeurBooleen = valeurBooleen;
    }

    public TypeOptionSansReponse getTypeOptionSansReponse() {
        return this.typeOptionSansReponse;
    }

    public void setTypeOptionSansReponse(TypeOptionSansReponse typeOptionSansReponse) {
        this.typeOptionSansReponse = typeOptionSansReponse;
    }

    public Boolean getAutreOption() {
        return this.autreOption;
    }

    public void setAutreOption(Boolean autreOption) {
        this.autreOption = autreOption;
    }

    public Boolean getOption1() {
        return this.option1;
    }

    public void setOption1(Boolean option1) {
        this.option1 = option1;
    }

    public Boolean getOption2() {
        return this.option2;
    }

    public void setOption2(Boolean option2) {
        this.option2 = option2;
    }

    public Boolean getOption3() {
        return this.option3;
    }

    public void setOption3(Boolean option3) {
        this.option3 = option3;
    }

    public Boolean getOption4() {
        return this.option4;
    }

    public void setOption4(Boolean option4) {
        this.option4 = option4;
    }

    public Boolean getOption5() {
        return this.option5;
    }

    public void setOption5(Boolean option5) {
        this.option5 = option5;
    }

    public Boolean getOption6() {
        return this.option6;
    }

    public void setOption6(Boolean option6) {
        this.option6 = option6;
    }

    public Boolean getOption7() {
        return this.option7;
    }

    public void setOption7(Boolean option7) {
        this.option7 = option7;
    }

    public Boolean getOption8() {
        return this.option8;
    }

    public void setOption8(Boolean option8) {
        this.option8 = option8;
    }

    public Boolean getOption9() {
        return this.option9;
    }

    public void setOption9(Boolean option9) {
        this.option9 = option9;
    }

    public Boolean getOption10() {
        return this.option10;
    }

    public void setOption10(Boolean option10) {
        this.option10 = option10;
    }

    public Question getQuestion() {
        return this.question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public SessionInterview getSessionInterview() {
        return this.sessionInterview;
    }

    public void setSessionInterview(SessionInterview sessionInterview) {
        this.sessionInterview = sessionInterview;
    }

    public Questionnaire getQuestionnaire() {
        return this.questionnaire;
    }

    public void setQuestionnaire(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Reponse)) {
            return false;
        }
        return getId() != null && getId().equals(((Reponse) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}