package app.domain.tiers.victime;

import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import app.domain.commun.reference.reference.Reference;
import app.domain.sinistre.sinistre.Sinistre;
import app.domain.tiers.entiteAdverse.EntiteAdverse;
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
@Table(name = "victime")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Victime implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_victime")
    @SequenceGenerator(name = "seq_victime", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "date_naissance")
    private LocalDate dateNaissance;

    @Column(name = "nom_prenom_arabe")
    private String nomPrenomArabe;

    @ManyToOne(fetch = FetchType.LAZY)
    private Reference sexe;

    @ManyToOne(fetch = FetchType.LAZY)
    private Reference typeIdentification;

    @Column(name = "numero_identification")
    private String numeroIdentification;

    @ManyToOne(fetch = FetchType.LAZY)
    private Reference ville;

    @ManyToOne(fetch = FetchType.LAZY)
    private Reference qualiteVictime;

    @Column(name = "mineur")
    private Boolean mineur;

    @Column(name = "ayant_droit")
    private Boolean ayantDroit;

    @Column(name = "id_client")
    private String idClient;

    @Column(name = "id_tiers_associe")
    private Integer idTiersAssocie;

    @ManyToOne(fetch = FetchType.LAZY)
    private Reference situationFamiliale;

    @Column(name = "tel")
    private String tel;

    @Column(name = "email")
    private String email;

    @Column(name = "categorie")
    private String categorie;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "numero_police_externe")
    private String numeroPoliceExterne;

    @ManyToOne(fetch = FetchType.LAZY)
    private Reference profession;

    @ManyToOne(fetch = FetchType.LAZY)
    private Reference assureur;

    @Column(name = "id_sous_dossier_victime")
    private String idSousDossierVictime;

    @ManyToOne(fetch = FetchType.LAZY)
    private Reference lienParente;

    @Column(name = "sans_materiel")
    private Boolean sansMateriel;

    @ManyToOne(fetch = FetchType.LAZY)
    private EntiteAdverse entiteAdverse;

    @ManyToOne(fetch = FetchType.LAZY)
    private Sinistre sinistre;

    public Long getId() {
        return this.id;
    }

    public Victime id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayString() {
        return nom;
    }

    public Long getIdVictime() {
        return this.id;
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

    public LocalDate getDateNaissance() {
        return this.dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getNomPrenomArabe() {
        return this.nomPrenomArabe;
    }

    public void setNomPrenomArabe(String nomPrenomArabe) {
        this.nomPrenomArabe = nomPrenomArabe;
    }

    public Reference getSexe() {
        return this.sexe;
    }

    public void setSexe(Reference sexe) {
        this.sexe = sexe;
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

    public Reference getVille() {
        return this.ville;
    }

    public void setVille(Reference ville) {
        this.ville = ville;
    }

    public Reference getQualiteVictime() {
        return this.qualiteVictime;
    }

    public void setQualiteVictime(Reference qualiteVictime) {
        this.qualiteVictime = qualiteVictime;
    }

    public Boolean getMineur() {
        return this.mineur;
    }

    public void setMineur(Boolean mineur) {
        this.mineur = mineur;
    }

    public Boolean getAyantDroit() {
        return this.ayantDroit;
    }

    public void setAyantDroit(Boolean ayantDroit) {
        this.ayantDroit = ayantDroit;
    }

    public String getIdClient() {
        return this.idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public Integer getIdTiersAssocie() {
        return this.idTiersAssocie;
    }

    public void setIdTiersAssocie(Integer idTiersAssocie) {
        this.idTiersAssocie = idTiersAssocie;
    }

    public Reference getSituationFamiliale() {
        return this.situationFamiliale;
    }

    public void setSituationFamiliale(Reference situationFamiliale) {
        this.situationFamiliale = situationFamiliale;
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

    public String getCategorie() {
        return this.categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getAdresse() {
        return this.adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNumeroPoliceExterne() {
        return this.numeroPoliceExterne;
    }

    public void setNumeroPoliceExterne(String numeroPoliceExterne) {
        this.numeroPoliceExterne = numeroPoliceExterne;
    }

    public Reference getProfession() {
        return this.profession;
    }

    public void setProfession(Reference profession) {
        this.profession = profession;
    }

    public Reference getAssureur() {
        return this.assureur;
    }

    public void setAssureur(Reference assureur) {
        this.assureur = assureur;
    }

    public String getIdSousDossierVictime() {
        return this.idSousDossierVictime;
    }

    public void setIdSousDossierVictime(String idSousDossierVictime) {
        this.idSousDossierVictime = idSousDossierVictime;
    }

    public Reference getLienParente() {
        return this.lienParente;
    }

    public void setLienParente(Reference lienParente) {
        this.lienParente = lienParente;
    }

    public Boolean getSansMateriel() {
        return this.sansMateriel;
    }

    public void setSansMateriel(Boolean sansMateriel) {
        this.sansMateriel = sansMateriel;
    }

    public EntiteAdverse getEntiteAdverse() {
        return this.entiteAdverse;
    }

    public void setEntiteAdverse(EntiteAdverse entiteAdverse) {
        this.entiteAdverse = entiteAdverse;
    }

    public Sinistre getSinistre() {
        return this.sinistre;
    }

    public void setSinistre(Sinistre sinistre) {
        this.sinistre = sinistre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Victime)) {
            return false;
        }
        return getId() != null && getId().equals(((Victime) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}