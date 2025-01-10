import { ModuleDefinition } from 'waxant';
import { I18nIdentification } from './I18nIdentification';
import ListePageIdentification, { PageChercherIdentification } from './ListePageIdentification';
import ReducerIdentification from './ReducerIdentification';

const ModuleIdentification = (): ModuleDefinition => {
    return {
        key: 'ModuleIdentification',
        mapI18n: I18nIdentification,
        listePage: ListePageIdentification,
        reducer: ReducerIdentification,
        index: PageChercherIdentification,
    };
};
export default ModuleIdentification;