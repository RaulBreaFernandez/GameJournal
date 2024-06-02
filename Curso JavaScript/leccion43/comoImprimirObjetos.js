let persona = {

    nombre: "Raúl",
    apellido: "Brea",
    edad: 22,
    email: "raul@gmail.com"

}


console.log(persona.nombre + " " + persona.apellido);

for(i in persona) { 
    
    console.log(persona[i]);

}

let personaArray = Object.values(persona);

console.log(Object.values(persona));

JSON.stringify(persona);

console.log(JSON.stringify(persona))

