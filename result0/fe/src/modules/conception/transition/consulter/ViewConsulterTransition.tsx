import { useEffect } from 'react';
import { ActionUcModifier, Bloc, BlocAction, CadreBas, Section, useExecute } from 'waxant';
import { ActionConception } from '../../ActionConception';
import { PageConsulterQuestion, PageModifierTransition } from '../../ListePageConception';
import CtrlConsulterTransition from './CtrlConsulterTransition';
import ActionSupprimerTransition from './element/ActionSupprimerTransition';
import EtatTransition from './element/EtatTransition';

const ViewConsulterTransition = () => {
    const { execute } = useExecute();

    useEffect(() => {
        execute(CtrlConsulterTransition.recupererTransitionParId);
    }, []);
    //
    return (
        <Section pageRetour={PageConsulterQuestion}>
            <Bloc largeur="800px">
                <CadreBas titre="etatTransition">
                    <BlocAction>
                        <ActionUcModifier nom={ActionConception.UcConsulterTransition.MODIFIER_TRANSITION} page={PageModifierTransition} />
                        <ActionSupprimerTransition />
                    </BlocAction>
                    <EtatTransition />
                </CadreBas>
            </Bloc>
        </Section>
    );
};

export default ViewConsulterTransition;