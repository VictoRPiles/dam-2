#include <stdio.h>

int main ()
{
  /* demana el nombre màgic fins que s'introdueix un entre 1 i 100 */
  unsigned int nombreMagic;
  do
	{
	  printf ("JUGADOR 1: Introdueix un nombre entre 1 i 100.\n");
	  scanf ("%d", &nombreMagic);

	  if (nombreMagic < 1 || nombreMagic > 100)
		printf ("El nombre %d no esta en els limits del 1 al 100.\n", nombreMagic);
	}
  while (nombreMagic < 1 || nombreMagic > 100);

  /* demana un nombre fins que s'introdueix un igual al nombre màgic */
  unsigned int nombre;
  do
	{
	  printf ("JUGADOR 2: Introdueix un nombre entre 1 i 100.\n");
	  scanf ("%d", &nombre);

	  if (nombre > nombreMagic)
		printf ("El nombre magic es menor, continua intentant-ho...\n");
	  else if (nombre < nombreMagic)
		printf ("El nombre magic es major, continua intentant-ho...\n");
	}
  while (nombre != nombreMagic);

  printf ("Enhorabona, has endevinat el nombre!\n");

  return 0;
}