import { Form } from 'antd';
import { useEffect } from 'react';
import { useSelector } from 'react-redux';
import { ActionUcRetourListe, Bloc, BlocAction, CadreBas, Section, useExecute } from 'waxant';
import { ActionConception } from '../../ActionConception';
import { PageConsulterQuestion } from '../../ListePageConception';
import CtrlCreerOption from './CtrlCreerOption';
import { selectCreerOptionResultat } from './MdlCreerOption';
import ActionCreerOption from './element/ActionCreerOption';
import FormulaireOption from './element/FormulaireOption';

const ViewCreerOption = () => {
    const resultat = useSelector(selectCreerOptionResultat);
    const { execute, success } = useExecute(resultat);
    const [form] = Form.useForm();

    useEffect(() => {
        execute(CtrlCreerOption.initCreationOption);
    }, []);

    useEffect(() => {
        success && form.setFieldsValue(resultat.option);
    }, [success]);
    //
    return (
        <Section>
            <Bloc largeur="800px">
                <CadreBas titre="formulaireOption">
                    <FormulaireOption form={form} />
                    <BlocAction>
                        <ActionCreerOption form={form} />
                        <ActionUcRetourListe nom={ActionConception.UcCreerOption.RETOUR_LISTE_OPTION} page={PageConsulterQuestion} />
                    </BlocAction>
                </CadreBas>
            </Bloc>
        </Section>
    );
};

export default ViewCreerOption;