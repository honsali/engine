import { useEffect } from 'react';
import { ActionUcAjouter, Bloc, BlocAction, CadreBas, Section, useExecute } from 'waxant';
import { ActionConception } from '../../ActionConception';
import { PageCreerRubrique, PageListerModeleQuestionnaire } from '../../ListePageConception';
import CtrlConsulterVersionModeleQuestionnaire from './CtrlConsulterVersionModeleQuestionnaire';
import EtatVersionModeleQuestionnaire from './element/EtatVersionModeleQuestionnaire';
import TableauRubrique from './element/TableauRubrique';

const ViewConsulterVersionModeleQuestionnaire = () => {
    const { execute } = useExecute();

    useEffect(() => {
        execute(CtrlConsulterVersionModeleQuestionnaire.recupererVersionModeleQuestionnaireParId);
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
                        <ActionUcAjouter nom={ActionConception.UcConsulterVersionModeleQuestionnaire.AJOUTER_RUBRIQUE} page={PageCreerRubrique} />
                    </BlocAction>
                    <TableauRubrique />
                </CadreBas>
            </Bloc>
        </Section>
    );
};

export default ViewConsulterVersionModeleQuestionnaire;