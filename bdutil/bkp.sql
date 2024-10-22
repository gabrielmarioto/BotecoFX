PGDMP                     
    w           botecodb    10.9    10.10 X    S           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            T           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            U           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false            V           1262    16393    botecodb    DATABASE     �   CREATE DATABASE botecodb WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Portuguese_Brazil.1252' LC_CTYPE = 'Portuguese_Brazil.1252';
    DROP DATABASE botecodb;
             postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
             postgres    false            W           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                  postgres    false    3                        3079    12924    plpgsql 	   EXTENSION     ?   CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;
    DROP EXTENSION plpgsql;
                  false            X           0    0    EXTENSION plpgsql    COMMENT     @   COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';
                       false    1            �            1259    16394 	   categoria    TABLE     l   CREATE TABLE public.categoria (
    cat_id integer NOT NULL,
    cat_nome character varying(15) NOT NULL
);
    DROP TABLE public.categoria;
       public         postgres    false    3            �            1259    16397    categoria_cat_id_seq    SEQUENCE     }   CREATE SEQUENCE public.categoria_cat_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.categoria_cat_id_seq;
       public       postgres    false    196    3            Y           0    0    categoria_cat_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.categoria_cat_id_seq OWNED BY public.categoria.cat_id;
            public       postgres    false    197            �            1259    16399    comanda    TABLE     6  CREATE TABLE public.comanda (
    com_id integer NOT NULL,
    gar_id integer NOT NULL,
    com_numero numeric(15,0) NOT NULL,
    com_nome character varying(40),
    com_data timestamp without time zone,
    com_desc character varying(255) NOT NULL,
    com_valor numeric(8,2),
    com_status character(1)
);
    DROP TABLE public.comanda;
       public         postgres    false    3            Z           0    0    COLUMN comanda.com_status    COMMENT     G   COMMENT ON COLUMN public.comanda.com_status IS 'A: aberto
F: fechado';
            public       postgres    false    198            �            1259    16402    comanda_com_id_seq    SEQUENCE     {   CREATE SEQUENCE public.comanda_com_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.comanda_com_id_seq;
       public       postgres    false    3    198            [           0    0    comanda_com_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.comanda_com_id_seq OWNED BY public.comanda.com_id;
            public       postgres    false    199            �            1259    16404    comanda_gar_id_seq    SEQUENCE     {   CREATE SEQUENCE public.comanda_gar_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.comanda_gar_id_seq;
       public       postgres    false    198    3            \           0    0    comanda_gar_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.comanda_gar_id_seq OWNED BY public.comanda.gar_id;
            public       postgres    false    200            �            1259    16406    garcon    TABLE     V  CREATE TABLE public.garcon (
    gar_id integer NOT NULL,
    gar_nome character varying(40) NOT NULL,
    gar_endereco character varying(50),
    gar_cidade character varying(20),
    gar_uf character varying(2),
    gar_fone character varying(15),
    gar_foto bytea,
    gar_cep character varying(11),
    gar_cpf character varying(11)
);
    DROP TABLE public.garcon;
       public         postgres    false    3            �            1259    16412    garcon_gar_id_seq    SEQUENCE     z   CREATE SEQUENCE public.garcon_gar_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.garcon_gar_id_seq;
       public       postgres    false    3    201            ]           0    0    garcon_gar_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.garcon_gar_id_seq OWNED BY public.garcon.gar_id;
            public       postgres    false    202            �            1259    16414    item    TABLE     �   CREATE TABLE public.item (
    it_quantidade integer NOT NULL,
    com_id integer NOT NULL,
    prod_id integer NOT NULL,
    it_preco numeric(8,2)
);
    DROP TABLE public.item;
       public         postgres    false    3            �            1259    16417    item_com_id_seq    SEQUENCE     x   CREATE SEQUENCE public.item_com_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.item_com_id_seq;
       public       postgres    false    203    3            ^           0    0    item_com_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.item_com_id_seq OWNED BY public.item.com_id;
            public       postgres    false    204            �            1259    16419    item_prod_id_seq    SEQUENCE     y   CREATE SEQUENCE public.item_prod_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.item_prod_id_seq;
       public       postgres    false    203    3            _           0    0    item_prod_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.item_prod_id_seq OWNED BY public.item.prod_id;
            public       postgres    false    205            �            1259    16421 	   pagamento    TABLE     �   CREATE TABLE public.pagamento (
    pag_id integer NOT NULL,
    com_id integer,
    pag_valor numeric(8,2),
    tpg_id integer
);
    DROP TABLE public.pagamento;
       public         postgres    false    3            �            1259    16424    pagamento_pag_cod_seq    SEQUENCE     ~   CREATE SEQUENCE public.pagamento_pag_cod_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.pagamento_pag_cod_seq;
       public       postgres    false    206    3            `           0    0    pagamento_pag_cod_seq    SEQUENCE OWNED BY     N   ALTER SEQUENCE public.pagamento_pag_cod_seq OWNED BY public.pagamento.pag_id;
            public       postgres    false    207            �            1259    16426    produto    TABLE     �   CREATE TABLE public.produto (
    prod_id integer NOT NULL,
    cat_id integer NOT NULL,
    uni_id integer NOT NULL,
    prod_nome character varying(30) NOT NULL,
    prod_preco numeric(8,2) NOT NULL,
    prod_descr character varying(100)
);
    DROP TABLE public.produto;
       public         postgres    false    3            �            1259    16429    produto_cat_id_seq    SEQUENCE     {   CREATE SEQUENCE public.produto_cat_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.produto_cat_id_seq;
       public       postgres    false    208    3            a           0    0    produto_cat_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.produto_cat_id_seq OWNED BY public.produto.cat_id;
            public       postgres    false    209            �            1259    16431    produto_prod_id_seq    SEQUENCE     |   CREATE SEQUENCE public.produto_prod_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.produto_prod_id_seq;
       public       postgres    false    3    208            b           0    0    produto_prod_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.produto_prod_id_seq OWNED BY public.produto.prod_id;
            public       postgres    false    210            �            1259    16433    produto_uni_id_seq    SEQUENCE     {   CREATE SEQUENCE public.produto_uni_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.produto_uni_id_seq;
       public       postgres    false    3    208            c           0    0    produto_uni_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.produto_uni_id_seq OWNED BY public.produto.uni_id;
            public       postgres    false    211            �            1259    16435    tipopgto    TABLE     b   CREATE TABLE public.tipopgto (
    tpg_id integer NOT NULL,
    tpg_nome character varying(15)
);
    DROP TABLE public.tipopgto;
       public         postgres    false    3            �            1259    16438    tipopgto_tpg_id_seq    SEQUENCE     |   CREATE SEQUENCE public.tipopgto_tpg_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.tipopgto_tpg_id_seq;
       public       postgres    false    212    3            d           0    0    tipopgto_tpg_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.tipopgto_tpg_id_seq OWNED BY public.tipopgto.tpg_id;
            public       postgres    false    213            �            1259    16440    unidade    TABLE     j   CREATE TABLE public.unidade (
    uni_id integer NOT NULL,
    uni_nome character varying(15) NOT NULL
);
    DROP TABLE public.unidade;
       public         postgres    false    3            �            1259    16443    unidade_uni_id_seq    SEQUENCE     {   CREATE SEQUENCE public.unidade_uni_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.unidade_uni_id_seq;
       public       postgres    false    214    3            e           0    0    unidade_uni_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.unidade_uni_id_seq OWNED BY public.unidade.uni_id;
            public       postgres    false    215            �
           2604    16445    categoria cat_id    DEFAULT     t   ALTER TABLE ONLY public.categoria ALTER COLUMN cat_id SET DEFAULT nextval('public.categoria_cat_id_seq'::regclass);
 ?   ALTER TABLE public.categoria ALTER COLUMN cat_id DROP DEFAULT;
       public       postgres    false    197    196            �
           2604    16446    comanda com_id    DEFAULT     p   ALTER TABLE ONLY public.comanda ALTER COLUMN com_id SET DEFAULT nextval('public.comanda_com_id_seq'::regclass);
 =   ALTER TABLE public.comanda ALTER COLUMN com_id DROP DEFAULT;
       public       postgres    false    199    198            �
           2604    16447    comanda gar_id    DEFAULT     p   ALTER TABLE ONLY public.comanda ALTER COLUMN gar_id SET DEFAULT nextval('public.comanda_gar_id_seq'::regclass);
 =   ALTER TABLE public.comanda ALTER COLUMN gar_id DROP DEFAULT;
       public       postgres    false    200    198            �
           2604    16448    garcon gar_id    DEFAULT     n   ALTER TABLE ONLY public.garcon ALTER COLUMN gar_id SET DEFAULT nextval('public.garcon_gar_id_seq'::regclass);
 <   ALTER TABLE public.garcon ALTER COLUMN gar_id DROP DEFAULT;
       public       postgres    false    202    201            �
           2604    16449    item com_id    DEFAULT     j   ALTER TABLE ONLY public.item ALTER COLUMN com_id SET DEFAULT nextval('public.item_com_id_seq'::regclass);
 :   ALTER TABLE public.item ALTER COLUMN com_id DROP DEFAULT;
       public       postgres    false    204    203            �
           2604    16450    item prod_id    DEFAULT     l   ALTER TABLE ONLY public.item ALTER COLUMN prod_id SET DEFAULT nextval('public.item_prod_id_seq'::regclass);
 ;   ALTER TABLE public.item ALTER COLUMN prod_id DROP DEFAULT;
       public       postgres    false    205    203            �
           2604    16451    pagamento pag_id    DEFAULT     u   ALTER TABLE ONLY public.pagamento ALTER COLUMN pag_id SET DEFAULT nextval('public.pagamento_pag_cod_seq'::regclass);
 ?   ALTER TABLE public.pagamento ALTER COLUMN pag_id DROP DEFAULT;
       public       postgres    false    207    206            �
           2604    16452    produto prod_id    DEFAULT     r   ALTER TABLE ONLY public.produto ALTER COLUMN prod_id SET DEFAULT nextval('public.produto_prod_id_seq'::regclass);
 >   ALTER TABLE public.produto ALTER COLUMN prod_id DROP DEFAULT;
       public       postgres    false    210    208            �
           2604    16453    produto cat_id    DEFAULT     p   ALTER TABLE ONLY public.produto ALTER COLUMN cat_id SET DEFAULT nextval('public.produto_cat_id_seq'::regclass);
 =   ALTER TABLE public.produto ALTER COLUMN cat_id DROP DEFAULT;
       public       postgres    false    209    208            �
           2604    16454    produto uni_id    DEFAULT     p   ALTER TABLE ONLY public.produto ALTER COLUMN uni_id SET DEFAULT nextval('public.produto_uni_id_seq'::regclass);
 =   ALTER TABLE public.produto ALTER COLUMN uni_id DROP DEFAULT;
       public       postgres    false    211    208            �
           2604    16455    tipopgto tpg_id    DEFAULT     r   ALTER TABLE ONLY public.tipopgto ALTER COLUMN tpg_id SET DEFAULT nextval('public.tipopgto_tpg_id_seq'::regclass);
 >   ALTER TABLE public.tipopgto ALTER COLUMN tpg_id DROP DEFAULT;
       public       postgres    false    213    212            �
           2604    16456    unidade uni_id    DEFAULT     p   ALTER TABLE ONLY public.unidade ALTER COLUMN uni_id SET DEFAULT nextval('public.unidade_uni_id_seq'::regclass);
 =   ALTER TABLE public.unidade ALTER COLUMN uni_id DROP DEFAULT;
       public       postgres    false    215    214            =          0    16394 	   categoria 
   TABLE DATA               5   COPY public.categoria (cat_id, cat_nome) FROM stdin;
    public       postgres    false    196   �\       ?          0    16399    comanda 
   TABLE DATA               r   COPY public.comanda (com_id, gar_id, com_numero, com_nome, com_data, com_desc, com_valor, com_status) FROM stdin;
    public       postgres    false    198   	]       B          0    16406    garcon 
   TABLE DATA               z   COPY public.garcon (gar_id, gar_nome, gar_endereco, gar_cidade, gar_uf, gar_fone, gar_foto, gar_cep, gar_cpf) FROM stdin;
    public       postgres    false    201   �]       D          0    16414    item 
   TABLE DATA               H   COPY public.item (it_quantidade, com_id, prod_id, it_preco) FROM stdin;
    public       postgres    false    203   *�       G          0    16421 	   pagamento 
   TABLE DATA               F   COPY public.pagamento (pag_id, com_id, pag_valor, tpg_id) FROM stdin;
    public       postgres    false    206   x�       I          0    16426    produto 
   TABLE DATA               ]   COPY public.produto (prod_id, cat_id, uni_id, prod_nome, prod_preco, prod_descr) FROM stdin;
    public       postgres    false    208   ��       M          0    16435    tipopgto 
   TABLE DATA               4   COPY public.tipopgto (tpg_id, tpg_nome) FROM stdin;
    public       postgres    false    212   ��       O          0    16440    unidade 
   TABLE DATA               3   COPY public.unidade (uni_id, uni_nome) FROM stdin;
    public       postgres    false    214   э       f           0    0    categoria_cat_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.categoria_cat_id_seq', 4, true);
            public       postgres    false    197            g           0    0    comanda_com_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.comanda_com_id_seq', 6, true);
            public       postgres    false    199            h           0    0    comanda_gar_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.comanda_gar_id_seq', 1, false);
            public       postgres    false    200            i           0    0    garcon_gar_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.garcon_gar_id_seq', 5, true);
            public       postgres    false    202            j           0    0    item_com_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.item_com_id_seq', 1, false);
            public       postgres    false    204            k           0    0    item_prod_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.item_prod_id_seq', 1, false);
            public       postgres    false    205            l           0    0    pagamento_pag_cod_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.pagamento_pag_cod_seq', 4, true);
            public       postgres    false    207            m           0    0    produto_cat_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.produto_cat_id_seq', 1, false);
            public       postgres    false    209            n           0    0    produto_prod_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.produto_prod_id_seq', 13, true);
            public       postgres    false    210            o           0    0    produto_uni_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.produto_uni_id_seq', 1, false);
            public       postgres    false    211            p           0    0    tipopgto_tpg_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.tipopgto_tpg_id_seq', 5, true);
            public       postgres    false    213            q           0    0    unidade_uni_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.unidade_uni_id_seq', 7, true);
            public       postgres    false    215            �
           2606    16459    categoria cat_id 
   CONSTRAINT     R   ALTER TABLE ONLY public.categoria
    ADD CONSTRAINT cat_id PRIMARY KEY (cat_id);
 :   ALTER TABLE ONLY public.categoria DROP CONSTRAINT cat_id;
       public         postgres    false    196            �
           2606    16461    comanda com_id 
   CONSTRAINT     P   ALTER TABLE ONLY public.comanda
    ADD CONSTRAINT com_id PRIMARY KEY (com_id);
 8   ALTER TABLE ONLY public.comanda DROP CONSTRAINT com_id;
       public         postgres    false    198            �
           2606    16463    garcon gar_id 
   CONSTRAINT     O   ALTER TABLE ONLY public.garcon
    ADD CONSTRAINT gar_id PRIMARY KEY (gar_id);
 7   ALTER TABLE ONLY public.garcon DROP CONSTRAINT gar_id;
       public         postgres    false    201            �
           2606    16465    item item_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.item
    ADD CONSTRAINT item_pkey PRIMARY KEY (com_id, prod_id);
 8   ALTER TABLE ONLY public.item DROP CONSTRAINT item_pkey;
       public         postgres    false    203    203            �
           2606    16467    pagamento pagamento_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.pagamento
    ADD CONSTRAINT pagamento_pkey PRIMARY KEY (pag_id);
 B   ALTER TABLE ONLY public.pagamento DROP CONSTRAINT pagamento_pkey;
       public         postgres    false    206            �
           2606    16469    produto prod_id 
   CONSTRAINT     R   ALTER TABLE ONLY public.produto
    ADD CONSTRAINT prod_id PRIMARY KEY (prod_id);
 9   ALTER TABLE ONLY public.produto DROP CONSTRAINT prod_id;
       public         postgres    false    208            �
           2606    16471    tipopgto tipopgto_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.tipopgto
    ADD CONSTRAINT tipopgto_pkey PRIMARY KEY (tpg_id);
 @   ALTER TABLE ONLY public.tipopgto DROP CONSTRAINT tipopgto_pkey;
       public         postgres    false    212            �
           2606    16473    unidade uni_id 
   CONSTRAINT     P   ALTER TABLE ONLY public.unidade
    ADD CONSTRAINT uni_id PRIMARY KEY (uni_id);
 8   ALTER TABLE ONLY public.unidade DROP CONSTRAINT uni_id;
       public         postgres    false    214            �
           2606    16474    produto cat_id    FK CONSTRAINT     t   ALTER TABLE ONLY public.produto
    ADD CONSTRAINT cat_id FOREIGN KEY (cat_id) REFERENCES public.categoria(cat_id);
 8   ALTER TABLE ONLY public.produto DROP CONSTRAINT cat_id;
       public       postgres    false    208    2734    196            �
           2606    16479    item com_id    FK CONSTRAINT     o   ALTER TABLE ONLY public.item
    ADD CONSTRAINT com_id FOREIGN KEY (com_id) REFERENCES public.comanda(com_id);
 5   ALTER TABLE ONLY public.item DROP CONSTRAINT com_id;
       public       postgres    false    203    198    2736            �
           2606    16484    comanda gar_id    FK CONSTRAINT     q   ALTER TABLE ONLY public.comanda
    ADD CONSTRAINT gar_id FOREIGN KEY (gar_id) REFERENCES public.garcon(gar_id);
 8   ALTER TABLE ONLY public.comanda DROP CONSTRAINT gar_id;
       public       postgres    false    198    2738    201            �
           2606    16489    pagamento pagamento_com_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.pagamento
    ADD CONSTRAINT pagamento_com_id_fkey FOREIGN KEY (com_id) REFERENCES public.comanda(com_id);
 I   ALTER TABLE ONLY public.pagamento DROP CONSTRAINT pagamento_com_id_fkey;
       public       postgres    false    206    2736    198            �
           2606    16494    pagamento pagamento_tpg_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.pagamento
    ADD CONSTRAINT pagamento_tpg_id_fkey FOREIGN KEY (tpg_id) REFERENCES public.tipopgto(tpg_id);
 I   ALTER TABLE ONLY public.pagamento DROP CONSTRAINT pagamento_tpg_id_fkey;
       public       postgres    false    212    206    2746            �
           2606    16499    item prod_id    FK CONSTRAINT     r   ALTER TABLE ONLY public.item
    ADD CONSTRAINT prod_id FOREIGN KEY (prod_id) REFERENCES public.produto(prod_id);
 6   ALTER TABLE ONLY public.item DROP CONSTRAINT prod_id;
       public       postgres    false    208    2744    203            �
           2606    16504    produto uni_id    FK CONSTRAINT     r   ALTER TABLE ONLY public.produto
    ADD CONSTRAINT uni_id FOREIGN KEY (uni_id) REFERENCES public.unidade(uni_id);
 8   ALTER TABLE ONLY public.produto DROP CONSTRAINT uni_id;
       public       postgres    false    2748    208    214            =      x�3�tJM�LI�2�t��1b���� Jp�      ?   �   x�u�1
�0������kZ�[7qry$#M�7� x�\�袋�m������3���"�j�F���~;N�0��C���Ġe}&1 A�x�x��/���ǲ:��<3��y)�@Z�oFW��LY�%�9Ԕ�aCrP�)�x��2�      B      x���K�.K���7G�#@�o�fV�(EѬ����H�T��*K�01��c�DP � �>����G��l�Zf�����������˿���m����y���������?��|�������B����K������>�}�u]��#�|�z����K�]���si����0?��g�����}!�9��,�����7y�������?~����׏ze>�Y��v>�����ɿ����?�ÿ����G��z�������:���{~���_�j�������e
��fe�`!��1�:��yU�U�Ϻ��=�٩��O8�ɼ�����]a��r�ݙƟ�-g��{���~��;�R��!�P��bl!�{��`�1�8�
���0ĘS����3oi�4�����#�TRM-�t��Sι`-wlp�Tr)��Vz�˨��Zj��j���[i�����FO=��ko����;���w����~���rp���/cƙf�e��f��+���*������x�0��ԧ=����Î;��ˮ����=���7��-o}����ݯp�!������
?�� �_������x%�p�ث�[�\��bt��r�u�1�T"C,n��+�.����k�}��o�X���g�r�����d����y������ĵ�;g~�ܵ<O)�ֻ���k���{�q�W�}���Y�|;�'�����[��ڳ}[|��V��f��uc*\���Y���T������(k�=��O	�Oi\���ݵj~~̷��^wۋ�� WXo�������O{��|�-�G���j�����
���2���ݵq�g=影��bgG���rہ{����9�B���*�h�g�9��ʷs��۪�SX�6[f��z��׫�`�x׾_�����¼��f�䑜����m��#���wf�5�k�����i�
�M;�����sU�ޓ���^��4\��y�b�̵�ڛArV�b��Xӌ7����3��z��s~X�w���}V01�����x�*,,�joNOx7��O��� /�𲒸X�Ao�w��b;�'��JBŎ�s��bg����|��Mx&��>�x�k����T�8;w�e|S~�����{�L��j�;�0�>j)�sԼ��J=A�}��oM��K�<��ب:1玱�;�4�4W �v��KV�X����yF~���}��O1��w���3�Z��|;�I E�L���;0wm�\��~�@��6���y�U�]�����Eg�Kv`O�c�=0����.�1�w��eN�f�{�s��BU�*cp�1���up��@2�t�t�k=��v1�q7�R'�� �;���Lb�8�N��V6l��� ��\#+��rM�����~���g���#><?�Pۀ��T6���;6��8@+x*pR�x���N������r��SfHw�X��:�1�l�#��V���4�#��:��=F����R������p�愹]�ʈ�K��x ����b�&�z�PD��5V�Q�Yg5B����2~�h���`���a�k�̈́Y�}�A{�?�K�)� ��� �/��{̊ծ�q�n�'�\z@����bv��Ćqo����y��ڈ��2�e��7@(�\f<��@��۱h�Ps��l�>��GePxx��ܳ9�w���s�4�}Ǭ�kx�M�]��-�����b��n�4Ų޺�v�y.�|̧�w�����y/c��w���c�� �a���ᦼ�h	[�LL�B8^U����X|$6�0o�F�'Yc&��Cg)Į%�P�*�0�p�+�m�'/�����/�@>�t���`'�b4��}�]g �⻵��&���1��Dp�f"���Y#��f=܉ 9�8p�.U����е�=f2�<`:��?�H��`��5�*�KcUk���*v�n��~�:��	��4�3�|C���6F�Ȉ@>��xb(ı�"�����&�e�$�k�y6̔�A���b��V��N`&��b ���6066nk:�;�!����Z����rIf�q50�$�_o��i./7Əa���u��>�7۸v���I|��T�A��g�Tl{[�Cn`q����m����3�a`� �2 Ι*،I.�%�|@�ˁQ|�b��2���~"�%���^��7�
,yӒ��+t�۶��bż��h*�3}\[��
c���]���<���b�	�o0M\;ѷ�������UDa���`� <a|j{bIe�',7y�C8,��5f���:{��@��o���D� �Ye𺀅�.������Q�}��<���P��T0��
1����=�3���^ �@{Ќ�*2�����-�.v����L�e��ID<��=�lua�����s{"3^�F.�8�^��|�<D_������ԠߦrB�=qfuH�61&�<Ɖ���I��8Åv�mM�-&�#� $+�(pg�z, 8/����[��L^'�d����\����!{�'���b�a<oT8#��s� ���c��O��`���+��$]\W���� �l�K��1Й�m�#�GQ%=�lĿa�~g��p��GE~)& ��Ih�V@OnDh@�2Ze����[YOxi���<2�:�� ���ı�W��@�Jr��(F�
n��I� U�g]�D��� ���U~�,r�C�&����&8"�I/v��1���|1p���9h1y���1Z`]�nB��0��~�ôA�y���QW?�]�\?��Dk~�P`5Xe"gG)�� �_bֽ��+J���Ѝ��Sdv��@�{�X����~h�6�ݡ���Ot@v0�2��Y�4du�Wh�H� �p�u"";��ou�ZW�	*�6�\��
Z_�%��T��͹
�3�0Ub\��c�����dY·_�����RcG0S�s�8:M,C6�Ŝ���;b�N���|�pPˬ�W0�b��r���/ #�*<`���f~å J���g�J@�;�Ò��1���a�3"��4 Hp.Ӌ�!�JC�UB���g��2�v��^����p/�}��
�%�~:.��'����ݘ����H�< ��U���)�{EϢ;�N����5-����Y�%R�Ǹ�Ǩ�k��$L�GA�ť_���@.��]'qsq�C�&qN�s1��f@��s�!M���7�̝(8�n���[�����3�+��h�
�B�ke��D��5�ER69Pv}��A��@�YC�5h��5iȸ0��7
&�Y�j�bI@Nh��~�S�ʌ3i����n�\@V��#����@��q�6!�@a3"�qN�I��2ݔ?UK� O���_�~��y���!��ɲQ]wM�!c{�p4&�.S	��D�S H��11D����Ӏ(���û#-kI�oVbxf��M�̧�O"ή���/�M�$�8i��A������W� �V�^�C���$8��4z:	���#����[�K�د���Z��O��a�0����8�+���L;A�;t<m�r�����h{a\��
K����G�?��)D(OX�3J�[<X5:�!���nY����m4h?�gf�WL	/9u�m�Z \I�A����.�7W���ǘ�$�F�;�0�iN #�ip�}��Iu���`yq���KpH�os@ĕU<+z^z
0eS(*D��ղ��?B�1�'̍���-!/ē�A׳����`�{`m�\�#m�8�����L(�G�\Jtk?c�x��F&�00T�|���T����	�p
�5n{�f�p@�+1�56�:+W���)lr�YZ�+ 9պ|ہ2����3#���`��'/	d�C1&`���d�f@��#��.db�:��M���Ќ3hhh<��J;u�啒�P��4�]8"��8�M�&�Vtl_5@� �'��I~�K�Do�*�2�.�^ˆ��^\k2����JG�7����K��<�EgK���3�0�&�X�C&�sB����A<�q��5��&��լnz-H��~��n�$��Dc���x�>���!���9"SCS]    ��̄��/h�o�th/��f�c��b�1Pƶ��s-��*T�5�G�)wS�����9l�&
��Y:�+�,�4-<>��F	��y�-� F$���9#?�ES��N��$�9�S� |��.4	��q'���a��W[f�p��4�-��_�� =#Vx��ƺ/m~.n_R�N!a�g�b��5�ꂾ��!2�y�}c���c5��6��Y��������$�z��A
a��Ϊ��
H���J�0�jn=�9���Ki�$22�J�D�O�F�Dl��/wk*��n��8�a8��~�brM�|u�Y5�EZ�6s�J��܄��RٲF&���� �_1'M����G٤/~�zu��CK *����h�m*���𔸲��M̍��; ��א�EK��$�0GC
���l�h��2�?�_���:��&�z{i�ft��D�5>9�:�2�)�O�3�­L�@�%��b\���d�P�)���]Z?��n�J,�<Nѭ!�!6`nf�	�r�Tkej �GL�0ϑ�Vs��%�Im��qd���u��7Bt��D�^���6�
�����SN��9R%4qE�à�rG��N�����@2Q��w���>Y�zҔ�t��w�'�@���5�:V�0�4��R\C�MЍ���6���ukms�%1/��R�	�{�&��B��|UK�]�� D�j�z-��XH�e��1����c��7v,t`7&>۩�A_����!�O9�c��osaʅI��8&���
0|��y��ɿ��'c/�gS�b�Ed:��|jg�9]�J_�p�yт�u���#�Վ���UO~��a��D.�4���vbL4Z�1#�"����2p�)��Dύ��"
s��	�*[�����heƢ�Y8�Uˌ��{�_<��Pb2��V8c_�dґ9x����\]!�y�o�Jn���l�)1�n6qhu�w^י6̗ѥ�����cu�?&��[�E@��}-cqĲ%�k�(��o($^$<��kD��>ADf��g�P���H�_�L{�հ�R	V�3 ��K��3������2�h�"U5s5��.9:ar��� ����6�ӆٞ��c�	��k��y�5��"�I@�C��(��DB��M�<�b!1LÑ���X�&01�GD2^k�(#H�"<���1�0?ѣ�t�5y���6Ti��0����s>B�a2���v6
�\Ȍ��C�E�%��D���

�Ֆݰ���Bq<���=�)�~/kvE��a茈�>
@�Q+��Evl�n�n<
�)��$l.�u4�5���*i;�1WqG&�N"`�0���v�o���f�:I�'uRERz�]��0S�8B�яf7͙W��X��ڇM �@���'~ ���(H#��t`g;���';�=�oD ����G���̬F`дN��EȤyG0�o:P� �aM~���?�Gx�S�xP�uw#�a_��t}�B��]
ˋ�j��8	5�Ȉ7��"���]C2� ��9���zkϘ�YDln9���_�� #��c�bte��e�(���}fd�2����=�P�J��Q�<}`��pg՞ b3�=�h�X���=��ߐ�<O~n�2<�Ѕ��Bo�6�,�������̼t]h%R���O��S����u�z7�Z/(��n1�K�"�~����@r²�`��'��/QbrѺ��-�6c�W<`"��˷��	x�gp�@��!L�5<�W�����Ʃ���:+r�
kܪ��g�sb�=�|�$�1[��l�.��l�<ξ/n��B8�T��,%�P���g���@���l0��%��ǔ��%B��O��t�:i�U�}�󵿃q1�}2ۼ�"�I�C")H�l��X ���ez/��H�W�̔���jV��Έ* �����$�I�&�-��[�����Df:uJ�ҧz<�=
��_��ˬ��7�S�(���xBQL�J�vZy��Q�M�d,w2��(v��Yo+F�V:�f������VwCдh�x�*�.���T�ӂ�b>��?ukf8�\yX����wm븃�����$$漟3\�Zd�����-��]A�-���7���a�DI��	���Q�p���� e�P�F������k��`?��Za�0�7��'栦{BCpc	a�gt�`���%?E;n��%G���cs���5�u2��e�M0��I�],��X��Dp,�1&~8	� �ž$��ҭ�dl(#�:K��Nb*z-3`-
%��p�	� ��`,�O j�
����ib��j-]���R��m~$l_D��%_�x��0Y(�|;��m_��(���5w�B?qѡ��y��nej�L��O�;5�~jf(*cG�f �M�ǂ�i�!s(f�4��(3l1~#�=�(\3�
�@�&&��*#�F�B���	�����V�.^�x"6�V�'�KV[�+�RU��r	��N����Y@`ZR���uG��d�ڔܶa�m1�*�3�Knbf�W�������h,��jN�?���c~=z��O���Է)(����@����y�"��O�;�f	�{W�X=^3Ӿ�M�`�ǫ)�{� n���$Ě���y�K�qSX��-(^����%�X�5V�}�'�."�u��e��6C[�*�Jd5��ᰇ"��;+d}��Xk�������+^!��)�F\�� Ʉ�;D�}��	V�����u��v���y��iQ8��@�ঢ়�&�3�y�3�q�	�8�z,1�cT�jf<r58��FV.��Z=����u9u,��L̖e7���=��Z�pZ꩸������(zV�z��9y�	�u�8���� pb1cg�e��Š���X	'7�-m(Oٳa�D��n� f�l��!8�M��p�{�"L{E#C/.�܏2o+�@T#��p�Ψ�i�r�jJCғ��ʔ�A��h��(ӆ�~��;�I.f�.�/���"ú̌V���Ď��vG8�=���u��t��j"�_*�d�5�a�<U�hw�Q�������k�9�U��aÂHzO�f�3]~%�Z�`���fEnt͉���&����5�L���#TH����S����:LVֆR{���N���4Սˆ`�#�0?�l<��gs�J�.?Зr�Sص�ܘ#P��:G-�A��!S��Qt#��� �]��h���h��S?����h���Ԫ�	� �l�p'g�^��j��Ђԩ���v�Vk��7%��I��H�����}�沭%E��D&hv�fVH��\vB��6!�R[��0�'��WC��cF�]dk5f�$ Yw��z��RGE���_�m����ѹZ����Ap�m��o�L�}�%�s��/ ��-6��??�������,z��-I�&#�ӆ�h?�򈅺;��
��Dai�K���^,+�o;浾�ֶ[���,����b`�?% ���qD�*�B�c̱���3x�����2����>h�XX��╼���	�`�2���i���5$�a�1�����=��{l�nO0����9|�R��vZYWQ��قޟ��л��ƚ��Z��G�w��K�{�����o�\���pG�&O�(��#���Io���I�ybx6j60�"J�%d�`h5��ǲ�y������9�"!�����^P�$�����"��\U�\�=�L��7.�����P�2�is��e�hq�۱U��zE�¦�~�,��!O�0��˩=��$���v�������V��R']�I�A�R�z�@v��)xA�Ʊ��Z���a �]�T3kq�OD�͟h���=�ӢE���>78�Ú#.g�ێǡӞ`(\���&4�\<z<	]�Cz����8���Pi��u�;E��
f8�`��d�[%b�����E��#tх@88Sװ~~�8�m2�g0R�c�mt�a)�K ��/��.��c�='�بɌ1�;.�c�{,��fw�ԦTȲ��t^�!�y^�8�\�Y�mX��7;��xޤ�u�NA��gt���R�⋯�:��" _  ��x��2����|��Yt�CAdC�wV��0 -2�a��X�_���"7ɏ����;{h���dh3�-ȶP�/�"���K��`Z�3_��i�����A�F����Zl��:
��R?3!"J|�6(|�}�����u�v:��K9$k���=O=�+R��Q�t��6b�:	��e�¨�r*X��e�+��o����B�]fTY��"fB�L�A�Myd���G���aHV�a@�D3��d���}���41����/��U�^��Ig�2�l�h5<�R��� C]vl��2� 1�b���1���H�ź�2{p��+ֵ? �G��A*��)bh ��v	V�N6�4`bD1�1�����DZ	}���;����A���oQt���tGa����c۳� ,)��öAM�&Pm�d���Z�m�(�ӈ�Ac� �i�~��b���8�,䂙�d�tR M�nц%P�>*`{��xuo9Qq�H�ZNĤ,���d8چj񆭻��a,�%{�͐��O��:MT��~S�OCw��%��2�W��W�0TH��;�:����T�b�,���T���0��1��eEӌ*^�p���j�v
�G ���E��3�0��n5k_ݷz���u����u�t��S+*�_p�j�� �"��AA{ǡ�ts�r�u�,���>�����0��z�d�e7]�6$]�w*+o�sI��9��I1,o i�(%�
rn�h�'VL(ؚ���lUB�F�مd���� �x�����`���n'߂���,�s[�K���؆��0�
u}�Ȳ��߶=�S��&E(!nܧ�q���t���b\���¸� ��i��3�~�������(��[� �
F��<�愹�f�8d!����®�\C��)oD�$⫹=8��?38���U�YڳRm��ꢶjtM1�`��<�@�:�^�!BY��yA���n'��ó(0jb��`�_��;} �7d��7@�	��7B;��WI�*�l�)��"
���	�S��&�P���m��❒�[�����D���y&y����U����xv�iA3rW��x����A%�{�b �X$&)�*�$����k��z�ޤq�}ۤ�Ӷǣi�I�M��bH ��d���y*y��͑�Ps{6�BXN��L9r�Ndw6>%Q��	K
- �J����������H]�ٱ�6�)���a�����Nfq+���tw�su�����c.X+�t�i�{d\���&l\���G��(Jр�@$pw;ڿ���ë��ζQN(`LI߹,��)Ox���~�>5������4�.���2��m�?�|,�ؾyZ���Rcv�Y��y!}���B=�mp0�b��ζ�l̲�,R�G�u��o;�ٽ ���zY�iK��:7O�BZM[ �q������_^w�v5�&@̪2�j6)��<����A8�X���3�u=�P;�c�zx�$��GY��|���$������5�}Q9�}hp��|' ��J���q���ԉ�������c�  ��28z�y��G%긜�袾4hZ#,vL�ly�sܶ�0B�����g)�9S{��95j�T��,��2�L]�H,��^eO��#>��N��jX��d�ŷN"� ��V�����m� � �<s{���ρ�Ua%���I���i������13k��er�s��]�
��Z��&|���%�"�yq�U�b����<b�^��";�NW�h1�����5{ �G�J%�O��W>.�}Z[qS�5%��`ޖB�hD��	bgJ�b�y~��F�{5�R<���賙Ӻ���h��6�b��{X�fF;�����m���Y��Q�i�D;����FCJ�I�f�4a�X�9��b
�6���Ɗ@���&'~�ζ���`�ב���ɳ���i���)��k��
����c������G�yT�==����|�B2P��MV����q
�њXJ}��k��R$a�:��੄?�:M��H��S��.D�]�H�mX���W;�=�ޞx��ǽ.tx��F�i�|��V���u���b�'h��@�a�s���z�b��ߘ��x��R�0�tp�sXIg.�:J?G�!��'��Ƙ��f�cvj�,XZ�����`�a?���AU��0�25�1����[���r򶘳z���Y>��I\�H�P1Wq�;�N*��?�GB��������g����4�@��p?'W�<������O�D�1#>Fmd�d�����I6��ap�(~{��lk���I')�O�Ʒ�1�B%�v���[��L���?B\?�֧�X2%�β��F��N��>eb�b?���Ty^pr3ar�d��1�����9̀ڱ+��&��{f���|��G���>g���#	�,�ǔ,�7�ƕ�F�2� b�6nO��)�F,�a摛��8�(�V�-^��}��_�J�~86q��[��u���Qj*x���1x�Q��<s��[�:'�}Ë�{=��*�(;	Y;]��X�f��9�z�%<�F{��B3sc��#~�^/���{B	ƥ{���|lX�u�K�j�j�LpV�A��ᣣ�#��=�����i���@|��*�=%�����8��mk�����V�!aH�'���bX�a��c�G}�O,S�])��$dw�3�T���S(�TQ0�`y�Ѝ	x�M��s<a}��[u�6�x�����s�}����ǎ=ϑk ��PP�%�Ǹ|ЋG���ø�6)V;�<9d�x�􍗥v�1�q_��j�7lE/@���5Я&/�W_����:�cب�lҞ�'���q����%��Box~�B���ɉ�L�m}�}�TA�! �H�����l�T��t<���G����ɽ_��	��ñ��ל�0M�ݎN�� �}ק��0}�~��������+>�|�Y�@�zU��Y�Ȝ�m�g�H��sꕶ~���Sz��7�dv��C������ĔFD�7.}ջ�N�M�\Շ�H��V�<��ekS��xϴ(�6Zbc�Y_�"�Q��Hi�3}`��s�z��1��y ��Ym��N��3Ll[8�u�a)�I�0O{oW��a��8ޖ��|�@o�Ns�A0K��S�� 圢��}���#@��H��<7᜛n���'H���5P�z�F��r�Y���|�M[���Rv��<�L�����ӦXDK.ދ<���|r�Um���|<�9B~gWH^��)�����R��@fg�L�9y�-�wB|aS�p�t�3J�j	&<"׺�VpА�U|`"!��|��}بd3����+�h(v�m����=�o�x ?$[VR9����;�s�j�L�Sa��x>���GEl;o���;|�ab~�y�n�6B�����:a�r��a��j�ѐ����]$,_aM��F�����`[߷#�>6�[�6���vV��O������������!�������������������?���LN.���L���٨~��G�r��������.�7���?�??�}�?��_��O\��������Ͽ���~����:2���>4���~�lG �~�w��7�������      D   >   x�-��  C��2����������I�#��Ȇ!����˷~z-��w��[yմx��.*�t      G   $   x�3�4�4�30�4�2�4�47�33�4����� 9��      I   �   x�U�A� DןSp	����&�L41��|[ڢ�=�w�b~�11����|rP����1|���RS�4!�א�68��6�!S��T�v<�L�%,X���~���=��8���f;Z�<��<`[��/^�w�6%���w6����UBe�d�+Z���]��������]m]��A~	xA��s�?�ɘ;���E      M   ;   x�3�t���H�,��2�tN,*�Sp.:�2%�$��*�rxe�o�霑ZX������ -Z/      O   G   x�3��J,*J�2�t�/N�2��/:����|.CN���|.S΀Ԕ�����8}K��9�A:��b���� �~�     