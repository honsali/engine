
import ViewConsulterTransition from 'modules/modules/conception/transition/consulter/ViewConsulterTransition';
import ViewCreerVictime from 'modules/modules/instruction/tiers/victime/creer/ViewCreerVictime';
import ViewModifierRubrique from 'modules/modules/conception/rubrique/modifier/ViewModifierRubrique';
import ViewConsulterEntiteAdverse from 'modules/modules/instruction/tiers/entiteAdverse/consulter/ViewConsulterEntiteAdverse';
import ViewModifierVictime from 'modules/modules/instruction/tiers/victime/modifier/ViewModifierVictime';
import ViewCreerQuestion from 'modules/modules/conception/question/creer/ViewCreerQuestion';
import ViewConsulterQuestionnaire from 'modules/modules/execution/questionnaire/consulter/ViewConsulterQuestionnaire';
import ViewModifierEntiteAdverse from 'modules/modules/instruction/tiers/entiteAdverse/modifier/ViewModifierEntiteAdverse';
import ViewModifierQuestion from 'modules/modules/conception/question/modifier/ViewModifierQuestion';
import ViewCreerAyantDroit from 'modules/modules/instruction/tiers/ayantDroit/creer/ViewCreerAyantDroit';
import ViewCreerTransition from 'modules/modules/conception/transition/creer/ViewCreerTransition';
import ViewConsulterVersionModeleQuestionnaire from 'modules/modules/conception/versionModeleQuestionnaire/consulter/ViewConsulterVersionModeleQuestionnaire';
import ViewModifierReponse from 'modules/modules/execution/reponse/modifier/ViewModifierReponse';
import ViewConsulterAyantDroit from 'modules/modules/instruction/tiers/ayantDroit/consulter/ViewConsulterAyantDroit';
import ViewConsulterOption from 'modules/modules/conception/option/consulter/ViewConsulterOption';
import ViewModifierVersionModeleQuestionnaire from 'modules/modules/conception/versionModeleQuestionnaire/modifier/ViewModifierVersionModeleQuestionnaire';
import ViewListerEntiteAdverse from 'modules/modules/instruction/tiers/entiteAdverse/lister/ViewListerEntiteAdverse';
import ViewConsulterRubrique from 'modules/modules/conception/rubrique/consulter/ViewConsulterRubrique';
import ViewCreerModeleQuestionnaire from 'modules/modules/conception/modeleQuestionnaire/creer/ViewCreerModeleQuestionnaire';
import ViewListerQuestionnaire from 'modules/modules/execution/questionnaire/lister/ViewListerQuestionnaire';
import ViewCreerQuestionnaire from 'modules/modules/execution/questionnaire/creer/ViewCreerQuestionnaire';
import ViewModifierAyantDroit from 'modules/modules/instruction/tiers/ayantDroit/modifier/ViewModifierAyantDroit';
import ViewListerModeleQuestionnaire from 'modules/modules/conception/modeleQuestionnaire/lister/ViewListerModeleQuestionnaire';
import ViewCreerOption from 'modules/modules/conception/option/creer/ViewCreerOption';
import ViewConsulterVictime from 'modules/modules/instruction/tiers/victime/consulter/ViewConsulterVictime';
import ViewConsulterQuestion from 'modules/modules/conception/question/consulter/ViewConsulterQuestion';
import ViewModifierTransition from 'modules/modules/conception/transition/modifier/ViewModifierTransition';
import ViewModifierOption from 'modules/modules/conception/option/modifier/ViewModifierOption';
import ViewListerTiers from 'modules/modules/instruction/tiers/lister/ViewListerTiers';
import ViewChercherIdentification from 'modules/modules/identification/chercher/ViewChercherIdentification';
import ViewCreerRubrique from 'modules/modules/conception/rubrique/creer/ViewCreerRubrique';
import ViewListerVictime from 'modules/modules/instruction/tiers/victime/lister/ViewListerVictime';
import ViewCreerEntiteAdverse from 'modules/modules/instruction/tiers/entiteAdverse/creer/ViewCreerEntiteAdverse';
import { Suspense } from 'react';
import { Route } from 'react-router';
import AccueilAdmin from './home';

const PageAccueilAdmin = (props) => (
    <Suspense fallback="">
        <AccueilAdmin {...props} />
    </Suspense>
);
const PageConsulterTransition = (props) => (
    <Suspense fallback="">
        <ViewConsulterTransition {...props} />
    </Suspense>
);
const PageCreerVictime = (props) => (
    <Suspense fallback="">
        <ViewCreerVictime {...props} />
    </Suspense>
);
const PageModifierRubrique = (props) => (
    <Suspense fallback="">
        <ViewModifierRubrique {...props} />
    </Suspense>
);
const PageConsulterEntiteAdverse = (props) => (
    <Suspense fallback="">
        <ViewConsulterEntiteAdverse {...props} />
    </Suspense>
);
const PageModifierVictime = (props) => (
    <Suspense fallback="">
        <ViewModifierVictime {...props} />
    </Suspense>
);
const PageCreerQuestion = (props) => (
    <Suspense fallback="">
        <ViewCreerQuestion {...props} />
    </Suspense>
);
const PageConsulterQuestionnaire = (props) => (
    <Suspense fallback="">
        <ViewConsulterQuestionnaire {...props} />
    </Suspense>
);
const PageModifierEntiteAdverse = (props) => (
    <Suspense fallback="">
        <ViewModifierEntiteAdverse {...props} />
    </Suspense>
);
const PageModifierQuestion = (props) => (
    <Suspense fallback="">
        <ViewModifierQuestion {...props} />
    </Suspense>
);
const PageCreerAyantDroit = (props) => (
    <Suspense fallback="">
        <ViewCreerAyantDroit {...props} />
    </Suspense>
);
const PageCreerTransition = (props) => (
    <Suspense fallback="">
        <ViewCreerTransition {...props} />
    </Suspense>
);
const PageConsulterVersionModeleQuestionnaire = (props) => (
    <Suspense fallback="">
        <ViewConsulterVersionModeleQuestionnaire {...props} />
    </Suspense>
);
const PageModifierReponse = (props) => (
    <Suspense fallback="">
        <ViewModifierReponse {...props} />
    </Suspense>
);
const PageConsulterAyantDroit = (props) => (
    <Suspense fallback="">
        <ViewConsulterAyantDroit {...props} />
    </Suspense>
);
const PageConsulterOption = (props) => (
    <Suspense fallback="">
        <ViewConsulterOption {...props} />
    </Suspense>
);
const PageModifierVersionModeleQuestionnaire = (props) => (
    <Suspense fallback="">
        <ViewModifierVersionModeleQuestionnaire {...props} />
    </Suspense>
);
const PageListerEntiteAdverse = (props) => (
    <Suspense fallback="">
        <ViewListerEntiteAdverse {...props} />
    </Suspense>
);
const PageConsulterRubrique = (props) => (
    <Suspense fallback="">
        <ViewConsulterRubrique {...props} />
    </Suspense>
);
const PageCreerModeleQuestionnaire = (props) => (
    <Suspense fallback="">
        <ViewCreerModeleQuestionnaire {...props} />
    </Suspense>
);
const PageListerQuestionnaire = (props) => (
    <Suspense fallback="">
        <ViewListerQuestionnaire {...props} />
    </Suspense>
);
const PageCreerQuestionnaire = (props) => (
    <Suspense fallback="">
        <ViewCreerQuestionnaire {...props} />
    </Suspense>
);
const PageModifierAyantDroit = (props) => (
    <Suspense fallback="">
        <ViewModifierAyantDroit {...props} />
    </Suspense>
);
const PageListerModeleQuestionnaire = (props) => (
    <Suspense fallback="">
        <ViewListerModeleQuestionnaire {...props} />
    </Suspense>
);
const PageCreerOption = (props) => (
    <Suspense fallback="">
        <ViewCreerOption {...props} />
    </Suspense>
);
const PageConsulterVictime = (props) => (
    <Suspense fallback="">
        <ViewConsulterVictime {...props} />
    </Suspense>
);
const PageConsulterQuestion = (props) => (
    <Suspense fallback="">
        <ViewConsulterQuestion {...props} />
    </Suspense>
);
const PageModifierTransition = (props) => (
    <Suspense fallback="">
        <ViewModifierTransition {...props} />
    </Suspense>
);
const PageModifierOption = (props) => (
    <Suspense fallback="">
        <ViewModifierOption {...props} />
    </Suspense>
);
const PageListerTiers = (props) => (
    <Suspense fallback="">
        <ViewListerTiers {...props} />
    </Suspense>
);
const PageChercherIdentification = (props) => (
    <Suspense fallback="">
        <ViewChercherIdentification {...props} />
    </Suspense>
);
const PageCreerRubrique = (props) => (
    <Suspense fallback="">
        <ViewCreerRubrique {...props} />
    </Suspense>
);
const PageListerVictime = (props) => (
    <Suspense fallback="">
        <ViewListerVictime {...props} />
    </Suspense>
);
const PageCreerEntiteAdverse = (props) => (
    <Suspense fallback="">
        <ViewCreerEntiteAdverse {...props} />
    </Suspense>
);
const RoutesAdmin = () => {
    const map = [];
    map.push(<Route key="100" index element={<PageAccueilAdmin />} />);
    map.push(<Route key="101" path="/accueil" element={<PageAccueilAdmin />} />);
    map.push(<Route key="103" path="/null" element={<PageConsulterTransition />} />);
    map.push(<Route key="104" path="/null" element={<PageCreerVictime />} />);
    map.push(<Route key="105" path="/null" element={<PageModifierRubrique />} />);
    map.push(<Route key="106" path="/null" element={<PageConsulterEntiteAdverse />} />);
    map.push(<Route key="107" path="/null" element={<PageModifierVictime />} />);
    map.push(<Route key="108" path="/null" element={<PageCreerQuestion />} />);
    map.push(<Route key="109" path="/null" element={<PageConsulterQuestionnaire />} />);
    map.push(<Route key="1010" path="/null" element={<PageModifierEntiteAdverse />} />);
    map.push(<Route key="1011" path="/null" element={<PageModifierQuestion />} />);
    map.push(<Route key="1012" path="/null" element={<PageCreerAyantDroit />} />);
    map.push(<Route key="1013" path="/null" element={<PageCreerTransition />} />);
    map.push(<Route key="1014" path="/null" element={<PageConsulterVersionModeleQuestionnaire />} />);
    map.push(<Route key="1015" path="/null" element={<PageModifierReponse />} />);
    map.push(<Route key="1016" path="/null" element={<PageConsulterAyantDroit />} />);
    map.push(<Route key="1017" path="/null" element={<PageConsulterOption />} />);
    map.push(<Route key="1018" path="/null" element={<PageModifierVersionModeleQuestionnaire />} />);
    map.push(<Route key="1019" path="/null" element={<PageListerEntiteAdverse />} />);
    map.push(<Route key="1020" path="/null" element={<PageConsulterRubrique />} />);
    map.push(<Route key="1021" path="/null" element={<PageCreerModeleQuestionnaire />} />);
    map.push(<Route key="1022" path="/null" element={<PageListerQuestionnaire />} />);
    map.push(<Route key="1023" path="/null" element={<PageCreerQuestionnaire />} />);
    map.push(<Route key="1024" path="/null" element={<PageModifierAyantDroit />} />);
    map.push(<Route key="1025" path="/null" element={<PageListerModeleQuestionnaire />} />);
    map.push(<Route key="1026" path="/null" element={<PageCreerOption />} />);
    map.push(<Route key="1027" path="/null" element={<PageConsulterVictime />} />);
    map.push(<Route key="1028" path="/null" element={<PageConsulterQuestion />} />);
    map.push(<Route key="1029" path="/null" element={<PageModifierTransition />} />);
    map.push(<Route key="1030" path="/null" element={<PageModifierOption />} />);
    map.push(<Route key="1031" path="/null" element={<PageListerTiers />} />);
    map.push(<Route key="1032" path="/null" element={<PageChercherIdentification />} />);
    map.push(<Route key="1033" path="/null" element={<PageCreerRubrique />} />);
    map.push(<Route key="1034" path="/null" element={<PageListerVictime />} />);
    map.push(<Route key="1035" path="/null" element={<PageCreerEntiteAdverse />} />);
    return map;
};
export default RoutesAdmin;