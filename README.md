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
 -Working
 -Prints to Answers.txt

 RangeSearch:
 -Working
 -Not Extensively tested eg. does not handle case where invalid bounds are given


Not Working:
------------

Important Notes:
----------------
- If you are useing a while loop to increment the cursor you MUST** reinitalize the key and data
  to be new DatabaseEntry() objects. Otherwise you get garbage from previous result.