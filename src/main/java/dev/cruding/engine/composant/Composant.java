package dev.cruding.engine.composant;

import java.util.Arrays;
import java.util.Objects;
import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entite.Entite;
import dev.cruding.engine.flow.ViewFlow;

public abstract class Composant {
    public static final String tab = "    ";
    public static final String[] indent = new String[] {"\n" + tab, "\n" + tab + tab, "\n" + tab + tab + tab, "\n" + tab + tab + tab + tab, "\n" + tab + tab + tab + tab + tab, "\n" + tab + tab + tab + tab + tab + tab, "\n" + tab + tab + tab + tab + tab + tab + tab};

    public Composant[] ComposantList;
    public Champ[] listeChamp;
    public Entite entite;
    public Element element;
    public Composant pereComposant;
    public boolean inElement = false;
    public boolean isElement = false;
    public boolean inline = false;

    public Composant(Element element) {
        this.element = element;
    }

    public Composant(Element element, Entite entite) {
        this.element = element;
        this.entite = entite;
    }

    public Composant(Element element, Entite entite, Composant... ComposantList) {
        this(element, entite);
        this.ComposantList = ComposantList;
    }

    public Composant(Element element, Entite entite, Champ... listeChamp) {
        this(element, entite);
        this.listeChamp = clean(listeChamp);
    }

    public Composant(Element element, Composant... ComposantList) {
        this(element);
        this.ComposantList = ComposantList;
    }

    public Composant(Element element, Champ... listeChamp) {
        this(element);
        this.listeChamp = clean(listeChamp);
    }

    public void addLabel(String key, String value) {
        // Contexte.getInstance().addLabel(element.module.uname, key, value);
    }

    public void addInlineContent(ViewFlow flow, boolean flush) {
        addImport(flow);
        addScript(flow);
        addInlineTag(flow);
        flow.addToUi(";");
        if (flush) {
            flow.flush(this);
        }
    }

    public void addContent(Composant pereComposant, ViewFlow flow, boolean inline, int level) {
        addContent(pereComposant, flow, inline, level, (level == 1));
    }

    public void addContent(Composant pereComposant, ViewFlow flow, int level) {
        if (inline) {
            addInlineContent(flow, (level == 1));
        } else {
            addContent(pereComposant, flow, false, level, (level == 1));
        }
    }

    public void addContent(Composant pereComposant, ViewFlow flow, boolean inline, int level, boolean flush) {
        inElement = inElement || (pereComposant != null && pereComposant.inElement);
        this.pereComposant = pereComposant;
        this.inline = inline;
        addImport(flow);
        addScript(flow);
        if (level == 1) {
            flow.addToUi("(");
        }
        boolean childInline = addOpenTag(flow, level);
        if (!isElement) {
            if (ComposantList != null) {
                for (Composant composant : ComposantList) {
                    composant.addContent(this, flow, childInline, level + 1);
                }
            }
        }
        addCloseTag(flow, level);
        if (level == 1) {
            indent(flow, 0).append(");");
        }
        if (flush) {
            flow.flush(this);
        }
    }



    public StringBuilder indent(ViewFlow flow, int level) {
        if (inline) {
            return flow.addToUi("");
        }
        return flow.addToUi(indent[level]);
    }

    public void addImport(ViewFlow flow) {}

    public boolean addOpenTag(ViewFlow c, int level) {
        return false;
    }

    public void addCloseTag(ViewFlow c, int level) {}

    public void addInlineTag(ViewFlow c) {}

    public void addScript(ViewFlow c) {}

    private Champ[] clean(Champ[] listeChamp) {
        return Arrays.stream(listeChamp).filter(Objects::nonNull).toArray(Champ[]::new);
    }


}
