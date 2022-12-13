const fs = require('fs');
let fichero = fs.readFileSync('./electrodomesticos.json');
let productos = [];

productos = JSON.parse(fichero);
console.log(productos);

let cadenaDOM = '<select id="select">';
productos.forEach(producto => {
    cadenaDOM += `<option value="${producto.nombre}">${producto.nombre}</option>`;
});
cadenaDOM += '</select>';
console.log(cadenaDOM);
document.getElementById("desplegable").innerHTML = cadenaDOM;

let creaInformacion = (indice) => {
    cadenaDOM = `<table class="table-striped"><tbody>
				<tr><td>Descripción:</td><td>${productos[indice].nombre}</td></tr>
				<tr><td>Precio coste:</td><td>${productos[indice].precioCoste}</td></tr>
				<tr><td>Precio venta:</td><td>${productos[indice].precioVenta}</td></tr>
				<tr><td>Stock actual:</td><td>${productos[indice].stockActual}</td></tr>
				<tr><td>Stock mínimo:</td><td>${productos[indice].stockMin}</td></tr>
				<tbody></table>`;
    document.getElementById("informacion").innerHTML = cadenaDOM;
}

document.getElementById("select").addEventListener('click', () => {
    let indice = document.getElementById("select").selectedIndex;
    console.log(indice);
    creaInformacion(indice);
});

creaInformacion(0);

let suma = 0;
productos.forEach(p => {
    suma += parseInt(p.stockActual);
});
console.log(suma);

let stockmin = productos.filter(p => {
    return p.stockActual < p.stockMin;
});
console.log(stockmin);
cadenaDOM = `<ul>
			<li>Total Productos: ${productos.length}</li>
			<li>Total Stock Actual: ${suma}</li>
			<li>Productos con stock por debajo del mínimo:
			
				<ol>`;

stockmin.forEach(p => {
    cadenaDOM += `<li> ${p.nombre} </li>`;
});
cadenaDOM += `</ol>
			</li>
		</ul>`;
console.log(cadenaDOM);
document.getElementById("totales").innerHTML = cadenaDOM;