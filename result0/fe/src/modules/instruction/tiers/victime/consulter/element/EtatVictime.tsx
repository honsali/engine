import { useSelector } from 'react-redux';
import { Bloc, ChampVide, FormulaireConsultation, Reference, Texte } from 'waxant';
import { selectVictime } from '../MdlConsulterVictime';

const EtatVictime = () => {
    const victime = useSelector(selectVictime);
    //
    return (
        <Bloc>
            <FormulaireConsultation modele={victime}>
                <Reference nom="qualiteVictime" />
                <Reference nom="entiteAdverse" invisible={entiteAdverseInvisible} />
                <ChampVide invisible={!entiteAdverseInvisible} />
                <Texte nom="nom" />
                <Texte nom="prenom" />
                <Texte nom="dateNaissance" />
                <Reference nom="sexe" />
                <Reference nom="typeIdentification" />
                <Texte nom="numeroIdentification" />
                <Texte nom="tel" />
                <Texte nom="email" />
                <Reference nom="ville" />
                <Texte nom="adresse" />
                <Reference nom="situationFamiliale" />
                <Reference nom="profession" />
            </FormulaireConsultation>
        </Bloc>
    );
};

export default EtatVictime;