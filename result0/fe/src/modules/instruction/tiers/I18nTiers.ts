import { enteteConfirmation, messageSuccess, titreConfirmation } from 'waxant';
import { ActionTiers } from './ActionTiers';

export const I18nTiers = {
    PageConsulterAyantDroit: 'Consulter Ayant Droit',
    'UcConsulterAyantDroit.titre': 'Consulter Ayant Droit',
    [ActionTiers.UcConsulterAyantDroit.MODIFIER_AYANT_DROIT]: 'Modifier',
    [ActionTiers.UcConsulterAyantDroit.RETOUR_LISTE_AYANT_DROIT]: 'Retour Liste',
    PageCreerAyantDroit: 'Créer Ayant Droit',
    'UcCreerAyantDroit.titre': 'Créer Ayant Droit',
    [ActionTiers.UcCreerAyantDroit.CREER_AYANT_DROIT]: 'Enregistrer',
    [titreConfirmation(ActionTiers.UcCreerAyantDroit.CREER_AYANT_DROIT)]: 'Créer Ayant Droit',
    [enteteConfirmation(ActionTiers.UcCreerAyantDroit.CREER_AYANT_DROIT)]: 'Etes vous sur de vouloir créer cet Ayant Droit',
    [messageSuccess(ActionTiers.UcCreerAyantDroit.CREER_AYANT_DROIT)]: 'Ayant Droit créé avec succès',
    [ActionTiers.UcCreerAyantDroit.RETOUR_LISTE_AYANT_DROIT]: 'Retour Liste',
    PageConsulterEntiteAdverse: 'Consulter Adversaire',
    'UcConsulterEntiteAdverse.titre': 'Consulter Adversaire',
    [ActionTiers.UcConsulterEntiteAdverse.ENVOYER_SMS_ENTITE_ADVERSE]: 'Envoyer SMS',
    [ActionTiers.UcConsulterEntiteAdverse.MODIFIER_ENTITE_ADVERSE]: 'Modifier',
    [ActionTiers.UcConsulterEntiteAdverse.RETOUR_LISTE_ENTITE_ADVERSE]: 'Retour Liste',
    PageCreerEntiteAdverse: 'Créer Adversaire',
    'UcCreerEntiteAdverse.titre': 'Créer Adversaire',
    [ActionTiers.UcCreerEntiteAdverse.CREER_ENTITE_ADVERSE]: 'Enregistrer',
    [titreConfirmation(ActionTiers.UcCreerEntiteAdverse.CREER_ENTITE_ADVERSE)]: 'Créer Adversaire',
    [enteteConfirmation(ActionTiers.UcCreerEntiteAdverse.CREER_ENTITE_ADVERSE)]: 'Etes vous sur de vouloir créer cet Adversaire',
    [messageSuccess(ActionTiers.UcCreerEntiteAdverse.CREER_ENTITE_ADVERSE)]: 'Adversaire créé avec succès',
    [ActionTiers.UcCreerEntiteAdverse.RETOUR_LISTE_ENTITE_ADVERSE]: 'Retour Liste',
    PageModifierAyantDroit: 'Modifier Ayant Droit',
    'UcModifierAyantDroit.titre': 'Modifier Ayant Droit',
    [ActionTiers.UcModifierAyantDroit.ENREGISTRER_AYANT_DROIT]: 'Enregistrer',
    [titreConfirmation(ActionTiers.UcModifierAyantDroit.ENREGISTRER_AYANT_DROIT)]: 'Enregistrer Ayant Droit',
    [enteteConfirmation(ActionTiers.UcModifierAyantDroit.ENREGISTRER_AYANT_DROIT)]: 'Etes vous sur de vouloir enregistrer cet Ayant Droit',
    [messageSuccess(ActionTiers.UcModifierAyantDroit.ENREGISTRER_AYANT_DROIT)]: 'Ayant Droit enregistré avec succès',
    [ActionTiers.UcModifierAyantDroit.RETOUR_CONSULTER_AYANT_DROIT]: 'Retour',
    PageListerEntiteAdverse: 'Liste Adversaires',
    'UcListerEntiteAdverse.titre': 'Liste Adversaires',
    [ActionTiers.UcListerEntiteAdverse.AJOUTER_ENTITE_ADVERSE]: 'Nouvel Adversaire',
    PageModifierEntiteAdverse: 'Modifier Adversaire',
    'UcModifierEntiteAdverse.titre': 'Modifier Adversaire',
    [ActionTiers.UcModifierEntiteAdverse.ENREGISTRER_ENTITE_ADVERSE]: 'Enregistrer',
    [titreConfirmation(ActionTiers.UcModifierEntiteAdverse.ENREGISTRER_ENTITE_ADVERSE)]: 'Enregistrer Adversaire',
    [enteteConfirmation(ActionTiers.UcModifierEntiteAdverse.ENREGISTRER_ENTITE_ADVERSE)]: 'Etes vous sur de vouloir enregistrer cet Adversaire',
    [messageSuccess(ActionTiers.UcModifierEntiteAdverse.ENREGISTRER_ENTITE_ADVERSE)]: 'Adversaire enregistré avec succès',
    [ActionTiers.UcModifierEntiteAdverse.RETOUR_CONSULTER_ENTITE_ADVERSE]: 'Retour',
    PageConsulterVictime: 'Consulter Victime',
    'UcConsulterVictime.titre': 'Consulter Victime',
    [ActionTiers.UcConsulterVictime.ENVOYER_SMS_VICTIME]: 'Envoyer SMS',
    [ActionTiers.UcConsulterVictime.MODIFIER_VICTIME]: 'Modifier',
    [ActionTiers.UcConsulterVictime.RETOUR_LISTE_VICTIME]: 'Retour Liste',
    PageCreerVictime: 'Créer Victime',
    'UcCreerVictime.titre': 'Créer Victime',
    [ActionTiers.UcCreerVictime.CREER_VICTIME]: 'Enregistrer',
    [titreConfirmation(ActionTiers.UcCreerVictime.CREER_VICTIME)]: 'Créer Victime',
    [enteteConfirmation(ActionTiers.UcCreerVictime.CREER_VICTIME)]: 'Etes vous sur de vouloir créer cette Victime',
    [messageSuccess(ActionTiers.UcCreerVictime.CREER_VICTIME)]: 'Victime créée avec succès',
    [ActionTiers.UcCreerVictime.RETOUR_LISTE_VICTIME]: 'Retour Liste',
    PageListerTiers: 'Liste Tierss',
    'UcListerTiers.titre': 'Liste Tierss',
    PageListerVictime: 'Liste Victimes',
    'UcListerVictime.titre': 'Liste Victimes',
    [ActionTiers.UcListerVictime.AJOUTER_AYANT_DROIT]: 'Nouvel Ayant Droit',
    [ActionTiers.UcListerVictime.AJOUTER_VICTIME]: 'Nouvelle Victime',
    PageModifierVictime: 'Modifier Victime',
    'UcModifierVictime.titre': 'Modifier Victime',
    [ActionTiers.UcModifierVictime.ENREGISTRER_VICTIME]: 'Enregistrer',
    [titreConfirmation(ActionTiers.UcModifierVictime.ENREGISTRER_VICTIME)]: 'Enregistrer Victime',
    [enteteConfirmation(ActionTiers.UcModifierVictime.ENREGISTRER_VICTIME)]: 'Etes vous sur de vouloir enregistrer cette Victime',
    [messageSuccess(ActionTiers.UcModifierVictime.ENREGISTRER_VICTIME)]: 'Victime enregistrée avec succès',
    [ActionTiers.UcModifierVictime.RETOUR_CONSULTER_VICTIME]: 'Retour',
    'UcConsulterEntiteAdverse.infoContrat': 'Info Contrat',
    'UcConsulterEntiteAdverse.infoPersonneMorale': 'Info Personne Morale',
    'UcConsulterEntiteAdverse.infoPersonnePhysique': 'Info Personne Physique',
    'UcCreerEntiteAdverse.infoContrat': 'Info Contrat',
    'UcCreerEntiteAdverse.infoPersonneMorale': 'Info Personne Morale',
    'UcCreerEntiteAdverse.infoPersonnePhysique': 'Info Personne Physique',
    'UcListerVictime.listeAyantDroit': 'Liste Ayant Droit',
    'UcModifierEntiteAdverse.infoContrat': 'Info Contrat',
    'UcModifierEntiteAdverse.infoPersonneMorale': 'Info Personne Morale',
    'UcModifierEntiteAdverse.infoPersonnePhysique': 'Info Personne Physique',
    'aucun.ayantDroit': 'Aucun Ayant Droit',
    'aucun.entiteAdverse': 'Aucun Adversaire',
    'aucun.victime': 'Aucune Victime',
};