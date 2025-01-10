package app.domain.sinistre.identification.identificationDocument;

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
@Table(name = "identification_document")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class IdentificationDocument implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_identification_document")
    @SequenceGenerator(name = "seq_identification_document", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "reference_document", nullable = false)
    private String referenceDocument;

    @Column(name = "numero_document")
    private String numeroDocument;

    @ManyToOne(fetch = FetchType.LAZY)
    private Reference familleDocument;

    @ManyToOne(fetch = FetchType.LAZY)
    private Reference typeDocument;

    @Column(name = "date_cachet")
    private String dateCachet;

    @Column(name = "date_reception")
    private String dateReception;

    @ManyToOne(fetch = FetchType.LAZY)
    private Reference typeExpediteur;

    @ManyToOne(fetch = FetchType.LAZY)
    private Expediteur prestataire;

    public Long getId() {
        return this.id;
    }

    public IdentificationDocument id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayString() {
        return referenceDocument;
    }

    public Long getIdIdentificationDocument() {
        return this.id;
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

    public String getDateCachet() {
        return this.dateCachet;
    }

    public void setDateCachet(String dateCachet) {
        this.dateCachet = dateCachet;
    }

    public String getDateReception() {
        return this.dateReception;
    }

    public void setDateReception(String dateReception) {
        this.dateReception = dateReception;
    }

    public Reference getTypeExpediteur() {
        return this.typeExpediteur;
    }

    public void setTypeExpediteur(Reference typeExpediteur) {
        this.typeExpediteur = typeExpediteur;
    }

    public Expediteur getPrestataire() {
        return this.prestataire;
    }

    public void setPrestataire(Expediteur prestataire) {
        this.prestataire = prestataire;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof IdentificationDocument)) {
            return false;
        }
        return getId() != null && getId().equals(((IdentificationDocument) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}