import { ActionUcAjouter, BlocAction, CadreBas } from 'waxant';
import { ActionTiers } from '../../../ActionTiers';
import { PageCreerAyantDroit } from '../../../ListePageTiers';
import TableauAyantDroit from './TableauAyantDroit';

const PanneauAyantDroit = ({ idSousDossier }) => {
    //
    return (
        <CadreBas titre="listeAyantDroit">
            <TableauAyantDroit idSousDossier={idSousDossier} />
            <BlocAction>
                <ActionUcAjouter nom={ActionTiers.UcListerVictime.AJOUTER_AYANT_DROIT} page={PageCreerAyantDroit} modele={{ idSousDossier }} />
            </BlocAction>
        </CadreBas>
    );
};

export default PanneauAyantDroit;