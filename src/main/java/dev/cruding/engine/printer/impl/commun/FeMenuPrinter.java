package dev.cruding.engine.printer.impl.commun;

import org.apache.commons.lang3.StringUtils;

import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.gen.Page;
import dev.cruding.engine.printer.Printer;

public class FeMenuPrinter extends Printer {

    public void print() {
        Flow f = new Flow();

        f.__("import { HomeOutlined, MinusOutlined } from '@ant-design/icons';");
        f.L("import { Menu } from 'antd';");
        f.L("import labels from 'core/util/labels';");
        f.L("import { useLocation, useNavigate } from 'react-router';");
        f.L("import { Link } from 'react-router-dom';");
        f.L("");
        f.L("const items = [");
        f.L("    { key: '/', icon: <HomeOutlined />, label: <Link to=\"/\">{labels.get('accueil')}</Link> },");
        for (Page p : pageList()) {
            if (p.uc.indexOf("Lister") > -1) {
                f.L("    { key: '/", p.path, "', icon: <MinusOutlined />, label: <Link to=\"/", p.path, "\">{labels.get('", StringUtils.uncapitalize(p.uc.substring(6)), "')}</Link> },");
            } else if (p.uc.indexOf("Chercher") > -1) {
                f.L("    { key: '/", p.path, "', icon: <MinusOutlined />, label: <Link to=\"/", p.path, "\">{labels.get('", StringUtils.uncapitalize(p.uc.substring(8)), "')}</Link> },");
            }
        }
        f.L("];");
        f.L("");
        f.L("const MenuAdmin = () => {");
        f.L("    const navigate = useNavigate();");
        f.L("    const location = useLocation();");
        f.L("    const openMenu = () => {");
        f.L("        const current = location.pathname;");
        f.L("        return [current.substring(0, current.indexOf('/', 1))];");
        f.L("    };");
        f.L("");
        f.L("    return <Menu defaultOpenKeys={openMenu()} items={items} mode=\"inline\" theme=\"dark\" onClick={(e) => navigate(e.key)} selectedKeys={[location.pathname]} />;");
        f.L("};");
        f.L("");
        f.L("export default MenuAdmin;");

        String s = f.toString();
        printFile(s, getBasePath() + "/fe/src/domaines/admin/menu.tsx");
    }
}