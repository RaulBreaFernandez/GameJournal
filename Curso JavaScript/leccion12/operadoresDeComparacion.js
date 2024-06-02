let a = 3, b = 2, c = "3";

let z = a == c; //compara solo los valores --> da igual que sean números o String

console.log(z);

z = a === c; //compara los valores y tipos --> importa si son números o String

console.log(z);