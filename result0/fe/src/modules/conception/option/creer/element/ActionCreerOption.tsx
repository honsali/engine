import { useEffect } from 'react';
import { useSelector } from 'react-redux';
import { ActionUcCreer, useExecute, useGoToPage } from 'waxant';
import { ActionConception } from '../../../ActionConception';
import { PageConsulterOption } from '../../../ListePageConception';
import CtrlCreerOption from '../CtrlCreerOption';
import { selectCreerOptionResultat } from '../MdlCreerOption';

const ActionCreerOption = ({ form }) => {
    const goToPage = useGoToPage();
    const resultat = useSelector(selectCreerOptionResultat);
    const { execute, success, rid } = useExecute(resultat);

    const creerOption = () => {
        execute(CtrlCreerOption.creerOption, { form });
    };

    useEffect(() => {
        if (success) {
            goToPage(PageConsulterOption, resultat);
        }
    }, [success]);
    //
    return (
        <ActionUcCreer nom={ActionConception.UcCreerOption.CREER_OPTION} action={creerOption} rid={rid} />
    );
};

export default ActionCreerOption;