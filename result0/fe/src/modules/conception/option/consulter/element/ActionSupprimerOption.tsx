import { useEffect } from 'react';
import { useSelector } from 'react-redux';
import { ActionUcSupprimer, useExecute, useGoToPage } from 'waxant';
import { ActionConception } from '../../../ActionConception';
import { PageConsulterQuestion } from '../../../ListePageConception';
import CtrlConsulterOption from '../CtrlConsulterOption';
import { selectConsulterOptionResultat } from '../MdlConsulterOption';

const ActionSupprimerOption = () => {
    const goToPage = useGoToPage();
    const resultat = useSelector(selectConsulterOptionResultat);
    const { execute, success, rid } = useExecute(resultat);

    const supprimerOption = () => {
        execute(CtrlConsulterOption.supprimerOption);
    };

    useEffect(() => {
        if (success) {
            goToPage(PageConsulterQuestion, resultat);
        }
    }, [success]);
    //
    return (
        <ActionUcSupprimer nom={ActionConception.UcConsulterOption.SUPPRIMER_OPTION} action={supprimerOption} rid={rid} />
    );
};

export default ActionSupprimerOption;