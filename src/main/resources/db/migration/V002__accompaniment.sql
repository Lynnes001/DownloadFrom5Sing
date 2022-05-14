CREATE TABLE accompaniment_binary_archive (
	id UUID,
	accompaniment_binary bytea NOT NULL,
	PRIMARY KEY (id)
);


CREATE TABLE accompaniment (
    id UUID NOT NULL,
    accompaniment_binary_id UUID NOT NULL,
    search_keyword TEXT UNIQUE NOT NULL,
    song_name VARCHAR(128) NOT NULL,
    download_url VARCHAR(128),
    PRIMARY KEY (id)
);

CREATE TABLE accompaniment_search_history (
    search_keyword TEXT,
    song_name VARCHAR(16),
    song_url TEXT,
    success boolean,
    created_at timestamp default current_timestamp
);

