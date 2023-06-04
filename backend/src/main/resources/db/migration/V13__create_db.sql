CREATE TABLE public.application_user (
                                         id bigint NOT NULL,
                                         created_date timestamp(6) without time zone NOT NULL,
                                         email character varying(255) NOT NULL,
                                         enable boolean NOT NULL,
                                         keycloak_id uuid NOT NULL,
                                         modified_date timestamp(6) without time zone,
                                         username character varying(255)
);


ALTER TABLE public.application_user OWNER TO postgres;


CREATE SEQUENCE public.application_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.application_user_id_seq OWNER TO postgres;



ALTER SEQUENCE public.application_user_id_seq OWNED BY public.application_user.id;



CREATE TABLE public.athlete (
                                id bigint NOT NULL,
                                birth_date date,
                                cpf character varying(255),
                                created_date timestamp(6) without time zone NOT NULL,
                                first_name character varying(255) NOT NULL,
                                last_name character varying(255) NOT NULL,
                                modified_date timestamp(6) without time zone,
                                social_name character varying(255),
                                locale_id bigint,
                                user_id bigint
);


ALTER TABLE public.athlete OWNER TO postgres;



CREATE TABLE public.attendance (
                                   athlete_id bigint NOT NULL,
                                   booking_id bigint NOT NULL,
                                   attendance_confirmation character varying(255),
                                   created_by character varying(255),
                                   created_date timestamp(6) without time zone NOT NULL,
                                   modified_by character varying(255),
                                   modified_date timestamp(6) without time zone
);


ALTER TABLE public.attendance OWNER TO postgres;


CREATE TABLE public.body_measure (
                                     id bigint NOT NULL,
                                     biceps double precision,
                                     calf double precision,
                                     chest double precision,
                                     created_by character varying(255),
                                     created_date timestamp(6) without time zone NOT NULL,
                                     forearm double precision,
                                     height double precision,
                                     modified_by character varying(255),
                                     modified_date timestamp(6) without time zone,
                                     thigh double precision,
                                     weight double precision,
                                     athlete_id bigint
);


ALTER TABLE public.body_measure OWNER TO postgres;



CREATE SEQUENCE public.body_measure_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.body_measure_id_seq OWNER TO postgres;



ALTER SEQUENCE public.body_measure_id_seq OWNED BY public.body_measure.id;



CREATE TABLE public.booking (
                                id bigint NOT NULL,
                                application_state character varying(255) NOT NULL,
                                checkin_booking time without time zone NOT NULL,
                                checkout_booking time without time zone NOT NULL,
                                created_by character varying(255),
                                created_date timestamp(6) without time zone NOT NULL,
                                day smallint NOT NULL,
                                modified_by character varying(255),
                                modified_date timestamp(6) without time zone,
                                community_id bigint NOT NULL,
                                gym_id bigint NOT NULL
);


ALTER TABLE public.booking OWNER TO postgres;


CREATE SEQUENCE public.booking_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.booking_id_seq OWNER TO postgres;

ALTER SEQUENCE public.booking_id_seq OWNED BY public.booking.id;



CREATE TABLE public.community (
                                  id bigint NOT NULL,
                                  community_rules text,
                                  created_by character varying(255),
                                  created_date timestamp(6) without time zone NOT NULL,
                                  description text NOT NULL,
                                  modified_by character varying(255),
                                  modified_date timestamp(6) without time zone,
                                  name character varying(255) NOT NULL,
                                  community_image_id bigint
);


ALTER TABLE public.community OWNER TO postgres;

CREATE SEQUENCE public.community_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.community_id_seq OWNER TO postgres;



ALTER SEQUENCE public.community_id_seq OWNED BY public.community.id;




CREATE TABLE public.gym (
                            id bigint NOT NULL,
                            created_by character varying(255),
                            created_date timestamp(6) without time zone NOT NULL,
                            modified_by character varying(255),
                            modified_date timestamp(6) without time zone,
                            name character varying(255) NOT NULL,
                            rules text,
                            short_description character varying(150) NOT NULL,
                            locale_id bigint,
                            manager_id bigint
);


ALTER TABLE public.gym OWNER TO postgres;


CREATE SEQUENCE public.gym_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.gym_id_seq OWNER TO postgres;


ALTER SEQUENCE public.gym_id_seq OWNED BY public.gym.id;


CREATE TABLE public.hibernate_sequences (
                                            sequence_name character varying(255) NOT NULL,
                                            next_val bigint
);


ALTER TABLE public.hibernate_sequences OWNER TO postgres;


CREATE TABLE public.image (
                              id bigint NOT NULL,
                              imagedata oid,
                              name character varying(255),
                              type character varying(255)
);


ALTER TABLE public.image OWNER TO postgres;


CREATE SEQUENCE public.image_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.image_id_seq OWNER TO postgres;



ALTER SEQUENCE public.image_id_seq OWNED BY public.image.id;


CREATE TABLE public.locale (
                               id bigint NOT NULL,
                               city character varying(255) NOT NULL,
                               complement character varying(255),
                               created_by character varying(255),
                               created_date timestamp(6) without time zone,
                               modified_by character varying(255),
                               modified_date timestamp(6) without time zone,
                               number character varying(255) NOT NULL,
                               state character varying(255) NOT NULL,
                               street character varying(255) NOT NULL,
                               zip_code character varying(255) NOT NULL
);


ALTER TABLE public.locale OWNER TO postgres;


CREATE SEQUENCE public.locale_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.locale_id_seq OWNER TO postgres;


ALTER SEQUENCE public.locale_id_seq OWNED BY public.locale.id;

CREATE TABLE public.manager (
                                id bigint NOT NULL,
                                birth_date date,
                                cpf character varying(255),
                                created_date timestamp(6) without time zone NOT NULL,
                                first_name character varying(255) NOT NULL,
                                last_name character varying(255) NOT NULL,
                                modified_date timestamp(6) without time zone,
                                social_name character varying(255),
                                locale_id bigint,
                                user_id bigint
);


ALTER TABLE public.manager OWNER TO postgres;

CREATE TABLE public.membership (
                                   athlete_id bigint NOT NULL,
                                   community_id bigint NOT NULL,
                                   application_state character varying(255),
                                   community_profile character varying(255),
                                   created_by character varying(255),
                                   created_date timestamp(6) without time zone NOT NULL,
                                   modified_by character varying(255),
                                   modified_date timestamp(6) without time zone
);


ALTER TABLE public.membership OWNER TO postgres;

CREATE TABLE public.practicable (
                                    gym_id bigint NOT NULL,
                                    sport_id bigint NOT NULL,
                                    created_by character varying(255),
                                    created_date timestamp(6) without time zone NOT NULL,
                                    modified_by character varying(255),
                                    modified_date timestamp(6) without time zone,
                                    practicable_state character varying(255)
);


ALTER TABLE public.practicable OWNER TO postgres;

CREATE TABLE public.sport (
                              id bigint NOT NULL,
                              created_by character varying(255),
                              created_date timestamp(6) without time zone NOT NULL,
                              modified_by character varying(255),
                              modified_date timestamp(6) without time zone,
                              name character varying(255) NOT NULL
);


ALTER TABLE public.sport OWNER TO postgres;

CREATE SEQUENCE public.sport_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.sport_id_seq OWNER TO postgres;
ALTER SEQUENCE public.sport_id_seq OWNED BY public.sport.id;
ALTER TABLE ONLY public.application_user ALTER COLUMN id SET DEFAULT nextval('public.application_user_id_seq'::regclass);
ALTER TABLE ONLY public.body_measure ALTER COLUMN id SET DEFAULT nextval('public.body_measure_id_seq'::regclass);
ALTER TABLE ONLY public.booking ALTER COLUMN id SET DEFAULT nextval('public.booking_id_seq'::regclass);
ALTER TABLE ONLY public.community ALTER COLUMN id SET DEFAULT nextval('public.community_id_seq'::regclass);
ALTER TABLE ONLY public.gym ALTER COLUMN id SET DEFAULT nextval('public.gym_id_seq'::regclass);
ALTER TABLE ONLY public.image ALTER COLUMN id SET DEFAULT nextval('public.image_id_seq'::regclass);
ALTER TABLE ONLY public.locale ALTER COLUMN id SET DEFAULT nextval('public.locale_id_seq'::regclass);
ALTER TABLE ONLY public.sport ALTER COLUMN id SET DEFAULT nextval('public.sport_id_seq'::regclass);

SELECT pg_catalog.setval('public.application_user_id_seq', 2, true);
SELECT pg_catalog.setval('public.body_measure_id_seq', 1, false);
SELECT pg_catalog.setval('public.booking_id_seq', 1, false);
SELECT pg_catalog.setval('public.community_id_seq', 3, true);
SELECT pg_catalog.setval('public.gym_id_seq', 1, false);
SELECT pg_catalog.setval('public.image_id_seq', 3, true);
SELECT pg_catalog.setval('public.locale_id_seq', 22, true);
SELECT pg_catalog.setval('public.sport_id_seq', 1, false);

ALTER TABLE ONLY public.application_user
    ADD CONSTRAINT application_user_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.athlete
    ADD CONSTRAINT athlete_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.attendance
    ADD CONSTRAINT attendance_pkey PRIMARY KEY (athlete_id, booking_id);

ALTER TABLE ONLY public.body_measure
    ADD CONSTRAINT body_measure_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.booking
    ADD CONSTRAINT booking_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.community
    ADD CONSTRAINT community_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.gym
    ADD CONSTRAINT gym_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.hibernate_sequences
    ADD CONSTRAINT hibernate_sequences_pkey PRIMARY KEY (sequence_name);

ALTER TABLE ONLY public.image
    ADD CONSTRAINT image_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.locale
    ADD CONSTRAINT locale_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.manager
    ADD CONSTRAINT manager_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.membership
    ADD CONSTRAINT membership_pkey PRIMARY KEY (athlete_id, community_id);

ALTER TABLE ONLY public.practicable
    ADD CONSTRAINT practicable_pkey PRIMARY KEY (gym_id, sport_id);

ALTER TABLE ONLY public.sport
    ADD CONSTRAINT sport_pkey PRIMARY KEY (id);


ALTER TABLE ONLY public.body_measure
    ADD CONSTRAINT uk_13jhht8vtac2jeyw091lm5jlw UNIQUE (created_by);

ALTER TABLE ONLY public.athlete
    ADD CONSTRAINT uk_3c792hhw22kj9ciyss2kl29px UNIQUE (user_id);

ALTER TABLE ONLY public.attendance
    ADD CONSTRAINT uk_4qaubs99wum51kgnwugxn8p8w UNIQUE (created_by);

ALTER TABLE ONLY public.manager
    ADD CONSTRAINT uk_4vbgsjl6mcxrqyvts0hlilhob UNIQUE (user_id);

ALTER TABLE ONLY public.application_user
    ADD CONSTRAINT uk_5xlxc5x36ssrwdq0vcnntbs9k UNIQUE (keycloak_id);

ALTER TABLE ONLY public.gym
    ADD CONSTRAINT uk_69p52vagdbot2hbyhpnnhfser UNIQUE (locale_id);

ALTER TABLE ONLY public.application_user
    ADD CONSTRAINT uk_6c0v0rco93sykgyetukfmkkod UNIQUE (username);

ALTER TABLE ONLY public.manager
    ADD CONSTRAINT uk_8r69rgtkks0yq2mfcl4d2iiw5 UNIQUE (locale_id);

ALTER TABLE ONLY public.community
    ADD CONSTRAINT uk_ggi0mfnbrejia9lxku7voffc9 UNIQUE (name);

ALTER TABLE ONLY public.athlete
    ADD CONSTRAINT uk_ja42ea6oy9kbgcv22vua3aiiu UNIQUE (locale_id);

ALTER TABLE ONLY public.membership
    ADD CONSTRAINT fk4a4o7fx3xersm0wlav1d0hmqm FOREIGN KEY (community_id) REFERENCES public.community(id);

ALTER TABLE ONLY public.practicable
    ADD CONSTRAINT fk4astekm158gf8ypsil5uup6pc FOREIGN KEY (gym_id) REFERENCES public.gym(id);

ALTER TABLE ONLY public.body_measure
    ADD CONSTRAINT fk61ktfjmuev86ic1h6rdfgu3l FOREIGN KEY (athlete_id) REFERENCES public.athlete(id);

ALTER TABLE ONLY public.attendance
    ADD CONSTRAINT fk6koc5m5mxtx6nrqn9vp14kntd FOREIGN KEY (athlete_id) REFERENCES public.athlete(id);

ALTER TABLE ONLY public.membership
    ADD CONSTRAINT fk7vs0dkftdx1b7u9i5lbmsjup9 FOREIGN KEY (athlete_id) REFERENCES public.athlete(id);

ALTER TABLE ONLY public.practicable
    ADD CONSTRAINT fk96den0luls7wcaiy8sytl84q0 FOREIGN KEY (sport_id) REFERENCES public.sport(id);

ALTER TABLE ONLY public.athlete
    ADD CONSTRAINT fk_3c792hhw22kj9ciyss2kl29px FOREIGN KEY (user_id) REFERENCES public.application_user(id);
ALTER TABLE ONLY public.manager
    ADD CONSTRAINT fk_4vbgsjl6mcxrqyvts0hlilhob FOREIGN KEY (user_id) REFERENCES public.application_user(id);

ALTER TABLE ONLY public.manager
    ADD CONSTRAINT fk_8r69rgtkks0yq2mfcl4d2iiw5 FOREIGN KEY (locale_id) REFERENCES public.locale(id);

ALTER TABLE ONLY public.athlete
    ADD CONSTRAINT fk_ja42ea6oy9kbgcv22vua3aiiu FOREIGN KEY (locale_id) REFERENCES public.locale(id);

ALTER TABLE ONLY public.community
    ADD CONSTRAINT fkg0dtoy3ipvenr3mry0s2yyhis FOREIGN KEY (community_image_id) REFERENCES public.image(id);

ALTER TABLE ONLY public.gym
    ADD CONSTRAINT fkg4lg20qy3077exloole8259gu FOREIGN KEY (locale_id) REFERENCES public.locale(id);

ALTER TABLE ONLY public.booking
    ADD CONSTRAINT fki81a3k5iauq0jb3fq7st2rchd FOREIGN KEY (community_id) REFERENCES public.community(id);

ALTER TABLE ONLY public.booking
    ADD CONSTRAINT fkkyc9nv9emu6sd6efl13g3r9u2 FOREIGN KEY (gym_id) REFERENCES public.gym(id);

ALTER TABLE ONLY public.attendance
    ADD CONSTRAINT fkmyobp8qdqecw7voxp72qjeqo7 FOREIGN KEY (booking_id) REFERENCES public.booking(id);

ALTER TABLE ONLY public.gym
    ADD CONSTRAINT fkp4v3qnttb6tgvmu319obi41h2 FOREIGN KEY (manager_id) REFERENCES public.manager(id);