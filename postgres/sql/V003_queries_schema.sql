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