-- Цей SQL-запит виведе наймолодшого та найстаршого працівника
SELECT 'YOUNGEST' AS TYPE, NAME, BIRTHDAY
FROM worker
WHERE BIRTHDAY = (
    SELECT MIN(BIRTHDAY)
    FROM worker
)
UNION ALL
SELECT 'OLDEST' AS TYPE, NAME, BIRTHDAY
FROM worker
WHERE BIRTHDAY = (
    SELECT MAX(BIRTHDAY)
    FROM worker
);