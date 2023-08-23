CREATE TABLE public.body_measure (
                                     id bigserial NOT NULL,
                                     biceps float8 NULL,
                                     calf float8 NULL,
                                     chest float8 NULL,
                                     created_by varchar(255) NULL,
                                     created_date timestamp(6) NOT NULL,
                                     forearm float8 NULL,
                                     height float8 NULL,
                                     modified_by varchar(255) NULL,
                                     modified_date timestamp(6) NULL,
                                     thigh float8 NULL,
                                     weight float8 NULL,
                                     athlete_id int8 NULL,
                                     CONSTRAINT body_measure_pkey PRIMARY KEY (id),
                                     CONSTRAINT fk_attendance_athlete UNIQUE (created_by)
);


-- public.body_measure foreign keys

ALTER TABLE public.body_measure ADD CONSTRAINT fk_attendance_athlete FOREIGN KEY (athlete_id) REFERENCES public.athlete(id);