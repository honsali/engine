package app.domain.commun.reference.reference;

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
@Table(name = "reference")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Reference implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_reference")
    @SequenceGenerator(name = "seq_reference", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "description")
    private String description;

    @Column(name = "taux")
    private String taux;

    @Column(name = "nom_ou_raison_sociale")
    private String nomOuRaisonSociale;

    public Long getId() {
        return this.id;
    }

    public Reference id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayString() {
        return code;
    }

    public Long getIdReference() {
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

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTaux() {
        return this.taux;
    }

    public void setTaux(String taux) {
        this.taux = taux;
    }

    public String getNomOuRaisonSociale() {
        return this.nomOuRaisonSociale;
    }

    public void setNomOuRaisonSociale(String nomOuRaisonSociale) {
        this.nomOuRaisonSociale = nomOuRaisonSociale;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Reference)) {
            return false;
        }
        return getId() != null && getId().equals(((Reference) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}