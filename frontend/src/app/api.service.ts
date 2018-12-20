import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {VeiculoModel} from "./core/models/VeiculoModel";
import {ViaModel} from "./core/models/ViaModel";
import {ProdutoModel} from "./core/models/ProdutoModel";
import {CalculoModel} from "./core/models/CalculoModel";

const endpoint = 'http://localhost:9010';
const httpOptions = {
    header: new HttpHeaders({
        'Content-Type': 'application/json'
    })
};

@Injectable({
    providedIn: 'root'
})
export class ApiService {

    constructor(private httpClient: HttpClient) {
    }

    getVeiculos(): Observable<VeiculoModel[]> {
        return this.httpClient.get(`${endpoint}/veiculo`);
    }

    getVias(): Observable<ViaModel[]> {
        return this.httpClient.get(`${endpoint}/via`);
    }

    getProdutos(): Observable<ProdutoModel[]> {
        return this.httpClient.get(`${endpoint}/produto`);
    }

    calcularTransporte(dados: any): Observable<CalculoModel> {
        return this.httpClient.post(`${endpoint}/transporte/calcular`, dados);
    }

    salvarCalcularTransporte(dados: any): Observable<CalculoModel> {
        return this.httpClient.post(`${endpoint}/transporte`, dados);
    }
}
