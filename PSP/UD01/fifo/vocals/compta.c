#include <stdio.h>
#include <unistd.h>
#include <string.h>
#include <sys/stat.h>
#include <fcntl.h>

int main()
{
	int fp, bytes_llegits;
	char buffer[] = " ";
	/* cada vocal té una posició al array */
	/* (0, a) (1, e) (2, i) (3, o) (4, u) */
	int comptador[5] = { 0, 0, 0, 0 };
	int sumatori = 0;

	mkfifo("fifo", S_IFIFO | 0666);

	while (1) {
		fp = open("fifo", 0);
		printf("(-) Llegint informació...\n");
		do {
			/* llig un caràcter des del FIFO i el carrega al buffer */
			bytes_llegits = read(fp, buffer, 1);

			/* afig el caràcter llegit al recompte (si és una vocal) */
			switch (buffer[0]) {
			case 'a':
				comptador[0]++;
				break;
			case 'e':
				comptador[1]++;
				break;
			case 'i':
				comptador[2]++;
				break;
			case 'o':
				comptador[3]++;
				break;
			case 'u':
				comptador[4]++;
				break;
			}
		} while (bytes_llegits != 0);

		printf("Recompte de 'a' -> %d\n", comptador[0]);
		printf("Recompte de 'e' -> %d\n", comptador[1]);
		printf("Recompte de 'i' -> %d\n", comptador[2]);
		printf("Recompte de 'o' -> %d\n", comptador[3]);
		printf("Recompte de 'u' -> %d\n", comptador[4]);

		size_t n = sizeof(comptador) / sizeof(int);
		for (int i = 0; i < n; ++i) {
			sumatori += comptador[i];
		}
		printf("Recompte total de vocals -> %d\n", sumatori);

		/* reset de la informació */
		memset(comptador, 0, sizeof comptador);
		sumatori = 0;
	}

	close(fp);

	return 0;
}