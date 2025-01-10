import { useEffect } from 'react';
import { useSelector } from 'react-redux';
import { ActionUcCreer, useExecute, useGoToPage } from 'waxant';
import { ActionConception } from '../../../ActionConception';
import { PageConsulterQuestion } from '../../../ListePageConception';
import CtrlCreerQuestion from '../CtrlCreerQuestion';
import { selectCreerQuestionResultat } from '../MdlCreerQuestion';

const ActionCreerQuestion = ({ form }) => {
    const goToPage = useGoToPage();
    const resultat = useSelector(selectCreerQuestionResultat);
    const { execute, success, rid } = useExecute(resultat);

    const creerQuestion = () => {
        execute(CtrlCreerQuestion.creerQuestion, { form });
    };

    useEffect(() => {
        if (success) {
            goToPage(PageConsulterQuestion, resultat);
        }
    }, [success]);
    //
    return (
        <ActionUcCreer nom={ActionConception.UcCreerQuestion.CREER_QUESTION} action={creerQuestion} rid={rid} />
    );
};

export default ActionCreerQuestion;