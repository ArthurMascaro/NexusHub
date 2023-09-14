--! SQL for populate database --!


--! User Queries --!


insert into nexushub_platform.application_user (id, name, username, password,
    is_account_non_expired, is_account_nonlocked, is_credentials_non_expired, is_enabled)
        values ('f7467ed7-624e-4834-94c3-388dc6ec3072'::uuid, 'arthur mascaro', 'arthur',
                '$2a$10$Jw8xjo5aMTEfYICDI19VNulcj3RBYl4vgOSj5q5EZdX0u7Uym0Jty', true, true, true, true);

insert into nexushub_platform.application_user (id, name, username, password,
                                                is_account_non_expired, is_account_nonlocked, is_credentials_non_expired, is_enabled)
        values ('c7265bb5-3d44-4e33-b420-13dd2cf783dd'::uuid, 'arthur mascaro2', 'arthur22',
        '$2a$10$cQyDx0O1.eGALqe0IPhjQu1Rt6F0FkD7y9iK32iFS8zxhXOrb/JD2', true, true, true, true);


--! Subjects Queries --!


insert into nexushub_platform.subject (id, name, difficulty, owner_id, color)
    values ('f3010575-bb49-45c8-ba59-55117aec93e0'::uuid, 'Matemática', 7,
            'f7467ed7-624e-4834-94c3-388dc6ec3072'::uuid, '#AD6FEB');

insert into nexushub_platform.subject (id, name, difficulty, owner_id, color)
    values ('c62f835d-d035-4b4e-b1ed-ac949c84f3b4'::uuid, 'Português', 5,
            'f7467ed7-624e-4834-94c3-388dc6ec3072'::uuid, '#FFC107');

insert into nexushub_platform.subject (id, name, difficulty, owner_id, color)
    values ('9fe78067-907e-4d00-a94c-38ad4857ebdc'::uuid, 'História', 3,
            'f7467ed7-624e-4834-94c3-388dc6ec3072'::uuid, '#FF5722');

insert into nexushub_platform.subject (id, name, difficulty, owner_id, color)
    values ('701a4fc5-543a-4aad-9cc9-71ae596171d1'::uuid, 'Biologia', 2,
        'f7467ed7-624e-4834-94c3-388dc6ec3072'::uuid, '#4CAF50');


--! To-do Queries --!


insert into nexushub_platform.todo (id, text, date, status, subject_id)
    values ('4ee7e798-d502-44ab-97d4-038f35afd943'::uuid, 'Estudar Matemática',
        '2023-06-08', 'PENDING', 'f3010575-bb49-45c8-ba59-55117aec93e0');

insert into nexushub_platform.todo (id, text, date, status, subject_id)
    values ('bfb3463d-c91f-4a01-a252-77db4b7ac9c4'::uuid, 'Estudar Português',
        '2023-06-08', 'PENDING', 'c62f835d-d035-4b4e-b1ed-ac949c84f3b4');

insert into nexushub_platform.todo (id, text, date, status, subject_id)
    values ('476763f6-6c78-4e1e-8171-e12d078ce87d'::uuid, 'Estudar História',
        '2023-06-08', 'DONE', '9fe78067-907e-4d00-a94c-38ad4857ebdc');


--! Cycle Queries --!


insert into nexushub_platform.cycle (id, name, description, owner_id, amount_hours)
    values ('19fe9b65-6700-4f9a-8c20-fddecec1fc24'::uuid, 'Ciclo de Estudos',
        'Ciclo de estudos para o vestibular', 'f7467ed7-624e-4834-94c3-388dc6ec3072'::uuid, 10);

insert into nexushub_platform.sequence(id, step, last_sequence_subject_step, status, cycle_id)
    values ('faf57f7c-ae00-4c90-bee2-9fc7ca1444f7'::uuid, 1, 1, 'RUNNING', '19fe9b65-6700-4f9a-8c20-fddecec1fc24');

insert into nexushub_platform.sequence_subject(id, step, hours, studied_hours, status, sequence_id, subject_id)
    values ('a2adde45-5f1f-446d-b906-8190c63aa1cc'::uuid, 1, 04.07, 0,
        'STUDYING', 'faf57f7c-ae00-4c90-bee2-9fc7ca1444f7'::uuid, 'f3010575-bb49-45c8-ba59-55117aec93e0'::uuid);

insert into nexushub_platform.sequence_subject(id, step, hours, studied_hours, status, sequence_id, subject_id)
    values ('7eb7af17-5625-486f-901c-db51fd4cc989'::uuid, 2, 01.46, 0,
        'PENDING', 'faf57f7c-ae00-4c90-bee2-9fc7ca1444f7'::uuid, '9fe78067-907e-4d00-a94c-38ad4857ebdc'::uuid);

insert into nexushub_platform.sequence_subject(id, step, hours, studied_hours, status, sequence_id, subject_id)
    values ('99bdc3b6-9d8a-46ba-93be-af747ab5c6eb'::uuid, 3, 02.56, 0,
        'PENDING', 'faf57f7c-ae00-4c90-bee2-9fc7ca1444f7'::uuid, 'c62f835d-d035-4b4e-b1ed-ac949c84f3b4'::uuid);

insert into nexushub_platform.sequence_subject(id, step, hours, studied_hours, status, sequence_id, subject_id)
    values ('af57ac50-448b-482f-87c7-6d30469b9e8e'::uuid, 3, 01.11, 0,
        'PENDING', 'faf57f7c-ae00-4c90-bee2-9fc7ca1444f7'::uuid, '701a4fc5-543a-4aad-9cc9-71ae596171d1'::uuid);


--! Deck Queries --!


insert into nexushub_platform.deck(id, name, owner_id, subject_id, parent_deck_id)
    values ('592d50d6-59fa-4dc6-b73e-ad90bed0776b'::uuid, 'Matemática',
        'f7467ed7-624e-4834-94c3-388dc6ec3072'::uuid, 'f3010575-bb49-45c8-ba59-55117aec93e0'::uuid, null);

insert into nexushub_platform.deck(id, name, owner_id, subject_id, parent_deck_id)
    values ('32e3b118-dd9d-40be-af06-6b0da51d39f0'::uuid, 'Geometria Plana',
        'f7467ed7-624e-4834-94c3-388dc6ec3072'::uuid, null, '592d50d6-59fa-4dc6-b73e-ad90bed0776b'::uuid);


--! Flashcard Queries --!


insert into nexushub_platform.flashcard(id, question, answer, next_revision_date,
                                        last_revision_date, status, maturity, deck_id)
        values ('0c2ff5d3-0fa8-4379-a33e-f3e5fb355503'::uuid, 'Uma simples pergunta', 'Uma simples resposta',
        '2023-06-10', '2023-06-08', 'LEARNED', 0.99, '32e3b118-dd9d-40be-af06-6b0da51d39f0'::uuid);

insert into nexushub_platform.flashcard(id, question, answer, next_revision_date,
                                        last_revision_date, status, maturity, deck_id)
        values ('36e8c0c2-1ac8-416f-8192-7e1766baba7c'::uuid, 'Uma simples pergunta', 'Uma simples resposta',
        '2023-06-9', '2023-06-01', 'REVIEWING', 0.37, '32e3b118-dd9d-40be-af06-6b0da51d39f0'::uuid);

insert into nexushub_platform.flashcard(id, question, answer, next_revision_date,
                                        last_revision_date, status, maturity, deck_id)
        values ('c2e5ee42-0191-4b9a-8cf6-2d6b229ff7c0'::uuid, 'Uma simples pergunta', 'Uma simples resposta',
        '2023-06-10', '2023-06-03', 'NEW', 0, '32e3b118-dd9d-40be-af06-6b0da51d39f0'::uuid);


--! TAG Queries --!


insert into nexushub_platform.tag(id, name, owner_id)
    values ('9861587b-b1ce-4c5d-9614-f949846043e0'::uuid, 'TAG TESTE 1',
        'f7467ed7-624e-4834-94c3-388dc6ec3072'::uuid);

insert into nexushub_platform.tag(id, name, owner_id)
values ('44c2e88c-1f49-482c-b0fe-aaebdc2396c0'::uuid, 'TAG TESTE 2',
        'f7467ed7-624e-4834-94c3-388dc6ec3072'::uuid);


--! FLASHCARD TAG Queries --!


insert into nexushub_platform.flashcard_tag(flashcard_id, tag_id)
    values ('0c2ff5d3-0fa8-4379-a33e-f3e5fb355503'::uuid, '9861587b-b1ce-4c5d-9614-f949846043e0'::uuid);

insert into nexushub_platform.flashcard_tag(flashcard_id, tag_id)
values ('0c2ff5d3-0fa8-4379-a33e-f3e5fb355503'::uuid, '44c2e88c-1f49-482c-b0fe-aaebdc2396c0'::uuid);

insert into nexushub_platform.flashcard_tag(flashcard_id, tag_id)
    values ('36e8c0c2-1ac8-416f-8192-7e1766baba7c'::uuid, '9861587b-b1ce-4c5d-9614-f949846043e0'::uuid);


--! SESSION Queries --!


insert into nexushub_platform.session(id, start_time, end_time,
                                      owner_id, subject_id, sequence_subject_id, deck_id)
        values('239287f3-98e1-4990-88b4-3df3b4974892'::uuid, '2023-06-10 10:00:00', '2023-06-10 11:00:00',
        'f7467ed7-624e-4834-94c3-388dc6ec3072'::uuid, null, null, null);

insert into nexushub_platform.session(id, start_time, end_time,
                                      owner_id, subject_id, sequence_subject_id, deck_id)
values('b2594d47-3580-4151-b89f-034c02f030b3'::uuid, '2023-06-10 08:00:00', '2023-06-10 12:00:00',
       'f7467ed7-624e-4834-94c3-388dc6ec3072'::uuid, 'f3010575-bb49-45c8-ba59-55117aec93e0'::uuid, null, null);

insert into nexushub_platform.session(id, start_time, end_time,
                                      owner_id, subject_id, sequence_subject_id, deck_id)
values('81a4c320-cd44-4cd6-a092-26dee72c7e6c'::uuid, '2023-06-10 12:00:00', '2023-06-10 12:17:10',
       'f7467ed7-624e-4834-94c3-388dc6ec3072'::uuid, null, '99bdc3b6-9d8a-46ba-93be-af747ab5c6eb'::uuid, null);

insert into nexushub_platform.session(id, start_time, end_time,
                                      owner_id, subject_id, sequence_subject_id, deck_id)
values('18c5687a-14e8-438f-a6ad-99c1cd13c3fa'::uuid, '2023-06-10 01:00:00', '2023-06-10 01:01:10',
       'f7467ed7-624e-4834-94c3-388dc6ec3072'::uuid, null, null, '592d50d6-59fa-4dc6-b73e-ad90bed0776b'::uuid);