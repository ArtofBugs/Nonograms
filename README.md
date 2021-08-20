# How to use the nonogram solvers in this repository!

Currently, this project supports black-and-white nonograms following the .non
nonogram file format; more information can be found at mikix's puzzle database
repository at [https://github.com/mikix/nonogram-db](URL). This project doesn't
yet support bundles (.nonpack files) or colored nonograms.

To download the files in my repository, type

`git clone https://github.com/ArtofBugs/Nonograms`

in your command line (or copy the line above by triple-clicking it, and then
paste it into your command line using Ctrl-Shift-V) and hit the
enter/return key. You may need to install git first; you can use

`sudo apt install git`

Then navigate to the new Nonograms folder using

`cd ./Nonograms`

Then type

`make`

You may have to install make; use

`sudo apt install make`

Now you can pick a solver and a puzzle. As an example, if I wanted to solve the
included heart.non puzzle using brute force, I'd type

`sh runner.sh -a heart.non`

It may take some time for the solver to finish, but it should eventually print,
to standard output (your command line) the amount of time it took to solve the
puzzle (this does not include parsing time) as well as the first solution to the
puzzle that it finds (if any). In the solution, asterisks (*) represent filled
squares; periods (.) represent unfilled squares. If no solution exists, the
solver will print "NO SOLUTION" as well as the time it took to reach that
conclusion.

***************

If you wanted to test the other two solvers or other puzzles in my repository,
use the same sh command listed above, but replace "heart.non" with the path
of the puzzle file and remove the `-a` option. Currently, the available solvers are brute force, backtracking, and row-column switching backtracking (called "RCBacktracking"). To run backtracking and RCBacktracking together only, leave
out the `-a`.

***************

Another included tool is the goal parser (used in the case you want to cheat and
just get the solution off of the goal listed in a .non puzzle, or you
legitimately want to make sure a solver is solving correctly). To run that, type

`javac GoalParser.java`

and then

`java GoalParser puzzle`

replacing "puzzle" with the name of the puzzle. (If the puzzle's not from my
repository, replace "puzzle" with the path of the puzzle file.) It will print a
representation of the puzzle's solution to standard error, following the same
format that the solvers follow (asterisks for filled squares, periods for
unfilled squares).

***************

If you wanted, you could also solve .non puzzles from mikix's repository by running

`cd ..`

`git clone https://github.com/mikix/nonogram-db`

`cd ./Nonograms`

Then run

`sh runner.sh [option] ../nonogram-db/db/folder/name`

replacing "name" with the name of the puzzle and "folder" with the name of
the folder the puzzle is in (currently, this will be gnonograms, qnonograms, or
webpbn). mikix also has puzzle generators that you can use.

If you'd like to make your own puzzle, create a text file with the .non file 
ending and follow the format provided in the FORMAT.md file in mikix's
nonogram-db repository.

***************


