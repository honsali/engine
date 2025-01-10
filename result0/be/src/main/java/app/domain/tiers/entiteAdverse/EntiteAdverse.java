package app.domain.tiers.entiteAdverse;

import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import app.domain.commun.reference.reference.Reference;
import app.domain.sinistre.sinistre.Sinistre;
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
@Table(name = "entite_adverse")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EntiteAdverse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_entite_adverse")
    @SequenceGenerator(name = "seq_entite_adverse", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @ManyToOne(fetch = FetchType.LAZY)
    private Reference typeIdentification;

    @Column(name = "numero_identification")
    private String numeroIdentification;

    @Column(name = "adresse")
    private String adresse;

    @ManyToOne(fetch = FetchType.LAZY)
    private Reference assureur;

    @Column(name = "code_intermediaire")
    private String codeIntermediaire;

    @Column(name = "nom_intermediaire")
    private String nomIntermediaire;

    @Column(name = "immatriculation_vehicule")
    private String immatriculationVehicule;

    @Column(name = "numero_police")
    private String numeroPolice;

    @Column(name = "date_debut_contrat")
    private LocalDate dateDebutContrat;

    @Column(name = "date_fin_contrat")
    private LocalDate dateFinContrat;

    @Column(name = "degats_materiel")
    private Boolean degatsMateriel;

    @Column(name = "degats_corporel")
    private Boolean degatsCorporel;

    @Column(name = "personne_physique")
    private Boolean personnePhysique;

    @Column(name = "personne_morale")
    private Boolean personneMorale;

    @Column(name = "date_naissance")
    private LocalDate dateNaissance;

    @ManyToOne(fetch = FetchType.LAZY)
    private Reference sexe;

    @ManyToOne(fetch = FetchType.LAZY)
    private Reference ville;

    @ManyToOne(fetch = FetchType.LAZY)
    private Reference qualiteVictime;

    @ManyToOne(fetch = FetchType.LAZY)
    private Reference situationFamiliale;

    @Column(name = "tel")
    private String tel;

    @Column(name = "email")
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    private Reference profession;

    @ManyToOne(fetch = FetchType.LAZY)
    private Sinistre sinistre;

    public Long getId() {
        return this.id;
    }

    public EntiteAdverse id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayString() {
        return nom;
    }

    public Long getIdEntiteAdverse() {
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

    public String getAdresse() {
        return this.adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Reference getAssureur() {
        return this.assureur;
    }

    public void setAssureur(Reference assureur) {
        this.assureur = assureur;
    }

    public String getCodeIntermediaire() {
        return this.codeIntermediaire;
    }

    public void setCodeIntermediaire(String codeIntermediaire) {
        this.codeIntermediaire = codeIntermediaire;
    }

    public String getNomIntermediaire() {
        return this.nomIntermediaire;
    }

    public void setNomIntermediaire(String nomIntermediaire) {
        this.nomIntermediaire = nomIntermediaire;
    }

    public String getImmatriculationVehicule() {
        return this.immatriculationVehicule;
    }

    public void setImmatriculationVehicule(String immatriculationVehicule) {
        this.immatriculationVehicule = immatriculationVehicule;
    }

    public String getNumeroPolice() {
        return this.numeroPolice;
    }

    public void setNumeroPolice(String numeroPolice) {
        this.numeroPolice = numeroPolice;
    }

    public LocalDate getDateDebutContrat() {
        return this.dateDebutContrat;
    }

    public void setDateDebutContrat(LocalDate dateDebutContrat) {
        this.dateDebutContrat = dateDebutContrat;
    }

    public LocalDate getDateFinContrat() {
        return this.dateFinContrat;
    }

    public void setDateFinContrat(LocalDate dateFinContrat) {
        this.dateFinContrat = dateFinContrat;
    }

    public Boolean getDegatsMateriel() {
        return this.degatsMateriel;
    }

    public void setDegatsMateriel(Boolean degatsMateriel) {
        this.degatsMateriel = degatsMateriel;
    }

    public Boolean getDegatsCorporel() {
        return this.degatsCorporel;
    }

    public void setDegatsCorporel(Boolean degatsCorporel) {
        this.degatsCorporel = degatsCorporel;
    }

    public Boolean getPersonnePhysique() {
        return this.personnePhysique;
    }

    public void setPersonnePhysique(Boolean personnePhysique) {
        this.personnePhysique = personnePhysique;
    }

    public Boolean getPersonneMorale() {
        return this.personneMorale;
    }

    public void setPersonneMorale(Boolean personneMorale) {
        this.personneMorale = personneMorale;
    }

    public LocalDate getDateNaissance() {
        return this.dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Reference getSexe() {
        return this.sexe;
    }

    public void setSexe(Reference sexe) {
        this.sexe = sexe;
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

    public Reference getProfession() {
        return this.profession;
    }

    public void setProfession(Reference profession) {
        this.profession = profession;
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
        if (!(o instanceof EntiteAdverse)) {
            return false;
        }
        return getId() != null && getId().equals(((EntiteAdverse) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}