import { useEffect } from 'react';
import { useSelector } from 'react-redux';
import { Bloc, Colonne, Tableau, useExecute, useGoToPage } from 'waxant';
import { PageConsulterOption } from '../../../ListePageConception';
import CtrlConsulterQuestion from '../CtrlConsulterQuestion';
import { selectListeOption } from '../MdlConsulterQuestion';

const TableauOption = () => {
    const goToPage = useGoToPage();
    const listeOption = useSelector(selectListeOption);
    const { execute } = useExecute();


    const consulterOption = (option) => {
        goToPage(PageConsulterOption, option);
    };

    useEffect(() => {
        execute(CtrlConsulterQuestion.listerOptionParIdQuestion);
    }, []);
    //
    return (
        <Bloc>
            <Tableau listeDonnee={listeOption} siClicLigne={consulterOption} texteAucunResultat="aucun.option">
                <Colonne nom="libelle" />
                <Colonne nom="valeur" />
            </Tableau>
        </Bloc>
    );
};

export default TableauOption;