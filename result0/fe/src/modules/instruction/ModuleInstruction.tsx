import { ModuleDefinition } from 'waxant';
import { I18nInstruction } from './I18nInstruction';
import ListePageInstruction, { PageConsulterFicheEtude } from './ListePageInstruction';
import ReducerInstruction from './ReducerInstruction';

const ModuleInstruction = (): ModuleDefinition => {
    return {
        key: 'ModuleInstruction',
        mapI18n: I18nInstruction,
        listePage: ListePageInstruction,
        reducer: ReducerInstruction,
        index: PageConsulterFicheEtude,
    };
};
export default ModuleInstruction;