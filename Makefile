APPLETVIEWER = ./appletviewer
JAVAC = ./javac
JAVA = ./java

run:
	make compile
	make run-applet

compile: Main.java ExprScriptCompiler*
	$(JAVAC) Main.java
	$(JAVAC) ExprScriptCompiler.java
	$(JAVAC) ExprScriptCompilerTest.java

run-applet: Main.java
	$(APPLETVIEWER) Main.java

run-normal:
	$(JAVA) Main

test:
	make compile
	$(JAVA) ExprScriptCompilerTest
