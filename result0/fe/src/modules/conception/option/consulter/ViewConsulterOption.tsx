import { useEffect } from 'react';
import { ActionUcModifier, Bloc, BlocAction, CadreBas, Section, useExecute } from 'waxant';
import { ActionConception } from '../../ActionConception';
import { PageConsulterQuestion, PageModifierOption } from '../../ListePageConception';
import CtrlConsulterOption from './CtrlConsulterOption';
import ActionSupprimerOption from './element/ActionSupprimerOption';
import EtatOption from './element/EtatOption';

const ViewConsulterOption = () => {
    const { execute } = useExecute();

    useEffect(() => {
        execute(CtrlConsulterOption.recupererOptionParId);
    }, []);
    //
    return (
        <Section pageRetour={PageConsulterQuestion}>
            <Bloc largeur="800px">
                <CadreBas titre="etatOption">
                    <BlocAction>
                        <ActionUcModifier nom={ActionConception.UcConsulterOption.MODIFIER_OPTION} page={PageModifierOption} />
                        <ActionSupprimerOption />
                    </BlocAction>
                    <EtatOption />
                </CadreBas>
            </Bloc>
        </Section>
    );
};

export default ViewConsulterOption;