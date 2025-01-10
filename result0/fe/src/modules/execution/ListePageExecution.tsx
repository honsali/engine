import IconeMenuCarre from 'commun/composants/IconeMenuCarre';
import { ContexteeViewProvider, PageDefinition } from 'waxant';
import ViewConsulterQuestionnaire from './questionnaire/consulter/ViewConsulterQuestionnaire';
import ViewCreerQuestionnaire from './questionnaire/creer/ViewCreerQuestionnaire';
import ViewListerQuestionnaire from './questionnaire/lister/ViewListerQuestionnaire';
import ViewModifierReponse from './reponse/modifier/ViewModifierReponse';

export const PageConsulterQuestionnaire: PageDefinition = {
    key: 'PageConsulterQuestionnaire',
    path: '/execution/questionnaire/consulter',
    toPath: (args) => '/execution/questionnaire/consulter',
    icone: <IconeMenuCarre />,
    view: (
        <ContexteeViewProvider uc="UcConsulterQuestionnaire">
            <ViewConsulterQuestionnaire />
        </ContexteeViewProvider>
    ),
};

export const PageCreerQuestionnaire: PageDefinition = {
    key: 'PageCreerQuestionnaire',
    path: '/execution/questionnaire/creer',
    toPath: (args) => '/execution/questionnaire/creer',
    icone: <IconeMenuCarre />,
    view: (
        <ContexteeViewProvider uc="UcCreerQuestionnaire">
            <ViewCreerQuestionnaire />
        </ContexteeViewProvider>
    ),
};

export const PageListerQuestionnaire: PageDefinition = {
    key: 'PageListerQuestionnaire',
    path: '/execution/questionnaire/lister',
    toPath: (args) => '/execution/questionnaire/lister',
    icone: <IconeMenuCarre />,
    view: (
        <ContexteeViewProvider uc="UcListerQuestionnaire">
            <ViewListerQuestionnaire />
        </ContexteeViewProvider>
    ),
};

export const PageModifierReponse: PageDefinition = {
    key: 'PageModifierReponse',
    path: '/execution/reponse/modifier',
    toPath: (args) => '/execution/reponse/modifier',
    icone: <IconeMenuCarre />,
    view: (
        <ContexteeViewProvider uc="UcModifierReponse">
            <ViewModifierReponse />
        </ContexteeViewProvider>
    ),
};

const ListePageExecution = [
    PageConsulterQuestionnaire, //
    PageCreerQuestionnaire,
    PageListerQuestionnaire,
    PageModifierReponse,
];
export default ListePageExecution;