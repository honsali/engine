package app.domain.sinistre.identification;

import java.io.Serializable;
import java.time.LocalDate;
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
@Table(name = "identification")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Identification implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_identification")
    @SequenceGenerator(name = "seq_identification", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "libelle_statut", nullable = false)
    private String libelleStatut;

    @Column(name = "date_ouverture")
    private LocalDate dateOuverture;

    @Column(name = "numero_police")
    private String numeroPolice;

    @Column(name = "numero_serie")
    private String numeroSerie;

    @Column(name = "date_souscription")
    private LocalDate dateSouscription;

    @Column(name = "code_intermediaire")
    private String codeIntermediaire;

    @Column(name = "libelle_intermediaire")
    private String libelleIntermediaire;

    @Column(name = "libelle_assure")
    private String libelleAssure;

    @Column(name = "numero_identification_assure")
    private String numeroIdentificationAssure;

    @Column(name = "numero_vehicule")
    private String numeroVehicule;

    @Column(name = "numero_immatriculation")
    private String numeroImmatriculation;

    @Column(name = "reference_document")
    private String referenceDocument;

    @Column(name = "numero_document")
    private String numeroDocument;

    @ManyToOne(fetch = FetchType.LAZY)
    private Reference familleDocument;

    @ManyToOne(fetch = FetchType.LAZY)
    private Reference typeDocument;

    @Column(name = "date_cachet")
    private LocalDate dateCachet;

    @Column(name = "date_reception")
    private LocalDate dateReception;

    @ManyToOne(fetch = FetchType.LAZY)
    private Reference prestataire;

    @Column(name = "numero_sinistre")
    private String numeroSinistre;

    @Column(name = "date_survenance")
    private LocalDate dateSurvenance;

    @Column(name = "numero_identification_tiers")
    private String numeroIdentificationTiers;

    @Column(name = "libelle_tiers")
    private String libelleTiers;

    @ManyToOne(fetch = FetchType.LAZY)
    private Reference compagnieAdverse;

    @ManyToOne(fetch = FetchType.LAZY)
    private Reference villeTribunal;

    @ManyToOne(fetch = FetchType.LAZY)
    private Reference tribunal;

    @Column(name = "numero_dossier")
    private String numeroDossier;

    @ManyToOne(fetch = FetchType.LAZY)
    private Reference numeroChambre;

    @ManyToOne(fetch = FetchType.LAZY)
    private Reference phase;

    @Column(name = "exercice")
    private LocalDate exercice;

    @Column(name = "date_audience")
    private LocalDate dateAudience;

    @Column(name = "date_jugement")
    private LocalDate dateJugement;

    public Long getId() {
        return this.id;
    }

    public Identification id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayString() {
        return libelleStatut;
    }

    public Long getIdIdentification() {
        return this.id;
    }

    public String getLibelleStatut() {
        return this.libelleStatut;
    }

    public void setLibelleStatut(String libelleStatut) {
        this.libelleStatut = libelleStatut;
    }

    public LocalDate getDateOuverture() {
        return this.dateOuverture;
    }

    public void setDateOuverture(LocalDate dateOuverture) {
        this.dateOuverture = dateOuverture;
    }

    public String getNumeroPolice() {
        return this.numeroPolice;
    }

    public void setNumeroPolice(String numeroPolice) {
        this.numeroPolice = numeroPolice;
    }

    public String getNumeroSerie() {
        return this.numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public LocalDate getDateSouscription() {
        return this.dateSouscription;
    }

    public void setDateSouscription(LocalDate dateSouscription) {
        this.dateSouscription = dateSouscription;
    }

    public String getCodeIntermediaire() {
        return this.codeIntermediaire;
    }

    public void setCodeIntermediaire(String codeIntermediaire) {
        this.codeIntermediaire = codeIntermediaire;
    }

    public String getLibelleIntermediaire() {
        return this.libelleIntermediaire;
    }

    public void setLibelleIntermediaire(String libelleIntermediaire) {
        this.libelleIntermediaire = libelleIntermediaire;
    }

    public String getLibelleAssure() {
        return this.libelleAssure;
    }

    public void setLibelleAssure(String libelleAssure) {
        this.libelleAssure = libelleAssure;
    }

    public String getNumeroIdentificationAssure() {
        return this.numeroIdentificationAssure;
    }

    public void setNumeroIdentificationAssure(String numeroIdentificationAssure) {
        this.numeroIdentificationAssure = numeroIdentificationAssure;
    }

    public String getNumeroVehicule() {
        return this.numeroVehicule;
    }

    public void setNumeroVehicule(String numeroVehicule) {
        this.numeroVehicule = numeroVehicule;
    }

    public String getNumeroImmatriculation() {
        return this.numeroImmatriculation;
    }

    public void setNumeroImmatriculation(String numeroImmatriculation) {
        this.numeroImmatriculation = numeroImmatriculation;
    }

    public String getReferenceDocument() {
        return this.referenceDocument;
    }

    public void setReferenceDocument(String referenceDocument) {
        this.referenceDocument = referenceDocument;
    }

    public String getNumeroDocument() {
        return this.numeroDocument;
    }

    public void setNumeroDocument(String numeroDocument) {
        this.numeroDocument = numeroDocument;
    }

    public Reference getFamilleDocument() {
        return this.familleDocument;
    }

    public void setFamilleDocument(Reference familleDocument) {
        this.familleDocument = familleDocument;
    }

    public Reference getTypeDocument() {
        return this.typeDocument;
    }

    public void setTypeDocument(Reference typeDocument) {
        this.typeDocument = typeDocument;
    }

    public LocalDate getDateCachet() {
        return this.dateCachet;
    }

    public void setDateCachet(LocalDate dateCachet) {
        this.dateCachet = dateCachet;
    }

    public LocalDate getDateReception() {
        return this.dateReception;
    }

    public void setDateReception(LocalDate dateReception) {
        this.dateReception = dateReception;
    }

    public Reference getPrestataire() {
        return this.prestataire;
    }

    public void setPrestataire(Reference prestataire) {
        this.prestataire = prestataire;
    }

    public String getNumeroSinistre() {
        return this.numeroSinistre;
    }

    public void setNumeroSinistre(String numeroSinistre) {
        this.numeroSinistre = numeroSinistre;
    }

    public LocalDate getDateSurvenance() {
        return this.dateSurvenance;
    }

    public void setDateSurvenance(LocalDate dateSurvenance) {
        this.dateSurvenance = dateSurvenance;
    }

    public String getNumeroIdentificationTiers() {
        return this.numeroIdentificationTiers;
    }

    public void setNumeroIdentificationTiers(String numeroIdentificationTiers) {
        this.numeroIdentificationTiers = numeroIdentificationTiers;
    }

    public String getLibelleTiers() {
        return this.libelleTiers;
    }

    public void setLibelleTiers(String libelleTiers) {
        this.libelleTiers = libelleTiers;
    }

    public Reference getCompagnieAdverse() {
        return this.compagnieAdverse;
    }

    public void setCompagnieAdverse(Reference compagnieAdverse) {
        this.compagnieAdverse = compagnieAdverse;
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

    public LocalDate getExercice() {
        return this.exercice;
    }

    public void setExercice(LocalDate exercice) {
        this.exercice = exercice;
    }

    public LocalDate getDateAudience() {
        return this.dateAudience;
    }

    public void setDateAudience(LocalDate dateAudience) {
        this.dateAudience = dateAudience;
    }

    public LocalDate getDateJugement() {
        return this.dateJugement;
    }

    public void setDateJugement(LocalDate dateJugement) {
        this.dateJugement = dateJugement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Identification)) {
            return false;
        }
        return getId() != null && getId().equals(((Identification) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}