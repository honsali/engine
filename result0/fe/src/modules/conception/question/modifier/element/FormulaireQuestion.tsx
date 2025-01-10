import { Bloc, ChampCache, ChampOuiNon, ChampReference, ChampTexte, Formulaire } from 'waxant';

const FormulaireQuestion = ({ form }) => {
    //
    return (
        <Bloc>
            <Formulaire form={form}>
                <ChampTexte nom="code" />
                <ChampTexte nom="titre" requis="true" />
                <ChampTexte nom="description" />
                <ChampReference nom="typeReponse" requis="true" />
                <ChampOuiNon nom="autreOption" />
                <ChampTexte nom="autreLibelle" />
                <ChampCache nom="id" />
                <ChampCache nom="position" />
                <ChampCache nom="rubrique" />
            </Formulaire>
        </Bloc>
    );
};

export default FormulaireQuestion;