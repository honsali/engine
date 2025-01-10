package app.domain.sinistre.identification.identificationTribunal;

import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import app.domain.commun.reference.reference.Reference;
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
@Table(name = "identification_tribunal")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class IdentificationTribunal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_identification_tribunal")
    @SequenceGenerator(name = "seq_identification_tribunal", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Reference villeTribunal;

    @ManyToOne(fetch = FetchType.LAZY)
    private Reference tribunal;

    @NotNull
    @Column(name = "numero_dossier", nullable = false)
    private String numeroDossier;

    @ManyToOne(fetch = FetchType.LAZY)
    private Reference numeroChambre;

    @ManyToOne(fetch = FetchType.LAZY)
    private Reference phase;

    @Column(name = "exercice")
    private String exercice;

    @Column(name = "date_audience")
    private String dateAudience;

    @Column(name = "date_jugement")
    private String dateJugement;

    public Long getId() {
        return this.id;
    }

    public IdentificationTribunal id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayString() {
        return numeroDossier;
    }

    public Long getIdIdentificationTribunal() {
        return this.id;
    }

    public Reference getVilleTribunal() {
        return this.villeTribunal;
    }

    public void setVilleTribunal(Reference villeTribunal) {
        this.villeTribunal = villeTribunal;
    }

    public Reference getTribunal() {
        return this.tribunal;
    }

    public void setTribunal(Reference tribunal) {
        this.tribunal = tribunal;
    }

    public String getNumeroDossier() {
        return this.numeroDossier;
    }

    public void setNumeroDossier(String numeroDossier) {
        this.numeroDossier = numeroDossier;
    }

    public Reference getNumeroChambre() {
        return this.numeroChambre;
    }

    public void setNumeroChambre(Reference numeroChambre) {
        this.numeroChambre = numeroChambre;
    }

    public Reference getPhase() {
        return this.phase;
    }

    public void setPhase(Reference phase) {
        this.phase = phase;
    }

    public String getExercice() {
        return this.exercice;
    }

    public void setExercice(String exercice) {
        this.exercice = exercice;
    }

    public String getDateAudience() {
        return this.dateAudience;
    }

    public void setDateAudience(String dateAudience) {
        this.dateAudience = dateAudience;
    }

    public String getDateJugement() {
        return this.dateJugement;
    }

    public void setDateJugement(String dateJugement) {
        this.dateJugement = dateJugement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof IdentificationTribunal)) {
            return false;
        }
        return getId() != null && getId().equals(((IdentificationTribunal) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}