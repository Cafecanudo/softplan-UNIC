import {AfterContentInit, Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {MatTableDataSource} from "@angular/material";
import {ApiService} from "../api.service";

export interface DadosInput {
    input?: any;
    select?: Via[] | Produto[];
    tabela: {
        colunms: string[];
        data: any;
    };
}

export interface Veiculo {
    id: string;
    nome: string;
    fatorMultiplicador: number;
}

export interface Via {
    id: string;
    nome: string;
    valor: number;
}

export interface Rota {
    via: Via;
    quilometros: number;
}

export interface Produto {
    id: string;
    nome: string;
    image?: string;
    peso: number;
    valor: number;
}

export interface Carga {
    produto: Produto;
    quantidade: number;
}

// export interface CalculoRequest {
//     veiculo: Veiculo;
//     rotas: Rota[];
//     carga: Carga[];
//     calculo: Calculo;
// }

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
export class CaluclarTransporteComponent implements OnInit, AfterContentInit {

    constructor(public apiService: ApiService) {
    }

    enviarInformacoes: boolean = false;

    veiculosDiponiveis: Veiculo[] = [];

    dadosVia: DadosInput = {
        input: {
            viaSelecionada: new FormControl(null, {validators: Validators.required}),
            quantidade: new FormControl(null, {validators: Validators.required}),
        },
        select: [],
        tabela: {
            colunms: ['nome', 'valor', 'quilometros', 'total'],
            data: new MatTableDataSource([])
        }
    };

    calcularTodaTransporte() {
        var valorTotal: number = 0;
        this.dadosVia.tabela.data.data.forEach((rota: Rota) => {
            valorTotal += rota.quilometros * rota.via.valor;
        });
        return valorTotal;
    }

    addVia() {
        var rota: Rota = {
            via: this.dadosVia.input.viaSelecionada.value,
            quilometros: Number(this.dadosVia.input.quantidade.value)
        };
        this.dadosVia.input.viaSelecionada.reset();
        this.dadosVia.input.quantidade.reset();
        this.dadosVia.tabela.data.data = [...this.dadosVia.tabela.data.data, rota];
    }

    dadosProdutos: DadosInput = {
        input: {
            produtoSelecionada: new FormControl(null, {validators: Validators.required}),
            quantidade: new FormControl(null, {validators: Validators.required}),
        },
        select: [
            {id: 'steak-0', nome: 'Bloco Cerâmico Vedação 11,5x14x24cm Jad', peso: 1.2, valor: 10},
            {id: 'tacos-2', nome: 'Telha de PVC Cumeeira Central Colonial 56x90cm Axton', peso: 2.0, valor: 45.5}
        ],
        tabela: {
            colunms: ['nome', 'peso', 'valor', 'quantidade', 'total'],
            data: new MatTableDataSource([])
        }
    };

    addProduto() {
        var carga: Carga = {
            produto: this.dadosProdutos.input.produtoSelecionada.value,
            quantidade: this.dadosProdutos.input.quantidade.value,
        };
        this.dadosProdutos.input.produtoSelecionada.reset();
        this.dadosProdutos.input.quantidade.reset();
        this.dadosProdutos.tabela.data.data = [...this.dadosProdutos.tabela.data.data, carga];
    }

    calcularTodaMercadotia() {
        var valorTotal: number = 0;
        this.dadosProdutos.tabela.data.data.forEach((carga: Carga) => {
            valorTotal += carga.quantidade * carga.produto.valor
        });
        return valorTotal;
    }

    calcularPesoTotal() {
        var pesoTotal: number = 0;
        this.dadosProdutos.tabela.data.data.forEach((carga: Carga) => {
            pesoTotal += carga.quantidade * carga.produto.peso
        });
        return pesoTotal;
    }

    dadosCalculo: DadosInput = {
        tabela: {
            colunms: ['valorTransporte', 'valorMercadoria', 'quilometro', 'pesoTotal'],
            data: new MatTableDataSource([])
        }
    };

    calculoGroup = new FormGroup({
        veiculo: new FormControl(null, {
            validators: Validators.required
        }),
        rotas: new FormControl(null, {
            validators: Validators.required
        }),
        cargas: new FormControl(null, {
            validators: Validators.required
        }),
        calculo: new FormControl(null, {
            validators: Validators.required
        })
    });

    calcularCustoTransporte() {
        // this.calculoGroup.controls.rotas.setValue(this.dadosVia.tabela.data.data);
        // this.calculoGroup.controls.cargas.setValue(this.dadosProdutos.tabela.data.data);
        this.enviarInformacoes = true;
    }

    loadVeiculo() {
        this.apiService.getVeiculos().subscribe((veiculos) => {
            this.veiculosDiponiveis = veiculos;
        });
    }

    loadVias() {
        this.apiService.getVias().subscribe((vias) => {
            this.dadosVia.tabela.data.data = [...vias];
        });
    }

    ngOnInit(): void {

    }

    ngAfterContentInit(){
        this.loadVeiculo();
        this.loadVias();
    }

}
