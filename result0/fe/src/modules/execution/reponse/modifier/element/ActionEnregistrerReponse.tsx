import { useEffect } from 'react';
import { useSelector } from 'react-redux';
import { ActionUcEnregistrer, useExecute, useGoToPage } from 'waxant';
import { ActionExecution } from '../../../ActionExecution';
import { PageConsulterQuestionnaire } from '../../../ListePageExecution';
import CtrlModifierReponse from '../CtrlModifierReponse';
import { selectModifierReponseResultat } from '../MdlModifierReponse';

const ActionEnregistrerReponse = ({ form }) => {
    const goToPage = useGoToPage();
    const resultat = useSelector(selectModifierReponseResultat);
    const { execute, success, rid } = useExecute(resultat);

    const enregistrerReponse = () => {
        execute(CtrlModifierReponse.enregistrerReponse, { form });
    };

    useEffect(() => {
        if (success) {
            goToPage(PageConsulterQuestionnaire, resultat);
        }
    }, [success]);
    //
    return (
        <ActionUcEnregistrer nom={ActionExecution.UcModifierReponse.ENREGISTRER_REPONSE} action={enregistrerReponse} rid={rid} />
    );
};

export default ActionEnregistrerReponse;