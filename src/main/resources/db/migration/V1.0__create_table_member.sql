create table Member (id bigint not null, email varchar(255), name varchar(255), phone_number varchar(255), primary key (id));
alter table if exists Member drop constraint if exists member_uniq_email;
alter table if exists Member add constraint member_uniq_email unique (email);
create sequence Member_SEQ start with 1 increment by 50;