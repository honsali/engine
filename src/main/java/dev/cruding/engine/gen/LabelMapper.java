package dev.cruding.engine.gen;

import java.util.HashMap;
import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.entity.Entity;

public class LabelMapper {

    private static LabelMapper instance = new LabelMapper();

    private static final HashMap<String, String> actionVerbInTitleMap = new HashMap<>();

    private static final HashMap<String, String> actionVerbInButtonMap = new HashMap<>();

    private static final HashMap<String, String> actionPpMap = new HashMap<>();
    public static final String noPermissionActionList = "#goTo#appliquer#filtrer#lister#consulter#recupererParId#initCreation#initModification#changerPage#chercher#imprimer#retourListe#retourConsulter#";
    public static final String normalActionList = "#refuser#modifier#ajouter#creer#enregistrer#valider#annuler#rejeter#verrouiller#deverrouiller#accepter#rejeter#confirmer#supprimer#" + noPermissionActionList;

    static {

        actionVerbInTitleMap.put("lister", "Liste");
        actionVerbInTitleMap.put("Lister", "Liste");
        actionVerbInTitleMap.put("creer", "créer");
        actionVerbInTitleMap.put("enregistrer", "enregistrer");

        actionVerbInTitleMap.put("Creer", "créer");
        actionVerbInTitleMap.put("Maj", "enregistrer");

        actionVerbInButtonMap.put("creer", "Enregistrer");
        actionVerbInButtonMap.put("Creer", "Enregistrer");
        actionVerbInButtonMap.put("maj", "Enregistrer");
        actionVerbInButtonMap.put("Maj", "Enregistrer");
        actionVerbInButtonMap.put("ajouterce", "Nouveau");
        actionVerbInButtonMap.put("ajoutercet", "Nouvel");
        actionVerbInButtonMap.put("ajoutercette", "Nouvelle");
        actionVerbInButtonMap.put("RetourListe", "Retour Liste");
        actionVerbInButtonMap.put("RetourConsulter", "Retour");
        actionVerbInButtonMap.put("retourConsulter", "Retour");
        actionVerbInButtonMap.put("initialiserFiltre", "Initialiser");
        actionVerbInButtonMap.put("appliquerFiltre", "Filtrer");

        actionPpMap.put("creer", "créé");
        actionPpMap.put("maj", "enregistré");
        actionPpMap.put("Maj", "enregistré");
        actionPpMap.put("supprimer", "supprimé");
        actionPpMap.put("Supprimer", "supprimé");
    }

    public static LabelMapper getInstance() {
        return instance;
    }

    private String plurals = "#lister#Lister#";
    private String withEntity = "#ajouter#Ajouter#";

    private LabelMapper() {}

    public String nameAction(String key) {
        if (actionVerbInButtonMap.containsKey(key)) {
            return actionVerbInButtonMap.get(key);
        }
        return StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(StringUtils.capitalize(key)), " ");
    }

    public String nameAction(String key, Entity entity) {
        String name = key;
        if (actionVerbInButtonMap.containsKey(key)) {
            name = actionVerbInButtonMap.get(key);
        } else if (actionVerbInButtonMap.containsKey(key + entity.setting.that())) {
            name = actionVerbInButtonMap.get(key + entity.setting.that());
        } else {
            name = StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(StringUtils.capitalize(key)), " ");
        }

        if (withEntity.indexOf(key) > -1) {
            name = name + " " + entity.setting.label;
            if (plurals.indexOf(key) > -1) {
                name = name + "s";
            }
        }
        return name;
    }

    public String titleConfirmation(String key, Entity entity) {
        String verb = key;
        if (actionVerbInTitleMap.containsKey(key)) {
            verb = actionVerbInTitleMap.get(key);
        } else {
            verb = StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(StringUtils.capitalize(key)), " ");
        }
        return StringUtils.capitalize(verb + " " + entity.setting.label);

    }

    public String enteteConfirmation(String key, Entity entity) {
        String verb = key;
        if (actionVerbInTitleMap.containsKey(key)) {
            verb = actionVerbInTitleMap.get(key);
        } else {
            verb = StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(StringUtils.capitalize(key)), " ");
        }
        return "Êtes-vous sûr de vouloir " + verb + " " + entity.setting.that() + " " + entity.setting.label + " ?";
    }

    public String messageSuccess(String key, Entity entity) {
        String lPp = key;
        if (actionPpMap.containsKey(key)) {
            lPp = actionPpMap.get(key) + (entity.setting.feminine ? "e" : "");

        } else {
            lPp = StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(StringUtils.capitalize(key)), " ");
        }
        return entity.setting.label + " " + lPp + " avec succès";
    }

    public String uLabel(String key) {
        String label = key;
        if (actionVerbInTitleMap.containsKey(key)) {
            label = actionVerbInTitleMap.get(key);
        }
        return StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(StringUtils.capitalize(label)), " ");
    }

    public String getTitle(Page page) {
        String title = uLabel(page.actionUname);

        Entity entity = Context.getInstance().getEntity(page.entityUname);
        if (entity != null) {
            title = title + " " + entity.setting.label;

            if (plurals.indexOf(page.actionUname) > -1) {
                title = title + "s";
            }
        }
        return title;
    }
}
