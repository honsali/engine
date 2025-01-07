package dev.cruding.engine.action.impl;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.composant.bouton.Actionnable;
import dev.cruding.engine.flow.CtrlFlow;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.flow.MdlFlow;
import dev.cruding.engine.flow.ViewFlow;

public class ActionSpecifique extends Action {



    public void addCtrlImport(CtrlFlow f) {
        f.addCtrlImport("Service" + entite().uname, "modele/" + entite().path + "/Service" + entite().uname);
        if (byForm()) {
            f.addCtrlImport("{ util }", "waxant");
        }
    }

    public void addCtrlImplementation(CtrlFlow f) {
        f.L("");
        f.L("const ", lname(), "Impl = async (requete: Req", uc(), ", resultat: Res", uc(), ", thunkAPI) => {");
        if (byEntite()) {
            f.L____("const { mdl", uc(), " } = thunkAPI.getState() as any;");
            f.L____("const ", entite().lname, " = mdl", uc(), ".", entite().lname, ";");
        }
        if (byForm()) {
            f.L____("await requete.form.validateFields();");
            f.L____("const dataForm = util.removeNonSerialisable(requete.form.getFieldsValue());");
        }

        f.L____("");
        if (resultatInId()) {
            f.__("resultat.id", entite().uname, " = ");
        }
        f.__("await Service", entite().uname, ".", lcoreName(), "(");
        boolean withComma = false;
        if (byGrandPereId() && entite().haveGrandPere) {
            f.__("requete.id" + entite().ugrandPere, ", ");
            withComma = true;
        }
        if (byPereId() && entite().havePere) {
            f.__("requete.id" + entite().upere, ", ");
            withComma = true;
        }
        if (byId()) {
            f.__("requete.id", entite().uname, ", ");
            withComma = true;
        }
        if (byChamp() != null) {
            f.__("requete.", byChamp().lname, ", ");
            withComma = true;
        }
        if (child() != null) {
            f.__("requete.liste" + child().uname, ", ");
            withComma = true;
        }
        if (byForm()) {
            if (byEntite()) {
                f.__("{ ...", entite().lname, ", ...dataForm },");
            } else {
                f.__("dataForm, ");
            }
            withComma = true;
        }
        if (withComma) {
            f.removeLastComma();
        }
        f.__(");");
        if (recharger()) {
            f.L____("resultat.", entite().lname, " = await Service", entite().uname, ".recupererParId(");
            if (byGrandPereId() && entite().haveGrandPere) {
                f.__("requete.id" + entite().ugrandPere, ", ");
            }
            if (byPereId() && entite().havePere) {
                f.__("requete.id" + entite().upere, ", ");
            }
            f.__("requete.id", entite().uname, ");");
        }
        f.L("};");

    }

    public void addMdlImport(MdlFlow f) {
        f.addMdlImport("{ I" + entite().uname + " }", "modele/" + entite().path + "/Domaine" + entite().uname);
        if (child() != null) {
            f.addMdlRequestAttribute("liste" + child().uname, "I" + child().uname + "[]");
            f.addMdlImport("{ I" + child().uname + " }", "modele/" + child().lname + "/Domaine" + child().uname);
            f.addMdlImport("{ PayloadAction }", "@reduxjs/toolkit");
        }
    }

    public void addMdlRequestAttribute(MdlFlow f) {
        if (byForm()) {
            f.addMdlRequestAttribute("form", "any");
        }
        if (child() != null) {
            f.addMdlRequestAttribute("liste" + child().uname, "I" + child().uname + "[]");
        }
        if (byGrandPereId() && entite().haveGrandPere) {
            f.addMdlRequestAttribute("id" + entite().ugrandPere, "string");
        }
        if (byPereId() && entite().havePere) {
            f.addMdlRequestAttribute("id" + entite().upere, "string");
        }

        if (byId()) {
            f.addMdlRequestAttribute("id" + entite().uname, "string");
        }
    }

    public void addMdlResultAttribute(MdlFlow f) {
        f.addMdlResultAttribute(entite().lname, "I" + entite().uname);
        if (resultatInId()) {
            f.addMdlResultAttribute("id" + entite().uname, "string");
        }

    }

    public void addMdlStateAttribute(MdlFlow f) {
        f.addMdlStateAttribute(entite().lname, "I" + entite().uname);
        if (child() != null) {
            f.addMdlStateAttribute("liste" + child().uname, "I" + child().uname + "[]");
        }
    }


    public boolean addMdlReducer(MdlFlow f) {
        if (child() != null) {
            f.L________("setListe", child().uname, ": (state, action: PayloadAction<I", child().uname, "[]>) => {");
            f.L________________("state.liste", child().uname, " = action.payload;");
            f.L________("},");
            return true;
        }
        return false;
    }

    public void addMdlExtraReducer(MdlFlow f) {
        f.L____________(".addCase(Ctrl", uc(), ".", lname(), ".fulfilled, (state, action) => {");
        f.L________________("state.resultat = action.payload;");
        if (recharger()) {
            f.L________________("state.", entite().lname, " = action.payload.", entite().lname, ";");
        }
        f.L____________("})");
    }


    public void addResourceImport(JavaFlow f) {
        f.addJavaImport("org.springframework.http.ResponseEntity");
        f.addJavaImport("jakarta.validation.Valid");
        f.addJavaImport("org.springframework.web.bind.annotation.RequestBody");
        f.addJavaImport("java.net.URISyntaxException");
        f.addJavaImport("org.springframework.web.bind.annotation." + urest() + "Mapping");

    }

    public void addResourceDeclaration(JavaFlow f) {
        f.L("");
        f.L____("@", urest(), "Mapping");
        if ((byGrandPereId() && entite().haveGrandPere) || (byPereId() && entite().havePere) || byId()) {
            f.__("(\"/", lcoreName());
        }
        if (byGrandPereId() && entite().haveGrandPere) {
            f.__("/", entite().lgrandPere, "/{id" + entite().ugrandPere, "}");
        }
        if (byPereId() && entite().havePere) {
            f.__("/", entite().lpere, "/{id" + entite().upere, "}");
        }
        if (byId()) {
            f.__("/{id", entite().uname, "}");
        }
        if ((byGrandPereId() && entite().haveGrandPere) || (byPereId() && entite().havePere) || byId()) {
            f.__("\")");
        }
        f.L____("public ResponseEntity<", entite().uname, "Dto> ", lcoreName(), "(");
        if (byGrandPereId() && entite().haveGrandPere) {
            f.__("@PathVariable Long id" + entite().ugrandPere, ",");
        }
        if (byPereId() && entite().havePere) {
            f.__("@PathVariable Long id" + entite().upere, ",");
        }
        if (byId()) {
            f.__("@PathVariable Long id" + entite().uname, ",");
        }
        if (byForm() || byEntite()) {
            f.__("@Valid @RequestBody ", entite().uname, " ", entite().lname);
        }
        f.cleanComma();
        f.__(") throws URISyntaxException {");
        f.L________(entite().uname, " result = ", entite().lname, "Repository.save(", entite().lname, ");");
        f.L________("return ResponseEntity.ok().body(", entite().uname, "Dto.asEntity(result));");
        f.L____("}");
    }

    public void addServiceImplementation(Flow f) {
        f.L("");
        f.L("const ", lcoreName(), " = async (");
        boolean withComma = false;
        if (byGrandPereId() && entite().haveGrandPere) {
            f.__("id" + entite().ugrandPere, ", ");
            withComma = true;
        }
        if (byPereId() && entite().havePere) {
            f.__("id" + entite().upere, ": string, ");
            withComma = true;
        }
        if (byId()) {
            f.__("id", entite().uname, ": string, ");
            withComma = true;
        }
        if (byChamp() != null) {
            f.__("", byChamp().lname, ", ");
            withComma = true;
        }
        if (child() != null) {
            f.__("liste" + child().uname, ", ");
            withComma = true;
        }
        if (byForm()) {
            f.__(entite().lname, ": I", entite().uname, ", ");
            withComma = true;
        }
        if (withComma) {
            f.removeLastComma();
        }
        f.__(") => {");
        f.L____("return (await axios.", lrest(), "(`${resourceUri}");
        if (byGrandPereId() && entite().haveGrandPere) {
            f.__("/", entite().lgrandPere, "/${id" + entite().ugrandPere, "}");
        }
        if (byPereId() && entite().havePere) {
            f.__("/", entite().lpere, "/${id" + entite().upere, "}");
        }
        if (byId()) {
            f.__("/${id", entite().uname, "}");
        }
        f.__("`");
        if (byForm() || byEntite()) {
            f.__(", ", entite().lname);
        }
        f.__(")).data");
        if (resultatInId()) {
            f.__(".id");
        }
        f.__(";");
        f.L("};");

    }

    public String lrest() {
        return "get";
    }

    public String urest() {
        return "Get";
    }

    public void addServiceDeclaration(Flow f) {
        f.L____(lcoreName(), ",");
    }

    public void addImport(ViewFlow f) {

        f.addJsImport("Ctrl" + actionnable.uc, "../Ctrl" + actionnable.uc);
    }

    public boolean addViewScript(ViewFlow flow) {
        addImport(flow);
        if (actionnable.byEntite) {
            flow.addProp(actionnable.entite.lname);
        }
        if (!this.actionnable.inViewOnly) {
            flow.useExecute();
            flow.totalScript().L____("const " + actionnable.lname + " = () => {");
            flow.totalScript().L________("execute(Ctrl" + actionnable.page.uc + "." + actionnable.lname);
            if (this.actionnable.byForm) {
                flow.totalScript().__(", { form }");
            }
            if (actionnable.byEntite) {
                flow.totalScript().__(", { " + actionnable.entite.lname + " }");
            }
            flow.totalScript().__(");");
            flow.totalScript().L____("};");
        }

        if (actionnable.hasReussi) {
            flow.useExecute("execute, success, rid");
        } else if (this.actionnable.confirmer) {
            flow.useExecute("execute, rid");
        } else {
            flow.useExecute("execute");
        }
        if (this.actionnable.confirmer || actionnable.hasReussi) {
            flow.addSpecificSelector("resultat", actionnable.page.uc + "Resultat", "../Mdl" + actionnable.page.uc);
        }

        if (actionnable.hasReussi) {
            flow.useEffect();
            flow.totalScript().__("\n");
            flow.totalScript().L____("useEffect(() => {");

            flow.totalScript().L________("if (success) {");
            for (Actionnable siReussiAction : actionnable.siReussi) {
                siReussiAction.action.addFlowScript(flow, 2);
            }
            flow.totalScript().L________("}");
            flow.totalScript().L____("}, [success]);");
        }

        return false;
    }

}
