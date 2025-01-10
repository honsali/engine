import { Bloc, ChampReference, ChampTexte, Formulaire } from 'waxant';

const FormulaireTransition = ({ form }) => {
    //
    return (
        <Bloc>
            <Formulaire form={form}>
                <ChampTexte nom="raison" requis="true" />
                <ChampReference nom="optionChoisie" reference="option" />
                <ChampReference nom="questionCible" reference="question" />
            </Formulaire>
        </Bloc>
    );
};

export default FormulaireTransition;