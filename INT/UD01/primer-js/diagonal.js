const N = 20;
// Diagonal descendent
console.log("===== Diagonal descendent =====")
for (let i = 0; i < N; i++) {
    console.log(" ".repeat(i * 2) + "*");
}
// Diagonal ascendent
console.log("===== Diagonal ascendent =====")
for (let i = 0; i < N; i++) {
    console.log(" ".repeat((N - i) * 2) + "*");
}