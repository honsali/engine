import { APP_EVENT } from 'commun';
import { useEffect } from 'react';
import { useSelector } from 'react-redux';
import { ActionUcCreer, useEventBus, useExecute, useGoToPage } from 'waxant';
import { ActionTiers } from '../../../ActionTiers';
import { PageConsulterVictime } from '../../../ListePageTiers';
import CtrlCreerVictime from '../CtrlCreerVictime';
import { selectCreerVictimeResultat } from '../MdlCreerVictime';

const ActionCreerVictime = ({ form }) => {
    const goToPage = useGoToPage();
    const { emit } = useEventBus();
    const resultat = useSelector(selectCreerVictimeResultat);
    const { execute, success, rid } = useExecute(resultat);

    const creerVictime = () => {
        execute(CtrlCreerVictime.creerVictime, { form });
    };

    useEffect(() => {
        if (success) {
            emit(APP_EVENT.RECHARGER_SINISTRE, resultat);
            goToPage(PageConsulterVictime, resultat);
        }
    }, [success]);
    //
    return (
        <ActionUcCreer nom={ActionTiers.UcCreerVictime.CREER_VICTIME} action={creerVictime} rid={rid} />
    );
};

export default ActionCreerVictime;