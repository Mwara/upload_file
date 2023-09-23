--
-- PostgreSQL database dump
--

-- Dumped from database version 13.3
-- Dumped by pg_dump version 13.3

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: call_detail_records; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.call_detail_records (
    record_date timestamp without time zone NOT NULL,
    l_spc integer,
    l_ssn integer,
    l_ri integer,
    l_gt_i integer,
    l_gt_digits character varying(18),
    r_spc integer,
    r_ssn integer,
    r_ri integer,
    r_gt_i integer,
    r_gt_digits character varying(18),
    service_code character varying(50),
    or_nature integer,
    or_plan integer,
    or_digits character varying(18),
    de_nature integer,
    de_plan integer,
    de_digits character varying(18),
    isdn_nature integer,
    isdn_plan integer,
    msisdn character varying(18),
    vlr_nature integer,
    vlr_plan integer,
    vlr_digits character varying(18),
    imsi character varying(100),
    status character varying(30) NOT NULL,
    type character varying(30) NOT NULL,
    tstamp timestamp without time zone NOT NULL,
    local_dialog_id bigint,
    remote_dialog_id bigint,
    dialog_duration bigint,
    ussd_string character varying(255),
    id character varying(150) NOT NULL,
    file_id integer NOT NULL
);


ALTER TABLE public.call_detail_records OWNER TO postgres;

--
-- Name: upload_file_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.upload_file_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.upload_file_id_seq OWNER TO postgres;

--
-- Name: cdr_logs; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cdr_logs (
    id integer DEFAULT nextval('public.upload_file_id_seq'::regclass) NOT NULL,
    file_name text NOT NULL,
    upload_start_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    upload_finish_date timestamp without time zone,
    success_register_number integer,
    fail_register_number integer,
    upload_register_total character varying
);


ALTER TABLE public.cdr_logs OWNER TO postgres;

--
-- Name: cdr_logs cdr_logs_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cdr_logs
    ADD CONSTRAINT cdr_logs_pk PRIMARY KEY (id);


--
-- Name: call_detail_records call_detail_records_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.call_detail_records
    ADD CONSTRAINT call_detail_records_fk FOREIGN KEY (file_id) REFERENCES public.cdr_logs(id);


--
-- PostgreSQL database dump complete
--

