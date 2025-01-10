package app.domain.tiers.ayantDroit;

import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import app.domain.commun.reference.reference.Reference;
import app.domain.sinistre.sousDossier.SousDossier;
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
@Table(name = "ayant_droit")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AyantDroit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_ayant_droit")
    @SequenceGenerator(name = "seq_ayant_droit", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "id_tiers")
    private String idTiers;

    @NotNull
    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "nom_prenom_arabe")
    private String nomPrenomArabe;

    @ManyToOne(fetch = FetchType.LAZY)
    private Reference typeIdentification;

    @Column(name = "numero_identification")
    private String numeroIdentification;

    @ManyToOne(fetch = FetchType.LAZY)
    private Reference sexe;

    @Column(name = "date_naissance")
    private LocalDate dateNaissance;

    @ManyToOne(fetch = FetchType.LAZY)
    private Reference situationFamiliale;

    @ManyToOne(fetch = FetchType.LAZY)
    private Reference lienParente;

    @Column(name = "adresse")
    private String adresse;

    @ManyToOne(fetch = FetchType.LAZY)
    private Reference ville;

    @Column(name = "tel")
    private String tel;

    @Column(name = "email")
    private String email;

    @Column(name = "infirme")
    private Boolean infirme;

    @Column(name = "id_sinistre")
    private String idSinistre;

    @Column(name = "id_sous_dossier")
    private String idSousDossier;

    @Column(name = "id_sous_dossier_victime_decede")
    private String idSousDossierVictimeDecede;

    @Column(name = "obligation_alimentaire")
    private Boolean obligationAlimentaire;

    @Column(name = "perte_ressource")
    private Boolean perteRessource;

    @ManyToOne(fetch = FetchType.LAZY)
    private SousDossier sousDossier;

    public Long getId() {
        return this.id;
    }

    public AyantDroit id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayString() {
        return nom;
    }

    public Long getIdAyantDroit() {
        return this.id;
    }

    public String getIdTiers() {
        return this.idTiers;
    }

    public void setIdTiers(String idTiers) {
        this.idTiers = idTiers;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNomPrenomArabe() {
        return this.nomPrenomArabe;
    }

    public void setNomPrenomArabe(String nomPrenomArabe) {
        this.nomPrenomArabe = nomPrenomArabe;
    }

    public Reference getTypeIdentification() {
        return this.typeIdentification;
    }

    public void setTypeIdentification(Reference typeIdentification) {
        this.typeIdentification = typeIdentification;
    }

    public String getNumeroIdentification() {
        return this.numeroIdentification;
    }

    public void setNumeroIdentification(String numeroIdentification) {
        this.numeroIdentification = numeroIdentification;
    }

    public Reference getSexe() {
        return this.sexe;
    }

    public void setSexe(Reference sexe) {
        this.sexe = sexe;
    }

    public LocalDate getDateNaissance() {
        return this.dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Reference getSituationFamiliale() {
        return this.situationFamiliale;
    }

    public void setSituationFamiliale(Reference situationFamiliale) {
        this.situationFamiliale = situationFamiliale;
    }

    public Reference getLienParente() {
        return this.lienParente;
    }

    public void setLienParente(Reference lienParente) {
        this.lienParente = lienParente;
    }

    public String getAdresse() {
        return this.adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Reference getVille() {
        return this.ville;
    }

    public void setVille(Reference ville) {
        this.ville = ville;
    }

    public String getTel() {
        return this.tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getInfirme() {
        return this.infirme;
    }

    public void setInfirme(Boolean infirme) {
        this.infirme = infirme;
    }

    public String getIdSinistre() {
        return this.idSinistre;
    }

    public void setIdSinistre(String idSinistre) {
        this.idSinistre = idSinistre;
    }

    public String getIdSousDossier() {
        return this.idSousDossier;
    }

    public void setIdSousDossier(String idSousDossier) {
        this.idSousDossier = idSousDossier;
    }

    public String getIdSousDossierVictimeDecede() {
        return this.idSousDossierVictimeDecede;
    }

    public void setIdSousDossierVictimeDecede(String idSousDossierVictimeDecede) {
        this.idSousDossierVictimeDecede = idSousDossierVictimeDecede;
    }

    public Boolean getObligationAlimentaire() {
        return this.obligationAlimentaire;
    }

    public void setObligationAlimentaire(Boolean obligationAlimentaire) {
        this.obligationAlimentaire = obligationAlimentaire;
    }

    public Boolean getPerteRessource() {
        return this.perteRessource;
    }

    public void setPerteRessource(Boolean perteRessource) {
        this.perteRessource = perteRessource;
    }

    public SousDossier getSousDossier() {
        return this.sousDossier;
    }

    public void setSousDossier(SousDossier sousDossier) {
        this.sousDossier = sousDossier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AyantDroit)) {
            return false;
        }
        return getId() != null && getId().equals(((AyantDroit) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}