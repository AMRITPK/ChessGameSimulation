# ChessGameSimulation
simulating the status of chess game after getting all moves in the PGN notations

Input is the mooves in the game represented in PGN notation and process this inputs and return the final state of the board


Representation
Board as a 8*8 array of strings
each object in a chess is represented by a string 
eg P=pawn, Q= gueen etc

approach

parse each moove from the file and based on which piece mooved, we will search from which location that moved there and then update the board accodingly
