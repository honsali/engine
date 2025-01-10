import { useEffect } from 'react';
import { useSelector } from 'react-redux';
import { Bloc, Colonne, Tableau, useExecute, useGoToPage } from 'waxant';
import { PageConsulterQuestionnaire } from '../../../ListePageExecution';
import CtrlListerQuestionnaire from '../CtrlListerQuestionnaire';
import { selectListeQuestionnaire } from '../MdlListerQuestionnaire';

const TableauQuestionnaire = () => {
    const goToPage = useGoToPage();
    const listeQuestionnaire = useSelector(selectListeQuestionnaire);
    const { execute } = useExecute();


    const consulterQuestionnaire = (questionnaire) => {
        goToPage(PageConsulterQuestionnaire, questionnaire);
    };

    useEffect(() => {
        execute(CtrlListerQuestionnaire.listerQuestionnaire);
    }, []);
    //
    return (
        <Bloc>
            <Tableau listeDonnee={listeQuestionnaire} siClicLigne={consulterQuestionnaire} texteAucunResultat="aucun.questionnaire">
                <Colonne nom="code" />
                <Colonne tc="reference" nom="versionModeleQuestionnaire" />
            </Tableau>
        </Bloc>
    );
};

export default TableauQuestionnaire;