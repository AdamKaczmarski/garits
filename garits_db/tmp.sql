update
    payments
set
    amount = ROUND(
        ROUND(
            amount + (
                SELECT
                    ROUND(
                        j.act_time_min *(
                            select
                                ROUND(hourly_rate / 60, 2)
                            from
                                roles
                            where
                                id_role = (
                                    select
                                        role_id
                                    from
                                        users_roles
                                    where
                                        user_id = (
                                            SELECT
                                                user_id
                                            from
                                                users_jobs
                                            where
                                                job_id = 3
                                        )
                                )
                        ),
                        2
                    )
                from
                    jobs j
                where
                    id_job = 3
            ),
            2
        ) * 1.2,
        2
    )
where
    id_payment = (
        SELECT
            payment_id
        from
            jobs_payments
        where
            job_id = 3
    )