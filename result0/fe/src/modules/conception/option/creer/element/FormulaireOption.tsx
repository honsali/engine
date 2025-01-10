import { Bloc, ChampTexte, Formulaire } from 'waxant';

const FormulaireOption = ({ form }) => {
    //
    return (
        <Bloc>
            <Formulaire form={form}>
                <ChampTexte nom="libelle" requis="true" />
                <ChampTexte nom="valeur" requis="true" />
            </Formulaire>
        </Bloc>
    );
};

export default FormulaireOption;