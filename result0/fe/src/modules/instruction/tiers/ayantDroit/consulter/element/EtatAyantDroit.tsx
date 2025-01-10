import { useSelector } from 'react-redux';
import { Bloc, FormulaireConsultation, OuiNon, Reference, Texte } from 'waxant';
import { selectAyantDroit } from '../MdlConsulterAyantDroit';

const EtatAyantDroit = () => {
    const ayantDroit = useSelector(selectAyantDroit);
    //
    return (
        <Bloc>
            <FormulaireConsultation modele={ayantDroit}>
                <Texte nom="nom" />
                <Texte nom="prenom" />
                <Texte nom="nomPrenomArabe" />
                <Texte nom="dateNaissance" />
                <Reference nom="typeIdentification" />
                <Texte nom="numeroIdentification" />
                <Reference nom="sexe" />
                <Reference nom="ville" />
                <Reference nom="lienParente" />
                <OuiNon nom="infirme" />
                <OuiNon nom="obligationAlimentaire" />
                <OuiNon nom="perteRessource" />
            </FormulaireConsultation>
        </Bloc>
    );
};

export default EtatAyantDroit;