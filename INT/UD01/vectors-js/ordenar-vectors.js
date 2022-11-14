/* ordena els caràcters. */
let a = ["f", "a", "d", "c", "e", "b",];
console.log(a.sort());

/* ordena els nombres, pero els ordena com a caràcters. */
let b = [100, 20, 25, 6, 9];
console.log(b.sort());

/* ordena els nombres "correctament". */
console.log(b.sort((n1, n2) => {
    return n1 - n2;
}));