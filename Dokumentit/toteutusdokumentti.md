# Toteutus

## Yleisrakenne

Ohjelman käyttöliittymä on toteutettu hyödyntäen javan Swing-kirjastoa.
Ohjelma koostuu muuten omista tietorakenteista, kuten:

- Cell: Yksittäinen labyrintin solu, jolla on koordinaatit, sekä tieto ympärillä olevista seinistä
- CellList: Listarakenne labyrintin soluille
- CellStack: Pinorakenne labyrintin soluille
- CellQueue: Jonorakenne labyrintin soluille
- DisjointSet: Aputietorakenne sille mihin joukkoon solu kuuluu (Käytetään kruskalin algoritmissa)

Sekä labyrintin generointialgoritmeista, jotka ovat seuraavat:

- Aldous-Broder
- Binääripuu
- Primin algoritmi
- Satunnaistettu syvyyshaku (Recursive Backtracker)
- Metsästä ja tapa (Hunt and kill)
- Kruskalin algoritmi

Labyrintin ratkaisualgoritmina leveyshaku


## Pientä algoritmien vertailua
- Aldous-Broder
  - Ei tuota visuaalisesti kovin miellyttäviä labyrintteja (paljon suhteellisen lyhyitä umpikujia)
  - Myöskin suoritusaika on todella pitkä
- Binääripuu
  - Tuottaa samantyylisen labyrintin aina ja reitti labyrintin vasemmasta yläkulmasta oikeaan alakulmaan on aina suhteellisen suora ja halkaisee labyrintin diagonaalisti
- Primin algoritmi
  - Tuottaa myöskin paljon umpikujia, jotka ovat todella lyhyitä ja reitit labyrintissa ovat aika suoria
- Satunnaistettu syvyyshaku
  - Tuottaa visuaalisesti hyvän näköisiä labyrintteja, jonka reitit ovat mutkittelevia ja umpikujat ovat paljon pidempiä ja niitä on vähemmän
- Metsästä ja tapa
  - On hyvin samantyyppinen satunnaistetun syvyyshaun kanssa, koska algoritmi perustuu osittain syvyyshakuun
  - Tuottaa myöskin visuaalisesti miellyttävän labyrintin, jonka reitit kiemurtelevat paljon
- Kruskalin algoritmi
  - Tämä on implementoitu ehkä vähän väärällä tavalla tähän projektiin, mutta toimii jotenkin
  - Ei tuota kovin miellyttävää lopputulosta ja sisältää paljon todella lyhyitä umpikujia
  - Reitit ovat suhteellisen myös suoria
## Puutteet ja ongelmat

Käyttöliittymän koodi on hieman sekavaa ja sitä voisi parantaa vielä huomattavasti.
Käyttöliittymässä on myös pieni epäloogisuus, että algoritmin nopeutta ei voi valita enää ratkaisua varten, vaan se ajetaan samalla nopeudella kuin labyrintin luontivaiheessa.

Alla listatut ongelmat liittyvät pitkälti siihen, että lähdin toteuttamaan alusta pitäen koko ohjelmaa vähän väärällä tavalla. Olisi pitänyt olla jokin yleiskuva heti alkuun millaisen rakenteen haluan ohjelmalle.

 - Kaikki algoritmit eivät toimi ihan täysin kuin niiden pitäisi, johtuen tavasta miten olen toteuttanut esimerkiksi labyrintin seinien käsittelyn, mutta niiden perusidea pätee kuitenkin.

 - Koodissa on myös muutama staattinen muuttuja, jonka avulla kerrotaan onko labyrintti generoitu tai ratkaistu. Tämä tuotti yksikkötestien kirjoittamisessa ainakin vähän ongelmia, koska algoritmien suorittaminen perustuu pitkälti näihin muuttujiin.
    En ainakaan itse pidä näistä staattisista muuttujista tämän takia, mutta päätin ajan puitteissa jättää asian näin, koska se kuitenkin vaikuttaa toimivan.

 - Myöskin jokainen luokka algoritmeille sisältää yhden metodin, joka suorittaa algoritmin yhden askeleen, vaikka ideaalitapauksessa käytössä olisi luokka, jonka metodia kutsuessa algoritmi tekisi kaiken mitä aikoo tehdä, eikä tarvitsisi muualla miettiä onko se valmistunut vai ei.


## Lähteet

https://weblog.jamisbuck.org/2011/2/7/maze-generation-algorithm-recap

https://en.wikipedia.org/wiki/Maze_generation_algorithm