function apartat04(
    nom = "Producte genèric",
    preu = 100,
    impost = 21
) {
    /* si no son del tipo esperat, es fa un casting */
    if (typeof nom !== "string") nom = String(nom);
    if (typeof preu !== "number") preu = Number(preu);
    if (typeof impost !== "number") impost = Number(impost);

    /* si no son valors vàlids */
    if (preu < 0) preu = NaN;
    if (impost < 0 || impost > 100) impost = NaN;

    return "Nom -> " + nom + ".\n" + "Preu amb impost -> " + (preu + (preu * (impost / 100)) + "€.");
}

/* utilitza el valor per defecte */
console.log(apartat04());
console.log(apartat04("Pà", 1, 12));
/* realitza castings */
console.log(apartat04("Pà", "1", "12"));
/* valors no vàlids */
console.log(apartat04("Pà", -1, 12));
console.log(apartat04("Pà", 1, -12));