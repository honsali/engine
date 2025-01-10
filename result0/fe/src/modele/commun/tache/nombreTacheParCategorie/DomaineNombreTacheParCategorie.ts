import { IPagination } from 'modele/commun/pagination/DomainePagination';

export interface INombreTacheParCategorie {
    id?: number;
    idNombreTacheParCategorie?: number;
    categorieTache?: string;
    nombre?: number;
}

export interface IRequeteNombreTacheParCategorie extends INombreTacheParCategorie, IPagination { }
export interface IListePagineeNombreTacheParCategorie {
    liste?: INombreTacheParCategorie[];
    pagination?: IPagination;
}