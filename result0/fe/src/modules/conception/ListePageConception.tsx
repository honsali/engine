import IconeMenuCarre from 'commun/composants/IconeMenuCarre';
import { ContexteeViewProvider, PageDefinition } from 'waxant';
import ViewCreerModeleQuestionnaire from './modeleQuestionnaire/creer/ViewCreerModeleQuestionnaire';
import ViewListerModeleQuestionnaire from './modeleQuestionnaire/lister/ViewListerModeleQuestionnaire';
import ViewConsulterOption from './option/consulter/ViewConsulterOption';
import ViewCreerOption from './option/creer/ViewCreerOption';
import ViewModifierOption from './option/modifier/ViewModifierOption';
import ViewConsulterQuestion from './question/consulter/ViewConsulterQuestion';
import ViewCreerQuestion from './question/creer/ViewCreerQuestion';
import ViewModifierQuestion from './question/modifier/ViewModifierQuestion';
import ViewConsulterRubrique from './rubrique/consulter/ViewConsulterRubrique';
import ViewCreerRubrique from './rubrique/creer/ViewCreerRubrique';
import ViewModifierRubrique from './rubrique/modifier/ViewModifierRubrique';
import ViewConsulterTransition from './transition/consulter/ViewConsulterTransition';
import ViewCreerTransition from './transition/creer/ViewCreerTransition';
import ViewModifierTransition from './transition/modifier/ViewModifierTransition';
import ViewConsulterVersionModeleQuestionnaire from './versionModeleQuestionnaire/consulter/ViewConsulterVersionModeleQuestionnaire';
import ViewModifierVersionModeleQuestionnaire from './versionModeleQuestionnaire/modifier/ViewModifierVersionModeleQuestionnaire';

export const PageConsulterOption: PageDefinition = {
    key: 'PageConsulterOption',
    path: '/conception/option/consulter',
    toPath: (args) => '/conception/option/consulter',
    icone: <IconeMenuCarre />,
    view: (
        <ContexteeViewProvider uc="UcConsulterOption">
            <ViewConsulterOption />
        </ContexteeViewProvider>
    ),
};

export const PageCreerModeleQuestionnaire: PageDefinition = {
    key: 'PageCreerModeleQuestionnaire',
    path: '/conception/modeleQuestionnaire/creer',
    toPath: (args) => '/conception/modeleQuestionnaire/creer',
    icone: <IconeMenuCarre />,
    view: (
        <ContexteeViewProvider uc="UcCreerModeleQuestionnaire">
            <ViewCreerModeleQuestionnaire />
        </ContexteeViewProvider>
    ),
};

export const PageConsulterQuestion: PageDefinition = {
    key: 'PageConsulterQuestion',
    path: '/conception/question/consulter',
    toPath: (args) => '/conception/question/consulter',
    icone: <IconeMenuCarre />,
    view: (
        <ContexteeViewProvider uc="UcConsulterQuestion">
            <ViewConsulterQuestion />
        </ContexteeViewProvider>
    ),
};

export const PageConsulterRubrique: PageDefinition = {
    key: 'PageConsulterRubrique',
    path: '/conception/rubrique/consulter',
    toPath: (args) => '/conception/rubrique/consulter',
    icone: <IconeMenuCarre />,
    view: (
        <ContexteeViewProvider uc="UcConsulterRubrique">
            <ViewConsulterRubrique />
        </ContexteeViewProvider>
    ),
};

export const PageCreerOption: PageDefinition = {
    key: 'PageCreerOption',
    path: '/conception/option/creer',
    toPath: (args) => '/conception/option/creer',
    icone: <IconeMenuCarre />,
    view: (
        <ContexteeViewProvider uc="UcCreerOption">
            <ViewCreerOption />
        </ContexteeViewProvider>
    ),
};

export const PageConsulterTransition: PageDefinition = {
    key: 'PageConsulterTransition',
    path: '/conception/transition/consulter',
    toPath: (args) => '/conception/transition/consulter',
    icone: <IconeMenuCarre />,
    view: (
        <ContexteeViewProvider uc="UcConsulterTransition">
            <ViewConsulterTransition />
        </ContexteeViewProvider>
    ),
};

export const PageCreerQuestion: PageDefinition = {
    key: 'PageCreerQuestion',
    path: '/conception/question/creer',
    toPath: (args) => '/conception/question/creer',
    icone: <IconeMenuCarre />,
    view: (
        <ContexteeViewProvider uc="UcCreerQuestion">
            <ViewCreerQuestion />
        </ContexteeViewProvider>
    ),
};

export const PageCreerRubrique: PageDefinition = {
    key: 'PageCreerRubrique',
    path: '/conception/rubrique/creer',
    toPath: (args) => '/conception/rubrique/creer',
    icone: <IconeMenuCarre />,
    view: (
        <ContexteeViewProvider uc="UcCreerRubrique">
            <ViewCreerRubrique />
        </ContexteeViewProvider>
    ),
};

export const PageConsulterVersionModeleQuestionnaire: PageDefinition = {
    key: 'PageConsulterVersionModeleQuestionnaire',
    path: '/conception/versionModeleQuestionnaire/consulter',
    toPath: (args) => '/conception/versionModeleQuestionnaire/consulter',
    icone: <IconeMenuCarre />,
    view: (
        <ContexteeViewProvider uc="UcConsulterVersionModeleQuestionnaire">
            <ViewConsulterVersionModeleQuestionnaire />
        </ContexteeViewProvider>
    ),
};

export const PageCreerTransition: PageDefinition = {
    key: 'PageCreerTransition',
    path: '/conception/transition/creer',
    toPath: (args) => '/conception/transition/creer',
    icone: <IconeMenuCarre />,
    view: (
        <ContexteeViewProvider uc="UcCreerTransition">
            <ViewCreerTransition />
        </ContexteeViewProvider>
    ),
};

export const PageListerModeleQuestionnaire: PageDefinition = {
    key: 'PageListerModeleQuestionnaire',
    path: '/conception/modeleQuestionnaire/lister',
    toPath: (args) => '/conception/modeleQuestionnaire/lister',
    icone: <IconeMenuCarre />,
    view: (
        <ContexteeViewProvider uc="UcListerModeleQuestionnaire">
            <ViewListerModeleQuestionnaire />
        </ContexteeViewProvider>
    ),
};

export const PageModifierOption: PageDefinition = {
    key: 'PageModifierOption',
    path: '/conception/option/modifier',
    toPath: (args) => '/conception/option/modifier',
    icone: <IconeMenuCarre />,
    view: (
        <ContexteeViewProvider uc="UcModifierOption">
            <ViewModifierOption />
        </ContexteeViewProvider>
    ),
};

export const PageModifierQuestion: PageDefinition = {
    key: 'PageModifierQuestion',
    path: '/conception/question/modifier',
    toPath: (args) => '/conception/question/modifier',
    icone: <IconeMenuCarre />,
    view: (
        <ContexteeViewProvider uc="UcModifierQuestion">
            <ViewModifierQuestion />
        </ContexteeViewProvider>
    ),
};

export const PageModifierRubrique: PageDefinition = {
    key: 'PageModifierRubrique',
    path: '/conception/rubrique/modifier',
    toPath: (args) => '/conception/rubrique/modifier',
    icone: <IconeMenuCarre />,
    view: (
        <ContexteeViewProvider uc="UcModifierRubrique">
            <ViewModifierRubrique />
        </ContexteeViewProvider>
    ),
};

export const PageModifierTransition: PageDefinition = {
    key: 'PageModifierTransition',
    path: '/conception/transition/modifier',
    toPath: (args) => '/conception/transition/modifier',
    icone: <IconeMenuCarre />,
    view: (
        <ContexteeViewProvider uc="UcModifierTransition">
            <ViewModifierTransition />
        </ContexteeViewProvider>
    ),
};

export const PageModifierVersionModeleQuestionnaire: PageDefinition = {
    key: 'PageModifierVersionModeleQuestionnaire',
    path: '/conception/versionModeleQuestionnaire/modifier',
    toPath: (args) => '/conception/versionModeleQuestionnaire/modifier',
    icone: <IconeMenuCarre />,
    view: (
        <ContexteeViewProvider uc="UcModifierVersionModeleQuestionnaire">
            <ViewModifierVersionModeleQuestionnaire />
        </ContexteeViewProvider>
    ),
};

const ListePageConception = [
    PageConsulterOption, //
    PageCreerModeleQuestionnaire,
    PageConsulterQuestion,
    PageConsulterRubrique,
    PageCreerOption,
    PageConsulterTransition,
    PageCreerQuestion,
    PageCreerRubrique,
    PageConsulterVersionModeleQuestionnaire,
    PageCreerTransition,
    PageListerModeleQuestionnaire,
    PageModifierOption,
    PageModifierQuestion,
    PageModifierRubrique,
    PageModifierTransition,
    PageModifierVersionModeleQuestionnaire,
];
export default ListePageConception;