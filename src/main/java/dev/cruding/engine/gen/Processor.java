package dev.cruding.engine.gen;

import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.printer.impl.commun.BeLiqMasterPrinter;
import dev.cruding.engine.printer.impl.commun.FeGoToPrinter;
import dev.cruding.engine.printer.impl.commun.FeHasRightPrinter;
import dev.cruding.engine.printer.impl.commun.FeMenuPrinter;
import dev.cruding.engine.printer.impl.commun.FeRoutesPrinter;
import dev.cruding.engine.printer.impl.commun.FeStorePrinter;
import dev.cruding.engine.printer.impl.element.FeElementPrinter;
import dev.cruding.engine.printer.impl.entity.BeDomainePrinter;
import dev.cruding.engine.printer.impl.entity.BeLiqConstraintPrinter;
import dev.cruding.engine.printer.impl.entity.BeLiqDataPrinter;
import dev.cruding.engine.printer.impl.entity.BeLiqTablePrinter;
import dev.cruding.engine.printer.impl.entity.BeRefDomainePrinter;
import dev.cruding.engine.printer.impl.entity.BeRefRepositoryPrinter;
import dev.cruding.engine.printer.impl.entity.BeRepositoryPrinter;
import dev.cruding.engine.printer.impl.entity.BeResourcePrinter;
import dev.cruding.engine.printer.impl.entity.FeDomainePrinter;
import dev.cruding.engine.printer.impl.entity.FeServicePrinter;
import dev.cruding.engine.printer.impl.module.FeActionModule;
import dev.cruding.engine.printer.impl.module.FeI18nPrinter;
import dev.cruding.engine.printer.impl.module.FeListePagePrinter;
import dev.cruding.engine.printer.impl.module.FeModulePrinter;
import dev.cruding.engine.printer.impl.module.FeReducerPrinter;
import dev.cruding.engine.printer.impl.page.FeCtrlPrinter;
import dev.cruding.engine.printer.impl.page.FeMdlPrinter;

public class Processor {

    private final FeCtrlPrinter feCtrlPrinter = new FeCtrlPrinter();
    private final FeMdlPrinter feMdlPrinter = new FeMdlPrinter();
    private final FeDomainePrinter feDomainePrinter = new FeDomainePrinter();
    private final FeServicePrinter feServicePrinter = new FeServicePrinter();
    private final BeDomainePrinter beDomainePrinter = new BeDomainePrinter();
    private final BeRefDomainePrinter beRefDomainePrinter = new BeRefDomainePrinter();
    private final BeRepositoryPrinter beRepositoryPrinter = new BeRepositoryPrinter();
    private final BeRefRepositoryPrinter beRefRepositoryPrinter = new BeRefRepositoryPrinter();
    private final BeResourcePrinter beResourcePrinter = new BeResourcePrinter();
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
    private final FeListePagePrinter feListePagePrinter = new FeListePagePrinter();
    private final FeReducerPrinter feReducerPrinter = new FeReducerPrinter();
    private final FeElementPrinter feElementPrinter = new FeElementPrinter();

    public void process() {
        for (Page page : Context.getInstance().getPageList()) {
            if (page.estReelle()) {
                for (Element element : page.listeElement) {
                    printFeElementFiles(element);
                }
                feCtrlPrinter.print(page);
                feMdlPrinter.print(page);
            }
        }

        printFeGlobalFiles();
        printBeGlobalFiles();

        for (Entity entity : Context.getInstance().getEntityList()) {
            printFeEntityFiles(entity);
            printBeEntityFiles(entity);
        }

        for (Module module : Context.getInstance().getModuleList()) {
            printFeModuleFiles(module);
        }
    }

    private void printFePageFiles(Page page) {
        feCtrlPrinter.print(page);
        feMdlPrinter.print(page);
    }

    private void printFeElementFiles(Element element) {
        feElementPrinter.print(element);
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

    private void printFeEntityFiles(Entity entity) {
        feDomainePrinter.print(entity);
        feServicePrinter.print(entity);
    }

    private void printBeEntityFiles(Entity entity) {
        beRefDomainePrinter.print(entity);
        beDomainePrinter.print(entity);
        beRepositoryPrinter.print(entity);
        beRefRepositoryPrinter.print(entity);
        beResourcePrinter.print(entity);
        beLiqConstraintPrinter.print(entity);
        beLiqDataPrinter.print(entity);
        beLiqTablePrinter.print(entity);

    }

    private void printFeModuleFiles(Module module) {
        feI18nPrinter.print(module);
        feListePagePrinter.print(module);
        feModulePrinter.print(module);
        feReducerPrinter.print(module);
        feActionModule.print(module);
    }
}
