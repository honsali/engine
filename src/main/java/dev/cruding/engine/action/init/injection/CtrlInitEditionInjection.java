package dev.cruding.engine.action.init.injection;

import java.util.Arrays;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.champ.impl.ChampRef;
import dev.cruding.engine.flow.CtrlFlow;
import dev.cruding.engine.injection.CtrlActionInjection;

public class CtrlInitEditionInjection extends CtrlActionInjection {

    private Champ[] fieldList = new Champ[0];

    public CtrlInitEditionInjection(Champ[] fieldList) {
        this.fieldList = fieldList;
    };

    public void addCtrlImport(CtrlFlow f) {
        for (Champ c : fieldList) {
            if (c instanceof ChampRef) {
                ((ChampRef) c).addCtrlImport(f);
            }
        }
    }

    public void addCtrlImplementation(CtrlFlow f) {
        f.L("");
        f.L("const ", lname(), "Impl = async (requete: Req", uc(), ", resultat: Res", uc(), ", thunkAPI) => {");
        f.L____("resultat.", entite().lname, " = {");
        String result = Arrays.asList(fieldList).stream().filter(field -> field.init != null).map(field -> " " + field.lname + ": " + field.init).collect(Collectors.joining(","));
        f.__(result, StringUtils.isNotBlank(result) ? " " : "");
        f.__("};");
        for (Champ c : fieldList) {
            if (c instanceof ChampRef) {
                ((ChampRef) c).addCtrlImplementation(f);
            }
        }
        f.L("};");
    }
}