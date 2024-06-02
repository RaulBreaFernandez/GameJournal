let persona = {

    nombre: "Raúl",
    apellido: "Brea",
    edad: 22,
    email: "raul@gmail.com"

}

persona.telefono = "666666666";
console.log(persona);

delete persona.telefono;

console.log(persona);