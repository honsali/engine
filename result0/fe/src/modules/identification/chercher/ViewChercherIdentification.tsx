import { Panneau, Section } from 'waxant';
import FiltreIdentification from './element/FiltreIdentification';
import TableauIdentification from './element/TableauIdentification';

const ViewChercherIdentification = () => {
    //
    return (
        <Section>
            <Panneau titre="listeIdentification">
                <TableauIdentification />
            </Panneau>
            <FiltreIdentification />
        </Section>
    );
};

export default ViewChercherIdentification;