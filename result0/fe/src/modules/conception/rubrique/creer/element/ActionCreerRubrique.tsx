import { useEffect } from 'react';
import { useSelector } from 'react-redux';
import { ActionUcCreer, useExecute, useGoToPage } from 'waxant';
import { ActionConception } from '../../../ActionConception';
import { PageConsulterRubrique } from '../../../ListePageConception';
import CtrlCreerRubrique from '../CtrlCreerRubrique';
import { selectCreerRubriqueResultat } from '../MdlCreerRubrique';

const ActionCreerRubrique = ({ form }) => {
    const goToPage = useGoToPage();
    const resultat = useSelector(selectCreerRubriqueResultat);
    const { execute, success, rid } = useExecute(resultat);

    const creerRubrique = () => {
        execute(CtrlCreerRubrique.creerRubrique, { form });
    };

    useEffect(() => {
        if (success) {
            goToPage(PageConsulterRubrique, resultat);
        }
    }, [success]);
    //
    return (
        <ActionUcCreer nom={ActionConception.UcCreerRubrique.CREER_RUBRIQUE} action={creerRubrique} rid={rid} />
    );
};

export default ActionCreerRubrique;