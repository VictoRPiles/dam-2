const remote = require('@electron/remote');

const btnCancelar = document.getElementById('btnCancelar');

btnCancelar.addEventListener('click', () => {
    remote.getCurrentWindow().close();
})