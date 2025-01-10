import { Form } from 'antd';
import { useEffect } from 'react';
import { useSelector } from 'react-redux';
import { ActionUcRetourListe, Bloc, BlocAction, CadreBas, Section, useExecute } from 'waxant';
import { ActionConception } from '../../ActionConception';
import { PageConsulterVersionModeleQuestionnaire } from '../../ListePageConception';
import CtrlCreerRubrique from './CtrlCreerRubrique';
import { selectCreerRubriqueResultat } from './MdlCreerRubrique';
import ActionCreerRubrique from './element/ActionCreerRubrique';
import FormulaireRubrique from './element/FormulaireRubrique';

const ViewCreerRubrique = () => {
    const resultat = useSelector(selectCreerRubriqueResultat);
    const { execute, success } = useExecute(resultat);
    const [form] = Form.useForm();

    useEffect(() => {
        execute(CtrlCreerRubrique.initCreationRubrique);
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
                        <ActionCreerRubrique form={form} />
                        <ActionUcRetourListe nom={ActionConception.UcCreerRubrique.RETOUR_LISTE_RUBRIQUE} page={PageConsulterVersionModeleQuestionnaire} />
                    </BlocAction>
                </CadreBas>
            </Bloc>
        </Section>
    );
};

export default ViewCreerRubrique;