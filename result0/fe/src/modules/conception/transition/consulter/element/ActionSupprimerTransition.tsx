import { useEffect } from 'react';
import { useSelector } from 'react-redux';
import { ActionUcSupprimer, useExecute, useGoToPage } from 'waxant';
import { ActionConception } from '../../../ActionConception';
import { PageConsulterQuestion } from '../../../ListePageConception';
import CtrlConsulterTransition from '../CtrlConsulterTransition';
import { selectConsulterTransitionResultat } from '../MdlConsulterTransition';

const ActionSupprimerTransition = () => {
    const goToPage = useGoToPage();
    const resultat = useSelector(selectConsulterTransitionResultat);
    const { execute, success, rid } = useExecute(resultat);

    const supprimerTransition = () => {
        execute(CtrlConsulterTransition.supprimerTransition);
    };

    useEffect(() => {
        if (success) {
            goToPage(PageConsulterQuestion, resultat);
        }
    }, [success]);
    //
    return (
        <ActionUcSupprimer nom={ActionConception.UcConsulterTransition.SUPPRIMER_TRANSITION} action={supprimerTransition} rid={rid} />
    );
};

export default ActionSupprimerTransition;