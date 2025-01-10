import { useEffect } from 'react';
import { useSelector } from 'react-redux';
import { ActionUcCreer, useExecute, useGoToPage } from 'waxant';
import { ActionExecution } from '../../../ActionExecution';
import { PageConsulterQuestionnaire } from '../../../ListePageExecution';
import CtrlConsulterQuestionnaire from '../CtrlConsulterQuestionnaire';
import { selectConsulterQuestionnaireResultat } from '../MdlConsulterQuestionnaire';

const ActionCreerSessionInterview = () => {
    const goToPage = useGoToPage();
    const resultat = useSelector(selectConsulterQuestionnaireResultat);
    const { execute, success, rid } = useExecute(resultat);

    const creerSessionInterview = () => {
        execute(CtrlConsulterQuestionnaire.creerSessionInterview);
    };

    useEffect(() => {
        if (success) {
            goToPage(PageConsulterQuestionnaire, resultat);
        }
    }, [success]);
    //
    return (
        <ActionUcCreer nom={ActionExecution.UcConsulterQuestionnaire.CREER_SESSION_INTERVIEW} action={creerSessionInterview} rid={rid} />
    );
};

export default ActionCreerSessionInterview;