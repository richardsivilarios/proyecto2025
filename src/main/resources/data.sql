/* JOSUE RAFAEL SANCHEZ QUIROGA
INSERT INTO academico.personal(
	idusuario, nombre, ap, am, estado, fnac, ecivil, genero, dir, telf, tipo, foto, create_time, update_time)
	VALUES (1,'JOSE RAFAEL','SANCHEZ','QUIROGA', 1,'01-10-1990' ,1,'MASCULINO','ZONA EL TEJAR','756456454','E','sin foto','2023-01-01 00:19:38','2023-01-01 00:19:38');
*/

/*mybatis-plus-join  ejemplo*/
DELETE
FROM academico.user;

INSERT INTO academico.user (id, name, age, email)
VALUES (1, 'Jone', 18, 'test1@baomidou.com'),
       (2, 'Jack', 20, 'test2@baomidou.com'),
       (3, 'Tom', 28, 'test3@baomidou.com'),
       (4, 'Sandy', 21, 'test4@baomidou.com'),
       (5, 'Billie', 24, 'test5@baomidou.com');

DELETE
FROM academico.address;

INSERT INTO academico.address (id, user_id, city, address)
VALUES (1, 1, 'Běijīng', 'rénmín guǎngchǎng'),
       (2, 2, 'shànghǎi', 'rénmín guǎngchǎng'),
       (3, 3, 'guǎngzhōu', 'rénmín guǎngchǎng'),
       (4, 4, 'shànghǎi', 'rénmín guǎngchǎng'),
       (5, 5, 'běijīng', 'rénmín guǎngchǎng');
