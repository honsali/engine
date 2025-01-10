import { APP_EVENT } from 'commun';
import { useSelector } from 'react-redux';
import { Bloc, Colonne, Tableau, useEventBus, useExecute } from 'waxant';
import CtrlChercherIdentification from '../CtrlChercherIdentification';
import { selectListePagineeIdentification } from '../MdlChercherIdentification';

const TableauIdentification = () => {
    const { emit } = useEventBus();
    const listePagineeIdentification = useSelector(selectListePagineeIdentification);
    const { execute } = useExecute();


    const actionChangementPage = (pageCourante: number) => {
        execute(CtrlChercherIdentification.changerPageIdentification, { pageCourante });
    };

    const selectionSinistre = (identification) => {
        emit(APP_EVENT.SELECTION_SINISTRE, identification);
    };
    //
    return (
        <Bloc>
            <Tableau listeDonnee={listePagineeIdentification.liste} pagination={listePagineeIdentification.pagination} siClicLigne={selectionSinistre} siChangementPage={actionChangementPage} texteAucunResultat="aucun.identification">
                <Colonne nom="numeroSinistre" />
                <Colonne nom="numeroPolice" />
                <Colonne tc="date" nom="dateSurvenance" />
                <Colonne tc="date" nom="dateOuverture" />
                <Colonne nom="libelleAssure" />
                <Colonne nom="libelleIntermediaire" />
                <Colonne tc="tag" nom="libelleStatut" />
            </Tableau>
        </Bloc>
    );
};

export default TableauIdentification;