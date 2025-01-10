import { ModuleDefinition } from 'waxant';
import { I18nConception } from './I18nConception';
import ListePageConception, { PageListerModeleQuestionnaire } from './ListePageConception';
import ReducerConception from './ReducerConception';

const ModuleConception = (): ModuleDefinition => {
    return {
        key: 'ModuleConception',
        mapI18n: I18nConception,
        listePage: ListePageConception,
        reducer: ReducerConception,
        index: PageListerModeleQuestionnaire,
    };
};
export default ModuleConception;