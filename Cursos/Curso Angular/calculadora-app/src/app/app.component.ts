import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { CalculadoraComponent } from './calculadora/calculadora.component';
import { FormularioComponent } from './formulario/formulario.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, CalculadoraComponent, FormularioComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'Aplicacion Calculadora';
}
