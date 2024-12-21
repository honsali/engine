package ma.wafaassurance.sinistre.corpo.workflow;

import lombok.Getter;

public interface Workflow {
        enum Task {

                TASK_OUVERTURE("traiterOuvertureTask", Process.PROC_OUVERTURE, Category.OUVERTURE), //
                TASK_VALIDER_OUVERTURE("validerOuvertureTask", Process.PROC_VALIDER_OUVERTURE, Category.OUVERTURE), //
                TASK_VALIDER_REOUVERTURE("validerReouvertureTask", Process.PROC_VALIDER_REOUVERTURE, Category.OUVERTURE), //
                TASK_ELIGIBLE_TRANSACTION("traiterEligibleTransactionTask", Process.PROC_ELIGIBLE_TRANSACTION, Category.TRANSACTION), //
                TASK_TRANSACTION("traiterTransactionTask", Process.PROC_TRAITER_TRANSACTION, Category.TRANSACTION), //
                TASK_CONTROLLER_DOCUMENT("controllerDocumentTask", Process.PROC_CONTROLLER_DOCUMENT, Category.CONTROLLER_DOCUMENT), //
                TASK_ETUDE_DE_PV("etudeDePvTask", Process.PROC_ETUDE_DE_PV, Category.INSTRUCTION), //
                TASK_APPLIQUER_RESERVE("traiterReserveTask", Process.PROC_TRAITER_RESERVE, Category.INSTRUCTION), //
                TASK_APPLIQUER_RESERVE_SS_DOSSIER("appliquerReserveSsDossierTask", Process.PROC_APPLIQUER_RESERVE_SS_DOSSIER, Category.INSTRUCTION), //
                TASK_SAISIR_PROCEDURE_JUD("saisieProcedureJudTask", Process.PROC_SAISIR_PROCEDURE_JUD, Category.JUDICIAIRE), //
                TASK_EVALUER_RAPPORT_ASSISTANCE("evaluerRapportAssistanceTask", Process.PROC_EVALUER_RAPPORT_ASSISTANCE, Category.EXPERTISE), //
                TASK_VALIDER_RAPPORT_EXPERTSE_JUD("validerRapportExpertiseJudTask", Process.PROC_VALIDER_RAPPORT_EXPERTISE_JUD, Category.EXPERTISE), //
                TASK_SAISIR_JUGEMENT("saisieJugementTask", Process.PROC_SAISIR_JUGEMENT, Category.JUDICIAIRE), //
                TASK_RECOURS("recoursTask", Process.PROC_RECOURS, Category.JUDICIAIRE), //
                TASK_INSTRUIRE_RAPPORT("instruireRapportTask", Process.PROC_INSTRUIRE_RAPPORT, Category.TRANSACTION), //
                TASK_INSTRUIRE_RAPPORT_ASSISTANCE("instruireRapportAssistanceTask", Process.PROC_INSTRUIRE_RAPPORT_ASSISTANCE, Category.JUDICIAIRE), //
                TASK_REGLEMENT_JUDICIAIRE("reglementJudiciaireTask", Process.PROC_REGLEMENT_JUDICIAIRE, Category.REGLEMENT), //
                TASK_REGLEMENT_AMIABLE("reglementAmiableTask", Process.PROC_REGLEMENT_AMIABLE, Category.REGLEMENT), //
                TASK_REGLEMENT_TAXE("reglementTaxeTask", Process.PROC_REGLEMENT_TAXE, Category.REGLEMENT), //
                TASK_REGLEMENT_EXEC_PROVISOIRE("reglementExecProvisoireTask", Process.PROC_REGLEMENT_EXEC_PROVISOIRE, Category.REGLEMENT), //
                TASK_SOUMETTRE_REGLEMENT("soumettreReglementTask", Process.PROC_SOUMETTRE_REGLEMENT, Category.REGLEMENT), //
                TASK_VALIDATION_REGLEMENT("validationReglementTask", Process.PROC_VALIDATION_REGLEMENT, Category.REGLEMENT), //
                TASK_VALIDATION_REGLEMENT_HONORAIRE_MED("validerReglementHonoraireMedTask", Process.PROC_VAL_REG_HONO_MED, Category.REGLEMENT), //
                TASK_VALIDATION_REGLEMENT_HONORAIRE_AVOCAT("validerReglementHonoraireAvTask", Process.PROC_VAL_REG_HONO_AVOCAT, Category.REGLEMENT), //
                TASK_SAISIE_REGLEMENT_HONORAIRE_AVOCAT("saisieReglementHonoraireAvocatTask", Process.PROC_SAISIE_REGLEMENT_HONORAIRE_AVOCAT, Category.REGLEMENT), //
                TASK_SAISIE_REGLEMENT_HONORAIRE_MEDECIN("saisieReglementHonoraireMedecinTask", Process.PROC_SAISIE_REGLEMENT_HONORAIRE_MEDECIN, Category.REGLEMENT), //
                TASK_RECEVOIR_COMPLEMENT_DOCUMENT("recevoirComplementDocumentTask", Process.PROC_RECEVOIR_COMPLEMENT_DOCUMENT, Category.INSTRUCTION), //
                TASK_TRAITER_RAPPORT_EXP_AMIABLE("traiterRapportExpAmiableTask", Process.PROC_TRAITER_RAPPORT_EXP_AMIABLE, Category.EXPERTISE), //
                TASK_VALIDER_OFFRE_TRANSACTION("validerOffreTransactionTask", Process.PROC_VALIDER_OFFRE_TRANSACTION, Category.INSTRUCTION), //
                TASK_REGLER_TRANSACTION("reglerTransactionTask", Process.PROC_REGLER_TRANSACTION, Category.REGLEMENT), //
                TASK_VALIDER_REGLEMENT_TRANSACTION("validerReglementTransactionTask", Process.PROC_VALIDER_REGLEMENT_TRANSACTION, Category.REGLEMENT), //
                TASK_VALIDER_ANNULATION_REGLEMENT("validerAnnulationReglementTask", Process.PROC_VALIDER_ANNULATION_REGLEMENT, Category.REGLEMENT), //
                TASK_VALIDER_ANNULATION_HONORAIRE("validerAnnulationHonoraireTask", Process.PROC_VALIDER_ANNULATION_HONORAIRE, Category.REGLEMENT), //
                TASK_VALIDER_OUVERTURE_MIXTE_IFX("validerOuvertureSinistreMixteTask", Process.PROC_VALIDER_OUVERTURE_SINISTRE_MIXTE, Category.OUVERTURE_MIXTE_IFX);

                public enum Category {
                        CONTROLLER_DOCUMENT, OUVERTURE, OUVERTURE_MIXTE_IFX, INSTRUCTION, NOTIFICATION, JUDICIAIRE, TRANSACTION, EXPERTISE, REGLEMENT
                }

                enum Process {
                        PROC_OUVERTURE("traiterOuverture"), //
                        PROC_VALIDER_OUVERTURE("validerOuverture"), //
                        PROC_VALIDER_REOUVERTURE("validerReouverture"), //
                        PROC_VALIDER_OUVERTURE_SINISTRE_MIXTE("validerOuvertureSinistreMixte"), //
                        PROC_TRAITER_DOCUMENT("traiterDocument"), //
                        PROC_CONTROLLER_DOCUMENT("controllerDocument"), //
                        PROC_ETUDE_DE_PV("etudeDePv"), //
                        PROC_TRAITER_TRANSACTION("traiterTransaction"), //
                        PROC_ELIGIBLE_TRANSACTION("traiterEligibleTransaction"), //
                        PROC_TRAITER_RESERVE("traiterReserve"), //
                        PROC_APPLIQUER_RESERVE_SS_DOSSIER("appliquerReserveSsDossier"), //
                        PROC_SAISIR_PROCEDURE_JUD("saisieProcedureJud"), //
                        PROC_SAISIR_JUGEMENT("saisieJugement"), //
                        PROC_RECOURS("recours"), //
                        PROC_INSTRUIRE_RAPPORT("instruireRapport"), //
                        PROC_INSTRUIRE_RAPPORT_ASSISTANCE("instruireRapportAssistance"), //
                        PROC_REGLEMENT_JUDICIAIRE("reglementJudiciaire"), //
                        PROC_REGLEMENT_AMIABLE("reglementAmiable"), //
                        PROC_REGLEMENT_TAXE("reglementTaxe"), //
                        PROC_REGLEMENT_EXEC_PROVISOIRE("reglementExecutionProvisoire"), //
                        PROC_SOUMETTRE_REGLEMENT("soumettreReglement"), //
                        PROC_VALIDATION_REGLEMENT("validationReglement"), //
                        PROC_VAL_REG_HONO_MED("validerReglementHonoraireMed"), //
                        PROC_VAL_REG_HONO_AVOCAT("validerReglementHonoraireAv"), //
                        PROC_SAISIE_REGLEMENT_HONORAIRE_AVOCAT("saisieReglementHonoraireAvocat"), //
                        PROC_SAISIE_REGLEMENT_HONORAIRE_MEDECIN("saisieReglementHonoraireMedecin"), //
                        PROC_RECEVOIR_COMPLEMENT_DOCUMENT("recevoirComplementDocument"), //
                        PROC_EVALUER_RAPPORT_ASSISTANCE("evaluerRapportAssistance"), //
                        PROC_VALIDER_RAPPORT_EXPERTISE_JUD("validerRapportExpertiseJud"), //
                        PROC_TRAITER_RAPPORT_EXP_AMIABLE("traiterRapportExpertiseAmiable"), //
                        PROC_VALIDER_OFFRE_TRANSACTION("validerOffreTransaction"), //
                        PROC_REGLER_TRANSACTION("reglerTransaction"), //
                        PROC_VALIDER_REGLEMENT_TRANSACTION("validerReglementTransaction"), //
                        PROC_VALIDER_ANNULATION_REGLEMENT("validerAnnulationReglement"), //
                        PROC_VALIDER_ANNULATION_HONORAIRE("validerAnnulationHonoraire");

                        @Getter
                        String processName;

                        Process(String processName) {
                                this.processName = processName;
                        }
                }

                @Getter
                String taskName;

                @Getter
                String processName;

                @Getter
                Category category;

                Task(String taskName, Process process, Category category) {
                        this.taskName = taskName;
                        this.processName = process.getProcessName();
                        this.category = category;
                }

        }

        enum Variable {
                VAR_TYPE_TACHE("TYPE_TACHE"), //
                PROC_VAR_SINISTRE_ID(WfConstantes.PROC_VAR_SINISTRE_ID), //
                PROC_VAR_HAS_DOCUMENTS_JOINTS(WfConstantes.PROC_VAR_HAS_DOCUMENTS_JOINTS), //
                PROC_VAR_TENANT_CODE(WfConstantes.PROC_VAR_TENANT_CODE), //
                PROC_VAR_REFERENCE(WfConstantes.PROC_VAR_REF_DOCUMENT), //
                PROC_VAR_CODE_DOCUMENT(WfConstantes.PROC_VAR_CODE_DOCUMENT), //
                PROC_VAR_NUMERO_DOCUMENT(WfConstantes.PROC_VAR_NUMERO_DOCUMENT), //
                PROC_VAR_DOCUMENT_ID(WfConstantes.PROC_VAR_DOCUMENT_ID), //
                PROC_VAR_NUM_REF_JUDICIAIRE(WfConstantes.PROC_VAR_NUM_REF_JUDICIAIRE), //
                PROC_VAR_DOCUMENT_CA_ID(WfConstantes.PROC_VAR_DOCUMENT_CA_ID), //
                PROC_VAR_DOCUMENT_PARENT_CA_ID(WfConstantes.PROC_VAR_DOCUMENT_PARENT_CA_ID), //
                PROC_VAR_SOUS_DOSSIER_BENEFICIARE_ID(WfConstantes.PROC_VAR_SOUS_DOSSIER_BENEFICIARE_ID), //
                PROC_VAR_NOM_TIER(WfConstantes.PROC_VAR_NOM_TIER), //
                PROC_VAR_PRIORITE_TACHE(WfConstantes.PROC_VAR_PRIORITE_TACHE), //
                PROC_VAR_DELAI_TRAIEMENT_TACHE(WfConstantes.PROC_VAR_DELAI_TRAIEMENT_TACHE), //
                PROC_VAR_PRENOM_TIER(WfConstantes.PROC_VAR_PRENOM_TIER), //
                PROC_VAR_CAT_SINISTRE(WfConstantes.PROC_VAR_CAT_SINISTRE), //
                PROC_VAR_EXE_SINISTRE(WfConstantes.PROC_VAR_EXE_SINISTRE), //
                PROC_VAR_NUM_SINISTRE(WfConstantes.PROC_VAR_NUM_SINISTRE), //
                PROC_VAR_FAMILLE_CODE(WfConstantes.PROC_VAR_FAMILLE_CODE), //
                PROC_VAR_FAMILLE_LIBELLE(WfConstantes.PROC_VAR_FAMILLE_LIBELLE), //
                PROC_VAR_TYPE_DOCUMENT_LIBELLE(WfConstantes.PROC_VAR_TYPE_DOCUMENT_LIBELLE), //
                PROC_VAR_TYPE_DOCUMENT_CODE(WfConstantes.PROC_VAR_TYPE_DOCUMENT_CODE), //
                PROC_VAR_UTILISATEUR_DESTINATAIRE(WfConstantes.PROC_VAR_UTILISATEUR_DESTINATAIRE), //
                PROC_VAR_UTILISATEUR_SOURCE(WfConstantes.PROC_VAR_UTILISATEUR_SOURCE), //
                PROC_VAR_DATE_NUMERISATION(WfConstantes.PROC_VAR_DATE_NUMERISATION), //
                PROC_VAR_DATE_RECEPTION(WfConstantes.PROC_VAR_DATE_RECEPTION), //
                PROC_VAR_DATE_CACHET(WfConstantes.PROC_VAR_DATE_CACHET), //
                PROC_VAR_DATE_AFFECTATION(WfConstantes.PROC_VAR_DATE_AFFECTATION), //
                PROC_VAR_CANAL(WfConstantes.PROC_VAR_CANAL), //
                PROC_VAR_MIME_TYPE(WfConstantes.PROC_VAR_MIME_TYPE), //
                PROC_VAR_STATUT(WfConstantes.PROC_VAR_STATUT), //
                PROC_VAR_IS_UTILISATEUR_GROUP(WfConstantes.PROC_VAR_IS_UTILISATEUR_GROUP), //
                PROC_VAR_CELLULE(WfConstantes.PROC_VAR_CELLULE), //
                PROC_VAR_MOTIF_VALIDATION(WfConstantes.PROC_VAR_MOTIF_VALIDATION), //
                PROC_VAR_MOTIF_VALIDATION_REOUVERTURE(WfConstantes.PROC_VAR_MOTIF), //
                PROC_VAR_COMMENTAIRE(WfConstantes.PROC_VAR_COMMENTAIRE), //
                PROC_VAR_ETAT_POLICE(WfConstantes.PROC_VAR_ETAT_VALIDATION), //
                PROC_VAR_NUMERO_SINISTRE(WfConstantes.PROC_VAR_NUMERO_SINISTRE), //
                PROC_VAR_DATE_SURVENANCE(WfConstantes.PROC_VAR_DATE_SURVENANCE), //
                PROC_VAR_DATE_OUVERTURE(WfConstantes.PROC_VAR_DATE_OUVERTURE), //
                PROC_VAR_NOM_ASSURE(WfConstantes.PROC_VAR_NOM_ASSURE), //
                PROC_VAR_NUMERO_POLICE(WfConstantes.PROC_VAR_NUMERO_POLICE), //
                PROC_VAR_CODE_INTERMEDIAIRE(WfConstantes.PROC_VAR_CODE_INTERMEDIAIRE), //
                PROC_VAR_NOM_INTERMEDIAIRE(WfConstantes.PROC_VAR_NOM_INTERMEDIAIRE), //
                PROC_VAR_OUVERTURE_ID(WfConstantes.PROC_VAR_OUVERTURE_ID), //
                PROC_VAR_MOTIF_VALIDATION_OUVERTURE(WfConstantes.PROC_VAR_MOTIF_VALIDATION_OUVERTURE), //
                PROC_VAR_APPLI_RESERVE_EVT(WfConstantes.PROC_VAR_APPLI_RESERVE_EVT), //
                PROC_VAR_NATURE_REGLEMENT(WfConstantes.PROC_VAR_NATURE_REGLEMENT), //
                PROC_VAR_TYPE_REGLEMENT(WfConstantes.PROC_VAR_TYPE_REGLEMENT), //
                PROC_VAR_JUGEMENT_ID(WfConstantes.PROC_VAR_JUGEMENT_ID), //
                PROC_VAR_OBJECT_ID(WfConstantes.PROC_VAR_OBJECT_ID), //
                PROC_VAR_NUM_DOSSIER_TRIBUNAL(WfConstantes.PROC_VAR_NUM_DOSSIER_TRIBUNAL), //
                PROC_VAR_PARENT_TACHE_ID(WfConstantes.PROC_VAR_PARENT_TACHE_ID), //
                PROC_VAR_DATE_CREATION(WfConstantes.PROC_VAR_DATE_CREATION), //
                PROC_VAR_RAISON_SOCIAL(WfConstantes.PROC_VAR_RAISON_SOCIAL), //
                PROC_VAR_MISSION_EXTRANET_ID(WfConstantes.PROC_VAR_MISSION_EXTRANET_ID), //
                PROC_VAR_MISSION_ID(WfConstantes.PROC_VAR_MISSION_ID), //
                PROC_VAR_EXPERT_JUD_CODE(WfConstantes.PROC_VAR_EXPERT_JUD_CODE), //
                PROC_VAR_EXPERT_JUD_NOM(WfConstantes.PROC_VAR_EXPERT_JUD_NOM), //
                PROC_VAR_EXPERT_CONSEIL_CODE(WfConstantes.PROC_VAR_EXPERT_CONSEIL_CODE), //
                PROC_VAR_EXPERT_CONSEIL_NOM(WfConstantes.PROC_VAR_EXPERT_CONSEIL_NOM), //
                PROC_VAR_MONTANT_TTC(WfConstantes.PROC_VAR_MONTANT_TTC), //
                PROC_VAR_CODE_EVENEMENT(WfConstantes.PROC_VAR_CODE_EVENEMENT), //
                PROC_VAR_NUM_ORDRE(WfConstantes.PROC_VAR_NUM_ORDRE), //
                PROC_VAR_CHAMBRE(WfConstantes.PROC_VAR_CHAMBRE), //
                PROC_VAR_EXERCICE(WfConstantes.PROC_VAR_EXERCICE), //
                PROC_VAR_RAPPORT_EXPERTISE_CORPO_ID(WfConstantes.PROC_VAR_RAPPORT_EXPERTISE_CORPO_ID), //
                PROC_VAR_OFFRE_TRANSACTION_ID(WfConstantes.PROC_VAR_OFFRE_TRANSACTION_ID), //
                PROC_VAR_BENEFICIAIRES_REGLEMENT(WfConstantes.PROC_VAR_BENEFICIAIRES_REGLEMENT), //
                PROC_VAR_VOIE_TRAITEMENT_SINISTRE(WfConstantes.PROC_VAR_VOIE_TRAITEMENT_SINISTRE), //
                PROC_VAR_TYPE_PRESTATAIRE(WfConstantes.PROC_VAR_TYPE_PRESTATAIRE), //
                PROC_VAR_IS_AVOCAT(WfConstantes.PROC_VAR_IS_AVOCAT), //
                PROC_VAR_INSTANCE_GED(WfConstantes.PROC_VAR_INSTANCE_GED), //
                PROC_VAR_OLD_RESERVE(WfConstantes.PROC_VAR_OLD_RESERVE), //
                PROC_VAR_NEW_RESERVE(WfConstantes.PROC_VAR_NEW_RESERVE);

                @Getter
                String varName;

                Variable(String varName) {
                        this.varName = varName;
                }
        }

}
