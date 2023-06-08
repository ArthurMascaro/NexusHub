--! SUBJECT QUERIES --!


-- Select By Id
SELECT * FROM nexushub_platform.subject WHERE id='f3010575-bb49-45c8-ba59-55117aec93e0'::uuid;

-- Select All By User id
SELECT * FROM nexushub_platform.subject WHERE owner_id='f7467ed7-624e-4834-94c3-388dc6ec3072'::uuid;

--! APPLICATION USER QUERIES --!

-- Application User by username
SELECT * FROM nexushub_platform.application_user WHERE username='arthur';

-- Application User by id
SELECT * FROM nexushub_platform.application_user WHERE id='f7467ed7-624e-4834-94c3-388dc6ec3072'::uuid;


--! TO-DO QUERIES --!


-- Select All peding to-do's
SELECT * FROM nexushub_platform.todo WHERE status='PENDING';

-- Select All completed to-do's
SELECT * FROM nexushub_platform.todo WHERE status='DONE';

-- Select All to-do's for a specific date
SELECT * FROM nexushub_platform.todo WHERE date='2023-06-09';


--! Cycle QUERIES --!


-- Select Cycle by user Id
SELECT * FROM nexushub_platform.cycle WHERE owner_id='f7467ed7-624e-4834-94c3-388dc6ec3072'::uuid;

-- Select Cycle by id
SELECT * FROM nexushub_platform.cycle WHERE id='19fe9b65-6700-4f9a-8c20-fddecec1fc24'::uuid;

-- Select Sequence by Cycle Id
SELECT * FROM nexushub_platform.sequence WHERE cycle_id='19fe9b65-6700-4f9a-8c20-fddecec1fc24'::uuid;

-- Select last Sequence
SELECT * FROM nexushub_platform.sequence WHERE step = (SELECT MAX(step) FROM nexushub_platform.sequence);

-- Select current Sequence Subject
SELECT * FROM nexushub_platform.sequence_subject WHERE status='STUDYING';

-- Select peding Sequence Subject
SELECT * FROM nexushub_platform.sequence_subject WHERE status='PENDING';

-- Select finished Sequence Subject
SELECT * FROM nexushub_platform.sequence_subject WHERE status='FINISHED';

-- Select current Sequence Subject in the last sequence
SELECT * FROM nexushub_platform.sequence_subject ss
    INNER JOIN nexushub_platform.sequence s on s.id = ss.sequence_id
    WHERE ss.status='STUDYING' AND s.step = (SELECT MAX(step) FROM nexushub_platform.sequence);


--! DECK QUERIES --!


-- Select Deck by user Id
SELECT * FROM nexushub_platform.deck WHERE owner_id='f7467ed7-624e-4834-94c3-388dc6ec3072'::uuid;

-- Select Deck by id
SELECT * FROM nexushub_platform.deck WHERE id='592d50d6-59fa-4dc6-b73e-ad90bed0776b'::uuid;

-- Select Deck by parent id
SELECT * FROM nexushub_platform.deck WHERE parent_deck_id='592d50d6-59fa-4dc6-b73e-ad90bed0776b'::uuid;


--! FLASHCARD QUERIES --!


-- Select Flashcard by deck id
SELECT * FROM nexushub_platform.flashcard WHERE deck_id='32e3b118-dd9d-40be-af06-6b0da51d39f0'::uuid;

-- Select Flashcard by id
SELECT * FROM nexushub_platform.flashcard WHERE id='0c2ff5d3-0fa8-4379-a33e-f3e5fb355503'::uuid;

-- Select Flashcard with the lowest maturity
SELECT * FROM nexushub_platform.flashcard WHERE maturity = (SELECT MIN(maturity) FROM nexushub_platform.flashcard);

-- Select all the New Flashcards
SELECT * FROM nexushub_platform.flashcard WHERE status='NEW';

-- Select all the Flashcards with Last revision date before today
SELECT * FROM nexushub_platform.flashcard WHERE last_revision_date < CURRENT_DATE;


--! SESSION QUERIES --!

-- Select Session by user id
SELECT * FROM nexushub_platform.session WHERE owner_id='f7467ed7-624e-4834-94c3-388dc6ec3072'::uuid;

-- Select Session who has Subject
SELECT * FROM nexushub_platform.session s WHERE s.subject_id IN
                                                (SELECT subject_id FROM nexushub_platform.session);

SELECT * FROM nexushub_platform.session s WHERE s.subject_id IS NOT null;

-- Select the duration of the session
SELECT end_time - start_time as duration FROM nexushub_platform.session WHERE id='18c5687a-14e8-438f-a6ad-99c1cd13c3fa'::uuid;

-- Select the session with the longest duration
SELECT * FROM nexushub_platform.session WHERE end_time - start_time =
                                              (SELECT MAX(end_time - start_time) FROM nexushub_platform.session);



