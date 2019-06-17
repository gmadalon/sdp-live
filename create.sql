create sequence customer_sq start with 1 increment by  1
create sequence execution_log_sq start with 1 increment by  1
create sequence service_provider_sq start with 1 increment by  1
create sequence subscription_sq start with 1 increment by  1
create table application (id number(19,0) not null, external_application_id varchar2(40 char), name varchar2(40 char), service_provider_id number(19,0), primary key (id))
create table customer (id number(19,0) not null, cpf varchar2(11 char) not null, external_co_id varchar2(255 char) not null, external_customer_id varchar2(255 char) not null, primary key (id))
create table execution_log (id number(19,0) not null, cpf varchar2(255 char), error_code number(10,0), event number(10,0) not null, exception_message varchar2(4000 char), external_application_id varchar2(50 char), external_co_id varchar2(50 char), external_customer_id varchar2(50 char), is_ok number(1,0) not null, log_date TIMESTAMP not null, message varchar2(1000 char), subscription_id varchar2(50 char), transaction_id varchar2(50 char), primary key (id))
create table service_provider (id number(19,0) not null, name varchar2(40 char), subscription_notification_url varchar2(1000 char), primary key (id))
create table subscription (id number(19,0) not null, subscription_date TIMESTAMP, subscription_id varchar2(255 char), transaction_id varchar2(255 char), application_id number(19,0), customer_id number(19,0), primary key (id))
alter table application add constraint UK_b3sdp8nptt20rxx6hadu4dxu4 unique (external_application_id)
alter table customer add constraint UK_cwtgtb16nebxu54elskdjei66 unique (cpf)
alter table customer add constraint UK_kjbqtihxii1oa2u9i80xpj19e unique (external_co_id)
alter table customer add constraint UK_guh9i2802yhgxqlrhsg6218nb unique (external_customer_id)
alter table subscription add constraint UKop9nb1a5ya7bn9uqmdataa4vc unique (customer_id, application_id)
alter table application add constraint FKh9o69e3337ggmyajuyj1egack foreign key (service_provider_id) references service_provider
alter table subscription add constraint FKm8hlhil1xr78g95cqsnrtb6fp foreign key (application_id) references application
alter table subscription add constraint FKg04cmtkq431k68csu8emybpcc foreign key (customer_id) references customer
create sequence customer_sq start with 1 increment by  1
create sequence execution_log_sq start with 1 increment by  1
create sequence service_provider_sq start with 1 increment by  1
create sequence subscription_sq start with 1 increment by  1
create table application (id number(19,0) not null, external_application_id varchar2(40 char), name varchar2(40 char), service_provider_id number(19,0), primary key (id))
create table customer (id number(19,0) not null, cpf varchar2(11 char) not null, external_co_id varchar2(255 char) not null, external_customer_id varchar2(255 char) not null, primary key (id))
create table execution_log (id number(19,0) not null, cpf varchar2(255 char), error_code number(10,0), event number(10,0) not null, exception_message varchar2(4000 char), external_application_id varchar2(50 char), external_co_id varchar2(50 char), external_customer_id varchar2(50 char), is_ok number(1,0) not null, log_date TIMESTAMP not null, message varchar2(1000 char), subscription_id varchar2(50 char), transaction_id varchar2(50 char), primary key (id))
create table service_provider (id number(19,0) not null, name varchar2(40 char), subscription_notification_url varchar2(1000 char), primary key (id))
create table subscription (id number(19,0) not null, subscription_date TIMESTAMP, subscription_id varchar2(255 char), transaction_id varchar2(255 char), application_id number(19,0), customer_id number(19,0), primary key (id))
alter table application add constraint UK_b3sdp8nptt20rxx6hadu4dxu4 unique (external_application_id)
alter table customer add constraint UK_cwtgtb16nebxu54elskdjei66 unique (cpf)
alter table customer add constraint UK_kjbqtihxii1oa2u9i80xpj19e unique (external_co_id)
alter table customer add constraint UK_guh9i2802yhgxqlrhsg6218nb unique (external_customer_id)
alter table subscription add constraint UKop9nb1a5ya7bn9uqmdataa4vc unique (customer_id, application_id)
alter table application add constraint FKh9o69e3337ggmyajuyj1egack foreign key (service_provider_id) references service_provider
alter table subscription add constraint FKm8hlhil1xr78g95cqsnrtb6fp foreign key (application_id) references application
alter table subscription add constraint FKg04cmtkq431k68csu8emybpcc foreign key (customer_id) references customer
create sequence customer_sq start with 1 increment by  1
create sequence execution_log_sq start with 1 increment by  1
create sequence service_provider_sq start with 1 increment by  1
create sequence subscription_sq start with 1 increment by  1
create table application (id number(19,0) not null, external_application_id varchar2(40 char), name varchar2(40 char), service_provider_id number(19,0), primary key (id))
create table customer (id number(19,0) not null, cpf varchar2(11 char) not null, external_co_id varchar2(255 char) not null, external_customer_id varchar2(255 char) not null, primary key (id))
create table execution_log (id number(19,0) not null, cpf varchar2(255 char), error_code number(10,0), event number(10,0) not null, exception_message varchar2(4000 char), external_application_id varchar2(50 char), external_co_id varchar2(50 char), external_customer_id varchar2(50 char), is_ok number(1,0) not null, log_date TIMESTAMP not null, message varchar2(1000 char), subscription_id varchar2(50 char), transaction_id varchar2(50 char), primary key (id))
create table service_provider (id number(19,0) not null, name varchar2(40 char), subscription_notification_url varchar2(1000 char), primary key (id))
create table subscription (id number(19,0) not null, subscription_date TIMESTAMP, subscription_id varchar2(255 char), transaction_id varchar2(255 char), application_id number(19,0), customer_id number(19,0), primary key (id))
alter table application add constraint UK_b3sdp8nptt20rxx6hadu4dxu4 unique (external_application_id)
alter table customer add constraint UK_cwtgtb16nebxu54elskdjei66 unique (cpf)
alter table customer add constraint UK_kjbqtihxii1oa2u9i80xpj19e unique (external_co_id)
alter table customer add constraint UK_guh9i2802yhgxqlrhsg6218nb unique (external_customer_id)
alter table subscription add constraint UKop9nb1a5ya7bn9uqmdataa4vc unique (customer_id, application_id)
alter table application add constraint FKh9o69e3337ggmyajuyj1egack foreign key (service_provider_id) references service_provider
alter table subscription add constraint FKm8hlhil1xr78g95cqsnrtb6fp foreign key (application_id) references application
alter table subscription add constraint FKg04cmtkq431k68csu8emybpcc foreign key (customer_id) references customer
create sequence customer_sq start with 1 increment by  1
create sequence execution_log_sq start with 1 increment by  1
create sequence service_provider_sq start with 1 increment by  1
create sequence subscription_sq start with 1 increment by  1
create table application (id number(19,0) not null, external_application_id varchar2(40 char), name varchar2(40 char), service_provider_id number(19,0), primary key (id))
create table customer (id number(19,0) not null, cpf varchar2(11 char) not null, external_co_id varchar2(255 char) not null, external_customer_id varchar2(255 char) not null, primary key (id))
create table execution_log (id number(19,0) not null, cpf varchar2(255 char), error_code number(10,0), event number(10,0) not null, exception_message varchar2(4000 char), external_application_id varchar2(50 char), external_co_id varchar2(50 char), external_customer_id varchar2(50 char), is_ok number(1,0) not null, log_date TIMESTAMP not null, message varchar2(1000 char), subscription_id varchar2(50 char), transaction_id varchar2(50 char), primary key (id))
create table service_provider (id number(19,0) not null, name varchar2(40 char), subscription_notification_url varchar2(1000 char), primary key (id))
create table subscription (id number(19,0) not null, subscription_date TIMESTAMP, subscription_id varchar2(255 char), transaction_id varchar2(255 char), application_id number(19,0), customer_id number(19,0), primary key (id))
alter table application add constraint UK_b3sdp8nptt20rxx6hadu4dxu4 unique (external_application_id)
alter table customer add constraint UK_cwtgtb16nebxu54elskdjei66 unique (cpf)
alter table customer add constraint UK_kjbqtihxii1oa2u9i80xpj19e unique (external_co_id)
alter table customer add constraint UK_guh9i2802yhgxqlrhsg6218nb unique (external_customer_id)
alter table subscription add constraint UKop9nb1a5ya7bn9uqmdataa4vc unique (customer_id, application_id)
alter table application add constraint FKh9o69e3337ggmyajuyj1egack foreign key (service_provider_id) references service_provider
alter table subscription add constraint FKm8hlhil1xr78g95cqsnrtb6fp foreign key (application_id) references application
alter table subscription add constraint FKg04cmtkq431k68csu8emybpcc foreign key (customer_id) references customer
create sequence customer_sq start with 1 increment by  1
create sequence execution_log_sq start with 1 increment by  1
create sequence service_provider_sq start with 1 increment by  1
create sequence subscription_sq start with 1 increment by  1
create table application (id number(19,0) not null, external_application_id varchar2(40 char), name varchar2(40 char), service_provider_id number(19,0), primary key (id))
create table customer (id number(19,0) not null, cpf varchar2(11 char) not null, external_co_id varchar2(255 char) not null, external_customer_id varchar2(255 char) not null, primary key (id))
create table execution_log (id number(19,0) not null, cpf varchar2(255 char), error_code number(10,0), event number(10,0) not null, exception_message varchar2(4000 char), external_application_id varchar2(50 char), external_co_id varchar2(50 char), external_customer_id varchar2(50 char), is_ok number(1,0) not null, log_date TIMESTAMP not null, message varchar2(1000 char), subscription_id varchar2(50 char), transaction_id varchar2(50 char), primary key (id))
create table service_provider (id number(19,0) not null, name varchar2(40 char), subscription_notification_url varchar2(1000 char), primary key (id))
create table subscription (id number(19,0) not null, subscription_date TIMESTAMP, subscription_id varchar2(255 char), transaction_id varchar2(255 char), application_id number(19,0), customer_id number(19,0), primary key (id))
alter table application add constraint UK_b3sdp8nptt20rxx6hadu4dxu4 unique (external_application_id)
alter table customer add constraint UK_cwtgtb16nebxu54elskdjei66 unique (cpf)
alter table customer add constraint UK_kjbqtihxii1oa2u9i80xpj19e unique (external_co_id)
alter table customer add constraint UK_guh9i2802yhgxqlrhsg6218nb unique (external_customer_id)
alter table subscription add constraint UKop9nb1a5ya7bn9uqmdataa4vc unique (customer_id, application_id)
alter table application add constraint FKh9o69e3337ggmyajuyj1egack foreign key (service_provider_id) references service_provider
alter table subscription add constraint FKm8hlhil1xr78g95cqsnrtb6fp foreign key (application_id) references application
alter table subscription add constraint FKg04cmtkq431k68csu8emybpcc foreign key (customer_id) references customer
create sequence customer_sq start with 1 increment by  1
create sequence execution_log_sq start with 1 increment by  1
create sequence service_provider_sq start with 1 increment by  1
create sequence subscription_sq start with 1 increment by  1
create table application (id number(19,0) not null, external_application_id varchar2(40 char), name varchar2(40 char), service_provider_id number(19,0), primary key (id))
create table customer (id number(19,0) not null, cpf varchar2(11 char) not null, external_co_id varchar2(255 char) not null, external_customer_id varchar2(255 char) not null, primary key (id))
create table execution_log (id number(19,0) not null, cpf varchar2(255 char), error_code number(10,0), event number(10,0) not null, exception_message varchar2(4000 char), external_application_id varchar2(50 char), external_co_id varchar2(50 char), external_customer_id varchar2(50 char), is_ok number(1,0) not null, log_date TIMESTAMP not null, message varchar2(1000 char), subscription_id varchar2(50 char), transaction_id varchar2(50 char), primary key (id))
create table service_provider (id number(19,0) not null, name varchar2(40 char), subscription_notification_url varchar2(1000 char), primary key (id))
create table subscription (id number(19,0) not null, subscription_date TIMESTAMP, subscription_id varchar2(255 char), transaction_id varchar2(255 char), application_id number(19,0), customer_id number(19,0), primary key (id))
alter table application add constraint UK_b3sdp8nptt20rxx6hadu4dxu4 unique (external_application_id)
alter table customer add constraint UK_cwtgtb16nebxu54elskdjei66 unique (cpf)
alter table customer add constraint UK_kjbqtihxii1oa2u9i80xpj19e unique (external_co_id)
alter table customer add constraint UK_guh9i2802yhgxqlrhsg6218nb unique (external_customer_id)
alter table subscription add constraint UKop9nb1a5ya7bn9uqmdataa4vc unique (customer_id, application_id)
alter table application add constraint FKh9o69e3337ggmyajuyj1egack foreign key (service_provider_id) references service_provider
alter table subscription add constraint FKm8hlhil1xr78g95cqsnrtb6fp foreign key (application_id) references application
alter table subscription add constraint FKg04cmtkq431k68csu8emybpcc foreign key (customer_id) references customer
create sequence customer_sq start with 1 increment by  1
create sequence execution_log_sq start with 1 increment by  1
create sequence service_provider_sq start with 1 increment by  1
create sequence subscription_sq start with 1 increment by  1
create table application (id number(19,0) not null, external_application_id varchar2(40 char), name varchar2(40 char), service_provider_id number(19,0), primary key (id))
create table customer (id number(19,0) not null, cpf varchar2(11 char) not null, external_co_id varchar2(255 char) not null, external_customer_id varchar2(255 char) not null, primary key (id))
create table execution_log (id number(19,0) not null, cpf varchar2(255 char), error_code number(10,0), event number(10,0) not null, exception_message varchar2(4000 char), external_application_id varchar2(50 char), external_co_id varchar2(50 char), external_customer_id varchar2(50 char), is_ok number(1,0) not null, log_date TIMESTAMP not null, message varchar2(1000 char), subscription_id varchar2(50 char), transaction_id varchar2(50 char), primary key (id))
create table service_provider (id number(19,0) not null, name varchar2(40 char), subscription_notification_url varchar2(1000 char), primary key (id))
create table subscription (id number(19,0) not null, subscription_date TIMESTAMP, subscription_id varchar2(255 char), transaction_id varchar2(255 char), application_id number(19,0), customer_id number(19,0), primary key (id))
alter table application add constraint UK_b3sdp8nptt20rxx6hadu4dxu4 unique (external_application_id)
alter table customer add constraint UK_cwtgtb16nebxu54elskdjei66 unique (cpf)
alter table customer add constraint UK_kjbqtihxii1oa2u9i80xpj19e unique (external_co_id)
alter table customer add constraint UK_guh9i2802yhgxqlrhsg6218nb unique (external_customer_id)
alter table subscription add constraint UKop9nb1a5ya7bn9uqmdataa4vc unique (customer_id, application_id)
alter table application add constraint FKh9o69e3337ggmyajuyj1egack foreign key (service_provider_id) references service_provider
alter table subscription add constraint FKm8hlhil1xr78g95cqsnrtb6fp foreign key (application_id) references application
alter table subscription add constraint FKg04cmtkq431k68csu8emybpcc foreign key (customer_id) references customer
