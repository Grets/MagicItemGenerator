rm magicitemgen/MagicItemGen.class magicitemgen/Table.class MagicItemGen.jar
javac magicitemgen/MagicItemGen.java magicitemgen/Table.java
jar cfm MagicItemGen.jar manifest.txt magicitemgen/*.class magic.txt
