import { useSelector } from 'react-redux';
import { Bloc, FormulaireConsultation, OuiNon, Reference, Texte } from 'waxant';
import { selectQuestion } from '../MdlConsulterQuestion';

const EtatQuestion = () => {
    const question = useSelector(selectQuestion);
    //
    return (
        <Bloc>
            <FormulaireConsultation modele={question} nombreColonne={1}>
                <Texte nom="position" />
                <Texte nom="code" />
                <Texte nom="titre" />
                <Texte nom="description" />
                <Reference nom="typeReponse" />
                <OuiNon nom="autreOption" />
                <Texte nom="autreLibelle" />
            </FormulaireConsultation>
        </Bloc>
    );
};

export default EtatQuestion;