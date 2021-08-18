#!/bin/sh
# Nonogram benchmark runner
# Bart Massey 2021

PROGS="Backtracking RCBacktracking"

case "$1" in
    -a) PROGS="BruteForce $PROGS" ;;
esac

for prog in $PROGS
do
    echo -n "${prog}:"
    java $prog "$@"
done
