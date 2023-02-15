const {dialog} = require('@electron/remote');
const fs = require('fs');

const openButton = document.getElementById('open');
const saveButton = document.getElementById('save');
const table = document.getElementById('cTabla');

let recordCount = 0;

/* ########## Open file ########## */
openButton.addEventListener('click', () => {
    let fileNames = dialog.showOpenDialogSync({
        title: 'Abriendo archivos de notas',
        defaultPath: 'files',
        filters: [
            {name: 'Notas', extensions: ['json']},
            {name: 'All Files', extensions: ['*']}
        ]
    });
    console.log('Opening ' + fileNames + ' ...');

    let json = JSON.parse(fs.readFileSync(fileNames.toString()));
    console.log(json);

    recordCount = json.length;

    table.innerHTML = '';
    for (let record in json) {
        let group = json[record].grupo;
        let name = json[record].nombre;
        let grade = json[record].nota;

        table.innerHTML += `
        <tr>
            <td id="group${record}">${group}</td>
            <td id="name${record}">${name}</td>
            <td id="grade${record}">${grade}</td>
        </tr>`;
    }
});

/* ########## Save file ########## */
saveButton.addEventListener('click', () => {
    let fileSave = dialog.showSaveDialogSync();

    let values = [];
    let json = '';

    for (let record = 0; record < recordCount; record++) {
        let group = document.getElementById('group' + record);
        let name = document.getElementById('name' + record);
        let grade = document.getElementById('grade' + record);
        let rowValue = {
            grupo: group.innerText,
            nombre: name.innerText,
            nota: grade.innerText
        };
        values.push(rowValue);
        console.log(values);
    }
    json = JSON.stringify(values);
    console.log(json);

    fs.writeFileSync(fileSave, json);

    console.log('Saving ' + fileSave + ' ...');
});