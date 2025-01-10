import { useSelector } from 'react-redux';
import { Bloc, FormulaireConsultation, Reference, Texte } from 'waxant';
import { selectVersionModeleQuestionnaire } from '../MdlConsulterVersionModeleQuestionnaire';

const EtatVersionModeleQuestionnaire = () => {
    const versionModeleQuestionnaire = useSelector(selectVersionModeleQuestionnaire);
    //
    return (
        <Bloc>
            <FormulaireConsultation modele={versionModeleQuestionnaire}>
                <Texte nom="version" />
                <Texte nom="dateCreation" />
                <Texte nom="dateModification" />
                <Texte nom="dateDebutUtilisation" />
                <Texte nom="dateFinUtilisation" />
                <Reference nom="etat" reference="etatVersionModeleQuestionnaire" />
            </FormulaireConsultation>
        </Bloc>
    );
};

export default EtatVersionModeleQuestionnaire;