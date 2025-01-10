import { useSelector } from 'react-redux';
import { Bloc, FormulaireConsultation, Texte } from 'waxant';
import { selectSousDossier } from '../MdlModifierAyantDroit';

const EtatSousDossier = () => {
    const sousDossier = useSelector(selectSousDossier);
    //
    return (
        <Bloc>
            <FormulaireConsultation modele={sousDossier}>
                <Texte nom="dateSurvenance" />
            </FormulaireConsultation>
        </Bloc>
    );
};

export default EtatSousDossier;