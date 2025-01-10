import { useEffect } from 'react';
import { useSelector } from 'react-redux';
import { Bloc, Colonne, Tableau, useExecute, useGoToPage } from 'waxant';
import { PageConsulterVictime } from '../../../ListePageTiers';
import CtrlListerVictime from '../CtrlListerVictime';
import { selectListeVictime } from '../MdlListerVictime';
import PanneauAyantDroit from './PanneauAyantDroit';

const TableauVictime = () => {
    const goToPage = useGoToPage();
    const listeVictime = useSelector(selectListeVictime);
    const { execute } = useExecute();


    const consulterVictime = (victime) => {
        goToPage(PageConsulterVictime, victime);
    };

    useEffect(() => {
        execute(CtrlListerVictime.listerVictime);
    }, []);
    //
    return (
        <Bloc>
            <PanneauAyantDroit idSousDossier={idSousDossier} />
            <Tableau listeDonnee={listeVictime} siClicLigne={consulterVictime} texteAucunResultat="aucun.victime">
                <Colonne tc="rendu" content={getColonneVictime} nom="victime" />
                <Colonne tc="reference" nom="qualiteVictime" />
                <Colonne tc="reference" nom="entiteAdverse" />
            </Tableau>
        </Bloc>
    );
};

export default TableauVictime;