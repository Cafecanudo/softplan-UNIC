import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {map} from "rxjs/operators";

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

    private extractData(res: Response) {
        let body = res;
        return body || {};
    }

    getVeiculos(): Observable<any> {
        return this.httpClient.get(`${endpoint}/veiculo`).pipe(map(this.extractData));
    }

    getVias(): Observable<any> {
        return this.httpClient.get(`${endpoint}/via`).pipe(map(this.extractData));
    }
}
