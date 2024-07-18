package dev.cruding.engine.action.impl;

import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.flow.MCFlow;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.LabelMapper;
import dev.cruding.engine.gen.Page;

public class ActionCreer extends Action {

    public ActionCreer(String actionType) {
        super(actionType);
        withService = true;
    }

    public void addCtrlImport(MCFlow f) {
        f.addCtrlImport("Service" + entity.uname, "modele/" + entity.modulePath + "/Service" + entity.uname);
        if (byForm) {
            f.addCtrlImport("{ util }", "waxant");
        }
    }

    public void addMdlImport(MCFlow f) {
        f.addMdlImport("{ I" + entity.uname + " }", "modele/" + entity.modulePath + "/Domaine" + entity.uname);
        if (child != null) {
            f.addMdlRequestAttribute("liste" + child.uname, "I" + child.uname + "[]");
            f.addMdlImport("{ I" + child.uname + " }", "modele/" + child.lname + "/Domaine" + child.uname);
            f.addMdlImport("{ PayloadAction }", "@reduxjs/toolkit");
        }
    }

    public void addMdlRequestAttribute(MCFlow f) {
        f.addMdlRequestAttribute("form", "any");
        if (child != null) {
            f.addMdlRequestAttribute("liste" + child.uname, "I" + child.uname + "[]");
        }

        if (byGrandFatherId && entity.haveGrandFather) {
            f.addMdlRequestAttribute("id" + entity.ugrandfather, "string");
        }
        if (byFatherId && entity.haveFather) {
            f.addMdlRequestAttribute("id" + entity.ufather, "string");
        }
    }

    public void addMdlResultAttribute(MCFlow f) {
        f.addMdlResultAttribute(entity.lname, "I" + entity.uname);
        f.addMdlResultAttribute("id" + entity.uname, "string");

    }

    public void addMdlStateAttribute(MCFlow f) {
        f.addMdlStateAttribute(entity.lname, "I" + entity.uname);
        if (child != null) {
            f.addMdlStateAttribute("liste" + child.uname, "I" + child.uname + "[]");
        }
    }

    public void addCtrlImplementation(MCFlow f) {
        f.L("");
        f.L("const ", lname, "Impl = async (requete: Req", uc, ", resultat: Res", uc, ", thunkAPI) => {");
        f.L____("await requete.form.validateFields();");
        f.L____("const dataForm = util.removeNonSerialisable(requete.form.getFieldsValue());");
        f.L____("resultat.id", entity.uname, " = ");
        f.L____("await Service", entity.uname, ".", actionType, "(");
        boolean withComma = false;
        if (byGrandFatherId && entity.haveGrandFather) {
            f.__("requete.id" + entity.ugrandfather, ", ");
            withComma = true;
        }
        if (byFatherId && entity.haveFather) {
            f.__("requete.id" + entity.ufather, ", ");
        }
        if (child != null) {
            f.__("requete.liste" + child.uname, ", ");
            withComma = true;
        }
        if (byForm) {
            f.__("dataForm, ");
            withComma = true;
        }
        if (withComma) {
            f.removeLastComma();
        }
        f.__(");");
        f.L("};");

    }

    public void addCtrlDeclaration(MCFlow f) {
        f.L____("", lname, ": action<Req", uc, ", Res", uc, ">(", lname, "Impl, 'Ctrl", uc, "/", lname, "'),");
    }

    public boolean addMdlReducer(MCFlow f) {
        if (child != null) {
            f.L________("setListe", child.uname, ": (state, action: PayloadAction<I", child.uname, "[]>) => {");
            f.L____________("state.liste", child.uname, " = action.payload;");
            f.L________("},");
            return true;
        }
        return false;
    }

    public void addMdlExtraReducer(MCFlow f) {
        f.L____________(".addCase(Ctrl", uc, ".", lname, ".fulfilled, (state, action) => {");
        f.L____________("    state.resultat = action.payload;");
        if (recharger) {
            f.L________________("state.", entity.lname, " = action.payload.", entity.lname, ";");
        }
        f.L____________("})");
    }

    public void addViewScript(ViewFlow f) {}

    public void addResourceImport(JavaFlow f) {
        f.addJavaImport("org.springframework.http.ResponseEntity");
        f.addJavaImport("jakarta.validation.Valid");
        f.addJavaImport("org.springframework.web.bind.annotation.RequestBody");
        f.addJavaImport("java.net.URISyntaxException");
        f.addJavaImport("org.springframework.web.bind.annotation.PostMapping");

    }

    public void addResourceDeclaration(JavaFlow f) {
        f.L("");
        f.L____("@PostMapping");
        f.L____("public ResponseEntity<", entity.uname, "> ", lname, entity.uname, "(@Valid @RequestBody ", entity.uname, " ", entity.lname, ") throws URISyntaxException {");
        f.L________("", entity.uname, " result = ", entity.lname, "Repository.save(", entity.lname, ");");
        f.L________("return ResponseEntity.ok().body(result);");
        f.L____("}");
    }

    public void addServicImplementation(Flow f) {
        f.L("");
        f.L("const ", actionType, " = async (", entity.lname, ": I", entity.uname, ") => {");
        f.L____("return (await axios.post(`${resourceUri}`, ", entity.lname, ")).data;");
        f.L("};");

    }

    public void addServiceDeclaration(Flow f) {
        f.L____(lname, ",");
    }

    public void addI18n(Flow f, Page page) {
        String entityLabel = entity.setting.libelle;
        String lAction = LabelMapper.getInstance().lAction(actionType);
        String uAction = LabelMapper.getInstance().uAction(actionType);

        f.L____("[Action", page.moduleDefinition.unameLast, ".", "Uc", page.uc, ".", StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(lname), "_").toUpperCase(), "]", ": '", uAction, " ", entityLabel, "',");

        if (confirmer) {
            f.L____("'Uc", page.uc, ".action.", actionType, ".confirmation.titre': '", uAction, " ", entityLabel, "',");
            f.L____("'Uc", page.uc, ".action.", actionType, ".confirmation.entete': 'Etes vous sur de vouloir ", lAction, " ", entity.setting.ce(), " ", entityLabel, "',");
        }
    }

    public void addActionModule(Flow f, Page page) {
        f.L________(StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(lname), "_").toUpperCase(), ": 'Uc", page.uc, ".action." + StringUtils.uncapitalize(lname), "',");
    }
}
