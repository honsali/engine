import { useEffect } from 'react';
import { useSelector } from 'react-redux';
import { Bloc, Colonne, Tableau, useExecute, useGoToPage } from 'waxant';
import { PageConsulterTransition } from '../../../ListePageConception';
import CtrlConsulterQuestion from '../CtrlConsulterQuestion';
import { selectListeTransition } from '../MdlConsulterQuestion';

const TableauTransition = () => {
    const goToPage = useGoToPage();
    const listeTransition = useSelector(selectListeTransition);
    const { execute } = useExecute();


    const consulterTransition = (transition) => {
        goToPage(PageConsulterTransition, transition);
    };

    useEffect(() => {
        execute(CtrlConsulterQuestion.listerTransitionParIdQuestion);
    }, []);
    //
    return (
        <Bloc>
            <Tableau listeDonnee={listeTransition} siClicLigne={consulterTransition} texteAucunResultat="aucun.transition">
                <Colonne nom="raison" />
                <Colonne tc="reference" nom="optionChoisie" />
                <Colonne tc="reference" nom="questionCible" />
            </Tableau>
        </Bloc>
    );
};

export default TableauTransition;