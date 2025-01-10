package app.domain.execution.optionReponse;

import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import app.domain.conception.option.Option;
import app.domain.execution.reponse.Reponse;
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
@Table(name = "option_reponse")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OptionReponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_option_reponse")
    @SequenceGenerator(name = "seq_option_reponse", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "code", nullable = false)
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    private Option option;

    @ManyToOne(fetch = FetchType.LAZY)
    private Reponse reponse;

    public Long getId() {
        return this.id;
    }

    public OptionReponse id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayString() {
        return code;
    }

    public Long getIdOptionReponse() {
        return this.id;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Option getOption() {
        return this.option;
    }

    public void setOption(Option option) {
        this.option = option;
    }

    public Reponse getReponse() {
        return this.reponse;
    }

    public void setReponse(Reponse reponse) {
        this.reponse = reponse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OptionReponse)) {
            return false;
        }
        return getId() != null && getId().equals(((OptionReponse) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}