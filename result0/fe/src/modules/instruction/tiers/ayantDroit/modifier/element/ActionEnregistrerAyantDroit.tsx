import { useEffect } from 'react';
import { useSelector } from 'react-redux';
import { ActionUcEnregistrer, useExecute, useGoToPage } from 'waxant';
import { ActionTiers } from '../../../ActionTiers';
import { PageConsulterAyantDroit } from '../../../ListePageTiers';
import CtrlModifierAyantDroit from '../CtrlModifierAyantDroit';
import { selectModifierAyantDroitResultat } from '../MdlModifierAyantDroit';

const ActionEnregistrerAyantDroit = ({ form }) => {
    const goToPage = useGoToPage();
    const resultat = useSelector(selectModifierAyantDroitResultat);
    const { execute, success, rid } = useExecute(resultat);

    const enregistrerAyantDroit = () => {
        execute(CtrlModifierAyantDroit.enregistrerAyantDroit, { form });
    };

    useEffect(() => {
        if (success) {
            goToPage(PageConsulterAyantDroit, resultat);
        }
    }, [success]);
    //
    return (
        <ActionUcEnregistrer nom={ActionTiers.UcModifierAyantDroit.ENREGISTRER_AYANT_DROIT} action={enregistrerAyantDroit} rid={rid} />
    );
};

export default ActionEnregistrerAyantDroit;