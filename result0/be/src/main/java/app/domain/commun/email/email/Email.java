package app.domain.commun.email.email;

import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import app.domain.commun.documentGed.documentGed.DocumentGed;
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
@Table(name = "email")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Email implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_email")
    @SequenceGenerator(name = "seq_email", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "adresse_mail", nullable = false)
    private String adresseMail;

    @Column(name = "cc")
    private String cc;

    @Column(name = "cci")
    private String cci;

    @Column(name = "expediteur_code")
    private String expediteurCode;

    @Column(name = "objet")
    private String objet;

    @Column(name = "message")
    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    private DocumentGed documentGed;

    @Column(name = "cible")
    private String cible;

    public Long getId() {
        return this.id;
    }

    public Email id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayString() {
        return adresseMail;
    }

    public Long getIdEmail() {
        return this.id;
    }

    public String getAdresseMail() {
        return this.adresseMail;
    }

    public void setAdresseMail(String adresseMail) {
        this.adresseMail = adresseMail;
    }

    public String getCc() {
        return this.cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getCci() {
        return this.cci;
    }

    public void setCci(String cci) {
        this.cci = cci;
    }

    public String getExpediteurCode() {
        return this.expediteurCode;
    }

    public void setExpediteurCode(String expediteurCode) {
        this.expediteurCode = expediteurCode;
    }

    public String getObjet() {
        return this.objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DocumentGed getDocumentGed() {
        return this.documentGed;
    }

    public void setDocumentGed(DocumentGed documentGed) {
        this.documentGed = documentGed;
    }

    public String getCible() {
        return this.cible;
    }

    public void setCible(String cible) {
        this.cible = cible;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Email)) {
            return false;
        }
        return getId() != null && getId().equals(((Email) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}