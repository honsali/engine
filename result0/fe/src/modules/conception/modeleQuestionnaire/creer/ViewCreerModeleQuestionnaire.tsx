import { Form } from 'antd';
import { useEffect } from 'react';
import { useSelector } from 'react-redux';
import { ActionUcRetourListe, Bloc, BlocAction, CadreBas, Section, useExecute } from 'waxant';
import { ActionConception } from '../../ActionConception';
import { PageListerModeleQuestionnaire } from '../../ListePageConception';
import CtrlCreerModeleQuestionnaire from './CtrlCreerModeleQuestionnaire';
import { selectCreerModeleQuestionnaireResultat } from './MdlCreerModeleQuestionnaire';
import ActionCreerModeleQuestionnaire from './element/ActionCreerModeleQuestionnaire';
import FormulaireModeleQuestionnaire from './element/FormulaireModeleQuestionnaire';

const ViewCreerModeleQuestionnaire = () => {
    const resultat = useSelector(selectCreerModeleQuestionnaireResultat);
    const { execute, success } = useExecute(resultat);
    const [form] = Form.useForm();

    useEffect(() => {
        execute(CtrlCreerModeleQuestionnaire.initCreationModeleQuestionnaire);
    }, []);

    useEffect(() => {
        success && form.setFieldsValue(resultat.modeleQuestionnaire);
    }, [success]);
    //
    return (
        <Section>
            <Bloc largeur="800px">
                <CadreBas titre="formulaireModeleQuestionnaire">
                    <FormulaireModeleQuestionnaire form={form} />
                    <BlocAction>
                        <ActionCreerModeleQuestionnaire form={form} />
                        <ActionUcRetourListe nom={ActionConception.UcCreerModeleQuestionnaire.RETOUR_LISTE_MODELE_QUESTIONNAIRE} page={PageListerModeleQuestionnaire} />
                    </BlocAction>
                </CadreBas>
            </Bloc>
        </Section>
    );
};

export default ViewCreerModeleQuestionnaire;