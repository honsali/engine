import { useEffect } from 'react';
import { ActionUcAjouter, Bloc, BlocAction, CadreBas, Section, useExecute } from 'waxant';
import { ActionConception } from '../../ActionConception';
import { PageCreerRubrique, PageListerModeleQuestionnaire } from '../../ListePageConception';
import CtrlModifierVersionModeleQuestionnaire from './CtrlModifierVersionModeleQuestionnaire';
import EtatVersionModeleQuestionnaire from './element/EtatVersionModeleQuestionnaire';
import TableauRubrique from './element/TableauRubrique';

const ViewModifierVersionModeleQuestionnaire = () => {
    const { execute } = useExecute();

    useEffect(() => {
        execute(CtrlModifierVersionModeleQuestionnaire.recupererVersionModeleQuestionnaireParId);
    }, []);
    //
    return (
        <Section pageRetour={PageListerModeleQuestionnaire}>
            <Bloc largeur="800px">
                <CadreBas titre="etatModeleQuestionnaire">
                    <EtatVersionModeleQuestionnaire />
                </CadreBas>
            </Bloc>
            <Bloc largeur="800px">
                <CadreBas titre="listeRubrique">
                    <BlocAction>
                        <ActionUcAjouter nom={ActionConception.UcModifierVersionModeleQuestionnaire.AJOUTER_RUBRIQUE} page={PageCreerRubrique} />
                    </BlocAction>
                    <TableauRubrique />
                </CadreBas>
            </Bloc>
        </Section>
    );
};

export default ViewModifierVersionModeleQuestionnaire;