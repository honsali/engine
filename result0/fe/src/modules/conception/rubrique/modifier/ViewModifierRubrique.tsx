import { Form } from 'antd';
import { useEffect } from 'react';
import { useSelector } from 'react-redux';
import { ActionUcRetourConsulter, Bloc, BlocAction, CadreBas, Section, useExecute } from 'waxant';
import { ActionConception } from '../../ActionConception';
import { PageConsulterRubrique } from '../../ListePageConception';
import CtrlModifierRubrique from './CtrlModifierRubrique';
import { selectModifierRubriqueResultat } from './MdlModifierRubrique';
import ActionEnregistrerRubrique from './element/ActionEnregistrerRubrique';
import FormulaireRubrique from './element/FormulaireRubrique';

const ViewModifierRubrique = () => {
    const resultat = useSelector(selectModifierRubriqueResultat);
    const { execute, success } = useExecute(resultat);
    const [form] = Form.useForm();

    useEffect(() => {
        execute(CtrlModifierRubrique.initModificationRubrique);
    }, []);

    useEffect(() => {
        success && form.setFieldsValue(resultat.rubrique);
    }, [success]);
    //
    return (
        <Section>
            <Bloc largeur="800px">
                <CadreBas titre="formulaireRubrique">
                    <FormulaireRubrique form={form} />
                    <BlocAction>
                        <ActionEnregistrerRubrique form={form} />
                        <ActionUcRetourConsulter nom={ActionConception.UcModifierRubrique.RETOUR_CONSULTER_RUBRIQUE} page={PageConsulterRubrique} />
                    </BlocAction>
                </CadreBas>
            </Bloc>
        </Section>
    );
};

export default ViewModifierRubrique;