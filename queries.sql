-- Part 1: Test it with SQL
--employer varchar(255), id int, name varchar(255), skills varchar(255)
-- Part 2: Test it with SQL
SELECT name FROM employer where location = "St. Louis City";
-- Part 3: Test it with SQL
DROP Table job;
-- Part 4: Test it with SQL
--SELECT distinct `name` FROM  skill inner Join job_skills on skill.id =job_skills.skills_id order by `name`;
SELECT * FROM  skill inner Join job_skills on skill.id = job_skills.skills_id where job_skills.jobs_id is not null order by name asc;