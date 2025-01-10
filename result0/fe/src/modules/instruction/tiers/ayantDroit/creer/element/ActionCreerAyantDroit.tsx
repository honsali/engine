import { useEffect } from 'react';
import { useSelector } from 'react-redux';
import { ActionUcCreer, useExecute, useGoToPage } from 'waxant';
import { ActionTiers } from '../../../ActionTiers';
import { PageConsulterAyantDroit } from '../../../ListePageTiers';
import CtrlCreerAyantDroit from '../CtrlCreerAyantDroit';
import { selectCreerAyantDroitResultat } from '../MdlCreerAyantDroit';

const ActionCreerAyantDroit = ({ form }) => {
    const goToPage = useGoToPage();
    const resultat = useSelector(selectCreerAyantDroitResultat);
    const { execute, success, rid } = useExecute(resultat);

    const creerAyantDroit = () => {
        execute(CtrlCreerAyantDroit.creerAyantDroit, { form });
    };

    useEffect(() => {
        if (success) {
            goToPage(PageConsulterAyantDroit, resultat);
        }
    }, [success]);
    //
    return (
        <ActionUcCreer nom={ActionTiers.UcCreerAyantDroit.CREER_AYANT_DROIT} action={creerAyantDroit} rid={rid} />
    );
};

export default ActionCreerAyantDroit;