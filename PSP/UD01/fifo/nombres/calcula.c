#include <stdio.h>
#include <limits.h>

int main()
{
	int nombre = 0;
	int contador = 0, mitjana = 0, minim = INT_MAX, maxim = INT_MIN;

	while (nombre != 100) {
		printf("Introdueix un nombre -> ");
		scanf("%d", &nombre);

		if (nombre != 100) {
			if (nombre < minim) {
				minim = nombre;
			}
			if (nombre > maxim) {
				maxim = nombre;
			}
			contador++;
			mitjana += nombre;
		}
	}
	mitjana /= contador;

	printf("Mínim -> %d\n", minim);
	printf("Màxim -> %d\n", maxim);
	printf("Mitjana -> %d\n", mitjana);

	return 0;
}