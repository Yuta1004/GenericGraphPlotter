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
	@echo "ExprParser"
	$(JAVAC) ESParser/ExprParserTest.java
	$(JAVA) ESParser/ExprParserTest
	@echo "\n"
	@echo "ScriptParser"
	$(JAVAC) ESParser/ScriptParserTest.java
	$(JAVA) ESParser/ScriptParserTest


clean:
	rm -rf *.class ESParser/*.class GraphDrawer/*.class
