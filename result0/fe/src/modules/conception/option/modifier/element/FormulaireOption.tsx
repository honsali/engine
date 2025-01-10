import { Bloc, ChampCache, ChampTexte, Formulaire } from 'waxant';

const FormulaireOption = ({ form }) => {
    //
    return (
        <Bloc>
            <Formulaire form={form}>
                <ChampTexte nom="libelle" requis="true" />
                <ChampTexte nom="valeur" requis="true" />
                <ChampCache nom="id" />
                <ChampCache nom="position" />
                <ChampCache nom="code" />
                <ChampCache nom="question" />
            </Formulaire>
        </Bloc>
    );
};

export default FormulaireOption;