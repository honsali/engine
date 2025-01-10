import { HomeOutlined, MinusOutlined } from '@ant-design/icons';
import { Menu } from 'antd';
import labels from 'core/util/labels';
import { useLocation, useNavigate } from 'react-router';
import { Link } from 'react-router-dom';

const items = [
    { key: '/', icon: <HomeOutlined />, label: <Link to="/">{labels.get('accueil')}</Link> },
    { key: '/modules/instruction/tiers/entiteAdverse/lister', icon: <MinusOutlined />, label: <Link to="/modules/instruction/tiers/entiteAdverse/lister">{labels.get('entiteAdverse')}</Link> },
    { key: '/modules/execution/questionnaire/lister', icon: <MinusOutlined />, label: <Link to="/modules/execution/questionnaire/lister">{labels.get('questionnaire')}</Link> },
    { key: '/modules/conception/modeleQuestionnaire/lister', icon: <MinusOutlined />, label: <Link to="/modules/conception/modeleQuestionnaire/lister">{labels.get('modeleQuestionnaire')}</Link> },
    { key: '/modules/instruction/tiers/lister', icon: <MinusOutlined />, label: <Link to="/modules/instruction/tiers/lister">{labels.get('tiers')}</Link> },
    { key: '/modules/identification/chercher', icon: <MinusOutlined />, label: <Link to="/modules/identification/chercher">{labels.get('identification')}</Link> },
    { key: '/modules/instruction/tiers/victime/lister', icon: <MinusOutlined />, label: <Link to="/modules/instruction/tiers/victime/lister">{labels.get('victime')}</Link> },
];

const MenuAdmin = () => {
    const navigate = useNavigate();
    const location = useLocation();
    const openMenu = () => {
        const current = location.pathname;
        return [current.substring(0, current.indexOf('/', 1))];
    };

    return <Menu defaultOpenKeys={openMenu()} items={items} mode="inline" theme="dark" onClick={(e) => navigate(e.key)} selectedKeys={[location.pathname]} />;
};

export default MenuAdmin;