import { useEffect } from 'react';
import { useSelector } from 'react-redux';
import { ActionUcCreer, useExecute, useGoToPage } from 'waxant';
import { ActionExecution } from '../../../ActionExecution';
import { PageConsulterQuestionnaire } from '../../../ListePageExecution';
import CtrlCreerQuestionnaire from '../CtrlCreerQuestionnaire';
import { selectCreerQuestionnaireResultat } from '../MdlCreerQuestionnaire';

const ActionCreerQuestionnaire = ({ form }) => {
    const goToPage = useGoToPage();
    const resultat = useSelector(selectCreerQuestionnaireResultat);
    const { execute, success, rid } = useExecute(resultat);

    const creerQuestionnaire = () => {
        execute(CtrlCreerQuestionnaire.creerQuestionnaire, { form });
    };

    useEffect(() => {
        if (success) {
            goToPage(PageConsulterQuestionnaire, resultat);
        }
    }, [success]);
    //
    return (
        <ActionUcCreer nom={ActionExecution.UcCreerQuestionnaire.CREER_QUESTIONNAIRE} action={creerQuestionnaire} rid={rid} />
    );
};

export default ActionCreerQuestionnaire;