import { useEffect } from 'react';
import { useSelector } from 'react-redux';
import { Bloc, Colonne, Tableau, useExecute, useGoToPage } from 'waxant';
import { PageConsulterEntiteAdverse } from '../../../ListePageTiers';
import CtrlListerEntiteAdverse from '../CtrlListerEntiteAdverse';
import { selectListeEntiteAdverse } from '../MdlListerEntiteAdverse';

const TableauEntiteAdverse = () => {
    const goToPage = useGoToPage();
    const listeEntiteAdverse = useSelector(selectListeEntiteAdverse);
    const { execute } = useExecute();


    const consulterEntiteAdverse = (entiteAdverse) => {
        goToPage(PageConsulterEntiteAdverse, entiteAdverse);
    };

    useEffect(() => {
        execute(CtrlListerEntiteAdverse.listerEntiteAdverse);
    }, []);
    //
    return (
        <Bloc>
            <Tableau listeDonnee={listeEntiteAdverse} siClicLigne={consulterEntiteAdverse} texteAucunResultat="aucun.entiteAdverse">
                <Colonne nom="nom" />
                <Colonne nom="prenom" />
                <Colonne tc="reference" nom="assureur" />
                <Colonne tc="ouiNon" nom="degatsCorporel" />
                <Colonne tc="ouiNon" nom="degatsMateriel" />
            </Tableau>
        </Bloc>
    );
};

export default TableauEntiteAdverse;