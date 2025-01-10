import { ActionUcAjouter, BlocAction, Panneau } from 'waxant';
import { ActionTiers } from '../../ActionTiers';
import { PageCreerVictime } from '../../ListePageTiers';
import TableauVictime from './element/TableauVictime';

const ViewListerVictime = () => {
    //
    return (
        <Panneau>
            <TableauVictime />
            <BlocAction>
                <ActionUcAjouter nom={ActionTiers.UcListerVictime.AJOUTER_VICTIME} page={PageCreerVictime} />
            </BlocAction>
        </Panneau>
    );
};

export default ViewListerVictime;