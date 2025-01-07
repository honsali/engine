package dev.cruding.engine.entite;

import dev.cruding.engine.champ.Champ;

public abstract class ReferenceData extends Entite {

    public final Champ nom = Texte("libelle").required().isId();

    public boolean isReferenceData() {
        return true;
    }
}
