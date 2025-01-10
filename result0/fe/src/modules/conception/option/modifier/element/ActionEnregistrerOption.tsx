import { useEffect } from 'react';
import { useSelector } from 'react-redux';
import { ActionUcEnregistrer, useExecute, useGoToPage } from 'waxant';
import { ActionConception } from '../../../ActionConception';
import { PageConsulterOption } from '../../../ListePageConception';
import CtrlModifierOption from '../CtrlModifierOption';
import { selectModifierOptionResultat } from '../MdlModifierOption';

const ActionEnregistrerOption = ({ form }) => {
    const goToPage = useGoToPage();
    const resultat = useSelector(selectModifierOptionResultat);
    const { execute, success, rid } = useExecute(resultat);

    const enregistrerOption = () => {
        execute(CtrlModifierOption.enregistrerOption, { form });
    };

    useEffect(() => {
        if (success) {
            goToPage(PageConsulterOption, resultat);
        }
    }, [success]);
    //
    return (
        <ActionUcEnregistrer nom={ActionConception.UcModifierOption.ENREGISTRER_OPTION} action={enregistrerOption} rid={rid} />
    );
};

export default ActionEnregistrerOption;