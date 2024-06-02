let persona = {

    nombre: "Raúl",
    apellido: "Brea",
    edad: 22,
    email: "raul@gmail.com",
    idioma: "ESP",

    get lang() {
        
        return this.idioma.toLowerCase();

    },

    set lang(lang) {

        this.idioma = lang.toLowerCase();

    },

    get nombreCompleto() {
        
        return this.nombre + " " + this.apellido;

    }

}

console.log(persona.lang);

persona.lang = 'EN';

console.log(persona.lang);