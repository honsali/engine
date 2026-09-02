package dev.cruding.engine.gen;

import java.util.Objects;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.printer.impl.common.BeLiqMasterPrinter;
import dev.cruding.engine.printer.impl.element.FeElementPrinter;
import dev.cruding.engine.printer.impl.entity.BeBusinessPrinter;
import dev.cruding.engine.printer.impl.entity.BeControllerPrinter;
import dev.cruding.engine.printer.impl.entity.BeDomainPrinter;
import dev.cruding.engine.printer.impl.entity.BeLiqConstraintPrinter;
import dev.cruding.engine.printer.impl.entity.BeLiqDataPrinter;
import dev.cruding.engine.printer.impl.entity.BeLiqTablePrinter;
import dev.cruding.engine.printer.impl.entity.BeMapperPrinter;
import dev.cruding.engine.printer.impl.entity.BeRepositoryPrinter;
import dev.cruding.engine.printer.impl.entity.BeRequestPrinter;
import dev.cruding.engine.printer.impl.entity.BeResponsePrinter;
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

    private final Context context;
    private final FeCtrlPrinter feCtrlPrinter;
    private final FeMdlPrinter feMdlPrinter;
    private final FeHookPrinter feHookPrinter;
    private final FeDomainPrinter feDomainPrinter;
    private final FeServicePrinter feServicePrinter;
    private final BeDomainPrinter beDomainPrinter;
    private final BeRequestPrinter beRequestPrinter;
    private final BeResponsePrinter beResponsePrinter;
    private final BeMapperPrinter beMapperPrinter;
    private final BeSpecificationPrinter beSpecificationPrinter;
    private final BeRepositoryPrinter beRepositoryPrinter;
    private final BeControllerPrinter beResourcePrinter;
    private final BeBusinessPrinter beBusinessPrinter;
    private final BeLiqConstraintPrinter beLiqConstraintPrinter;
    private final BeLiqDataPrinter beLiqDataPrinter;
    private final BeLiqMasterPrinter beLiqMasterPrinter;
    private final BeLiqTablePrinter beLiqTablePrinter;
    private final FeModulePrinter feModulePrinter;
    private final FeI18nPrinter feI18nPrinter;
    private final FeActionPrinter feActionPrinter;
    private final FeAclPrinter feAclPrinter;
    private final FePageListPrinter feListePagePrinter;
    private final FeReducerPrinter feReducerPrinter;
    private final FeElementPrinter feElementPrinter;

    public Processor(Context context) {
        this.context = Objects.requireNonNull(context, "Processor Context cannot be null");
        feCtrlPrinter = new FeCtrlPrinter(context);
        feMdlPrinter = new FeMdlPrinter(context);
        feHookPrinter = new FeHookPrinter(context);
        feDomainPrinter = new FeDomainPrinter(context);
        feServicePrinter = new FeServicePrinter(context);
        beDomainPrinter = new BeDomainPrinter(context);
        beRequestPrinter = new BeRequestPrinter(context);
        beResponsePrinter = new BeResponsePrinter(context);
        beMapperPrinter = new BeMapperPrinter(context);
        beSpecificationPrinter = new BeSpecificationPrinter(context);
        beRepositoryPrinter = new BeRepositoryPrinter(context);
        beResourcePrinter = new BeControllerPrinter(context);
        beBusinessPrinter = new BeBusinessPrinter(context);
        beLiqConstraintPrinter = new BeLiqConstraintPrinter(context);
        beLiqDataPrinter = new BeLiqDataPrinter(context);
        beLiqMasterPrinter = new BeLiqMasterPrinter(context);
        beLiqTablePrinter = new BeLiqTablePrinter(context);
        feModulePrinter = new FeModulePrinter(context);
        feI18nPrinter = new FeI18nPrinter(context);
        feActionPrinter = new FeActionPrinter(context);
        feAclPrinter = new FeAclPrinter(context);
        feListePagePrinter = new FePageListPrinter(context);
        feReducerPrinter = new FeReducerPrinter(context);
        feElementPrinter = new FeElementPrinter(context);
    }

    public void execute() {

        printBeGlobalFiles();

        // Page and element files are generated before module files because some labels
        // are collected as a side effect of rendering components.
        for (Page page : context.getPageList()) {
            if (page.containsComponent()) {
                for (Element element : page.elementList) {
                    printFeElementFiles(element);
                }
                feCtrlPrinter.print(page);
                feMdlPrinter.print(page);
                feHookPrinter.print(page);
            }
        }


        for (Entity entity : context.getEntityList()) {
            printFeEntityFiles(entity);
            printBeEntityFiles(entity);
        }

        for (Module module : context.getModuleList()) {
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
            beRequestPrinter.print(entity);
            beResourcePrinter.print(entity);
            beBusinessPrinter.print(entity);
            beResponsePrinter.print(entity);
        }
        beRepositoryPrinter.print(entity);
        beMapperPrinter.print(entity);
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
