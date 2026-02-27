package dev.cruding.engine.gen;

import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.printer.impl.common.BeLiqMasterPrinter;
import dev.cruding.engine.printer.impl.element.FeElementPrinter;
import dev.cruding.engine.printer.impl.entity.BeBusinessPrinter;
import dev.cruding.engine.printer.impl.entity.BeDomainPrinter;
import dev.cruding.engine.printer.impl.entity.BeDtoPrinter;
import dev.cruding.engine.printer.impl.entity.BeFilterPrinter;
import dev.cruding.engine.printer.impl.entity.BeLiqConstraintPrinter;
import dev.cruding.engine.printer.impl.entity.BeLiqDataPrinter;
import dev.cruding.engine.printer.impl.entity.BeLiqTablePrinter;
import dev.cruding.engine.printer.impl.entity.BeRepositoryPrinter;
import dev.cruding.engine.printer.impl.entity.BeResourcePrinter;
import dev.cruding.engine.printer.impl.entity.BeSpecificationPrinter;
import dev.cruding.engine.printer.impl.entity.FeDomainPrinter;
import dev.cruding.engine.printer.impl.entity.FeServicePrinter;
import dev.cruding.engine.printer.impl.module.FeAclPrinter;
import dev.cruding.engine.printer.impl.module.FeActionPrinter;
import dev.cruding.engine.printer.impl.module.FeI18nPrinter;
import dev.cruding.engine.printer.impl.module.FeModulePrinter;
import dev.cruding.engine.printer.impl.module.FePageListPrinter;
import dev.cruding.engine.printer.impl.module.FeReducerPrinter;
import dev.cruding.engine.printer.impl.page.FeCtrlPrinter;
import dev.cruding.engine.printer.impl.page.FeHookPrinter;
import dev.cruding.engine.printer.impl.page.FeMdlPrinter;

public class Processor {

    private final FeCtrlPrinter feCtrlPrinter = new FeCtrlPrinter();
    private final FeMdlPrinter feMdlPrinter = new FeMdlPrinter();
    private final FeHookPrinter feHookPrinter = new FeHookPrinter();
    private final FeDomainPrinter feDomainPrinter = new FeDomainPrinter();
    private final FeServicePrinter feServicePrinter = new FeServicePrinter();
    private final BeDomainPrinter beDomainPrinter = new BeDomainPrinter();
    private final BeDtoPrinter beDtoPrinter = new BeDtoPrinter();
    private final BeFilterPrinter beFilterPrinter = new BeFilterPrinter();
    private final BeSpecificationPrinter beSpecificationPrinter = new BeSpecificationPrinter();
    private final BeRepositoryPrinter beRepositoryPrinter = new BeRepositoryPrinter();
    private final BeResourcePrinter beResourcePrinter = new BeResourcePrinter();
    private final BeBusinessPrinter beBusinessPrinter = new BeBusinessPrinter();
    private final BeLiqConstraintPrinter beLiqConstraintPrinter = new BeLiqConstraintPrinter();
    private final BeLiqDataPrinter beLiqDataPrinter = new BeLiqDataPrinter();
    private final BeLiqMasterPrinter beLiqMasterPrinter = new BeLiqMasterPrinter();
    private final BeLiqTablePrinter beLiqTablePrinter = new BeLiqTablePrinter();
    private final FeModulePrinter feModulePrinter = new FeModulePrinter();
    private final FeI18nPrinter feI18nPrinter = new FeI18nPrinter();
    private final FeActionPrinter feActionPrinter = new FeActionPrinter();
    private final FeAclPrinter feAclPrinter = new FeAclPrinter();
    private final FePageListPrinter feListePagePrinter = new FePageListPrinter();
    private final FeReducerPrinter feReducerPrinter = new FeReducerPrinter();
    private final FeElementPrinter feElementPrinter = new FeElementPrinter();

    public void execute() {

        printBeGlobalFiles();

        for (Page page : Context.getInstance().getPageList()) {
            if (page.containsComponent()) {
                for (Element element : page.elementList) {
                    printFeElementFiles(element);
                }
                feCtrlPrinter.print(page);
                feMdlPrinter.print(page);
                feHookPrinter.print(page);
            }
        }


        for (Entity entity : Context.getInstance().getEntityList()) {
            printFeEntityFiles(entity);
            printBeEntityFiles(entity);
        }

        for (Module module : Context.getInstance().getModuleList()) {
            printFeModuleFiles(module);
        }
    }

    private void printFeElementFiles(Element element) {
        if (!element.fake) {
            feElementPrinter.print(element);
        }
    }


    private void printBeGlobalFiles() {
        beLiqMasterPrinter.print();
    }

    private void printFeEntityFiles(Entity entity) {
        feDomainPrinter.print(entity);
        feServicePrinter.print(entity);
    }

    private void printBeEntityFiles(Entity entity) {
        beDomainPrinter.print(entity);
        if (!entity.isReferenceData()) {
            beResourcePrinter.print(entity);
            beBusinessPrinter.print(entity);
            beRepositoryPrinter.print(entity);
        }
        beDtoPrinter.print(entity);
        beFilterPrinter.print(entity);
        beSpecificationPrinter.print(entity);
        beLiqConstraintPrinter.print(entity);
        beLiqDataPrinter.print(entity);
        beLiqTablePrinter.print(entity);

    }

    private void printFeModuleFiles(Module module) {
        feI18nPrinter.print(module);
        feListePagePrinter.print(module);
        feModulePrinter.print(module);
        feReducerPrinter.print(module);
        feActionPrinter.print(module);
        feAclPrinter.print(module);
    }
}
