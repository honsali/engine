import { useEffect } from 'react';
import { useSelector } from 'react-redux';
import { Bloc, Colonne, Tableau, useExecute } from 'waxant';
import CtrlListerModeleQuestionnaire from '../CtrlListerModeleQuestionnaire';
import { selectListeModeleQuestionnaire } from '../MdlListerModeleQuestionnaire';

const TableauModeleQuestionnaire = () => {
    const listeModeleQuestionnaire = useSelector(selectListeModeleQuestionnaire);
    const { execute } = useExecute();


    useEffect(() => {
        execute(CtrlListerModeleQuestionnaire.listerModeleQuestionnaire);
    }, []);
    //
    return (
        <Bloc>
            <Tableau listeDonnee={listeModeleQuestionnaire} texteAucunResultat="aucun.modeleQuestionnaire">
                <Colonne nom="nom" />
                <Colonne nom="description" />
                <Colonne tc="date" nom="dateCreation" />
                <Colonne tc="reference" nom="versionDeveloppement" />
                <Colonne tc="reference" nom="versionProduction" />
            </Tableau>
        </Bloc>
    );
};

export default TableauModeleQuestionnaire;