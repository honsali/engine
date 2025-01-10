import { ActionUcAjouter, Bloc, BlocAction, CadreBas, Section } from 'waxant';
import { ActionExecution } from '../../ActionExecution';
import { PageCreerQuestionnaire } from '../../ListePageExecution';
import TableauQuestionnaire from './element/TableauQuestionnaire';

const ViewListerQuestionnaire = () => {
    //
    return (
        <Section>
            <Bloc largeur="800px">
                <CadreBas titre="listeQuestionnaire">
                    <BlocAction>
                        <ActionUcAjouter nom={ActionExecution.UcListerQuestionnaire.AJOUTER_QUESTIONNAIRE} page={PageCreerQuestionnaire} />
                    </BlocAction>
                    <TableauQuestionnaire />
                </CadreBas>
            </Bloc>
        </Section>
    );
};

export default ViewListerQuestionnaire;