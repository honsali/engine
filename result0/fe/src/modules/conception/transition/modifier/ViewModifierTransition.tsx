import { Form } from 'antd';
import { useEffect } from 'react';
import { useSelector } from 'react-redux';
import { ActionUcRetourConsulter, Bloc, BlocAction, CadreBas, Section, useExecute } from 'waxant';
import { ActionConception } from '../../ActionConception';
import { PageConsulterTransition } from '../../ListePageConception';
import CtrlModifierTransition from './CtrlModifierTransition';
import { selectModifierTransitionResultat } from './MdlModifierTransition';
import ActionEnregistrerTransition from './element/ActionEnregistrerTransition';
import FormulaireTransition from './element/FormulaireTransition';

const ViewModifierTransition = () => {
    const resultat = useSelector(selectModifierTransitionResultat);
    const { execute, success } = useExecute(resultat);
    const [form] = Form.useForm();

    useEffect(() => {
        execute(CtrlModifierTransition.initModificationTransition);
    }, []);

    useEffect(() => {
        success && form.setFieldsValue(resultat.transition);
    }, [success]);
    //
    return (
        <Section>
            <Bloc largeur="800px">
                <CadreBas titre="formulaireTransition">
                    <FormulaireTransition form={form} />
                    <BlocAction>
                        <ActionEnregistrerTransition form={form} />
                        <ActionUcRetourConsulter nom={ActionConception.UcModifierTransition.RETOUR_CONSULTER_TRANSITION} page={PageConsulterTransition} />
                    </BlocAction>
                </CadreBas>
            </Bloc>
        </Section>
    );
};

export default ViewModifierTransition;