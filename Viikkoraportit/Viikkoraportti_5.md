# Viikko 5

Tähän mennessä lisätty muutamia testejä lisää ja mockito-kirjasto, jotta voidaan automaattisesti testata algoritmien toimintaa.

Tällä viikolla pitää vielä:

- [x] Refaktoroida algoritmien toteuttavia luokkia, jotta niiden testaaminen onnistuu fiksummin kuin tällä hetkellä
    - Yhdistetty algoritmit yhteen rajapintaan, jonka avulla kutsutaan niitä käyttöliittymästä ja siirretty ajastintoiminto myös käyttöliittymän puolelle, jotta algoritmien testaaminen on helpomaa
- [x] Toteuttaa jokin algoritmi, jonka avulla testata, että generointialgoritmit luovat validin labyrintin
    - Toteutettu leveyshaku, jonka avulla on mahdollista nähdä, että labyrintin jokaiseen soluun pääsee
    - Tämän mukana toteutettu myös oma tietorakenne jonolle (CellQueue)
- [x] Lisätä automaattiset testit algoritmeille
    - Lisätty vasta binääripuu algoritmille testi

Tällä viikolla oli vähän vaikeuksia käyttöliittymän ja leveyshaun lisäämisen kanssa

Ensi viikolla:

- Lisätä testit muillekin algoritmeille
- Lisätä leveyshakuun keino löytää lyhin reitti aloituspisteestä päätöspisteeseen ja lisätä käyttöliittymään mahdollisuus valita nämä pisteet
- Koodin refaktorointia

