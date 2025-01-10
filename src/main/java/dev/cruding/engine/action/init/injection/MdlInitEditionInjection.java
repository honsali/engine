package dev.cruding.engine.action.init.injection;

import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.champ.impl.ChampRef;
import dev.cruding.engine.flow.MdlFlow;
import dev.cruding.engine.injection.MdlActionInjection;

public class MdlInitEditionInjection extends MdlActionInjection {

    protected Champ[] fieldList = new Champ[0];

    public MdlInitEditionInjection(Champ[] fieldList) {
        this.fieldList = fieldList;
    };

    public void addMdlImport(MdlFlow f) {
        for (Champ c : fieldList) {
            if (c instanceof ChampRef) {
                ((ChampRef) c).addMdlImport(f);
            }
        }
    }

    public void addMdlRequestAttribute(MdlFlow f) {}

    public void addMdlResultAttribute(MdlFlow f) {
        for (Champ c : fieldList) {
            if (c instanceof ChampRef) {
                ((ChampRef) c).addMdlResultAttribute(f);
            }
        }
    }

    public void addMdlStateAttribute(MdlFlow f) {
        for (Champ c : fieldList) {
            if (c instanceof ChampRef) {
                ((ChampRef) c).addMdlStateAttribute(f);
            }
        }
    }

    public void addMdlSelector(MdlFlow f, String uc) {
        for (Champ c : fieldList) {
            if (c instanceof ChampRef) {
                ((ChampRef) c).addMdlSelector(f, uc);
            }
        }
    }


    public void addMdlExtraReducer(MdlFlow f) {
        f.L____________(".addCase(Ctrl", uc(), ".", lname(), ".fulfilled, (state, action) => {");
        f.L________________("state.resultat = action.payload;");
        f.L________________("state.", entite().lname, " = action.payload.", entite().lname, ";");

        for (Champ c : fieldList) {
            if (c instanceof ChampRef) {
                ((ChampRef) c).addMdlExtraReducer(f);
            }
        }

        f.L____________("})");
    }
}
