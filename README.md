BerkleyDBProj
=============

Working:
--------

KeySearch:
-currently searches for a random key that it gets when the data base is created
-prints the results into Answers.txt properly
-Please see code for other methods that may be useful such as printing the reults
 to the screen

 DataSearch:
 -Mostly working, for some reason the random data string I search for is a few characters shorter
  than the data im getting out of the DB ... don't know why that is
 -Prints to Answers.txt as above

 RangeSearch:
 -Finished first implementation, seems to be working, needs testing.


Not Working:
------------

Cursor:
Any iteration with the cursor seems to return some garbage at the end of every data value. Even in the example
program given by TA's, the data returned when iterating with a cursor is different than the data returned if we
used a .get(). No solution to this so far, might have to ask TA's.
