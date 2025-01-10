import { useSelector } from 'react-redux';
import { Bloc, CadreHaut, FormulaireConsultation, OuiNon, Reference, Texte } from 'waxant';
import { selectEntiteAdverse } from '../MdlConsulterEntiteAdverse';

const EtatEntiteAdverse = () => {
    const entiteAdverse = useSelector(selectEntiteAdverse);
    //
    return (
        <Bloc>
            <FormulaireConsultation modele={entiteAdverse}>
                <OuiNon nom="personnePhysique" oui="Physique" non="Morale" />
                <OuiNon nom="degatsMateriel" />
            </FormulaireConsultation>
            {entiteAdverse.personnePhysique && (
                <CadreHaut titre="infoPersonnePhysique">
                    <FormulaireConsultation modele={entiteAdverse}>
                        <OuiNon nom="degatsCorporel" />
                        <Reference nom="qualiteVictime" reference="sousQualiteVictime" arg={{ code: 'ADVERSE' }} />
                        <Texte nom="nom" />
                        <Texte nom="prenom" />
                        <Reference nom="typeIdentification" />
                        <Texte nom="numeroIdentification" />
                        <Reference nom="ville" seulDansLaLigne />
                        <Texte nom="adresse" surTouteLaLigne />
                        <Texte nom="dateNaissance" />
                        <Reference nom="sexe" />
                        <Texte nom="tel" />
                        <Texte nom="email" />
                        <Reference nom="situationFamiliale" />
                        <Reference nom="profession" />
                    </FormulaireConsultation>
                </CadreHaut>
            )}
            {!entiteAdverse.personnePhysique && (
                <CadreHaut titre="infoPersonneMorale">
                    <FormulaireConsultation modele={entiteAdverse}>
                        <Texte nom="nom" libelle="Raison Sociale" />
                        <Reference nom="qualiteVictime" reference="sousQualiteVictime" arg={{ code: 'ADVERSE' }} />
                        <Texte nom="adresse" surTouteLaLigne />
                    </FormulaireConsultation>
                </CadreHaut>
            )}
            <CadreHaut titre="infoContrat">
                <FormulaireConsultation modele={entiteAdverse}>
                    <Reference nom="assureur" seulDansLaLigne />
                    <Texte nom="codeIntermediaire" />
                    <Texte nom="nomIntermediaire" />
                    <Texte nom="immatriculationVehicule" />
                    <Texte nom="numeroPolice" />
                    <Texte nom="dateDebutContrat" />
                    <Texte nom="dateFinContrat" />
                </FormulaireConsultation>
            </CadreHaut>
        </Bloc>
    );
};

export default EtatEntiteAdverse;