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
	$(JAVAC) ESCompiler/ExprScriptCompilerTest.java
	$(JAVA) ESCompiler/ExprScriptCompilerTest

clean:
	rm -rf *.class
