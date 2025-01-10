import { ActionUcAjouter, BlocAction, Panneau } from 'waxant';
import { ActionTiers } from '../../ActionTiers';
import { PageCreerEntiteAdverse } from '../../ListePageTiers';
import TableauEntiteAdverse from './element/TableauEntiteAdverse';

const ViewListerEntiteAdverse = () => {
    //
    return (
        <Panneau>
            <TableauEntiteAdverse />
            <BlocAction>
                <ActionUcAjouter nom={ActionTiers.UcListerEntiteAdverse.AJOUTER_ENTITE_ADVERSE} page={PageCreerEntiteAdverse} />
            </BlocAction>
        </Panneau>
    );
};

export default ViewListerEntiteAdverse;