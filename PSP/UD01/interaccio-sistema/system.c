#include <stdio.h>
#include <stdlib.h>

int main() {
	printf("%d", system("ls --format=single-column -i > llistat.txt"));
	printf("%d", system("nano llistat.txt"));

	return 0;
}