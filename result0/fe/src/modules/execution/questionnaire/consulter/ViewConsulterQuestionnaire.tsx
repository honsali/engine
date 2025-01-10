import { useEffect } from 'react';
import { Bloc, BlocAction, CadreBas, Section, useExecute } from 'waxant';
import { PageListerQuestionnaire } from '../../ListePageExecution';
import CtrlConsulterQuestionnaire from './CtrlConsulterQuestionnaire';
import ActionCreerSessionInterview from './element/ActionCreerSessionInterview';
import EtatQuestionnaire from './element/EtatQuestionnaire';

const ViewConsulterQuestionnaire = () => {
    const { execute } = useExecute();

    useEffect(() => {
        execute(CtrlConsulterQuestionnaire.recupererQuestionnaireParId);
    }, []);
    //
    return (
        <Section pageRetour={PageListerQuestionnaire}>
            <Bloc largeur="800px">
                <CadreBas titre="etatQuestionnaire">
                    <BlocAction>
                        <ActionCreerSessionInterview />
                    </BlocAction>
                    <EtatQuestionnaire />
                </CadreBas>
            </Bloc>
        </Section>
    );
};

export default ViewConsulterQuestionnaire;