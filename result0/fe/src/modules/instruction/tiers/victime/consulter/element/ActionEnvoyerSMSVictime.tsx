import { MailOutlined } from '@ant-design/icons';
import { ActionUcNormale, useExecute } from 'waxant';
import { ActionTiers } from '../../../ActionTiers';
import CtrlConsulterVictime from '../CtrlConsulterVictime';

const ActionEnvoyerSMSVictime = () => {
    const { execute } = useExecute();

    const envoyerSMSVictime = () => {
        execute(CtrlConsulterVictime.envoyerSMSVictime);
    };
    //
    return (
        <ActionUcNormale nom={ActionTiers.UcConsulterVictime.ENVOYER_SMS_VICTIME} action={envoyerSMSVictime} icone={<MailOutlined />} />
    );
};

export default ActionEnvoyerSMSVictime;