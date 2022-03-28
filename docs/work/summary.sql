SELECT u.user_id, r.correct, COUNT(*) AS count
FROM response AS r
         JOIN card AS c ON c.card_id = r.card_id
         JOIN user AS u ON u.user_id = c.user_id
GROUP BY u.user_id, r.correct;
