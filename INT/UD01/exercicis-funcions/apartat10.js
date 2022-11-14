function apartat10(costat1, costat2, angle) {
    angle = (Math.PI * angle) / 180;
    return 0.5 * costat1 * costat2 * Math.sin(angle);
}

console.log(apartat10(3, 2, 45));