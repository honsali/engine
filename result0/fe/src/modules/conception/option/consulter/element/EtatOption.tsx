import { useSelector } from 'react-redux';
import { Bloc, FormulaireConsultation, Texte } from 'waxant';
import { selectOption } from '../MdlConsulterOption';

const EtatOption = () => {
    const option = useSelector(selectOption);
    //
    return (
        <Bloc>
            <FormulaireConsultation modele={option} nombreColonne={1}>
                <Texte nom="libelle" />
                <Texte nom="valeur" />
            </FormulaireConsultation>
        </Bloc>
    );
};

export default EtatOption;