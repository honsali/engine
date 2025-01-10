import { useEffect } from 'react';
import { useSelector } from 'react-redux';
import { ActionUcEnregistrer, useExecute, useGoToPage } from 'waxant';
import { ActionTiers } from '../../../ActionTiers';
import { PageConsulterEntiteAdverse } from '../../../ListePageTiers';
import CtrlModifierEntiteAdverse from '../CtrlModifierEntiteAdverse';
import { selectModifierEntiteAdverseResultat } from '../MdlModifierEntiteAdverse';

const ActionEnregistrerEntiteAdverse = ({ form }) => {
    const goToPage = useGoToPage();
    const resultat = useSelector(selectModifierEntiteAdverseResultat);
    const { execute, success, rid } = useExecute(resultat);

    const enregistrerEntiteAdverse = () => {
        execute(CtrlModifierEntiteAdverse.enregistrerEntiteAdverse, { form });
    };

    useEffect(() => {
        if (success) {
            goToPage(PageConsulterEntiteAdverse, resultat);
        }
    }, [success]);
    //
    return (
        <ActionUcEnregistrer nom={ActionTiers.UcModifierEntiteAdverse.ENREGISTRER_ENTITE_ADVERSE} action={enregistrerEntiteAdverse} rid={rid} />
    );
};

export default ActionEnregistrerEntiteAdverse;