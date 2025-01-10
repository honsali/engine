package app.domain.commun.notification.notification;

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
@Table(name = "notification")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Notification implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_notification")
    @SequenceGenerator(name = "seq_notification", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "utilisateur")
    private String utilisateur;

    @Column(name = "active")
    private Boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    private Reference typeNotification;

    @Column(name = "date_creation")
    private String dateCreation;

    @Column(name = "createur")
    private String createur;

    public Long getId() {
        return this.id;
    }

    public Notification id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayString() {
        return message;
    }

    public Long getIdNotification() {
        return this.id;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUtilisateur() {
        return this.utilisateur;
    }

    public void setUtilisateur(String utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Reference getTypeNotification() {
        return this.typeNotification;
    }

    public void setTypeNotification(Reference typeNotification) {
        this.typeNotification = typeNotification;
    }

    public String getDateCreation() {
        return this.dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getCreateur() {
        return this.createur;
    }

    public void setCreateur(String createur) {
        this.createur = createur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Notification)) {
            return false;
        }
        return getId() != null && getId().equals(((Notification) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}