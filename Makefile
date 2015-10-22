all:
	javac ballbouncer2/*.java

jar:
	jar cfm BallBouncer2.jar MANIFEST.MF ballbouncer2/*

run:
	java -jar BallBouncer2.jar

clean:
	rm ballbouncer2/*.class ballbouncer2/droppings/*.class
