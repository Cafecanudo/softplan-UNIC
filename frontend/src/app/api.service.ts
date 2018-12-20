import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";

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

    getVeiculos(): Observable<any> {
        return this.httpClient.get(`${endpoint}/veiculo`);
    }

    getVias(): Observable<any> {
        return this.httpClient.get(`${endpoint}/via`);
    }

    getProdutos(): Observable<any> {
        return this.httpClient.get(`${endpoint}/produto`);
    }

    calcularTransporte(dados: any): Observable<any> {
        return this.httpClient.post(`${endpoint}/transporte/calcular`, dados);
    }

    salvarCalcularTransporte(dados: any): Observable<any> {
        return this.httpClient.post(`${endpoint}/transporte`, dados);
    }
}
