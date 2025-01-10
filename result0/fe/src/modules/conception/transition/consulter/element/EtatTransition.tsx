import { useSelector } from 'react-redux';
import { Bloc, FormulaireConsultation, Reference, Texte } from 'waxant';
import { selectTransition } from '../MdlConsulterTransition';

const EtatTransition = () => {
    const transition = useSelector(selectTransition);
    //
    return (
        <Bloc>
            <FormulaireConsultation modele={transition} nombreColonne={1}>
                <Texte nom="raison" />
                <Reference nom="optionChoisie" reference="option" />
                <Reference nom="questionCible" reference="question" />
            </FormulaireConsultation>
        </Bloc>
    );
};

export default EtatTransition;