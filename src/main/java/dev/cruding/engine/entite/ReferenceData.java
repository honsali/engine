package dev.cruding.engine.entite;

import dev.cruding.engine.champ.Champ;

public abstract class ReferenceData extends Entite {

    public final Champ nom = Texte("libelle").requis().isId();
    public final Champ code = Texte("code").requis();
    public final Champ reference = Texte("reference");
    public final Champ arg = Texte("arg");

    public boolean isReferenceData() {
        return true;
    }
}
