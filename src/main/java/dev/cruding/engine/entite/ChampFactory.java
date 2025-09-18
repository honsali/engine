package dev.cruding.engine.entite;

import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.champ.impl.Annee;
import dev.cruding.engine.champ.impl.Booleen;
import dev.cruding.engine.champ.impl.Date;
import dev.cruding.engine.champ.impl.Double;
import dev.cruding.engine.champ.impl.Email;
import dev.cruding.engine.champ.impl.File;
import dev.cruding.engine.champ.impl.Heure;
import dev.cruding.engine.champ.impl.Int;
import dev.cruding.engine.champ.impl.ListeStatique;
import dev.cruding.engine.champ.impl.Pere;
import dev.cruding.engine.champ.impl.Ref;
import dev.cruding.engine.champ.impl.RefMany;
import dev.cruding.engine.champ.impl.Setting;
import dev.cruding.engine.champ.impl.Tel;
import dev.cruding.engine.champ.impl.TextArray;
import dev.cruding.engine.champ.impl.Texte;
import dev.cruding.engine.champ.impl.TexteArabe;
import dev.cruding.engine.champ.impl.TexteLong;

public class ChampFactory {

    public Setting Setting() {
        return new Setting();
    }

    public Champ Texte(String name) {
        return new Texte(name);
    }

    public Champ TexteArabe(String name) {
        return new TexteArabe(name);
    }

    public Champ Annee(String name) {
        return new Annee(name);
    }

    public Champ Double(String name) {
        return new Double(name);
    }

    public Champ Int(String name) {
        return new Int(name);
    }

    public Champ Date(String name) {
        return new Date(name);
    }

    public Champ Tel(String name) {
        return new Tel(name);
    }

    public Champ Email(String name) {
        return new Email(name);
    }

    public Champ Heure(String name) {
        return new Heure(name);
    }

    public Champ File(String name) {
        return new File(name);
    }

    public ListeStatique ListeStatique(String name) {
        return new ListeStatique(name);
    }

    public ListeStatique ListeStatique(String name, String type) {
        return new ListeStatique(name, type);
    }


    public <T extends Entite> Pere<T> Pere(Class<T> type) {
        return new Pere<T>(type);
    }

    public <T extends Entite> Ref<T> Ref(Class<T> type) {
        return new Ref<T>(type);
    }


    public <T extends Entite> Ref<T> Ref(Class<T> type, String lname) {
        return new Ref<T>(type, lname);
    }

    public <T extends Entite> RefMany<T> RefMany(Class<T> type) {
        return new RefMany<T>(type);
    }

    public <T extends Entite> RefMany<T> RefMany(Class<T> type, String lname) {
        return new RefMany<T>(type, lname);
    }

    public Champ TextArray(String name) {
        return new TextArray(name);
    }

    public Champ Booleen(String name) {
        return new Booleen(name);
    }

    public Champ TexteLong(String name) {
        return new TexteLong(name);
    }

}
