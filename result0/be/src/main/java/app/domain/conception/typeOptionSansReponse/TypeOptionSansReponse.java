package app.domain.conception.typeOptionSansReponse;

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
@Table(name = "type_option_sans_reponse")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TypeOptionSansReponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_type_option_sans_reponse")
    @SequenceGenerator(name = "seq_type_option_sans_reponse", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "code", nullable = false)
    private String code;

    @NotNull
    @Column(name = "libelle", nullable = false)
    private String libelle;

    public Long getId() {
        return this.id;
    }

    public TypeOptionSansReponse id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdTypeOptionSansReponse() {
        return this.id;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
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
        if (!(o instanceof TypeOptionSansReponse)) {
            return false;
        }
        return getId() != null && getId().equals(((TypeOptionSansReponse) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}