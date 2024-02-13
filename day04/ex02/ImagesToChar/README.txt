rm -rf target
mkdir lib
wget -Olib/JCDP-4.0.2.jar https://repo1.maven.org/maven2/com/diogonunes/JCDP/4.0.2/JCDP-4.0.2.jar
wget -Olib/jcommander-1.82.jar https://repo1.maven.org/maven2/com/beust/jcommander/1.82/jcommander-1.82.jar

javac -classpath lib/JCDP-4.0.2.jar:lib/jcommander-1.82.jar -d ./target/ src/java/edu/school21/printer/*/*.java

cd target
jar xf ./../lib/JCDP-4.0.2.jar com ; jar xf ./../lib/jcommander-1.82.jar com
cd ..

cp -R src/resources target/.

jar cfm ./target/images-to-chars-printer.jar src/manifest.txt -C target .

java -jar ./target/images-to-chars-printer.jar --white=BLACK --black=GREEN