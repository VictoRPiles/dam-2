#include <signal.h>
#include <stdio.h>
#include <unistd.h>
#include <wait.h>

void handler();

int
main()
{
	pid_t hijo01, hijo02;

	printf("Padre.pid = %d \n", getpid());

	hijo01 = fork();

	switch (hijo01) {
	case -1:
		printf("Error creando el hijo\n");
		return 1;
	case 0:
		/* ********** Hijo 01 ********** */
		printf("Soy el hijo 01, comienzo!\n");
		signal(SIGUSR1, handler);
		pause();
		printf("Soy el hijo 01, acabo!\n");
		break;
	default:
		hijo02 = fork();
		switch (hijo02) {
		case -1:
			printf("Error creando el hijo\n");
			return 1;
		case 0:
			/* ********** Hijo 02 ********** */
			printf("Soy el hijo 02, comienzo!\n");
			signal(SIGUSR1, handler);
			pause();
			printf("Soy el hijo 02, acabo!\n");
			break;
		default:
			/* ********** Padre ********** */
			sleep(1);
			kill(hijo01, SIGUSR1);
			sleep(1);
			kill(hijo02, SIGUSR1);
			printf("Ha terminado mi hijo con pid = %d\n", hijo01);
			printf("Ha terminado mi hijo con pid = %d\n", hijo02);
			waitpid(hijo01, NULL, 0);
			waitpid(hijo02, NULL, 0);
			printf("Soy el padre, ya no tengo hijos, acabo\n");
		}
	}
}

void
handler()
{
	printf("Señal recibida. Soy un hijo cualquiera (pid = %d, ppid = %d)\n", getpid(), getppid());
}