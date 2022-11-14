#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>
#include <string.h>

int main()
{
	int fp;
	int bytes_llegits;
	char buffer[10] = " ";

	mkfifo("fifo", S_IFIFO | 0666);

	while (1) {
		fp = open("fifo", 0);
		bytes_llegits = read(fp, buffer, 1);
		printf("(-) Obtenint informació...\n");

		while (bytes_llegits != 0) {
			printf("%c", buffer[0]);
			bytes_llegits = read(fp, buffer, 1);
		}
		close(fp);
	}

	return 0;
}
