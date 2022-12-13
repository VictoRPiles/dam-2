function printPascal() {
    document.getElementById("pascal").innerHTML = ""

    var filas = document.getElementById("filas").value
    var valores = pascal(filas)

    for (var i = 0; i < valores.length; i++) {

        var div = document.createElement('div')
        div.className = "divblock"

        for (var j = 0; j < valores[i].length; j++) {

            var span = document.createElement('span')
            span.innerHTML = valores[i][j]
            span.className = "block"

            div.appendChild(span)
        }
        
        document.getElementById("pascal").appendChild(div);
    }
}
function pascal(filas) {
    var arr = [];
    var tmp;
    for (var i = 0; i < filas; i++) {
        arr[i] = [];
        for (var j = 0; j <= i; j++) {
            if (j == i) {
                arr[i].push(1);
            } else {
                tmp = (!!arr[i - 1][j - 1] ? arr[i - 1][j - 1] : 0) + (!!arr[i - 1][j] ? arr[i - 1][j] : 0);
                arr[i].push(tmp);
            }
        }
    }
    return arr;
}