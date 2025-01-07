package dev.cruding.engine.action;

import java.util.Objects;
import dev.cruding.engine.entite.Entite;
import dev.cruding.engine.flow.CtrlFlow;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.flow.JsFlow;
import dev.cruding.engine.flow.MdlFlow;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Contexte;
import dev.cruding.engine.gen.LabelMapper;
import dev.cruding.engine.gen.Page;

public abstract class Action extends ActionnableWrapper implements Comparable<Action> {

    public void addCtrlImport(CtrlFlow f) {}

    public void addCtrlImplementation(CtrlFlow f) {}

    public void addCtrlDeclaration(CtrlFlow f, Page page) {
        if (uc() != null && !inViewOnly()) {
            f.L____(lname(), ": action<Req", uc(), ", Res", uc(), ">(", lname(), "Impl, Action", page.module.unameLast, ".Uc", uc(), ".", actionKey(), "),");
        }
    }

    public void addMdlImport(MdlFlow flow) {}

    public void addMdlRequestAttribute(MdlFlow flow) {}

    public void addMdlResultAttribute(MdlFlow flow) {}

    public void addMdlStateAttribute(MdlFlow flow) {}

    public void addMdlSelector(MdlFlow flow, String uc) {}

    public boolean addViewScript(ViewFlow flow) {
        return false;
    }

    public void addFlowScript(ViewFlow flow, int level) {}



    public boolean addMdlReducer(MdlFlow flow) {
        return false;
    }

    public void addMdlExtraReducer(MdlFlow flow) {}

    public void addRepositoryDeclaration(JavaFlow flow) {}

    public void addRepositoryImport(JavaFlow flow) {}

    public void addResourceDeclaration(JavaFlow flow) {}

    public void addResourceImport(JavaFlow flow) {}

    public void addServiceImport(JsFlow flow) {}

    public void addServiceDeclaration(Flow flow) {}

    public void addServiceImplementation(Flow flow) {}

    public void addI18n(Flow f, Page page) {
        if (!noUi()) {

            Entite e = entite() == null ? Contexte.getInstance().getEntite(actionnable.page.entiteUname) : entite();
            if (e != null) {

                String nomAction = LabelMapper.getInstance().nomAction(lcoreName(), e);
                String titreConfirmation = LabelMapper.getInstance().titreConfirmation(lcoreName(), e);
                String enteteConfirmation = LabelMapper.getInstance().enteteConfirmation(lcoreName(), e);
                String messageSuccess = LabelMapper.getInstance().messageSuccess(lcoreName(), e);


                String key = "Action" + actionnable.page.module.unameLast + "." + "Uc" + page.uc + "." + actionnable.actionKey;
                f.L____("[", key, "]", ": '", nomAction, "',");

                if (actionnable.confirmer) {
                    f.L____("[titreConfirmation(", key, ")]: '", titreConfirmation, "',");
                    f.L____("[enteteConfirmation(", key, ")]: '", enteteConfirmation, "',");
                    f.L____("[messageSuccess(", key, ")]: '", messageSuccess, "',");
                }
            }
        }
    }



    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        Action other = (Action) obj;

        return lname().equals(other.lname());
    }


    public int hashCode() {
        return Objects.hash(lname());
    }

    @Override
    public int compareTo(Action o) {
        return this.lname().compareTo(o.lname());
    }

}
