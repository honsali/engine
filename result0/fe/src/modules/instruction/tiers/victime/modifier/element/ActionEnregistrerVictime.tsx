import { useEffect } from 'react';
import { useSelector } from 'react-redux';
import { ActionUcEnregistrer, useExecute, useGoToPage } from 'waxant';
import { ActionTiers } from '../../../ActionTiers';
import { PageConsulterVictime } from '../../../ListePageTiers';
import CtrlModifierVictime from '../CtrlModifierVictime';
import { selectModifierVictimeResultat } from '../MdlModifierVictime';

const ActionEnregistrerVictime = ({ form }) => {
    const goToPage = useGoToPage();
    const resultat = useSelector(selectModifierVictimeResultat);
    const { execute, success, rid } = useExecute(resultat);

    const enregistrerVictime = () => {
        execute(CtrlModifierVictime.enregistrerVictime, { form });
    };

    useEffect(() => {
        if (success) {
            goToPage(PageConsulterVictime, resultat);
        }
    }, [success]);
    //
    return (
        <ActionUcEnregistrer nom={ActionTiers.UcModifierVictime.ENREGISTRER_VICTIME} action={enregistrerVictime} rid={rid} />
    );
};

export default ActionEnregistrerVictime;