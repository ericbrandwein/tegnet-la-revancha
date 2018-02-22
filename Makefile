build:
	kotlinc src/Main.kt -include-runtime -d Main.jar

run:
	java -jar Main.jar