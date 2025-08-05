package dev.cruding.engine.composant;

import java.util.Arrays;
import java.util.Objects;
import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entite.Entite;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.ViewFlow;

public abstract class Composant {
    public static final String tab = "    ";
    public static final String[] indent = new String[] {//
            "\n" + tab, //
            "\n" + tab + tab, "\n" + tab + tab + tab, //
            "\n" + tab + tab + tab + tab, //
            "\n" + tab + tab + tab + tab + tab, //
            "\n" + tab + tab + tab + tab + tab + tab, //
            "\n" + tab + tab + tab + tab + tab + tab + tab, //
            "\n" + tab + tab + tab + tab + tab + tab + tab + tab, //
            "\n" + tab + tab + tab + tab + tab + tab + tab + tab + tab//
    };

    public Composant[] listeComposant;
    public Champ[] listeChamp;
    public Entite entite;
    public Element element;
    public Composant pereComposant;
    public boolean inElement = false;
    public boolean isElement = false;
    public boolean inline = false;
    public String nom;

    public Composant(Element element) {
        this.element = element;
    }

    public Composant(Element element, Entite entite) {
        this.element = element;
        this.entite = entite;
    }

    public Composant(Element element, Entite entite, Composant... listeComposant) {
        this(element, entite);
        this.listeComposant = listeComposant;
    }

    public Composant(Element element, Entite entite, Champ... listeChamp) {
        this(element, entite);
        this.listeChamp = clean(listeChamp);
    }

    public Composant(Element element, Composant... listeComposant) {
        this(element);
        this.listeComposant = listeComposant;
    }

    public Composant nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void addLabel(String key, String value) {
        // Contexte.getInstance().addLabel(element.module.uname, key, value);
    }

    public void appendContent(ViewFlow vf, Flow flow) {}

    public void addContent(Composant pereComposant, ViewFlow flow, int level) {
        addContent(pereComposant, flow, false, level);
    }

    public void addContent(Composant pereComposant, ViewFlow flow, boolean inline, int level) {
        inElement = inElement || (pereComposant != null && pereComposant.inElement);
        this.pereComposant = pereComposant;
        this.inline = inline;
        addImport(flow);
        addScript(flow);
        if (level == 1) {
            flow.totalUi().__("(");
        }
        boolean childInline = addOpenTag(flow, level);
        if (!isElement) {
            if (listeComposant != null) {
                for (Composant composant : listeComposant) {
                    composant.addContent(this, flow, childInline, level + 1);
                }
            }
        }
        addCloseTag(flow, level);
        if (level == 1) {
            indent(flow, 0).append(");");
        }
    }

    public Flow indent(ViewFlow flow, int level) {
        if (inline) {
            return flow.totalUi().__("");
        }
        return flow.totalUi().__(indent[level]);
    }

    public void addImport(ViewFlow flow) {}

    public boolean addOpenTag(ViewFlow c, int level) {
        return false;
    }

    public void addCloseTag(ViewFlow c, int level) {}


    public void addScript(ViewFlow c) {}

    private Champ[] clean(Champ[] listeChamp) {
        return Arrays.stream(listeChamp).filter(Objects::nonNull).toArray(Champ[]::new);
    }

}
