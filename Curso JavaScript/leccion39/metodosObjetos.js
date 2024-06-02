let x = 10;
let y = "Raúl"

let persona = {

    nombre: "Raúl",
    apellido: "Brea",
    edad: 22,
    email: "raul@gmail.com",

    nombreCompleto: function () {

        return this.nombre + ' ' + this.apellido;

    }

}

console.log(persona.nombre);
console.log(persona.apellido);
console.log(persona);
console.log(persona.nombreCompleto());
