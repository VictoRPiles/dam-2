const {app, BrowserWindow} = require('electron')
require('@electron/remote/main').initialize()
const path = require('path')

function createWindow() {
    const win = new BrowserWindow({
        width: 900,
        height: 800,
        webPreferences: {
            nodeIntegration: true,
            contextIsolation: false,
            preload: path.join(__dirname, 'preload.js')
        }
    })

    /* Xel dialog */
    require('@electron/remote/main').enable(win.webContents)

    win.loadFile('app/index.html')
}

app.whenReady().then(() => {
    createWindow()

    app.on('activate', () => {
        if (BrowserWindow.getAllWindows().length === 0) {
            createWindow()
        }
    })
})

app.on('window-all-closed', () => {
    if (process.platform !== 'darwin') {
        app.quit()
    }
})