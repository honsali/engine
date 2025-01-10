import { useState } from 'react';
import { useSelector } from 'react-redux';
import { Bloc, ChampCache, ChampDate, ChampEmail, ChampListe, ChampReference, ChampReferenceAvecFiltre, ChampTel, ChampTexte, ChampVide, Formulaire, useOnChange } from 'waxant';
import { selectListeEntiteAdverse } from '../MdlModifierVictime';

const FormulaireVictime = ({ form }) => {
    const listeEntiteAdverse = useSelector(selectListeEntiteAdverse);
    const [entiteAdverseInvisible, setEntiteAdverseInvisible] = useState(false);

    useOnChange('qualiteVictime', form, (valeur) => {
        setEntiteAdverseInvisible(valeur);
    });
    //
    return (
        <Bloc>
            <Formulaire form={form} nombreColonne={2}>
                <ChampReferenceAvecFiltre nom="qualiteVictime" reference="sousQualiteVictime" arg={{ code: 'VICTIME' }} requis="true" />
                <ChampListe nom="entiteAdverse" liste={listeEntiteAdverse} requis="true" invisible={entiteAdverseInvisible} />
                <ChampVide invisible={!entiteAdverseInvisible} />
                <ChampTexte nom="nom" requis="true" />
                <ChampTexte nom="prenom" requis="true" />
                <ChampDate nom="dateNaissance" requis="true" />
                <ChampReference nom="sexe" requis="true" />
                <ChampReference nom="typeIdentification" requis="true" />
                <ChampTexte nom="numeroIdentification" requis="true" />
                <ChampTel nom="tel" />
                <ChampEmail nom="email" />
                <ChampReference nom="ville" />
                <ChampTexte nom="adresse" />
                <ChampReference nom="situationFamiliale" />
                <ChampReference nom="profession" />
                <ChampCache nom="id" />
                <ChampCache nom="nomPrenomArabe" />
                <ChampCache nom="mineur" />
                <ChampCache nom="ayantDroit" />
                <ChampCache nom="idClient" />
                <ChampCache nom="idTiersAssocie" />
                <ChampCache nom="categorie" />
                <ChampCache nom="numeroPoliceExterne" />
                <ChampCache nom="assureur" />
            </Formulaire>
        </Bloc>
    );
};

export default FormulaireVictime;