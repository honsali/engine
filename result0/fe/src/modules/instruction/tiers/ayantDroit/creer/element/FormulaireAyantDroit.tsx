import { useState } from 'react';
import { Bloc, ChampDate, ChampOuiNon, ChampReference, ChampTexte, ChampTexteArabe, ChampVide, Formulaire, useOnChange } from 'waxant';

const FormulaireAyantDroit = ({ form }) => {
    const [adulte, setAdulte] = useState(false);

    useOnChange('lienParente', form, (valeur) => {
        setAdulte(valeur);
    });
    //
    return (
        <Bloc>
            <Formulaire form={form} nombreColonne={2}>
                <ChampTexte nom="nom" requis="true" />
                <ChampTexte nom="prenom" requis="true" />
                <ChampTexteArabe nom="nomPrenomArabe" />
                <ChampDate nom="dateNaissance" requis="true" />
                <ChampReference nom="typeIdentification" />
                <ChampTexte nom="numeroIdentification" />
                <ChampReference nom="sexe" requis="true" />
                <ChampReference nom="ville" requis="true" />
                <ChampReference nom="lienParente" requis="true" />
                <ChampOuiNon nom="infirme" requis="true" invisible={adulte} />
                <ChampVide invisible={!adulte} />
                <ChampOuiNon nom="obligationAlimentaire" invisible={!autre} />
                <ChampOuiNon nom="perteRessource" />
            </Formulaire>
        </Bloc>
    );
};

export default FormulaireAyantDroit;