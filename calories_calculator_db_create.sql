create table dish (id bigint not null auto_increment, cal_total double precision, carbs_total double precision, fat_total double precision, name varchar(255), proteins_total double precision, primary key (id)) engine=InnoDB;
create table ingredient (id bigint not null auto_increment, amount double precision, carbs double precision, fat double precision, kcal double precision, name varchar(255), proteins double precision, dish_id bigint, primary key (id)) engine=InnoDB;
alter table ingredient add constraint FKl5flopow34ng2dywdl2p3l5b8 foreign key (dish_id) references dish (id);
