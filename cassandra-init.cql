CREATE KEYSPACE sentencesks
           WITH replication = {'class': 'SimpleStrategy', 'replication_factor' : 1};

USE sentencesks;

CREATE TABLE sentencesks.sentences (
    sentence text,
    PRIMARY KEY (sentence)
);

CREATE TABLE sentencesks.wordCount (
    word text,
    count int,
    PRIMARY KEY (word)
);