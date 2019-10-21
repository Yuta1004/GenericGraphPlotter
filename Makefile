APPLETVIEWER = ./appletviewer
JAVAC = ./javac
JAVA = ./java

run:
	make compile
	make run-applet

compile: Main.java
	$(JAVAC) Main.java

run-applet: Main.java
	$(APPLETVIEWER) Main.java

run-normal:
	$(JAVA) Main

test:
	$(JAVAC) ESParser/ExprParserTest.java
	$(JAVA) ESParser/ExprParserTest

clean:
	rm -rf *.class ESParser/*.class
