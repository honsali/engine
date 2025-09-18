package modules.rh.employe;

import dev.cruding.engine.composant.Composant;
import dev.cruding.engine.gen.ElementComposer;
import modele.rh.Conge;
import modele.rh.Employe;
import modules.rh.conge.TableauConge;

public class ViewConsulterEmploye extends ElementComposer {
        public ViewConsulterEmploye() {
                super("ViewConsulterEmploye", "/");
        }

        public Composant composantRacine() {

                Employe e = (Employe) getEntite("Employe");
                Conge c = (Conge) getEntite("Conge");
                return section( //
                                menuOnglet(//
                                                bloc(//
                                                                element(new EtatEmploye()), //
                                                                blocAction(//
                                                                                bouton(actionModifier(e, "PageModifierEmploye")), //
                                                                                bouton(actionRetourListe(e, "PageFiltrerEmploye")), //
                                                                                bouton(actionSupprimer(e).siReussi(goToPage(e, "PageFiltrerEmploye")))//
                                                                )//
                                                ).marge("20px").nom("employe"), //
                                                bloc(//
                                                                element(new TableauConge()), //
                                                                blocAction(//
                                                                                bouton(actionAjouter(c, "PageModifierConge")) //
                                                                )//
                                                ).nom("conge")//
                                )//
                );
        }

}
