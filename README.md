# NexusHub (Back-end)- Study management application

## Description
Application with multiple study techniques that are designed to improve the learning process and reduce the time wasted while studying. The app includes organizational tools and flashcards for revision

## Target Audience
The main focus of the application is to be used by students of all levels, from primary school students to university students 

## Objectives
* Organize and prioritize the order of subjects to be studied;
* Better control of time spent;
* Reviewing content in a dynamic way.

## Technologies
Front-end  | Back-end
------------- | -------------
React (JavaScript)  | Spring Boot (Java)
Next.js  | PostgreSQL
Global State Management (Redux, COntext, etc.) |
Figma |
Tailwind |

## Development Team
### See below the developers of the NexusHub software:
- `Arthur Vicente Mascaro`
- `Gustavo Rodrigues Trizotti`

## Application Setup
### Creating the Database

In IntelliJ Ultimate, open the database tab and connect to your machine's postgresql database.

Then go to the directory postgre/sql where you'll execute the following files:
- ``V000_setup.sql``
- ``V001_create_schema.sql``
- ``V002_populate_schema.sql``

Afterwards, select and run all the code in ``V000_setup.sql`` in a session from the database connection you just made. After successfully running the code in this file you'll have created a database called "nexushub" with an owner "nexushub-app". You should be able to verify these changes in pgAdmin4.

Now connect to the "nexushub" database in the same way you connected to the postgresql one. Afterwards, select and run all the code in ``V001_create_schema.sql`` in a session in the DB connection you just made. Then repeat the same proccess with the ``V002_populate_schema.sql`` file.

A user is created with the username "arthur" and password "admin"

### Running the Application

After making sure your database is set up and populated, you can run the main java file

### Observations

The entire flashcards and decks module is ready for use, with the implementation of the study cycle and sessions module still required.
