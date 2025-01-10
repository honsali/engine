import { Bloc, ChampCache, ChampTexte, Formulaire } from 'waxant';

const FormulaireRubrique = ({ form }) => {
    //
    return (
        <Bloc>
            <Formulaire form={form}>
                <ChampTexte nom="titre" requis="true" />
                <ChampTexte nom="description" />
                <ChampCache nom="id" />
                <ChampCache nom="position" />
                <ChampCache nom="versionModeleQuestionnaire" />
            </Formulaire>
        </Bloc>
    );
};

export default FormulaireRubrique;