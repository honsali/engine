import { useEffect } from 'react';
import { useSelector } from 'react-redux';
import { ActionUcEnregistrer, useExecute, useGoToPage } from 'waxant';
import { ActionConception } from '../../../ActionConception';
import { PageConsulterQuestion } from '../../../ListePageConception';
import CtrlModifierQuestion from '../CtrlModifierQuestion';
import { selectModifierQuestionResultat } from '../MdlModifierQuestion';

const ActionEnregistrerQuestion = ({ form }) => {
    const goToPage = useGoToPage();
    const resultat = useSelector(selectModifierQuestionResultat);
    const { execute, success, rid } = useExecute(resultat);

    const enregistrerQuestion = () => {
        execute(CtrlModifierQuestion.enregistrerQuestion, { form });
    };

    useEffect(() => {
        if (success) {
            goToPage(PageConsulterQuestion, resultat);
        }
    }, [success]);
    //
    return (
        <ActionUcEnregistrer nom={ActionConception.UcModifierQuestion.ENREGISTRER_QUESTION} action={enregistrerQuestion} rid={rid} />
    );
};

export default ActionEnregistrerQuestion;