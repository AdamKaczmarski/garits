-- 1st bullet point
-- number of vehicles booked in on a monthly basis, overall and per job type (MoT/Annual service/repair), and
-- type of customer (casual or Account Holder)




-- 2nd bullet point
-- average time, and price, per job type (i.e. MoT/Annual service/repair). This should be done overall, and per
-- job type (MoT, annual service, repair, etc.), and / or given mechanic
-- Overall
SELECT ROUND(AVG(j.act_time_min),2) AS 'Average job time', ROUND(AVG(p.amount),2) AS 'Average price'
FROM jobs j 
INNER JOIN jobs_payments jp ON jp.job_id=j.id_job
INNER JOIN payments p on p.id_payment = jp.payment_id ;
-- Per Job Type
SELECT s.service_name AS 'Job type',ROUND(AVG(j.act_time_min),2) AS 'Average job time', ROUND(AVG(p.amount),2) AS 'Average price'
FROM jobs j 
INNER JOIN jobs_payments jp ON jp.job_id=j.id_job
INNER JOIN payments p on p.id_payment = jp.payment_id
INNER JOIN jobs_services js ON j.id_job = js.job_id
INNER JOIN services s ON s.id_service=js.service_id
GROUP BY s.service_name;
-- Per mechanic
SELECT s.service_name AS 'Job type',CONCAT(u.first_name,' ',u.last_name) AS 'Mechanic',ROUND(AVG(j.act_time_min),2) AS 'Average job time', ROUND(AVG(p.amount),2) AS 'Average price'
FROM jobs j 
INNER JOIN jobs_payments jp ON jp.job_id=j.id_job
INNER JOIN payments p on p.id_payment = jp.payment_id
INNER JOIN jobs_services js ON j.id_job = js.job_id
INNER JOIN services s ON s.id_service=js.service_id
INNER JOIN users_jobs uj ON uj.job_id = j.id_job
INNER JOIN users u ON u.id_user = uj.user_id
GROUP BY s.service_name, CONCAT(u.first_name,' ',u.last_name)
ORDER BY 2,1;

-- Per chosen mechanic
SELECT s.service_name AS 'Job type',CONCAT(u.first_name,' ',u.last_name) AS 'Mechanic',ROUND(AVG(j.act_time_min),2) AS 'Average job time', ROUND(AVG(p.amount),2) AS 'Average price'
FROM jobs j 
INNER JOIN jobs_payments jp ON jp.job_id=j.id_job
INNER JOIN payments p on p.id_payment = jp.payment_id
INNER JOIN jobs_services js ON j.id_job = js.job_id
INNER JOIN services s ON s.id_service=js.service_id
INNER JOIN users_jobs uj ON uj.job_id = j.id_job
INNER JOIN users u ON u.id_user = uj.user_id
WHERE first_name='Gavin' AND last_name='Ross'
GROUP BY s.service_name, CONCAT(u.first_name,' ',u.last_name)
ORDER BY 2,1;
