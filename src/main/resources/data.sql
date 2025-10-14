-- CATEGORY
INSERT INTO category (id, name, category_code, created_at, modified_at, deleted_at, category_id)
VALUES (1, 'Categoria Esempio', 'CAT001', CURRENT_TIMESTAMP, NULL, NULL, NULL);

-- TAG
INSERT INTO tag (id, name, tag_code, created_at, modified_at, deleted_at, category_id)
VALUES (1, 'Tag Test', 'TAG001', CURRENT_TIMESTAMP, NULL, NULL, 1);

-- IMAGE
INSERT INTO image (id, content, description, image_code, created_at, modified_at, deleted_at, name)
VALUES (1, "sdas", 'Descrizione immagine', 'IMG001', CURRENT_TIMESTAMP, NULL, NULL, 'immagine1');

-- EMPLOYEE
INSERT INTO employee (id, employee_code, created_at, modified_at, deleted_at, name, surname, email, password, username)
VALUES (1, 'EMP001', CURRENT_TIMESTAMP, NULL, NULL, 'Nome', 'Cognome', 'email@test.com', 'pass', 'username');

-- JOURNALIST
INSERT INTO journalist (journalist_code, name, surname, email, username, password, created_at, modified_at, deleted_at)
VALUES ('JRN001', 'Giornalista', 'Test', 'giornalista@test.com', 'giornalista1', 'pass', CURRENT_TIMESTAMP, NULL, NULL);

-- CONTEST
INSERT INTO contest (id, name, description, contest_code, created_at, modified_at, deleted_at, image_id)
VALUES (1, 'Concorso Test', 'Descrizione Test', 'CON001', CURRENT_TIMESTAMP, NULL, NULL, 1);

-- PRIZE_ARTICLE
INSERT INTO prize_article (id, name, description, prize_article_code, created_at, modified_at, deleted_at)
VALUES (1, 'Premio Test', 'Descrizione premio', 'PA001', CURRENT_TIMESTAMP, NULL, NULL);

-- NEWS
INSERT INTO news (id, news_code, publication_date, positive_vote, negative_vote, category_id, created_at, modified_at, deleted_at, image_id, title, summary, type, journalist_code)
VALUES (1, 'NEWS001', CURRENT_TIMESTAMP, 0, 0, 1, CURRENT_TIMESTAMP, NULL, NULL, 1, 'Titolo Test', 'Sommario Test', 'news', 'JRN001');

-- NEWS_TEXT
INSERT INTO news_text (id, text, order_num, news_text_code, news_id, type, image_id, created_at, modified_at, deleted_at)
VALUES (1, 'Paragrafo Test', 1, 'NTXT001', 1, 'T', NULL, CURRENT_TIMESTAMP, NULL, NULL);

-- NEWS_TAG
INSERT INTO news_tag (id, news_id, tag_id, created_at, news_tag_code)
VALUES (1, 1, 1, CURRENT_TIMESTAMP, 'NT001');

-- REVIEW
INSERT INTO review (id, review_code, category_id, vote, image_id, title, summary, journalist_code, positive_vote, negative_vote)
VALUES (1, 'REV001', 1, 0, NULL, 'Titolo Review', 'Sommario Review', 'JRN001', 0, 0);

-- REVIEW_TEXT
INSERT INTO review_text (id, text, type, order_num, review_text_code, review_id, image_id)
VALUES (1, 'Paragrafo Review', 'T', 1, 'RT001', 1, NULL);

-- COMMENT
INSERT INTO comment (id, text, user_id, news_id, review_id, created_at, comment_id)
VALUES (1, 'Test commento', 1, NULL, NULL, CURRENT_TIMESTAMP, NULL);

-- USER
INSERT INTO "user" (id, user_code, created_at, modified_at, deleted_at, name, surname, username, password, email)
VALUES (1, 'USR001', CURRENT_TIMESTAMP, NULL, NULL, 'NomeUser', 'CognomeUser', 'user1', 'pass', 'user@test.com');

-- USER_TAG
INSERT INTO user_tag (id, user_id, tag_id)
VALUES (1, 1, 1);
