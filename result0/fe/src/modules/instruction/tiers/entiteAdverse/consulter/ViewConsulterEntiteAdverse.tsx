import { useEffect } from 'react';
import { ActionUcModifier, ActionUcRetourListe, BlocAction, Panneau, useExecute } from 'waxant';
import { ActionTiers } from '../../ActionTiers';
import { PageListerTiers, PageModifierEntiteAdverse } from '../../ListePageTiers';
import CtrlConsulterEntiteAdverse from './CtrlConsulterEntiteAdverse';
import ActionEnvoyerSMSEntiteAdverse from './element/ActionEnvoyerSMSEntiteAdverse';
import EtatEntiteAdverse from './element/EtatEntiteAdverse';

const ViewConsulterEntiteAdverse = () => {
    const { execute } = useExecute();

    useEffect(() => {
        execute(CtrlConsulterEntiteAdverse.recupererEntiteAdverseParId);
    }, []);
    //
    return (
        <Panneau>
            <EtatEntiteAdverse />
            <BlocAction>
                <ActionUcModifier nom={ActionTiers.UcConsulterEntiteAdverse.MODIFIER_ENTITE_ADVERSE} page={PageModifierEntiteAdverse} />
                <ActionEnvoyerSMSEntiteAdverse />
                <ActionUcRetourListe nom={ActionTiers.UcConsulterEntiteAdverse.RETOUR_LISTE_ENTITE_ADVERSE} page={PageListerTiers} />
            </BlocAction>
        </Panneau>
    );
};

export default ViewConsulterEntiteAdverse;