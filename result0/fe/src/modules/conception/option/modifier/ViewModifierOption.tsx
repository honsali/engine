import { Form } from 'antd';
import { useEffect } from 'react';
import { useSelector } from 'react-redux';
import { ActionUcRetourConsulter, Bloc, BlocAction, CadreBas, Section, useExecute } from 'waxant';
import { ActionConception } from '../../ActionConception';
import { PageConsulterOption } from '../../ListePageConception';
import CtrlModifierOption from './CtrlModifierOption';
import { selectModifierOptionResultat } from './MdlModifierOption';
import ActionEnregistrerOption from './element/ActionEnregistrerOption';
import FormulaireOption from './element/FormulaireOption';

const ViewModifierOption = () => {
    const resultat = useSelector(selectModifierOptionResultat);
    const { execute, success } = useExecute(resultat);
    const [form] = Form.useForm();

    useEffect(() => {
        execute(CtrlModifierOption.initModificationOption);
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
                        <ActionEnregistrerOption form={form} />
                        <ActionUcRetourConsulter nom={ActionConception.UcModifierOption.RETOUR_CONSULTER_OPTION} page={PageConsulterOption} />
                    </BlocAction>
                </CadreBas>
            </Bloc>
        </Section>
    );
};

export default ViewModifierOption;