import { useSelector } from 'react-redux';
import { Bloc, FormulaireConsultation, Texte } from 'waxant';
import { selectRubrique } from '../MdlConsulterRubrique';

const EtatRubrique = () => {
    const rubrique = useSelector(selectRubrique);
    //
    return (
        <Bloc>
            <FormulaireConsultation modele={rubrique} nombreColonne={1}>
                <Texte nom="position" />
                <Texte nom="titre" />
                <Texte nom="description" />
            </FormulaireConsultation>
        </Bloc>
    );
};

export default EtatRubrique;