import { useSelector } from 'react-redux';
import { Bloc, FormulaireConsultation, Reference, Texte } from 'waxant';
import { selectQuestionnaire } from '../MdlConsulterQuestionnaire';

const EtatQuestionnaire = () => {
    const questionnaire = useSelector(selectQuestionnaire);
    //
    return (
        <Bloc>
            <FormulaireConsultation modele={questionnaire} nombreColonne={1}>
                <Texte nom="code" />
                <Texte nom="dateCreation" />
                <Reference nom="versionModeleQuestionnaire" />
            </FormulaireConsultation>
        </Bloc>
    );
};

export default EtatQuestionnaire;