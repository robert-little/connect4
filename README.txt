This is a connect4 backend. 

To use the victory test a testfile must be specified.
The file is of type:
two-0
one-2
one-3
one-4
two-5
...
final
one-2,one-5,one-6

Where the word is the player name (one or two in this case) and the number is the slot the piece will be dropped into. This allows users to have a pre-specified board layout to test with. In Connect 4 the pieces are dropped into slots from above and fall until they hit something. Because of this the file should be generated row by row starting from the bottom and working your way up.

The final keyword shows which move should be tested for victory. After the final keyword either a single move or multiple moves sperated by commas can be entered to be tested.

Author: Rob Little
Date: 2016/12/21
