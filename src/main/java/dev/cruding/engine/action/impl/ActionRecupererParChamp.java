package dev.cruding.engine.action.impl;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.component.bouton.Actionnable;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.flow.JsFlow;
import dev.cruding.engine.flow.MCFlow;
import dev.cruding.engine.flow.ViewFlow;

public class ActionRecupererParChamp extends Action {

    public Champ field;

    public ActionRecupererParChamp(Champ field) {
        this.field = field;
    }

    public void actionnable(Actionnable actionnable) {
        this.actionnable = actionnable;
        this.actionnable.lcoreName("recupererPar" + field.uname);
        this.actionnable.lname("recuperer" + entite().uname + "Par" + field.uname);
    };

    public void addCtrlImport(MCFlow f) {
        f.addCtrlImport("Service" + entite().uname, "modele/" + entite().path + "/Service" + entite().uname);
    }

    public void addMdlImport(MCFlow f) {
        f.addMdlImport("{ I" + entite().uname + " }", "modele/" + entite().path + "/Domaine" + entite().uname);
    }

    public void addMdlRequestAttribute(MCFlow f) {
        f.addMdlRequestAttribute("id" + entite().uname, "string");
        if (byGrandFatherId() && entite().haveGrandFather) {
            f.addMdlRequestAttribute("id" + entite().ugrandfather, "string");
        }
        if (byFatherId() && entite().haveFather) {
            f.addMdlRequestAttribute("id" + entite().ufather, "string");
        }
    }

    public void addMdlResultAttribute(MCFlow f) {
        f.addMdlResultAttribute(entite().lname, "I" + entite().uname);
    }

    public void addMdlStateAttribute(MCFlow f) {
        f.addMdlStateAttribute(entite().lname, "I" + entite().uname);
    }

    public void addMdlSelector(MCFlow f, String uc) {
        f.L("export const select", entite().uname, " = createSelector([selectMdl", uc(), "], (state: ", uc(), "Type) => state.", entite().lname, ");");
    }

    public void addCtrlImplementation(MCFlow f) {
        f.L("");
        f.L("const ", lname(), "Impl = async (requete: Req", uc(), ", resultat: Res", uc(), ", thunkAPI) => {");
        f.L____("resultat.", entite().lname, " = await Service", entite().uname, ".recupererPar", field.uname, "(");
        if (byGrandFatherId() && entite().haveGrandFather) {
            f.__("requete.id" + entite().ugrandfather, ", ");
        }
        if (byFatherId() && entite().haveFather) {
            f.__("requete.id" + entite().ufather, ", ");
        }
        f.__("requete.", field.lname, entite().uname, ");");
        f.L("};");
    }



    public void addMdlExtraReducer(MCFlow f) {
        f.L____________(".addCase(Ctrl", uc(), ".", lname(), ".fulfilled, (state, action) => {");
        f.L________________("state.resultat = action.payload;");
        f.L________________("state.", entite().lname, " = action.payload.", entite().lname, ";");
        f.L____________("})");
    }

    public boolean addViewScript(ViewFlow f) {
        f.totalScript().L____("useEffect(() => {");
        f.totalScript().L________(f.getPretCondition(), "execute(Ctrl", uc(), ".", lname(), ");");
        f.totalScript().L____("}, [", f.getPretArray(), "]);");
        f.useExecute();
        f.useEffect();
        f.addJsImport("Ctrl" + uc(), mvcPath() + "/Ctrl" + uc());
        return true;
    }

    public void addViewSelector(ViewFlow f) {
        f.addSpecificSelector(entite().uname, mvcPath() + "/Mdl" + uc());
    }

    public void addResourceImport(JavaFlow f) {
        f.addJavaImport("java.util.Optional");
        f.addJavaImport("org.springframework.http.HttpStatus");
        f.addJavaImport("org.springframework.http.ResponseEntity");
        f.addJavaImport("org.springframework.web.bind.annotation.GetMapping");
        f.addJavaImport("org.springframework.web.bind.annotation.PathVariable");
        f.addJavaImport("org.springframework.web.server.ResponseStatusException");

    }

    public void addResourceDeclaration(JavaFlow f) {
        f.L("");
        f.L____("@GetMapping(\"/{" + field.lname + "}\")");
        f.L____("public ResponseEntity<", entite().uname, "> ", lcoreName(), "(@PathVariable ", entite().id_.jtype, " id) {");
        f.L________("Optional<", entite().uname, "> ", entite().lname, " = ", entite().lname, "Repository.findById(id);");
        f.L________("return ", entite().lname, ".map(response -> ResponseEntity.ok().body(response)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));");
        f.L____("}");
    }

    public void addServiceImport(JsFlow f) {
        f.addJsImport("{ I" + entite().uname + " }", "./Domaine" + entite().uname);
    }

    public void addServiceImplementation(Flow f) {
        f.L("");
        f.L("const recupererPar", field.uname, " = async (", field.lname, entite().uname, ": string) => {");
        f.L____("const ", entite().lname, ": I", entite().uname, " = (await axios.get<I", entite().uname, ">(`${resourceUri}/${", field.lname, entite().uname, "}`)).data;");
        f.L____("return ", entite().lname, ";");
        f.L("};");

    }

    public void addServiceDeclaration(Flow f) {
        f.L____("recupererPar" + field.uname + ",");
    }
}
