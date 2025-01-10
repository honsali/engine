import { APP_EVENT } from 'commun';
import { useEffect } from 'react';
import { useSelector } from 'react-redux';
import { ActionUcCreer, useEventBus, useExecute, useGoToPage } from 'waxant';
import { ActionTiers } from '../../../ActionTiers';
import { PageConsulterEntiteAdverse } from '../../../ListePageTiers';
import CtrlCreerEntiteAdverse from '../CtrlCreerEntiteAdverse';
import { selectCreerEntiteAdverseResultat } from '../MdlCreerEntiteAdverse';

const ActionCreerEntiteAdverse = ({ form }) => {
    const goToPage = useGoToPage();
    const { emit } = useEventBus();
    const resultat = useSelector(selectCreerEntiteAdverseResultat);
    const { execute, success, rid } = useExecute(resultat);

    const creerEntiteAdverse = () => {
        execute(CtrlCreerEntiteAdverse.creerEntiteAdverse, { form });
    };

    useEffect(() => {
        if (success) {
            emit(APP_EVENT.RECHARGER_SINISTRE, resultat);
            goToPage(PageConsulterEntiteAdverse, resultat);
        }
    }, [success]);
    //
    return (
        <ActionUcCreer nom={ActionTiers.UcCreerEntiteAdverse.CREER_ENTITE_ADVERSE} action={creerEntiteAdverse} rid={rid} />
    );
};

export default ActionCreerEntiteAdverse;