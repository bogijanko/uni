--1. Za polikliniku u kojoj ima najviše zaposlenih lekara specijalista prikazati imena svih pacijenta
--kao i datume pregleda. Ukoliko ima više klinika sa istim, najvećim brojem lekara specijalista –
--prikazati podatke imena svih pacijenata i datume pregleda za svaku od tih klinika.
SELECT p.ime, pre.datum, COUNT(*) AS broj_specijalista
FROM lekar l 
JOIN pregled pre 
ON pre.idlekara = l.idlekara
JOIN radi r 
ON l.idlekara = r.idlekara
JOIN pacijent p
ON p.idpacijenta = pre.idpacijenta
GROUP BY pre.idklinike, pre.idpacijenta
ORDER BY broj_specijalista DESC;
--2. Prikazati podatke o lekarima koji su izdali više od 10 recepata. 
SELECT l.*, COUNT(*) AS broj_recepata
FROM lekar l
JOIN pregled p 
ON l.idlekara= p.idlekara
JOIN recept r
ON p.idpregleda = r.idpregleda
GROUP BY p.idpregleda
HAVING broj_recepata > 10;
--3. Pronaći prve pacijente za svaku polikliniku. Prvi pacijent u jednoj poliklinici je onaj koji je uradio prvi pregled.
SELECT pac.*
FROM pacijent pac
JOIN pregled pre
ON pac.idpacijenta = pre.idpacijenta
ORDER BY pre.datum DESC
LIMIT 1;
--4. Sortirati u opadajućem redosledu spisak lekova koji su izdavani u svim poliklinikama, ispisati
--samo ime leka i broj recepata za taj lek.
SELECT l.naziv, COUNT(*) AS broj_recepata
FROM lek l 
JOIN recept r
ON l.idleka = r.idleka
JOIN pregled p
ON r.idpregleda = p.idpregleda
GROUP BY l.idleka,p.idklinike
ORDER BY l.naziv DESC;
--5. Napraviti pogled koji za svakog pacijenta ispisuje ime njegovog odabranog lekara opšte prakse, kao i to koliko dijagnoza mu je konstatovano.
CREATE VIEW PregledPacijenataIDijagnoza AS
SELECT pac.IDPACIJENTA,pac.IME,l.imelekara, COUNT(*) AS broj_dijagnoza
FROM lekar l
JOIN pacijent pac
ON l.idlekara = pac.idlekara
JOIN pregled pre
ON pac.idpacijenta = pre.idpacijenta
GROUP BY pre.dijagnoza;
--6.  Napisati upit kojim se prikazuje statistika na dnevnom nivou, za svakog lekara. Prikazati datum,
--ime lekara i broj pacijenata koje je pregledao tog dana. Za lekare koji nisu imali pacijente ne
--prikazivati rezultate.
SELECT l.imelekara, p.datum, COUNT(*) AS broj_pacijenata
FROM lekar l
JOIN pregled p 
ON l.idlekara = p.idlekara
GROUP BY p.datum
HAVING broj_pacijenata > 0;