import { Bloc, CadreBas, Section } from 'waxant';
import TableauModeleQuestionnaire from './element/TableauModeleQuestionnaire';

const ViewListerModeleQuestionnaire = () => {
    //
    return (
        <Section>
            <Bloc largeur="800px">
                <CadreBas titre="listeModeleQuestionnaire">
                    <TableauModeleQuestionnaire />
                </CadreBas>
            </Bloc>
        </Section>
    );
};

export default ViewListerModeleQuestionnaire;