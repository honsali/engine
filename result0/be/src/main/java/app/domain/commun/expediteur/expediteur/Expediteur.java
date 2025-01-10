package app.domain.commun.expediteur.expediteur;

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
@Table(name = "expediteur")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Expediteur implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_expediteur")
    @SequenceGenerator(name = "seq_expediteur", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "description")
    private String description;

    @Column(name = "actif")
    private String actif;

    @ManyToOne(fetch = FetchType.LAZY)
    private Reference typeExpediteur;

    @ManyToOne(fetch = FetchType.LAZY)
    private Reference ville;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "email")
    private String email;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "region")
    private String region;

    public Long getId() {
        return this.id;
    }

    public Expediteur id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayString() {
        return code;
    }

    public Long getIdExpediteur() {
        return this.id;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return this.libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getActif() {
        return this.actif;
    }

    public void setActif(String actif) {
        this.actif = actif;
    }

    public Reference getTypeExpediteur() {
        return this.typeExpediteur;
    }

    public void setTypeExpediteur(Reference typeExpediteur) {
        this.typeExpediteur = typeExpediteur;
    }

    public Reference getVille() {
        return this.ville;
    }

    public void setVille(Reference ville) {
        this.ville = ville;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return this.adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getRegion() {
        return this.region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Expediteur)) {
            return false;
        }
        return getId() != null && getId().equals(((Expediteur) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}