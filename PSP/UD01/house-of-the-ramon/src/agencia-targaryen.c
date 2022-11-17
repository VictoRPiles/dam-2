#include <stdio.h>
#include <string.h>
#include <fcntl.h>
#include <sys/stat.h>
#include <unistd.h>

int
main()
{
	int fd;

	char fifo[] = "/tmp/fifo_targaryen";

	printf("=================================\n");
	printf("I        AGENCIA TARGARYEN      I\n");
	printf("=================================\n");

	mkfifo(fifo, 0666);

	char missatge[100], dona[100];
	while (1) {
		/* Llegir el missatge */
		fd = open(fifo, O_RDONLY);
		read(fd, missatge, 100);

		printf("%s\n", missatge);
		close(fd);

		/* Escriure una dona */
		fd = open(fifo, O_WRONLY);

		printf("Introduce una mujer Targaryen: ");
		fgets(dona, 100, stdin);
		/* Eliminar el bot de línea del final */
		dona[strcspn(dona, "\n")] = '\0';

		write(fd, dona, strlen(dona) + 1);
		close(fd);
	}
	return 0;
}