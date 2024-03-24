-- Цей SQL-запит поверне працівників з найвищою заробітною платою
SELECT *
FROM worker
WHERE SALARY = (
    SELECT MAX(SALARY)
    FROM worker
);