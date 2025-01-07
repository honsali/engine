package dev.cruding.engine.gen;

import java.util.HashMap;
import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.entite.Entite;

public class LabelMapper {

    private static LabelMapper instance = new LabelMapper();

    private static final HashMap<String, String> verbeActionDansTitreMap = new HashMap<>();

    private static final HashMap<String, String> verbeActionDansBoutonMap = new HashMap<>();

    private static final HashMap<String, String> actionPpMap = new HashMap<>();
    public static final String listeActionSansPermission = "#goTo#appliquer#filtrer#lister#consulter#recupererParId#initCreation#initModification#changerPage#chercher#imprimer#retourListe#retourConsulter#";
    public static final String listeActionStandard = "#refuser#modifier#ajouter#creer#enregistrer#valider#annuler#rejeter#verrouiller#deverrouiller#accepter#rejeter#confirmer#" + listeActionSansPermission;


    static {

        verbeActionDansTitreMap.put("lister", "Liste");
        verbeActionDansTitreMap.put("Lister", "Liste");
        verbeActionDansTitreMap.put("creer", "créer");
        verbeActionDansTitreMap.put("enregistrer", "enregistrer");

        verbeActionDansTitreMap.put("Creer", "créer");
        verbeActionDansTitreMap.put("Enregistrer", "enregistrer");

        verbeActionDansBoutonMap.put("creer", "Enregistrer");
        verbeActionDansBoutonMap.put("Creer", "Enregistrer");
        verbeActionDansBoutonMap.put("enregistrer", "Enregistrer");
        verbeActionDansBoutonMap.put("Enregistrer", "Enregistrer");
        verbeActionDansBoutonMap.put("ajouterce", "Nouveau");
        verbeActionDansBoutonMap.put("ajoutercet", "Nouvel");
        verbeActionDansBoutonMap.put("ajoutercette", "Nouvelle");
        verbeActionDansBoutonMap.put("RetourListe", "Retour Liste");
        verbeActionDansBoutonMap.put("RetourConsulter", "Retour");
        verbeActionDansBoutonMap.put("retourConsulter", "Retour");
        verbeActionDansBoutonMap.put("initialiserFiltre", "Initialiser");
        verbeActionDansBoutonMap.put("appliquerFiltre", "Filtrer");


        actionPpMap.put("creer", "créé");
        actionPpMap.put("enregistrer", "enregistré");
        actionPpMap.put("Enregistrer", "enregistré");
    }

    public static LabelMapper getInstance() {
        return instance;
    }

    private String pluriels = "#lister#Lister#";
    private String avecEntite = "#ajouter#Ajouter#";


    private LabelMapper() {}


    public String nomAction(String key) {
        if (verbeActionDansBoutonMap.containsKey(key)) {
            return verbeActionDansBoutonMap.get(key);
        }
        return StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(StringUtils.capitalize(key)), " ");
    }

    public String nomAction(String key, Entite entite) {
        String nom = key;
        if (verbeActionDansBoutonMap.containsKey(key)) {
            nom = verbeActionDansBoutonMap.get(key);
        } else if (verbeActionDansBoutonMap.containsKey(key + entite.setting.ce())) {
            nom = verbeActionDansBoutonMap.get(key + entite.setting.ce());
        } else {
            nom = StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(StringUtils.capitalize(key)), " ");
        }

        if (avecEntite.indexOf(key) > -1) {
            nom = nom + " " + entite.setting.libelle;
            if (pluriels.indexOf(key) > -1) {
                nom = nom + "s";
            }
        }
        return nom;
    }



    public String titreConfirmation(String key, Entite entite) {
        String verbe = key;
        if (verbeActionDansTitreMap.containsKey(key)) {
            verbe = verbeActionDansTitreMap.get(key);
        } else {
            verbe = StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(StringUtils.capitalize(key)), " ");
        }
        return StringUtils.capitalize(verbe + " " + entite.setting.libelle);

    }

    public String enteteConfirmation(String key, Entite entite) {
        String verbe = key;
        if (verbeActionDansTitreMap.containsKey(key)) {
            verbe = verbeActionDansTitreMap.get(key);
        } else {
            verbe = StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(StringUtils.capitalize(key)), " ");
        }
        return "Etes vous sur de vouloir " + verbe + " " + entite.setting.ce() + " " + entite.setting.libelle;
    }

    public String messageSuccess(String key, Entite entite) {
        String lPp = key;
        if (actionPpMap.containsKey(key)) {
            lPp = actionPpMap.get(key) + (entite.setting.feminin ? "e" : "");

        } else {
            lPp = StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(StringUtils.capitalize(key)), " ");
        }
        return entite.setting.libelle + " " + lPp + " avec succès";
    }

    public String uLabel(String key) {
        String label = key;
        if (verbeActionDansTitreMap.containsKey(key)) {
            label = verbeActionDansTitreMap.get(key);
        }
        return StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(StringUtils.capitalize(label)), " ");
    }


    public String getTitre(Page page) {
        String titre = uLabel(page.actionUname);

        Entite entite = Contexte.getInstance().getEntite(page.entiteUname);
        if (entite != null) {
            titre = titre + " " + entite.setting.libelle;

            if (pluriels.indexOf(page.actionUname) > -1) {
                titre = titre + "s";
            }
        }
        return titre;
    }
}
