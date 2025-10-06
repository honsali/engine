package dev.cruding.engine.action.recuperer.injection;

import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.injection.ViewActionInjection;

public class ViewRecupererParChampInjection extends ViewActionInjection {



    public boolean addViewScript(ViewFlow f) {
        String lnameChamp = parChamp()[0].lname;
        if (flow()) {
            f.addSelector(lnameAvecEntite());
        } else if (inInit()) {
            f.totalScript().L____("useEffect(() => {");
            f.totalScript().L________(lnameAvecEntite(), "();");
            f.totalScript().L____("}, [", lnameChamp.equals("id") ? "" : lnameChamp, "]);");
            f.useEffect();
            f.addSelector(lnameAvecEntite());
        } else if (parForm()) {
            f.totalScript().L____("const ", lnameAvecEntite(), " = () => {");
            f.totalScript().L________(lnameAvecEntite(), "({ ", lnameChamp, ": form.getFieldValue('", lnameChamp, "') });");
            f.totalScript().L____("};");
            f.useForm();
        } else {
            f.totalScript().L____("const ", lnameAvecEntite(), " = (", lnameChamp, ") => {");
            f.totalScript().L________(lnameAvecEntite(), "({ ", lnameChamp, " });");
            f.totalScript().L____("};");
            f.useEffect();
        }
        return true;
    }

    public void addViewSelector(ViewFlow f) {
        f.addSelector(entite().uname);
    }

}
