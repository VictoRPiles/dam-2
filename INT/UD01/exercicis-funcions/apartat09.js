function apartat09(...nombres) {
    console.log(nombres);
    console.log(nombres.map((element) => {
        /* casting a string i se separa per digits */
        let arrayString = String(element).split('');
        /* casting a number */
        let arrayNumber = arrayString.map(Number)
        /* suma dels digits */
        return arrayNumber.reduce((digit1, digit2) => digit1 + digit2);
    }));
}

apartat09(123, 34, 52);