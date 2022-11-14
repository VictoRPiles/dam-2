#include <stdio.h>
#include <string.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>

int main()
{
	char frase[100] = " ";
	printf("Escriu una frase -> ");
	fgets(frase, 100, stdin);

	int fp;
	fp = open("fifo", 1);
	if (fp == -1) {
		printf("(-) No es troba el fitxer FIFO...\n");
		return 1;
	}
	write(fp, frase, strlen(frase));

	return 0;
}