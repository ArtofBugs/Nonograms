# Nonograms... this is a yet unfinished project :)

Currently, this project contains a brute force nonogram solver. It supports
black-and-white nonograms following the .non nonogram file format; more
information can be found at mikix's puzzle database at
https://github.com/mikix/nonogram-db. This project doesn't yet support bundles
(.nonpack files) or colored nonograms.

You can clone mikix's repository by typing
"git clone https://github.com/mikix/nonogram-db" (without quotation marks)
into your command line and hitting the enter/return key. (You may need to
install git first; you can use "sudo apt install git".) You can also use the
simpler test.non file in my repository; use
"git clone https://github.com/ArtofBugs/Nonograms". In either of these cases,
the file will be downloaded to your computer. If you'd like to make your own
puzzle, follow the format provided in the FORMAT.md file in mikix's nonogram-db
repository to make your own .non file.

To run the brute force solver on a command line, use "javac BruteForce.java"
and hit enter/return. Then use "java BruteForce filepath" (replacing "filepath"
with the path of your .non file) and hit enter/return again.
It may take some time for the solver to finish, but it should eventually print
the amount of time it took to solve the puzzle as well as the solution to the
puzzle. In the solution, asterisks (*) represent filled squares; periods (.)
represent unfilled squares.
