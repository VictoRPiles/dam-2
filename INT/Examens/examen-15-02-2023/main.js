const {app, BrowserWindow} = require('electron');

function createWindow() {
	const mainWindow = new BrowserWindow({
		width: 1280, height: 800, webPreferences: {
			nodeIntegration: true, contextIsolation: false
		}
	});
	// quita menu por defecto de chromium
	// mainWindow.setMenu(null)

	mainWindow.loadFile('index.html');

	// Open the DevTools.
	// mainWindow.webContents.openDevTools()
}

app.whenReady().then(createWindow);