import { MailOutlined } from '@ant-design/icons';
import { ActionUcNormale, useExecute } from 'waxant';
import { ActionTiers } from '../../../ActionTiers';
import CtrlConsulterEntiteAdverse from '../CtrlConsulterEntiteAdverse';

const ActionEnvoyerSMSEntiteAdverse = () => {
    const { execute } = useExecute();

    const envoyerSMSEntiteAdverse = () => {
        execute(CtrlConsulterEntiteAdverse.envoyerSMSEntiteAdverse);
    };
    //
    return (
        <ActionUcNormale nom={ActionTiers.UcConsulterEntiteAdverse.ENVOYER_SMS_ENTITE_ADVERSE} action={envoyerSMSEntiteAdverse} icone={<MailOutlined />} />
    );
};

export default ActionEnvoyerSMSEntiteAdverse;