import { Form } from 'antd';
import { useEffect } from 'react';
import { useSelector } from 'react-redux';
import { ActionUcRetourListe, BlocAction, Panneau, useExecute } from 'waxant';
import { ActionTiers } from '../../ActionTiers';
import { PageListerTiers } from '../../ListePageTiers';
import CtrlCreerVictime from './CtrlCreerVictime';
import { selectCreerVictimeResultat } from './MdlCreerVictime';
import ActionCreerVictime from './element/ActionCreerVictime';
import FormulaireVictime from './element/FormulaireVictime';

const ViewCreerVictime = () => {
    const resultat = useSelector(selectCreerVictimeResultat);
    const { execute, success } = useExecute(resultat);
    const [form] = Form.useForm();

    useEffect(() => {
        execute(CtrlCreerVictime.initCreationVictime);
    }, []);

    useEffect(() => {
        success && form.setFieldsValue(resultat.victime);
    }, [success]);
    //
    return (
        <Panneau>
            <FormulaireVictime form={form} />
            <BlocAction>
                <ActionCreerVictime form={form} />
                <ActionUcRetourListe nom={ActionTiers.UcCreerVictime.RETOUR_LISTE_VICTIME} page={PageListerTiers} />
            </BlocAction>
        </Panneau>
    );
};

export default ViewCreerVictime;