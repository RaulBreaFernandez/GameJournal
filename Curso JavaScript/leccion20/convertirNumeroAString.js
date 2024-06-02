let numero = "18";

console.log(typeof(numero));

let edad = Number(numero);

console.log(typeof(edad));

if(edad >= 18) {

    console.log("Puede votar");

}else {

    console.log("No puede votar");

}

let resultado = (edad >= 18) ? "Puede votar" : "No puede votar";

console.log(resultado);