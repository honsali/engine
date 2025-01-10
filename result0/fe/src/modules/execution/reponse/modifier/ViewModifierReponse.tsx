import { Form } from 'antd';
import { useEffect } from 'react';
import { useSelector } from 'react-redux';
import { ActionUcRetourConsulter, Bloc, BlocAction, CadreBas, Section, useExecute } from 'waxant';
import { ActionExecution } from '../../ActionExecution';
import { PageConsulterQuestionnaire } from '../../ListePageExecution';
import CtrlModifierReponse from './CtrlModifierReponse';
import { selectModifierReponseResultat } from './MdlModifierReponse';
import ActionEnregistrerReponse from './element/ActionEnregistrerReponse';
import FormulaireReponse from './element/FormulaireReponse';

const ViewModifierReponse = () => {
    const resultat = useSelector(selectModifierReponseResultat);
    const { execute, success } = useExecute(resultat);
    const [form] = Form.useForm();

    useEffect(() => {
        execute(CtrlModifierReponse.initModificationReponse);
    }, []);

    useEffect(() => {
        success && form.setFieldsValue(resultat.reponse);
    }, [success]);
    //
    return (
        <Section>
            <Bloc largeur="800px">
                <CadreBas titre="formulaireQuestionnaire">
                    <FormulaireReponse form={form} />
                    <BlocAction>
                        <ActionEnregistrerReponse form={form} />
                        <ActionUcRetourConsulter nom={ActionExecution.UcModifierReponse.RETOUR_CONSULTER_REPONSE} page={PageConsulterQuestionnaire} />
                    </BlocAction>
                </CadreBas>
            </Bloc>
        </Section>
    );
};

export default ViewModifierReponse;