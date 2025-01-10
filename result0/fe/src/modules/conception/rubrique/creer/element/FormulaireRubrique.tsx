import { Bloc, ChampTexte, Formulaire } from 'waxant';

const FormulaireRubrique = ({ form }) => {
    //
    return (
        <Bloc>
            <Formulaire form={form}>
                <ChampTexte nom="titre" requis="true" />
                <ChampTexte nom="description" />
            </Formulaire>
        </Bloc>
    );
};

export default FormulaireRubrique;