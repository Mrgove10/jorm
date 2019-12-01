create table Album (
  ID int auto_increment,
  Members varchar(2048) null,
  Title varchar(2048) null,
  DateRelease DATE null,
  constraint creation_ID_uindex unique (ID)
) comment 'Table album';
alter table Album
add
  primary key (ID);
create table LiveAlbum (
    ID int auto_increment,
    AlbumID int null,
    PlaceOfRecording varchar(2048) null,
    constraint liveAlbum_ID_uindex unique (ID),
    constraint liveAlbum_creation_ID_fk foreign key (AlbumID) references Album (ID) on update cascade on delete cascade
  ) comment 'Table liveAlbum';
alter table LiveAlbum
add
  primary key (ID);
create table BOAlbum (
    ID int auto_increment,
    AlbumID int null,
    Film varchar(2048) null,
    constraint boAlbum_ID_uindex unique (ID),
    constraint boAlbum_creation_ID_fk foreign key (AlbumID) references Album (ID) on update cascade on delete cascade
  ) comment 'Table BOAlbum';
alter table BOAlbum
add
  primary key (ID);
-- Insertion de données
INSERT INTO `Album` (`ID`, `Members`, `Title`, `DateRelease`)
VALUES
  (
    1,
    "Nathan Gaume",
    "Out Of Tomorrow",
    "2019-11-05"
  ),
  (
    2,
    "Fabrice Lemoine,Colin Carpentier",
    "Honey, We're Crazy In Love",
    "2019-10-10"
  ),
  (
    3,
    "Damien Lemaître,Alexandra Lozé",
    "Dance With Me",
    "2019-11-14"
  ),
  (
    4,
    "Clarisse Allaire",
    "She knows You Miss Me",
    "2019-11-24"
  ),
  (
    5,
    "Joséphine Vannier,Gwenaël Dutertre",
    "I think I'm In Love With You",
    "2018-12-14"
  ),
  (
    6,
    "Kévin Thiers,Kévin Thiers,Rebecca Messier",
    "Do You Remember?",
    "2019-09-05"
  ),
  (
    7,
    "Élisabeth Barbeau,Carine Rochette,Alexis Alarie",
    "Golden Tomorrow",
    "2019-11-13"
  ),
  (
    8,
    "Leslie Sardou",
    "She Loves She Knows",
    "2019-11-02"
  ),
  (
    9,
    "Émilien Cochet,Roméo Périer",
    "Remember The Times",
    "2019-10-08"
  ),
  (
    10,
    "Samuel Guillaume,Claudia Masson",
    "Dance With My Voice",
    "2019-12-09"
  ),
  (
    11,
    "Jacques Bougie",
    "Images Of Fireworks",
    "2019-10-05"
  ),
  (
    12,
    "Clémence Chopin",
    "Gift Of A Dream",
    "2019-10-19"
  ),
  (
    13,
    "Gabriel Robillard,Axel Delaunay",
    "Male Power",
    "2019-10-30"
  ),
  (
    14,
    "Bastien Celice,Diane Veil",
    "Need Your Music",
    "2019-11-07"
  ),
  (
    15,
    "Anna Arsenault",
    "Heart Of The Morning",
    "2019-11-26"
  ),
  (
    16,
    "Magali Jégou,Justine Girardot",
    "I think I Know",
    "2019-10-27"
  ),
  (
    17,
    "Jacques Bougie,Lucie Delisle",
    "Light Kisses",
    "2019-09-03"
  ),
  (
    18,
    "Jonathan Grandjean,Natanaël Loupe,Armelle Bernier",
    "Last Games",
    "2020-01-10"
  ),
  (
    19,
    "Nathan Rapace,Lara Calvet",
    "She knows She Wants You",
    "2019-09-20"
  ),
  (
    20,
    "Théodore Périer",
    "I think She Knows",
    "2019-11-06"
  );
INSERT INTO `BOAlbum` (`ID`, `AlbumID`, `Film`)
VALUES
  (1, "7", "La promenade des égoïstes"),
  (2, "17", "Le masque d'un dictateur "),
  (3, " 10 ", " La taverne des lucioles "),
  (4, " 13 ", " La sensualité des îles "),
  (5, " 8 ", " La solitude des anges ");
INSERT INTO `LiveAlbum` (`ID`, `AlbumID`, `PlaceOfRecording`)
VALUES
  (1, " 9 ", " Snowbush "),
  (2, " 20 ", " Nearon "),
  (3, " 6 ", " Aempleforth "),
  (4, " 15 ", " Dalelry "),
  (5, " 11 ", " Leeside ");