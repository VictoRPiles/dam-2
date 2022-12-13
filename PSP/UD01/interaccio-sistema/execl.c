#include <stdio.h>
#include <unistd.h>

int main() {
	printf("Este es un exemple de execl():\n");
	printf("Els fitxers al directori son:\n");
	execl("/bin/ls", "ls", "-l", (char *)NULL);

	return 0;
}
