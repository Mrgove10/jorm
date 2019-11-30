create table Album
(
    ID          int auto_increment,
    Members     varchar(2048) null,
    Title       varchar(2048) null,
    DateRelease DATE          null,
    constraint creation_ID_uindex
        unique (ID)
)
    comment 'Table album';

alter table Album
    add primary key (ID);

create table LiveAlbum
(
    ID               int auto_increment,
    AlbumID          int           null,
    PlaceOfRecording varchar(2048) null,
    constraint liveAlbum_ID_uindex
        unique (ID),
    constraint liveAlbum_creation_ID_fk
        foreign key (AlbumID) references Album (ID)
)
    comment 'Table liveAlbum';

alter table LiveAlbum
    add primary key (ID);

create table BOAlbum
(
    ID         int auto_increment,
    AlbumID    int           null,
    Film       varchar(2048) null,
    constraint boAlbum_ID_uindex
        unique (ID),
    constraint boAlbum_creation_ID_fk
        foreign key (AlbumID) references Album (ID)
)
    comment 'Table BOAlbum';

alter table BOAlbum
    add primary key (ID);

-- Insertion de données
INSERT INTO `Album` (`ID`, `Members`, `Title`, `DateRelease`) VALUES (NULL, 'Nathan Gaume', 'Out Of Tomorrow', '2019-11-05'),
								     (NULL, 'Fabrice Lemoine,Colin Carpentier', "Honey, We're Crazy In Love", '2019-10-10'),
								     (NULL, 'Damien Lemaître,Alexandra Lozé', 'Dance With Me', '2019-11-14'),
								     (NULL, 'Clarisse Allaire', 'She knows You Miss Me', '2019-11-24'),
								     (NULL, 'Joséphine Vannier,Gwenaël Dutertre', "I think I'm In Love With You", '2018-12-14'),
								     (NULL, 'Kévin Thiers,Kévin Thiers,Rebecca Messier', 'Do You Remember?', '2019-09-05'),
								     (NULL, 'Élisabeth Barbeau,Carine Rochette,Alexis Alarie', 'Golden Tomorrow', '2019-11-13'),
								     (NULL, 'Leslie Sardou', 'She Loves She Knows', '2019-11-02'),
								     (NULL, 'Émilien Cochet,Roméo Périer', 'Remember The Times', '2019-10-08'),
								     (NULL, 'Samuel Guillaume,Claudia Masson', 'Dance With My Voice', '2019-12-09');
								     (NULL, 'Jacques Bougie', 'Images Of Fireworks', '2019-10-05'),
								     (NULL, 'Clémence Chopin', 'Gift Of A Dream', '2019-10-19'),
								     (NULL, 'Gabriel Robillard,Axel Delaunay', 'Male Power', '2019-10-30'),
								     (NULL, 'Bastien Celice,Diane Veil', 'Need Your Music', '2019-11-07'),
								     (NULL, 'Anna Arsenault', 'Heart Of The Morning', '2019-11-26'),
								     (NULL, 'Magali Jégou,Justine Girardot', 'I think I Know', '2019-10-27'),
								     (NULL, 'Jacques Bougie,Lucie Delisle', 'Light Kisses', '2019-09-03'),
								     (NULL, 'Jonathan Grandjean,Natanaël Loupe,Armelle Bernier', 'Last Games', '2020-01-10'),
								     (NULL, 'Nathan Rapace,Lara Calvet', 'She knows She Wants You', '2019-09-20'),
								     (NULL, 'Théodore Périer', 'I think She Knows', '2019-11-06')

INSERT INTO `BOAlbum` (`ID`, `AlbumID`, `Film`) VALUES (NULL, '7', 'La promenade des égoïstes'),
						       (NULL, '17', "Le masque d'un dictateur"),
						       (NULL, '10', 'La taverne des lucioles'),
						       (NULL, '13', 'La sensualité des îles'),
						       (NULL, '8', 'La solitude des anges')

INSERT INTO `LiveAlbum` (`ID`, `AlbumID`, `PlaceOfRecording`) VALUES (NULL, '9', 'Snowbush'),
								     (NULL, '22', 'Nearon '),
								     (NULL, '26', 'Aempleforth'),
								     (NULL, '15', 'Dalelry'),
								     (NULL, '11', 'Leeside')


