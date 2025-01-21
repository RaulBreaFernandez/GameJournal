import { Component, EventEmitter, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-formulario',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './formulario.component.html',
  styleUrl: './formulario.component.css'
})
export class FormularioComponent {

  operandoA: number | null = 0;
  operandoB: number | null = 0;

  @Output() resultadoSuma = new EventEmitter<number>();

  sumar() {
    if(this.operandoA && this.operandoB != null) {
      const resultado = this.operandoA + this.operandoB;
      this.resultadoSuma.emit(resultado);
    } else {
      console.log('Debes de introducir valores correctos');
    }
  }

}
