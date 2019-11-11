create table creation
(
    ID          int auto_increment,
    creatorName varchar(2048) null,
    title       varchar(2048) null,
    yearRelease int           null,
    constraint creation_ID_uindex
        unique (ID)
)
    comment 'creation of an artist';

alter table creation
    add primary key (ID);

create table liveAlbum
(
    ID               int auto_increment,
    creationID       int           null,
    placeOfRecording varchar(2048) null,
    constraint liveAlbum_ID_uindex
        unique (ID),
    constraint liveAlbum_creation_ID_fk
        foreign key (creationID) references creation (ID)
)
    comment 'live recordings';

alter table liveAlbum
    add primary key (ID);

create table music
(
    ID         int auto_increment,
    creationID int           null,
    name       varchar(2048) null,
    constraint music_ID_uindex
        unique (ID),
    constraint music_creation_ID_fk
        foreign key (creationID) references creation (ID)
)
    comment 'music creations';

alter table music
    add primary key (ID);

create table ost
(
    ID         int auto_increment,
    creationID int           null,
    film       varchar(2048) null,
    constraint ost_ID_uindex
        unique (ID),
    constraint ost_creation_ID_fk
        foreign key (creationID) references creation (ID)
)
    comment 'original sound track';

alter table ost
    add primary key (ID);

