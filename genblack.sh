#!/bin/sh
# Generate black nonogram puzzle of given dim
# Bart Massey 2021

N="$1"

cat <<EOF
title "black"
width $N
height $N

EOF

echo columns
for i in `seq $N`
do
    echo $N
done

echo ""

echo rows
for i in `seq $N`
do
    echo $N
done

echo ""

N2=`expr $N \* $N`
echo -n goal \"
for i in `seq $N2`
do
    echo -n 1
done
echo \"

echo ""

N1=`expr $N - 1`
for i in `seq $N`
do
    echo -n \*
    for j in `seq $N1`
    do
        echo -n ' *'
    done
    echo ""
done
