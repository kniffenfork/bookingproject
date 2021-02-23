CREATE TABLE IF NOT EXISTS rooms(
    id bigint PRIMARY KEY AUTO_INCREMENT,
    check_in DATE NOT NULL,
    check_out DATE NOT NULL,
    roomID bigint
)