-- aqui se rrellena la base 
INSERT INTO customers (first_name, last_name, middle_name, date_of_birth, phone, email) VALUES
    ('Juan', 'Pérez', 'Gómez', '1990-05-15', '+525512345678', 'juan.perez@example.com'),
    ('María', 'García', 'López', '1985-08-22', '+525523456789', 'maria.garcia@example.com');
    
    INSERT INTO video_games (
    name, platform, release_year, is_available,
    classification, genre, available_stock,
    rent_price, sale_price, developed_by, description
) VALUES (
    'The Legend of Zelda: Breath of the Wild',
    'Nintendo Switch',
    2017,
    TRUE,
    'E10+',
    'Adventure',
    5,
    150.00,
    1200.00,
    'Nintendo EPD',
    'An epic adventure in the kingdom of Hyrule where you must rescue Princess Zelda and defeat Ganon.'
);


-- 10. Insert sample data into administrators
INSERT INTO administrators (first_name, last_name, middle_name, email, password) VALUES
('Admin', 'User', NULL, 'admin@example.com', 'admin123');


INSERT INTO customers (first_name, last_name, middle_name, date_of_birth, phone, email) VALUES
('Carlos', 'García', 'Antonio', '1988-04-12', '+525512345601', 'carlos.garcia@email.com'),
('Laura', 'Rodríguez', 'Fernanda', '1993-07-25', '+525512345602', 'laura.rodriguez@email.com'),
('Jorge', 'Sánchez', NULL, '1991-11-05', '+525512345603', 'jorge.sanchez@email.com'),
('Patricia', 'Díaz', 'Lucía', '1985-09-18', '+525512345604', 'patricia.diaz@email.com'),
('Ricardo', 'Fernández', 'Javier', '1995-02-28', '+525512345605', 'ricardo.fernandez@email.com'),
('Sofía', 'Martínez', 'Alejandra', '1990-12-15', '+525512345606', 'sofia.martinez@email.com'),
('Miguel', 'López', 'Ángel', '1987-06-22', '+525512345607', 'miguel.lopez@email.com'),
('Elena', 'Pérez', 'Gabriela', '1994-03-08', '+525512345608', 'elena.perez@email.com'),
('Daniel', 'Gómez', 'Oscar', '1989-08-14', '+525512345609', 'daniel.gomez@email.com'),
('Adriana', 'Hernández', 'María', '1992-10-30', '+525512345610', 'adriana.hernandez@email.com'),
('Roberto', 'Jiménez', NULL, '1986-01-17', '+525512345611', 'roberto.jimenez@email.com'),
('Fernanda', 'Ruiz', 'Isabel', '1996-05-19', '+525512345612', 'fernanda.ruiz@email.com'),
('Alejandro', 'Torres', 'David', '1993-04-23', '+525512345613', 'alejandro.torres@email.com'),
('Lucía', 'Flores', 'Carmen', '1988-07-11', '+525512345614', 'lucia.flores@email.com'),
('José', 'Vargas', 'Manuel', '1991-09-03', '+525512345615', 'jose.vargas@email.com'),
('Valeria', 'Ramírez', 'Daniela', '1997-02-09', '+525512345616', 'valeria.ramirez@email.com'),
('Francisco', 'Morales', 'Luis', '1984-12-27', '+525512345617', 'francisco.morales@email.com'),
('Mariana', 'Ortega', 'Rosa', '1990-06-14', '+525512345618', 'mariana.ortega@email.com'),
('Raúl', 'Silva', 'Juan', '1994-08-21', '+525512345619', 'raul.silva@email.com'),
('Gabriela', 'Castro', 'Patricia', '1989-03-05', '+525512345620', 'gabriela.castro@email.com'),
('Arturo', 'Mendoza', NULL, '1995-11-12', '+525512345621', 'arturo.mendoza@email.com'),
('Diana', 'Ríos', 'Adriana', '1992-10-08', '+525512345622', 'diana.rios@email.com'),
('Oscar', 'Guerrero', 'Fernando', '1987-04-16', '+525512345623', 'oscar.guerrero@email.com'),
('Claudia', 'Núñez', 'Verónica', '1996-01-29', '+525512345624', 'claudia.nunez@email.com'),
('Manuel', 'Medina', 'Ricardo', '1993-07-07', '+525512345625', 'manuel.medina@email.com'),
('Teresa', 'Cortés', 'Silvia', '1988-09-24', '+525512345626', 'teresa.cortes@email.com'),
('Héctor', 'Rojas', 'Alberto', '1991-05-31', '+525512345627', 'hector.rojas@email.com'),
('Silvia', 'Campos', 'Laura', '1994-02-13', '+525512345628', 'silvia.campos@email.com'),
('Enrique', 'Santos', NULL, '1986-08-20', '+525512345629', 'enrique.santos@email.com'),
('Carmen', 'Vega', 'Elena', '1990-12-03', '+525512345630', 'carmen.vega@email.com');


INSERT INTO video_games (name, platform, release_year, is_available, classification, genre, available_stock, rent_price, sale_price, developed_by, description) VALUES
('Grand Theft Auto V', 'PlayStation 5', 2022, TRUE, 'M', 'Action', 15, 65.00, 1400.00, 'Rockstar Games', 'Juego de mundo abierto en Los Santos.'),
('FIFA 23', 'Xbox Series X', 2022, TRUE, 'E', 'Sports', 20, 55.00, 1200.00, 'EA Sports', 'El mejor simulador de fútbol.'),
('Minecraft', 'Nintendo Switch', 2017, TRUE, 'E10+', 'Sandbox', 25, 40.00, 900.00, 'Mojang', 'Construye y explora mundos infinitos.'),
('Call of Duty: Modern Warfare II', 'PlayStation 5', 2022, TRUE, 'M', 'FPS', 12, 70.00, 1500.00, 'Infinity Ward', 'Dispara en campaña y multijugador.'),
('The Witcher 3: Wild Hunt', 'PC', 2015, TRUE, 'M', 'RPG', 18, 50.00, 1000.00, 'CD Projekt Red', 'Aventura épica de Geralt de Rivia.'),
('Animal Crossing: New Horizons', 'Nintendo Switch', 2020, TRUE, 'E', 'Simulation', 10, 45.00, 1100.00, 'Nintendo', 'Vida relajada en una isla paradisíaca.'),
('Red Dead Redemption 2', 'Xbox Series X', 2018, TRUE, 'M', 'Action-Adventure', 8, 75.00, 1600.00, 'Rockstar Games', 'Historia del vaquero Arthur Morgan.'),
('Cyberpunk 2077', 'PlayStation 5', 2020, TRUE, 'M', 'RPG', 14, 60.00, 1300.00, 'CD Projekt Red', 'Futuro distópico en Night City.'),
('Super Smash Bros. Ultimate', 'Nintendo Switch', 2018, TRUE, 'E10+', 'Fighting', 22, 50.00, 1200.00, 'Nintendo', 'Lucha con personajes icónicos.'),
('Horizon Forbidden West', 'PlayStation 5', 2022, TRUE, 'T', 'Action-RPG', 9, 80.00, 1700.00, 'Guerrilla Games', 'Aventura de Aloy en un mundo postapocalíptico.'),
('Assassin’s Creed Valhalla', 'Xbox Series X', 2020, TRUE, 'M', 'Action-Adventure', 11, 65.00, 1400.00, 'Ubisoft', 'Invasión vikinga en Inglaterra.'),
('The Last of Us Part II', 'PlayStation 5', 2020, TRUE, 'M', 'Action-Adventure', 7, 70.00, 1500.00, 'Naughty Dog', 'Historia emocional de Ellie y Joel.'),
('Pokémon Scarlet', 'Nintendo Switch', 2022, TRUE, 'E', 'RPG', 30, 55.00, 1200.00, 'Game Freak', 'Aventura en la región de Paldea.'),
('Resident Evil 4 Remake', 'PlayStation 5', 2023, TRUE, 'M', 'Horror', 6, 85.00, 1800.00, 'Capcom', 'Reimaginación del clásico de terror.'),
('Star Wars Jedi: Survivor', 'Xbox Series X', 2023, TRUE, 'T', 'Action-Adventure', 10, 75.00, 1600.00, 'Respawn', 'Continúa la historia de Cal Kestis.'),
('Sonic Frontiers', 'Nintendo Switch', 2022, TRUE, 'E10+', 'Adventure', 15, 50.00, 1100.00, 'Sega', 'Sonic en un mundo abierto.'),
('Dead Space Remake', 'PlayStation 5', 2023, TRUE, 'M', 'Horror', 5, 80.00, 1700.00, 'EA Motive', 'Terror espacial renovado.'),
('Forza Horizon 5', 'Xbox Series X', 2021, TRUE, 'E', 'Racing', 25, 60.00, 1300.00, 'Playground Games', 'Carreras en México.'),
('Mario Kart 8 Deluxe', 'Nintendo Switch', 2017, TRUE, 'E', 'Racing', 20, 45.00, 1000.00, 'Nintendo', 'Diversión con amigos en pistas locas.'),
('Final Fantasy XVI', 'PlayStation 5', 2023, TRUE, 'M', 'RPG', 12, 90.00, 1900.00, 'Square Enix', 'Nueva entrega de la saga FF.'),
('Hogwarts Legacy', 'Xbox Series X', 2023, TRUE, 'T', 'Action-RPG', 18, 75.00, 1600.00, 'Avalanche', 'Vive como estudiante en Hogwarts.'),
('Metroid Prime Remastered', 'Nintendo Switch', 2023, TRUE, 'T', 'Adventure', 8, 60.00, 1300.00, 'Retro Studios', 'Remaster del clásico de Samus.'),
('Dead Island 2', 'PlayStation 5', 2023, TRUE, 'M', 'Action', 10, 70.00, 1500.00, 'Dambuster Studios', 'Zombies en Los Ángeles.'),
('Sea of Stars', 'Nintendo Switch', 2023, TRUE, 'E10+', 'RPG', 14, 50.00, 1100.00, 'Sabotage', 'Juego indie estilo retro.'),
('Street Fighter 6', 'PlayStation 5', 2023, TRUE, 'T', 'Fighting', 16, 65.00, 1400.00, 'Capcom', 'Nueva entrega del clásico de pelea.'),
('Pikmin 4', 'Nintendo Switch', 2023, TRUE, 'E', 'Strategy', 12, 55.00, 1200.00, 'Nintendo', 'Aventura con pequeñas criaturas.'),
('Diablo IV', 'Xbox Series X', 2023, TRUE, 'M', 'RPG', 9, 85.00, 1800.00, 'Blizzard', 'Regreso del juego de acción-RPG.'),
('Octopath Traveler II', 'Nintendo Switch', 2023, TRUE, 'T', 'RPG', 11, 60.00, 1300.00, 'Square Enix', 'Historia de 8 personajes únicos.'),
('Armored Core VI', 'PlayStation 5', 2023, TRUE, 'T', 'Action', 7, 75.00, 1600.00, 'FromSoftware', 'Mechas y combate intenso.'),
('Baldur’s Gate 3', 'PC', 2023, TRUE, 'M', 'RPG', 13, 80.00, 1700.00, 'Larian Studios', 'RPG basado en Dungeons & Dragons.');


INSERT INTO administrators (first_name, last_name, middle_name, email, password) VALUES
('a', 'Uss', NULL, 'la@lo', 'a');

INSERT INTO promotions (purchase_amount, frequency_discount) VALUES
(1000.00, 5.00),
(1200.00, 7.00),
(1400.00, 9.00),
(1600.00, 11.00),
(1800.00, 13.00),
(2000.00, 15.00),
(2200.00, 17.00),
(2400.00, 19.00);

USE Base_de_datos_renta;

SELECT * FROM transactions;
