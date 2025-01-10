import { Form } from 'antd';
import { useEffect } from 'react';
import { useSelector } from 'react-redux';
import { ActionUcRetourListe, BlocAction, Panneau, useExecute } from 'waxant';
import { ActionTiers } from '../../ActionTiers';
import { PageListerTiers } from '../../ListePageTiers';
import EtatSousDossier from '../commun/EtatSousDossier';
import CtrlCreerAyantDroit from './CtrlCreerAyantDroit';
import { selectCreerAyantDroitResultat } from './MdlCreerAyantDroit';
import ActionCreerAyantDroit from './element/ActionCreerAyantDroit';
import FormulaireAyantDroit from './element/FormulaireAyantDroit';

const ViewCreerAyantDroit = () => {
    const resultat = useSelector(selectCreerAyantDroitResultat);
    const { execute, success } = useExecute(resultat);
    const [form] = Form.useForm();

    useEffect(() => {
        execute(CtrlCreerAyantDroit.initCreationAyantDroit);
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
                <ActionCreerAyantDroit form={form} />
                <ActionUcRetourListe nom={ActionTiers.UcCreerAyantDroit.RETOUR_LISTE_AYANT_DROIT} page={PageListerTiers} />
            </BlocAction>
        </Panneau>
    );
};

export default ViewCreerAyantDroit;