import { useEffect } from 'react';
import { useSelector } from 'react-redux';
import { ActionUcEnregistrer, useExecute, useGoToPage } from 'waxant';
import { ActionConception } from '../../../ActionConception';
import { PageConsulterRubrique } from '../../../ListePageConception';
import CtrlModifierRubrique from '../CtrlModifierRubrique';
import { selectModifierRubriqueResultat } from '../MdlModifierRubrique';

const ActionEnregistrerRubrique = ({ form }) => {
    const goToPage = useGoToPage();
    const resultat = useSelector(selectModifierRubriqueResultat);
    const { execute, success, rid } = useExecute(resultat);

    const enregistrerRubrique = () => {
        execute(CtrlModifierRubrique.enregistrerRubrique, { form });
    };

    useEffect(() => {
        if (success) {
            goToPage(PageConsulterRubrique, resultat);
        }
    }, [success]);
    //
    return (
        <ActionUcEnregistrer nom={ActionConception.UcModifierRubrique.ENREGISTRER_RUBRIQUE} action={enregistrerRubrique} rid={rid} />
    );
};

export default ActionEnregistrerRubrique;