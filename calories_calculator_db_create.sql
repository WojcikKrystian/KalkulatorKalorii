create table dish (id bigint not null auto_increment, cal_total integer, carbs_total integer, fat_total integer, name varchar(255), proteins_total integer, primary key (id)) engine=InnoDB;
create table ingredient (id bigint not null auto_increment, amount decimal(19,2), carbs integer, fat integer, kcal integer, name varchar(255), proteins integer, dish_id bigint, primary key (id)) engine=InnoDB;
alter table ingredient add constraint FKl5flopow34ng2dywdl2p3l5b8 foreign key (dish_id) references dish (id);

