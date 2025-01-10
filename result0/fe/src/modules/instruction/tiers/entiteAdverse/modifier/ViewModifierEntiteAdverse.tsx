import { Form } from 'antd';
import { useEffect } from 'react';
import { useSelector } from 'react-redux';
import { ActionUcRetourConsulter, BlocAction, Panneau, useExecute } from 'waxant';
import { ActionTiers } from '../../ActionTiers';
import { PageConsulterEntiteAdverse } from '../../ListePageTiers';
import CtrlModifierEntiteAdverse from './CtrlModifierEntiteAdverse';
import { selectModifierEntiteAdverseResultat } from './MdlModifierEntiteAdverse';
import ActionEnregistrerEntiteAdverse from './element/ActionEnregistrerEntiteAdverse';
import FormulaireEntiteAdverse from './element/FormulaireEntiteAdverse';

const ViewModifierEntiteAdverse = () => {
    const resultat = useSelector(selectModifierEntiteAdverseResultat);
    const { execute, success } = useExecute(resultat);
    const [form] = Form.useForm();

    useEffect(() => {
        execute(CtrlModifierEntiteAdverse.initModificationEntiteAdverse);
    }, []);

    useEffect(() => {
        success && form.setFieldsValue(resultat.entiteAdverse);
    }, [success]);
    //
    return (
        <Panneau>
            <FormulaireEntiteAdverse form={form} />
            <BlocAction>
                <ActionEnregistrerEntiteAdverse form={form} />
                <ActionUcRetourConsulter nom={ActionTiers.UcModifierEntiteAdverse.RETOUR_CONSULTER_ENTITE_ADVERSE} page={PageConsulterEntiteAdverse} />
            </BlocAction>
        </Panneau>
    );
};

export default ViewModifierEntiteAdverse;