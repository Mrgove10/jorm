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


