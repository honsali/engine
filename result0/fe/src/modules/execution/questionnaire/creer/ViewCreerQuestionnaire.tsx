import { Form } from 'antd';
import { useEffect } from 'react';
import { useSelector } from 'react-redux';
import { ActionUcRetourListe, Bloc, BlocAction, CadreBas, Section, useExecute } from 'waxant';
import { ActionExecution } from '../../ActionExecution';
import { PageListerQuestionnaire } from '../../ListePageExecution';
import CtrlCreerQuestionnaire from './CtrlCreerQuestionnaire';
import { selectCreerQuestionnaireResultat } from './MdlCreerQuestionnaire';
import ActionCreerQuestionnaire from './element/ActionCreerQuestionnaire';
import FormulaireQuestionnaire from './element/FormulaireQuestionnaire';

const ViewCreerQuestionnaire = () => {
    const resultat = useSelector(selectCreerQuestionnaireResultat);
    const { execute, success } = useExecute(resultat);
    const [form] = Form.useForm();

    useEffect(() => {
        execute(CtrlCreerQuestionnaire.initCreationQuestionnaire);
    }, []);

    useEffect(() => {
        success && form.setFieldsValue(resultat.questionnaire);
    }, [success]);
    //
    return (
        <Section>
            <Bloc largeur="800px">
                <CadreBas titre="formulaireQuestionnaire">
                    <FormulaireQuestionnaire form={form} />
                    <BlocAction>
                        <ActionCreerQuestionnaire form={form} />
                        <ActionUcRetourListe nom={ActionExecution.UcCreerQuestionnaire.RETOUR_LISTE_QUESTIONNAIRE} page={PageListerQuestionnaire} />
                    </BlocAction>
                </CadreBas>
            </Bloc>
        </Section>
    );
};

export default ViewCreerQuestionnaire;