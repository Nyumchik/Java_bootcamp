rm -rf target
javac -sourcepath src -d target/ src/java/edu/school21/printer/**/*.java
cp -R src/resources target/.

jar cfm ./target/images-to-chars-printer.jar src/manifest.txt -C target .
java -jar target/images-to-chars-printer.jar . 0