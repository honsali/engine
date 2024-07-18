package dev.cruding.engine.action.impl;

import dev.cruding.engine.action.ActionAvecTable;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.flow.MCFlow;
import dev.cruding.engine.flow.ViewFlow;

public class ActionListerEnPage extends ActionAvecTable {

    public ActionListerEnPage() {
        super("listerEnPage");
        withService = true;
    }

    public ActionListerEnPage entity(Entity entity) {
        this.actionType = "listerEnPage";
        this.lname = "lister" + entity.uname + "EnPage";
        this.entity = entity;
        this.sourceDonnee = "liste" + entity.uname;
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
        f.L____("resultat.listePaginee", entity.uname, " = await Service", entity.uname, ".", actionType, "({pageCourante: 0 });");
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
        f.L________("return ", entity.lname, "Repository.findAll(pageable);");
        f.L____("}");
    }

    public void addServicImplementation(Flow f) {
        f.L("");
        f.L("const ", actionType, " = async (pageCourante: number) => {");
        f.L____("const listePaginee", entity.uname, ": IListePaginee", entity.uname, " = {} as IListePaginee", entity.uname, ";");
        f.L____("const requetePage = MapperPagination.creerRequetePage(pageCourante);");
        f.L____("const page", entity.uname, ": IPage<I", entity.uname, "> = (await axios.get<IPage<I", entity.uname, ">>(`${resourceUri}/", actionType, "?page=${requetePage.page}&size=${requetePage.size}`)).data;");
        f.L____("listePaginee", entity.uname, ".liste = page", entity.uname, ".content;");
        f.L____("listePaginee", entity.uname, ".pagination = MapperPagination.creerPagination(page", entity.uname, ");");
        f.L____("return listePaginee", entity.uname, ";");
        f.L("};");

    }

    public void addServiceDeclaration(Flow f) {
        f.L____(actionType, ",");
    }
}