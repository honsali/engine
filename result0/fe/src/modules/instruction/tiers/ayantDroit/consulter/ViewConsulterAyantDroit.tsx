import { useEffect } from 'react';
import { ActionUcModifier, ActionUcRetourListe, BlocAction, Panneau, useExecute } from 'waxant';
import { ActionTiers } from '../../ActionTiers';
import { PageListerTiers, PageModifierAyantDroit } from '../../ListePageTiers';
import EtatSousDossier from '../commun/EtatSousDossier';
import CtrlConsulterAyantDroit from './CtrlConsulterAyantDroit';
import EtatAyantDroit from './element/EtatAyantDroit';

const ViewConsulterAyantDroit = () => {
    const { execute } = useExecute();

    useEffect(() => {
        execute(CtrlConsulterAyantDroit.recupererAyantDroitParId);
    }, []);
    //
    return (
        <Panneau>
            <EtatSousDossier />
            <EtatAyantDroit />
            <BlocAction>
                <ActionUcModifier nom={ActionTiers.UcConsulterAyantDroit.MODIFIER_AYANT_DROIT} page={PageModifierAyantDroit} />
                <ActionUcRetourListe nom={ActionTiers.UcConsulterAyantDroit.RETOUR_LISTE_AYANT_DROIT} page={PageListerTiers} />
            </BlocAction>
        </Panneau>
    );
};

export default ViewConsulterAyantDroit;