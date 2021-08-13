# Nonograms... this is a yet unfinished project :)

Currently, this project contains a brute force nonogram solver. It supports
black-and-white nonograms following the .non nonogram file format; more
information can be found at mikix's puzzle database at
[https://github.com/mikix/nonogram-db](URL). This project doesn't yet support
bundles (.nonpack files) or colored nonograms.

To download the files in my repository, type

`git clone https://github.com/ArtofBugs/Nonograms`

in your command line (or copy the line above by triple-clicking it, and then
paste it into your command line using Ctrl-Shift-V) and hitting the
enter/return key. You may need to install git first; you can use

`sudo apt install git`

To run the brute force solver from the command line, first use

`javac BruteForce.java`

As an example, if I wanted to solve the included heart.non puzzle, I'd type

`java BruteForce heart.non`

It may take some time for the solver to finish, but it should eventually print
the amount of time it took to solve the puzzle (this does not include parsing
time) as well as the solution to the puzzle. In the solution, asterisks (*)
represent filled squares; periods (.) represent unfilled squares.

***************

You could test other .non files from mikix's repository by running

`cd ..`
`git clone https://github.com/mikix/nonogram-db`
`cd ./Nonograms`

Then run

`java BruteForce ../nonogram-db/db/folder/puzzle.non`

replacing "puzzle" with the name of the puzzle and "folder" with the name of
the folder the puzzle is in (currently, this will be gnonograms, qnonograms, or
webpbn).

If you'd like to make your own puzzle, create a .non file and follow the format
provided in the FORMAT.md file in mikix's nonogram-db repository.

***************

