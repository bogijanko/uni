/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     07/01/2021 22:21:51                          */
/*==============================================================*/


drop table if exists dosijepacijenta;

drop table if exists klinika;

drop table if exists lek;

drop table if exists lekar;

drop table if exists lekaropsteprakse;

drop table if exists lekarspecijalista;

drop table if exists pacijent;

drop table if exists pregled;

drop table if exists radi;

drop table if exists recept;

drop table if exists zakazivanje;

/*==============================================================*/
/* Table: dosijepacijenta                                       */
/*==============================================================*/
create table dosijepacijenta
(
   iddosijea            int not null auto_increment,
   idpregleda           int,
   primary key (iddosijea)
);

/*==============================================================*/
/* Table: klinika                                               */
/*==============================================================*/
create table klinika
(
   idklinike            int not null auto_increment,
   nazivklinike         char(100),
   mesto                char(50) not null,
   brojtelefona         char(13) not null,
   primary key (idklinike)
);

/*==============================================================*/
/* Table: lek                                                   */
/*==============================================================*/
create table lek
(
   idleka               int not null auto_increment,
   naziv                char(100) not null,
   proizvodjac          char(100) not null,
   cena                 int not null,
   primary key (idleka)
);

/*==============================================================*/
/* Table: lekar                                                 */
/*==============================================================*/
create table lekar
(
   idlekara             int not null auto_increment,
   imelekara            char(20) not null,
   prezimelekara        char(20) not null,
   primary key (idlekara)
);

/*==============================================================*/
/* Table: lekaropsteprakse                                      */
/*==============================================================*/
create table lekaropsteprakse
(
   idlekara             int not null auto_increment,
   imelekara            char(20) not null,
   prezimelekara        char(20) not null,
   telefon              char(20),
   primary key (idlekara)
);

/*==============================================================*/
/* Table: lekarspecijalista                                     */
/*==============================================================*/
create table lekarspecijalista
(
   idlekara             int not null auto_increment,
   imelekara            char(20) not null,
   prezimelekara        char(20) not null,
   specijalnost         char(200) not null,
   primary key (idlekara)
);

/*==============================================================*/
/* Table: pacijent                                              */
/*==============================================================*/
create table pacijent
(
   idpacijenta          int not null auto_increment,
   iddosijea            int not null,
   idlekara             int not null,
   ime                  char(50) not null,
   adresa               char(100),
   kontakttelefon       char(15),
   primary key (idpacijenta)
);

/*==============================================================*/
/* Table: pregled                                               */
/*==============================================================*/
create table pregled
(
   idpregleda           int not null auto_increment,
   idklinike            int,
   idpacijenta          int,
   idlekara             int,
   idzakazivanja        int,
   nalaz_opis           text not null,
   dijagnoza            text not null,
   datum                date not null,
   vreme                time not null,
   primary key (idpregleda)
);

/*==============================================================*/
/* Table: radi                                                  */
/*==============================================================*/
create table radi
(
   idlekara             int not null,
   idklinike            int not null,
   primary key (idlekara, idklinike)
);

/*==============================================================*/
/* Table: recept                                                */
/*==============================================================*/
create table recept
(
   sifrarecepta         int not null,
   idleka               int not null,
   idpregleda           int not null,
   nazivleka            char(100) not null,
   nacinupotrebe        text not null,
   primary key (sifrarecepta)
);

/*==============================================================*/
/* Table: zakazivanje                                           */
/*==============================================================*/
create table zakazivanje
(
   idzakazivanja        int not null auto_increment,
   idklinike            int not null,
   idpacijenta          int not null,
   idlekara             int not null,
   datumzakazivanja     date not null,
   vremezakazivanja     time,
   primary key (idzakazivanja)
);

alter table dosijepacijenta add constraint fk_upisivanje foreign key (idpregleda)
      references pregled (idpregleda) on delete restrict on update restrict;

alter table lekaropsteprakse add constraint fk_specijalizacija foreign key (idlekara)
      references lekar (idlekara) on delete restrict on update restrict;

alter table lekarspecijalista add constraint fk_specijalizacija2 foreign key (idlekara)
      references lekar (idlekara) on delete restrict on update restrict;

alter table pacijent add constraint fk_istorija foreign key (iddosijea)
      references dosijepacijenta (iddosijea) on delete restrict on update restrict;

alter table pacijent add constraint fk_prati foreign key (idlekara)
      references lekaropsteprakse (idlekara) on delete restrict on update restrict;

alter table pregled add constraint fk_mestopregleda foreign key (idklinike)
      references klinika (idklinike) on delete restrict on update restrict;

alter table pregled add constraint fk_pregled foreign key (idpacijenta)
      references pacijent (idpacijenta) on delete restrict on update restrict;

alter table pregled add constraint fk_pregledalekar foreign key (idlekara)
      references lekarspecijalista (idlekara) on delete restrict on update restrict;

alter table pregled add constraint fk_zakazivanjepregleda foreign key (idzakazivanja)
      references zakazivanje (idzakazivanja) on delete restrict on update restrict;

alter table radi add constraint fk_radi foreign key (idklinike)
      references klinika (idklinike) on delete restrict on update restrict;

alter table radi add constraint fk_radi2 foreign key (idlekara)
      references lekarspecijalista (idlekara) on delete restrict on update restrict;

alter table recept add constraint fk_izdati foreign key (idpregleda)
      references pregled (idpregleda) on delete restrict on update restrict;

alter table recept add constraint fk_prepisan foreign key (idleka)
      references lek (idleka) on delete restrict on update restrict;

alter table zakazivanje add constraint fk_gde foreign key (idklinike)
      references klinika (idklinike) on delete restrict on update restrict;

alter table zakazivanje add constraint fk_kod foreign key (idlekara)
      references lekarspecijalista (idlekara) on delete restrict on update restrict;

alter table zakazivanje add constraint fk_zakazuje foreign key (idpacijenta)
      references pacijent (idpacijenta) on delete restrict on update restrict;

