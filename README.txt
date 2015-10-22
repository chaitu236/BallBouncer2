COMPILE:
  make && make jar

RUN:
  make run

ABOUT THE GAME:

This game was developed by Chaitanya Vadrevu (i.e., chaitu236 i.e., me) from scratch. Source code 
and images are original creations of the said developer. Images were 
created using GIMP using only default textures and fonts which are 
licensed under GNU GPL.

**************************************************************************

HOW TO PLAY:

1. Make sure you have the latest version of Java (JRE) installed and 
double click the "BallBouncer2.jar" file. The program is written in 
J2SE and should run on any modern OS.
2. Click or press any button to start the game.
3. Use Mouse or Arrow Keys to control the puddle/slider position (preferably mouse).
4. If you have a weapon, press SPACEBAR or use LEFT CLICK to fire weapon.

NOTE: The speed of the game seems to be dependent on the computer it is 
played on and the OS. Please contact the developer if you find the game 
unplayable.

**************************************************************************

LICENSING:

This software is licensed under GNU GPL version 3 or above. Please refer
 to the accompanying license file, 'gpl.txt' for content of the license.

NOTE: This is a free software and you may modify and/or distribute the 
source code ( but not modify license) freely subject to the conditions 
laid in the license.

**************************************************************************

BUGS:

The ball may behave in an unexpected way when it 'grazes' the edge
 of a brick/stone. The probability of occurance of such a condition is 
less than 1%.

**************************************************************************

IMPROVEMENTS:

Since all the levels are generated randomly, placement of 'stones' in some
 cases may render the completion of the game impossible. The probability 
of appearance of 'stones' is set to 3% to minimize this occurance. A better
 solution would be to implement predefined levels and a method to store,
 retrieve and create such levels.
