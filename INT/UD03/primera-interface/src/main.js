const {app, BrowserWindow} = require('electron')

function createWindow() {
    /* Crea la finestra del navegador. */
    const window = new BrowserWindow({
        width: 800,
        height: 600,
        webPreferences: {
            nodeIntegration: true,
            contextIsolation: false
        }
    })
    /* Carrega index.html */
    window.loadFile('src/static/index.html')
    window.webContents.openDevTools()
}

/* Llança l'app */
app.on('ready', createWindow)