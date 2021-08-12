# Nonograms... this is a yet unfinished project :)

Currently, this project contains a brute force nonogram solver. It supports the .non
nonogram file format; I used the puzzle database at https://github.com/mikix/nonogram-db
by mikix. This project doesn't yet support the bundles or colored nonograms.

To run this project, go to BruteForce.java and replace "test.non" (without
quotes) with the path of a .non file that contains the puzzle you'd like to
solve. Run BruteForce.java, and it should print the solution to standard
output. Asterisks (*) represent filled squares; periods (.) represent unfilled
squares.
