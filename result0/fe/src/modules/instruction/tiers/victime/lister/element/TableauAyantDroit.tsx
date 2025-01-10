import { useEffect } from 'react';
import { useSelector } from 'react-redux';
import { Bloc, Colonne, Tableau, useExecute, useGoToPage } from 'waxant';
import { PageConsulterAyantDroit } from '../../../ListePageTiers';
import CtrlListerVictime from '../CtrlListerVictime';
import { selectListeAyantDroit } from '../MdlListerVictime';

const TableauAyantDroit = ({ idSousDossier }) => {
    const goToPage = useGoToPage();
    const listeAyantDroit = useSelector(selectListeAyantDroit);
    const { execute } = useExecute();


    const consulterAyantDroit = (ayantDroit) => {
        goToPage(PageConsulterAyantDroit, ayantDroit);
    };

    useEffect(() => {
        execute(CtrlListerVictime.listerAyantDroit);
    }, []);
    //
    return (
        <Bloc>
            <Tableau listeDonnee={listeAyantDroit} siClicLigne={consulterAyantDroit} texteAucunResultat="aucun.ayantDroit">
                <Colonne nom="nom" />
                <Colonne nom="prenom" />
                <Colonne tc="date" nom="dateNaissance" />
                <Colonne tc="reference" nom="lienParente" />
                <Colonne tc="ouiNon" nom="infirme" />
            </Tableau>
        </Bloc>
    );
};

export default TableauAyantDroit;