const puppeteer = require('puppeteer');

const btnAcceptar = document.getElementById('btnAcceptar');

async function imprimir(html) {
    const fs = require('fs');
    const dir = './pdfPedidos/';
    const inputNom = document.getElementById('inputNom');

    if (!fs.existsSync(dir)) {
        fs.mkdirSync(dir);
    }
    try {
        const browser = await puppeteer.launch();
        const page = await browser.newPage();
        await page.setContent(html);
        await page.pdf({
            path: dir + inputNom.value + '.pdf',
            format: 'A4',
            printBackground: true
        });
        await browser.close();
    } catch (e) {
        console.log('our error', e);
    }
}

btnAcceptar.addEventListener('click', () => {
    const inputNom = document.getElementById('inputNom');
    const inputTelefon = document.getElementById('inputTelefon');
    const inputDireccio = document.getElementById('inputDireccio');
    const tamany = document.querySelector('input[name="tamanys"]:checked').value;
    const massa = document.querySelector('input[name="massa"]:checked').value;
    const ingredientsDiv = document.getElementById('ingredients').getElementsByTagName('input');
    let ingredientsValues = []
    for (let ingredient in ingredientsDiv) {
        if (ingredientsDiv[ingredient].checked) {
            ingredientsValues.push(ingredientsDiv[ingredient].value)
        }
    }
    console.log(ingredientsValues)

    const html = `<!DOCTYPE html>
    <html lang="en">
    <head>
        <title>Pedido</title>
    </head>
    <body>
        <h1>Pedido</h1>
        <p>Nom: ${inputNom.value}</p>
        <p>Telèfon: ${inputTelefon.value}</p>
        <p>Direcció: ${inputDireccio.value}</p>
        <p>Tamany: ${tamany}</p>
        <p>Massa: ${massa}</p>
        <p>Ingredients: ${ingredientsValues}</p>
    </body>
    </html>`;

    imprimir(html);
})