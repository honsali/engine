import { Form } from 'antd';
import { useEffect } from 'react';
import { useSelector } from 'react-redux';
import { ActionUcRetourConsulter, BlocAction, Panneau, useExecute } from 'waxant';
import { ActionTiers } from '../../ActionTiers';
import { PageConsulterVictime } from '../../ListePageTiers';
import CtrlModifierVictime from './CtrlModifierVictime';
import { selectModifierVictimeResultat } from './MdlModifierVictime';
import ActionEnregistrerVictime from './element/ActionEnregistrerVictime';
import FormulaireVictime from './element/FormulaireVictime';

const ViewModifierVictime = () => {
    const resultat = useSelector(selectModifierVictimeResultat);
    const { execute, success } = useExecute(resultat);
    const [form] = Form.useForm();

    useEffect(() => {
        execute(CtrlModifierVictime.initModificationVictime);
    }, []);

    useEffect(() => {
        success && form.setFieldsValue(resultat.victime);
    }, [success]);
    //
    return (
        <Panneau>
            <FormulaireVictime form={form} />
            <BlocAction>
                <ActionEnregistrerVictime form={form} />
                <ActionUcRetourConsulter nom={ActionTiers.UcModifierVictime.RETOUR_CONSULTER_VICTIME} page={PageConsulterVictime} />
            </BlocAction>
        </Panneau>
    );
};

export default ViewModifierVictime;