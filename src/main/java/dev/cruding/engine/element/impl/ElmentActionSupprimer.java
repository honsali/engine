package dev.cruding.engine.element.impl;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Page;

public class ElmentActionSupprimer extends Element {

    private static final String utype = "ActionSupprimer";

    public ElmentActionSupprimer(Entity entity, Component component) {
        super(utype, entity);
    }

    public void print(Page page) {
        ViewFlow f = new ViewFlow();

        /* *********************************************************************** */
        f.useEffect();
        f.addSpecificSelector("resultat", page.uc + "Resultat", "../Mdl" + page.uc);
        f.addJsImport("{  useState }", "react");
        f.addJsImport("{ DeleteOutlined }", "@ant-design/icons");
        f.addJsImport("{ useAppDispatch, creerRequete, PopupConfirmationCritique}", "waxant");
        f.addJsImport("Ctrl" + page.uc, "../Ctrl" + page.uc);
        f.flushJsImportBloc();

        f.L("");
        f.L("const ActionSupprimer", entity.uname, " = ({siSuppression}) => {");
        f.L____("const dispatch = useAppDispatch();");
        f.L____("const resultat = useSelector(select", page.uc, "Resultat);");

        f.L____("const [rid, setRid] = useState(null);");
        f.L("");
        f.L____("const attributes = {");
        f.L________("nom: 'supprimer.", entity.lname, "',");
        f.L________("entete: 'message.confirmer.supprimer.", entity.lname, "',");
        f.L________("actionConfirmer: () => executer(),");
        f.L________("icone: <DeleteOutlined />,");
        f.L____("};");
        f.L("");
        f.L____("const executer = () => {");
        f.L________("if (_.estNul(rid)) {");
        f.L____________("execute(Ctrl", page.uc, ".supprimer", entity.uname, " { id", entity.uname, " });");

        f.L________("}");
        f.L____("};");
        f.L("");
        f.L____("useEffect(() => {");
        f.L________("if (rid && rid === resultat.rid) {");
        f.L____________("siSuppression();");
        f.L____________("setRid(null);");
        f.L________("} else if (resultat.rid === 'erreur') {");
        f.L____________("setRid(null);");
        f.L________("}");
        f.L____("}, [resultat, rid]);");
        f.L("");
        f.L____("return <PopupConfirmationCritique {...attributes} rid={rid}></PopupConfirmationCritique>;");
        f.L("};");
        f.L("export default ActionSupprimer", entity.uname, ";");

        /* *********************************************************************** */

        String s = f.toString();
        printFile(s, getBasePath() + "/fe/src/" + page.path + "/element/" + utype + entity.uname + ".tsx");
    }
}
