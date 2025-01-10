import { useEffect } from 'react';
import { ActionUcModifier, ActionUcRetourListe, BlocAction, Panneau, useExecute } from 'waxant';
import { ActionTiers } from '../../ActionTiers';
import { PageListerTiers, PageModifierVictime } from '../../ListePageTiers';
import CtrlConsulterVictime from './CtrlConsulterVictime';
import ActionEnvoyerSMSVictime from './element/ActionEnvoyerSMSVictime';
import EtatVictime from './element/EtatVictime';

const ViewConsulterVictime = () => {
    const { execute } = useExecute();

    useEffect(() => {
        execute(CtrlConsulterVictime.recupererVictimeParId);
    }, []);
    //
    return (
        <Panneau>
            <EtatVictime />
            <BlocAction>
                <ActionUcModifier nom={ActionTiers.UcConsulterVictime.MODIFIER_VICTIME} page={PageModifierVictime} />
                <ActionEnvoyerSMSVictime />
                <ActionUcRetourListe nom={ActionTiers.UcConsulterVictime.RETOUR_LISTE_VICTIME} page={PageListerTiers} />
            </BlocAction>
        </Panneau>
    );
};

export default ViewConsulterVictime;