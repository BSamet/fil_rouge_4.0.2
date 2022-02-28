CREATE TABLE forms2d
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name       VARCHAR(255) NOT NULL,
    longueur   DOUBLE       NULL,
    largeur    DOUBLE       NULL,
    rayon      DOUBLE       NULL
);

CREATE TABLE forms3d
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    rayon      DOUBLE NULL
);


INSERT INTO forms2d
VALUES (1, 'Rectangle 1', 10, 10, 0);
INSERT INTO forms2d
VALUES (2, 'Rectangle 2', 20, 20, 0);
INSERT INTO forms2d
VALUES (3, 'Rectangle 3', 30, 30, 0);

INSERT INTO forms3d
VALUES (1, 30);
INSERT INTO forms3d
VALUES (2, 20);
INSERT INTO forms3d
VALUES (3, 10);