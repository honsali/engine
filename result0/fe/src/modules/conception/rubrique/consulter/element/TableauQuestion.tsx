import { useEffect } from 'react';
import { useSelector } from 'react-redux';
import { Bloc, Colonne, Tableau, useExecute, useGoToPage } from 'waxant';
import { PageConsulterQuestion } from '../../../ListePageConception';
import CtrlConsulterRubrique from '../CtrlConsulterRubrique';
import { selectListeQuestion } from '../MdlConsulterRubrique';

const TableauQuestion = () => {
    const goToPage = useGoToPage();
    const listeQuestion = useSelector(selectListeQuestion);
    const { execute } = useExecute();


    const consulterQuestion = (question) => {
        goToPage(PageConsulterQuestion, question);
    };

    useEffect(() => {
        execute(CtrlConsulterRubrique.listerQuestionParIdRubrique);
    }, []);
    //
    return (
        <Bloc>
            <Tableau listeDonnee={listeQuestion} siClicLigne={consulterQuestion} texteAucunResultat="aucun.question">
                <Colonne nom="position" />
                <Colonne nom="titre" />
                <Colonne tc="reference" nom="typeReponse" />
            </Tableau>
        </Bloc>
    );
};

export default TableauQuestion;