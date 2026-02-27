package dev.cruding.engine.action.init.injection;

import dev.cruding.engine.field.Field;
import dev.cruding.engine.field.impl.RefField;
import dev.cruding.engine.flow.MdlFlow;
import dev.cruding.engine.injection.ActionMdlInjection;

public class MdlInitCreateInjection extends ActionMdlInjection {

    protected Field[] fieldList = new Field[0];

    public MdlInitCreateInjection(Field[] fieldList) {
        this.fieldList = fieldList;
    };

    public void addMdlImport(MdlFlow f) {
        f.addMdlImport("{ I" + entity().uname + " }", "modele/" + entity().path + "/Domaine" + entity().uname);
        for (Field c : fieldList) {
            if (c instanceof RefField) {
                ((RefField<?>) c).addMdlImport(f);
            }
        }
    }

    public void addMdlRequestAttribute(MdlFlow f) {}

    public void addMdlResultAttribute(MdlFlow f) {
        f.addMdlResultAttribute(entity().lname, "I" + entity().uname);
        for (Field c : fieldList) {
            if (c instanceof RefField) {
                ((RefField<?>) c).addMdlResultAttribute(f);
            }
        }
    }

    public void addMdlStateAttribute(MdlFlow f) {
        f.addMdlStateAttribute(entity().lname, "I" + entity().uname);
        for (Field c : fieldList) {
            if (c instanceof RefField) {
                ((RefField<?>) c).addMdlStateAttribute(f);
            }
        }
        f.addMdlSelectorAttribute(entity().lname, entity().uname);
    }



    public void addUseSelector(MdlFlow f) {
        for (Field c : fieldList) {
            if (c instanceof RefField) {
                ((RefField<?>) c).addUseSelector(f);
            }
        }
        f.L________(entity().lname, ",");
    }

    public void addMdlExtraReducerAffectation(MdlFlow f) {
        f.L________________("state.", entity().lname, " = action.payload.", entity().lname, ";");

        for (Field c : fieldList) {
            if (c instanceof RefField) {
                ((RefField<?>) c).addMdlExtraReducer(f);
            }
        }

    }
}
