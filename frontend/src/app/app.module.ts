import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

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
    MatInputModule,
    MatSelectModule, MatTableModule
} from "@angular/material";
import {ReactiveFormsModule} from "@angular/forms";

@NgModule({
    declarations: [
        AppComponent,
        CaluclarTransporteComponent,
        NumberDirective
    ],
    imports: [
        BrowserModule,
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
        MatTableModule
    ],
    providers: [],
    bootstrap: [AppComponent],
})
export class AppModule {
}
