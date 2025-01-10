import { Form } from 'antd';
import { useEffect } from 'react';
import { useSelector } from 'react-redux';
import { ActionUcRetourListe, Bloc, BlocAction, CadreBas, Section, useExecute } from 'waxant';
import { ActionConception } from '../../ActionConception';
import { PageConsulterQuestion } from '../../ListePageConception';
import CtrlCreerTransition from './CtrlCreerTransition';
import { selectCreerTransitionResultat } from './MdlCreerTransition';
import ActionCreerTransition from './element/ActionCreerTransition';
import FormulaireTransition from './element/FormulaireTransition';

const ViewCreerTransition = () => {
    const resultat = useSelector(selectCreerTransitionResultat);
    const { execute, success } = useExecute(resultat);
    const [form] = Form.useForm();

    useEffect(() => {
        execute(CtrlCreerTransition.initCreationTransition);
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
                        <ActionCreerTransition form={form} />
                        <ActionUcRetourListe nom={ActionConception.UcCreerTransition.RETOUR_LISTE_TRANSITION} page={PageConsulterQuestion} />
                    </BlocAction>
                </CadreBas>
            </Bloc>
        </Section>
    );
};

export default ViewCreerTransition;