import { EyeOutlined } from '@ant-design/icons';
import {ActionUcNormale} from 'waxant';
import { ActionConception } from '../../../ActionConception';

const ActionExecuterQuestion = () => {
    //
    return (
        <ActionUcNormale nom={ActionConception.UcConsulterQuestion.EXECUTER_QUESTION} icone={<EyeOutlined />} />
    );
};

export default ActionExecuterQuestion;