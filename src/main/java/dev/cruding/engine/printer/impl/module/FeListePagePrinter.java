package dev.cruding.engine.printer.impl.module;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Contexte;
import dev.cruding.engine.gen.Module;
import dev.cruding.engine.gen.Page;
import dev.cruding.engine.printer.Printer;

public class FeListePagePrinter extends Printer {

    public void print(Module module) {
        ViewFlow f = new ViewFlow();

        /* *********************************************************************** */
        if (module.estParent) {
            f.addJsImport("{ FontAwesomeIcon }", "@fortawesome/react-fontawesome");
            f.addJsImport("{ " + module.icone + " }", "@fortawesome/free-solid-svg-icons");
            f.addJsImport("{ ContexteViewProvider, PageDefinition }", "waxant");
            f.addJsImport("{ Outlet }", "react-router");
            f.flushJsImportBloc();
            f.L("");
            f.L("export const Page", module.unameLast, ": PageDefinition = {");
            f.L____("key: 'Page", module.unameLast, "',");
            f.L____("path: '/", module.lnameLast, "',");
            f.L____("toPath: (args) => '/", module.lnameLast, "',");
            f.L____("icone: <FontAwesomeIcon icon={", module.icone, "} />,");
            f.L____("menu: '", getMenuPath(module), "',");
            f.L____("view: (");
            f.L________("<ContexteViewProvider uc=\"Uc", module.unameLast, "\">");
            f.L____________("<Outlet />");
            f.L________("</ContexteViewProvider>");
            f.L____("),");
            f.L("};");
            f.L("");
            f.L("const ListePage", module.unameLast, " = [");
            f.L____("Page", module.unameLast, ",");
            f.L("];");
        } else if (module.estMenuOnglet) {
            List<Page> listePage = new ArrayList<>(Contexte.getInstance().getPageList(module));
            if (listePage.size() > 0) {
                Collections.sort(listePage);
                int idx = module.path.length();
                for (Page page : listePage) {
                    if (page.estReelle()) {
                        f.addJsImport("View" + page.uc, "." + page.path.substring(idx) + "/View" + page.uc);
                    }
                }
            }
            f.addJsImport("{ FontAwesomeIcon }", "@fortawesome/react-fontawesome");
            f.addJsImport("{ " + module.icone + " }", "@fortawesome/free-solid-svg-icons");
            f.addJsImport("{ ContexteViewProvider, MenuOnglet, Onglet, PageDefinition, Section }", "waxant");
            f.flushJsImportBloc();
            f.L("");
            f.L("export const Page", module.unameLast, ": PageDefinition = {");
            f.L____("key: 'Page", module.unameLast, "',");
            f.L____("path: '/", getMenuPath(module), "',");
            f.L____("toPath: (args) => '/", getMenuPath(module), "',");
            f.L____("icone: <FontAwesomeIcon icon={", module.icone, "} />,");
            f.L____("menu: '", getMenuPath(module), "',");
            f.L____("view: (");
            f.L________("<ContexteViewProvider uc=\"Uc", module.unameLast, "\">");
            f.L____________("<Section>");
            listePage.sort(Comparator.comparingInt(Page::getPosition));
            f.L________________("<MenuOnglet ongletActif=\"", StringUtils.uncapitalize(listePage.get(0).uc), "\" fond=\"fonce\">");
            for (Page page : listePage) {
                f.L____________________("<Onglet key=\"", StringUtils.uncapitalize(page.uc), "\">");
                f.L________________________("<ContexteViewProvider uc=\"Uc", page.uc, "\">");
                f.L____________________________("<View", page.uc, " />");
                f.L________________________("</ContexteViewProvider>");
                f.L____________________("</Onglet>");
            }
            f.L____________("</MenuOnglet>");
            f.L____________("</Section>");
            f.L________("</ContexteViewProvider>");
            f.L____("),");
            f.L("};");
            f.L("");
            f.L("const ListePage", module.unameLast, " = [");
            f.L____("Page", module.unameLast, ",");
            f.L("];");
        } else {
            List<Page> listePage = new ArrayList<>(Contexte.getInstance().getPageList(module));
            if (listePage.size() > 0) {
                Collections.sort(listePage);
                int idx = module.path.length();
                Page pageIndex = Contexte.getInstance().getPage(module.pageIndex);
                f.addJsImport("{ FontAwesomeIcon }", "@fortawesome/react-fontawesome");
                f.addJsImport("{ " + pageIndex.icone + " }", "@fortawesome/free-solid-svg-icons");
                f.addJsImport("{ ContexteViewProvider, PageDefinition }", "waxant");
                for (Page page : listePage) {
                    if (page.estReelle()) {
                        f.addJsImport("View" + page.uc, "." + page.path.substring(idx) + "/View" + page.uc);
                    }
                }
                f.flushJsImportBloc();
                f.L("");
                for (Page page : listePage) {
                    f.L("export const Page", page.uc, ": PageDefinition = {");
                    f.L____("key: 'Page", page.uc, "',");
                    if (page.pathById) {
                        f.L____("path: '/", getPath(module, page), "/:id", page.entiteUname, "',");
                        f.L____("toPath: (args) => `", getToPath(module, page), "/${args.id", page.entiteUname, "}`,");
                    } else {
                        f.L____("path: '/", getPath(module, page), "',");
                        f.L____("toPath: (args) => '", getToPath(module, page), "',");
                    }
                    if (page.name.equals(pageIndex.name)) {
                        f.L____("icone: <FontAwesomeIcon icon={", pageIndex.icone, "} />,");
                        f.L____("menu: '", getMenuPath(module), "',");
                    }
                    f.L____("view: (");
                    f.L________("<ContexteViewProvider uc=\"Uc", page.uc, "\">");
                    f.L____________("<View", page.uc, " />");
                    f.L________("</ContexteViewProvider>");
                    f.L____("),");
                    f.L("};");
                    f.L("");
                }

            }

            f.L("const ListePage", module.unameLast, " = [");
            int i = 0;
            for (Page page : listePage) {
                if (page.estReelle()) {
                    f.L____("Page", page.uc, ",");
                    if (i == 0) {
                        f.__(" //");
                        i++;
                    }
                }
            }
            f.L("];");
        }
        f.L("export default ListePage", module.unameLast, ";");
        /* *********************************************************************** */

        String s = f.toString();
        printFile(s, getBasePath() + "/fe/src/" + module.path + "/ListePage" + module.unameLast + ".tsx");
    }

    private String getPath(Module module, Page page) {
        return module.path.substring(8) + "/" + page.actionLname;
    }

    private String getMenuPath(Module module) {
        return module.path.substring(8);
    }

    private String getToPath(Module module, Page page) {
        return module.path.substring(7) + "/" + page.actionLname;
    }
}
