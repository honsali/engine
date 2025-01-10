import MdlConsulterAyantDroit from './ayantDroit/consulter/MdlConsulterAyantDroit';
import MdlCreerAyantDroit from './ayantDroit/creer/MdlCreerAyantDroit';
import MdlConsulterEntiteAdverse from './entiteAdverse/consulter/MdlConsulterEntiteAdverse';
import MdlCreerEntiteAdverse from './entiteAdverse/creer/MdlCreerEntiteAdverse';
import MdlModifierAyantDroit from './ayantDroit/modifier/MdlModifierAyantDroit';
import MdlListerEntiteAdverse from './entiteAdverse/lister/MdlListerEntiteAdverse';
import MdlModifierEntiteAdverse from './entiteAdverse/modifier/MdlModifierEntiteAdverse';
import MdlConsulterVictime from './victime/consulter/MdlConsulterVictime';
import MdlCreerVictime from './victime/creer/MdlCreerVictime';
import MdlListerTiers from './lister/MdlListerTiers';
import MdlListerVictime from './victime/lister/MdlListerVictime';
import MdlModifierVictime from './victime/modifier/MdlModifierVictime';

const ReducerTiers = {
    mdlConsulterAyantDroit: MdlConsulterAyantDroit,
    mdlCreerAyantDroit: MdlCreerAyantDroit,
    mdlConsulterEntiteAdverse: MdlConsulterEntiteAdverse,
    mdlCreerEntiteAdverse: MdlCreerEntiteAdverse,
    mdlModifierAyantDroit: MdlModifierAyantDroit,
    mdlListerEntiteAdverse: MdlListerEntiteAdverse,
    mdlModifierEntiteAdverse: MdlModifierEntiteAdverse,
    mdlConsulterVictime: MdlConsulterVictime,
    mdlCreerVictime: MdlCreerVictime,
    mdlListerTiers: MdlListerTiers,
    mdlListerVictime: MdlListerVictime,
    mdlModifierVictime: MdlModifierVictime,
};

export default ReducerTiers;