const fs = require('fs');
/* llegim el fitxer */
const path = '../res/clients.json';
let fitxer = fs.readFileSync(path);
/* transformem el fitxer a JSON */
let clients = JSON.parse(fitxer.toString());
console.log(clients);

/* canvia el valor en la variable */
clients.listaClientes[0].nombre = "victor";
/* escriu de nou al JSON */
fs.writeFileSync(path, JSON.stringify(clients));
console.log(clients);