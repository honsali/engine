import { ModuleDefinition } from 'waxant';
import { I18nExecution } from './I18nExecution';
import ListePageExecution, { PageListerQuestionnaire } from './ListePageExecution';
import ReducerExecution from './ReducerExecution';

const ModuleExecution = (): ModuleDefinition => {
    return {
        key: 'ModuleExecution',
        mapI18n: I18nExecution,
        listePage: ListePageExecution,
        reducer: ReducerExecution,
        index: PageListerQuestionnaire,
    };
};
export default ModuleExecution;