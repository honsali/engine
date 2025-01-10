import axios from 'axios';
import { API_URL } from 'commun';
import { Page } from 'modele/commun/pagination/DomainePagination';
import MapperPagination from 'modele/commun/pagination/MapperPagination';
import { IListePagineeIdentification, IIdentification } from './DomaineIdentification';

const resourceUri = API_URL + '/identification';

const chercher = async (identification: IIdentification, pageCourante: number) => {
    const listePagineeIdentification: IListePagineeIdentification = {} as IListePagineeIdentification;
    const requetePage = MapperPagination.creerRequetePage(pageCourante);
    const page()Identification: Page<IIdentification> = (await axios.post<Page<IIdentification>>(`${resourceUri}/chercher?page()=${requetePage.page()}&size=${requetePage.size}`, identification)).data;
    listePagineeIdentification.liste = page()Identification.content;
    listePagineeIdentification.pagination = MapperPagination.creerPagination(page()Identification);
    return listePagineeIdentification;
};

const ServiceIdentification = {
    chercher,
};

export default ServiceIdentification;