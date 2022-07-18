# Figures counter

Module counts figures in the figures map. Map is a 2D array of chars. The '0' char represents non figure area, '1' - figure.
The concept of counting the figures is based on idea of traversing thru the figure map. If '1' is spoted then
figure counter is incremented and flood fill is initiated starting from the point with the '1' value so that the whole figure 
is filled with the char other than '0' and '1'. The char to be used may be fixed (like 'P') or changing from 'A' 
to 'Z' and starting over if number of figures is big enough. Implementation of LetterSource interface used determines 
the behaviour.