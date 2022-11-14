#include <stdio.h>
#include <unistd.h>

int main()
{
	pid_t id_actual, id_padre;

	id_actual = getpid();
	id_padre = getppid();

	printf("PID Actual -> %lld\n", id_actual);
	printf("PID Padre  -> %lld\n", id_padre);

	return 0;
}