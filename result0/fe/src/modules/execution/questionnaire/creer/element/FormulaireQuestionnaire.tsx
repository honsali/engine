import { Bloc, ChampReference, Formulaire } from 'waxant';

const FormulaireQuestionnaire = ({ form }) => {
    //
    return (
        <Bloc>
            <Formulaire form={form}>
                <ChampReference nom="versionModeleQuestionnaire" />
            </Formulaire>
        </Bloc>
    );
};

export default FormulaireQuestionnaire;