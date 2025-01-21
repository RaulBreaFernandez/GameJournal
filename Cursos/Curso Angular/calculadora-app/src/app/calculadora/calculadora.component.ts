import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-calculadora',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './calculadora.component.html',
  styleUrl: './calculadora.component.css',
})
export class CalculadoraComponent {
  resultado: number = 0;


}
