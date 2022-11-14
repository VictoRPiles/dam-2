#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <string.h>

int main()
{
	int fp;
	char saludo[] = "Bon dia!!\n";

	fp = open("fifo", 1);
	if (fp == -1) {
		printf("(-) Error al obrir el fitxer...\n");
		exit(1);
	}

	printf("(-) Enviant informació al FIFO...\n");
	write(fp, saludo, strlen(saludo));

	close(fp);

	return 0;
}
