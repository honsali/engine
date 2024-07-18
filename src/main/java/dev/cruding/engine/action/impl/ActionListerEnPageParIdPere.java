package dev.cruding.engine.action.impl;

import dev.cruding.engine.action.ActionAvecTable;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.flow.MCFlow;
import dev.cruding.engine.flow.ViewFlow;

public class ActionListerEnPageParIdPere extends ActionAvecTable {

    public ActionListerEnPageParIdPere() {
        super("listerEnPageParIdPere");
        byFatherId = true;
        byGrandFatherId = true;
        withService = true;
    }

    public ActionListerEnPageParIdPere entity(Entity entity) {
        this.actionType = "listerEnPageParId" + entity.ufather;
        this.lname = "lister" + entity.uname + "EnPageParId" + entity.ufather;
        this.entity = entity;
        this.sourceDonnee = "listePaginee" + entity.uname;
        return this;
    }



    public void addCtrlImport(MCFlow f) {
        f.addCtrlImport("Service" + entity.uname, "modele/" + entity.modulePath + "/Service" + entity.uname);
    }

    public void addMdlImport(MCFlow f) {
        f.addMdlImport("{ IListePaginee" + entity.uname + " }", "modele/" + entity.modulePath + "/Domaine" + entity.uname);
    }

    public void addMdlRequestAttribute(MCFlow f) {
        f.addMdlRequestAttribute("pageCourante", "number");
        if (byGrandFatherId && entity.haveGrandFather) {
            f.addMdlRequestAttribute("id" + entity.ugrandfather, "string");
        }
        f.addMdlRequestAttribute("id" + entity.ufather, "string");
    }

    public void addMdlResultAttribute(MCFlow f) {
        f.addMdlResultAttribute("listePaginee" + entity.uname, "IListePaginee" + entity.uname);

    }

    public void addMdlStateAttribute(MCFlow f) {
        f.addMdlStateAttribute("listePaginee" + entity.uname, "IListePaginee" + entity.uname);
    }

    public void addMdlSelector(MCFlow f, String uc) {
        f.L("export const selectListePaginee", entity.uname, " = createSelector([selectMdl", uc, "], (state: ", uc, "Type) => state.listePaginee", entity.uname, ");");
    }

    public void addCtrlImplementation(MCFlow f) {
        f.L("");
        f.L("const ", lname, "Impl = async (requete: Req", uc, ", resultat: Res", uc, ", thunkAPI) => {");
        f.L____("resultat.listePaginee", entity.uname, " = await Service", entity.uname, ".", actionType, "(");
        if (byGrandFatherId && entity.haveGrandFather) {
            f.__("requete.id" + entity.ugrandfather, ", ");
        }
        f.__("requete.id", entity.ufather);
        f.__(", 0);");
        f.L("};");
    }

    public void addCtrlDeclaration(MCFlow f) {
        f.L____(lname, ": action<Req", uc, ", Res", uc, ">(", lname, "Impl, 'Ctrl", uc, "/", lname, "'),");
    }

    public boolean addMdlReducer(MCFlow f) {
        return false;
    }

    public void addMdlExtraReducer(MCFlow f) {
        f.L____________(".addCase(Ctrl", uc, ".", lname, ".fulfilled, (state, action) => {");
        f.L____________("    state.resultat = action.payload;");
        f.L____________("    state.listePaginee", entity.uname, " = action.payload.listePaginee", entity.uname, ";");
        f.L____________("})");
    }

    public void addViewScript(ViewFlow f) {
        f.totalScript().L("");
        f.totalScript().L____("useEffect(() => {");
        f.totalScript().L____("    execute(Ctrl", uc, ".", lname, ");");
        f.totalScript().L____("}, []);");
        f.useExecute();
        f.useEffect();
        f.addJsImport("Ctrl" + uc, mvcPath + "/Ctrl" + uc);
    }

    public void addRepositoryImport(JavaFlow f) {
        f.addJavaImport("org.springframework.data.domain.Page");
        f.addJavaImport("org.springframework.data.domain.Pageable");
    }

    public void addRepositoryDeclaration(JavaFlow f) {
        f.L____________("Page<", entity.uname, "> findAll(Pageable pageable);");
    }

    public void addResourceImport(JavaFlow f) {
        f.addJavaImport("org.springdoc.api.annotations.ParameterObject");
        f.addJavaImport("org.springframework.data.domain.Page");
        f.addJavaImport("org.springframework.data.domain.Pageable");
    }

    public void addResourceDeclaration(JavaFlow f) {
        f.L("");
        f.L____("@GetMapping(\"/", actionType, "\")");
        f.L____("public Page<", entity.uname, "> ", actionType, "(@ParameterObject Pageable pageable) {");
        f.L________("return ", entity.lname, "Repository.findBy", entity.ufather, "IdOrderBy", entity.uid, "(id", entity.ufather, ",pageable);");
        f.L____("}");
    }

    public void addServicImplementation(Flow f) {
        f.L("");
        f.L("const ", actionType, " = async (id", entity.ufather, ": string, pageCourante: number) => {");
        f.L____("const listePaginee", entity.uname, ": IListePaginee", entity.uname, " = {} as IListePaginee", entity.uname, ";");
        f.L____("const requetePage = MapperPagination.creerRequetePage(pageCourante);");
        f.L____("const page", entity.uname, ": IPage<I", entity.uname, "> = (await axios.get<IPage<I", entity.uname, ">>(`${resourceUri}/", actionType, "/${id", entity.ufather, "}?page=${requetePage.page}&size=${requetePage.size}`)).data;");
        f.L____("listePaginee", entity.uname, ".liste = page", entity.uname, ".content;");
        f.L____("listePaginee", entity.uname, ".pagination = MapperPagination.creerPagination(page", entity.uname, ");");
        f.L____("return listePaginee", entity.uname, ";");
        f.L("};");

    }

    public void addServiceDeclaration(Flow f) {
        f.L____(actionType, ",");
    }
}
