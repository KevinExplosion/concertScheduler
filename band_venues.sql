--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: bands; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE bands (
    id integer NOT NULL,
    band character varying
);


ALTER TABLE bands OWNER TO "Guest";

--
-- Name: bands_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE bands_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE bands_id_seq OWNER TO "Guest";

--
-- Name: bands_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE bands_id_seq OWNED BY bands.id;


--
-- Name: bands_venues; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE bands_venues (
    id integer NOT NULL,
    band_id integer,
    venue_id integer
);


ALTER TABLE bands_venues OWNER TO "Guest";

--
-- Name: bands_venues_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE bands_venues_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE bands_venues_id_seq OWNER TO "Guest";

--
-- Name: bands_venues_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE bands_venues_id_seq OWNED BY bands_venues.id;


--
-- Name: venues; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE venues (
    id integer NOT NULL,
    venue character varying
);


ALTER TABLE venues OWNER TO "Guest";

--
-- Name: venues_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE venues_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE venues_id_seq OWNER TO "Guest";

--
-- Name: venues_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE venues_id_seq OWNED BY venues.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY bands ALTER COLUMN id SET DEFAULT nextval('bands_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY bands_venues ALTER COLUMN id SET DEFAULT nextval('bands_venues_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY venues ALTER COLUMN id SET DEFAULT nextval('venues_id_seq'::regclass);


--
-- Data for Name: bands; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY bands (id, band) FROM stdin;
7	Eastern Youth
8	DeadMAU5
9	Logic
10	Madeon
11	Mord Fustang
12	Grades
\.


--
-- Name: bands_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('bands_id_seq', 12, true);


--
-- Data for Name: bands_venues; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY bands_venues (id, band_id, venue_id) FROM stdin;
1	10	8
2	10	8
\.


--
-- Name: bands_venues_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('bands_venues_id_seq', 2, true);


--
-- Data for Name: venues; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY venues (id, venue) FROM stdin;
3	Backbooth
4	Hard Rock
5	Firestone
6	The Social
7	House of Blues
8	Amway Arena
9	Citrus Bowl
10	Wonder Ballroom
\.


--
-- Name: venues_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('venues_id_seq', 10, true);


--
-- Name: bands_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY bands
    ADD CONSTRAINT bands_pkey PRIMARY KEY (id);


--
-- Name: bands_venues_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY bands_venues
    ADD CONSTRAINT bands_venues_pkey PRIMARY KEY (id);


--
-- Name: venues_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY venues
    ADD CONSTRAINT venues_pkey PRIMARY KEY (id);


--
-- Name: public; Type: ACL; Schema: -; Owner: epicodus
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM epicodus;
GRANT ALL ON SCHEMA public TO epicodus;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

