package dev.cruding.engine.action.impl;

import java.util.List;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.flow.MCFlow;
import dev.cruding.engine.flow.ViewFlow;

public class ActionSupprimer extends Action {

    public ActionSupprimer() {
        super("supprimer");
        withService = true;
    }

    public void addCtrlImport(MCFlow f) {
        f.addCtrlImport("Service" + entity.uname, "modele/" + entity.modulePath + "/Service" + entity.uname);
    }

    public void addMdlImport(MCFlow f) {

    }

    public void addMdlRequestAttribute(MCFlow f) {
        f.addMdlRequestAttribute("id" + entity.uname, "string");
    }

    public void addMdlResultAttribute(MCFlow f) {
        f.addMdlResultAttribute(entity.lname, "I" + entity.uname);

    }

    public void addMdlStateAttribute(MCFlow f) {
        f.addMdlStateAttribute(entity.lname, "I" + entity.uname);
    }

    public void addCtrlImplementation(MCFlow f) {

        f.L("");
        f.L("const ", lname, "Impl = async (requete: Req", uc, ", resultat: Res", uc, ", thunkAPI) => {");
        f.L____("await Service", entity.uname, ".supprimer(requete.id" + entity.uname);
        if (byFatherId && entity.haveFather) {
            f.__(", requete.id" + entity.ufather);
        }
        f.__(");");
        f.L("};");

    }

    public void addCtrlDeclaration(MCFlow f) {
        f.L____("", lname, ": action<Req", uc, ", Res", uc, ">(", lname, "Impl, 'Ctrl", uc, "/", lname, "'),");
    }

    public boolean addMdlReducer(MCFlow flow) {
        return false;
    }

    public void addMdlExtraReducer(MCFlow f) {
        f.L____________(".addCase(Ctrl", uc, ".", lname, ".fulfilled, (state, action) => {");
        f.L____________("    state.resultat = action.payload;");
        f.L____________("})");
    }

    public void addViewScript(ViewFlow f) {}

    public void addResourceImport(JavaFlow f) {
        f.addJavaImport("org.springframework.http.ResponseEntity");
        f.addJavaImport("org.springframework.web.bind.annotation.DeleteMapping");
        f.addJavaImport("org.springframework.web.bind.annotation.PathVariable");

    }

    public void addResourceDeclaration(JavaFlow f) {

        List<Field> childList = entity.fieldList.stream().filter(fld -> fld.isChild).toList();
        f.L("");
        f.L____("@DeleteMapping(\"/{id}\")");
        f.L____("public ResponseEntity<Void> supprimer", entity.uname, "(@PathVariable Long id) {");
        for (Field fld : childList) {
            f.L________("", fld.lname, "Repository.deleteBy", entity.uname, "Id(id);");
        }
        f.L________("", entity.lname, "Repository.deleteById(id);");
        f.L________("return ResponseEntity.noContent().build();");
        f.L____("}");
    }

    public void addServicImplementation(Flow f) {
        f.L("");
        f.L("const supprimer = async (id", entity.uname, ": string) => {");
        f.L____("await axios.delete(`${resourceUri}/${id", entity.uname, "}`);");
        f.L("};");
    }

    public void addServiceDeclaration(Flow f) {
        f.L____("supprimer,");
    }
}
