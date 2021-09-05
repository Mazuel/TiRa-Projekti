# Labyrintti algoritmeja Javalla

Erilaisten algoritmien visualisointia labyrinttien generoinnissa

## Käyttöohje

Suoritettavan jar:in voi luoda ja ajaa seuraavalla tavalla:

1. **./gradlew jar**
2. **java -jar app/build/libs/mazealgorithms.jar**

Jar-tiedostolle voi antaa argumenttina labyrintin koon, vaihtoehtoja on kolme (small, medium, large)

esim. jos haluttaisiin keskikokoinen labyrintti: **java -jar app/build/libs/mazealgorithms.jar medium**

Main-metodi sijaitsee luokassa Maze.java


**./gradlew build** -komento ajaa testit, checkstylen, jacoco-raportin ja luo suoritettavan jar-tiedoston

**./gradlew test** -komento ajaa pelkästään yksikkötestit
