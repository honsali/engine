import { Form } from 'antd';
import { useEffect } from 'react';
import { useSelector } from 'react-redux';
import { ActionUcRetourListe, BlocAction, Panneau, useExecute } from 'waxant';
import { ActionTiers } from '../../ActionTiers';
import { PageListerTiers } from '../../ListePageTiers';
import CtrlCreerEntiteAdverse from './CtrlCreerEntiteAdverse';
import { selectCreerEntiteAdverseResultat } from './MdlCreerEntiteAdverse';
import ActionCreerEntiteAdverse from './element/ActionCreerEntiteAdverse';
import FormulaireEntiteAdverse from './element/FormulaireEntiteAdverse';

const ViewCreerEntiteAdverse = () => {
    const resultat = useSelector(selectCreerEntiteAdverseResultat);
    const { execute, success } = useExecute(resultat);
    const [form] = Form.useForm();

    useEffect(() => {
        execute(CtrlCreerEntiteAdverse.initCreationEntiteAdverse);
    }, []);

    useEffect(() => {
        success && form.setFieldsValue(resultat.entiteAdverse);
    }, [success]);
    //
    return (
        <Panneau>
            <FormulaireEntiteAdverse form={form} />
            <BlocAction>
                <ActionCreerEntiteAdverse form={form} />
                <ActionUcRetourListe nom={ActionTiers.UcCreerEntiteAdverse.RETOUR_LISTE_ENTITE_ADVERSE} page={PageListerTiers} />
            </BlocAction>
        </Panneau>
    );
};

export default ViewCreerEntiteAdverse;