import { useEffect } from 'react';
import { useSelector } from 'react-redux';
import { ActionUcEnregistrer, useExecute, useGoToPage } from 'waxant';
import { ActionConception } from '../../../ActionConception';
import { PageConsulterTransition } from '../../../ListePageConception';
import CtrlModifierTransition from '../CtrlModifierTransition';
import { selectModifierTransitionResultat } from '../MdlModifierTransition';

const ActionEnregistrerTransition = ({ form }) => {
    const goToPage = useGoToPage();
    const resultat = useSelector(selectModifierTransitionResultat);
    const { execute, success, rid } = useExecute(resultat);

    const enregistrerTransition = () => {
        execute(CtrlModifierTransition.enregistrerTransition, { form });
    };

    useEffect(() => {
        if (success) {
            goToPage(PageConsulterTransition, resultat);
        }
    }, [success]);
    //
    return (
        <ActionUcEnregistrer nom={ActionConception.UcModifierTransition.ENREGISTRER_TRANSITION} action={enregistrerTransition} rid={rid} />
    );
};

export default ActionEnregistrerTransition;