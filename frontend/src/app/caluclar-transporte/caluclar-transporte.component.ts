import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";

export interface Veiculo {
    id: string;
    descricao: string;
}

export interface produto {
    id: string;
    descricao: string;
    peso: Number;
}

export interface Via {
    id: string;
    descricao: string;
    quilometro?: number;
}

export interface Calculo {
    valorTransporte: number;
    valorMercadoria: number;
    quilometro: number;
    pesoTotal: number;
}

@Component({
    selector: 'mns-caluclar-transporte',
    templateUrl: './caluclar-transporte.component.html',
})
export class CaluclarTransporteComponent implements OnInit {

    calculoGroup = new FormGroup({
        veiculo: new FormControl(''),
    });

    veiculosSelect: Veiculo[] = [
        {id: 'steak-0', descricao: 'Carro1'},
        {id: 'tacos-2', descricao: 'Carro2'}
    ];

    viaSelect: Via[] = [
        {id: 'steak-0', descricao: 'Pavimentada'},
        {id: 'tacos-2', descricao: 'Maritima'}
    ];

    viasAdicionadas: Via[] = [
        {id: "YXdkYXdkYXc", descricao: 'Pavimentada', quilometro: 152.2},
    ];

    columnsVias: string[] = ['descricao', 'quilometro'];
    dataSourceVia = this.viasAdicionadas;

    /*-------------------------------------------------------------------------------------------*/
    produtoSelect: produto[] = [
        {id: 'steak-0', descricao: 'Bloco Cerâmico Vedação 11,5x14x24cm Jad', peso: 1.2},
        {id: 'tacos-2', descricao: 'Telha de PVC Cumeeira Central Colonial 56x90cm Axton', peso: 2.0}
    ];

    produtoAdicionados: produto[] = [
        {id: 'steak-0', descricao: 'Bloco Cerâmico Vedação 11,5x14x24cm Jad', peso: 1.2},
        {id: 'tacos-2', descricao: 'Telha de PVC Cumeeira Central Colonial 56x90cm Axton', peso: 2.0}
    ];

    columnsProduto: string[] = ['descricao', 'peso', 'quantidade'];
    dataSourceProduto = this.produtoAdicionados;
    /*-------------------------------------------------------------------------------------------*/

    columnsCalculo: string[] = ['valorTransporte', 'valorMercadoria', 'quilometro', 'pesoTotal'];
    calculoEfetuado: Calculo[] = [
        {valorTransporte: 4521.01, valorMercadoria: 145.25, quilometro: 125, pesoTotal: 8000},
    ];

    ngOnInit(): void {

    }

}
