import { Bloc, ChampOuiNon, ChampReference, ChampTexte, Formulaire } from 'waxant';

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
            </Formulaire>
        </Bloc>
    );
};

export default FormulaireQuestion;