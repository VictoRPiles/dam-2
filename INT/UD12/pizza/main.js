const {app, BrowserWindow} = require('electron')
require('@electron/remote/main').initialize()

function createWindow() {
    const mainWindow = new BrowserWindow({
        width: 800, height: 600, webPreferences: {
            nodeIntegration: true, contextIsolation: false
        }
    })
    require('@electron/remote/main').enable(mainWindow.webContents)
    // quita menu por defecto de chromium
    // mainWindow.setMenu(null)

    mainWindow.loadFile('index.html')

    // Open the DevTools.
    // mainWindow.webContents.openDevTools()
}

app.whenReady().then(createWindow)