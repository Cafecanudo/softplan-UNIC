import {Component} from '@angular/core';
import {MatSnackBar} from '@angular/material';

@Component({
    selector: 'app-mat-snack-bar',
    template: ''
})
export class MatSnackBarComponent {

    constructor(public snackBar: MatSnackBar) {
    }

    openSnackBar(message: string, action: string, className: string = 'info', duration: number = 2000,
                 ) {
        this.snackBar.open(message, action, {
            duration: duration,
            panelClass: 'snackbar-' + [className],
        });
    }
}