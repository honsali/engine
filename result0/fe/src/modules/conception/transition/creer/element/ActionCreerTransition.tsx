import { useEffect } from 'react';
import { useSelector } from 'react-redux';
import { ActionUcCreer, useExecute, useGoToPage } from 'waxant';
import { ActionConception } from '../../../ActionConception';
import { PageConsulterTransition } from '../../../ListePageConception';
import CtrlCreerTransition from '../CtrlCreerTransition';
import { selectCreerTransitionResultat } from '../MdlCreerTransition';

const ActionCreerTransition = ({ form }) => {
    const goToPage = useGoToPage();
    const resultat = useSelector(selectCreerTransitionResultat);
    const { execute, success, rid } = useExecute(resultat);

    const creerTransition = () => {
        execute(CtrlCreerTransition.creerTransition, { form });
    };

    useEffect(() => {
        if (success) {
            goToPage(PageConsulterTransition, resultat);
        }
    }, [success]);
    //
    return (
        <ActionUcCreer nom={ActionConception.UcCreerTransition.CREER_TRANSITION} action={creerTransition} rid={rid} />
    );
};

export default ActionCreerTransition;