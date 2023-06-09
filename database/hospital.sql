PGDMP                         {            hospital #   14.7 (Ubuntu 14.7-0ubuntu0.22.04.1) #   14.7 (Ubuntu 14.7-0ubuntu0.22.04.1) ;    [           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            \           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            ]           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            ^           1262    22459    hospital    DATABASE     ]   CREATE DATABASE hospital WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'en_US.UTF-8';
    DROP DATABASE hospital;
                postgres    false            �            1259    22755    appointments    TABLE     �   CREATE TABLE public.appointments (
    id integer NOT NULL,
    patient_id integer,
    schedule_id integer,
    description character varying(255)
);
     DROP TABLE public.appointments;
       public         heap    postgres    false            �            1259    22754    appointments_id_seq    SEQUENCE     �   CREATE SEQUENCE public.appointments_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.appointments_id_seq;
       public          postgres    false    222            _           0    0    appointments_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.appointments_id_seq OWNED BY public.appointments.id;
          public          postgres    false    221            �            1259    22535    doctors    TABLE     '  CREATE TABLE public.doctors (
    id integer NOT NULL,
    name character varying(255) NOT NULL,
    gender character varying(10) NOT NULL,
    birthdate date NOT NULL,
    phone character varying(20) NOT NULL,
    email character varying(255),
    speciality character varying(255) NOT NULL
);
    DROP TABLE public.doctors;
       public         heap    postgres    false            �            1259    22534    doctors_id_seq    SEQUENCE     �   CREATE SEQUENCE public.doctors_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.doctors_id_seq;
       public          postgres    false    214            `           0    0    doctors_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.doctors_id_seq OWNED BY public.doctors.id;
          public          postgres    false    213            �            1259    22511    medical_records    TABLE       CREATE TABLE public.medical_records (
    id integer NOT NULL,
    patient_id integer NOT NULL,
    date date NOT NULL,
    complaint character varying(255) NOT NULL,
    diagnosis character varying(255) NOT NULL,
    treatment character varying(255) NOT NULL
);
 #   DROP TABLE public.medical_records;
       public         heap    postgres    false            �            1259    22510    medical_records_id_seq    SEQUENCE     �   CREATE SEQUENCE public.medical_records_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.medical_records_id_seq;
       public          postgres    false    212            a           0    0    medical_records_id_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.medical_records_id_seq OWNED BY public.medical_records.id;
          public          postgres    false    211            �            1259    22624    medicine    TABLE     �   CREATE TABLE public.medicine (
    id integer NOT NULL,
    name character varying(255) NOT NULL,
    description character varying(255)
);
    DROP TABLE public.medicine;
       public         heap    postgres    false            �            1259    22623    medicine_id_seq    SEQUENCE     �   CREATE SEQUENCE public.medicine_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.medicine_id_seq;
       public          postgres    false    216            b           0    0    medicine_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.medicine_id_seq OWNED BY public.medicine.id;
          public          postgres    false    215            �            1259    22692    medicine_medical_records    TABLE     �   CREATE TABLE public.medicine_medical_records (
    id integer NOT NULL,
    medicine_id integer NOT NULL,
    medical_record_id integer NOT NULL,
    dosage character varying(255)
);
 ,   DROP TABLE public.medicine_medical_records;
       public         heap    postgres    false            �            1259    22691    medicine_medical_records_id_seq    SEQUENCE     �   CREATE SEQUENCE public.medicine_medical_records_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 6   DROP SEQUENCE public.medicine_medical_records_id_seq;
       public          postgres    false    218            c           0    0    medicine_medical_records_id_seq    SEQUENCE OWNED BY     c   ALTER SEQUENCE public.medicine_medical_records_id_seq OWNED BY public.medicine_medical_records.id;
          public          postgres    false    217            �            1259    22502    patients    TABLE     -  CREATE TABLE public.patients (
    id integer NOT NULL,
    name character varying(255) NOT NULL,
    gender character varying(25) NOT NULL,
    birthdate date NOT NULL,
    address character varying(255) NOT NULL,
    phone character varying(20) NOT NULL,
    email character varying(25) NOT NULL
);
    DROP TABLE public.patients;
       public         heap    postgres    false            �            1259    22501    patients_id_seq    SEQUENCE     �   CREATE SEQUENCE public.patients_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.patients_id_seq;
       public          postgres    false    210            d           0    0    patients_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.patients_id_seq OWNED BY public.patients.id;
          public          postgres    false    209            �            1259    22709    schedule    TABLE     y   CREATE TABLE public.schedule (
    id integer NOT NULL,
    id_doctor integer,
    day character varying(10) NOT NULL
);
    DROP TABLE public.schedule;
       public         heap    postgres    false            �            1259    22708    schedule_id_seq    SEQUENCE     �   CREATE SEQUENCE public.schedule_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.schedule_id_seq;
       public          postgres    false    220            e           0    0    schedule_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.schedule_id_seq OWNED BY public.schedule.id;
          public          postgres    false    219            �           2604    22758    appointments id    DEFAULT     r   ALTER TABLE ONLY public.appointments ALTER COLUMN id SET DEFAULT nextval('public.appointments_id_seq'::regclass);
 >   ALTER TABLE public.appointments ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    221    222    222            �           2604    22538 
   doctors id    DEFAULT     h   ALTER TABLE ONLY public.doctors ALTER COLUMN id SET DEFAULT nextval('public.doctors_id_seq'::regclass);
 9   ALTER TABLE public.doctors ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    214    213    214            �           2604    22514    medical_records id    DEFAULT     x   ALTER TABLE ONLY public.medical_records ALTER COLUMN id SET DEFAULT nextval('public.medical_records_id_seq'::regclass);
 A   ALTER TABLE public.medical_records ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    212    211    212            �           2604    22627    medicine id    DEFAULT     j   ALTER TABLE ONLY public.medicine ALTER COLUMN id SET DEFAULT nextval('public.medicine_id_seq'::regclass);
 :   ALTER TABLE public.medicine ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    215    216    216            �           2604    22695    medicine_medical_records id    DEFAULT     �   ALTER TABLE ONLY public.medicine_medical_records ALTER COLUMN id SET DEFAULT nextval('public.medicine_medical_records_id_seq'::regclass);
 J   ALTER TABLE public.medicine_medical_records ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    218    217    218            �           2604    22505    patients id    DEFAULT     j   ALTER TABLE ONLY public.patients ALTER COLUMN id SET DEFAULT nextval('public.patients_id_seq'::regclass);
 :   ALTER TABLE public.patients ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    210    209    210            �           2604    22712    schedule id    DEFAULT     j   ALTER TABLE ONLY public.schedule ALTER COLUMN id SET DEFAULT nextval('public.schedule_id_seq'::regclass);
 :   ALTER TABLE public.schedule ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    220    219    220            X          0    22755    appointments 
   TABLE DATA           P   COPY public.appointments (id, patient_id, schedule_id, description) FROM stdin;
    public          postgres    false    222   �D       P          0    22535    doctors 
   TABLE DATA           X   COPY public.doctors (id, name, gender, birthdate, phone, email, speciality) FROM stdin;
    public          postgres    false    214   (E       N          0    22511    medical_records 
   TABLE DATA           `   COPY public.medical_records (id, patient_id, date, complaint, diagnosis, treatment) FROM stdin;
    public          postgres    false    212   yE       R          0    22624    medicine 
   TABLE DATA           9   COPY public.medicine (id, name, description) FROM stdin;
    public          postgres    false    216   �E       T          0    22692    medicine_medical_records 
   TABLE DATA           ^   COPY public.medicine_medical_records (id, medicine_id, medical_record_id, dosage) FROM stdin;
    public          postgres    false    218   F       L          0    22502    patients 
   TABLE DATA           V   COPY public.patients (id, name, gender, birthdate, address, phone, email) FROM stdin;
    public          postgres    false    210   )F       V          0    22709    schedule 
   TABLE DATA           6   COPY public.schedule (id, id_doctor, day) FROM stdin;
    public          postgres    false    220   {F       f           0    0    appointments_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.appointments_id_seq', 4, true);
          public          postgres    false    221            g           0    0    doctors_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.doctors_id_seq', 8, true);
          public          postgres    false    213            h           0    0    medical_records_id_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.medical_records_id_seq', 2, true);
          public          postgres    false    211            i           0    0    medicine_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.medicine_id_seq', 3, true);
          public          postgres    false    215            j           0    0    medicine_medical_records_id_seq    SEQUENCE SET     M   SELECT pg_catalog.setval('public.medicine_medical_records_id_seq', 2, true);
          public          postgres    false    217            k           0    0    patients_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.patients_id_seq', 1, true);
          public          postgres    false    209            l           0    0    schedule_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.schedule_id_seq', 3, true);
          public          postgres    false    219            �           2606    22760    appointments appointments_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.appointments
    ADD CONSTRAINT appointments_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.appointments DROP CONSTRAINT appointments_pkey;
       public            postgres    false    222            �           2606    22542    doctors doctors_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.doctors
    ADD CONSTRAINT doctors_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.doctors DROP CONSTRAINT doctors_pkey;
       public            postgres    false    214            �           2606    22518 $   medical_records medical_records_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.medical_records
    ADD CONSTRAINT medical_records_pkey PRIMARY KEY (id);
 N   ALTER TABLE ONLY public.medical_records DROP CONSTRAINT medical_records_pkey;
       public            postgres    false    212            �           2606    22697 6   medicine_medical_records medicine_medical_records_pkey 
   CONSTRAINT     t   ALTER TABLE ONLY public.medicine_medical_records
    ADD CONSTRAINT medicine_medical_records_pkey PRIMARY KEY (id);
 `   ALTER TABLE ONLY public.medicine_medical_records DROP CONSTRAINT medicine_medical_records_pkey;
       public            postgres    false    218            �           2606    22631    medicine medicine_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.medicine
    ADD CONSTRAINT medicine_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.medicine DROP CONSTRAINT medicine_pkey;
       public            postgres    false    216            �           2606    22509    patients patients_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.patients
    ADD CONSTRAINT patients_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.patients DROP CONSTRAINT patients_pkey;
       public            postgres    false    210            �           2606    22714    schedule schedule_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.schedule
    ADD CONSTRAINT schedule_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.schedule DROP CONSTRAINT schedule_pkey;
       public            postgres    false    220            �           2606    22761 )   appointments appointments_patient_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.appointments
    ADD CONSTRAINT appointments_patient_id_fkey FOREIGN KEY (patient_id) REFERENCES public.patients(id);
 S   ALTER TABLE ONLY public.appointments DROP CONSTRAINT appointments_patient_id_fkey;
       public          postgres    false    3245    210    222            �           2606    22766 *   appointments appointments_schedule_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.appointments
    ADD CONSTRAINT appointments_schedule_id_fkey FOREIGN KEY (schedule_id) REFERENCES public.schedule(id);
 T   ALTER TABLE ONLY public.appointments DROP CONSTRAINT appointments_schedule_id_fkey;
       public          postgres    false    3255    220    222            �           2606    22703 +   medicine_medical_records fk_medical_records    FK CONSTRAINT     �   ALTER TABLE ONLY public.medicine_medical_records
    ADD CONSTRAINT fk_medical_records FOREIGN KEY (medical_record_id) REFERENCES public.medical_records(id);
 U   ALTER TABLE ONLY public.medicine_medical_records DROP CONSTRAINT fk_medical_records;
       public          postgres    false    3247    212    218            �           2606    22698 $   medicine_medical_records fk_medicine    FK CONSTRAINT     �   ALTER TABLE ONLY public.medicine_medical_records
    ADD CONSTRAINT fk_medicine FOREIGN KEY (medicine_id) REFERENCES public.medicine(id);
 N   ALTER TABLE ONLY public.medicine_medical_records DROP CONSTRAINT fk_medicine;
       public          postgres    false    216    218    3251            �           2606    22519 /   medical_records medical_records_patient_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.medical_records
    ADD CONSTRAINT medical_records_patient_id_fkey FOREIGN KEY (patient_id) REFERENCES public.patients(id);
 Y   ALTER TABLE ONLY public.medical_records DROP CONSTRAINT medical_records_patient_id_fkey;
       public          postgres    false    3245    210    212            �           2606    22715     schedule schedule_id_doctor_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.schedule
    ADD CONSTRAINT schedule_id_doctor_fkey FOREIGN KEY (id_doctor) REFERENCES public.doctors(id);
 J   ALTER TABLE ONLY public.schedule DROP CONSTRAINT schedule_id_doctor_fkey;
       public          postgres    false    3249    214    220            X      x�3�4�4�H-��.N����� *�4      P   A   x����H�K)���I����������@�i �`5鹉�9z����^�y%�y�\1z\\\ �a      N   E   x�3�4�4202�50�54�(-��K�Q�-M���,�(-��,.�,J�H,Q�L�KWH.�.-������ ��      R   $   x�3�H,JLN-I�����OJ,Q�������� �G      T      x�3�4�4�,�O����� Y�      L   B   x�3�N�)�L�H-J�-(M��4��4�54�54��KO,��4��b�R��������\�=... �U�      V      x�3���*�M,����� �1     