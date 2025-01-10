import { Bloc, ChampCache, ChampTexte, Formulaire } from 'waxant';

const FormulaireReponse = ({ form }) => {
    //
    return (
        <Bloc>
            <Formulaire form={form}>
                <ChampTexte nom="code" requis="true" />
                <ChampCache nom="id" />
            </Formulaire>
        </Bloc>
    );
};

export default FormulaireReponse;