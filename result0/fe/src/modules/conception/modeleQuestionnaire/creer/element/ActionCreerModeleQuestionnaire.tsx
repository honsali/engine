import { useEffect } from 'react';
import { useSelector } from 'react-redux';
import { ActionUcCreer, useExecute, useGoToPage } from 'waxant';
import { ActionConception } from '../../../ActionConception';
import { PageListerModeleQuestionnaire } from '../../../ListePageConception';
import CtrlCreerModeleQuestionnaire from '../CtrlCreerModeleQuestionnaire';
import { selectCreerModeleQuestionnaireResultat } from '../MdlCreerModeleQuestionnaire';

const ActionCreerModeleQuestionnaire = ({ form }) => {
    const goToPage = useGoToPage();
    const resultat = useSelector(selectCreerModeleQuestionnaireResultat);
    const { execute, success, rid } = useExecute(resultat);

    const creerModeleQuestionnaire = () => {
        execute(CtrlCreerModeleQuestionnaire.creerModeleQuestionnaire, { form });
    };

    useEffect(() => {
        if (success) {
            goToPage(PageListerModeleQuestionnaire, resultat);
        }
    }, [success]);
    //
    return (
        <ActionUcCreer nom={ActionConception.UcCreerModeleQuestionnaire.CREER_MODELE_QUESTIONNAIRE} action={creerModeleQuestionnaire} rid={rid} />
    );
};

export default ActionCreerModeleQuestionnaire;