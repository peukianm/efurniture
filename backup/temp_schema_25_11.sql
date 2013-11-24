CREATE TABLE FURNITURE.COMPANY
(
  COMPANYID           NUMBER                    NOT NULL,
  NAME                VARCHAR2(100 BYTE)        NOT NULL,
  DESCRIPTION         VARCHAR2(300 BYTE),
  EMAIL               VARCHAR2(40 BYTE),
  AFM                 VARCHAR2(12 BYTE),
  CONTACTPERSON       VARCHAR2(70 BYTE),
  CREATED_TIMESTAMP   TIMESTAMP(6)              DEFAULT SYSDATE,
  MODIFIED_TIMESTAMP  TIMESTAMP(6)              DEFAULT SYSDATE,
  PHONE1              VARCHAR2(22 BYTE),
  PHONE2              VARCHAR2(22 BYTE),
  ACTIVE              INTEGER                   DEFAULT 1                     NOT NULL,
  CREATEDDATE         DATE
)
TABLESPACE FURNITURE_TABLES
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE FURNITURE.CURRENCY
(
  CURRENCYID  NUMBER                            NOT NULL,
  NAME        VARCHAR2(100 BYTE)                NOT NULL,
  SYMBOL      VARCHAR2(8 BYTE)                  NOT NULL
)
TABLESPACE FURNITURE_TABLES
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE FURNITURE.ADDRESS
(
  ADDRESSID   NUMBER                            NOT NULL,
  ADDRESS     VARCHAR2(60 BYTE)                 NOT NULL,
  POSTALCODE  VARCHAR2(10 BYTE),
  CITY        VARCHAR2(60 BYTE)                 NOT NULL,
  COUNTRY     VARCHAR2(60 BYTE),
  COMPANYID   NUMBER                            NOT NULL
)
TABLESPACE FURNITURE_TABLES
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE FURNITURE.SPECIFICATIONCATEGORY
(
  SPECIFICATIONCATEGORYID  NUMBER               NOT NULL,
  NAME                     VARCHAR2(80 BYTE)    NOT NULL,
  ACTIVE                   INTEGER              DEFAULT 1                     NOT NULL,
  CREATED_TIMESTAMP        TIMESTAMP(6)         DEFAULT SYSDATE,
  MODIFIED_TIMESTAMP       TIMESTAMP(6)         DEFAULT SYSDATE
)
TABLESPACE FURNITURE_TABLES
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE FURNITURE.PRODUCTCATEGORY
(
  PRODUCTCATEGORYID   NUMBER                    NOT NULL,
  NAME                VARCHAR2(60 BYTE)         NOT NULL,
  ACTIVE              INTEGER                   DEFAULT 1,
  DESCRIPTION         VARCHAR2(300 BYTE),
  ORDERED             INTEGER,
  CREATED_TIMESTAMP   TIMESTAMP(6)              DEFAULT SYSDATE,
  MODIFIED_TIMESTAMP  TIMESTAMP(6)              DEFAULT SYSDATE
)
TABLESPACE FURNITURE_TABLES
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE FURNITURE.ROLE
(
  ROLEID       NUMBER                           NOT NULL,
  NAME         VARCHAR2(100 BYTE)               NOT NULL,
  DESCRIPTION  VARCHAR2(200 BYTE),
  ORDERED      INTEGER
)
TABLESPACE FURNITURE_TABLES
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE FURNITURE.ACTIONSCATEGORY
(
  CATEGORYID  NUMBER                            NOT NULL,
  NAME        VARCHAR2(4000 BYTE)
)
TABLESPACE FURNITURE_TABLES
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE FURNITURE.USERS
(
  USERID              NUMBER                    NOT NULL,
  USERNAME            VARCHAR2(100 BYTE)        NOT NULL,
  PASSWORD            VARCHAR2(100 BYTE)        NOT NULL,
  DESCRIPTION         VARCHAR2(100 BYTE),
  ROLEID              NUMBER,
  NAME                VARCHAR2(80 BYTE),
  SURNAME             VARCHAR2(80 BYTE),
  CREATED_TIMESTAMP   TIMESTAMP(6)              DEFAULT SYSDATE,
  MODIFIED_TIMESTAMP  TIMESTAMP(6)              DEFAULT SYSDATE,
  COMPANYID           NUMBER
)
TABLESPACE FURNITURE_TABLES
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE FURNITURE.CATALOGUE
(
  CATALOGUEID         NUMBER                    NOT NULL,
  NAME                VARCHAR2(80 BYTE)         NOT NULL,
  DESCRIPTION         VARCHAR2(400 BYTE),
  ACTIVE              INTEGER                   DEFAULT 1,
  CREATED_TIMESTAMP   TIMESTAMP(6)              DEFAULT SYSDATE,
  MODIFIED_TIMESTAMP  TIMESTAMP(6)              DEFAULT SYSDATE,
  CREATEDDATE         DATE
)
TABLESPACE FURNITURE_TABLES
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE FURNITURE.PRODUCTLINE
(
  PRODUCTLINEID       NUMBER                    NOT NULL,
  NAME                VARCHAR2(80 BYTE)         NOT NULL,
  DESCRIPTION         VARCHAR2(400 BYTE),
  ACTIVE              INTEGER                   DEFAULT 1,
  CREATED_TIMESTAMP   TIMESTAMP(6)              DEFAULT SYSDATE,
  MODIFIED_TIMESTAMP  TIMESTAMP(6)              DEFAULT SYSDATE,
  CREATEDDATE         DATE
)
TABLESPACE FURNITURE_TABLES
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE FURNITURE.ITEM
(
  ITEMID              NUMBER                    NOT NULL,
  NAME                VARCHAR2(80 BYTE)         NOT NULL,
  DESCRIPTION         VARCHAR2(400 BYTE),
  CREATED_TIMESTAMP   TIMESTAMP(6)              DEFAULT SYSDATE,
  MODIFIED_TIMESTAMP  TIMESTAMP(6)              DEFAULT SYSDATE,
  ACTIVE              INTEGER                   DEFAULT 1
)
TABLESPACE FURNITURE_TABLES
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE FURNITURE.COMPANYCATALOGUE
(
  ID                  NUMBER                    NOT NULL,
  COMPANYID           NUMBER                    NOT NULL,
  CATALOGUEID         NUMBER                    NOT NULL,
  ACTIVE              INTEGER                   DEFAULT 1,
  CREATED_TIMESTAMP   TIMESTAMP(6)              DEFAULT SYSDATE,
  MODIFIED_TIMESTAMP  TIMESTAMP(6)              DEFAULT SYSDATE
)
TABLESPACE FURNITURE_TABLES
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE FURNITURE.SVALUE
(
  VALUEID             NUMBER                    NOT NULL,
  NAME                VARCHAR2(80 BYTE)         NOT NULL,
  COMMENTS            VARCHAR2(400 BYTE),
  HASIMAGE            INTEGER                   DEFAULT 0                     NOT NULL,
  CREATED_TIMESTAMP   TIMESTAMP(6)              DEFAULT SYSDATE,
  MODIFIED_TIMESTAMP  TIMESTAMP(6)              DEFAULT SYSDATE
)
TABLESPACE FURNITURE_TABLES
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE FURNITURE.IMAGECATALOGUE
(
  IMAGEID             NUMBER                    NOT NULL,
  DESCRIPTION         VARCHAR2(400 BYTE),
  ACTIVE              INTEGER                   DEFAULT 1                     NOT NULL,
  CATALOGUEID         NUMBER                    NOT NULL,
  IMAGE               BLOB,
  PATH                VARCHAR2(100 BYTE)        NOT NULL,
  PRIMARY             INTEGER,
  FILENAME            VARCHAR2(100 BYTE)        NOT NULL,
  CREATED_TIMESTAMP   TIMESTAMP(6)              DEFAULT SYSDATE,
  MODIFIED_TIMESTAMP  TIMESTAMP(6)              DEFAULT SYSDATE
)
TABLESPACE FURNITURE_BLOB
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
LOB (IMAGE) STORE AS 
      ( TABLESPACE  FURNITURE_BLOB 
        ENABLE      STORAGE IN ROW
        CHUNK       8192
        RETENTION
        NOCACHE
        INDEX       (
          TABLESPACE FURNITURE_BLOB
          STORAGE    (
                      INITIAL          64K
                      NEXT             1
                      MINEXTENTS       1
                      MAXEXTENTS       UNLIMITED
                      PCTINCREASE      0
                      BUFFER_POOL      DEFAULT
                     ))
        STORAGE    (
                    INITIAL          64K
                    MINEXTENTS       1
                    MAXEXTENTS       UNLIMITED
                    PCTINCREASE      0
                    BUFFER_POOL      DEFAULT
                   )
      )
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE FURNITURE.IMAGEPRODUCTLINE
(
  IMAGEID             NUMBER                    NOT NULL,
  DESCRIPTION         VARCHAR2(400 BYTE),
  ACTIVE              INTEGER                   DEFAULT 1                     NOT NULL,
  PRODUCTLINEID       NUMBER                    NOT NULL,
  IMAGE               BLOB,
  PATH                VARCHAR2(100 BYTE)        NOT NULL,
  PRIMARY             INTEGER,
  FILENAME            VARCHAR2(100 BYTE)        NOT NULL,
  CREATED_TIMESTAMP   TIMESTAMP(6)              DEFAULT SYSDATE,
  MODIFIED_TIMESTAMP  TIMESTAMP(6)              DEFAULT SYSDATE
)
TABLESPACE FURNITURE_BLOB
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
LOB (IMAGE) STORE AS 
      ( TABLESPACE  FURNITURE_TABLES 
        ENABLE      STORAGE IN ROW
        CHUNK       8192
        RETENTION
        NOCACHE
        INDEX       (
          TABLESPACE FURNITURE_TABLES
          STORAGE    (
                      INITIAL          64K
                      NEXT             1
                      MINEXTENTS       1
                      MAXEXTENTS       UNLIMITED
                      PCTINCREASE      0
                      BUFFER_POOL      DEFAULT
                     ))
        STORAGE    (
                    INITIAL          64K
                    MINEXTENTS       1
                    MAXEXTENTS       UNLIMITED
                    PCTINCREASE      0
                    BUFFER_POOL      DEFAULT
                   )
      )
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE FURNITURE.MEASURMENT
(
  MEASURMENTID  NUMBER                          NOT NULL,
  NAME          VARCHAR2(80 BYTE)               NOT NULL,
  SYMBOL        VARCHAR2(80 BYTE)               NOT NULL,
  TYPE          INTEGER,
  ACTIVE        INTEGER                         DEFAULT 1                     NOT NULL,
  ORDERED       INTEGER
)
TABLESPACE FURNITURE_TABLES
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE FURNITURE.CATEGORY
(
  NAME                VARCHAR2(100 BYTE)        NOT NULL,
  DESCRIPTION         VARCHAR2(400 BYTE),
  DEPTH               INTEGER,
  PARENTCATEGORYID    NUMBER,
  ACTIVE              INTEGER                   DEFAULT 1                     NOT NULL,
  ORDERED             INTEGER,
  CATEGORYID          NUMBER                    NOT NULL,
  CREATED_TIMESTAMP   TIMESTAMP(6)              DEFAULT SYSDATE,
  MODIFIED_TIMESTAMP  TIMESTAMP(6)              DEFAULT SYSDATE
)
TABLESPACE FURNITURE_TABLES
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE FURNITURE.COMPCAT
(
  ID                  NUMBER                    NOT NULL,
  COMPANYID           NUMBER                    NOT NULL,
  CATEGORYID          NUMBER                    NOT NULL,
  ACTIVE              INTEGER                   DEFAULT 1                     NOT NULL,
  ORDERED             INTEGER,
  CREATED_TIMESTAMP   TIMESTAMP(6)              DEFAULT SYSDATE,
  MODIFIED_TIMESTAMP  TIMESTAMP(6)              DEFAULT SYSDATE
)
TABLESPACE FURNITURE_TABLES
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE UNIQUE INDEX FURNITURE.ACTIONSCATEGORY_PK ON FURNITURE.ACTIONSCATEGORY
(CATEGORYID)
LOGGING
TABLESPACE FURNITURE_INDEX
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


CREATE UNIQUE INDEX FURNITURE.ADDRESS_PK ON FURNITURE.ADDRESS
(ADDRESSID)
LOGGING
TABLESPACE FURNITURE_INDEX
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


CREATE UNIQUE INDEX FURNITURE.CATALOGUE_PK ON FURNITURE.CATALOGUE
(CATALOGUEID)
LOGGING
TABLESPACE FURNITURE_INDEX
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


CREATE UNIQUE INDEX FURNITURE.COMPANYCATALOGUE_PK ON FURNITURE.COMPANYCATALOGUE
(ID)
LOGGING
TABLESPACE FURNITURE_INDEX
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


CREATE UNIQUE INDEX FURNITURE.COMPANY_PK ON FURNITURE.COMPANY
(COMPANYID)
LOGGING
TABLESPACE FURNITURE_INDEX
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


CREATE UNIQUE INDEX FURNITURE.COMPCAT_PK ON FURNITURE.COMPCAT
(ID)
LOGGING
TABLESPACE FURNITURE_TABLES
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


CREATE UNIQUE INDEX FURNITURE.CURRENCY_PK ON FURNITURE.CURRENCY
(CURRENCYID)
LOGGING
TABLESPACE FURNITURE_TABLES
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


CREATE UNIQUE INDEX FURNITURE.IMAGECATALOGUE_PK ON FURNITURE.IMAGECATALOGUE
(IMAGEID)
LOGGING
TABLESPACE FURNITURE_INDEX
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


CREATE UNIQUE INDEX FURNITURE.IMAGEPRODUCTLINE_PK ON FURNITURE.IMAGEPRODUCTLINE
(IMAGEID)
LOGGING
TABLESPACE FURNITURE_INDEX
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


CREATE UNIQUE INDEX FURNITURE.ITEM_PK ON FURNITURE.ITEM
(ITEMID)
LOGGING
TABLESPACE FURNITURE_TABLES
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


CREATE UNIQUE INDEX FURNITURE.MEASURMENT_PK ON FURNITURE.MEASURMENT
(MEASURMENTID)
LOGGING
TABLESPACE FURNITURE_TABLES
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


CREATE UNIQUE INDEX FURNITURE.PRODUCTCATEGORY_PK ON FURNITURE.PRODUCTCATEGORY
(PRODUCTCATEGORYID)
LOGGING
TABLESPACE FURNITURE_TABLES
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


CREATE UNIQUE INDEX FURNITURE.PRODUCTLINE_PK ON FURNITURE.PRODUCTLINE
(PRODUCTLINEID)
LOGGING
TABLESPACE FURNITURE_INDEX
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


CREATE UNIQUE INDEX FURNITURE.ROLE_PK ON FURNITURE.ROLE
(ROLEID)
LOGGING
TABLESPACE FURNITURE_INDEX
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


CREATE UNIQUE INDEX FURNITURE.SPECIFICATIONCATEGORY_PK ON FURNITURE.SPECIFICATIONCATEGORY
(SPECIFICATIONCATEGORYID)
LOGGING
TABLESPACE FURNITURE_TABLES
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


CREATE UNIQUE INDEX FURNITURE.USERS_PK ON FURNITURE.USERS
(USERID)
LOGGING
TABLESPACE FURNITURE_INDEX
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


CREATE UNIQUE INDEX FURNITURE.VALUE_PK ON FURNITURE.SVALUE
(VALUEID)
LOGGING
TABLESPACE FURNITURE_TABLES
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


CREATE OR REPLACE TRIGGER FURNITURE."BI_USERS"   
  before insert on "USERS"               
  for each row
begin   
    select "USERS_SEQ".nextval into :NEW.USERID from dual; 
end;
/


CREATE OR REPLACE TRIGGER FURNITURE."BI_ACTIONSCATEGORY"   
  before insert on "ACTIONSCATEGORY"               
  for each row
begin   
    select "ACTIONSCATEGORY_SEQ".nextval into :NEW.CATEGORYID from dual; 
end;
/

ALTER TRIGGER FURNITURE.BI_ACTIONSCATEGORY DISABLE;


CREATE OR REPLACE TRIGGER FURNITURE."BI_ROLE"   
  before insert on "ROLE"               
  for each row
begin   
    select "ROLE_SEQ".nextval into :NEW.ROLEID from dual; 
end;
/

ALTER TRIGGER FURNITURE.BI_ROLE DISABLE;


CREATE TABLE FURNITURE.ACTION
(
  ACTIONID    NUMBER                            NOT NULL,
  NAME        VARCHAR2(300 BYTE)                NOT NULL,
  CATEGORYID  NUMBER
)
TABLESPACE FURNITURE_TABLES
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE FURNITURE.USERROLES
(
  ID                  NUMBER                    NOT NULL,
  USERID              NUMBER                    NOT NULL,
  ROLEID              NUMBER                    NOT NULL,
  PRIMARY             INTEGER,
  CREATED_TIMESTAMP   TIMESTAMP(6)              DEFAULT SYSDATE,
  MODIFIED_TIMESTAMP  TIMESTAMP(6)              DEFAULT SYSDATE
)
TABLESPACE FURNITURE_TABLES
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE FURNITURE.CATALOGUEPRODUCTLINE
(
  ID                  NUMBER                    NOT NULL,
  CATALOGUEID         NUMBER                    NOT NULL,
  PRODUCTLINEID       NUMBER                    NOT NULL,
  ACTIVE              INTEGER                   DEFAULT 1,
  CREATED_TIMESTAMP   TIMESTAMP(6)              DEFAULT SYSDATE,
  MODIFIED_TIMESTAMP  TIMESTAMP(6)              DEFAULT SYSDATE
)
TABLESPACE FURNITURE_TABLES
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE FURNITURE.SPECIFICATION
(
  SPECIFICATIONID          NUMBER               NOT NULL,
  NAME                     VARCHAR2(80 BYTE)    NOT NULL,
  DESCRIPTION              VARCHAR2(400 BYTE),
  ACTIVE                   INTEGER              DEFAULT 1                     NOT NULL,
  CREATED_TIMESTAMP        TIMESTAMP(6)         DEFAULT SYSDATE,
  MODIFIED_TIMESTAMP       TIMESTAMP(6)         DEFAULT SYSDATE,
  FREETEXT                 INTEGER              NOT NULL,
  SPECIFICATIONCATEGORYID  NUMBER               NOT NULL,
  MULTIPLEVALUES           INTEGER              DEFAULT 0                     NOT NULL,
  COLOR                    INTEGER              DEFAULT 0,
  DIMENSION                INTEGER              DEFAULT 0
)
TABLESPACE FURNITURE_TABLES
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE FURNITURE.IMAGEVALUE
(
  IMAGEID             NUMBER                    NOT NULL,
  DESCRIPTION         VARCHAR2(80 BYTE),
  ACTIVE              INTEGER                   DEFAULT 1                     NOT NULL,
  IMAGE               BLOB,
  VALUEID             NUMBER                    NOT NULL,
  PATH                VARCHAR2(100 BYTE)        NOT NULL,
  PRIMARY             INTEGER,
  FILENAME            VARCHAR2(100 BYTE)        NOT NULL,
  CREATED_TIMESTAMP   TIMESTAMP(6)              DEFAULT SYSDATE,
  MODIFIED_TIMESTAMP  TIMESTAMP(6)              DEFAULT SYSDATE
)
TABLESPACE FURNITURE_BLOB
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
LOB (IMAGE) STORE AS 
      ( TABLESPACE  FURNITURE_TABLES 
        ENABLE      STORAGE IN ROW
        CHUNK       8192
        RETENTION
        NOCACHE
        INDEX       (
          TABLESPACE FURNITURE_TABLES
          STORAGE    (
                      INITIAL          64K
                      NEXT             1
                      MINEXTENTS       1
                      MAXEXTENTS       UNLIMITED
                      PCTINCREASE      0
                      BUFFER_POOL      DEFAULT
                     ))
        STORAGE    (
                    INITIAL          64K
                    MINEXTENTS       1
                    MAXEXTENTS       UNLIMITED
                    PCTINCREASE      0
                    BUFFER_POOL      DEFAULT
                   )
      )
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE FURNITURE.PRODUCT
(
  PRODUCTID           NUMBER                    NOT NULL,
  NAME                VARCHAR2(80 BYTE)         NOT NULL,
  DESCRIPTION         VARCHAR2(400 BYTE),
  ACTIVE              INTEGER                   DEFAULT 1,
  CREATED_TIMESTAMP   TIMESTAMP(6)              DEFAULT SYSDATE,
  MODIFIED_TIMESTAMP  TIMESTAMP(6)              DEFAULT SYSDATE,
  SUBPRODUCT          INTEGER                   DEFAULT 0,
  PARENTPRODUCTID     NUMBER,
  ITEMID              NUMBER,
  PRODUCTCATEGORYID   NUMBER,
  CREATEDDATE         DATE
)
TABLESPACE FURNITURE_TABLES
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE FURNITURE.COMPANYPRODUCT
(
  ID                  NUMBER                    NOT NULL,
  COMPANYID           NUMBER                    NOT NULL,
  PRODUCTID           NUMBER                    NOT NULL,
  ACTIVE              INTEGER                   DEFAULT 1,
  CREATED_TIMESTAMP   TIMESTAMP(6)              DEFAULT SYSDATE,
  MODIFIED_TIMESTAMP  TIMESTAMP(6)              DEFAULT SYSDATE
)
TABLESPACE FURNITURE_TABLES
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE FURNITURE.IMAGEPRODUCT
(
  IMAGEID             NUMBER                    NOT NULL,
  DESCRIPTION         VARCHAR2(400 BYTE),
  ACTIVE              INTEGER                   DEFAULT 1                     NOT NULL,
  IMAGE               BLOB,
  PRODUCTID           NUMBER                    NOT NULL,
  PATH                VARCHAR2(100 BYTE)        NOT NULL,
  PRIMARY             INTEGER,
  FILENAME            VARCHAR2(100 BYTE)        NOT NULL,
  CREATED_TIMESTAMP   TIMESTAMP(6)              DEFAULT SYSDATE,
  MODIFIED_TIMESTAMP  TIMESTAMP(6)              DEFAULT SYSDATE
)
TABLESPACE FURNITURE_BLOB
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
LOB (IMAGE) STORE AS 
      ( TABLESPACE  FURNITURE_BLOB 
        ENABLE      STORAGE IN ROW
        CHUNK       8192
        RETENTION
        NOCACHE
        INDEX       (
          TABLESPACE FURNITURE_BLOB
          STORAGE    (
                      INITIAL          64K
                      NEXT             1
                      MINEXTENTS       1
                      MAXEXTENTS       UNLIMITED
                      PCTINCREASE      0
                      BUFFER_POOL      DEFAULT
                     ))
        STORAGE    (
                    INITIAL          64K
                    MINEXTENTS       1
                    MAXEXTENTS       UNLIMITED
                    PCTINCREASE      0
                    BUFFER_POOL      DEFAULT
                   )
      )
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE FURNITURE.SPECIFICATIONVALUE
(
  ID                  NUMBER                    NOT NULL,
  SPECIFICATIONID     NUMBER                    NOT NULL,
  VALUEID             NUMBER                    NOT NULL,
  ACTIVE              INTEGER                   DEFAULT 1                     NOT NULL,
  ORDERED             INTEGER,
  CREATED_TIMESTAMP   TIMESTAMP(6)              DEFAULT SYSDATE,
  MODIFIED_TIMESTAMP  TIMESTAMP(6)              DEFAULT SYSDATE
)
TABLESPACE FURNITURE_TABLES
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE FURNITURE.CATPROD
(
  ID                  NUMBER                    NOT NULL,
  CATEGORYID          NUMBER                    NOT NULL,
  PRODUCTID           NUMBER                    NOT NULL,
  ACTIVE              INTEGER                   DEFAULT 1                     NOT NULL,
  ORDERED             INTEGER,
  CREATED_TIMESTAMP   TIMESTAMP(6)              DEFAULT SYSDATE,
  MODIFIED_TIMESTAMP  TIMESTAMP(6)              DEFAULT SYSDATE
)
TABLESPACE FURNITURE_TABLES
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE FURNITURE.CONNPRODUCT
(
  ID                  NUMBER                    NOT NULL,
  PARENTPRODUCTID     NUMBER                    NOT NULL,
  SUBPRODUCTID        NUMBER                    NOT NULL,
  ACTIVE              INTEGER                   DEFAULT 1                     NOT NULL,
  SHOWABLE            INTEGER                   DEFAULT 1                     NOT NULL,
  CREATED_TIMESTAMP   TIMESTAMP(6)              DEFAULT SYSDATE,
  MODIFIED_TIMESTAMP  TIMESTAMP(6)              DEFAULT SYSDATE
)
TABLESPACE FURNITURE_TABLES
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE UNIQUE INDEX FURNITURE.ACTION_PK ON FURNITURE.ACTION
(ACTIONID)
LOGGING
TABLESPACE FURNITURE_INDEX
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


CREATE UNIQUE INDEX FURNITURE.CATALOGUEPRODUCTLINE_PK ON FURNITURE.CATALOGUEPRODUCTLINE
(ID)
LOGGING
TABLESPACE FURNITURE_INDEX
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


CREATE UNIQUE INDEX FURNITURE.CATPROD_PK ON FURNITURE.CATPROD
(ID)
LOGGING
TABLESPACE FURNITURE_TABLES
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


CREATE UNIQUE INDEX FURNITURE.COMPANYPRODUCT_PK ON FURNITURE.COMPANYPRODUCT
(ID)
LOGGING
TABLESPACE FURNITURE_INDEX
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


CREATE UNIQUE INDEX FURNITURE.CONNPRODUCT_PK ON FURNITURE.CONNPRODUCT
(ID)
LOGGING
TABLESPACE FURNITURE_TABLES
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


CREATE UNIQUE INDEX FURNITURE.IMAGEPRODUCT_PK ON FURNITURE.IMAGEPRODUCT
(IMAGEID)
LOGGING
TABLESPACE FURNITURE_INDEX
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


CREATE UNIQUE INDEX FURNITURE.IMAGEVALUE_PK ON FURNITURE.IMAGEVALUE
(IMAGEID)
LOGGING
TABLESPACE FURNITURE_TABLES
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


CREATE UNIQUE INDEX FURNITURE.PRODUCT_PK ON FURNITURE.PRODUCT
(PRODUCTID)
LOGGING
TABLESPACE FURNITURE_INDEX
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


CREATE UNIQUE INDEX FURNITURE.SPECIFICATION_PK ON FURNITURE.SPECIFICATION
(SPECIFICATIONID)
LOGGING
TABLESPACE FURNITURE_TABLES
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


CREATE UNIQUE INDEX FURNITURE.SPECIFICATIONVALUE_PK ON FURNITURE.SPECIFICATIONVALUE
(ID)
LOGGING
TABLESPACE FURNITURE_TABLES
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


CREATE UNIQUE INDEX FURNITURE.USERROLES_PK ON FURNITURE.USERROLES
(ID)
LOGGING
TABLESPACE FURNITURE_INDEX
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


CREATE INDEX FURNITURE.USERROLE_USERID_INDX ON FURNITURE.USERROLES
(USERID)
LOGGING
TABLESPACE FURNITURE_INDEX
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


CREATE OR REPLACE TRIGGER FURNITURE."BI_USERROLES"   
  before insert on "USERROLES"               
  for each row
begin   
    select "USERROLES_SEQ".nextval into :NEW.ID from dual; 
end;
/


CREATE OR REPLACE TRIGGER FURNITURE."BI_PRODUCT"   
  before insert on "PRODUCT"               
  for each row
begin   
    select "PRODUCT_SEQ".nextval into :NEW.PRODUCTID from dual; 
end;
/

ALTER TRIGGER FURNITURE.BI_PRODUCT DISABLE;


CREATE OR REPLACE TRIGGER FURNITURE."BI_COMPANYPRODUCT"   
  before insert on "COMPANYPRODUCT"               
  for each row
begin   
    select "COMPANYPRODUCT_SEQ".nextval into :NEW.ID from dual; 
end;
/


CREATE OR REPLACE TRIGGER FURNITURE."BI_IMAGEPRODUCT"   
  before insert on "IMAGEPRODUCT"               
  for each row
begin   
    select "IMAGEPRODUCT_SEQ".nextval into :NEW.IMAGEID from dual; 
end;
/


CREATE TABLE FURNITURE.ITEMSPECIFICATION
(
  ID                  NUMBER                    NOT NULL,
  ITEMID              NUMBER                    NOT NULL,
  SPECIFICATIONID     NUMBER                    NOT NULL,
  ACTIVE              INTEGER                   DEFAULT 1                     NOT NULL,
  CREATED_TIMESTAMP   TIMESTAMP(6)              DEFAULT SYSDATE,
  MODIFIED_TIMESTAMP  TIMESTAMP(6)              DEFAULT SYSDATE,
  ORDERED             INTEGER
)
TABLESPACE FURNITURE_TABLES
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE FURNITURE.PRODUCTLINEPRODUCT
(
  ID                  NUMBER                    NOT NULL,
  PRODUCTLINEID       NUMBER                    NOT NULL,
  PRODUCTID           NUMBER                    NOT NULL,
  ACTIVE              INTEGER                   DEFAULT 1,
  CREATED_TIMESTAMP   TIMESTAMP(6)              DEFAULT SYSDATE,
  MODIFIED_TIMESTAMP  TIMESTAMP(6)              DEFAULT SYSDATE
)
TABLESPACE FURNITURE_TABLES
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE FURNITURE.VIDEOPRODUCT
(
  VIDEOID             NUMBER                    NOT NULL,
  DESCRIPTION         VARCHAR2(400 BYTE),
  ACTIVE              INTEGER                   DEFAULT 1                     NOT NULL,
  VIDEO               BLOB,
  PRODUCTID           NUMBER                    NOT NULL,
  PATH                VARCHAR2(100 BYTE)        NOT NULL,
  PRIMARY             INTEGER,
  FILENAME            VARCHAR2(100 BYTE)        NOT NULL,
  CREATED_TIMESTAMP   TIMESTAMP(6)              DEFAULT SYSDATE,
  MODIFIED_TIMESTAMP  TIMESTAMP(6)              DEFAULT SYSDATE
)
TABLESPACE FURNITURE_BLOB
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
LOB (VIDEO) STORE AS 
      ( TABLESPACE  FURNITURE_BLOB 
        ENABLE      STORAGE IN ROW
        CHUNK       8192
        RETENTION
        NOCACHE
        INDEX       (
          TABLESPACE FURNITURE_BLOB
          STORAGE    (
                      INITIAL          64K
                      NEXT             1
                      MINEXTENTS       1
                      MAXEXTENTS       UNLIMITED
                      PCTINCREASE      0
                      BUFFER_POOL      DEFAULT
                     ))
        STORAGE    (
                    INITIAL          64K
                    MINEXTENTS       1
                    MAXEXTENTS       UNLIMITED
                    PCTINCREASE      0
                    BUFFER_POOL      DEFAULT
                   )
      )
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE FURNITURE.PRODUCTSPECIFICATION
(
  ID                  NUMBER                    NOT NULL,
  PRODUCTID           NUMBER                    NOT NULL,
  SPECIFICATIONID     NUMBER                    NOT NULL,
  FREETEXT            INTEGER,
  ORDERED             INTEGER,
  ACTIVE              INTEGER                   DEFAULT 1                     NOT NULL,
  CREATED_TIMESTAMP   TIMESTAMP(6)              DEFAULT SYSDATE,
  MODIFIED_TIMESTAMP  TIMESTAMP(6)              DEFAULT SYSDATE
)
TABLESPACE FURNITURE_TABLES
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE FURNITURE.PRODUCTVALUE
(
  ID                      NUMBER                NOT NULL,
  PRODUCTSPECIFICATIONID  NUMBER                NOT NULL,
  VALUEID                 NUMBER,
  VALUE                   VARCHAR2(200 BYTE),
  ORDERED                 INTEGER,
  ACTIVE                  INTEGER               DEFAULT 1                     NOT NULL,
  CREATED_TIMESTAMP       TIMESTAMP(6)          DEFAULT SYSDATE,
  MODIFIED_TIMESTAMP      TIMESTAMP(6)          DEFAULT SYSDATE,
  MEASURMENTID            NUMBER
)
TABLESPACE FURNITURE_TABLES
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE FURNITURE.AUDITING
(
  AUDITINGID       NUMBER                       NOT NULL,
  USERID           NUMBER                       NOT NULL,
  ACTIONID         NUMBER                       NOT NULL,
  COMPANYID        NUMBER,
  PRODUCTID        NUMBER,
  PRODUCTLINEID    NUMBER,
  CATALOGUEID      NUMBER,
  ITEMID           NUMBER,
  SPECIFICATIONID  NUMBER,
  COMMENTS         VARCHAR2(400 BYTE),
  ACTIONDATE       DATE                         NOT NULL,
  ACTIONTIME       TIMESTAMP(6)                 DEFAULT SYSDATE
)
TABLESPACE FURNITURE_TABLES
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE FURNITURE.PRICE
(
  PRICEID             NUMBER                    NOT NULL,
  AMOUNT              NUMBER(22,2)              NOT NULL,
  PRODUCTID           NUMBER                    NOT NULL,
  COMPANYID           NUMBER                    NOT NULL,
  CATALOGUEID         NUMBER,
  ACTIVE              INTEGER                   DEFAULT 1                     NOT NULL,
  CREATED_TIMESTAMP   TIMESTAMP(6)              DEFAULT SYSDATE,
  MODIFIED_TIMESTAMP  TIMESTAMP(6)              DEFAULT SYSDATE,
  CURRENCYID          NUMBER                    NOT NULL,
  PRICEDATE           DATE,
  DISCOUNT            NUMBER(22,2)              DEFAULT 0                     NOT NULL,
  INITIALPRICE        NUMBER(22,2)              NOT NULL
)
TABLESPACE FURNITURE_TABLES
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE UNIQUE INDEX FURNITURE.AUDITING_PK ON FURNITURE.AUDITING
(AUDITINGID)
LOGGING
TABLESPACE FURNITURE_TABLES
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


CREATE UNIQUE INDEX FURNITURE.ITEMSPECIFICATION_PK ON FURNITURE.ITEMSPECIFICATION
(ID)
LOGGING
TABLESPACE FURNITURE_TABLES
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


CREATE UNIQUE INDEX FURNITURE.PRICE_PK ON FURNITURE.PRICE
(PRICEID)
LOGGING
TABLESPACE FURNITURE_TABLES
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


CREATE UNIQUE INDEX FURNITURE.PRODUCTLINEPRODUCT_PK ON FURNITURE.PRODUCTLINEPRODUCT
(ID)
LOGGING
TABLESPACE FURNITURE_INDEX
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


CREATE UNIQUE INDEX FURNITURE.PRODUCTSPECIFICATION_PK ON FURNITURE.PRODUCTSPECIFICATION
(ID)
LOGGING
TABLESPACE FURNITURE_TABLES
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


CREATE UNIQUE INDEX FURNITURE.PRODUCTVALUE_PK ON FURNITURE.PRODUCTVALUE
(ID)
LOGGING
TABLESPACE FURNITURE_TABLES
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


CREATE UNIQUE INDEX FURNITURE.VIDEOPRODUCT_PK ON FURNITURE.VIDEOPRODUCT
(VIDEOID)
LOGGING
TABLESPACE FURNITURE_TABLES
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


CREATE OR REPLACE TRIGGER FURNITURE."BI_AUDITING"   
  before insert on "AUDITING"               
  for each row
begin   
    select "AUDITING_SEQ".nextval into :NEW.AUDITINGID from dual; 
end;
/


CREATE OR REPLACE TRIGGER FURNITURE."BI_PRODUCTLINEPRODUCT"   
  before insert on "PRODUCTLINEPRODUCT"               
  for each row
begin   
    select "PRODUCTLINEPRODUCT_SEQ".nextval into :NEW.ID from dual; 
end;
/


CREATE OR REPLACE TRIGGER FURNITURE."BI_PRODUCTSPECIFICATION"   
  before insert on "PRODUCTSPECIFICATION"               
  for each row
begin   
    select "PRODUCTSPECIFICATION_SEQ".nextval into :NEW.ID from dual; 
end;
/

ALTER TRIGGER FURNITURE.BI_PRODUCTSPECIFICATION DISABLE;


CREATE OR REPLACE TRIGGER FURNITURE."BI_PRODUCTVALUE"   
  before insert on "PRODUCTVALUE"               
  for each row
begin   
    select "PRODUCTVALUE_SEQ".nextval into :NEW.ID from dual; 
end;
/


CREATE OR REPLACE TRIGGER FURNITURE."BI_PRICE"   
  before insert on "PRICE"               
  for each row
begin   
    select "PRICE_SEQ".nextval into :NEW.PRICEID from dual; 
end;
/


CREATE OR REPLACE TRIGGER FURNITURE."BI_VIDEOPRODUCT"   
  before insert on "VIDEOPRODUCT"               
  for each row
begin   
    select "VIDEOPRODUCT_SEQ".nextval into :NEW.VIDEOID from dual; 
end;
/


ALTER TABLE FURNITURE.COMPANY ADD (
  CONSTRAINT COMPANY_PK
 PRIMARY KEY
 (COMPANYID)
    USING INDEX 
    TABLESPACE FURNITURE_INDEX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                PCTINCREASE      0
               ));

ALTER TABLE FURNITURE.CURRENCY ADD (
  CONSTRAINT CURRENCY_PK
 PRIMARY KEY
 (CURRENCYID)
    USING INDEX 
    TABLESPACE FURNITURE_TABLES
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                PCTINCREASE      0
               ));

ALTER TABLE FURNITURE.ADDRESS ADD (
  CONSTRAINT ADDRESS_PK
 PRIMARY KEY
 (ADDRESSID)
    USING INDEX 
    TABLESPACE FURNITURE_INDEX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                PCTINCREASE      0
               ));

ALTER TABLE FURNITURE.SPECIFICATIONCATEGORY ADD (
  CONSTRAINT SPECIFICATIONCATEGORY_PK
 PRIMARY KEY
 (SPECIFICATIONCATEGORYID)
    USING INDEX 
    TABLESPACE FURNITURE_TABLES
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                PCTINCREASE      0
               ));

ALTER TABLE FURNITURE.PRODUCTCATEGORY ADD (
  CONSTRAINT PRODUCTCATEGORY_PK
 PRIMARY KEY
 (PRODUCTCATEGORYID)
    USING INDEX 
    TABLESPACE FURNITURE_TABLES
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                PCTINCREASE      0
               ));

ALTER TABLE FURNITURE.ROLE ADD (
  CONSTRAINT ROLE_PK
 PRIMARY KEY
 (ROLEID)
    USING INDEX 
    TABLESPACE FURNITURE_INDEX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                PCTINCREASE      0
               ));

ALTER TABLE FURNITURE.ACTIONSCATEGORY ADD (
  CONSTRAINT ACTIONSCATEGORY_PK
 PRIMARY KEY
 (CATEGORYID)
    USING INDEX 
    TABLESPACE FURNITURE_INDEX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                PCTINCREASE      0
               ));

ALTER TABLE FURNITURE.USERS ADD (
  CONSTRAINT USERS_PK
 PRIMARY KEY
 (USERID)
    USING INDEX 
    TABLESPACE FURNITURE_INDEX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                PCTINCREASE      0
               ));

ALTER TABLE FURNITURE.CATALOGUE ADD (
  CONSTRAINT CATALOGUE_PK
 PRIMARY KEY
 (CATALOGUEID)
    USING INDEX 
    TABLESPACE FURNITURE_INDEX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                PCTINCREASE      0
               ));

ALTER TABLE FURNITURE.PRODUCTLINE ADD (
  CONSTRAINT PRODUCTLINE_PK
 PRIMARY KEY
 (PRODUCTLINEID)
    USING INDEX 
    TABLESPACE FURNITURE_INDEX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                PCTINCREASE      0
               ));

ALTER TABLE FURNITURE.ITEM ADD (
  CONSTRAINT ITEM_PK
 PRIMARY KEY
 (ITEMID)
    USING INDEX 
    TABLESPACE FURNITURE_TABLES
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                PCTINCREASE      0
               ));

ALTER TABLE FURNITURE.COMPANYCATALOGUE ADD (
  CONSTRAINT COMPANYCATALOGUE_PK
 PRIMARY KEY
 (ID)
    USING INDEX 
    TABLESPACE FURNITURE_INDEX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                PCTINCREASE      0
               ));

ALTER TABLE FURNITURE.SVALUE ADD (
  CONSTRAINT VALUE_PK
 PRIMARY KEY
 (VALUEID)
    USING INDEX 
    TABLESPACE FURNITURE_TABLES
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                PCTINCREASE      0
               ));

ALTER TABLE FURNITURE.IMAGECATALOGUE ADD (
  CONSTRAINT IMAGECATALOGUE_PK
 PRIMARY KEY
 (IMAGEID)
    USING INDEX 
    TABLESPACE FURNITURE_INDEX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                PCTINCREASE      0
               ));

ALTER TABLE FURNITURE.IMAGEPRODUCTLINE ADD (
  CONSTRAINT IMAGEPRODUCTLINE_PK
 PRIMARY KEY
 (IMAGEID)
    USING INDEX 
    TABLESPACE FURNITURE_INDEX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                PCTINCREASE      0
               ));

ALTER TABLE FURNITURE.MEASURMENT ADD (
  CONSTRAINT MEASURMENT_PK
 PRIMARY KEY
 (MEASURMENTID)
    USING INDEX 
    TABLESPACE FURNITURE_TABLES
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                PCTINCREASE      0
               ));

ALTER TABLE FURNITURE.CATEGORY ADD (
  PRIMARY KEY
 (CATEGORYID)
    USING INDEX 
    TABLESPACE FURNITURE_TABLES
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                PCTINCREASE      0
               ));

ALTER TABLE FURNITURE.COMPCAT ADD (
  CONSTRAINT COMPCAT_PK
 PRIMARY KEY
 (ID)
    USING INDEX 
    TABLESPACE FURNITURE_TABLES
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                PCTINCREASE      0
               ));

ALTER TABLE FURNITURE.ACTION ADD (
  CONSTRAINT ACTION_PK
 PRIMARY KEY
 (ACTIONID)
    USING INDEX 
    TABLESPACE FURNITURE_INDEX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                PCTINCREASE      0
               ));

ALTER TABLE FURNITURE.USERROLES ADD (
  CONSTRAINT USERROLES_PK
 PRIMARY KEY
 (ID)
    USING INDEX 
    TABLESPACE FURNITURE_INDEX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                PCTINCREASE      0
               ));

ALTER TABLE FURNITURE.CATALOGUEPRODUCTLINE ADD (
  CONSTRAINT CATALOGUEPRODUCTLINE_PK
 PRIMARY KEY
 (ID)
    USING INDEX 
    TABLESPACE FURNITURE_INDEX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                PCTINCREASE      0
               ));

ALTER TABLE FURNITURE.SPECIFICATION ADD (
  CONSTRAINT SPECIFICATION_PK
 PRIMARY KEY
 (SPECIFICATIONID)
    USING INDEX 
    TABLESPACE FURNITURE_TABLES
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                PCTINCREASE      0
               ));

ALTER TABLE FURNITURE.IMAGEVALUE ADD (
  CONSTRAINT IMAGEVALUE_PK
 PRIMARY KEY
 (IMAGEID)
    USING INDEX 
    TABLESPACE FURNITURE_TABLES
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                PCTINCREASE      0
               ));

ALTER TABLE FURNITURE.PRODUCT ADD (
  CONSTRAINT PRODUCT_PK
 PRIMARY KEY
 (PRODUCTID)
    USING INDEX 
    TABLESPACE FURNITURE_INDEX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                PCTINCREASE      0
               ));

ALTER TABLE FURNITURE.COMPANYPRODUCT ADD (
  CONSTRAINT COMPANYPRODUCT_PK
 PRIMARY KEY
 (ID)
    USING INDEX 
    TABLESPACE FURNITURE_INDEX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                PCTINCREASE      0
               ));

ALTER TABLE FURNITURE.IMAGEPRODUCT ADD (
  CONSTRAINT IMAGEPRODUCT_PK
 PRIMARY KEY
 (IMAGEID)
    USING INDEX 
    TABLESPACE FURNITURE_INDEX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                PCTINCREASE      0
               ));

ALTER TABLE FURNITURE.SPECIFICATIONVALUE ADD (
  CONSTRAINT SPECIFICATIONVALUE_PK
 PRIMARY KEY
 (ID)
    USING INDEX 
    TABLESPACE FURNITURE_TABLES
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                PCTINCREASE      0
               ));

ALTER TABLE FURNITURE.CATPROD ADD (
  CONSTRAINT CATPROD_PK
 PRIMARY KEY
 (ID)
    USING INDEX 
    TABLESPACE FURNITURE_TABLES
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                PCTINCREASE      0
               ));

ALTER TABLE FURNITURE.CONNPRODUCT ADD (
  CONSTRAINT CONNPRODUCT_PK
 PRIMARY KEY
 (ID)
    USING INDEX 
    TABLESPACE FURNITURE_TABLES
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                PCTINCREASE      0
               ));

ALTER TABLE FURNITURE.ITEMSPECIFICATION ADD (
  CONSTRAINT ITEMSPECIFICATION_PK
 PRIMARY KEY
 (ID)
    USING INDEX 
    TABLESPACE FURNITURE_TABLES
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                PCTINCREASE      0
               ));

ALTER TABLE FURNITURE.PRODUCTLINEPRODUCT ADD (
  CONSTRAINT PRODUCTLINEPRODUCT_PK
 PRIMARY KEY
 (ID)
    USING INDEX 
    TABLESPACE FURNITURE_INDEX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                PCTINCREASE      0
               ));

ALTER TABLE FURNITURE.VIDEOPRODUCT ADD (
  CONSTRAINT VIDEOPRODUCT_PK
 PRIMARY KEY
 (VIDEOID)
    USING INDEX 
    TABLESPACE FURNITURE_TABLES
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                PCTINCREASE      0
               ));

ALTER TABLE FURNITURE.PRODUCTSPECIFICATION ADD (
  CONSTRAINT PRODUCTSPECIFICATION_PK
 PRIMARY KEY
 (ID)
    USING INDEX 
    TABLESPACE FURNITURE_TABLES
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                PCTINCREASE      0
               ));

ALTER TABLE FURNITURE.PRODUCTVALUE ADD (
  CONSTRAINT PRODUCTVALUE_PK
 PRIMARY KEY
 (ID)
    USING INDEX 
    TABLESPACE FURNITURE_TABLES
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                PCTINCREASE      0
               ));

ALTER TABLE FURNITURE.AUDITING ADD (
  CONSTRAINT AUDITING_PK
 PRIMARY KEY
 (AUDITINGID)
    USING INDEX 
    TABLESPACE FURNITURE_TABLES
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                PCTINCREASE      0
               ));

ALTER TABLE FURNITURE.PRICE ADD (
  CONSTRAINT PRICE_PK
 PRIMARY KEY
 (PRICEID)
    USING INDEX 
    TABLESPACE FURNITURE_TABLES
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                PCTINCREASE      0
               ));

ALTER TABLE FURNITURE.ADDRESS ADD (
  CONSTRAINT ADDRESS_COMPANY_FK 
 FOREIGN KEY (COMPANYID) 
 REFERENCES FURNITURE.COMPANY (COMPANYID));

ALTER TABLE FURNITURE.USERS ADD (
  CONSTRAINT USER_COMPANY_FK 
 FOREIGN KEY (COMPANYID) 
 REFERENCES FURNITURE.COMPANY (COMPANYID),
  CONSTRAINT USER_ROLE_FK 
 FOREIGN KEY (ROLEID) 
 REFERENCES FURNITURE.ROLE (ROLEID));

ALTER TABLE FURNITURE.COMPANYCATALOGUE ADD (
  CONSTRAINT COMPANY_CATALOGUE_FK1 
 FOREIGN KEY (COMPANYID) 
 REFERENCES FURNITURE.COMPANY (COMPANYID),
  CONSTRAINT COMPANY_CATALOGUE_FK2 
 FOREIGN KEY (CATALOGUEID) 
 REFERENCES FURNITURE.CATALOGUE (CATALOGUEID));

ALTER TABLE FURNITURE.IMAGECATALOGUE ADD (
  CONSTRAINT IMAGE_CATALOGUE_FK 
 FOREIGN KEY (CATALOGUEID) 
 REFERENCES FURNITURE.CATALOGUE (CATALOGUEID));

ALTER TABLE FURNITURE.IMAGEPRODUCTLINE ADD (
  CONSTRAINT IMAGE_PRODUCTLINE_FK 
 FOREIGN KEY (PRODUCTLINEID) 
 REFERENCES FURNITURE.PRODUCTLINE (PRODUCTLINEID));

ALTER TABLE FURNITURE.CATEGORY ADD (
  CONSTRAINT CAT_PARENTCAT_FK 
 FOREIGN KEY (PARENTCATEGORYID) 
 REFERENCES FURNITURE.CATEGORY (CATEGORYID));

ALTER TABLE FURNITURE.COMPCAT ADD (
  CONSTRAINT COMPANY_FK 
 FOREIGN KEY (COMPANYID) 
 REFERENCES FURNITURE.COMPANY (COMPANYID),
  CONSTRAINT CATEGORY_FK 
 FOREIGN KEY (CATEGORYID) 
 REFERENCES FURNITURE.CATEGORY (CATEGORYID));

ALTER TABLE FURNITURE.ACTION ADD (
  CONSTRAINT ACTION_CAT_FK 
 FOREIGN KEY (CATEGORYID) 
 REFERENCES FURNITURE.ACTIONSCATEGORY (CATEGORYID));

ALTER TABLE FURNITURE.USERROLES ADD (
  CONSTRAINT USERROLE_USER 
 FOREIGN KEY (USERID) 
 REFERENCES FURNITURE.USERS (USERID),
  CONSTRAINT USERROLE_ROLE 
 FOREIGN KEY (ROLEID) 
 REFERENCES FURNITURE.ROLE (ROLEID));

ALTER TABLE FURNITURE.CATALOGUEPRODUCTLINE ADD (
  CONSTRAINT CATALOGUE_PRODUCTLINE_FK1 
 FOREIGN KEY (CATALOGUEID) 
 REFERENCES FURNITURE.CATALOGUE (CATALOGUEID),
  CONSTRAINT CATALOGUE_PRODUCTLINE_FK2 
 FOREIGN KEY (PRODUCTLINEID) 
 REFERENCES FURNITURE.PRODUCTLINE (PRODUCTLINEID));

ALTER TABLE FURNITURE.SPECIFICATION ADD (
  CONSTRAINT SPEC_CAT_FK 
 FOREIGN KEY (SPECIFICATIONCATEGORYID) 
 REFERENCES FURNITURE.SPECIFICATIONCATEGORY (SPECIFICATIONCATEGORYID));

ALTER TABLE FURNITURE.IMAGEVALUE ADD (
  CONSTRAINT IMAGE_VALUE_FK 
 FOREIGN KEY (VALUEID) 
 REFERENCES FURNITURE.SVALUE (VALUEID));

ALTER TABLE FURNITURE.PRODUCT ADD (
  CONSTRAINT PRODUCT_ITEM_FK 
 FOREIGN KEY (ITEMID) 
 REFERENCES FURNITURE.ITEM (ITEMID),
  CONSTRAINT PRODUCT_CATEGORY_FK 
 FOREIGN KEY (PRODUCTCATEGORYID) 
 REFERENCES FURNITURE.PRODUCTCATEGORY (PRODUCTCATEGORYID),
  CONSTRAINT PARENTPRODUCT_FK 
 FOREIGN KEY (PARENTPRODUCTID) 
 REFERENCES FURNITURE.PRODUCT (PRODUCTID));

ALTER TABLE FURNITURE.COMPANYPRODUCT ADD (
  CONSTRAINT COMPANY_PRODUCT_FK1 
 FOREIGN KEY (COMPANYID) 
 REFERENCES FURNITURE.COMPANY (COMPANYID),
  CONSTRAINT COMPANY_PRODUCT_FK2 
 FOREIGN KEY (PRODUCTID) 
 REFERENCES FURNITURE.PRODUCT (PRODUCTID));

ALTER TABLE FURNITURE.IMAGEPRODUCT ADD (
  CONSTRAINT IMAGE_PRODUCT_FK 
 FOREIGN KEY (PRODUCTID) 
 REFERENCES FURNITURE.PRODUCT (PRODUCTID));

ALTER TABLE FURNITURE.SPECIFICATIONVALUE ADD (
  CONSTRAINT SPEC_VALUE_FK1 
 FOREIGN KEY (SPECIFICATIONID) 
 REFERENCES FURNITURE.SPECIFICATION (SPECIFICATIONID),
  CONSTRAINT SPEC_VALUE_FK2 
 FOREIGN KEY (VALUEID) 
 REFERENCES FURNITURE.SVALUE (VALUEID));

ALTER TABLE FURNITURE.CATPROD ADD (
  CONSTRAINT CAT_FK 
 FOREIGN KEY (CATEGORYID) 
 REFERENCES FURNITURE.CATEGORY (CATEGORYID),
  CONSTRAINT PROD_FK 
 FOREIGN KEY (PRODUCTID) 
 REFERENCES FURNITURE.PRODUCT (PRODUCTID));

ALTER TABLE FURNITURE.CONNPRODUCT ADD (
  CONSTRAINT PARENT_PRODUCT_FK 
 FOREIGN KEY (PARENTPRODUCTID) 
 REFERENCES FURNITURE.PRODUCT (PRODUCTID),
  CONSTRAINT SUBPRODUCT_FK 
 FOREIGN KEY (SUBPRODUCTID) 
 REFERENCES FURNITURE.PRODUCT (PRODUCTID));

ALTER TABLE FURNITURE.ITEMSPECIFICATION ADD (
  CONSTRAINT ITEM_FK 
 FOREIGN KEY (ITEMID) 
 REFERENCES FURNITURE.ITEM (ITEMID),
  CONSTRAINT SPECIFICATION_FK 
 FOREIGN KEY (SPECIFICATIONID) 
 REFERENCES FURNITURE.SPECIFICATION (SPECIFICATIONID));

ALTER TABLE FURNITURE.PRODUCTLINEPRODUCT ADD (
  CONSTRAINT PRODUCTLINE_PRODUCT_FK1 
 FOREIGN KEY (PRODUCTLINEID) 
 REFERENCES FURNITURE.PRODUCTLINE (PRODUCTLINEID),
  CONSTRAINT PRODUCTLINE_PRODUCT_FK2 
 FOREIGN KEY (PRODUCTID) 
 REFERENCES FURNITURE.PRODUCT (PRODUCTID));

ALTER TABLE FURNITURE.VIDEOPRODUCT ADD (
  CONSTRAINT VIDEO_IMAGE_FK 
 FOREIGN KEY (PRODUCTID) 
 REFERENCES FURNITURE.PRODUCT (PRODUCTID));

ALTER TABLE FURNITURE.PRODUCTSPECIFICATION ADD (
  CONSTRAINT PRODUCT_SPEC_FK1 
 FOREIGN KEY (PRODUCTID) 
 REFERENCES FURNITURE.PRODUCT (PRODUCTID),
  CONSTRAINT PRODUCT_SPEC_FK2 
 FOREIGN KEY (SPECIFICATIONID) 
 REFERENCES FURNITURE.SPECIFICATION (SPECIFICATIONID));

ALTER TABLE FURNITURE.PRODUCTVALUE ADD (
  CONSTRAINT PRODUCTSPEC_VALUE_FK1 
 FOREIGN KEY (PRODUCTSPECIFICATIONID) 
 REFERENCES FURNITURE.PRODUCTSPECIFICATION (ID),
  CONSTRAINT PRODUCTSPEC_VALUE_FK2 
 FOREIGN KEY (VALUEID) 
 REFERENCES FURNITURE.SVALUE (VALUEID),
  FOREIGN KEY (MEASURMENTID) 
 REFERENCES FURNITURE.MEASURMENT (MEASURMENTID));

ALTER TABLE FURNITURE.AUDITING ADD (
  CONSTRAINT AUDIT_USER_FK 
 FOREIGN KEY (USERID) 
 REFERENCES FURNITURE.USERS (USERID),
  CONSTRAINT AUDIT_ACTION_FK 
 FOREIGN KEY (ACTIONID) 
 REFERENCES FURNITURE.ACTION (ACTIONID),
  CONSTRAINT AUDIT_COMPANY_FK 
 FOREIGN KEY (COMPANYID) 
 REFERENCES FURNITURE.COMPANY (COMPANYID),
  CONSTRAINT AUDIT_PRODUCT_FK 
 FOREIGN KEY (PRODUCTID) 
 REFERENCES FURNITURE.PRODUCT (PRODUCTID),
  CONSTRAINT AUDIT_LINE_FK 
 FOREIGN KEY (PRODUCTLINEID) 
 REFERENCES FURNITURE.PRODUCTLINE (PRODUCTLINEID),
  CONSTRAINT AUDIT_CATALOGUE_FK 
 FOREIGN KEY (CATALOGUEID) 
 REFERENCES FURNITURE.CATALOGUE (CATALOGUEID),
  CONSTRAINT AUDIT_ITEM_FK 
 FOREIGN KEY (ITEMID) 
 REFERENCES FURNITURE.ITEM (ITEMID),
  CONSTRAINT AUDIT_SPEC_FK 
 FOREIGN KEY (SPECIFICATIONID) 
 REFERENCES FURNITURE.SPECIFICATION (SPECIFICATIONID));

ALTER TABLE FURNITURE.PRICE ADD (
  CONSTRAINT PRICE_CURRENCY_FK 
 FOREIGN KEY (CURRENCYID) 
 REFERENCES FURNITURE.CURRENCY (CURRENCYID),
  CONSTRAINT PRICE_PRODUCT_FK 
 FOREIGN KEY (PRODUCTID) 
 REFERENCES FURNITURE.PRODUCT (PRODUCTID),
  CONSTRAINT PRICE_CATALOGUE_FK 
 FOREIGN KEY (CATALOGUEID) 
 REFERENCES FURNITURE.CATALOGUE (CATALOGUEID),
  CONSTRAINT PROCE_COMPANY_FK 
 FOREIGN KEY (COMPANYID) 
 REFERENCES FURNITURE.COMPANY (COMPANYID));
