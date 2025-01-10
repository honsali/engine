import { useEffect } from 'react';
import { ActionUcAjouter, ActionUcModifier, Bloc, BlocAction, CadreBas, Section, useExecute } from 'waxant';
import { ActionConception } from '../../ActionConception';
import { PageConsulterVersionModeleQuestionnaire, PageCreerQuestion, PageModifierRubrique } from '../../ListePageConception';
import CtrlConsulterRubrique from './CtrlConsulterRubrique';
import EtatRubrique from './element/EtatRubrique';
import TableauQuestion from './element/TableauQuestion';

const ViewConsulterRubrique = () => {
    const { execute } = useExecute();

    useEffect(() => {
        execute(CtrlConsulterRubrique.recupererRubriqueParId);
    }, []);
    //
    return (
        <Section pageRetour={PageConsulterVersionModeleQuestionnaire}>
            <Bloc largeur="800px">
                <CadreBas titre="etatRubrique">
                    <BlocAction>
                        <ActionUcModifier nom={ActionConception.UcConsulterRubrique.MODIFIER_RUBRIQUE} page={PageModifierRubrique} />
                    </BlocAction>
                    <EtatRubrique />
                </CadreBas>
            </Bloc>
            <Bloc largeur="800px">
                <CadreBas titre="listeQuestion">
                    <BlocAction>
                        <ActionUcAjouter nom={ActionConception.UcConsulterRubrique.AJOUTER_QUESTION} page={PageCreerQuestion} />
                    </BlocAction>
                    <TableauQuestion />
                </CadreBas>
            </Bloc>
        </Section>
    );
};

export default ViewConsulterRubrique;