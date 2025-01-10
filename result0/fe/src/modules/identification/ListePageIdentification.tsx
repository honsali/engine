import IconeMenuCarre from 'commun/composants/IconeMenuCarre';
import { ContexteeViewProvider, PageDefinition } from 'waxant';
import ViewChercherIdentification from './chercher/ViewChercherIdentification';

export const PageChercherIdentification: PageDefinition = {
    key: 'PageChercherIdentification',
    path: '/identification/identification/chercher',
    toPath: (args) => '/identification/identification/chercher',
    icone: <IconeMenuCarre />,
    view: (
        <ContexteeViewProvider uc="UcChercherIdentification">
            <ViewChercherIdentification />
        </ContexteeViewProvider>
    ),
};

const ListePageIdentification = [
    PageChercherIdentification, //
];
export default ListePageIdentification;