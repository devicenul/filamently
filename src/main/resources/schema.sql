CREATE TABLE IF NOT EXISTS filament (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    manufacturer VARCHAR(255),
    color VARCHAR(255),
    rgba VARCHAR(255),
    material INT,
    extruder_temp INT,
    bed_temp INT,
    bed_temp_first_layer INT,
    retraction FLOAT
);

CREATE TABLE IF NOT EXISTS inventory (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    filament_id BIGINT,
    purchase_date DATE,
    starting_weight FLOAT,
    used_weight FLOAT,
    price_per_kg FLOAT,
    FOREIGN KEY (filament_id) REFERENCES filament(id)
);
