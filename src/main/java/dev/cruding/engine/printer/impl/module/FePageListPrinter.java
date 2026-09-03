package dev.cruding.engine.printer.impl.module;

import dev.cruding.engine.gen.Context;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.flow.JsFlow;
import dev.cruding.engine.gen.Module;
import dev.cruding.engine.gen.Page;
import dev.cruding.engine.printer.Printer;

public class FePageListPrinter extends Printer {

    private static final Pattern ROUTE_PARAMETER = Pattern.compile(":([A-Za-z][A-Za-z0-9_]*)");

    public FePageListPrinter(Context context) {
        super(context);
    }

    public void print(Module module) {
        JsFlow f = new JsFlow();
        ArrayList<Page> pageList = sortedPageList(module);
        /* *********************************************************************** */
        if (module.isParent) {
            f.addJsImport("{ FontAwesomeIcon }", "@fortawesome/react-fontawesome");
            f.addJsImport("{ " + module.icon + " }", "@fortawesome/free-solid-svg-icons");
            f.addJsImport("{ ContexteViewProvider, PageDefinition }", "waxant");
            f.addJsImport("{ Outlet }", "react-router");
            f.flushJsImportBlock();
            f.L("");
            f.L("export const Page", module.unameLast, ": PageDefinition = {");
            f.L____("key: 'Page", module.unameLast, "',");
            f.L____("path: '/", module.lnameLast, "',");
            f.L____("toPath: () => '/", module.lnameLast, "',");
            f.L____("icone: <FontAwesomeIcon icon={", module.icon, "} />,");
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
        } else if (module.isTabMenu) {

            if (pageList.size() > 0) {
                int idx = module.path.length();
                for (Page page : pageList) {
                    if (page.containsComponent()) {
                        f.addJsImport("View" + page.uc, "." + page.path.substring(idx) + "/View" + page.uc);
                    }
                }
            }
            f.addJsImport("{ FontAwesomeIcon }", "@fortawesome/react-fontawesome");
            f.addJsImport("{ " + module.icon + " }", "@fortawesome/free-solid-svg-icons");
            f.addJsImport("{ ContexteViewProvider, MenuOnglet, Onglet, PageDefinition, Section }", "waxant");
            f.flushJsImportBlock();
            f.L("");
            f.L("export const Page", module.unameLast, ": PageDefinition = {");
            f.L____("key: 'Page", module.unameLast, "',");
            f.L____("path: '/", getMenuPath(module), "',");
            f.L____("toPath: () => '/", getMenuPath(module), "',");
            f.L____("icone: <FontAwesomeIcon icon={", module.icon, "} />,");
            f.L____("menu: '", getMenuPath(module), "',");
            f.L____("view: (");
            f.L________("<ContexteViewProvider uc=\"Uc", module.unameLast, "\">");
            f.L____________("<Section>");
            pageList.sort(Comparator.comparingInt(Page::getPosition));
            f.L________________("<MenuOnglet ongletActif=\"", StringUtils.uncapitalize(pageList.get(0).uc), "\" fond=\"fonce\">");
            for (Page page : pageList) {
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
            if (pageList.size() > 0) {
                int idx = module.path.length();
                Page pageIndex = module.requirePageIndex();
                f.addJsImport("{ FontAwesomeIcon }", "@fortawesome/react-fontawesome");
                f.addJsImport("{ " + pageIndex.icon + " }", "@fortawesome/free-solid-svg-icons");
                f.addJsImport("{ ContexteViewProvider, PageDefinition }", "waxant");
                for (Page page : pageList) {
                    if (page.containsComponent()) {
                        f.addJsImport("View" + page.uc, "." + page.path.substring(idx) + "/View" + page.uc);
                    }
                }
                f.flushJsImportBlock();
                f.L("");
                for (Page page : pageList) {
                    f.L("export const Page", page.uc, ": PageDefinition = {");
                    f.L____("key: 'Page", page.uc, "',");
                    String route = getRoute(module, page);
                    f.L____("path: '", route, "',");
                    if (ROUTE_PARAMETER.matcher(route).find()) {
                        f.L____("toPath: (args) => `", toRouteTemplate(route), "`,");
                    } else {
                        f.L____("toPath: () => '", route, "',");
                    }
                    if (page.name.equals(pageIndex.name)) {
                        f.L____("icone: <FontAwesomeIcon icon={", pageIndex.icon, "} />,");
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
            for (Page page : pageList) {
                if (page.containsComponent()) {
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

    private String getRoute(Module module, Page page) {
        if (page.route() != null) {
            return page.route();
        }
        String route = "/" + getPath(module, page);
        return page.pathById ? route + "/:id" + page.entityUname : route;
    }

    private String toRouteTemplate(String route) {
        Matcher matcher = ROUTE_PARAMETER.matcher(route);
        StringBuilder template = new StringBuilder();
        while (matcher.find()) {
            matcher.appendReplacement(template, Matcher.quoteReplacement("${args." + matcher.group(1) + "}"));
        }
        matcher.appendTail(template);
        return template.toString();
    }

    private String getMenuPath(Module module) {
        return module.path.substring(8);
    }

}
