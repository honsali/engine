import { Bloc, ChampTexte, ChampTexteLong, Formulaire } from 'waxant';

const FormulaireModeleQuestionnaire = ({ form }) => {
    //
    return (
        <Bloc>
            <Formulaire form={form}>
                <ChampTexte nom="nom" requis="true" />
                <ChampTexteLong nom="description" />
            </Formulaire>
        </Bloc>
    );
};

export default FormulaireModeleQuestionnaire;