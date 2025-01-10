package app.domain.sinistre.sinistre;

import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import app.domain.commun.expediteur.expediteur.Expediteur;
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
@Table(name = "sinistre")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Sinistre implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_sinistre")
    @SequenceGenerator(name = "seq_sinistre", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "numero_sinistre", nullable = false)
    private String numeroSinistre;

    @Column(name = "date_ouverture")
    private String dateOuverture;

    @Column(name = "date_survenance")
    private String dateSurvenance;

    @Column(name = "heure_survenance")
    private String heureSurvenance;

    @Column(name = "date_heure_survenance")
    private String dateHeureSurvenance;

    @Column(name = "etat")
    private String etat;

    @Column(name = "montant_reserve_global")
    private Integer montantReserveGlobal;

    @Column(name = "segment_dep")
    private String segmentDep;

    @Column(name = "numero_declaration")
    private String numeroDeclaration;

    @Column(name = "date_declaration")
    private String dateDeclaration;

    @Column(name = "numero_sinistre_intermediaire")
    private String numeroSinistreIntermediaire;

    @Column(name = "date_effet_contrat")
    private String dateEffetContrat;

    @Column(name = "date_fin_contrat")
    private String dateFinContrat;

    @Column(name = "validite")
    private String validite;

    @ManyToOne(fetch = FetchType.LAZY)
    private Reference etatPolice;

    @Column(name = "numero_police")
    private Integer numeroPolice;

    @Column(name = "categorie")
    private String categorie;

    @Column(name = "libelle_produit_technique")
    private String libelleProduitTechnique;

    @ManyToOne(fetch = FetchType.LAZY)
    private Expediteur intermediaire;

    @Column(name = "nom_ou_raison_sociale_client")
    private String nomOuRaisonSocialeClient;

    @ManyToOne(fetch = FetchType.LAZY)
    private Reference produitCommercial;

    public Long getId() {
        return this.id;
    }

    public Sinistre id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayString() {
        return numeroSinistre;
    }

    public Long getIdSinistre() {
        return this.id;
    }

    public String getNumeroSinistre() {
        return this.numeroSinistre;
    }

    public void setNumeroSinistre(String numeroSinistre) {
        this.numeroSinistre = numeroSinistre;
    }

    public String getDateOuverture() {
        return this.dateOuverture;
    }

    public void setDateOuverture(String dateOuverture) {
        this.dateOuverture = dateOuverture;
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

    public String getDateHeureSurvenance() {
        return this.dateHeureSurvenance;
    }

    public void setDateHeureSurvenance(String dateHeureSurvenance) {
        this.dateHeureSurvenance = dateHeureSurvenance;
    }

    public String getEtat() {
        return this.etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Integer getMontantReserveGlobal() {
        return this.montantReserveGlobal;
    }

    public void setMontantReserveGlobal(Integer montantReserveGlobal) {
        this.montantReserveGlobal = montantReserveGlobal;
    }

    public String getSegmentDep() {
        return this.segmentDep;
    }

    public void setSegmentDep(String segmentDep) {
        this.segmentDep = segmentDep;
    }

    public String getNumeroDeclaration() {
        return this.numeroDeclaration;
    }

    public void setNumeroDeclaration(String numeroDeclaration) {
        this.numeroDeclaration = numeroDeclaration;
    }

    public String getDateDeclaration() {
        return this.dateDeclaration;
    }

    public void setDateDeclaration(String dateDeclaration) {
        this.dateDeclaration = dateDeclaration;
    }

    public String getNumeroSinistreIntermediaire() {
        return this.numeroSinistreIntermediaire;
    }

    public void setNumeroSinistreIntermediaire(String numeroSinistreIntermediaire) {
        this.numeroSinistreIntermediaire = numeroSinistreIntermediaire;
    }

    public String getDateEffetContrat() {
        return this.dateEffetContrat;
    }

    public void setDateEffetContrat(String dateEffetContrat) {
        this.dateEffetContrat = dateEffetContrat;
    }

    public String getDateFinContrat() {
        return this.dateFinContrat;
    }

    public void setDateFinContrat(String dateFinContrat) {
        this.dateFinContrat = dateFinContrat;
    }

    public String getValidite() {
        return this.validite;
    }

    public void setValidite(String validite) {
        this.validite = validite;
    }

    public Reference getEtatPolice() {
        return this.etatPolice;
    }

    public void setEtatPolice(Reference etatPolice) {
        this.etatPolice = etatPolice;
    }

    public Integer getNumeroPolice() {
        return this.numeroPolice;
    }

    public void setNumeroPolice(Integer numeroPolice) {
        this.numeroPolice = numeroPolice;
    }

    public String getCategorie() {
        return this.categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getLibelleProduitTechnique() {
        return this.libelleProduitTechnique;
    }

    public void setLibelleProduitTechnique(String libelleProduitTechnique) {
        this.libelleProduitTechnique = libelleProduitTechnique;
    }

    public Expediteur getIntermediaire() {
        return this.intermediaire;
    }

    public void setIntermediaire(Expediteur intermediaire) {
        this.intermediaire = intermediaire;
    }

    public String getNomOuRaisonSocialeClient() {
        return this.nomOuRaisonSocialeClient;
    }

    public void setNomOuRaisonSocialeClient(String nomOuRaisonSocialeClient) {
        this.nomOuRaisonSocialeClient = nomOuRaisonSocialeClient;
    }

    public Reference getProduitCommercial() {
        return this.produitCommercial;
    }

    public void setProduitCommercial(Reference produitCommercial) {
        this.produitCommercial = produitCommercial;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Sinistre)) {
            return false;
        }
        return getId() != null && getId().equals(((Sinistre) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}