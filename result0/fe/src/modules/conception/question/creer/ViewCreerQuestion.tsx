import { Form } from 'antd';
import { useEffect } from 'react';
import { useSelector } from 'react-redux';
import { ActionUcRetourListe, Bloc, BlocAction, CadreBas, Section, useExecute } from 'waxant';
import { ActionConception } from '../../ActionConception';
import { PageConsulterRubrique } from '../../ListePageConception';
import CtrlCreerQuestion from './CtrlCreerQuestion';
import { selectCreerQuestionResultat } from './MdlCreerQuestion';
import ActionCreerQuestion from './element/ActionCreerQuestion';
import FormulaireQuestion from './element/FormulaireQuestion';

const ViewCreerQuestion = () => {
    const resultat = useSelector(selectCreerQuestionResultat);
    const { execute, success } = useExecute(resultat);
    const [form] = Form.useForm();

    useEffect(() => {
        execute(CtrlCreerQuestion.initCreationQuestion);
    }, []);

    useEffect(() => {
        success && form.setFieldsValue(resultat.question);
    }, [success]);
    //
    return (
        <Section>
            <Bloc largeur="800px">
                <CadreBas titre="formulaireQuestion">
                    <FormulaireQuestion form={form} />
                    <BlocAction>
                        <ActionCreerQuestion form={form} />
                        <ActionUcRetourListe nom={ActionConception.UcCreerQuestion.RETOUR_LISTE_QUESTION} page={PageConsulterRubrique} />
                    </BlocAction>
                </CadreBas>
            </Bloc>
        </Section>
    );
};

export default ViewCreerQuestion;