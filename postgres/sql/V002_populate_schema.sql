--! SQL para criar o banco de dados --!


--! CRIA USUARIO --!
insert into nexushub_platform.application_user (id, name, username, password,
    is_account_non_expired, is_account_nonlocked, is_credentials_non_expired, is_enabled)
        values ('f7467ed7-624e-4834-94c3-388dc6ec3072'::uuid, 'arthur mascaro', 'arthur', '$2a$10$Jw8xjo5aMTEfYICDI19VNulcj3RBYl4vgOSj5q5EZdX0u7Uym0Jty', true, true, true, true);

--! CRIA MATERIAS PARA USUÁRIO --!
insert into nexushub_platform.subject (id, name, difficulty, owner_id, color)
    values ('f3010575-bb49-45c8-ba59-55117aec93e0', 'Matemática', 7, 'f7467ed7-624e-4834-94c3-388dc6ec3072', 'BLUE');

insert into nexushub_platform.subject (id, name, difficulty, owner_id, color)
    values ('c62f835d-d035-4b4e-b1ed-ac949c84f3b4', 'Português', 5, 'f7467ed7-624e-4834-94c3-388dc6ec3072', 'RED');

insert into nexushub_platform.subject (id, name, difficulty, owner_id, color)
    values ('9fe78067-907e-4d00-a94c-38ad4857ebdc', 'História', 3, 'f7467ed7-624e-4834-94c3-388dc6ec3072', 'GREEN');