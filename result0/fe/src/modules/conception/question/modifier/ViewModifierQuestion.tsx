import { Form } from 'antd';
import { useEffect } from 'react';
import { useSelector } from 'react-redux';
import { ActionUcRetourConsulter, Bloc, BlocAction, CadreBas, Section, useExecute } from 'waxant';
import { ActionConception } from '../../ActionConception';
import { PageConsulterQuestion } from '../../ListePageConception';
import CtrlModifierQuestion from './CtrlModifierQuestion';
import { selectModifierQuestionResultat } from './MdlModifierQuestion';
import ActionEnregistrerQuestion from './element/ActionEnregistrerQuestion';
import FormulaireQuestion from './element/FormulaireQuestion';

const ViewModifierQuestion = () => {
    const resultat = useSelector(selectModifierQuestionResultat);
    const { execute, success } = useExecute(resultat);
    const [form] = Form.useForm();

    useEffect(() => {
        execute(CtrlModifierQuestion.initModificationQuestion);
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
                        <ActionEnregistrerQuestion form={form} />
                        <ActionUcRetourConsulter nom={ActionConception.UcModifierQuestion.RETOUR_CONSULTER_QUESTION} page={PageConsulterQuestion} />
                    </BlocAction>
                </CadreBas>
            </Bloc>
        </Section>
    );
};

export default ViewModifierQuestion;