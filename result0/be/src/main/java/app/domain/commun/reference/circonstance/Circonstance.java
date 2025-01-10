package app.domain.commun.reference.circonstance;

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
@Table(name = "circonstance")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Circonstance implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_circonstance")
    @SequenceGenerator(name = "seq_circonstance", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "part_responsabilite_code", nullable = false)
    private String partResponsabiliteCode;

    @Column(name = "part_responsabilite_libelle")
    private String partResponsabiliteLibelle;

    public Long getId() {
        return this.id;
    }

    public Circonstance id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayString() {
        return partResponsabiliteCode;
    }

    public Long getIdCirconstance() {
        return this.id;
    }

    public String getPartResponsabiliteCode() {
        return this.partResponsabiliteCode;
    }

    public void setPartResponsabiliteCode(String partResponsabiliteCode) {
        this.partResponsabiliteCode = partResponsabiliteCode;
    }

    public String getPartResponsabiliteLibelle() {
        return this.partResponsabiliteLibelle;
    }

    public void setPartResponsabiliteLibelle(String partResponsabiliteLibelle) {
        this.partResponsabiliteLibelle = partResponsabiliteLibelle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Circonstance)) {
            return false;
        }
        return getId() != null && getId().equals(((Circonstance) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}