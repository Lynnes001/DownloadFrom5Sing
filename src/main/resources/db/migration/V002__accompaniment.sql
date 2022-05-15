CREATE TABLE accompaniment_binary_archive (
	id UUID,
	accompaniment_binary bytea NOT NULL,
	PRIMARY KEY (id)
);


CREATE TABLE accompaniment (
    id VARCHAR(16) NOT NULL,
    accompaniment_binary_id UUID NOT NULL,
    search_keyword TEXT UNIQUE NOT NULL,
    song_name VARCHAR(128) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE accompaniment_search_history (
    id UUID,
    search_keyword TEXT,
    accompaniment_id VARCHAR(16),
    created_at timestamp default current_timestamp
);

