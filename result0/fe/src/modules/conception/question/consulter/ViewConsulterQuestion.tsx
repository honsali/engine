import { useEffect } from 'react';
import { ActionUcAjouter, ActionUcModifier, Bloc, BlocAction, CadreBas, Section, useExecute } from 'waxant';
import { ActionConception } from '../../ActionConception';
import { PageConsulterRubrique, PageCreerOption, PageCreerTransition, PageModifierQuestion } from '../../ListePageConception';
import CtrlConsulterQuestion from './CtrlConsulterQuestion';
import ActionExecuterQuestion from './element/ActionExecuterQuestion';
import EtatQuestion from './element/EtatQuestion';
import TableauOption from './element/TableauOption';
import TableauTransition from './element/TableauTransition';

const ViewConsulterQuestion = () => {
    const { execute } = useExecute();

    useEffect(() => {
        execute(CtrlConsulterQuestion.recupererQuestionParId);
    }, []);
    //
    return (
        <Section pageRetour={PageConsulterRubrique}>
            <Bloc largeur="800px">
                <CadreBas titre="etatQuestion">
                    <BlocAction>
                        <ActionUcModifier nom={ActionConception.UcConsulterQuestion.MODIFIER_QUESTION} page={PageModifierQuestion} />
                        <ActionExecuterQuestion />
                    </BlocAction>
                    <EtatQuestion />
                </CadreBas>
            </Bloc>
            <Bloc largeur="800px">
                <CadreBas titre="listeOption">
                    <BlocAction>
                        <ActionUcAjouter nom={ActionConception.UcConsulterQuestion.AJOUTER_OPTION} page={PageCreerOption} />
                    </BlocAction>
                    <TableauOption />
                </CadreBas>
            </Bloc>
            <Bloc largeur="800px">
                <CadreBas titre="listeTransition">
                    <BlocAction>
                        <ActionUcAjouter nom={ActionConception.UcConsulterQuestion.AJOUTER_TRANSITION} page={PageCreerTransition} />
                    </BlocAction>
                    <TableauTransition />
                </CadreBas>
            </Bloc>
        </Section>
    );
};

export default ViewConsulterQuestion;