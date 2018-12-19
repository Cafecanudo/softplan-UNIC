import {BrowserModule} from '@angular/platform-browser';
import {LOCALE_ID, NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {CaluclarTransporteComponent} from './caluclar-transporte/caluclar-transporte.component';
import {NumberDirective} from "./core/number-only";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {
    MatAutocompleteModule,
    MatButtonModule,
    MatCardModule,
    MatCheckboxModule,
    MatFormFieldModule,
    MatIconModule,
    MatInputModule, MatProgressBarModule,
    MatSelectModule, MatTableModule
} from "@angular/material";
import {ReactiveFormsModule} from "@angular/forms";
import {registerLocaleData} from "@angular/common";

import localePt from '@angular/common/locales/pt';
import {HttpClientModule} from "@angular/common/http";

registerLocaleData(localePt);

@NgModule({
    declarations: [
        AppComponent,
        CaluclarTransporteComponent,
        NumberDirective
    ],
    imports: [
        BrowserModule,
        HttpClientModule,
        AppRoutingModule,
        BrowserAnimationsModule,
        ReactiveFormsModule,
        MatButtonModule,
        MatCheckboxModule,
        MatInputModule,
        MatFormFieldModule,
        MatAutocompleteModule,
        MatSelectModule,
        MatCardModule,
        MatIconModule,
        MatTableModule,
        MatProgressBarModule,
    ],
    exports: [
        ReactiveFormsModule,
        MatButtonModule,
        MatCheckboxModule,
        MatInputModule,
        MatFormFieldModule,
        MatAutocompleteModule,
        MatSelectModule,
        MatCardModule,
        MatIconModule,
        MatTableModule,
        MatProgressBarModule
    ],
    providers: [
        {provide: LOCALE_ID, useValue: 'pt-BR'}
    ],
    bootstrap: [AppComponent],
})
export class AppModule {
}
