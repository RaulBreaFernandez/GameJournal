let persona = {

    nombre: "Raúl",
    apellido: "Brea",
    edad: 22,
    email: "raul@gmail.com",

    get nombreCompleto() {

        return this.nombre + " " + this.apellido;

    }

}

console.log(persona.nombreCompleto);