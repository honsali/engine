import { useEffect } from 'react';
import { useSelector } from 'react-redux';
import { Bloc, Colonne, Tableau, useExecute, useGoToPage } from 'waxant';
import { PageConsulterRubrique } from '../../../ListePageConception';
import CtrlModifierVersionModeleQuestionnaire from '../CtrlModifierVersionModeleQuestionnaire';
import { selectListeRubrique } from '../MdlModifierVersionModeleQuestionnaire';

const TableauRubrique = () => {
    const goToPage = useGoToPage();
    const listeRubrique = useSelector(selectListeRubrique);
    const { execute } = useExecute();


    const consulterRubrique = (rubrique) => {
        goToPage(PageConsulterRubrique, rubrique);
    };

    useEffect(() => {
        execute(CtrlModifierVersionModeleQuestionnaire.listerRubriqueParIdVersionModeleQuestionnaire);
    }, []);
    //
    return (
        <Bloc>
            <Tableau listeDonnee={listeRubrique} siClicLigne={consulterRubrique} texteAucunResultat="aucun.rubrique">
                <Colonne nom="position" />
                <Colonne nom="titre" />
                <Colonne nom="description" />
            </Tableau>
        </Bloc>
    );
};

export default TableauRubrique;