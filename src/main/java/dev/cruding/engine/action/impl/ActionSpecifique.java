package dev.cruding.engine.action.impl;

import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.flow.MCFlow;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.LabelMapper;
import dev.cruding.engine.gen.Page;

public class ActionSpecifique extends Action {

    public ActionSpecifique(String actionType) {
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
        if (byForm) {
            f.addMdlRequestAttribute("form", "any");
        }
        if (child != null) {
            f.addMdlRequestAttribute("liste" + child.uname, "I" + child.uname + "[]");
        }
        if (byGrandFatherId && entity.haveGrandFather) {
            f.addMdlRequestAttribute("id" + entity.ugrandfather, "string");
        }
        if (byFatherId && entity.haveFather) {
            f.addMdlRequestAttribute("id" + entity.ufather, "string");
        }

        if (byId) {
            f.addMdlRequestAttribute("id" + entity.uname, "string");
        }
    }

    public void addMdlResultAttribute(MCFlow f) {
        f.addMdlResultAttribute(entity.lname, "I" + entity.uname);
        if (resultatInId) {
            f.addMdlResultAttribute("id" + entity.uname, "string");
        }

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
        if (byEntity) {
            f.L____("const { mdl", uc, " } = thunkAPI.getState() as any;");
            f.L____("const ", entity.lname, " = mdl", uc, ".", entity.lname, ";");
        }
        if (byForm) {
            f.L____("await requete.form.validateFields();");
            f.L____("const dataForm = util.removeNonSerialisable(requete.form.getFieldsValue());");
        }

        f.L____("");
        if (resultatInId) {
            f.__("resultat.id", entity.uname, " = ");
        }
        f.__("await Service", entity.uname, ".", actionType, "(");
        boolean withComma = false;
        if (byGrandFatherId && entity.haveGrandFather) {
            f.__("requete.id" + entity.ugrandfather, ", ");
            withComma = true;
        }
        if (byFatherId && entity.haveFather) {
            f.__("requete.id" + entity.ufather, ", ");
            withComma = true;
        }
        if (byId) {
            f.__("requete.id", entity.uname, ", ");
            withComma = true;
        }
        if (byField != null) {
            f.__("requete.", byField.lname, ", ");
            withComma = true;
        }
        if (child != null) {
            f.__("requete.liste" + child.uname, ", ");
            withComma = true;
        }
        if (byForm) {
            if (byEntity) {
                f.__("{ ...", entity.lname, ", ...dataForm },");
            } else {
                f.__("dataForm, ");
            }
            withComma = true;
        }
        if (withComma) {
            f.removeLastComma();
        }
        f.__(");");
        if (recharger) {
            f.L____("resultat.", entity.lname, " = await Service", entity.uname, ".recupererParId(");
            if (byGrandFatherId && entity.haveGrandFather) {
                f.__("requete.id" + entity.ugrandfather, ", ");
            }
            if (byFatherId && entity.haveFather) {
                f.__("requete.id" + entity.ufather, ", ");
            }
            f.__("requete.id", entity.uname, ");");
        }
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
        f.addJavaImport("org.springframework.web.bind.annotation." + urest + "Mapping");

    }

    public void addResourceDeclaration(JavaFlow f) {
        f.L("");
        f.L____("@", urest, "Mapping");
        if ((byGrandFatherId && entity.haveGrandFather) || (byFatherId && entity.haveFather) || byId) {
            f.__("(\"");
        }
        if (byGrandFatherId && entity.haveGrandFather) {
            f.__("/", entity.lgrandfather, "/{id" + entity.ugrandfather, "}");
        }
        if (byFatherId && entity.haveFather) {
            f.__("/", entity.lfather, "/{id" + entity.ufather, "}");
        }
        if (byId) {
            f.__("/${id", entity.uname, "}");
        }
        if ((byGrandFatherId && entity.haveGrandFather) || (byFatherId && entity.haveFather) || byId) {
            f.__("\")");
        }
        f.L____("public ResponseEntity<", entity.uname, "> ", lname, "(");
        if (byGrandFatherId && entity.haveGrandFather) {
            f.__("@PathVariable Long id" + entity.ugrandfather, ",");
        }
        if (byFatherId && entity.haveFather) {
            f.__("@PathVariable Long id" + entity.ufather, ", ");
        }
        if (byId) {
            f.__("@PathVariable Long id" + entity.ugrandfather, ",");
        }
        if (byForm || byEntity) {
            f.__("@Valid @RequestBody ", entity.uname, " ", entity.lname);
        }
        f.__(") throws URISyntaxException {");
        f.L________("", entity.uname, " result = ", entity.lname, "Repository.save(", entity.lname, ");");
        f.L________("return ResponseEntity.ok().body(result);");
        f.L____("}");
    }

    public void addServicImplementation(Flow f) {

        f.L("");
        f.L("const ", actionType, " = async (");
        boolean withComma = false;
        if (byGrandFatherId && entity.haveGrandFather) {
            f.__("id" + entity.ugrandfather, ", ");
            withComma = true;
        }
        if (byFatherId && entity.haveFather) {
            f.__("id" + entity.ufather, ": string, ");
            withComma = true;
        }
        if (byId) {
            f.__("id", entity.uname, ": string, ");
            withComma = true;
        }
        if (byField != null) {
            f.__("", byField.lname, ", ");
            withComma = true;
        }
        if (child != null) {
            f.__("liste" + child.uname, ", ");
            withComma = true;
        }
        if (byForm) {
            f.__(entity.lname, ": I", entity.uname, ", ");
            withComma = true;
        }
        if (withComma) {
            f.removeLastComma();
        }
        f.__(") => {");
        f.L____("return (await axios.", lrest, "(`${resourceUri}");
        if (byGrandFatherId && entity.haveGrandFather) {
            f.__("/", entity.lgrandfather, "/${id" + entity.ugrandfather, "}");
        }
        if (byFatherId && entity.haveFather) {
            f.__("/", entity.lfather, "/${id" + entity.ufather, "}");
        }
        if (byId) {
            f.__("/${id", entity.uname, "}");
        }
        f.__("`");
        if (byForm || byEntity) {
            f.__(", ", entity.lname);
        }
        f.__(")).data");
        if (resultatInId) {
            f.__(".id");
        }
        f.__(";");
        f.L("};");

    }

    public void addServiceDeclaration(Flow f) {
        f.L____(actionType, ",");
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
        f.L________(StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(lname), "_").toUpperCase(), ": 'Uc", page.uc, ".action." + StringUtils.uncapitalize(actionType), "',");
    }
}
