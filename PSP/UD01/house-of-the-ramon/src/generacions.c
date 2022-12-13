#include <unistd.h>
#include <stdio.h>
#include <string.h>
#include <sys/wait.h>
#include <sys/stat.h>
#include <fcntl.h>

#define AGENCIA_TARGARYEN 0
#define AGENCIA_MARTELL 1

/**
 * Muestra un dragón en pantalla.
 */
void print_drago();

/**
 * Imprime un mensaje diciendo que el hijo ha nacido.
 * Se llama desde el SIGUSR1.
 */
void
naixement()
{
	printf("Hola papá, Ya he nacido!!!\n");
}

/**
 * Envía una solicitud al FIFO de la agencia correspondiente y imprime la mujer devuelta por la agencia.
 * @param agencia Agencia Targaryen o Martell, definidas en las macros.
 * @param nom Nombre del pretendiente.
 */
void
casament(int agencia, char nom[])
{
	char missatge[100] = "";
	char fifo[100] = "";

	if (agencia == AGENCIA_TARGARYEN) {
		strcat(missatge, "Soy ");
		strcat(missatge, nom);
		strcat(missatge, " busco mujer Targaryen fértil.");

		strcpy(fifo, "/tmp/fifo_targaryen");
	} else if (agencia == AGENCIA_MARTELL) {
		strcat(missatge, "Soy ");
		strcat(missatge, nom);
		strcat(missatge, " busco mujer Martell fértil.");

		strcpy(fifo, "/tmp/fifo_martell");
	}
	printf("%s\n", missatge);

	/* Creant el FIFO */
	int fd;
	mkfifo(fifo, 0666);

	char dona[100];
	/* Write only */
	fd = open(fifo, O_WRONLY);

	/* Escriure el missatge */
	write(fd, missatge, strlen(missatge) + 1);
	close(fd);

	/* Llegir el missatge */
	fd = open(fifo, O_RDONLY);
	read(fd, dona, sizeof(dona));

	printf("%s perpetuemos la casa Targaryen \n", dona);
	close(fd);
}

/**
 * Crea el árbol de generaciones, contiene la lógica del programa y llama al resto de funciones.
 */
int
main()
{
	/* Cuando recibe una señal SIGUSR1, llama a la función */
	signal(SIGUSR1, naixement);

	pid_t pid01, pid02, pid03, pid11, pid12;

	char pare[100] = "";
	char nom[100] = "";

	print_drago();

	/* ========== PADRE ========== */
	/* Se entiende que Aerys también tiene padres, por simplificar los llamaremos Primeros Targaryen */
	strcpy(pare, "Los primeros Targaryen");
	strcpy(nom, "Aerys Targaryen");

	printf("Soy %s (%d) hijo de %s (%d).\n", nom, getpid(), pare, getppid());
	/* Casamiento Targaryen */
	casament(AGENCIA_TARGARYEN, nom);

	/* ========== PRIMER HIJO ========== */
	pid01 = fork();
	if (pid01 == 0) {
		signal(SIGUSR1, naixement);
		sleep(1);
		strcpy(pare, "Aerys Targaryen");
		strcpy(nom, "Rhaegar Targaryen");

		printf("- Soy %s (%d) hijo de %s (%d).\n", nom, getpid(), pare,
			   getppid());
		kill(getppid(), SIGUSR1);
		/* Casamiento Martell */
		casament(AGENCIA_MARTELL, nom);

		/* ========== PRIMER HIJO DEL PRIMER HIJO ========== */
		pid11 = fork();
		if (pid11 == 0) {
			sleep(1);
			strcpy(pare, "Rhaegar Targaryen");
			strcpy(nom, "Rhaenys Targaryen");

			printf("-- Soy %s (%d) hijo de %s (%d).\n", nom, getpid(), pare,
				   getppid());
			kill(getppid(), SIGUSR1);

		} else {
			/* ========== SEGUNDO HIJO DEL PRIMER HIJO ========== */
			pid12 = fork();
			if (pid12 == 0) {
				sleep(1);
				strcpy(pare, "Rhaegar Targaryen");
				strcpy(nom, "Aegon Targaryen");

				printf("-- Soy %s (%d) hijo de %s (%d).\n", nom, getpid(), pare,
					   getppid());
				kill(getppid(), SIGUSR1);
			}
		}

		/* Espera a sus hijos */
		waitpid(pid11, NULL, 0);
		waitpid(pid12, NULL, 0);
	} else {
		/* ========== SEGUNDO HIJO ========== */
		pid02 = fork();
		if (pid02 == 0) {
			sleep(1);
			strcpy(pare, "Aerys Targaryen");
			strcpy(nom, "Viserys Targaryen");

			printf("- Soy %s (%d) hijo de %s (%d).\n", nom, getpid(), pare,
				   getppid());
			kill(getppid(), SIGUSR1);

		} else {
			/* ========== TERCER HIJO ========== */
			pid03 = fork();
			if (pid03 == 0) {
				sleep(1);
				strcpy(pare, "Aerys Targaryen");
				strcpy(nom, "Daenerys Targaryen");

				printf("- Soy %s (%d) hijo de %s (%d).\n", nom, getpid(), pare,
					   getppid());
				kill(getppid(), SIGUSR1);
			}
		}
	}

	/* Espera a los hijos antes de cerrar el programa */
	waitpid(pid01, NULL, 0);
	waitpid(pid02, NULL, 0);
	waitpid(pid03, NULL, 0);

	return 0;
}

void
print_drago()
{
	printf(
		" _____________________________ \n"
		"< Viva The House of the Ramon >\n"
		" ----------------------------- \n"
		"      \\                    / \\  //\\\n"
		"       \\    |\\___/|      /   \\//  \\\\\n"
		"            /0  0  \\__  /    //  | \\ \\    \n"
		"           /     /  \\/_/    //   |  \\  \\  \n"
		"           @_^_@'/   \\/_   //    |   \\   \\ \n"
		"           //_^_/     \\/_ //     |    \\    \\\n"
		"        ( //) |        \\///      |     \\     \\\n"
		"      ( / /) _|_ /   )  //       |      \\     _\\\n"
		"    ( // /) '/,_ _ _/  ( ; -.    |    _ _\\.-~        .-~~~^-.\n"
		"  (( / / )) ,-{        _      `-.|.-~-.           .~         `.\n"
		" (( // / ))  '/\\      /                 ~-. _ .-~      .-~^-.  \\\n"
		" (( /// ))      `.   {            }                   /      \\  \\\n"
		"  (( / ))     .----~-.\\        \\-'                 .~         \\  `. \\^-.\n"
		"             ///.----..>        \\             _ -~             `.  ^-`  ^-_\n"
		"               ///-._ _ _ _ _ _ _}^ - - - - ~                     ~-- ,.-~\n"
		"                                                                  /.-~\n");
	printf(
		"============================================================================\n");
}