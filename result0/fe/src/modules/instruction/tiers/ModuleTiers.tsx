import { ModuleDefinition } from 'waxant';
import { I18nTiers } from './I18nTiers';
import ListePageTiers, { PageListerTiers } from './ListePageTiers';
import ReducerTiers from './ReducerTiers';

const ModuleTiers = (): ModuleDefinition => {
    return {
        key: 'ModuleTiers',
        mapI18n: I18nTiers,
        listePage: ListePageTiers,
        reducer: ReducerTiers,
        index: PageListerTiers,
    };
};
export default ModuleTiers;