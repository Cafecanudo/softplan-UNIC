import {AfterContentInit, Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {MatTableDataSource} from "@angular/material";
import {ApiService} from "../api.service";
import {VeiculoModel} from "../core/models/VeiculoModel";
import {DadosInputModel} from "../core/models/DadosInputModel";
import {RotaModel} from "../core/models/RotaModel";
import {CargaModel} from "../core/models/CargaModel";
import {MatSnackBarComponent} from "../core/MatSnackBarComponent";

@Component({
    selector: 'mns-caluclar-transporte',
    templateUrl: './caluclar-transporte.component.html',
})
export class CaluclarTransporteComponent implements OnInit, AfterContentInit {

    constructor(public apiService: ApiService, private snackBar: MatSnackBarComponent) {
    }

    enviarInformacoes: boolean = false;

    veiculosDiponiveis: VeiculoModel[] = [];

    dadosVia: DadosInputModel = {
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
        this.dadosVia.tabela.data.data.forEach((rota: RotaModel) => {
            valorTotal += rota.quilometros * rota.via.valor;
        });
        return valorTotal;
    }

    addVia() {
        var rota: RotaModel = {
            via: this.dadosVia.input.viaSelecionada.value,
            quilometros: Number(this.dadosVia.input.quantidade.value)
        };
        this.dadosVia.input.viaSelecionada.reset();
        this.dadosVia.input.quantidade.reset();
        this.dadosVia.tabela.data.data = [...this.dadosVia.tabela.data.data, rota];
    }

    dadosProdutos: DadosInputModel = {
        input: {
            produtoSelecionada: new FormControl(null, {validators: Validators.required}),
            quantidade: new FormControl(null, {validators: Validators.required}),
        },
        select: [],
        tabela: {
            colunms: ['nome', 'peso', 'valor', 'quantidade', 'total'],
            data: new MatTableDataSource([])
        }
    };

    addProduto() {
        var carga: CargaModel = {
            produto: this.dadosProdutos.input.produtoSelecionada.value,
            quantidade: this.dadosProdutos.input.quantidade.value,
        };
        this.dadosProdutos.input.produtoSelecionada.reset();
        this.dadosProdutos.input.quantidade.reset();
        this.dadosProdutos.tabela.data.data = [...this.dadosProdutos.tabela.data.data, carga];
    }

    calcularTodaMercadotia() {
        var valorTotal: number = 0;
        this.dadosProdutos.tabela.data.data.forEach((carga: CargaModel) => {
            valorTotal += carga.quantidade * carga.produto.valor
        });
        return valorTotal;
    }

    calcularPesoTotal() {
        var pesoTotal: number = 0;
        this.dadosProdutos.tabela.data.data.forEach((carga: CargaModel) => {
            pesoTotal += carga.quantidade * carga.produto.peso
        });
        return pesoTotal;
    }

    dadosCalculo: DadosInputModel = {
        tabela: {
            colunms: ['valorTransporte', 'valorEmMercadoria', 'distanciaPercorrido', 'pesoTransportado'],
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
        this.calculoGroup.controls.rotas.setValue(this.dadosVia.tabela.data.data);
        this.calculoGroup.controls.cargas.setValue(this.dadosProdutos.tabela.data.data);
        this.enviarInformacoes = true;

        this.apiService.calcularTransporte(this.calculoGroup.value).subscribe(calc => {
            setTimeout(() => {
                this.enviarInformacoes = false;
                this.dadosCalculo.tabela.data.data = [...[], calc];
                this.calculoGroup.controls.calculo.setValue(calc);
            }, 3000);
        }, error => {
            this.enviarInformacoes = false;
            this.messageToast('error', 'Algo deu errado, favor tente novamente.');
        });
    }

    messageToast(classStyle: string, text: string, duration?: number): void {
        this.snackBar.openSnackBar(text, 'Fechar', classStyle, duration);
    }

    salvarCalculo(): void {
        this.enviarInformacoes = true;
        this.apiService.salvarCalcularTransporte(this.calculoGroup.value).subscribe(() => {
            setTimeout(() => {
                this.enviarInformacoes = false;
                this.messageToast('success', 'Calculo salvo com sucesso!');
                this.resetarFormulario();
            }, 3000);
        }, error => {
            this.enviarInformacoes = false;
            this.messageToast('error', 'Algo deu errado, favor tente novamente.');
        });
    }

    private resetarFormulario() {
        this.calculoGroup.reset();
        this.dadosVia.input.viaSelecionada.reset();
        this.dadosVia.input.quantidade.reset();

        this.dadosProdutos.input.produtoSelecionada.reset();
        this.dadosProdutos.input.quantidade.reset();

        this.dadosVia.tabela.data.data = [...[]];
        this.dadosProdutos.tabela.data.data = [...[]];
    }

    loadVeiculo(): void {
        this.apiService.getVeiculos().subscribe(veiculos => {
            this.veiculosDiponiveis = veiculos;
        });
    }

    loadVias(): void {
        this.apiService.getVias().subscribe(vias => {
            this.dadosVia.select = vias;
        });
    }

    loadProdutos(): void {
        this.apiService.getProdutos().subscribe(produtos => {
            this.dadosProdutos.select = produtos;
        });
    }

    ngAfterContentInit() {
        this.loadVeiculo();
        this.loadVias();
        this.loadProdutos();
    }

    ngOnInit(): void {
    }

}
