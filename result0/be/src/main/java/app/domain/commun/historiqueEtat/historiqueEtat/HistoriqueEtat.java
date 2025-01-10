package app.domain.commun.historiqueEtat.historiqueEtat;

import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import app.domain.commun.historiqueEtat.etat.Etat;
import app.domain.commun.historiqueEtat.motif.Motif;
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
@Table(name = "historique_etat")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class HistoriqueEtat implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_historique_etat")
    @SequenceGenerator(name = "seq_historique_etat", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "date_effet")
    private LocalDate dateEffet;

    @Column(name = "commentaire")
    private String commentaire;

    @ManyToOne(fetch = FetchType.LAZY)
    private Etat etat;

    @ManyToOne(fetch = FetchType.LAZY)
    private Motif motif;

    @Column(name = "gestionnaire")
    private String gestionnaire;

    @NotNull
    @Column(name = "nom_entite", nullable = false)
    private String nomEntite;

    @Column(name = "id_entite")
    private String idEntite;

    public Long getId() {
        return this.id;
    }

    public HistoriqueEtat id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayString() {
        return nomEntite;
    }

    public Long getIdHistoriqueEtat() {
        return this.id;
    }

    public LocalDate getDateEffet() {
        return this.dateEffet;
    }

    public void setDateEffet(LocalDate dateEffet) {
        this.dateEffet = dateEffet;
    }

    public String getCommentaire() {
        return this.commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Etat getEtat() {
        return this.etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public Motif getMotif() {
        return this.motif;
    }

    public void setMotif(Motif motif) {
        this.motif = motif;
    }

    public String getGestionnaire() {
        return this.gestionnaire;
    }

    public void setGestionnaire(String gestionnaire) {
        this.gestionnaire = gestionnaire;
    }

    public String getNomEntite() {
        return this.nomEntite;
    }

    public void setNomEntite(String nomEntite) {
        this.nomEntite = nomEntite;
    }

    public String getIdEntite() {
        return this.idEntite;
    }

    public void setIdEntite(String idEntite) {
        this.idEntite = idEntite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HistoriqueEtat)) {
            return false;
        }
        return getId() != null && getId().equals(((HistoriqueEtat) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}