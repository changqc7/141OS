all: classes run
JFLAGS = -g
JC = javac -cp src
JVM= java -cp
.SUFFIXES: .java .class
.java.class:
		$(JC) $(JFLAGS) $<

CLASSES = \
		src/Disk.java \
		src/Printer.java \
		src/FileInfo.java \
		src/ResourceManager.java \
		src/DirectoryManager.java \
        src/DiskManager.java \
        src/PrintJobThread.java \
        src/UserThread.java \
		src/Main.java 


MAIN = src Main

default: classes

classes: $(CLASSES:.java=.class)

run: classes
	$(JVM) $(MAIN)

clean:
		$(RM) src/*.class
		$(RM) output/PRINTER*
