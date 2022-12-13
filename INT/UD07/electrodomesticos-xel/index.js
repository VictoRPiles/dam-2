const fs = require('fs');
let fichero = fs.readFileSync('./electrodomesticos.json');
let productos = [];

productos = JSON.parse(fichero);
console.log(productos);

/* Select HTML */
let cadenaDOM = '<x-select id="select"><x-menu>';
productos.forEach((producto, i) => {
    cadenaDOM += `<x-menuitem value="${i}" toggled>
                    <x-label>
                        ${producto.nombre}
                    </x-label>
                  </x-menuitem>`;
});
cadenaDOM += '</x-menu></x-select>';
console.log(cadenaDOM);
document.getElementById("desplegable").innerHTML = cadenaDOM;

/* Tabla HTML */
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

document.getElementById("select").addEventListener('change', () => {
    let indice = document.getElementById("select").value;
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