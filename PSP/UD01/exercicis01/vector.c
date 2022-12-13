#include <stdio.h>

int main ()
{
  int nombres[5];
  for (int i = 0; i < sizeof (nombres) / sizeof (nombres[0]); ++i)
	{
	  if (i > 0)
		{
		  do
			{
			  printf ("Introdueix un valor per al index %d -> ", i);
			  scanf ("%d", &nombres[i]);
			}
		  while (nombres[i] > nombres[i - 1]); // TODO
		}
	}

  return 0;
}