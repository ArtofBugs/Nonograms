CLASSES = Board.class \
          BruteForce.class Backtracking.class RCBacktracking.class \
          NonParser.class Timer.class

.SUFFIXES: .java .class

.java.class:
	javac $*.java

all: $(CLASSES)
