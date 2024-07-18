package dev.cruding.engine.element.impl;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.component.entite.Child;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Page;

public class ChildElement extends Element {

    private static final String utype = "Detail";
    private Child detailLocal;

    public ChildElement(Entity entity, Component component) {
        super(utype, entity);
        this.detailLocal = (Child) component;
    }

    public void print(Page page) {
        ViewFlow f = new ViewFlow();

        /* *********************************************************************** */

        f.__("import { PlusCircleFilled } from '@ant-design/icons';");
        f.L("import { Form } from 'antd';");
        f.L("import BlocActionDroit from 'composants/bouton/BlocActionDroit';");
        f.L("import BoutonPleinSecondaire from 'composants/bouton/boutonBase/BoutonPleinSecondaire';");
        f.L("import { ChampCache, ChampDate, ChampFichier, ChampListe, ChampReference, ChampReferenceAvecFiltre, ChampVide, Formulaire } from 'waxant';");
        f.L("import { ColonneCustom, ColonneTexte, Tableau } from 'composants/tableau';");
        f.L("import ColonneActionModifier from 'composants/tableau/colonnes/colonne.actionModifier';");
        f.L("import ColonneActionSupprimer from 'composants/tableau/colonnes/colonne.actionSupprimer';");
        f.L("import Separateur20 from 'composants/widget/Separateur20';");
        f.L("import _ from 'core/config/lodash.config';");
        f.L("import { useAppDispatch, useAppSelector } from 'core/config/store.config';");
        f.L("import useValider from 'core/util/useValider';");
        f.L("import { useEffect, useState } from 'react';");
        f.L("import { Mdl", page.uc, " } from '../Mdl", page.uc, "';");
        f.L("");
        f.L("const Detail" + detailLocal.fieldList[0].uname + " = () => {");
        f.L____("const [form] = Form.useForm();");
        f.L____("const dispatch = useAppDispatch();");
        f.L____("const [liste" + detailLocal.fieldList[0].uname + ", setListe" + detailLocal.fieldList[0].uname + "] = useState({});");
        f.L____("const [action, setAction] = useState('Ajouter');");
        f.L("");
        f.L____("const init = () => {");
        f.L________("form.resetFields();");
        f.L________("setAction('Ajouter');");
        f.L____("};");
        f.L("");
        f.L____("const ajouter" + detailLocal.fieldList[0].uname + " = () => {");
        f.L________("useValider(form, dispatch, () => {");
        f.L____________("const l = { ...liste" + detailLocal.fieldList[0].uname + " };");
        f.L____________("if (form.getFieldValue('uid')) {");
        f.L____________("    l[form.getFieldValue('uid')] = _.removeNonSerialisable(form.getFieldsValue());");
        f.L____________("} else {");
        f.L____________("    const uid = 'uid' + _.uniqueId();");
        f.L____________("    l[uid] = { ..._.removeNonSerialisable(form.getFieldsValue()), uid };");
        f.L____________("}");
        f.L____________("setListe" + detailLocal.fieldList[0].uname + "(l);");
        f.L____________("dispatch(Mdl", page.uc, ".setListe" + detailLocal.fieldList[0].uname + "(_.values(l)));");
        f.L____________("init();");
        f.L________("});");
        f.L____("};");
        f.L("");
        f.L____("const supprimer" + detailLocal.fieldList[0].uname + " = (record, index) => {");
        f.L________("const l = { ...liste" + detailLocal.fieldList[0].uname + " };");
        f.L________("const l2 = _.omit(l, record.uid);");
        f.L________("setListe" + detailLocal.fieldList[0].uname + "(l2);");
        f.L________("dispatch(Mdl", page.uc, ".setListe" + detailLocal.fieldList[0].uname + "(_.values(l2)));");
        f.L____("};");
        f.L("");
        f.L____("const modifier" + detailLocal.fieldList[0].uname + " = (record, index) => {");
        f.L________("form.setFieldsValue(record);");
        f.L________("setAction('Modifier');");
        f.L____("};");
        f.L("");
        f.L____("return (");
        f.L________("<div>");
        f.L____________("<Formulaire form={form} nombreColonne={3}>");
        f.L____________("    <ChampCache nom=\"uid\" />");
        f.L____________("</Formulaire>");
        f.L____________("<BlocActionDroit>");
        f.L____________("    <BoutonPleinSecondaire label={action} action={ajouter" + detailLocal.fieldList[0].uname + "} icone={<PlusCircleFilled />} />");
        f.L____________("</BlocActionDroit>");
        f.L____________("<Separateur20 />");
        f.L____________("<Tableau listeDonnee={_.values(liste" + detailLocal.fieldList[0].uname + ")} texteAucunResultat=\"Aucun " + detailLocal.fieldList[0].uname + "\" champIdentification=\"uid\">");
        f.L____________("    <ColonneActionModifier typeEntity=\"" + detailLocal.fieldList[0].lname + "\" action={modifier" + detailLocal.fieldList[0].uname + "} />");
        f.L____________("    <ColonneActionSupprimer typeEntity=\"" + detailLocal.fieldList[0].lname + "\" action={supprimer" + detailLocal.fieldList[0].uname + "} />");
        f.L____________("</Tableau>");
        f.L________("</div>");
        f.L____(");");
        f.L("};");
        f.L("");
        f.L("export default Detail" + detailLocal.fieldList[0].uname + ";");

        /* *********************************************************************** */

        String s = f.toString();
        printFile(s, getBasePath() + "/fe/src/" + page.path + "/element/" + entity.uname + detailLocal.fieldList[0].uname + ".tsx");
    }

}
