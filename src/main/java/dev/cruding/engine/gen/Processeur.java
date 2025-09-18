package dev.cruding.engine.gen;

import dev.cruding.engine.element.Element;
import dev.cruding.engine.entite.Entite;
import dev.cruding.engine.printer.impl.commun.BeLiqMasterPrinter;
import dev.cruding.engine.printer.impl.commun.FeGoToPrinter;
import dev.cruding.engine.printer.impl.commun.FeHasRightPrinter;
import dev.cruding.engine.printer.impl.commun.FeMenuPrinter;
import dev.cruding.engine.printer.impl.commun.FeRoutesPrinter;
import dev.cruding.engine.printer.impl.commun.FeStorePrinter;
import dev.cruding.engine.printer.impl.element.FeElementPrinter;
import dev.cruding.engine.printer.impl.entite.BeBusinessPrinter;
import dev.cruding.engine.printer.impl.entite.BeDomainePrinter;
import dev.cruding.engine.printer.impl.entite.BeDtoPrinter;
import dev.cruding.engine.printer.impl.entite.BeFiltrePrinter;
import dev.cruding.engine.printer.impl.entite.BeLiqConstraintPrinter;
import dev.cruding.engine.printer.impl.entite.BeLiqDataPrinter;
import dev.cruding.engine.printer.impl.entite.BeLiqTablePrinter;
import dev.cruding.engine.printer.impl.entite.BeRepositoryPrinter;
import dev.cruding.engine.printer.impl.entite.BeResourcePrinter;
import dev.cruding.engine.printer.impl.entite.BeSpecificationPrinter;
import dev.cruding.engine.printer.impl.entite.FeDomainePrinter;
import dev.cruding.engine.printer.impl.entite.FeServicePrinter;
import dev.cruding.engine.printer.impl.module.FeAclModule;
import dev.cruding.engine.printer.impl.module.FeActionModule;
import dev.cruding.engine.printer.impl.module.FeI18nPrinter;
import dev.cruding.engine.printer.impl.module.FeListePagePrinter;
import dev.cruding.engine.printer.impl.module.FeModulePrinter;
import dev.cruding.engine.printer.impl.module.FeReducerPrinter;
import dev.cruding.engine.printer.impl.page.FeCtrlPrinter;
import dev.cruding.engine.printer.impl.page.FeHookPrinter;
import dev.cruding.engine.printer.impl.page.FeMdlPrinter;

public class Processeur {

    private final FeCtrlPrinter feCtrlPrinter = new FeCtrlPrinter();
    private final FeMdlPrinter feMdlPrinter = new FeMdlPrinter();
    private final FeHookPrinter feHookPrinter = new FeHookPrinter();
    private final FeDomainePrinter feDomainePrinter = new FeDomainePrinter();
    private final FeServicePrinter feServicePrinter = new FeServicePrinter();
    private final BeDomainePrinter beDomainePrinter = new BeDomainePrinter();
    private final BeDtoPrinter beDtoPrinter = new BeDtoPrinter();
    private final BeFiltrePrinter beFiltrePrinter = new BeFiltrePrinter();
    private final BeSpecificationPrinter beSpecificationPrinter = new BeSpecificationPrinter();
    private final BeRepositoryPrinter beRepositoryPrinter = new BeRepositoryPrinter();
    private final BeResourcePrinter beResourcePrinter = new BeResourcePrinter();
    private final BeBusinessPrinter beBusinessPrinter = new BeBusinessPrinter();
    private final BeLiqConstraintPrinter beLiqConstraintPrinter = new BeLiqConstraintPrinter();
    private final BeLiqDataPrinter beLiqDataPrinter = new BeLiqDataPrinter();
    private final BeLiqMasterPrinter beLiqMasterPrinter = new BeLiqMasterPrinter();
    private final BeLiqTablePrinter beLiqTablePrinter = new BeLiqTablePrinter();
    private final FeStorePrinter feStorePrinter = new FeStorePrinter();
    private final FeRoutesPrinter feRoutesPrinter = new FeRoutesPrinter();
    private final FeMenuPrinter feMenuPrinter = new FeMenuPrinter();
    private final FeHasRightPrinter feHasRightPrinter = new FeHasRightPrinter();
    private final FeGoToPrinter feGoToPrinter = new FeGoToPrinter();
    private final FeModulePrinter feModulePrinter = new FeModulePrinter();
    private final FeI18nPrinter feI18nPrinter = new FeI18nPrinter();
    private final FeActionModule feActionModule = new FeActionModule();
    private final FeAclModule feAclModule = new FeAclModule();
    private final FeListePagePrinter feListePagePrinter = new FeListePagePrinter();
    private final FeReducerPrinter feReducerPrinter = new FeReducerPrinter();
    private final FeElementPrinter feElementPrinter = new FeElementPrinter();

    public void executer() {

        printFeGlobalFiles();
        printBeGlobalFiles();

        for (Page page : Contexte.getInstance().getPageList()) {
            if (page.estReelle()) {
                for (Element element : page.listeElement) {
                    printFeElementFiles(element);
                }
                feCtrlPrinter.print(page);
                feMdlPrinter.print(page);
                feHookPrinter.print(page);
            }
        }


        for (Entite entite : Contexte.getInstance().getEntiteList()) {
            printFeEntiteFiles(entite);
            printBeEntiteFiles(entite);
        }

        for (Module module : Contexte.getInstance().getModuleList()) {
            printFeModuleFiles(module);
        }
    }

    private void printFeElementFiles(Element element) {
        if (!element.fake) {
            feElementPrinter.print(element);
        }
    }

    private void printFeGlobalFiles() {
        feStorePrinter.print();
        feRoutesPrinter.print();
        feMenuPrinter.print();
        feHasRightPrinter.print();
        feGoToPrinter.print();
    }

    private void printBeGlobalFiles() {
        beLiqMasterPrinter.print();
    }

    private void printFeEntiteFiles(Entite entite) {
        feDomainePrinter.print(entite);
        feServicePrinter.print(entite);
    }

    private void printBeEntiteFiles(Entite entite) {
        beDomainePrinter.print(entite);
        if (!entite.isReferenceData()) {
            beResourcePrinter.print(entite);
            beBusinessPrinter.print(entite);
            beRepositoryPrinter.print(entite);
        }
        beDtoPrinter.print(entite);
        beFiltrePrinter.print(entite);
        beSpecificationPrinter.print(entite);
        beLiqConstraintPrinter.print(entite);
        beLiqDataPrinter.print(entite);
        beLiqTablePrinter.print(entite);

    }

    private void printFeModuleFiles(Module module) {
        feI18nPrinter.print(module);
        feListePagePrinter.print(module);
        feModulePrinter.print(module);
        feReducerPrinter.print(module);
        feActionModule.print(module);
        feAclModule.print(module);
    }
}
