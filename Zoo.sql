PGDMP         '                {            Zoo    15.3    15.3     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16398    Zoo    DATABASE     |   CREATE DATABASE "Zoo" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Spanish_Guatemala.1252';
    DROP DATABASE "Zoo";
                postgres    false            �            1259    16438    infozoo    TABLE     �   CREATE TABLE public.infozoo (
    codigo integer NOT NULL,
    nombre character varying(75),
    tipo character varying(50),
    precio integer NOT NULL,
    validez character varying(10) NOT NULL
);
    DROP TABLE public.infozoo;
       public         heap    postgres    false            �            1259    16437    infozoo_codigo_seq    SEQUENCE     �   CREATE SEQUENCE public.infozoo_codigo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.infozoo_codigo_seq;
       public          postgres    false    215            �           0    0    infozoo_codigo_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.infozoo_codigo_seq OWNED BY public.infozoo.codigo;
          public          postgres    false    214            e           2604    16441    infozoo codigo    DEFAULT     p   ALTER TABLE ONLY public.infozoo ALTER COLUMN codigo SET DEFAULT nextval('public.infozoo_codigo_seq'::regclass);
 =   ALTER TABLE public.infozoo ALTER COLUMN codigo DROP DEFAULT;
       public          postgres    false    215    214    215            �          0    16438    infozoo 
   TABLE DATA           H   COPY public.infozoo (codigo, nombre, tipo, precio, validez) FROM stdin;
    public          postgres    false    215   �
       �           0    0    infozoo_codigo_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.infozoo_codigo_seq', 8, true);
          public          postgres    false    214            g           2606    16443    infozoo infozoo_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.infozoo
    ADD CONSTRAINT infozoo_pkey PRIMARY KEY (codigo);
 >   ALTER TABLE ONLY public.infozoo DROP CONSTRAINT infozoo_pkey;
       public            postgres    false    215            �   �   x�}�1
�0����E����EP,����J#FbSS�q<����,"M\\��>��gPګl�.�DCYB;a�n�B�$p�J6�dP���m�gC��X+=]#nH�4`�Y{4����=���F���gwm��)T[�О����+8�E[�:T4ʂPȡ�։����l*�a�yAy��n{     