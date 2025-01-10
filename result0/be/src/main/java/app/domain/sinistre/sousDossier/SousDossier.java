package app.domain.sinistre.sousDossier;

import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import app.domain.sinistre.tiers.tiers.Tiers;
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
@Table(name = "sous_dossier")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SousDossier implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_sous_dossier")
    @SequenceGenerator(name = "seq_sous_dossier", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "date_survenance", nullable = false)
    private String dateSurvenance;

    @Column(name = "heure_survenance")
    private String heureSurvenance;

    @Column(name = "etat")
    private String etat;

    @Column(name = "date_ouverture")
    private String dateOuverture;

    @Column(name = "date_declaration")
    private String dateDeclaration;

    @Column(name = "est_master")
    private Boolean estMaster;

    @Column(name = "id_utilisateur")
    private String idUtilisateur;

    @Column(name = "id_sinistre")
    private String idSinistre;

    @Column(name = "profession")
    private String profession;

    @Column(name = "revenu")
    private Integer revenu;

    @Column(name = "type_revenu")
    private String typeRevenu;

    @Column(name = "decede")
    private Boolean decede;

    @Column(name = "degat_mat")
    private Boolean degatMat;

    @Column(name = "degat_corpo")
    private Boolean degatCorpo;

    @ManyToOne(fetch = FetchType.LAZY)
    private Tiers tiers;

    public Long getId() {
        return this.id;
    }

    public SousDossier id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayString() {
        return dateSurvenance;
    }

    public Long getIdSousDossier() {
        return this.id;
    }

    public String getDateSurvenance() {
        return this.dateSurvenance;
    }

    public void setDateSurvenance(String dateSurvenance) {
        this.dateSurvenance = dateSurvenance;
    }

    public String getHeureSurvenance() {
        return this.heureSurvenance;
    }

    public void setHeureSurvenance(String heureSurvenance) {
        this.heureSurvenance = heureSurvenance;
    }

    public String getEtat() {
        return this.etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getDateOuverture() {
        return this.dateOuverture;
    }

    public void setDateOuverture(String dateOuverture) {
        this.dateOuverture = dateOuverture;
    }

    public String getDateDeclaration() {
        return this.dateDeclaration;
    }

    public void setDateDeclaration(String dateDeclaration) {
        this.dateDeclaration = dateDeclaration;
    }

    public Boolean getEstMaster() {
        return this.estMaster;
    }

    public void setEstMaster(Boolean estMaster) {
        this.estMaster = estMaster;
    }

    public String getIdUtilisateur() {
        return this.idUtilisateur;
    }

    public void setIdUtilisateur(String idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getIdSinistre() {
        return this.idSinistre;
    }

    public void setIdSinistre(String idSinistre) {
        this.idSinistre = idSinistre;
    }

    public String getProfession() {
        return this.profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public Integer getRevenu() {
        return this.revenu;
    }

    public void setRevenu(Integer revenu) {
        this.revenu = revenu;
    }

    public String getTypeRevenu() {
        return this.typeRevenu;
    }

    public void setTypeRevenu(String typeRevenu) {
        this.typeRevenu = typeRevenu;
    }

    public Boolean getDecede() {
        return this.decede;
    }

    public void setDecede(Boolean decede) {
        this.decede = decede;
    }

    public Boolean getDegatMat() {
        return this.degatMat;
    }

    public void setDegatMat(Boolean degatMat) {
        this.degatMat = degatMat;
    }

    public Boolean getDegatCorpo() {
        return this.degatCorpo;
    }

    public void setDegatCorpo(Boolean degatCorpo) {
        this.degatCorpo = degatCorpo;
    }

    public Tiers getTiers() {
        return this.tiers;
    }

    public void setTiers(Tiers tiers) {
        this.tiers = tiers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SousDossier)) {
            return false;
        }
        return getId() != null && getId().equals(((SousDossier) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}