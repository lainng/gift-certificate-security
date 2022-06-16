insert into tag (tag_name) values ('tagName1');
insert into tag (tag_name) values ('tagName3');
insert into tag (tag_name) values ('tagName5');
insert into tag (tag_name) values ('tagName4');
insert into tag (tag_name) values ('tagName2');


INSERT INTO gift_certificate (name, description, price, duration, create_date, last_update_date)
VALUES ('giftCertificate1', 'description1', 99.9, 1, '2020-10-20T07:20:15.156', '2020-10-20T07:20:15.156');

INSERT INTO gift_certificate (name, description, price, duration, create_date, last_update_date)
VALUES ('giftCertificate3', 'description3', 100.99, 3, '2019-10-20T07:20:15.156', '2019-10-20T07:20:15.156');

INSERT INTO gift_certificate (name, description, price, duration, create_date, last_update_date)
VALUES ('giftCertificate2', 'description2', 999.99, 2, '2018-10-20T07:20:15.156', '2018-10-20T07:20:15.156');


INSERT INTO gift_certificate_with_tags (gift_certificate_id, tag_id)
VALUES (1, 2);

INSERT INTO gift_certificate_with_tags (gift_certificate_id, tag_id)
VALUES (2, 2);

INSERT INTO gift_certificate_with_tags (gift_certificate_id, tag_id)
VALUES (3, 4);

INSERT INTO gift_certificate_with_tags (gift_certificate_id, tag_id)
VALUES (3, 2);

INSERT INTO gift_certificate_with_tags (gift_certificate_id, tag_id)
VALUES (2, 4);


INSERT INTO "user" (name, email, password, role)
VALUES ('name1', 'email1@email.com', '$2a$12$uZ8GTbHV019Cfq1QuSR0xeEpsp6cse3s41E0r6BnLgpEJdEUdB6y2', 'USER');

INSERT INTO "user" (name, email, password, role)
VALUES ('name2', 'email2@email.com', '$2a$12$lwRYdasb8dKItMgwFPVd2u26C1s3kNySJSdT1abAPBhTKOLGdO1Gy', 'USER');


INSERT INTO "order" (cost, purchase_time, user_id, gift_certificate_id)
VALUES (10.1, '2020-10-20T07:20:15.156', 1, 3);

INSERT INTO "order" (cost, purchase_time, user_id, gift_certificate_id)
VALUES (30.3, '2019-10-20T07:20:15.156', 1, 2);

INSERT INTO "order" (cost, purchase_time, user_id, gift_certificate_id)
VALUES (20.2, '2018-10-20T07:20:15.156', 2, 1);