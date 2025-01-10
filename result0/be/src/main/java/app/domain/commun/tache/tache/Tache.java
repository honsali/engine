package app.domain.commun.tache.tache;

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
@Table(name = "tache")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Tache implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tache")
    @SequenceGenerator(name = "seq_tache", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "id_process")
    private String idProcess;

    @NotNull
    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "categorie")
    private String categorie;

    @Column(name = "code")
    private String code;

    @Column(name = "code_tenant")
    private String codeTenant;

    @Column(name = "code_document")
    private String codeDocument;

    @Column(name = "id_document")
    private String idDocument;

    @Column(name = "id_sous_dossier")
    private String idSousDossier;

    @Column(name = "nom_tiers")
    private String nomTiers;

    @Column(name = "prenom_tiers")
    private String prenomTiers;

    @Column(name = "numero_document")
    private String numeroDocument;

    @Column(name = "libelle_famille")
    private String libelleFamille;

    @Column(name = "code_famille")
    private String codeFamille;

    @Column(name = "code_type_document")
    private String codeTypeDocument;

    @Column(name = "type_document_jugement")
    private String typeDocumentJugement;

    @Column(name = "libelle_type_document")
    private String libelleTypeDocument;

    @Column(name = "canal")
    private String canal;

    @Column(name = "date_creation")
    private LocalDate dateCreation;

    @Column(name = "date_reception")
    private String dateReception;

    @Column(name = "date_cachet")
    private String dateCachet;

    @Column(name = "date_numerisation")
    private String dateNumerisation;

    @Column(name = "statut_document")
    private String statutDocument;

    @Column(name = "utilisateur_source")
    private String utilisateurSource;

    @Column(name = "utilisateur_destinataire")
    private String utilisateurDestinataire;

    @Column(name = "reference_document")
    private String referenceDocument;

    @Column(name = "instance_ged")
    private String instanceGed;

    @Column(name = "cat_sinistre")
    private String catSinistre;

    @Column(name = "exe_sinistre")
    private String exeSinistre;

    @Column(name = "num_sinistre")
    private String numSinistre;

    @Column(name = "id_sinistre")
    private String idSinistre;

    @Column(name = "id_ouverture")
    private String idOuverture;

    @Column(name = "id_jugement")
    private String idJugement;

    @Column(name = "numero_sinistre")
    private String numeroSinistre;

    @Column(name = "statut")
    private String statut;

    @Column(name = "delai_restant")
    private String delaiRestant;

    @Column(name = "priorite")
    private String priorite;

    @Column(name = "id_object")
    private String idObject;

    @Column(name = "role")
    private String role;

    @ManyToOne(fetch = FetchType.LAZY)
    private Reference familleCible;

    @Column(name = "destinataire")
    private String destinataire;

    @Column(name = "action")
    private String action;

    @Column(name = "tiers")
    private String tiers;

    @Column(name = "commentaire")
    private String commentaire;

    @Column(name = "numero_ordre")
    private String numeroOrdre;

    @Column(name = "chambre")
    private String chambre;

    @Column(name = "exercice")
    private String exercice;

    @Column(name = "date_jugement")
    private String dateJugement;

    @Column(name = "code_mail_type")
    private String codeMailType;

    @Column(name = "id_affaire")
    private String idAffaire;

    @Column(name = "numero_affaire")
    private String numeroAffaire;

    @Column(name = "id_mission")
    private String idMission;

    @Column(name = "numero_dossier_judiciaire")
    private String numeroDossierJudiciaire;

    @Column(name = "equipe")
    private String equipe;

    @Column(name = "id_offre")
    private String idOffre;

    @Column(name = "liste_beneficiaire_reglement")
    private String listeBeneficiaireReglement;

    @ManyToOne(fetch = FetchType.LAZY)
    private Sinistre sinistre;

    public Long getId() {
        return this.id;
    }

    public Tache id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayString() {
        return nom;
    }

    public Long getIdTache() {
        return this.id;
    }

    public String getIdProcess() {
        return this.idProcess;
    }

    public void setIdProcess(String idProcess) {
        this.idProcess = idProcess;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCategorie() {
        return this.categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodeTenant() {
        return this.codeTenant;
    }

    public void setCodeTenant(String codeTenant) {
        this.codeTenant = codeTenant;
    }

    public String getCodeDocument() {
        return this.codeDocument;
    }

    public void setCodeDocument(String codeDocument) {
        this.codeDocument = codeDocument;
    }

    public String getIdDocument() {
        return this.idDocument;
    }

    public void setIdDocument(String idDocument) {
        this.idDocument = idDocument;
    }

    public String getIdSousDossier() {
        return this.idSousDossier;
    }

    public void setIdSousDossier(String idSousDossier) {
        this.idSousDossier = idSousDossier;
    }

    public String getNomTiers() {
        return this.nomTiers;
    }

    public void setNomTiers(String nomTiers) {
        this.nomTiers = nomTiers;
    }

    public String getPrenomTiers() {
        return this.prenomTiers;
    }

    public void setPrenomTiers(String prenomTiers) {
        this.prenomTiers = prenomTiers;
    }

    public String getNumeroDocument() {
        return this.numeroDocument;
    }

    public void setNumeroDocument(String numeroDocument) {
        this.numeroDocument = numeroDocument;
    }

    public String getLibelleFamille() {
        return this.libelleFamille;
    }

    public void setLibelleFamille(String libelleFamille) {
        this.libelleFamille = libelleFamille;
    }

    public String getCodeFamille() {
        return this.codeFamille;
    }

    public void setCodeFamille(String codeFamille) {
        this.codeFamille = codeFamille;
    }

    public String getCodeTypeDocument() {
        return this.codeTypeDocument;
    }

    public void setCodeTypeDocument(String codeTypeDocument) {
        this.codeTypeDocument = codeTypeDocument;
    }

    public String getTypeDocumentJugement() {
        return this.typeDocumentJugement;
    }

    public void setTypeDocumentJugement(String typeDocumentJugement) {
        this.typeDocumentJugement = typeDocumentJugement;
    }

    public String getLibelleTypeDocument() {
        return this.libelleTypeDocument;
    }

    public void setLibelleTypeDocument(String libelleTypeDocument) {
        this.libelleTypeDocument = libelleTypeDocument;
    }

    public String getCanal() {
        return this.canal;
    }

    public void setCanal(String canal) {
        this.canal = canal;
    }

    public LocalDate getDateCreation() {
        return this.dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getDateReception() {
        return this.dateReception;
    }

    public void setDateReception(String dateReception) {
        this.dateReception = dateReception;
    }

    public String getDateCachet() {
        return this.dateCachet;
    }

    public void setDateCachet(String dateCachet) {
        this.dateCachet = dateCachet;
    }

    public String getDateNumerisation() {
        return this.dateNumerisation;
    }

    public void setDateNumerisation(String dateNumerisation) {
        this.dateNumerisation = dateNumerisation;
    }

    public String getStatutDocument() {
        return this.statutDocument;
    }

    public void setStatutDocument(String statutDocument) {
        this.statutDocument = statutDocument;
    }

    public String getUtilisateurSource() {
        return this.utilisateurSource;
    }

    public void setUtilisateurSource(String utilisateurSource) {
        this.utilisateurSource = utilisateurSource;
    }

    public String getUtilisateurDestinataire() {
        return this.utilisateurDestinataire;
    }

    public void setUtilisateurDestinataire(String utilisateurDestinataire) {
        this.utilisateurDestinataire = utilisateurDestinataire;
    }

    public String getReferenceDocument() {
        return this.referenceDocument;
    }

    public void setReferenceDocument(String referenceDocument) {
        this.referenceDocument = referenceDocument;
    }

    public String getInstanceGed() {
        return this.instanceGed;
    }

    public void setInstanceGed(String instanceGed) {
        this.instanceGed = instanceGed;
    }

    public String getCatSinistre() {
        return this.catSinistre;
    }

    public void setCatSinistre(String catSinistre) {
        this.catSinistre = catSinistre;
    }

    public String getExeSinistre() {
        return this.exeSinistre;
    }

    public void setExeSinistre(String exeSinistre) {
        this.exeSinistre = exeSinistre;
    }

    public String getNumSinistre() {
        return this.numSinistre;
    }

    public void setNumSinistre(String numSinistre) {
        this.numSinistre = numSinistre;
    }

    public String getIdSinistre() {
        return this.idSinistre;
    }

    public void setIdSinistre(String idSinistre) {
        this.idSinistre = idSinistre;
    }

    public String getIdOuverture() {
        return this.idOuverture;
    }

    public void setIdOuverture(String idOuverture) {
        this.idOuverture = idOuverture;
    }

    public String getIdJugement() {
        return this.idJugement;
    }

    public void setIdJugement(String idJugement) {
        this.idJugement = idJugement;
    }

    public String getNumeroSinistre() {
        return this.numeroSinistre;
    }

    public void setNumeroSinistre(String numeroSinistre) {
        this.numeroSinistre = numeroSinistre;
    }

    public String getStatut() {
        return this.statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getDelaiRestant() {
        return this.delaiRestant;
    }

    public void setDelaiRestant(String delaiRestant) {
        this.delaiRestant = delaiRestant;
    }

    public String getPriorite() {
        return this.priorite;
    }

    public void setPriorite(String priorite) {
        this.priorite = priorite;
    }

    public String getIdObject() {
        return this.idObject;
    }

    public void setIdObject(String idObject) {
        this.idObject = idObject;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Reference getFamilleCible() {
        return this.familleCible;
    }

    public void setFamilleCible(Reference familleCible) {
        this.familleCible = familleCible;
    }

    public String getDestinataire() {
        return this.destinataire;
    }

    public void setDestinataire(String destinataire) {
        this.destinataire = destinataire;
    }

    public String getAction() {
        return this.action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getTiers() {
        return this.tiers;
    }

    public void setTiers(String tiers) {
        this.tiers = tiers;
    }

    public String getCommentaire() {
        return this.commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getNumeroOrdre() {
        return this.numeroOrdre;
    }

    public void setNumeroOrdre(String numeroOrdre) {
        this.numeroOrdre = numeroOrdre;
    }

    public String getChambre() {
        return this.chambre;
    }

    public void setChambre(String chambre) {
        this.chambre = chambre;
    }

    public String getExercice() {
        return this.exercice;
    }

    public void setExercice(String exercice) {
        this.exercice = exercice;
    }

    public String getDateJugement() {
        return this.dateJugement;
    }

    public void setDateJugement(String dateJugement) {
        this.dateJugement = dateJugement;
    }

    public String getCodeMailType() {
        return this.codeMailType;
    }

    public void setCodeMailType(String codeMailType) {
        this.codeMailType = codeMailType;
    }

    public String getIdAffaire() {
        return this.idAffaire;
    }

    public void setIdAffaire(String idAffaire) {
        this.idAffaire = idAffaire;
    }

    public String getNumeroAffaire() {
        return this.numeroAffaire;
    }

    public void setNumeroAffaire(String numeroAffaire) {
        this.numeroAffaire = numeroAffaire;
    }

    public String getIdMission() {
        return this.idMission;
    }

    public void setIdMission(String idMission) {
        this.idMission = idMission;
    }

    public String getNumeroDossierJudiciaire() {
        return this.numeroDossierJudiciaire;
    }

    public void setNumeroDossierJudiciaire(String numeroDossierJudiciaire) {
        this.numeroDossierJudiciaire = numeroDossierJudiciaire;
    }

    public String getEquipe() {
        return this.equipe;
    }

    public void setEquipe(String equipe) {
        this.equipe = equipe;
    }

    public String getIdOffre() {
        return this.idOffre;
    }

    public void setIdOffre(String idOffre) {
        this.idOffre = idOffre;
    }

    public String getListeBeneficiaireReglement() {
        return this.listeBeneficiaireReglement;
    }

    public void setListeBeneficiaireReglement(String listeBeneficiaireReglement) {
        this.listeBeneficiaireReglement = listeBeneficiaireReglement;
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
        if (!(o instanceof Tache)) {
            return false;
        }
        return getId() != null && getId().equals(((Tache) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}