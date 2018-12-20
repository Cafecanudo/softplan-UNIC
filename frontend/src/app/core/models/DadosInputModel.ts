import {ViaModel} from "./ViaModel";
import {ProdutoModel} from "./ProdutoModel";

export interface DadosInputModel {
    input?: any;
    select?: ViaModel[] | ProdutoModel[];
    tabela: {
        colunms: string[];
        data: any;
    };
}