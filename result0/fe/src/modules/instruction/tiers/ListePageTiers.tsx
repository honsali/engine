import IconeMenuCarre from 'commun/composants/IconeMenuCarre';
import { ContexteeViewProvider, PageDefinition } from 'waxant';
import ViewConsulterAyantDroit from './ayantDroit/consulter/ViewConsulterAyantDroit';
import ViewCreerAyantDroit from './ayantDroit/creer/ViewCreerAyantDroit';
import ViewModifierAyantDroit from './ayantDroit/modifier/ViewModifierAyantDroit';
import ViewConsulterEntiteAdverse from './entiteAdverse/consulter/ViewConsulterEntiteAdverse';
import ViewCreerEntiteAdverse from './entiteAdverse/creer/ViewCreerEntiteAdverse';
import ViewListerEntiteAdverse from './entiteAdverse/lister/ViewListerEntiteAdverse';
import ViewModifierEntiteAdverse from './entiteAdverse/modifier/ViewModifierEntiteAdverse';
import ViewListerTiers from './lister/ViewListerTiers';
import ViewConsulterVictime from './victime/consulter/ViewConsulterVictime';
import ViewCreerVictime from './victime/creer/ViewCreerVictime';
import ViewListerVictime from './victime/lister/ViewListerVictime';
import ViewModifierVictime from './victime/modifier/ViewModifierVictime';

export const PageConsulterAyantDroit: PageDefinition = {
    key: 'PageConsulterAyantDroit',
    path: '/instruction/tiers/ayantDroit/consulter',
    toPath: (args) => '/instruction/tiers/ayantDroit/consulter',
    icone: <IconeMenuCarre />,
    view: (
        <ContexteeViewProvider uc="UcConsulterAyantDroit">
            <ViewConsulterAyantDroit />
        </ContexteeViewProvider>
    ),
};

export const PageCreerAyantDroit: PageDefinition = {
    key: 'PageCreerAyantDroit',
    path: '/instruction/tiers/ayantDroit/creer',
    toPath: (args) => '/instruction/tiers/ayantDroit/creer',
    icone: <IconeMenuCarre />,
    view: (
        <ContexteeViewProvider uc="UcCreerAyantDroit">
            <ViewCreerAyantDroit />
        </ContexteeViewProvider>
    ),
};

export const PageConsulterEntiteAdverse: PageDefinition = {
    key: 'PageConsulterEntiteAdverse',
    path: '/instruction/tiers/entiteAdverse/consulter',
    toPath: (args) => '/instruction/tiers/entiteAdverse/consulter',
    icone: <IconeMenuCarre />,
    view: (
        <ContexteeViewProvider uc="UcConsulterEntiteAdverse">
            <ViewConsulterEntiteAdverse />
        </ContexteeViewProvider>
    ),
};

export const PageCreerEntiteAdverse: PageDefinition = {
    key: 'PageCreerEntiteAdverse',
    path: '/instruction/tiers/entiteAdverse/creer',
    toPath: (args) => '/instruction/tiers/entiteAdverse/creer',
    icone: <IconeMenuCarre />,
    view: (
        <ContexteeViewProvider uc="UcCreerEntiteAdverse">
            <ViewCreerEntiteAdverse />
        </ContexteeViewProvider>
    ),
};

export const PageModifierAyantDroit: PageDefinition = {
    key: 'PageModifierAyantDroit',
    path: '/instruction/tiers/ayantDroit/modifier',
    toPath: (args) => '/instruction/tiers/ayantDroit/modifier',
    icone: <IconeMenuCarre />,
    view: (
        <ContexteeViewProvider uc="UcModifierAyantDroit">
            <ViewModifierAyantDroit />
        </ContexteeViewProvider>
    ),
};

export const PageListerEntiteAdverse: PageDefinition = {
    key: 'PageListerEntiteAdverse',
    path: '/instruction/tiers/entiteAdverse/lister',
    toPath: (args) => '/instruction/tiers/entiteAdverse/lister',
    icone: <IconeMenuCarre />,
    view: (
        <ContexteeViewProvider uc="UcListerEntiteAdverse">
            <ViewListerEntiteAdverse />
        </ContexteeViewProvider>
    ),
};

export const PageModifierEntiteAdverse: PageDefinition = {
    key: 'PageModifierEntiteAdverse',
    path: '/instruction/tiers/entiteAdverse/modifier',
    toPath: (args) => '/instruction/tiers/entiteAdverse/modifier',
    icone: <IconeMenuCarre />,
    view: (
        <ContexteeViewProvider uc="UcModifierEntiteAdverse">
            <ViewModifierEntiteAdverse />
        </ContexteeViewProvider>
    ),
};

export const PageConsulterVictime: PageDefinition = {
    key: 'PageConsulterVictime',
    path: '/instruction/tiers/victime/consulter',
    toPath: (args) => '/instruction/tiers/victime/consulter',
    icone: <IconeMenuCarre />,
    view: (
        <ContexteeViewProvider uc="UcConsulterVictime">
            <ViewConsulterVictime />
        </ContexteeViewProvider>
    ),
};

export const PageCreerVictime: PageDefinition = {
    key: 'PageCreerVictime',
    path: '/instruction/tiers/victime/creer',
    toPath: (args) => '/instruction/tiers/victime/creer',
    icone: <IconeMenuCarre />,
    view: (
        <ContexteeViewProvider uc="UcCreerVictime">
            <ViewCreerVictime />
        </ContexteeViewProvider>
    ),
};

export const PageListerTiers: PageDefinition = {
    key: 'PageListerTiers',
    path: '/instruction/tiers/tiers/lister',
    toPath: (args) => '/instruction/tiers/tiers/lister',
    icone: <IconeMenuCarre />,
    view: (
        <ContexteeViewProvider uc="UcListerTiers">
            <ViewListerTiers />
        </ContexteeViewProvider>
    ),
};

export const PageListerVictime: PageDefinition = {
    key: 'PageListerVictime',
    path: '/instruction/tiers/victime/lister',
    toPath: (args) => '/instruction/tiers/victime/lister',
    icone: <IconeMenuCarre />,
    view: (
        <ContexteeViewProvider uc="UcListerVictime">
            <ViewListerVictime />
        </ContexteeViewProvider>
    ),
};

export const PageModifierVictime: PageDefinition = {
    key: 'PageModifierVictime',
    path: '/instruction/tiers/victime/modifier',
    toPath: (args) => '/instruction/tiers/victime/modifier',
    icone: <IconeMenuCarre />,
    view: (
        <ContexteeViewProvider uc="UcModifierVictime">
            <ViewModifierVictime />
        </ContexteeViewProvider>
    ),
};

const ListePageTiers = [
    PageConsulterAyantDroit, //
    PageCreerAyantDroit,
    PageConsulterEntiteAdverse,
    PageCreerEntiteAdverse,
    PageModifierAyantDroit,
    PageListerEntiteAdverse,
    PageModifierEntiteAdverse,
    PageConsulterVictime,
    PageCreerVictime,
    PageListerTiers,
    PageListerVictime,
    PageModifierVictime,
];
export default ListePageTiers;