package dev.cruding.engine.champ;

import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.champ.impl.Ref;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entite.Entite;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.flow.JsFlow;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Contexte;

public class Champ {

    public String larg;
    public String uarg;
    public String lname;
    public String uname;
    public String dbName;
    public String jtype;
    public String stype;
    public String jstype;
    public boolean requis;

    public boolean isRef;
    public boolean isRefMany;
    public boolean isChild;
    public boolean isBasic;
    public boolean isPere;
    public boolean isId;

    public boolean cloned = false;

    public String libelle;
    public int largeur;
    public boolean lectureSeule;
    public String lectureSeuleSi;
    public String invisibleSi;
    public String videSi;
    public String siChange;
    public Action siChangeAction;
    public boolean avecVariable;
    public boolean seulDansLaLigne;
    public boolean surTouteLaLigne;
    public boolean tranzient;
    public boolean filtre;
    public String reference;
    public String oui;
    public String non;
    public String init;
    public boolean initFromMdl;

    public Ref<?> of;

    public String containingEntite;
    public String containingEntiteDbname;

    public Champ(Champ f) {
        copyChampProps(f, this);
    }

    public Champ(boolean isBasic) {
        this.isBasic = isBasic;
    }

    public Champ lname(String lname) {
        this.lname = lname;
        this.uname = StringUtils.capitalize(lname);
        this.dbName = StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(lname), "_").toLowerCase();
        return this;
    }

    public Champ containingEntite(Entite entite) {
        this.containingEntite = entite.uname;
        this.containingEntiteDbname = entite.dbName;
        this.dbName = Contexte.getInstance().getLegacyDbName(entite.uname, lname, "column", this.dbName);
        return this;
    }

    public Champ stype(String stype) {
        this.stype = stype;
        return this;
    }

    public Champ jtype(String jtype) {
        this.jtype = jtype;
        return this;
    }

    public Champ jstype(String jstype) {
        this.jstype = jstype;
        return this;
    }

    public Champ largeur(int largeur) {
        Champ p = makeCopy();
        p.largeur = largeur;
        return p;
    }

    public Champ libelle(String libelle) {
        Champ p = makeCopy();
        p.libelle = libelle;
        return p;
    }

    public Champ requis() {
        Champ p = makeCopy();
        p.requis = true;
        return p;
    }

    public Champ init(String init) {
        Champ p = makeCopy();
        p.init = init;
        return p;
    }

    public Champ initFromMdl() {
        Champ p = makeCopy();
        p.initFromMdl = true;
        return p;
    }

    public Champ reference(String reference) {
        Champ p = makeCopy();
        p.reference = reference;
        return p;
    }

    public Champ isId() {
        Champ p = makeCopy();
        p.isId = true;
        // p.requis = true;
        return p;
    }

    public Champ lectureSeule() {
        Champ p = makeCopy();
        p.lectureSeule = true;
        return p;
    }

    public Champ lectureSeuleSi(String lectureSeuleSi) {
        Champ p = makeCopy();
        p.lectureSeuleSi = lectureSeuleSi;
        return p;
    }

    public Champ invisibleSi(String invisibleSi) {
        Champ p = makeCopy();
        p.invisibleSi = invisibleSi;
        return p;
    }

    public Champ videSi(String videSi) {
        Champ p = makeCopy();
        p.videSi = videSi;
        return p;
    }

    public Champ siChange() {
        Champ p = makeCopy();
        p.siChange = "";
        return p;
    }

    public Champ siChange(String siChange) {
        Champ p = makeCopy();
        p.siChange = siChange;
        return p;
    }

    public Champ siChange(Action action) {
        Champ p = makeCopy();
        p.siChange = "";
        p.siChangeAction = action;
        return p;
    }

    public Champ avecVariable() {
        Champ p = makeCopy();
        p.avecVariable = true;
        return p;
    }

    public Champ seulDansLaLigne() {
        Champ p = makeCopy();
        p.seulDansLaLigne = true;
        return p;
    }

    public Champ surTouteLaLigne() {
        Champ p = makeCopy();
        p.surTouteLaLigne = true;
        return p;
    }

    public Champ tranzient() {
        Champ p = makeCopy();
        p.tranzient = true;
        return p;
    }

    public Champ filtre() {
        Champ p = makeCopy();
        p.filtre = true;
        return p;
    }

    public Champ oui(String oui) {
        Champ p = makeCopy();
        p.oui = oui;
        return p;
    }

    public Champ non(String non) {
        Champ p = makeCopy();
        p.non = non;
        return p;
    }

    public Champ arg(String arg) {
        Champ p = makeCopy();
        p.larg = arg;
        p.uarg = StringUtils.capitalize(larg);
        return p;
    }

    public String ui(String element) {
        switch (element) {
            case Element.FORM:
                return "ChampTexte";
            case Element.DETAIL:
                return "Texte";
            case Element.TABLEAU:
                return "Colonne";
            default:
                return "";
        }

    }

    public String getExtension() {
        return "";
    }

    public StringBuilder getInit() {
        return null;
    }

    public void addJsImport(JsFlow f) {

    }

    public void addJsImport(JsFlow f, Entite entite) {
        addJsImport(f);
    }

    public void addJsDeclaration(JsFlow f) {
        f.addJsDeclaration(lname, jstype);
    }

    public void addFiltreImport(JavaFlow f) {
        addJavaImport(f);
    }

    public void addJavaImport(JavaFlow f) {
        if (requis) {
            f.addJavaImport("jakarta.validation.constraints.NotNull");
        }
        if (tranzient) {
            f.addJavaImport("jakarta.persistence.Transient");
        }
    }

    public void addDtoDeclaration(JavaFlow f) {
        f.L________(jtype + " " + lname + ", //");
    }

    public void addDtoImport(JavaFlow flow) {

    }

    public void addViewScript(ViewFlow f, String uc, String mvcPath) {}

    public void addFiltreJavaDeclaration(JavaFlow f) {
        f.L____("private " + jtype + " " + lname + ";");
    }

    public void addJavaDeclaration(JavaFlow f) {
        if (filtre) {
            return;
        }
        f.L("");
        if (requis) {
            f.L____("@NotNull");
        }
        if (tranzient) {
            f.L____("@Transient");
        } else {
            f.L____("@Column(name = \"" + dbName + "\"");
            if (requis) {
                f.__(", nullable = false");
            }
            f.__(")");
        }
        f.L____("private " + jtype + " " + lname + ";");
    }

    public void addFiltreGetterSetter(JavaFlow f) {
        addGetterSetter(f);
    }

    public void addSpecification(JavaFlow f) {
        f.L("");
        f.L____________("if (condition.get" + uname + "() != null && !condition.get" + uname + "().trim().isEmpty()) {");
        f.L________________("predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(\"" + lname + "\")), \"%\" + condition.get" + uname + "().toLowerCase() + \"%\"));");
        f.L____________("}");
    }

    public void addGetterSetter(JavaFlow f) {
        if (filtre) {
            return;
        }
        f.L("");
        f.L____("public " + jtype + " get" + uname + "() {");
        f.L________("return this." + lname + ";");
        f.L____("}");
        f.L("");
        f.L____("public void set" + uname + "(" + jtype + " " + lname + ") {");
        f.L________("this." + lname + " = " + lname + ";");
        f.L____("}");
    }

    public void addLiqDeclaration(Flow f) {
        f.L____________("<column name=\"" + dbName + "\" type=\"" + stype + "\">");
        f.L________________("<constraints nullable=\"" + !requis + "\" />");
        f.L____________("</column>");
    }

    public String getReferenceNameList(String entiteName) {
        Entite entite = Contexte.getInstance().getEntite(entiteName);
        if (entite != null && entite.listeChamp.size() > 0) {
            return entite.listeChamp.stream().filter(p -> p.isRef || p.isRefMany).map(p -> p.lname).collect(Collectors.joining("\", \"", "\"", "\""));
        }
        return null;
    }

    public String getReferenceName(String entiteName, String c) {
        Entite entite = Contexte.getInstance().getEntite(entiteName);
        if (entite != null && entite.listeChamp.size() > 0) {
            Optional<String> o = entite.listeChamp.stream().filter(p -> p.isRef || p.isRefMany).filter(p -> p.jtype.equals(c)).map(p -> p.lname).findAny();
            if (o.isPresent()) {
                return o.get();
            }
        }
        return null;
    }

    protected Champ initCopy() {
        return new Champ(true);
    }

    protected Champ makeCopy() {
        Champ p = initCopy();
        return copyChampProps(this, p);
    }

    protected <T extends Champ> T copyChampProps(Champ from, T to) {

        to.larg = from.larg;
        to.uarg = from.uarg;

        to.lname = from.lname;
        to.uname = from.uname;
        to.dbName = from.dbName;
        to.jtype = from.jtype;
        to.stype = from.stype;
        to.jstype = from.jstype;

        to.requis = from.requis;
        to.isRef = from.isRef;
        to.isRefMany = from.isRefMany;
        to.isChild = from.isChild;
        to.isBasic = from.isBasic;
        to.isPere = from.isPere;
        to.isId = from.isId;

        to.libelle = from.libelle;
        to.largeur = from.largeur;
        to.lectureSeule = from.lectureSeule;
        to.lectureSeuleSi = from.lectureSeuleSi;
        to.invisibleSi = from.invisibleSi;
        to.videSi = from.videSi;
        to.siChange = from.siChange;
        to.siChangeAction = from.siChangeAction;
        to.avecVariable = from.avecVariable;
        to.seulDansLaLigne = from.seulDansLaLigne;
        to.surTouteLaLigne = from.surTouteLaLigne;

        to.tranzient = from.tranzient;
        to.filtre = from.filtre;
        to.reference = from.reference;
        to.oui = from.oui;
        to.non = from.non;
        to.init = from.init;
        to.initFromMdl = from.initFromMdl;
        to.of = from.of;

        to.containingEntite = from.containingEntite;
        to.containingEntiteDbname = from.containingEntiteDbname;

        to.cloned = true;

        return to;
    }

}
