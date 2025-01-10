import { Form } from 'antd';
import { useEffect } from 'react';
import { ActionUcAppliquerFiltre, ActionUcInitialiserFiltre, BlocAction, ChampAnnee, ChampDate, ChampReference, ChampReferenceAvecFiltre, ChampTexte, Filtre, Formulaire, PanneauEtendable, Separateur, useExecute } from 'waxant';
import { ActionIdentification } from '../../ActionIdentification';
import CtrlChercherIdentification from '../CtrlChercherIdentification';

const FiltreIdentification = () => {
    const { execute } = useExecute();
    const [form] = Form.useForm();

    useEffect(() => {
        initialiserFiltreIdentification();
    }, []);

    const initialiserFiltreIdentification = () => {
        form.resetChamps();
        appliquerFiltreIdentification();
    };

    const appliquerFiltreIdentification = () => {
        execute(CtrlChercherIdentification.chercherIdentification, { form });
    };
    //
    return (
        <Filtre titre="filtreIdentification">
            <PanneauEtendable titre="sinistre" open={true}>
                <Formulaire form={form} nombreColonne={2}>
                    <ChampTexte nom="numeroSinistre" />
                    <ChampDate nom="dateSurvenance" />
                </Formulaire>
            </PanneauEtendable>
            <PanneauEtendable titre="contrat">
                <Formulaire form={form} nombreColonne={2}>
                    <ChampTexte nom="numeroSerie" />
                    <ChampDate nom="dateSouscription" />
                    <ChampTexte nom="codeIntermediaire" />
                    <ChampTexte nom="libelleIntermediaire" />
                </Formulaire>
            </PanneauEtendable>
            <PanneauEtendable titre="assure">
                <Formulaire form={form} nombreColonne={2}>
                    <ChampTexte nom="libelleAssure" />
                    <ChampTexte nom="numeroIdentificationAssure" />
                    <ChampTexte nom="numeroVehicule" />
                    <ChampTexte nom="numeroImmatriculation" />
                </Formulaire>
            </PanneauEtendable>
            <PanneauEtendable titre="document">
                <Formulaire form={form} nombreColonne={2}>
                    <ChampTexte nom="referenceDocument" />
                    <ChampTexte nom="numeroDocument" />
                    <ChampReference nom="familleDocument" />
                    <ChampReferenceAvecFiltre nom="typeDocument" arg="familleDocument" />
                    <ChampDate nom="dateCachet" />
                    <ChampDate nom="dateReception" />
                    <ChampReference nom="prestataire" />
                </Formulaire>
            </PanneauEtendable>
            <PanneauEtendable titre="tiers">
                <Formulaire form={form} nombreColonne={2}>
                    <ChampTexte nom="numeroIdentificationTiers" />
                    <ChampTexte nom="libelleTiers" />
                    <ChampReference nom="compagnieAdverse" reference="assureur" />
                </Formulaire>
            </PanneauEtendable>
            <PanneauEtendable titre="judiciaire">
                <Formulaire form={form} nombreColonne={2}>
                    <ChampReference nom="villeTribunal" reference="ville" />
                    <ChampReferenceAvecFiltre nom="tribunal" reference="tribunauxParVille" arg="villeTribunal" />
                    <ChampTexte nom="numeroDossier" />
                    <ChampReference nom="numeroChambre" />
                    <ChampReference nom="phase" reference="phaseJudiciaire" />
                    <ChampAnnee nom="exercice" />
                    <ChampDate nom="dateAudience" />
                    <ChampDate nom="dateJugement" />
                </Formulaire>
            </PanneauEtendable>
            <Separateur top="20" />
            <BlocAction>
                <ActionUcAppliquerFiltre nom={ActionIdentification.UcChercherIdentification.APPLIQUER_FILTRE_IDENTIFICATION} action={appliquerFiltreIdentification} />
                <ActionUcInitialiserFiltre nom={ActionIdentification.UcChercherIdentification.INITIALISER_FILTRE_IDENTIFICATION} action={initialiserFiltreIdentification} />
            </BlocAction>
        </Filtre>
    );
};

export default FiltreIdentification;