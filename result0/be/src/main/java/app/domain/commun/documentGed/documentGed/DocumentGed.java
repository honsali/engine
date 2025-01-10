package app.domain.commun.documentGed.documentGed;

import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "document_ged")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DocumentGed implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_document_ged")
    @SequenceGenerator(name = "seq_document_ged", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "id_document")
    private Integer idDocument;

    @NotNull
    @Column(name = "code_document", nullable = false)
    private String codeDocument;

    @Column(name = "reference_document")
    private String referenceDocument;

    @Column(name = "code_famille")
    private String codeFamille;

    @Column(name = "mime_type")
    private String mimeType;

    @Column(name = "numero_page")
    private Integer numeroPage;

    @Column(name = "instance_ged")
    private String instanceGed;

    @Column(name = "nombre_total_page")
    private Integer nombreTotalPage;

    @Column(name = "page_url")
    private String pageURL;

    public Long getId() {
        return this.id;
    }

    public DocumentGed id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayString() {
        return codeDocument;
    }

    public Long getIdDocumentGed() {
        return this.id;
    }

    public Integer getIdDocument() {
        return this.idDocument;
    }

    public void setIdDocument(Integer idDocument) {
        this.idDocument = idDocument;
    }

    public String getCodeDocument() {
        return this.codeDocument;
    }

    public void setCodeDocument(String codeDocument) {
        this.codeDocument = codeDocument;
    }

    public String getReferenceDocument() {
        return this.referenceDocument;
    }

    public void setReferenceDocument(String referenceDocument) {
        this.referenceDocument = referenceDocument;
    }

    public String getCodeFamille() {
        return this.codeFamille;
    }

    public void setCodeFamille(String codeFamille) {
        this.codeFamille = codeFamille;
    }

    public String getMimeType() {
        return this.mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public Integer getNumeroPage() {
        return this.numeroPage;
    }

    public void setNumeroPage(Integer numeroPage) {
        this.numeroPage = numeroPage;
    }

    public String getInstanceGed() {
        return this.instanceGed;
    }

    public void setInstanceGed(String instanceGed) {
        this.instanceGed = instanceGed;
    }

    public Integer getNombreTotalPage() {
        return this.nombreTotalPage;
    }

    public void setNombreTotalPage(Integer nombreTotalPage) {
        this.nombreTotalPage = nombreTotalPage;
    }

    public String getPageURL() {
        return this.pageURL;
    }

    public void setPageURL(String pageURL) {
        this.pageURL = pageURL;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DocumentGed)) {
            return false;
        }
        return getId() != null && getId().equals(((DocumentGed) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}