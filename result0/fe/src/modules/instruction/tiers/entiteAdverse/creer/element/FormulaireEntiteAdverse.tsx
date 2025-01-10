import { Form } from 'antd';
import { Bloc, CadreHaut, ChampDate, ChampEmail, ChampOuiNon, ChampReference, ChampReferenceAvecFiltre, ChampTel, ChampTexte, ChampTexteLong, Formulaire } from 'waxant';

const FormulaireEntiteAdverse = ({ form }) => {
    const personnePhysique = Form.useWatch('personnePhysique', form);
    //
    return (
        <Bloc>
            <Formulaire form={form} nombreColonne={2}>
                <ChampOuiNon nom="personnePhysique" oui="Physique" non="Morale" />
                <ChampOuiNon nom="degatsMateriel" />
            </Formulaire>
            {personnePhysique && (
                <CadreHaut titre="infoPersonnePhysique">
                    <Formulaire form={form} nombreColonne={2}>
                        <ChampOuiNon nom="degatsCorporel" />
                        <ChampReferenceAvecFiltre nom="qualiteVictime" reference="sousQualiteVictime" arg={{ code: 'ADVERSE' }} requis="true" />
                        <ChampTexte nom="nom" requis="true" />
                        <ChampTexte nom="prenom" requis="true" />
                        <ChampReference nom="typeIdentification" requis="true" />
                        <ChampTexte nom="numeroIdentification" requis="true" />
                        <ChampReference nom="ville" seulDansLaLigne />
                        <ChampTexteLong nom="adresse" surTouteLaLigne />
                        <ChampDate nom="dateNaissance" requis="true" />
                        <ChampReference nom="sexe" requis="true" />
                        <ChampTel nom="tel" />
                        <ChampEmail nom="email" />
                        <ChampReference nom="situationFamiliale" />
                        <ChampReference nom="profession" />
                    </Formulaire>
                </CadreHaut>
            )}
            {!personnePhysique && (
                <CadreHaut titre="infoPersonneMorale">
                    <Formulaire form={form} nombreColonne={2}>
                        <ChampTexte nom="nom" libelle="Raison Sociale" requis="true" />
                        <ChampReferenceAvecFiltre nom="qualiteVictime" reference="sousQualiteVictime" arg={{ code: 'ADVERSE' }} requis="true" />
                        <ChampTexteLong nom="adresse" surTouteLaLigne />
                    </Formulaire>
                </CadreHaut>
            )}
            <CadreHaut titre="infoContrat">
                <Formulaire form={form} nombreColonne={2}>
                    <ChampReference nom="assureur" seulDansLaLigne />
                    <ChampTexte nom="codeIntermediaire" />
                    <ChampTexte nom="nomIntermediaire" />
                    <ChampTexte nom="immatriculationVehicule" />
                    <ChampTexte nom="numeroPolice" />
                    <ChampDate nom="dateDebutContrat" />
                    <ChampDate nom="dateFinContrat" />
                </Formulaire>
            </CadreHaut>
        </Bloc>
    );
};

export default FormulaireEntiteAdverse;