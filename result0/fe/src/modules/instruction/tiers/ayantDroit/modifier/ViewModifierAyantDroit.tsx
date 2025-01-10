import { Form } from 'antd';
import { useEffect } from 'react';
import { useSelector } from 'react-redux';
import { ActionUcRetourConsulter, BlocAction, Panneau, useExecute } from 'waxant';
import { ActionTiers } from '../../ActionTiers';
import { PageConsulterAyantDroit } from '../../ListePageTiers';
import EtatSousDossier from '../commun/EtatSousDossier';
import CtrlModifierAyantDroit from './CtrlModifierAyantDroit';
import { selectModifierAyantDroitResultat } from './MdlModifierAyantDroit';
import ActionEnregistrerAyantDroit from './element/ActionEnregistrerAyantDroit';
import FormulaireAyantDroit from './element/FormulaireAyantDroit';

const ViewModifierAyantDroit = () => {
    const resultat = useSelector(selectModifierAyantDroitResultat);
    const { execute, success } = useExecute(resultat);
    const [form] = Form.useForm();

    useEffect(() => {
        execute(CtrlModifierAyantDroit.initModificationAyantDroit);
    }, []);

    useEffect(() => {
        success && form.setFieldsValue(resultat.ayantDroit);
    }, [success]);
    //
    return (
        <Panneau>
            <EtatSousDossier />
            <FormulaireAyantDroit form={form} />
            <BlocAction>
                <ActionEnregistrerAyantDroit form={form} />
                <ActionUcRetourConsulter nom={ActionTiers.UcModifierAyantDroit.RETOUR_CONSULTER_AYANT_DROIT} page={PageConsulterAyantDroit} />
            </BlocAction>
        </Panneau>
    );
};

export default ViewModifierAyantDroit;