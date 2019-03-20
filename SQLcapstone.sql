START TRANSACTION;
INSERT INTO campground (campground_id, park_id, name, open_from_mm, open_to_mm, daily_fee) VALUES (DEFAULT, 1, 'Flower', 03, 08, 30) RETURNING campground_id;

ROLLBACK;
SELECT * FROM CAMPGROUND;

INSERT INTO campground (campground_id, park_id, name, open_from_mm, open_to_mm, daily_fee) VALUES (DEFAULT, ?, ?, ?, ?, ?) RETURNING campground_id


Select distinct * from site
join campground on site.campground_id = campground.campground_id
where site.campground_id = ?
and site_id not in
(select site.site_id from site
join reservation on reservation.site_id = site.site_id
-- subselect Param 1 & 2: new From Date
-- subselect Param 3 & 4: new To Date
-- Example: ('2018-10-31' > reservation.from_date AND '2018-10-31' < reservation.to_date) OR ('2018-11-10' > reservation.from_date AND '2018-11-10' < reservation.to_date)
where (? > reservation.from_date AND ? < reservation.to_date) OR (? > reservation.from_date AND ? < reservation.to_date)) 
order by daily_fee
LIMIT 5;

start transaction;
INSERT INTO reservation (reservation_id, site_id, name, from_date, to_date, create_date)
VALUES (DEFAULT, ?, ?, ?, ?, current_date)
rollback;

INSERT INTO reservation (reservation_id, site_id, name, from_date, to_date, create_date)
VALUES (DEFAULT, 1, 'Smith', '03/01/2019', '03/05/2019', current_date)

SELECT * FROM reservation


