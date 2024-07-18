package dev.cruding.engine.gen;

import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.printer.impl.commun.BeLiqMasterPrinter;
import dev.cruding.engine.printer.impl.commun.FeGoToPrinter;
import dev.cruding.engine.printer.impl.commun.FeHasRightPrinter;
import dev.cruding.engine.printer.impl.commun.FeLabelPrinter;
import dev.cruding.engine.printer.impl.commun.FeMenuPrinter;
import dev.cruding.engine.printer.impl.commun.FeRoutesPrinter;
import dev.cruding.engine.printer.impl.commun.FeStorePrinter;
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
import dev.cruding.engine.printer.impl.page.FeViewPrinter;

public class Processor {

    private final FeCtrlPrinter feCtrlPrinter = new FeCtrlPrinter();
    private final FeMdlPrinter feMdlPrinter = new FeMdlPrinter();
    private final FeViewPrinter feViewPrinter = new FeViewPrinter();
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
    private final FeLabelPrinter feLabelPrinter = new FeLabelPrinter();
    private final FeMenuPrinter feMenuPrinter = new FeMenuPrinter();
    private final FeHasRightPrinter feHasRightPrinter = new FeHasRightPrinter();
    private final FeGoToPrinter feGoToPrinter = new FeGoToPrinter();
    private final FeModulePrinter feModulePrinter = new FeModulePrinter();
    private final FeI18nPrinter feI18nPrinter = new FeI18nPrinter();
    private final FeActionModule feActionModule = new FeActionModule();
    private final FeListePagePrinter feListePagePrinter = new FeListePagePrinter();
    private final FeReducerPrinter feReducerPrinter = new FeReducerPrinter();

    public void process() {
        for (Page page : Context.getInstance().getPageList()) {
            for (Element element : page.listeElement) {
                element.print(page);
            }
            if (!page.componentList.isEmpty()) {
                feCtrlPrinter.print(page);
                feMdlPrinter.print(page);
                feViewPrinter.print(page);
            }
        }
        feStorePrinter.print();
        feRoutesPrinter.print();
        feLabelPrinter.print();
        feMenuPrinter.print();
        feHasRightPrinter.print();
        feGoToPrinter.print();

        beLiqMasterPrinter.print();

        for (Entity entity : Context.getInstance().getEntityList()) {
            feDomainePrinter.print(entity);
            beRefDomainePrinter.print(entity);
            feServicePrinter.print(entity);
            beDomainePrinter.print(entity);
            beRepositoryPrinter.print(entity);
            beRefRepositoryPrinter.print(entity);
            beResourcePrinter.print(entity);
            beLiqConstraintPrinter.print(entity);
            beLiqDataPrinter.print(entity);
            beLiqTablePrinter.print(entity);
        }

        for (Module module : Context.getInstance().getModuleList()) {
            feI18nPrinter.print(module);
            feListePagePrinter.print(module);
            feModulePrinter.print(module);
            feReducerPrinter.print(module);
            feActionModule.print(module);
        }
    }

}
