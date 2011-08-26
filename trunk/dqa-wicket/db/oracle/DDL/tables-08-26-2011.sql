CREATE TABLE DQA_ORGANIZATION
(
  ORG_ID              INTEGER                   NOT NULL,
  ORG_LABEL           VARCHAR2(60 BYTE)         NOT NULL,
  ORG_PARENT_ID       INTEGER,
  PRIMARY_PROFILE_ID  INTEGER,
  ORG_LOCAL_CODE      VARCHAR2(60 BYTE)
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       2147483645
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE DQA_USER_ACCOUNT
(
  USERNAME      VARCHAR2(30 BYTE)               NOT NULL,
  PASSWORD      VARCHAR2(50 BYTE)               NOT NULL,
  ACCOUNT_TYPE  VARCHAR2(10 BYTE)               NOT NULL,
  ORG_ID        INTEGER                         DEFAULT 0,
  EMAIL         VARCHAR2(120 BYTE)              NOT NULL
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       2147483645
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE DQA_POTENTIAL_ISSUE_STATUS
(
  POTENTIAL_ISSUE_STATUS_ID  INTEGER            NOT NULL,
  ISSUE_ID                   INTEGER            NOT NULL,
  PROFILE_ID                 INTEGER            NOT NULL,
  ACTION_CODE                VARCHAR2(1 BYTE)   NOT NULL,
  EXPECT_MIN                 INTEGER            DEFAULT 0                     NOT NULL,
  EXPECT_MAX                 INTEGER            DEFAULT 100                   NOT NULL
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       2147483645
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE DQA_ISSUE_ACTION
(
  ACTION_CODE   VARCHAR2(1 BYTE)                NOT NULL,
  ACTION_LABEL  VARCHAR2(30 BYTE)               NOT NULL
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       2147483645
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE DQA_MESSAGE_BATCH
(
  BATCH_ID                  INTEGER             NOT NULL,
  BATCH_TITLE               VARCHAR2(60 BYTE),
  TYPE_CODE                 VARCHAR2(1 BYTE),
  START_DATE                DATE,
  END_DATE                  DATE,
  SUBMIT_CODE               VARCHAR2(1 BYTE),
  PROFILE_ID                INTEGER,
  COMP_PATIENT_SCORE        INTEGER,
  COMP_SCORE                INTEGER,
  COMP_VACCINATION_SCORE    INTEGER,
  COMP_VACCINE_GROUP_SCORE  INTEGER,
  MESSAGE_COUNT             INTEGER,
  MESSAGE_WITH_ADMIN_COUNT  INTEGER,
  NEXT_OF_KIN_COUNT         INTEGER,
  OVERALL_SCORE             INTEGER,
  PATIENT_COUNT             INTEGER,
  PATIENT_UNDERAGE_COUNT    INTEGER,
  QUAL_ERROR_SCORE          INTEGER,
  QUAL_SCORE                INTEGER,
  QUAL_WARN_SCORE           INTEGER,
  TIME_AVERAGE              NUMBER,
  TIME_COUNT_2_DAYS         INTEGER,
  TIME_COUNT_30_DAYS        INTEGER,
  TIME_COUNT_7_DAYS         INTEGER,
  TIME_DATE_FIRST           DATE,
  TIME_DATE_LAST            DATE,
  TIME_SCORE                INTEGER,
  TIME_SCORE_2_DAYS         INTEGER,
  TIME_SCORE_30_DAYS        INTEGER,
  TIME_SCORE_7_DAYS         INTEGER,
  VACC_ADMIN_COUNT          INTEGER,
  VACC_DELETE_COUNT         INTEGER,
  VACC_HISTORICAL_COUNT     INTEGER,
  VACC_NOT_ADMIN_COUNT      INTEGER
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       2147483645
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE DQA_RECEIVE_QUEUE
(
  RECEIVE_QUEUE_ID  INTEGER                     NOT NULL,
  BATCH_ID          INTEGER,
  RECEIVED_ID       INTEGER,
  SUBMIT_CODE       VARCHAR2(1 BYTE)
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       2147483645
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE DQA_MESSAGE_RECEIVED
(
  RECEIVED_ID    INTEGER                        NOT NULL,
  PROFILE_ID     INTEGER                        NOT NULL,
  RECEIVED_DATE  DATE                           NOT NULL,
  REQUEST_TEXT   CLOB,
  RESPONSE_TEXT  CLOB,
  ACTION_CODE    VARCHAR2(1 BYTE),
  SUBMIT_CODE    VARCHAR2(1 BYTE),
  PATIENT_ID     INTEGER
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       2147483645
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
LOB (RESPONSE_TEXT) STORE AS 
      ( TABLESPACE  USERS 
        ENABLE      STORAGE IN ROW
        CHUNK       8192
        PCTVERSION  0
        NOCACHE
        STORAGE    (
                    INITIAL          64K
                    MINEXTENTS       1
                    MAXEXTENTS       2147483645
                    PCTINCREASE      0
                    BUFFER_POOL      DEFAULT
                   )
      )
  LOB (REQUEST_TEXT) STORE AS 
      ( TABLESPACE  USERS 
        ENABLE      STORAGE IN ROW
        CHUNK       8192
        PCTVERSION  0
        NOCACHE
        STORAGE    (
                    INITIAL          64K
                    MINEXTENTS       1
                    MAXEXTENTS       2147483645
                    PCTINCREASE      0
                    BUFFER_POOL      DEFAULT
                   )
      )
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE DQA_NEXT_OF_KIN
(
  NEXT_OF_KIN_ID         INTEGER                NOT NULL,
  RECEIVED_ID            INTEGER                NOT NULL,
  POSITION_ID            INTEGER                NOT NULL,
  ADDRESS_CITY           VARCHAR2(250 BYTE),
  ADDRESS_COUNTRY        VARCHAR2(250 BYTE),
  ADDRESS_COUNTY_PARISH  VARCHAR2(250 BYTE),
  ADDRESS_STATE          VARCHAR2(250 BYTE),
  ADDRESS_STREET         VARCHAR2(250 BYTE),
  ADDRESS_STREET2        VARCHAR2(250 BYTE),
  ADDRESS_TYPE           VARCHAR2(250 BYTE),
  ADDRESS_ZIP            VARCHAR2(250 BYTE),
  NAME_FIRST             VARCHAR2(250 BYTE),
  NAME_LAST              VARCHAR2(250 BYTE),
  NAME_MIDDLE            VARCHAR2(250 BYTE),
  NAME_PREFIX            VARCHAR2(250 BYTE),
  NAME_SUFFIX            VARCHAR2(250 BYTE),
  NAME_TYPE_CODE         VARCHAR2(250 BYTE),
  PHONE_NUMBER           VARCHAR2(250 BYTE),
  RELATIONSHIP_CODE      VARCHAR2(250 BYTE),
  SKIPPED                VARCHAR2(1 BYTE)
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       2147483645
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE DQA_ISSUE_FOUND
(
  ISSUE_FOUND_ID  INTEGER                       NOT NULL,
  RECEIVED_ID     INTEGER                       NOT NULL,
  ISSUE_ID        INTEGER                       NOT NULL,
  POSITION_ID     INTEGER                       NOT NULL,
  ISSUE_NEGATE    VARCHAR2(1 BYTE)              DEFAULT 'N',
  ACTION_CODE     VARCHAR2(1 BYTE),
  CODE_ID         INTEGER
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       2147483645
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE DQA_CODE_STATUS
(
  CODE_STATUS  VARCHAR2(1 BYTE)                 NOT NULL,
  CODE_LABEL   VARCHAR2(30 BYTE)                NOT NULL
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       2147483645
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE DQA_BATCH_TYPE
(
  TYPE_CODE   VARCHAR2(1 BYTE)                  NOT NULL,
  TYPE_LABEL  VARCHAR2(30 BYTE)                 NOT NULL
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       2147483645
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE DQA_BATCH_ISSUES
(
  BATCH_ISSUES_ID  INTEGER                      NOT NULL,
  BATCH_ID         INTEGER                      NOT NULL,
  ISSUE_ID         INTEGER                      NOT NULL,
  ISSUE_COUNT_POS  INTEGER,
  ISSUE_COUNT_NEG  INTEGER
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       2147483645
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE DQA_BATCH_ACTIONS
(
  BATCH_ACTIONS_ID  INTEGER                     NOT NULL,
  BATCH_ID          INTEGER                     NOT NULL,
  ACTION_CODE       VARCHAR2(1 BYTE)            NOT NULL,
  ACTION_COUNT      INTEGER
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       2147483645
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE DQA_SUBMIT_STATUS
(
  SUBMIT_CODE   VARCHAR2(1 BYTE)                NOT NULL,
  SUBMIT_LABEL  VARCHAR2(30 BYTE)               NOT NULL
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       2147483645
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE DQA_PATIENT
(
  PATIENT_ID                INTEGER             NOT NULL,
  RECEIVED_ID               INTEGER             NOT NULL,
  ADDRESS_CITY              VARCHAR2(250 BYTE),
  ADDRESS_COUNTRY           VARCHAR2(250 BYTE),
  ADDRESS_COUNTY_PARISH     VARCHAR2(250 BYTE),
  ADDRESS_STATE             VARCHAR2(250 BYTE),
  ADDRESS_STREET            VARCHAR2(250 BYTE),
  ADDRESS_STREET2           VARCHAR2(250 BYTE),
  ADDRESS_TYPE              VARCHAR2(250 BYTE),
  ADDRESS_ZIP               VARCHAR2(250 BYTE),
  ALIAS_FIRST               VARCHAR2(250 BYTE),
  ALIAS_LAST                VARCHAR2(250 BYTE),
  ALIAS_MIDDLE              VARCHAR2(250 BYTE),
  ALIAS_PREFIX              VARCHAR2(250 BYTE),
  ALIAS_SUFFIX              VARCHAR2(250 BYTE),
  ALIAS_TYPE_CODE           VARCHAR2(250 BYTE),
  BIRTH_DATE                DATE,
  BIRTH_MULIPLE             VARCHAR2(250 BYTE),
  BIRTH_ORDER               VARCHAR2(250 BYTE),
  BIRTH_PLACE               VARCHAR2(250 BYTE),
  DEATH_DATE                DATE,
  DEATH_INDICATOR           VARCHAR2(250 BYTE),
  ETHNICITY_CODE            VARCHAR2(250 BYTE),
  FACILITY_ID               VARCHAR2(250 BYTE),
  FACILITY_NAME             VARCHAR2(250 BYTE),
  FINANCIAL_ELIGIBILITY     VARCHAR2(250 BYTE),
  ID_MEDICAID               VARCHAR2(250 BYTE),
  ID_SSN                    VARCHAR2(250 BYTE),
  ID_SUBMITTER_ASSIGN_AUTH  VARCHAR2(250 BYTE),
  ID_SUBMITTER_NUMBER       VARCHAR2(250 BYTE),
  ID_SUBMITTER_TYPE_CODE    VARCHAR2(250 BYTE),
  MOTHER_MAIDEN_NAME        VARCHAR2(250 BYTE),
  NAME_FIRST                VARCHAR2(250 BYTE),
  NAME_LAST                 VARCHAR2(250 BYTE),
  NAME_MIDDLE               VARCHAR2(250 BYTE),
  NAME_PREFIX               VARCHAR2(250 BYTE),
  NAME_SUFFIX               VARCHAR2(250 BYTE),
  NAME_TYPE_CODE            VARCHAR2(250 BYTE),
  PHONE_NUMBER              VARCHAR2(250 BYTE),
  PHYSICIAN_NAME_FIRST      VARCHAR2(250 BYTE),
  PHYSICIAN_NAME_LAST       VARCHAR2(250 BYTE),
  PHYSICIAN_NUMBER          VARCHAR2(250 BYTE),
  PRIMARY_LANGUAGE_CODE     VARCHAR2(250 BYTE),
  PROTECTION_CODE           VARCHAR2(250 BYTE),
  PUBLICITY_CODE            VARCHAR2(250 BYTE),
  RACE_CODE                 VARCHAR2(250 BYTE),
  REGISTRY_STATUS           VARCHAR2(250 BYTE),
  SEX_CODE                  VARCHAR2(250 BYTE),
  SKIPPED                   VARCHAR2(1 BYTE)
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       2147483645
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE DQA_POTENTIAL_ISSUE
(
  ISSUE_ID             INTEGER                  NOT NULL,
  TARGET_OBJECT        VARCHAR2(30 BYTE)        NOT NULL,
  TARGET_FIELD         VARCHAR2(50 BYTE)        NOT NULL,
  ISSUE_TYPE           VARCHAR2(50 BYTE)        NOT NULL,
  FIELD_VALUE          VARCHAR2(50 BYTE),
  DEFAULT_ACTION_CODE  VARCHAR2(1 BYTE)         NOT NULL,
  CHANGE_PRIORITY      VARCHAR2(7 BYTE)         NOT NULL,
  REPORT_DENOMINATOR   VARCHAR2(30 BYTE)        NOT NULL,
  ISSUE_DESCRIPTION    VARCHAR2(4000 BYTE)
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       2147483645
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE DQA_VACCINE_GROUP
(
  GROUP_ID      INTEGER                         NOT NULL,
  GROUP_CODE    VARCHAR2(30 BYTE)               NOT NULL,
  GROUP_LABEL   VARCHAR2(250 BYTE)              NOT NULL,
  GROUP_STATUS  VARCHAR2(30 BYTE)               NOT NULL
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       2147483645
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE DQA_VACCINE_CVX
(
  CVX_ID            INTEGER                     NOT NULL,
  CVX_CODE          VARCHAR2(10 BYTE)           NOT NULL,
  CVX_LABEL         VARCHAR2(250 BYTE)          NOT NULL,
  VALID_START_DATE  DATE                        NOT NULL,
  USE_START_DATE    DATE                        NOT NULL,
  USE_END_DATE      DATE                        NOT NULL,
  VALID_END_DATE    DATE                        NOT NULL,
  USE_MONTH_START   INTEGER                     NOT NULL,
  USE_MONTH_END     INTEGER                     NOT NULL,
  CONCEPT_TYPE      VARCHAR2(30 BYTE)           NOT NULL
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       2147483645
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE DQA_VACCINE_MVX
(
  MVX_CODE          VARCHAR2(10 BYTE)           NOT NULL,
  MVX_LABEL         VARCHAR2(250 BYTE)          NOT NULL,
  VALID_START_DATE  DATE                        NOT NULL,
  USE_START_DATE    DATE                        NOT NULL,
  USE_END_DATE      DATE                        NOT NULL,
  VALID_END_DATE    DATE                        NOT NULL
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       2147483645
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE DQA_VACCINATION
(
  VACCINATION_ID              INTEGER           NOT NULL,
  RECEIVED_ID                 INTEGER           NOT NULL,
  POSITION_ID                 INTEGER           NOT NULL,
  ADMIN_CODE_CPT              VARCHAR2(250 BYTE),
  ADMIN_CODE_CVX              VARCHAR2(250 BYTE),
  ADMIN_DATE                  DATE,
  AMOUNT                      VARCHAR2(250 BYTE),
  AMOUNT_UNIT_CODE            VARCHAR2(250 BYTE),
  BODY_ROUTE_CODE             VARCHAR2(250 BYTE),
  BODY_SITE_CODE              VARCHAR2(250 BYTE),
  COMPLETION_STATUS_CODE      VARCHAR2(250 BYTE),
  CONFIDENTIALITY_CODE        VARCHAR2(250 BYTE),
  ENTERED_BY_NUMBER           VARCHAR2(250 BYTE),
  ENTERED_BY_NAME_FIRST       VARCHAR2(250 BYTE),
  ENTERED_BY_NAME_LAST        VARCHAR2(250 BYTE),
  EXPIRATION_DATE             DATE,
  FACILITY_ID                 VARCHAR2(250 BYTE),
  FACILITY_NAME               VARCHAR2(250 BYTE),
  FINANCIAL_ELIGIBILITY_CODE  VARCHAR2(250 BYTE),
  GIVEN_BY_NUMBER             VARCHAR2(250 BYTE),
  GIVEN_BY_NAME_LAST          VARCHAR2(250 BYTE),
  GIVEN_BY_NAME_FIRST         VARCHAR2(250 BYTE),
  ID_SUBMITTER                VARCHAR2(250 BYTE),
  INFORMATION_SOURCE_CODE     VARCHAR2(250 BYTE),
  LOT_NUMBER                  VARCHAR2(250 BYTE),
  MANUFACTURER_CODE           VARCHAR2(250 BYTE),
  ORDERED_BY_NUMBER           VARCHAR2(250 BYTE),
  REFUSAL_CODE                VARCHAR2(250 BYTE),
  SYSTEM_ENTRY_DATE           DATE,
  VIS_PUBLICATION_DATE        VARCHAR2(250 BYTE),
  SKIPPED                     VARCHAR2(1 BYTE)
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       2147483645
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE DQA_CODE_MASTER
(
  CODE_MASTER_ID  INTEGER                       NOT NULL,
  TABLE_ID        INTEGER                       NOT NULL,
  CODE_VALUE      VARCHAR2(50 BYTE)             NOT NULL,
  CODE_LABEL      VARCHAR2(250 BYTE)            NOT NULL,
  USE_VALUE       VARCHAR2(50 BYTE)             NOT NULL,
  CODE_STATUS     VARCHAR2(1 BYTE)              NOT NULL
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       2147483645
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE DQA_KEYED_SETTING
(
  KEYED_ID     INTEGER                          NOT NULL,
  KEYED_CODE   VARCHAR2(50 BYTE)                NOT NULL,
  OBJECT_CODE  VARCHAR2(50 BYTE)                NOT NULL,
  OBJECT_ID    INTEGER                          NOT NULL,
  KEYED_VALUE  VARCHAR2(250 BYTE)               NOT NULL
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       2147483645
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE DQA_APPLICATION
(
  APPLICATION_ID     INTEGER                    NOT NULL,
  APPLICATION_LABEL  VARCHAR2(30 BYTE)          NOT NULL,
  APPLICATION_TYPE   VARCHAR2(30 BYTE)          NOT NULL,
  RUN_THIS           VARCHAR2(1 BYTE)           DEFAULT 'N'
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       2147483645
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE DQA_BATCH_CODE_RECEIVED
(
  BATCH_CODE_RECEIVED_ID  INTEGER               NOT NULL,
  BATCH_ID                INTEGER,
  CODED_ID                INTEGER,
  RECEIVED_COUNT          INTEGER
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       2147483645
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE DQA_CODE_TABLE
(
  TABLE_ID            INTEGER                   NOT NULL,
  TABLE_LABEL         VARCHAR2(50 BYTE)         NOT NULL,
  DEFAULT_CODE_VALUE  VARCHAR2(50 BYTE)
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       2147483645
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE DQA_CODE_RECEIVED
(
  CODE_ID         INTEGER                       NOT NULL,
  CODE_LABEL      VARCHAR2(30 BYTE),
  PROFILE_ID      INTEGER                       NOT NULL,
  TABLE_ID        INTEGER                       NOT NULL,
  RECEIVED_VALUE  VARCHAR2(50 BYTE)             NOT NULL,
  CODE_VALUE      VARCHAR2(50 BYTE),
  CODE_STATUS     VARCHAR2(1 BYTE)              NOT NULL,
  RECEIVED_COUNT  INTEGER
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       2147483645
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE DQA_VACCINE_CVX_GROUP
(
  CVX_GROUP_ID  INTEGER                         NOT NULL,
  GROUP_ID      INTEGER                         NOT NULL,
  CVX_ID        INTEGER                         NOT NULL
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       2147483645
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE DQA_VACCINE_PRODUCT
(
  PRODUCT_ID        INTEGER                     NOT NULL,
  PRODUCT_NAME      VARCHAR2(250 BYTE)          NOT NULL,
  PRODUCT_LABEL     VARCHAR2(250 BYTE)          NOT NULL,
  CVX_ID            INTEGER                     NOT NULL,
  MVX_CODE          VARCHAR2(10 BYTE)           NOT NULL,
  VALID_START_DATE  DATE                        NOT NULL,
  USE_START_DATE    DATE                        NOT NULL,
  USE_END_DATE      DATE                        NOT NULL,
  VALID_END_DATE    DATE                        NOT NULL
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       2147483645
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE DQA_VACCINE_CPT
(
  CPT_ID            INTEGER                     NOT NULL,
  CPT_CODE          VARCHAR2(10 BYTE)           NOT NULL,
  CPT_LABEL         VARCHAR2(250 BYTE)          NOT NULL,
  VALID_START_DATE  DATE                        NOT NULL,
  USE_START_DATE    DATE                        NOT NULL,
  USE_END_DATE      DATE                        NOT NULL,
  VALID_END_DATE    DATE                        NOT NULL,
  CVX_ID            INTEGER                     NOT NULL
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       2147483645
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE DQA_BATCH_VACCINE_CVX
(
  BATCH_VACCINE_CVX_ID  INTEGER                 NOT NULL,
  BATCH_ID              INTEGER,
  CVX_ID                INTEGER,
  RECEIVED_COUNT        INTEGER
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       2147483645
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE DQA_SUBMITTER_PROFILE
(
  PROFILE_ID         INTEGER                    NOT NULL,
  PROFILE_CODE       VARCHAR2(50 BYTE)          NOT NULL,
  PROFILE_LABEL      VARCHAR2(50 BYTE)          NOT NULL,
  PROFILE_STATUS     VARCHAR2(10 BYTE)          DEFAULT 'Setup'               NOT NULL,
  ORG_ID             INTEGER                    NOT NULL,
  DATA_FORMAT        VARCHAR2(5 BYTE)           DEFAULT 'HL7v2'               NOT NULL,
  TRANSFER_PRIORITY  VARCHAR2(7 BYTE)           DEFAULT 'Normal'              NOT NULL,
  ACCESS_KEY         VARCHAR2(50 BYTE)
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       2147483645
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE INDEX DQA_BATCH_ACTIONS_KEY ON DQA_BATCH_ACTIONS
(BATCH_ID, ACTION_CODE)
LOGGING
TABLESPACE USERS
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       2147483645
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


CREATE INDEX DQA_BATCH_ISSUES_KEY ON DQA_BATCH_ISSUES
(BATCH_ID, ISSUE_ID)
LOGGING
TABLESPACE USERS
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       2147483645
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


CREATE INDEX DQA_IN_CM_TABLE_ID ON DQA_CODE_MASTER
(TABLE_ID)
LOGGING
TABLESPACE USERS
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       2147483645
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


CREATE INDEX DQA_IN_CR_PROFILE_TABLE ON DQA_CODE_RECEIVED
(PROFILE_ID, TABLE_ID)
LOGGING
TABLESPACE USERS
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       2147483645
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


CREATE INDEX DQA_ISSUE_FOUND_KEY ON DQA_ISSUE_FOUND
(RECEIVED_ID, ISSUE_ID)
LOGGING
TABLESPACE USERS
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       2147483645
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


CREATE UNIQUE INDEX DQA_IN_PI ON DQA_POTENTIAL_ISSUE
(TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE)
LOGGING
TABLESPACE USERS
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       2147483645
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


CREATE INDEX DQA_POTENTIAL_ISSUE_STATUS_KEY ON DQA_POTENTIAL_ISSUE_STATUS
(ISSUE_ID, PROFILE_ID)
LOGGING
TABLESPACE USERS
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       2147483645
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


CREATE INDEX DQA_RECEIVE_QUEUE_KEY ON DQA_RECEIVE_QUEUE
(BATCH_ID, RECEIVED_ID)
LOGGING
TABLESPACE USERS
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       2147483645
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


CREATE UNIQUE INDEX DQA_IN_SP ON DQA_SUBMITTER_PROFILE
(PROFILE_CODE)
LOGGING
TABLESPACE USERS
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       2147483645
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


ALTER TABLE DQA_ORGANIZATION ADD (
  PRIMARY KEY
 (ORG_ID)
    USING INDEX 
    TABLESPACE USERS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
               ));


ALTER TABLE DQA_USER_ACCOUNT ADD (
  PRIMARY KEY
 (USERNAME)
    USING INDEX 
    TABLESPACE USERS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
               ));


ALTER TABLE DQA_POTENTIAL_ISSUE_STATUS ADD (
  PRIMARY KEY
 (POTENTIAL_ISSUE_STATUS_ID)
    USING INDEX 
    TABLESPACE USERS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
               ));


ALTER TABLE DQA_ISSUE_ACTION ADD (
  PRIMARY KEY
 (ACTION_CODE)
    USING INDEX 
    TABLESPACE USERS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
               ));


ALTER TABLE DQA_MESSAGE_BATCH ADD (
  PRIMARY KEY
 (BATCH_ID)
    USING INDEX 
    TABLESPACE USERS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
               ));


ALTER TABLE DQA_RECEIVE_QUEUE ADD (
  PRIMARY KEY
 (RECEIVE_QUEUE_ID)
    USING INDEX 
    TABLESPACE USERS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
               ));


ALTER TABLE DQA_MESSAGE_RECEIVED ADD (
  PRIMARY KEY
 (RECEIVED_ID)
    USING INDEX 
    TABLESPACE USERS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
               ));


ALTER TABLE DQA_NEXT_OF_KIN ADD (
  PRIMARY KEY
 (NEXT_OF_KIN_ID)
    USING INDEX 
    TABLESPACE USERS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
               ));


ALTER TABLE DQA_ISSUE_FOUND ADD (
  PRIMARY KEY
 (ISSUE_FOUND_ID)
    USING INDEX 
    TABLESPACE USERS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
               ));


ALTER TABLE DQA_CODE_STATUS ADD (
  PRIMARY KEY
 (CODE_STATUS)
    USING INDEX 
    TABLESPACE USERS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
               ));


ALTER TABLE DQA_BATCH_TYPE ADD (
  PRIMARY KEY
 (TYPE_CODE)
    USING INDEX 
    TABLESPACE USERS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
               ));


ALTER TABLE DQA_BATCH_ISSUES ADD (
  PRIMARY KEY
 (BATCH_ISSUES_ID)
    USING INDEX 
    TABLESPACE USERS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
               ));


ALTER TABLE DQA_BATCH_ACTIONS ADD (
  PRIMARY KEY
 (BATCH_ACTIONS_ID)
    USING INDEX 
    TABLESPACE USERS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
               ));


ALTER TABLE DQA_SUBMIT_STATUS ADD (
  PRIMARY KEY
 (SUBMIT_CODE)
    USING INDEX 
    TABLESPACE USERS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
               ));


ALTER TABLE DQA_PATIENT ADD (
  PRIMARY KEY
 (PATIENT_ID)
    USING INDEX 
    TABLESPACE USERS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
               ));


ALTER TABLE DQA_POTENTIAL_ISSUE ADD (
  PRIMARY KEY
 (ISSUE_ID)
    USING INDEX 
    TABLESPACE USERS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
               ));


ALTER TABLE DQA_VACCINE_GROUP ADD (
  PRIMARY KEY
 (GROUP_ID)
    USING INDEX 
    TABLESPACE USERS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
               ));


ALTER TABLE DQA_VACCINE_CVX ADD (
  PRIMARY KEY
 (CVX_ID)
    USING INDEX 
    TABLESPACE USERS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
               ));


ALTER TABLE DQA_VACCINE_MVX ADD (
  PRIMARY KEY
 (MVX_CODE)
    USING INDEX 
    TABLESPACE USERS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
               ));


ALTER TABLE DQA_VACCINATION ADD (
  PRIMARY KEY
 (VACCINATION_ID)
    USING INDEX 
    TABLESPACE USERS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
               ));


ALTER TABLE DQA_CODE_MASTER ADD (
  PRIMARY KEY
 (CODE_MASTER_ID)
    USING INDEX 
    TABLESPACE USERS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
               ));


ALTER TABLE DQA_KEYED_SETTING ADD (
  PRIMARY KEY
 (KEYED_ID)
    USING INDEX 
    TABLESPACE USERS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
               ));


ALTER TABLE DQA_APPLICATION ADD (
  PRIMARY KEY
 (APPLICATION_ID)
    USING INDEX 
    TABLESPACE USERS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
               ));


ALTER TABLE DQA_BATCH_CODE_RECEIVED ADD (
  PRIMARY KEY
 (BATCH_CODE_RECEIVED_ID)
    USING INDEX 
    TABLESPACE USERS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
               ));


ALTER TABLE DQA_CODE_TABLE ADD (
  PRIMARY KEY
 (TABLE_ID)
    USING INDEX 
    TABLESPACE USERS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
               ));


ALTER TABLE DQA_CODE_RECEIVED ADD (
  PRIMARY KEY
 (CODE_ID)
    USING INDEX 
    TABLESPACE USERS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
               ));


ALTER TABLE DQA_VACCINE_CVX_GROUP ADD (
  PRIMARY KEY
 (CVX_GROUP_ID)
    USING INDEX 
    TABLESPACE USERS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
               ));


ALTER TABLE DQA_VACCINE_PRODUCT ADD (
  PRIMARY KEY
 (PRODUCT_ID)
    USING INDEX 
    TABLESPACE USERS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
               ));


ALTER TABLE DQA_VACCINE_CPT ADD (
  PRIMARY KEY
 (CPT_ID)
    USING INDEX 
    TABLESPACE USERS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
               ));


ALTER TABLE DQA_BATCH_VACCINE_CVX ADD (
  PRIMARY KEY
 (BATCH_VACCINE_CVX_ID)
    USING INDEX 
    TABLESPACE USERS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
               ));


ALTER TABLE DQA_SUBMITTER_PROFILE ADD (
  PRIMARY KEY
 (PROFILE_ID)
    USING INDEX 
    TABLESPACE USERS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
               ));


ALTER TABLE DQA_ORGANIZATION ADD (
  CONSTRAINT DQA_FK_ORGANIZATION1 
 FOREIGN KEY (ORG_PARENT_ID) 
 REFERENCES DQA_ORGANIZATION (ORG_ID));

ALTER TABLE DQA_ORGANIZATION ADD (
  CONSTRAINT DQA_FK_ORGANIZATION2 
 FOREIGN KEY (PRIMARY_PROFILE_ID) 
 REFERENCES DQA_SUBMITTER_PROFILE (PROFILE_ID));


ALTER TABLE DQA_USER_ACCOUNT ADD (
  CONSTRAINT DQA_FK_USER_ACCOUNT1 
 FOREIGN KEY (ORG_ID) 
 REFERENCES DQA_ORGANIZATION (ORG_ID));


ALTER TABLE DQA_POTENTIAL_ISSUE_STATUS ADD (
  CONSTRAINT DQA_FK_POTENTIAL_ISSUE_STATUS1 
 FOREIGN KEY (ISSUE_ID) 
 REFERENCES DQA_POTENTIAL_ISSUE (ISSUE_ID));

ALTER TABLE DQA_POTENTIAL_ISSUE_STATUS ADD (
  CONSTRAINT DQA_FK_POTENTIAL_ISSUE_STATUS2 
 FOREIGN KEY (PROFILE_ID) 
 REFERENCES DQA_SUBMITTER_PROFILE (PROFILE_ID));

ALTER TABLE DQA_POTENTIAL_ISSUE_STATUS ADD (
  CONSTRAINT DQA_FK_POTENTIAL_ISSUE_STATUS3 
 FOREIGN KEY (ACTION_CODE) 
 REFERENCES DQA_ISSUE_ACTION (ACTION_CODE));


ALTER TABLE DQA_MESSAGE_BATCH ADD (
  CONSTRAINT DQA_FK_MESSAGE_BATCH1 
 FOREIGN KEY (TYPE_CODE) 
 REFERENCES DQA_BATCH_TYPE (TYPE_CODE));

ALTER TABLE DQA_MESSAGE_BATCH ADD (
  CONSTRAINT DQA_FK_MESSAGE_BATCH2 
 FOREIGN KEY (SUBMIT_CODE) 
 REFERENCES DQA_SUBMIT_STATUS (SUBMIT_CODE));

ALTER TABLE DQA_MESSAGE_BATCH ADD (
  CONSTRAINT DQA_FK_MESSAGE_BATCH3 
 FOREIGN KEY (PROFILE_ID) 
 REFERENCES DQA_SUBMITTER_PROFILE (PROFILE_ID));


ALTER TABLE DQA_RECEIVE_QUEUE ADD (
  CONSTRAINT DQA_FK_RECEIVE_QUEUE2 
 FOREIGN KEY (RECEIVED_ID) 
 REFERENCES DQA_MESSAGE_RECEIVED (RECEIVED_ID));

ALTER TABLE DQA_RECEIVE_QUEUE ADD (
  CONSTRAINT DQA_FK_RECEIVE_QUEUE3 
 FOREIGN KEY (SUBMIT_CODE) 
 REFERENCES DQA_SUBMIT_STATUS (SUBMIT_CODE));

ALTER TABLE DQA_RECEIVE_QUEUE ADD (
  CONSTRAINT DQA_FK_RECEIVE_QUEUE1 
 FOREIGN KEY (BATCH_ID) 
 REFERENCES DQA_MESSAGE_BATCH (BATCH_ID));


ALTER TABLE DQA_MESSAGE_RECEIVED ADD (
  CONSTRAINT DQA_FK_MESSAGE_RECEIVED3 
 FOREIGN KEY (SUBMIT_CODE) 
 REFERENCES DQA_SUBMIT_STATUS (SUBMIT_CODE));

ALTER TABLE DQA_MESSAGE_RECEIVED ADD (
  CONSTRAINT DQA_FK_MESSAGE_RECEIVED2 
 FOREIGN KEY (ACTION_CODE) 
 REFERENCES DQA_ISSUE_ACTION (ACTION_CODE));

ALTER TABLE DQA_MESSAGE_RECEIVED ADD (
  CONSTRAINT DQA_FK_MESSAGE_RECEIVED1 
 FOREIGN KEY (PROFILE_ID) 
 REFERENCES DQA_SUBMITTER_PROFILE (PROFILE_ID));


ALTER TABLE DQA_NEXT_OF_KIN ADD (
  CONSTRAINT DQA_FK_NEXT_OF_KIN1 
 FOREIGN KEY (RECEIVED_ID) 
 REFERENCES DQA_MESSAGE_RECEIVED (RECEIVED_ID));


ALTER TABLE DQA_ISSUE_FOUND ADD (
  CONSTRAINT DQA_FK_ISSUE_FOUND4 
 FOREIGN KEY (CODE_ID) 
 REFERENCES DQA_CODE_RECEIVED (CODE_ID));

ALTER TABLE DQA_ISSUE_FOUND ADD (
  CONSTRAINT DQA_FK_ISSUE_FOUND1 
 FOREIGN KEY (RECEIVED_ID) 
 REFERENCES DQA_MESSAGE_RECEIVED (RECEIVED_ID));

ALTER TABLE DQA_ISSUE_FOUND ADD (
  CONSTRAINT DQA_FK_ISSUE_FOUND2 
 FOREIGN KEY (ISSUE_ID) 
 REFERENCES DQA_POTENTIAL_ISSUE (ISSUE_ID));


ALTER TABLE DQA_BATCH_ISSUES ADD (
  CONSTRAINT DQA_FK_BATCH_ISSUES1 
 FOREIGN KEY (BATCH_ID) 
 REFERENCES DQA_MESSAGE_BATCH (BATCH_ID));

ALTER TABLE DQA_BATCH_ISSUES ADD (
  CONSTRAINT DQA_FK_BATCH_ISSUES2 
 FOREIGN KEY (ISSUE_ID) 
 REFERENCES DQA_POTENTIAL_ISSUE (ISSUE_ID));


ALTER TABLE DQA_BATCH_ACTIONS ADD (
  CONSTRAINT DQA_FK_BATCH_ACTIONS2 
 FOREIGN KEY (ACTION_CODE) 
 REFERENCES DQA_ISSUE_ACTION (ACTION_CODE));

ALTER TABLE DQA_BATCH_ACTIONS ADD (
  CONSTRAINT DQA_FK_BATCH_ACTIONS1 
 FOREIGN KEY (BATCH_ID) 
 REFERENCES DQA_MESSAGE_BATCH (BATCH_ID));


ALTER TABLE DQA_POTENTIAL_ISSUE ADD (
  CONSTRAINT DQA_FK_POTENTIAL_ISSUE1 
 FOREIGN KEY (DEFAULT_ACTION_CODE) 
 REFERENCES DQA_ISSUE_ACTION (ACTION_CODE));


ALTER TABLE DQA_VACCINATION ADD (
  CONSTRAINT DQA_FK_VACCINATION2 
 FOREIGN KEY (RECEIVED_ID) 
 REFERENCES DQA_MESSAGE_RECEIVED (RECEIVED_ID));


ALTER TABLE DQA_CODE_MASTER ADD (
  CONSTRAINT DQA_FK_CODE_MASTER1 
 FOREIGN KEY (TABLE_ID) 
 REFERENCES DQA_CODE_TABLE (TABLE_ID));

ALTER TABLE DQA_CODE_MASTER ADD (
  CONSTRAINT DQA_FK_CODE_MASTER2 
 FOREIGN KEY (CODE_STATUS) 
 REFERENCES DQA_CODE_STATUS (CODE_STATUS));


ALTER TABLE DQA_BATCH_CODE_RECEIVED ADD (
  CONSTRAINT DQA_FK_BATCH_CODE_RECEIVED2 
 FOREIGN KEY (CODED_ID) 
 REFERENCES DQA_CODE_RECEIVED (CODE_ID));

ALTER TABLE DQA_BATCH_CODE_RECEIVED ADD (
  CONSTRAINT DQA_FK_BATCH_CODE_RECEIVED1 
 FOREIGN KEY (BATCH_ID) 
 REFERENCES DQA_MESSAGE_BATCH (BATCH_ID));


ALTER TABLE DQA_CODE_RECEIVED ADD (
  CONSTRAINT DQA_FK_CODE_RECEIVED1 
 FOREIGN KEY (PROFILE_ID) 
 REFERENCES DQA_SUBMITTER_PROFILE (PROFILE_ID));

ALTER TABLE DQA_CODE_RECEIVED ADD (
  CONSTRAINT DQA_FK_CODE_RECEIVED2 
 FOREIGN KEY (TABLE_ID) 
 REFERENCES DQA_CODE_TABLE (TABLE_ID));

ALTER TABLE DQA_CODE_RECEIVED ADD (
  CONSTRAINT DQA_FK_CODE_RECEIVED3 
 FOREIGN KEY (CODE_STATUS) 
 REFERENCES DQA_CODE_STATUS (CODE_STATUS));


ALTER TABLE DQA_VACCINE_CVX_GROUP ADD (
  CONSTRAINT DQA_FK_CVX_VACCINE_GROUP1 
 FOREIGN KEY (GROUP_ID) 
 REFERENCES DQA_VACCINE_GROUP (GROUP_ID));

ALTER TABLE DQA_VACCINE_CVX_GROUP ADD (
  CONSTRAINT DQA_FK_CVX_VACCINE_GROUP2 
 FOREIGN KEY (CVX_ID) 
 REFERENCES DQA_VACCINE_CVX (CVX_ID));


ALTER TABLE DQA_VACCINE_PRODUCT ADD (
  CONSTRAINT DQA_FK_CVX_VACCINE_PRODUCT1 
 FOREIGN KEY (CVX_ID) 
 REFERENCES DQA_VACCINE_CVX (CVX_ID));

ALTER TABLE DQA_VACCINE_PRODUCT ADD (
  CONSTRAINT DQA_FK_CVX_VACCINE_PRODUCT2 
 FOREIGN KEY (MVX_CODE) 
 REFERENCES DQA_VACCINE_MVX (MVX_CODE));


ALTER TABLE DQA_VACCINE_CPT ADD (
  CONSTRAINT DQA_FK_CVX_VACCINE_CPT1 
 FOREIGN KEY (CVX_ID) 
 REFERENCES DQA_VACCINE_CVX (CVX_ID));


ALTER TABLE DQA_BATCH_VACCINE_CVX ADD (
  CONSTRAINT DQA_FK_BATCH_VACCINE_CVX1 
 FOREIGN KEY (BATCH_ID) 
 REFERENCES DQA_MESSAGE_BATCH (BATCH_ID));

ALTER TABLE DQA_BATCH_VACCINE_CVX ADD (
  CONSTRAINT DQA_FK_BATCH_VACCINE_CVX2 
 FOREIGN KEY (CVX_ID) 
 REFERENCES DQA_VACCINE_CVX (CVX_ID));


ALTER TABLE DQA_SUBMITTER_PROFILE ADD (
  CONSTRAINT DQA_FK_SUBMITTER_PROFILE1 
 FOREIGN KEY (ORG_ID) 
 REFERENCES DQA_ORGANIZATION (ORG_ID));
SET DEFINE OFF;
Insert into DQA_APPLICATION
   (APPLICATION_ID, APPLICATION_LABEL, APPLICATION_TYPE, RUN_THIS)
 Values
   (1, 'DQA Dev', 'Dev', 'Y');
Insert into DQA_APPLICATION
   (APPLICATION_ID, APPLICATION_LABEL, APPLICATION_TYPE, RUN_THIS)
 Values
   (2, 'ImmTrac', 'Test', 'N');
Insert into DQA_APPLICATION
   (APPLICATION_ID, APPLICATION_LABEL, APPLICATION_TYPE, RUN_THIS)
 Values
   (3, 'ImmTrac', 'Prod', 'N');
COMMIT;




SET DEFINE OFF;
Insert into DQA_BATCH_TYPE
   (TYPE_CODE, TYPE_LABEL)
 Values
   ('S', 'Submission');
Insert into DQA_BATCH_TYPE
   (TYPE_CODE, TYPE_LABEL)
 Values
   ('D', 'Daily');
Insert into DQA_BATCH_TYPE
   (TYPE_CODE, TYPE_LABEL)
 Values
   ('W', 'Weekly');
Insert into DQA_BATCH_TYPE
   (TYPE_CODE, TYPE_LABEL)
 Values
   ('M', 'Monthly');
Insert into DQA_BATCH_TYPE
   (TYPE_CODE, TYPE_LABEL)
 Values
   ('O', 'Other');
COMMIT;


SET DEFINE OFF;
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (807, 25, 'A', 'Add', 'A', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (808, 25, 'D', 'Delete', 'D', 
    'G');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (809, 25, 'U', 'Update', 'U', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (810, 5, 'C', 'Current or temporary', 'C', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (811, 5, 'P', 'Permanent', 'P', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (812, 5, 'M', 'Mailing', 'M', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (813, 5, 'B', 'Firm/Business', 'B', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (814, 5, 'O', 'Office', 'O', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (815, 5, 'H', 'Home', 'H', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (816, 5, 'N', 'Birth (nee)', 'N', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (817, 5, 'F', 'Country of origin', 'F', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (818, 5, 'L', 'Legal address', 'L', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (819, 5, 'BDL', 'Birth delivery location', 'BDL', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (820, 5, 'BR', 'Residence at birth', 'BR', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (821, 5, 'RH', 'Registry home', 'RH', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (822, 5, 'BA', 'Bad address', 'BA', 
    'I');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (823, 7, '1', 'First', '1', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (824, 7, '2', 'Second', '2', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (825, 7, '3', 'Third', '3', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (826, 7, '4', 'Fourth', '4', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (827, 7, '5', 'Fifth', '5', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (828, 8, 'ID', 'Intradermal', 'ID', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (829, 8, 'IM', 'Intramuscular', 'IM', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (830, 8, 'NS', 'Nasal', 'NS', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (831, 8, 'IN', 'Intranasal', 'NS', 
    'D');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (832, 8, 'IV', 'Intravenous', 'IV', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (833, 8, 'PO', 'Oral', 'PO', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (834, 8, 'OTH', 'Other/Miscellaneous', 'OTH', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (835, 8, 'SC', 'Subcutaneous', 'SC', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (836, 8, 'TD', 'Transdermal', 'TD', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (837, 8, 'C38238', 'Intradermal', 'C38238', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (838, 8, 'C28161', 'Intramuscular', 'C28161', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (839, 8, 'C38284', 'Nasal', 'C38284', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (840, 8, 'C38276', 'Intravenous', 'C38276', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (841, 8, 'C38288', 'Oral', 'C38288', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (842, 8, 'C38676', 'Percutaneous', 'C38676', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (843, 8, 'C38299', 'Subcutaneous', 'C38299', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (844, 8, 'C38305', 'Transdermal', 'C38305', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (845, 9, 'LT', 'Left Thigh', 'LT', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (846, 9, 'LA', 'Left Upper Arm', 'LA', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (847, 9, 'LD', 'Left Deltoid', 'LD', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (848, 9, 'LG', 'Left Gluteous Medius', 'LG', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (849, 9, 'LVL', 'Left Vastus Lateralis', 'LVL', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (850, 9, 'LLFA', 'Left Lower Forearm', 'LLFA', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (851, 9, 'RA', 'Right Upper Arm', 'RA', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (852, 9, 'RT', 'Right Thigh', 'RT', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (853, 9, 'RVL', 'Right Vastus Lateralis', 'RVL', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (854, 9, 'RG', 'Right Gluteous Medius', 'RG', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (855, 9, 'RD', 'Right Deltoid', 'RD', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (856, 9, 'RLFA', 'Right Lower Forearm', 'RLFA', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (857, 2, 'USA', 'United States', 'USA', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (858, 2, 'US', 'United States', 'USA', 
    'D');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (859, 2, 'MX', 'Mexico', 'MEX', 
    'D');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (860, 2, 'CA', 'Canada', 'CAN', 
    'D');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (861, 2, 'MEX', 'Mexico', 'MEX', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (862, 2, 'CAN', 'Canada', 'CAN', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (863, 30, '00', 'New immunization record', '00', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (864, 30, '01', 'Historical information - source unspecified', '01', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (865, 30, '02', 'Historical information - from other provider', '01', 
    'D');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (866, 30, '03', 'Historical information - from parents written record', '01', 
    'D');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (867, 30, '04', 'Historical information - from parents recall', '01', 
    'D');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (868, 30, '05', 'Historical information - from other registry', '01', 
    'D');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (869, 30, '06', 'Historical information - from birth certificate', '01', 
    'D');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (870, 30, '07', 'Historical information - from school record', '01', 
    'D');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (871, 30, '08', 'Historical information - from public agency', '01', 
    'D');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (872, 20, 'ara', 'Arabic', 'ara', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (873, 20, 'arm', 'Armenian', 'arm', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (874, 20, 'cat', 'Catalan; Valencian', 'cat', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (875, 20, 'chi', 'Chinese', 'chi', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (876, 20, 'dan', 'Danish', 'dan', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (877, 20, 'eng', 'English', 'eng', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (878, 20, 'fre', 'French', 'fre', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (879, 20, 'ger', 'German', 'ger', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (880, 20, 'hat', 'Haitian; Haitian Creole', 'hat', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (881, 20, 'heb', 'Hebrew', 'heb', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (882, 20, 'hin', 'Hindi', 'hin', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (883, 20, 'hmn', 'Hmong', 'hmn', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (884, 20, 'jpn', 'Japanese', 'jpn', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (885, 20, 'kor', 'Korean', 'kor', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (886, 20, 'rus', 'Russian', 'rus', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (887, 20, 'som', 'Somali', 'som', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (888, 20, 'spa', 'Spanish; Castilian', 'spa', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (889, 20, 'vie', 'Vietnamese', 'vie', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (890, 21, 'A', 'Alias name', 'A', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (891, 21, 'L', 'Legal name', 'L', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (892, 21, 'D', 'Display name', 'D', 
    'D');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (893, 21, 'M', 'Maiden name', 'M', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (894, 21, 'C', 'Adopted name', 'C', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (895, 21, 'B', 'Name at birth', 'B', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (896, 21, 'P', 'Name of partner/spouse', 'P', 
    'I');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (897, 21, 'U', 'Unspecified', 'U', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (898, 19, 'F', 'Female', 'F', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (899, 19, 'M', 'Male', 'M', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (900, 19, 'U', 'Unknown/undifferentiated', 'U', 
    'I');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (901, 10, 'V01', 'Not VFC eligible', 'V01', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (902, 10, 'V02', 'VFC eligible-Medicaid/Medicaid managed care', 'V02', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (903, 10, 'V03', 'VFC eligible-Uninsured', 'V03', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (904, 10, 'V04', 'VFC eligible- American Indian/Alaskan Native', 'V04', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (905, 10, 'V05', 'VFC eligible-Federally Qualified Health Center Patient (under-insured)', 'V05', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (906, 10, 'V06', 'VFC eligible- State specific eligibility (e.g. S-CHIP plan)', 'V06', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (907, 10, 'V07', 'VFC eligibility- Local-specific eligibility', 'V07', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (908, 10, 'V08', 'Not VFC eligible-Under-insured', 'V08', 
    'I');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (909, 4, 'AL', 'Alabama', 'AL', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (910, 4, 'AK', 'Alaska', 'AK', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (911, 4, 'AZ', 'Arizona', 'AZ', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (912, 4, 'AR', 'Arkansas', 'AR', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (913, 4, 'CA', 'California', 'CA', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (914, 4, 'CO', 'Colorado', 'CO', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (915, 4, 'CT', 'Connecticut', 'CT', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (916, 4, 'DE', 'Delaware', 'DE', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (917, 4, 'DC', 'District of Columbia', 'DC', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (918, 4, 'FL', 'Florida', 'FL', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (919, 4, 'GA', 'Georgia', 'GA', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (920, 4, 'HI', 'Hawaii', 'HI', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (921, 4, 'ID', 'Idaho', 'ID', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (922, 4, 'IL', 'Illinois', 'IL', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (923, 4, 'IN', 'Indiana', 'IN', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (924, 4, 'IA', 'Iowa', 'IA', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (925, 4, 'KS', 'Kansas', 'KS', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (926, 4, 'KY', 'Kentucky', 'KY', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (927, 4, 'LA', 'Louisiana', 'LA', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (928, 4, 'ME', 'Maine', 'ME', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (929, 4, 'MD', 'Maryland', 'MD', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (930, 4, 'MA', 'Massachusetts', 'MA', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (931, 4, 'MI', 'Michigan', 'MI', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (932, 4, 'MN', 'Minnesota', 'MN', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (933, 4, 'MS', 'Mississippi', 'MS', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (934, 4, 'MO', 'Missouri', 'MO', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (935, 4, 'MT', 'Montana', 'MT', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (936, 4, 'NE', 'Nebraska', 'NE', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (937, 4, 'NV', 'Nevada', 'NV', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (938, 4, 'NH', 'New Hampshire', 'NH', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (939, 4, 'NJ', 'New Jersey', 'NJ', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (940, 4, 'NM', 'New Mexico', 'NM', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (941, 4, 'NY', 'New York', 'NY', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (942, 4, 'NC', 'North Carolina', 'NC', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (943, 4, 'ND', 'North Dakota', 'ND', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (944, 4, 'OH', 'Ohio', 'OH', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (945, 4, 'OK', 'Oklahoma', 'OK', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (946, 4, 'OR', 'Oregon', 'OR', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (947, 4, 'PA', 'Pennsylvania', 'PA', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (948, 4, 'RI', 'Rhode Island', 'RI', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (949, 4, 'SC', 'South Carolina', 'SC', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (950, 4, 'SD', 'South Dakota', 'SD', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (951, 4, 'TN', 'Tennessee', 'TN', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (952, 4, 'TX', 'Texas', 'TX', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (953, 4, 'UT', 'Utah', 'UT', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (954, 4, 'VT', 'Vermont', 'VT', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (955, 4, 'VA', 'Virginia', 'VA', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (956, 4, 'WA', 'Washington', 'WA', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (957, 4, 'WV', 'West Virginia', 'WV', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (958, 4, 'WI', 'Wisconsin', 'WI', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (959, 4, 'WY', 'Wyoming', 'WY', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (960, 4, 'AS', 'American Samoa', 'AS', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (961, 4, 'GU', 'Guam', 'GU', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (962, 4, 'MP', 'Northern Mariana Islands', 'MP', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (963, 4, 'PR', 'Puerto Rico', 'PR', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (964, 4, 'VI', 'Virgin Islands', 'VI', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (965, 4, 'FM', 'Federated States of Micronesia', 'FM', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (966, 4, 'MH', 'Marshall Islands', 'MH', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (967, 4, 'PW', 'Palau', 'PW', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (968, 4, 'AA', 'Armed Forces Americas (except Canada)', 'AA', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (969, 4, 'AE', 'Armed Forces (Europe, Canada, Middle East, Africa)', 'AE', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (970, 4, 'AP', 'Armed Forces Pacific', 'AP', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (971, 4, 'CZ', 'Canal Zone', 'CZ', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (972, 4, 'PI', 'Philippine Islands', 'PI', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (973, 4, 'TT', 'Trust Territory of the Pacific Islands', 'TT', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (974, 4, 'CM', 'Commonwealth of the Northern Mariana Islands', 'CM', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (975, 26, 'CP', 'Complete', 'CP', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (976, 26, 'RE', 'Refused', 'RE', 
    'G');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (977, 26, 'NA', 'Not Administered', 'NA', 
    'G');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (978, 26, 'PA', 'Partially Administered', 'PA', 
    'G');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (979, 22, 'BRO', 'Brother', 'BRO', 
    'G');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (980, 22, 'CGV', 'Care Giver', 'CGV', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (981, 22, 'FTH', 'Father', 'FTH', 
    'G');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (982, 22, 'FCH', 'Foster Child', 'FCH', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (983, 22, 'GRP', 'Grand Parent', 'GRP', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (984, 22, 'GRD', 'Guardian', 'GRD', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (985, 22, 'MTH', 'Mother', 'MTH', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (986, 22, 'OTH', 'Other', 'OTH', 
    'G');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (987, 22, 'PAR', 'Parent', 'PAR', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (988, 22, 'SEL', 'Self', 'SEL', 
    'G');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (989, 22, 'SIB', 'Sibling', 'SIB', 
    'G');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (990, 22, 'SIS', 'Sister', 'SIS', 
    'G');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (991, 22, 'SPO', 'Spouse', 'SPO', 
    'G');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (992, 22, 'SCH', 'Step Child', 'SCH', 
    'G');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (993, 18, '1002-5', 'American Indian or Alaska Native', '1002-5', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (994, 18, '2028-9', 'Asian', '2028-9', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (995, 18, '2076-8', 'Native Hawaiian or Other Pacific Islander', '2076-8', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (996, 18, '2054-5', 'Black or African-American', '2054-5', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (997, 18, '2106-3', 'White', '2106-3', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (998, 18, '2131-1', 'Other Race', '2131-1', 
    'V');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (999, 18, 'I', 'American Indian or Alaska Native', '1002-5', 
    'D');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (1000, 18, 'A', 'Asian', '2028-9', 
    'D');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (1001, 18, 'A', 'Native Hawaiian or Other Pacific Islander', '2076-8', 
    'D');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (1002, 18, 'B', 'Black or African-American', '2054-5', 
    'D');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (1003, 18, 'W', 'White', '2106-3', 
    'D');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (1004, 18, 'O', 'Other Race', '2131-1', 
    'D');
Insert into DQA_CODE_MASTER
   (CODE_MASTER_ID, TABLE_ID, CODE_VALUE, CODE_LABEL, USE_VALUE, 
    CODE_STATUS)
 Values
   (1005, 18, 'U', 'Unknown', '2131-1', 
    'D');
COMMIT;


SET DEFINE OFF;
Insert into DQA_CODE_STATUS
   (CODE_STATUS, CODE_LABEL)
 Values
   ('G', 'Ignored');
Insert into DQA_CODE_STATUS
   (CODE_STATUS, CODE_LABEL)
 Values
   ('V', 'Valid');
Insert into DQA_CODE_STATUS
   (CODE_STATUS, CODE_LABEL)
 Values
   ('I', 'Invalid');
Insert into DQA_CODE_STATUS
   (CODE_STATUS, CODE_LABEL)
 Values
   ('U', 'Unrecognized');
Insert into DQA_CODE_STATUS
   (CODE_STATUS, CODE_LABEL)
 Values
   ('D', 'Deprecated');
COMMIT;

SET DEFINE OFF;
Insert into DQA_CODE_TABLE
   (TABLE_ID, TABLE_LABEL, DEFAULT_CODE_VALUE)
 Values
   (2, 'Address Country', NULL);
Insert into DQA_CODE_TABLE
   (TABLE_ID, TABLE_LABEL, DEFAULT_CODE_VALUE)
 Values
   (3, 'Address County', NULL);
Insert into DQA_CODE_TABLE
   (TABLE_ID, TABLE_LABEL, DEFAULT_CODE_VALUE)
 Values
   (4, 'Address State', NULL);
Insert into DQA_CODE_TABLE
   (TABLE_ID, TABLE_LABEL, DEFAULT_CODE_VALUE)
 Values
   (5, 'Address Type', NULL);
Insert into DQA_CODE_TABLE
   (TABLE_ID, TABLE_LABEL, DEFAULT_CODE_VALUE)
 Values
   (6, 'Administration Unit', NULL);
Insert into DQA_CODE_TABLE
   (TABLE_ID, TABLE_LABEL, DEFAULT_CODE_VALUE)
 Values
   (7, 'Birth Order', NULL);
Insert into DQA_CODE_TABLE
   (TABLE_ID, TABLE_LABEL, DEFAULT_CODE_VALUE)
 Values
   (8, 'Body Route', NULL);
Insert into DQA_CODE_TABLE
   (TABLE_ID, TABLE_LABEL, DEFAULT_CODE_VALUE)
 Values
   (9, 'Body Site', NULL);
Insert into DQA_CODE_TABLE
   (TABLE_ID, TABLE_LABEL, DEFAULT_CODE_VALUE)
 Values
   (10, 'Finanical Status Code', NULL);
Insert into DQA_CODE_TABLE
   (TABLE_ID, TABLE_LABEL, DEFAULT_CODE_VALUE)
 Values
   (11, 'Id Assigning Authority', NULL);
Insert into DQA_CODE_TABLE
   (TABLE_ID, TABLE_LABEL, DEFAULT_CODE_VALUE)
 Values
   (12, 'Id Type Code', NULL);
Insert into DQA_CODE_TABLE
   (TABLE_ID, TABLE_LABEL, DEFAULT_CODE_VALUE)
 Values
   (13, 'Organization', NULL);
Insert into DQA_CODE_TABLE
   (TABLE_ID, TABLE_LABEL, DEFAULT_CODE_VALUE)
 Values
   (14, 'Patient Ethnicity', NULL);
Insert into DQA_CODE_TABLE
   (TABLE_ID, TABLE_LABEL, DEFAULT_CODE_VALUE)
 Values
   (15, 'Patient Id', NULL);
Insert into DQA_CODE_TABLE
   (TABLE_ID, TABLE_LABEL, DEFAULT_CODE_VALUE)
 Values
   (16, 'Patient Protection', NULL);
Insert into DQA_CODE_TABLE
   (TABLE_ID, TABLE_LABEL, DEFAULT_CODE_VALUE)
 Values
   (17, 'Patient Publicity', NULL);
Insert into DQA_CODE_TABLE
   (TABLE_ID, TABLE_LABEL, DEFAULT_CODE_VALUE)
 Values
   (18, 'Patient Race', NULL);
Insert into DQA_CODE_TABLE
   (TABLE_ID, TABLE_LABEL, DEFAULT_CODE_VALUE)
 Values
   (19, 'Patient Sex', NULL);
Insert into DQA_CODE_TABLE
   (TABLE_ID, TABLE_LABEL, DEFAULT_CODE_VALUE)
 Values
   (20, 'Person Language', NULL);
Insert into DQA_CODE_TABLE
   (TABLE_ID, TABLE_LABEL, DEFAULT_CODE_VALUE)
 Values
   (21, 'Person Name Type', NULL);
Insert into DQA_CODE_TABLE
   (TABLE_ID, TABLE_LABEL, DEFAULT_CODE_VALUE)
 Values
   (22, 'Person Relationship', NULL);
Insert into DQA_CODE_TABLE
   (TABLE_ID, TABLE_LABEL, DEFAULT_CODE_VALUE)
 Values
   (23, 'Physician Number', NULL);
Insert into DQA_CODE_TABLE
   (TABLE_ID, TABLE_LABEL, DEFAULT_CODE_VALUE)
 Values
   (24, 'Registry Status', NULL);
Insert into DQA_CODE_TABLE
   (TABLE_ID, TABLE_LABEL, DEFAULT_CODE_VALUE)
 Values
   (25, 'Vaccination Action Code', 'A');
Insert into DQA_CODE_TABLE
   (TABLE_ID, TABLE_LABEL, DEFAULT_CODE_VALUE)
 Values
   (26, 'Vaccination Completion', NULL);
Insert into DQA_CODE_TABLE
   (TABLE_ID, TABLE_LABEL, DEFAULT_CODE_VALUE)
 Values
   (27, 'Vaccination Confidentiality', NULL);
Insert into DQA_CODE_TABLE
   (TABLE_ID, TABLE_LABEL, DEFAULT_CODE_VALUE)
 Values
   (28, 'Vaccination CPT Code', NULL);
Insert into DQA_CODE_TABLE
   (TABLE_ID, TABLE_LABEL, DEFAULT_CODE_VALUE)
 Values
   (29, 'Vaccination CVX Code', NULL);
Insert into DQA_CODE_TABLE
   (TABLE_ID, TABLE_LABEL, DEFAULT_CODE_VALUE)
 Values
   (30, 'Vaccination Information Source', NULL);
Insert into DQA_CODE_TABLE
   (TABLE_ID, TABLE_LABEL, DEFAULT_CODE_VALUE)
 Values
   (31, 'Vaccination Manufacturer Code', NULL);
Insert into DQA_CODE_TABLE
   (TABLE_ID, TABLE_LABEL, DEFAULT_CODE_VALUE)
 Values
   (32, 'Vaccination Refusal', NULL);
Insert into DQA_CODE_TABLE
   (TABLE_ID, TABLE_LABEL, DEFAULT_CODE_VALUE)
 Values
   (33, 'Vaccine Product', NULL);
COMMIT;

SET DEFINE OFF;
Insert into DQA_ISSUE_ACTION
   (ACTION_CODE, ACTION_LABEL)
 Values
   ('E', 'Error');
Insert into DQA_ISSUE_ACTION
   (ACTION_CODE, ACTION_LABEL)
 Values
   ('W', 'Warn');
Insert into DQA_ISSUE_ACTION
   (ACTION_CODE, ACTION_LABEL)
 Values
   ('A', 'Accept');
Insert into DQA_ISSUE_ACTION
   (ACTION_CODE, ACTION_LABEL)
 Values
   ('S', 'Skip');
COMMIT;


SET DEFINE OFF;
Insert into DQA_KEYED_SETTING
   (KEYED_ID, KEYED_CODE, OBJECT_CODE, OBJECT_ID, KEYED_VALUE)
 Values
   (1, 'in.file.wait', 'Application', 1, '15');
Insert into DQA_KEYED_SETTING
   (KEYED_ID, KEYED_CODE, OBJECT_CODE, OBJECT_ID, KEYED_VALUE)
 Values
   (2, 'in.file.enabled', 'Application', 1, 'Y');
Insert into DQA_KEYED_SETTING
   (KEYED_ID, KEYED_CODE, OBJECT_CODE, OBJECT_ID, KEYED_VALUE)
 Values
   (3, 'in.file.dir', 'Application', 1, 'c:\data\in\');
COMMIT;




SET DEFINE OFF;
Insert into DQA_ORGANIZATION
   (ORG_ID, ORG_LABEL, ORG_PARENT_ID, PRIMARY_PROFILE_ID, ORG_LOCAL_CODE)
 Values
   (261, 'Williams Family', 1, NULL, NULL);
Insert into DQA_ORGANIZATION
   (ORG_ID, ORG_LABEL, ORG_PARENT_ID, PRIMARY_PROFILE_ID, ORG_LOCAL_CODE)
 Values
   (262, 'Williams Family', 1, NULL, NULL);
Insert into DQA_ORGANIZATION
   (ORG_ID, ORG_LABEL, ORG_PARENT_ID, PRIMARY_PROFILE_ID, ORG_LOCAL_CODE)
 Values
   (264, 'Williams Family', 1, NULL, NULL);
Insert into DQA_ORGANIZATION
   (ORG_ID, ORG_LABEL, ORG_PARENT_ID, PRIMARY_PROFILE_ID, ORG_LOCAL_CODE)
 Values
   (266, 'Williams Family', 1, NULL, NULL);
Insert into DQA_ORGANIZATION
   (ORG_ID, ORG_LABEL, ORG_PARENT_ID, PRIMARY_PROFILE_ID, ORG_LOCAL_CODE)
 Values
   (267, 'Williams Family', 1, NULL, NULL);
Insert into DQA_ORGANIZATION
   (ORG_ID, ORG_LABEL, ORG_PARENT_ID, PRIMARY_PROFILE_ID, ORG_LOCAL_CODE)
 Values
   (272, 'Williams Family', 1, NULL, NULL);
Insert into DQA_ORGANIZATION
   (ORG_ID, ORG_LABEL, ORG_PARENT_ID, PRIMARY_PROFILE_ID, ORG_LOCAL_CODE)
 Values
   (280, 'Williams Family', 1, NULL, NULL);
Insert into DQA_ORGANIZATION
   (ORG_ID, ORG_LABEL, ORG_PARENT_ID, PRIMARY_PROFILE_ID, ORG_LOCAL_CODE)
 Values
   (281, 'Williams Family', 1, NULL, NULL);
Insert into DQA_ORGANIZATION
   (ORG_ID, ORG_LABEL, ORG_PARENT_ID, PRIMARY_PROFILE_ID, ORG_LOCAL_CODE)
 Values
   (263, 'Williams Family', 1, NULL, NULL);
Insert into DQA_ORGANIZATION
   (ORG_ID, ORG_LABEL, ORG_PARENT_ID, PRIMARY_PROFILE_ID, ORG_LOCAL_CODE)
 Values
   (282, '1124800189', 1, NULL, NULL);
Insert into DQA_ORGANIZATION
   (ORG_ID, ORG_LABEL, ORG_PARENT_ID, PRIMARY_PROFILE_ID, ORG_LOCAL_CODE)
 Values
   (283, '1124800189', 1, NULL, NULL);
Insert into DQA_ORGANIZATION
   (ORG_ID, ORG_LABEL, ORG_PARENT_ID, PRIMARY_PROFILE_ID, ORG_LOCAL_CODE)
 Values
   (284, 'TCHHL7', 1, NULL, '1124800189');
Insert into DQA_ORGANIZATION
   (ORG_ID, ORG_LABEL, ORG_PARENT_ID, PRIMARY_PROFILE_ID, ORG_LOCAL_CODE)
 Values
   (300, 'TCHHL7', 1, 300, NULL);
Insert into DQA_ORGANIZATION
   (ORG_ID, ORG_LABEL, ORG_PARENT_ID, PRIMARY_PROFILE_ID, ORG_LOCAL_CODE)
 Values
   (301, 'Williams Family', 1, 301, NULL);
Insert into DQA_ORGANIZATION
   (ORG_ID, ORG_LABEL, ORG_PARENT_ID, PRIMARY_PROFILE_ID, ORG_LOCAL_CODE)
 Values
   (260, 'Williams Family', 1, NULL, NULL);
Insert into DQA_ORGANIZATION
   (ORG_ID, ORG_LABEL, ORG_PARENT_ID, PRIMARY_PROFILE_ID, ORG_LOCAL_CODE)
 Values
   (265, 'Williams Family', 1, NULL, NULL);
Insert into DQA_ORGANIZATION
   (ORG_ID, ORG_LABEL, ORG_PARENT_ID, PRIMARY_PROFILE_ID, ORG_LOCAL_CODE)
 Values
   (268, 'Williams Family', 1, NULL, NULL);
Insert into DQA_ORGANIZATION
   (ORG_ID, ORG_LABEL, ORG_PARENT_ID, PRIMARY_PROFILE_ID, ORG_LOCAL_CODE)
 Values
   (269, 'Williams Family', 1, NULL, NULL);
Insert into DQA_ORGANIZATION
   (ORG_ID, ORG_LABEL, ORG_PARENT_ID, PRIMARY_PROFILE_ID, ORG_LOCAL_CODE)
 Values
   (270, 'Williams Family', 1, NULL, NULL);
Insert into DQA_ORGANIZATION
   (ORG_ID, ORG_LABEL, ORG_PARENT_ID, PRIMARY_PROFILE_ID, ORG_LOCAL_CODE)
 Values
   (271, 'Williams Family', 1, NULL, NULL);
Insert into DQA_ORGANIZATION
   (ORG_ID, ORG_LABEL, ORG_PARENT_ID, PRIMARY_PROFILE_ID, ORG_LOCAL_CODE)
 Values
   (241, 'Williams Family', 1, NULL, NULL);
Insert into DQA_ORGANIZATION
   (ORG_ID, ORG_LABEL, ORG_PARENT_ID, PRIMARY_PROFILE_ID, ORG_LOCAL_CODE)
 Values
   (242, 'Williams Family', 1, NULL, NULL);
Insert into DQA_ORGANIZATION
   (ORG_ID, ORG_LABEL, ORG_PARENT_ID, PRIMARY_PROFILE_ID, ORG_LOCAL_CODE)
 Values
   (320, 'Care360', 1, 320, NULL);
Insert into DQA_ORGANIZATION
   (ORG_ID, ORG_LABEL, ORG_PARENT_ID, PRIMARY_PROFILE_ID, ORG_LOCAL_CODE)
 Values
   (1, 'IIS', 1, 1, NULL);
Insert into DQA_ORGANIZATION
   (ORG_ID, ORG_LABEL, ORG_PARENT_ID, PRIMARY_PROFILE_ID, ORG_LOCAL_CODE)
 Values
   (240, 'Williams Family', 1, NULL, NULL);
COMMIT;


SET DEFINE OFF;
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (2, 'General', 'authorization', 'exception', NULL, 
    'E', 'Blocked', 'Message Count', 'Submitter is not authorized to submit messages');
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (3, 'General', 'configuration', 'exception', NULL, 
    'E', 'Blocked', 'Message Count', 'Profile is not configured properly, unable to accept messages');
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (4, 'General', 'parse', 'exception', NULL, 
    'E', 'Blocked', 'Message Count', 'Message can not be parsed');
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (5, 'General', 'processing', 'exception', NULL, 
    'E', 'Blocked', 'Message Count', 'General unexpected exception while processing message');
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (6, 'HL7 MSH', 'acknowledgement type', 'is missing', NULL, 
    'A', 'May', 'Message Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (7, 'HL7 MSH', 'acknowledgement type', 'is unrecognized', NULL, 
    'W', 'May', 'Message Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (8, 'HL7 MSH', 'acknowledgement type', 'is valued as', 'always', 
    'A', 'May', 'Message Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (9, 'HL7 MSH', 'acknowledgement type', 'is valued as', 'never', 
    'A', 'May', 'Message Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (10, 'HL7 MSH', 'acknowledgement type', 'is valued as', 'only on errors', 
    'A', 'May', 'Message Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (11, 'HL7 MSH', 'encoding character', 'is invalid', NULL, 
    'E', 'Blocked', 'Message Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (12, 'HL7 MSH', 'encoding character', 'is missing', NULL, 
    'E', 'Blocked', 'Message Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (13, 'HL7 MSH', 'encoding character', 'is non-standard', NULL, 
    'E', 'May', 'Message Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (14, 'HL7 MSH', 'message control id', 'is missing', NULL, 
    'W', 'May', 'Message Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (15, 'HL7 MSH', 'message date', 'is in future', NULL, 
    'E', 'May', 'Message Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (16, 'HL7 MSH', 'message date', 'is invalid', NULL, 
    'E', 'May', 'Message Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (17, 'HL7 MSH', 'message date', 'is missing', NULL, 
    'E', 'May', 'Message Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (18, 'HL7 MSH', 'message trigger', 'is missing', NULL, 
    'W', 'May', 'Message Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (19, 'HL7 MSH', 'message trigger', 'is unrecognized', NULL, 
    'E', 'May', 'Message Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (20, 'HL7 MSH', 'message type', 'is missing', NULL, 
    'W', 'May', 'Message Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (21, 'HL7 MSH', 'message type', 'is unrecognized', NULL, 
    'E', 'Blocked', 'Message Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (22, 'HL7 MSH', 'message type', 'is unsupported', NULL, 
    'E', 'Blocked', 'Message Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (23, 'HL7 MSH', 'processing id', 'is missing', NULL, 
    'A', 'May', 'Message Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (24, 'HL7 MSH', 'processing id', 'is valued as', 'debug', 
    'A', 'May', 'Message Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (25, 'HL7 MSH', 'processing id', 'is valued as', 'production', 
    'A', 'May', 'Message Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (26, 'HL7 MSH', 'processing id', 'is valued as', 'training', 
    'A', 'May', 'Message Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (27, 'HL7 MSH', 'profile id', 'is missing', NULL, 
    'A', 'May', 'Message Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (28, 'HL7 MSH', 'profile id', 'is unrecognized', NULL, 
    'A', 'May', 'Message Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (29, 'HL7 MSH', 'receiving application', 'is invalid', NULL, 
    'W', 'May', 'Message Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (30, 'HL7 MSH', 'receiving application', 'is missing', NULL, 
    'A', 'May', 'Message Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (31, 'HL7 MSH', 'receiving facility', 'is invalid', NULL, 
    'W', 'May', 'Message Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (32, 'HL7 MSH', 'receiving facility', 'is missing', NULL, 
    'A', 'May', 'Message Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (33, 'HL7 MSH', 'segment', 'is missing', NULL, 
    'E', 'Blocked', 'Message Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (34, 'HL7 MSH', 'sending application', 'is invalid', NULL, 
    'W', 'May', 'Message Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (35, 'HL7 MSH', 'sending application', 'is missing', NULL, 
    'A', 'May', 'Message Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (36, 'HL7 MSH', 'sending facility', 'is invalid', NULL, 
    'W', 'May', 'Message Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (37, 'HL7 MSH', 'sending facility', 'is missing', NULL, 
    'A', 'May', 'Message Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (38, 'HL7 MSH', 'version', 'is missing', NULL, 
    'A', 'May', 'Message Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (39, 'HL7 MSH', 'version', 'is unrecognized', NULL, 
    'W', 'May', 'Message Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (40, 'HL7 MSH', 'version', 'is valued as', '2.3.1', 
    'A', 'May', 'Message Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (41, 'HL7 MSH', 'version', 'is valued as', '2.4', 
    'A', 'May', 'Message Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (42, 'HL7 MSH', 'version', 'is valued as', '2.5', 
    'A', 'May', 'Message Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (43, 'HL7 NK1', 'segment', 'is missing', NULL, 
    'W', 'Should', 'Next-of-Kin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (44, 'HL7 NK1', 'segment', 'is repeated', NULL, 
    'A', 'May', 'Next-of-Kin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (45, 'HL7 OBX', 'segment', 'is missing', NULL, 
    'A', 'May', 'Observation Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (46, 'HL7 ORC', 'segment', 'is missing', NULL, 
    'A', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (47, 'HL7 ORC', 'segment', 'is repeated', NULL, 
    'E', 'Blocked', 'Message Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (48, 'HL7 PD1', 'segment', 'is missing', NULL, 
    'A', 'May', 'Message Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (49, 'HL7 PID', 'segment', 'is missing', NULL, 
    'E', 'Blocked', 'Message Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (50, 'HL7 PID', 'segment', 'is repeated', NULL, 
    'E', 'Blocked', 'Message Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (51, 'HL7 PV1', 'segment', 'is missing', NULL, 
    'A', 'May', 'Message Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (52, 'HL7 RXA', 'segment', 'is missing', NULL, 
    'W', 'Can', 'Message Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (53, 'HL7 RXA', 'segment', 'is repeated', NULL, 
    'A', 'Can', 'Message Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (54, 'HL7 RXR', 'segment', 'is missing', NULL, 
    'A', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (55, 'HL7 RXR', 'segment', 'is repeated', NULL, 
    'A', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (56, 'Next-of-kin', 'address', 'is different from patient address', NULL, 
    'A', 'May', 'Next-of-Kin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (57, 'Next-of-kin', 'address', 'is missing', NULL, 
    'A', 'May', 'Next-of-Kin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (58, 'Next-of-kin', 'address city', 'is invalid', NULL, 
    'W', 'May', 'Next-of-Kin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (59, 'Next-of-kin', 'address city', 'is missing', NULL, 
    'A', 'May', 'Next-of-Kin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (60, 'Next-of-kin', 'address country', 'is deprecated', NULL, 
    'W', 'May', 'Next-of-Kin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (61, 'Next-of-kin', 'address country', 'is ignored', NULL, 
    'S', 'May', 'Next-of-Kin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (62, 'Next-of-kin', 'address country', 'is invalid', NULL, 
    'W', 'May', 'Next-of-Kin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (63, 'Next-of-kin', 'address country', 'is missing', NULL, 
    'A', 'May', 'Next-of-Kin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (64, 'Next-of-kin', 'address country', 'is unrecognized', NULL, 
    'W', 'May', 'Next-of-Kin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (65, 'Next-of-kin', 'address county', 'is deprecated', NULL, 
    'W', 'May', 'Next-of-Kin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (66, 'Next-of-kin', 'address county', 'is ignored', NULL, 
    'S', 'May', 'Next-of-Kin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (67, 'Next-of-kin', 'address county', 'is invalid', NULL, 
    'W', 'May', 'Next-of-Kin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (68, 'Next-of-kin', 'address county', 'is missing', NULL, 
    'A', 'May', 'Next-of-Kin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (69, 'Next-of-kin', 'address county', 'is unrecognized', NULL, 
    'W', 'May', 'Next-of-Kin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (70, 'Next-of-kin', 'address state', 'is deprecated', NULL, 
    'W', 'May', 'Next-of-Kin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (71, 'Next-of-kin', 'address state', 'is ignored', NULL, 
    'S', 'May', 'Next-of-Kin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (72, 'Next-of-kin', 'address state', 'is invalid', NULL, 
    'W', 'May', 'Next-of-Kin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (73, 'Next-of-kin', 'address state', 'is missing', NULL, 
    'A', 'May', 'Next-of-Kin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (74, 'Next-of-kin', 'address state', 'is unrecognized', NULL, 
    'W', 'May', 'Next-of-Kin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (75, 'Next-of-kin', 'address street', 'is missing', NULL, 
    'A', 'May', 'Next-of-Kin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (76, 'Next-of-kin', 'address street2', 'is missing', NULL, 
    'A', 'May', 'Next-of-Kin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (77, 'Next-of-kin', 'address zip', 'is invalid', NULL, 
    'W', 'May', 'Next-of-Kin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (78, 'Next-of-kin', 'address zip', 'is missing', NULL, 
    'A', 'May', 'Next-of-Kin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (79, 'Next-of-kin', 'name', 'is missing', NULL, 
    'W', 'Can', 'Next-of-Kin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (80, 'Next-of-kin', 'name first', 'is missing', NULL, 
    'W', 'Can', 'Next-of-Kin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (81, 'Next-of-kin', 'name last', 'is missing', NULL, 
    'W', 'Can', 'Next-of-Kin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (82, 'Next-of-kin', 'phone number', 'is incomplete', NULL, 
    'W', 'May', 'Next-of-Kin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (83, 'Next-of-kin', 'phone number', 'is invalid', NULL, 
    'W', 'May', 'Next-of-Kin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (84, 'Next-of-kin', 'phone number', 'is missing', NULL, 
    'A', 'Can', 'Next-of-Kin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (85, 'Next-of-kin', 'relationship', 'is deprecated', NULL, 
    'W', 'May', 'Next-of-Kin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (86, 'Next-of-kin', 'relationship', 'is ignored', NULL, 
    'S', 'May', 'Next-of-Kin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (87, 'Next-of-kin', 'relationship', 'is invalid', NULL, 
    'W', 'May', 'Next-of-Kin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (88, 'Next-of-kin', 'relationship', 'is missing', NULL, 
    'W', 'Should', 'Next-of-Kin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (89, 'Next-of-kin', 'relationship', 'is not responsible party', NULL, 
    'A', 'May', 'Next-of-Kin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (90, 'Next-of-kin', 'relationship', 'is unrecognized', NULL, 
    'W', 'May', 'Next-of-Kin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (91, 'Next-of-kin', 'SSN', 'is missing', NULL, 
    'A', 'May', 'Next-of-Kin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (92, 'Patient', 'address', 'is missing', NULL, 
    'W', 'Should', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (93, 'Patient', 'address city', 'is invalid', NULL, 
    'E', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (94, 'Patient', 'address city', 'is missing', NULL, 
    'W', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (95, 'Patient', 'address country', 'is deprecated', NULL, 
    'W', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (96, 'Patient', 'address country', 'is ignored', NULL, 
    'S', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (97, 'Patient', 'address country', 'is invalid', NULL, 
    'W', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (98, 'Patient', 'address country', 'is missing', NULL, 
    'A', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (99, 'Patient', 'address country', 'is unrecognized', NULL, 
    'W', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (100, 'Patient', 'address county', 'is deprecated', NULL, 
    'W', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (101, 'Patient', 'address county', 'is ignored', NULL, 
    'S', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (102, 'Patient', 'address county', 'is invalid', NULL, 
    'W', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (103, 'Patient', 'address county', 'is missing', NULL, 
    'A', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (104, 'Patient', 'address county', 'is unrecognized', NULL, 
    'W', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (105, 'Patient', 'address state', 'is deprecated', NULL, 
    'W', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (106, 'Patient', 'address state', 'is ignored', NULL, 
    'S', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (107, 'Patient', 'address state', 'is invalid', NULL, 
    'W', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (108, 'Patient', 'address state', 'is missing', NULL, 
    'W', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (109, 'Patient', 'address state', 'is unrecognized', NULL, 
    'W', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (110, 'Patient', 'address street', 'is missing', NULL, 
    'W', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (111, 'Patient', 'address street2', 'is missing', NULL, 
    'A', 'Can', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (112, 'Patient', 'address zip', 'is invalid', NULL, 
    'W', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (113, 'Patient', 'address zip', 'is missing', NULL, 
    'W', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (114, 'Patient', 'alias', 'is missing', NULL, 
    'A', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (115, 'Patient', 'birth date', 'is after submission', NULL, 
    'E', 'Blocked', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (116, 'Patient', 'birth date', 'is in future', NULL, 
    'E', 'Blocked', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (117, 'Patient', 'birth date', 'is invalid', NULL, 
    'E', 'Blocked', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (118, 'Patient', 'birth date', 'is missing', NULL, 
    'E', 'Blocked', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (119, 'Patient', 'birth date', 'is underage', NULL, 
    'A', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (120, 'Patient', 'birth date', 'is very long ago', NULL, 
    'E', 'Blocked', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (121, 'Patient', 'birth indicator', 'is invalid', NULL, 
    'W', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (122, 'Patient', 'birth indicator', 'is missing', NULL, 
    'A', 'Should', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (123, 'Patient', 'birth order', 'is invalid', NULL, 
    'W', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (124, 'Patient', 'birth order', 'is missing', NULL, 
    'A', 'Should', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (125, 'Patient', 'birth order', 'is missing and multiple birth indicated', NULL, 
    'E', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (126, 'Patient', 'birth place', 'is missing', NULL, 
    'A', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (127, 'Patient', 'birth registry id', 'is invalid', NULL, 
    'W', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (128, 'Patient', 'birth registry id', 'is missing', NULL, 
    'A', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (129, 'Patient', 'death date', 'is before birth', NULL, 
    'E', 'Blocked', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (130, 'Patient', 'death date', 'is in future', NULL, 
    'E', 'Blocked', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (131, 'Patient', 'death date', 'is invalid', NULL, 
    'E', 'Blocked', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (132, 'Patient', 'death date', 'is missing', NULL, 
    'A', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (133, 'Patient', 'death indicator', 'is inconsistent', NULL, 
    'E', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (134, 'Patient', 'death indicator', 'is missing', NULL, 
    'A', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (135, 'Patient', 'ethnicity', 'is deprecated', NULL, 
    'W', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (136, 'Patient', 'ethnicity', 'is ignored', NULL, 
    'S', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (137, 'Patient', 'ethnicity', 'is invalid', NULL, 
    'W', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (138, 'Patient', 'ethnicity', 'is missing', NULL, 
    'A', 'Should', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (139, 'Patient', 'ethnicity', 'is unrecognized', NULL, 
    'W', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (140, 'Patient', 'first name', 'is invalid', NULL, 
    'E', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (141, 'Patient', 'first name', 'is missing', NULL, 
    'E', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (142, 'Patient', 'first name', 'may include middle initial', NULL, 
    'A', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (143, 'Patient', 'gender', 'is deprecated', NULL, 
    'W', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (144, 'Patient', 'gender', 'is ignored', NULL, 
    'S', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (145, 'Patient', 'gender', 'is invalid', NULL, 
    'E', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (146, 'Patient', 'gender', 'is missing', NULL, 
    'E', 'Blocked', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (147, 'Patient', 'gender', 'is unrecognized', NULL, 
    'E', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (148, 'Patient', 'guardian address', 'is missing', NULL, 
    'A', 'May', 'Patient Underage Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (149, 'Patient', 'guardian address city', 'is missing', NULL, 
    'A', 'May', 'Patient Underage Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (150, 'Patient', 'guardian address state', 'is missing', NULL, 
    'A', 'May', 'Patient Underage Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (151, 'Patient', 'guardian address street', 'is missing', NULL, 
    'A', 'May', 'Patient Underage Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (152, 'Patient', 'guardian address zip', 'is missing', NULL, 
    'A', 'May', 'Patient Underage Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (153, 'Patient', 'guardian first name', 'is missing', NULL, 
    'W', 'May', 'Patient Underage Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (154, 'Patient', 'guardian last name', 'is missing', NULL, 
    'W', 'May', 'Patient Underage Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (155, 'Patient', 'guardian name', 'is missing', NULL, 
    'A', 'May', 'Patient Underage Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (156, 'Patient', 'guardian name', 'is same as underage patient', NULL, 
    'A', 'May', 'Patient Underage Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (157, 'Patient', 'guardian party', 'is missing', NULL, 
    'W', 'May', 'Patient Underage Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (158, 'Patient', 'guardian phone', 'is missing', NULL, 
    'A', 'May', 'Patient Underage Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (159, 'Patient', 'guardian relationship', 'is missing', NULL, 
    'W', 'May', 'Patient Underage Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (160, 'Patient', 'immunization registry status', 'is deprecated', NULL, 
    'W', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (161, 'Patient', 'immunization registry status', 'is ignored', NULL, 
    'S', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (162, 'Patient', 'immunization registry status', 'is invalid', NULL, 
    'W', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (163, 'Patient', 'immunization registry status', 'is missing', NULL, 
    'A', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (164, 'Patient', 'immunization registry status', 'is unrecognized', NULL, 
    'W', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (165, 'Patient', 'last name', 'is invalid', NULL, 
    'E', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (166, 'Patient', 'last name', 'is missing', NULL, 
    'E', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (167, 'Patient', 'Medicaid number', 'is invalid', NULL, 
    'A', 'Can', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (168, 'Patient', 'Medicaid number', 'is missing', NULL, 
    'A', 'Can', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (169, 'Patient', 'middle name', 'is missing', NULL, 
    'A', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (170, 'Patient', 'middle name', 'may be initial', NULL, 
    'A', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (171, 'Patient', 'mother''s maiden name', 'is missing', NULL, 
    'A', 'Should', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (172, 'Patient', 'name', 'may be temporary newborn name', NULL, 
    'A', 'Should', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (173, 'Patient', 'name', 'may be test name', NULL, 
    'A', 'Should', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (174, 'Patient', 'phone', 'is incomplete', NULL, 
    'W', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (175, 'Patient', 'phone', 'is invalid', NULL, 
    'W', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (176, 'Patient', 'phone', 'is missing', NULL, 
    'A', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (177, 'Patient', 'primary facility id', 'is deprecated', NULL, 
    'W', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (178, 'Patient', 'primary facility id', 'is ignored', NULL, 
    'S', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (179, 'Patient', 'primary facility id', 'is invalid', NULL, 
    'E', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (180, 'Patient', 'primary facility id', 'is missing', NULL, 
    'A', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (181, 'Patient', 'primary facility id', 'is unrecognized', NULL, 
    'W', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (182, 'Patient', 'primary facility name', 'is missing', NULL, 
    'A', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (183, 'Patient', 'primary lanaguage', 'is deprecated', NULL, 
    'A', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (184, 'Patient', 'primary lanaguage', 'is ignored', NULL, 
    'S', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (185, 'Patient', 'primary lanaguage', 'is invalid', NULL, 
    'W', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (186, 'Patient', 'primary lanaguage', 'is missing', NULL, 
    'A', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (187, 'Patient', 'primary lanaguage', 'is unrecognized', NULL, 
    'W', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (188, 'Patient', 'primary physician id', 'is deprecated', NULL, 
    'W', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (189, 'Patient', 'primary physician id', 'is ignored', NULL, 
    'S', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (190, 'Patient', 'primary physician id', 'is invalid', NULL, 
    'W', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (191, 'Patient', 'primary physician id', 'is missing', NULL, 
    'A', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (192, 'Patient', 'primary physician id', 'is unrecognized', NULL, 
    'W', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (193, 'Patient', 'primary physician name', 'is missing', NULL, 
    'A', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (194, 'Patient', 'protection indicator', 'is deprecated', NULL, 
    'W', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (195, 'Patient', 'protection indicator', 'is ignored', NULL, 
    'S', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (196, 'Patient', 'protection indicator', 'is invalid', NULL, 
    'W', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (197, 'Patient', 'protection indicator', 'is missing', NULL, 
    'A', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (198, 'Patient', 'protection indicator', 'is unrecognized', NULL, 
    'W', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (199, 'Patient', 'protection indicator', 'is valued as', 'no', 
    'W', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (200, 'Patient', 'protection indicator', 'is valued as', 'yes', 
    'W', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (201, 'Patient', 'publicity code', 'is deprecated', NULL, 
    'W', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (202, 'Patient', 'publicity code', 'is ignored', NULL, 
    'S', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (203, 'Patient', 'publicity code', 'is invalid', NULL, 
    'W', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (204, 'Patient', 'publicity code', 'is missing', NULL, 
    'A', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (205, 'Patient', 'publicity code', 'is unrecognized', NULL, 
    'W', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (206, 'Patient', 'race', 'is deprecated', NULL, 
    'W', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (207, 'Patient', 'race', 'is ignored', NULL, 
    'S', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (208, 'Patient', 'race', 'is invalid', NULL, 
    'W', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (209, 'Patient', 'race', 'is missing', NULL, 
    'A', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (210, 'Patient', 'race', 'is unrecognized', NULL, 
    'W', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (211, 'Patient', 'registry id', 'is missing', NULL, 
    'A', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (212, 'Patient', 'registry id', 'is unrecognized', NULL, 
    'A', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (213, 'Patient', 'registry status', 'is deprecated', NULL, 
    'W', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (214, 'Patient', 'registry status', 'is ignored', NULL, 
    'S', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (215, 'Patient', 'registry status', 'is invalid', NULL, 
    'W', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (216, 'Patient', 'registry status', 'is missing', NULL, 
    'A', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (217, 'Patient', 'registry status', 'is unrecognized', NULL, 
    'W', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (218, 'Patient', 'SSN', 'is invalid', NULL, 
    'W', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (219, 'Patient', 'SSN', 'is missing', NULL, 
    'A', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (220, 'Patient', 'submitter id', 'is missing', NULL, 
    'E', 'Blocked', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (221, 'Patient', 'VFC effective date', 'is before birth', NULL, 
    'E', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (222, 'Patient', 'VFC effective date', 'is in future', NULL, 
    'E', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (223, 'Patient', 'VFC effective date', 'is invalid', NULL, 
    'E', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (224, 'Patient', 'VFC effective date', 'is missing', NULL, 
    'A', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (225, 'Patient', 'VFC status', 'is deprecated', NULL, 
    'W', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (226, 'Patient', 'VFC status', 'is ignored', NULL, 
    'S', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (227, 'Patient', 'VFC status', 'is invalid', NULL, 
    'W', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (228, 'Patient', 'VFC status', 'is missing', NULL, 
    'A', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (229, 'Patient', 'VFC status', 'is unrecognized', NULL, 
    'W', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (230, 'Patient', 'WIC id', 'is invalid', NULL, 
    'A', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (231, 'Patient', 'WIC id', 'is missing', NULL, 
    'A', 'May', 'Patient Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (232, 'Vaccination', 'action code', 'is deprecated', NULL, 
    'W', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (233, 'Vaccination', 'action code', 'is ignored', NULL, 
    'S', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (234, 'Vaccination', 'action code', 'is invalid', NULL, 
    'E', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (235, 'Vaccination', 'action code', 'is missing', NULL, 
    'A', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (236, 'Vaccination', 'action code', 'is unrecognized', NULL, 
    'W', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (237, 'Vaccination', 'action code', 'is valued as', 'add', 
    'A', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (238, 'Vaccination', 'action code', 'is valued as', 'add or update', 
    'A', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (239, 'Vaccination', 'action code', 'is valued as', 'delete', 
    'A', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (240, 'Vaccination', 'action code', 'is valued as', 'update', 
    'A', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (241, 'Vaccination', 'admin code', 'is deprecated', NULL, 
    'W', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (242, 'Vaccination', 'admin code', 'is ignored', NULL, 
    'S', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (243, 'Vaccination', 'admin code', 'is invalid', NULL, 
    'E', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (244, 'Vaccination', 'admin code', 'is missing', NULL, 
    'E', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (245, 'Vaccination', 'admin code', 'is not specific', NULL, 
    'W', 'May', 'Vaccination Admin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (246, 'Vaccination', 'admin code', 'is not vaccine', NULL, 
    'W', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (247, 'Vaccination', 'admin code', 'is unrecognized', NULL, 
    'E', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (248, 'Vaccination', 'admin code', 'is valued as', 'not administered', 
    'S', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (249, 'Vaccination', 'admin code', 'is valued as', 'unknown', 
    'W', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (250, 'Vaccination', 'admin code', 'may be variation of previously reported codes', NULL, 
    'W', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (251, 'Vaccination', 'admin date', 'is after lot expiration date', NULL, 
    'W', 'May', 'Vaccination Admin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (252, 'Vaccination', 'admin date', 'is after message submitted', NULL, 
    'E', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (253, 'Vaccination', 'admin date', 'is after patient death date', NULL, 
    'E', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (254, 'Vaccination', 'admin date', 'is after system entry date', NULL, 
    'E', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (255, 'Vaccination', 'admin date', 'is before birth', NULL, 
    'E', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (256, 'Vaccination', 'admin date', 'is before or after expected vaccine usage range', NULL, 
    'W', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (257, 'Vaccination', 'admin date', 'is before or after licensed vaccine range', NULL, 
    'E', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (258, 'Vaccination', 'admin date', 'is before or after when expected for patient age', NULL, 
    'W', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (259, 'Vaccination', 'admin date', 'is before or after when valid for patient age', NULL, 
    'E', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (260, 'Vaccination', 'admin date', 'is invalid', NULL, 
    'E', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (261, 'Vaccination', 'admin date', 'is missing', NULL, 
    'E', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (262, 'Vaccination', 'admin date', 'is on 15th day of month', NULL, 
    'A', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (263, 'Vaccination', 'admin date', 'is on first day of month', NULL, 
    'A', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (264, 'Vaccination', 'admin date', 'is on last day of month', NULL, 
    'A', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (265, 'Vaccination', 'admin date', 'is reported late', NULL, 
    'W', 'May', 'Vaccination Admin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (266, 'Vaccination', 'admin date end', 'is different from start date', NULL, 
    'W', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (267, 'Vaccination', 'admin date end', 'is missing', NULL, 
    'A', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (268, 'Vaccination', 'administered amount', 'is invalid', NULL, 
    'W', 'May', 'Vaccination Admin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (269, 'Vaccination', 'administered amount', 'is missing', NULL, 
    'A', 'May', 'Vaccination Admin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (270, 'Vaccination', 'administered amount', 'is valued as', 'zero', 
    'A', 'May', 'Vaccination Admin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (271, 'Vaccination', 'administered amount', 'is valued as', 'unknown', 
    'A', 'May', 'Vaccination Admin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (272, 'Vaccination', 'administered unit', 'is missing', NULL, 
    'A', 'May', 'Vaccination Admin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (273, 'Vaccination', 'body route', 'is deprecated', NULL, 
    'W', 'May', 'Vaccination Admin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (274, 'Vaccination', 'body route', 'is ignored', NULL, 
    'S', 'May', 'Vaccination Admin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (275, 'Vaccination', 'body route', 'is invalid', NULL, 
    'W', 'May', 'Vaccination Admin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (276, 'Vaccination', 'body route', 'is invalid for vaccine indicated', NULL, 
    'W', 'May', 'Vaccination Admin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (277, 'Vaccination', 'body route', 'is missing', NULL, 
    'A', 'May', 'Vaccination Admin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (278, 'Vaccination', 'body route', 'is unrecognized', NULL, 
    'W', 'May', 'Vaccination Admin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (279, 'Vaccination', 'body site', 'is deprecated', NULL, 
    'W', 'May', 'Vaccination Admin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (280, 'Vaccination', 'body site', 'is ignored', NULL, 
    'S', 'May', 'Vaccination Admin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (281, 'Vaccination', 'body site', 'is invalid', NULL, 
    'W', 'May', 'Vaccination Admin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (282, 'Vaccination', 'body site', 'is invalid for vaccine indicated', NULL, 
    'W', 'May', 'Vaccination Admin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (283, 'Vaccination', 'body site', 'is missing', NULL, 
    'A', 'May', 'Vaccination Admin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (284, 'Vaccination', 'body site', 'is unrecognized', NULL, 
    'W', 'May', 'Vaccination Admin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (285, 'Vaccination', 'completion status', 'is deprecated', NULL, 
    'W', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (286, 'Vaccination', 'completion status', 'is ignored', NULL, 
    'S', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (287, 'Vaccination', 'completion status', 'is invalid', NULL, 
    'E', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (288, 'Vaccination', 'completion status', 'is missing', NULL, 
    'A', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (289, 'Vaccination', 'completion status', 'is unrecognized', NULL, 
    'E', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (290, 'Vaccination', 'completion status', 'is valued as', 'completed', 
    'A', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (291, 'Vaccination', 'completion status', 'is valued as', 'not administered', 
    'S', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (292, 'Vaccination', 'completion status', 'is valued as', 'partially administered', 
    'S', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (293, 'Vaccination', 'completion status', 'is valued as', 'refused', 
    'S', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (294, 'Vaccination', 'confidentiality code', 'is deprecated', NULL, 
    'W', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (295, 'Vaccination', 'confidentiality code', 'is ignored', NULL, 
    'S', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (296, 'Vaccination', 'confidentiality code', 'is invalid', NULL, 
    'E', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (297, 'Vaccination', 'confidentiality code', 'is missing', NULL, 
    'A', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (298, 'Vaccination', 'confidentiality code', 'is unrecognized', NULL, 
    'W', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (299, 'Vaccination', 'confidentiality code', 'is valued as', 'restricted', 
    'W', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (300, 'Vaccination', 'CPT code', 'is deprecated', NULL, 
    'W', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (301, 'Vaccination', 'CPT code', 'is ignored', NULL, 
    'S', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (302, 'Vaccination', 'CPT code', 'is invalid', NULL, 
    'W', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (303, 'Vaccination', 'CPT code', 'is missing', NULL, 
    'A', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (304, 'Vaccination', 'CPT code', 'is unrecognized', NULL, 
    'W', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (305, 'Vaccination', 'CVX code', 'is deprecated', NULL, 
    'W', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (306, 'Vaccination', 'CVX code', 'is ignored', NULL, 
    'S', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (307, 'Vaccination', 'CVX code', 'is invalid', NULL, 
    'W', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (308, 'Vaccination', 'CVX code', 'is missing', NULL, 
    'A', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (309, 'Vaccination', 'CVX code', 'is unrecognized', NULL, 
    'W', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (310, 'Vaccination', 'CVX code and CPT code', 'are inconsistent', NULL, 
    'W', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (311, 'Vaccination', 'facility id', 'is deprecated', NULL, 
    'W', 'May', 'Vaccination Admin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (312, 'Vaccination', 'facility id', 'is ignored', NULL, 
    'S', 'May', 'Vaccination Admin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (313, 'Vaccination', 'facility id', 'is invalid', NULL, 
    'E', 'May', 'Vaccination Admin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (314, 'Vaccination', 'facility id', 'is missing', NULL, 
    'W', 'May', 'Vaccination Admin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (315, 'Vaccination', 'facility id', 'is unrecognized', NULL, 
    'W', 'May', 'Vaccination Admin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (316, 'Vaccination', 'facility name', 'is missing', NULL, 
    'A', 'May', 'Vaccination Admin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (317, 'Vaccination', 'given by', 'is deprecated', NULL, 
    'W', 'May', 'Vaccination Admin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (318, 'Vaccination', 'given by', 'is ignored', NULL, 
    'S', 'May', 'Vaccination Admin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (319, 'Vaccination', 'given by', 'is invalid', NULL, 
    'W', 'May', 'Vaccination Admin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (320, 'Vaccination', 'given by', 'is missing', NULL, 
    'A', 'May', 'Vaccination Admin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (321, 'Vaccination', 'given by', 'is unrecognized', NULL, 
    'W', 'May', 'Vaccination Admin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (322, 'Vaccination', 'id', 'is missing', NULL, 
    'A', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (323, 'Vaccination', 'id of receiver', 'is missing', NULL, 
    'A', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (324, 'Vaccination', 'id of receiver', 'is unrecognized', NULL, 
    'W', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (325, 'Vaccination', 'id of sender', 'is missing', NULL, 
    'A', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (326, 'Vaccination', 'id of sender', 'is unrecognized', NULL, 
    'W', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (327, 'Vaccination', 'information source', 'is administered but appears to historical', NULL, 
    'W', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (328, 'Vaccination', 'information source', 'is deprecated', NULL, 
    'W', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (329, 'Vaccination', 'information source', 'is historical but appears to be administered', NULL, 
    'W', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (330, 'Vaccination', 'information source', 'is ignored', NULL, 
    'S', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (331, 'Vaccination', 'information source', 'is invalid', NULL, 
    'E', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (332, 'Vaccination', 'information source', 'is missing', NULL, 
    'E', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (333, 'Vaccination', 'information source', 'is unrecognized', NULL, 
    'E', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (334, 'Vaccination', 'information source', 'is valued as', 'administered', 
    'A', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (335, 'Vaccination', 'information source', 'is valued as', 'historical', 
    'A', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (336, 'Vaccination', 'lot expiration date', 'is invalid', NULL, 
    'W', 'May', 'Vaccination Admin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (337, 'Vaccination', 'lot expiration date', 'is missing', NULL, 
    'A', 'May', 'Vaccination Admin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (338, 'Vaccination', 'lot number', 'is invalid', NULL, 
    'W', 'May', 'Vaccination Admin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (339, 'Vaccination', 'lot number', 'is missing', NULL, 
    'W', 'May', 'Vaccination Admin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (340, 'Vaccination', 'manufacturer code', 'is deprecated', NULL, 
    'W', 'May', 'Vaccination Admin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (341, 'Vaccination', 'manufacturer code', 'is ignored', NULL, 
    'S', 'May', 'Vaccination Admin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (342, 'Vaccination', 'manufacturer code', 'is invalid', NULL, 
    'W', 'May', 'Vaccination Admin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (343, 'Vaccination', 'manufacturer code', 'is missing', NULL, 
    'W', 'May', 'Vaccination Admin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (344, 'Vaccination', 'manufacturer code', 'is unrecognized', NULL, 
    'W', 'May', 'Vaccination Admin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (345, 'Vaccination', 'ordered by', 'is deprecated', NULL, 
    'W', 'May', 'Vaccination Admin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (346, 'Vaccination', 'ordered by', 'is ignored', NULL, 
    'S', 'May', 'Vaccination Admin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (347, 'Vaccination', 'ordered by', 'is invalid', NULL, 
    'W', 'May', 'Vaccination Admin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (348, 'Vaccination', 'ordered by', 'is missing', NULL, 
    'A', 'May', 'Vaccination Admin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (349, 'Vaccination', 'ordered by', 'is unrecognized', NULL, 
    'W', 'May', 'Vaccination Admin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (350, 'Vaccination', 'Product', 'is deprecated', NULL, 
    'W', 'May', 'Vaccination Admin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (351, 'Vaccination', 'Product', 'is invalid', NULL, 
    'E', 'May', 'Vaccination Admin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (352, 'Vaccination', 'Product', 'is missing', NULL, 
    'A', 'May', 'Vaccination Admin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (353, 'Vaccination', 'Product', 'is unrecognized', NULL, 
    'W', 'May', 'Vaccination Admin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (354, 'Vaccination', 'recorded by', 'is deprecated', NULL, 
    'W', 'May', 'Vaccination Admin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (355, 'Vaccination', 'recorded by', 'is ignored', NULL, 
    'S', 'May', 'Vaccination Admin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (356, 'Vaccination', 'recorded by', 'is invalid', NULL, 
    'W', 'May', 'Vaccination Admin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (357, 'Vaccination', 'recorded by', 'is missing', NULL, 
    'A', 'May', 'Vaccination Admin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (358, 'Vaccination', 'recorded by', 'is unrecognized', NULL, 
    'W', 'May', 'Vaccination Admin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (359, 'Vaccination', 'refusal reason', 'conflicts completion status', NULL, 
    'E', 'May', 'Vaccination Admin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (360, 'Vaccination', 'refusal reason', 'is deprecated', NULL, 
    'W', 'May', 'Vaccination Admin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (361, 'Vaccination', 'refusal reason', 'is ignored', NULL, 
    'S', 'May', 'Vaccination Admin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (362, 'Vaccination', 'refusal reason', 'is invalid', NULL, 
    'E', 'May', 'Vaccination Admin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (363, 'Vaccination', 'refusal reason', 'is missing', NULL, 
    'A', 'May', 'Vaccination Admin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (364, 'Vaccination', 'refusal reason', 'is unrecognized', NULL, 
    'E', 'May', 'Vaccination Admin Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (365, 'Vaccination', 'system entry time', 'is in future', NULL, 
    'E', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (366, 'Vaccination', 'system entry time', 'is invalid', NULL, 
    'E', 'May', 'Vaccination Count', NULL);
Insert into DQA_POTENTIAL_ISSUE
   (ISSUE_ID, TARGET_OBJECT, TARGET_FIELD, ISSUE_TYPE, FIELD_VALUE, 
    DEFAULT_ACTION_CODE, CHANGE_PRIORITY, REPORT_DENOMINATOR, ISSUE_DESCRIPTION)
 Values
   (367, 'Vaccination', 'system entry time', 'is missing', NULL, 
    'A', 'May', 'Vaccination Count', NULL);
COMMIT;



SET DEFINE OFF;
Insert into DQA_SUBMIT_STATUS
   (SUBMIT_CODE, SUBMIT_LABEL)
 Values
   ('E', 'Excluded');
Insert into DQA_SUBMIT_STATUS
   (SUBMIT_CODE, SUBMIT_LABEL)
 Values
   ('Q', 'Queued');
Insert into DQA_SUBMIT_STATUS
   (SUBMIT_CODE, SUBMIT_LABEL)
 Values
   ('H', 'Hold');
Insert into DQA_SUBMIT_STATUS
   (SUBMIT_CODE, SUBMIT_LABEL)
 Values
   ('P', 'Prepared');
Insert into DQA_SUBMIT_STATUS
   (SUBMIT_CODE, SUBMIT_LABEL)
 Values
   ('S', 'Submitted');
COMMIT;

SET DEFINE OFF;
Insert into DQA_SUBMITTER_PROFILE
   (PROFILE_ID, PROFILE_CODE, PROFILE_LABEL, PROFILE_STATUS, ORG_ID, 
    DATA_FORMAT, TRANSFER_PRIORITY, ACCESS_KEY)
 Values
   (1, 'MASTER:HL7', 'HL7', 'Template', 1, 
    'HL7v2', 'Normal', 'hl7');
Insert into DQA_SUBMITTER_PROFILE
   (PROFILE_ID, PROFILE_CODE, PROFILE_LABEL, PROFILE_STATUS, ORG_ID, 
    DATA_FORMAT, TRANSFER_PRIORITY, ACCESS_KEY)
 Values
   (300, 'TCHHL7', 'HL7 File', 'Prod', 300, 
    'HL7v2', 'Normal', '4TDxQYSDhKYgjn8M');
Insert into DQA_SUBMITTER_PROFILE
   (PROFILE_ID, PROFILE_CODE, PROFILE_LABEL, PROFILE_STATUS, ORG_ID, 
    DATA_FORMAT, TRANSFER_PRIORITY, ACCESS_KEY)
 Values
   (301, 'Williams Family', 'HL7 File', 'Test', 301, 
    'HL7v2', 'Normal', 'ak3JVxwPnWzFiwrK');
Insert into DQA_SUBMITTER_PROFILE
   (PROFILE_ID, PROFILE_CODE, PROFILE_LABEL, PROFILE_STATUS, ORG_ID, 
    DATA_FORMAT, TRANSFER_PRIORITY, ACCESS_KEY)
 Values
   (320, 'Care360', 'HL7 File', 'Test', 320, 
    'HL7v2', 'Normal', 'WFMnW3Dh3jXv9fgj');
COMMIT;

SET DEFINE OFF;
Insert into DQA_USER_ACCOUNT
   (USERNAME, PASSWORD, ACCOUNT_TYPE, ORG_ID, EMAIL)
 Values
   ('iisadmin', 'changeme', 'Admin', 1, 'Nathan.Bunker@gmail.com');
COMMIT;


SET DEFINE OFF;
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (2, '90476', 'adenovirus, type 4', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 54);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (3, '90477', 'adenovirus, type 7', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 55);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (4, '90581', 'anthrax', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 24);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (5, '90585', 'BCG', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 19);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (6, '90728', 'BCG', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('03/17/2011 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('03/17/2011 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 19);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (7, '90287', 'botulinum antitoxin', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 27);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (8, '90725', 'cholera', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 26);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (9, '90291', 'CMVIG', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 29);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (10, '90296', 'diphtheria antitoxin', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 12);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (11, '90702', 'DT (pediatric)', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 28);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (12, '90700', 'DTaP', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 20);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (13, '90700', 'DTaP, 5 pertussis antigens', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 106);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (14, '90723', 'DTaP-Hep B-IPV', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 110);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (15, '90721', 'DTaP-Hib', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 50);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (16, '90698', 'DTaP-Hib-IPV', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 120);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (17, '90696', 'DTaP-IPV', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 130);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (18, '90701', 'DTP', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (19, '90720', 'DTP-Hib', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 22);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (20, '90371', 'HBIG', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 30);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (21, '90632', 'Hep A, adult', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 52);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (22, '90633', 'Hep A, ped/adol, 2 dose', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 83);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (23, '90634', 'Hep A, ped/adol, 3 dose', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 84);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (24, '90730', 'Hep A, unspecified formulation', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('03/17/2011 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('03/17/2011 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 85);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (25, '90636', 'Hep A-Hep B', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 104);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (26, '90744', 'Hep B, adolescent or pediatric', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 8);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (27, '90745', 'Hep B, adolescent/high risk infant', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('03/17/2011 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('03/17/2011 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 42);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (28, '90743', 'Hep B, adult', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 43);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (29, '90746', 'Hep B, adult', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 43);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (30, '90740', 'Hep B, dialysis', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 44);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (31, '90747', 'Hep B, dialysis', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 44);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (32, '90731', 'Hep B, unspecified formulation', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('03/17/2011 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('03/17/2011 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 45);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (33, '90645', 'Hib (HbOC)', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 47);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (34, '90646', 'Hib (PRP-D)', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 46);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (35, '90647', 'Hib (PRP-OMP)', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 49);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (36, '90648', 'Hib (PRP-T)', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 48);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (37, '90737', 'Hib, unspecified formulation', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('03/17/2011 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('03/17/2011 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 17);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (38, '90748', 'Hib-Hep B', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 51);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (39, '90650', 'HPV, bivalent', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 118);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (40, '90649', 'HPV, quadrivalent', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 62);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (41, '90281', 'IG', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 86);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (42, '90741', 'IG, unspecified formulation', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('03/17/2011 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('03/17/2011 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 14);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (43, '90283', 'IGIV', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 87);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (44, '90662', 'Influenza, high dose seasonal', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 135);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (45, '90660', 'influenza, live, intranasal', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 111);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (46, '90657', 'Influenza, seasonal, injectable', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 141);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (47, '90658', 'Influenza, seasonal, injectable', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 141);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (48, '90656', 'Influenza, seasonal, injectable, preservative free', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 140);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (49, '90654', 'Influenza, seasonal, injectable, preservative free', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 144);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (50, '90655', 'Influenza, seasonal, injectable, preservative free', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 140);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (51, '90724', 'influenza, unspecified formulation', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('03/17/2011 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('03/17/2011 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 88);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (52, '90659', 'influenza, whole', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('03/17/2011 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('03/17/2011 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 16);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (53, '90713', 'IPV', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 10);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (54, '90738', 'Japanese Encephalitis IM', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 134);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (55, '90735', 'Japanese encephalitis SC', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 39);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (56, '90665', 'Lyme disease', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 66);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (57, '90708', 'M/R', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 4);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (58, '90705', 'measles', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 5);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (59, '90734', 'Meningococcal MCV4O', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 136);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (60, '90734', 'meningococcal MCV4P', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 114);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (61, '90733', 'meningococcal MPSV4', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 32);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (62, '90707', 'MMR', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 3);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (63, '90710', 'MMRV', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 94);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (64, '90704', 'mumps', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 7);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (65, '90668', 'Novel influenza-H1N1-09', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('08/31/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('08/31/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 127);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (66, '90470', 'Novel Influenza-H1N1-09, all formulations', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('08/31/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('08/31/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 128);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (67, '90663', 'Novel Influenza-H1N1-09, all formulations', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('08/31/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('08/31/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 128);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (68, '90664', 'Novel Influenza-H1N1-09, nasal', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('08/31/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('08/31/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 125);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (69, '90666', 'Novel influenza-H1N1-09, preservative-free', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('08/31/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('08/31/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 126);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (70, '90712', 'OPV', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 2);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (71, '90727', 'plague', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 23);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (72, '90670', 'Pneumococcal conjugate PCV 13', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 133);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (73, '90669', 'pneumococcal conjugate PCV 7', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 100);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (74, '90732', 'pneumococcal polysaccharide PPV23', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 33);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (75, '90676', 'rabies, intradermal injection', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 40);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (76, '90675', 'rabies, intramuscular injection', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 18);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (77, '90726', 'rabies, unspecified formulation', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('03/17/2011 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('03/17/2011 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 90);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (78, '90375', 'RIG', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 34);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (79, '90376', 'RIG', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 34);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (80, '90681', 'rotavirus, monovalent', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 119);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (81, '90680', 'rotavirus, pentavalent', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 116);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (82, '90379', 'RSV-IGIV', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 71);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (83, '90378', 'RSV-MAb', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 93);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (84, '90706', 'rubella', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 6);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (85, '90714', 'Td (adult) preservative free', TO_DATE('01/01/2005 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2005 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 113);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (86, '90718', 'Td (adult), adsorbed', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 9);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (87, '90715', 'Tdap', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 115);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (88, '90703', 'tetanus toxoid, adsorbed', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 35);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (89, '90389', 'TIG', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 13);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (90, '90690', 'typhoid, oral', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 25);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (91, '90692', 'typhoid, parenteral', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 41);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (92, '90693', 'typhoid, parenteral, AKD (U.S. military)', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 53);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (93, '90714', 'typhoid, unspecified formulation', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('03/17/2011 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('03/17/2011 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 91);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (94, '90691', 'typhoid, ViCPs', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 101);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (95, '90393', 'vaccinia immune globulin', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 79);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (96, '90716', 'varicella', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 21);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (97, '90396', 'VZIG', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 36);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (98, '90717', 'yellow fever', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 37);
Insert into DQA_VACCINE_CPT
   (CPT_ID, CPT_CODE, CPT_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, CVX_ID)
 Values
   (99, '90736', 'zoster', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 121);
COMMIT;

SET DEFINE OFF;
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (998, '998', 'no vaccine administered', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'non vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (99, '99', 'RESERVED - do not use', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'non vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (999, '999', 'unknown', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'non vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (143, '143', 'Adenovirus types 4 and 7', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (54, '54', 'adenovirus, type 4', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (55, '55', 'adenovirus, type 7', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (82, '82', 'adenovirus, unspecified formulation', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('09/30/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('09/30/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'unspecified');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (24, '24', 'anthrax', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (19, '19', 'BCG', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (27, '27', 'botulinum antitoxin', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (26, '26', 'cholera', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'unspecified');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (29, '29', 'CMVIG', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (56, '56', 'dengue fever', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (12, '12', 'diphtheria antitoxin', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (28, '28', 'DT (pediatric)', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (20, '20', 'DTaP', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (106, '106', 'DTaP, 5 pertussis antigens', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (107, '107', 'DTaP, unspecified formulation', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('09/30/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('09/30/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'unspecified');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (110, '110', 'DTaP-Hep B-IPV', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (50, '50', 'DTaP-Hib', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (120, '120', 'DTaP-Hib-IPV', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (130, '130', 'DTaP-IPV', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (132, '132', 'DTaP-IPV-HIB-HEP B, historical', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (1, '01', 'DTP', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (22, '22', 'DTP-Hib', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (102, '102', 'DTP-Hib-Hep B', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (57, '57', 'hantavirus', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (30, '30', 'HBIG', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (52, '52', 'Hep A, adult', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (83, '83', 'Hep A, ped/adol, 2 dose', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (84, '84', 'Hep A, ped/adol, 3 dose', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (31, '31', 'Hep A, pediatric, unspecified formulation', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('09/30/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('09/30/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (85, '85', 'Hep A, unspecified formulation', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('09/30/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('09/30/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'unspecified');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (104, '104', 'Hep A-Hep B', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (8, '08', 'Hep B, adolescent or pediatric', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 0, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (42, '42', 'Hep B, adolescent/high risk infant', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 0, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (43, '43', 'Hep B, adult', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (44, '44', 'Hep B, dialysis', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (45, '45', 'Hep B, unspecified formulation', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('09/30/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('09/30/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 0, 1440, 'unspecified');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (58, '58', 'Hep C', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (59, '59', 'Hep E', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (60, '60', 'herpes simplex 2', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (47, '47', 'Hib (HbOC)', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (46, '46', 'Hib (PRP-D)', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (49, '49', 'Hib (PRP-OMP)', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (48, '48', 'Hib (PRP-T)', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (17, '17', 'Hib, unspecified formulation', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('09/30/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('09/30/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'unspecified');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (51, '51', 'Hib-Hep B', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (61, '61', 'HIV', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (118, '118', 'HPV, bivalent', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (62, '62', 'HPV, quadrivalent', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (137, '137', 'HPV, unspecified formulation', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('09/30/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('09/30/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (86, '86', 'IG', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (14, '14', 'IG, unspecified formulation', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('09/30/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('09/30/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'unspecified');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (87, '87', 'IGIV', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (123, '123', 'influenza, H5N1-1203', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (135, '135', 'Influenza, high dose seasonal', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (111, '111', 'influenza, live, intranasal', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (141, '141', 'Influenza, seasonal, injectable', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (140, '140', 'Influenza, seasonal, injectable, preservative free', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (144, '144', 'influenza, seasonal, intradermal, preservative free', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (15, '15', 'influenza, split (incl. purified surface antigen)', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('09/30/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('09/30/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (88, '88', 'influenza, unspecified formulation', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('09/30/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('09/30/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'unspecified');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (16, '16', 'influenza, whole', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (10, '10', 'IPV', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (134, '134', 'Japanese Encephalitis IM', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (39, '39', 'Japanese encephalitis SC', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (129, '129', 'Japanese Encephalitis, unspecified formulation', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('09/30/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('09/30/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (63, '63', 'Junin virus', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (64, '64', 'leishmaniasis', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (65, '65', 'leprosy', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (66, '66', 'Lyme disease', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (4, '04', 'M/R', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (67, '67', 'malaria', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (5, '05', 'measles', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('08/31/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('08/31/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (68, '68', 'melanoma', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (103, '103', 'meningococcal C conjugate', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (136, '136', 'Meningococcal MCV4O', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (114, '114', 'meningococcal MCV4P', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (32, '32', 'meningococcal MPSV4', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (108, '108', 'meningococcal, unspecified formulation', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('09/30/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('09/30/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'unspecified');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (3, '03', 'MMR', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (94, '94', 'MMRV', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (7, '07', 'mumps', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (127, '127', 'Novel influenza-H1N1-09', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('08/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('08/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (128, '128', 'Novel Influenza-H1N1-09, all formulations', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('08/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('08/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'unspecified');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (125, '125', 'Novel Influenza-H1N1-09, nasal', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('08/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('08/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (126, '126', 'Novel influenza-H1N1-09, preservative-free', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('08/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('08/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (2, '02', 'OPV', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (69, '69', 'parainfluenza-3', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (11, '11', 'pertussis', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (23, '23', 'plague', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (133, '133', 'Pneumococcal conjugate PCV 13', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (100, '100', 'pneumococcal conjugate PCV 7', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (33, '33', 'pneumococcal polysaccharide PPV23', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (109, '109', 'pneumococcal, unspecified formulation', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('09/30/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('09/30/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'unspecified');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (89, '89', 'polio, unspecified formulation', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('09/30/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('09/30/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'unspecified');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (70, '70', 'Q fever', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (40, '40', 'rabies, intradermal injection', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (18, '18', 'rabies, intramuscular injection', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (90, '90', 'rabies, unspecified formulation', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('09/30/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('09/30/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'unspecified');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (72, '72', 'rheumatic fever', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (73, '73', 'Rift Valley fever', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (34, '34', 'RIG', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (119, '119', 'rotavirus, monovalent', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (116, '116', 'rotavirus, pentavalent', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (74, '74', 'rotavirus, tetravalent', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (122, '122', 'rotavirus, unspecified formulation', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('09/30/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('09/30/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'unspecified');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (71, '71', 'RSV-IGIV', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (93, '93', 'RSV-MAb', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (6, '06', 'rubella', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (38, '38', 'rubella/mumps', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (76, '76', 'Staphylococcus bacterio lysate', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (138, '138', 'Td (adult)', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (113, '113', 'Td (adult) preservative free', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (9, '09', 'Td (adult), adsorbed', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (139, '139', 'Td(adult) unspecified formulation', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('09/30/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('09/30/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'unspecified');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (115, '115', 'Tdap', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (35, '35', 'tetanus toxoid, adsorbed', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (142, '142', 'tetanus toxoid, not adsorbed', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (112, '112', 'tetanus toxoid, unspecified formulation', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('09/30/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('09/30/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (77, '77', 'tick-borne encephalitis', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (13, '13', 'TIG', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (98, '98', 'TST, unspecified formulation', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('09/30/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('09/30/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (95, '95', 'TST-OT tine test', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (96, '96', 'TST-PPD intradermal', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (97, '97', 'TST-PPD tine test', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (78, '78', 'tularemia vaccine', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (25, '25', 'typhoid, oral', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (41, '41', 'typhoid, parenteral', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (53, '53', 'typhoid, parenteral, AKD (U.S. military)', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (91, '91', 'typhoid, unspecified formulation', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('09/30/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('09/30/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'unspecified');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (101, '101', 'typhoid, ViCPs', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (131, '131', 'typhus, historical', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (75, '75', 'vaccinia (smallpox)', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (105, '105', 'vaccinia (smallpox) diluted', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (79, '79', 'vaccinia immune globulin', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (21, '21', 'varicella', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (81, '81', 'VEE, inactivated', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (80, '80', 'VEE, live', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (92, '92', 'VEE, unspecified formulation', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('09/30/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('09/30/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'unspecified');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (36, '36', 'VZIG', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (117, '117', 'VZIG (IND)', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (37, '37', 'yellow fever', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
Insert into DQA_VACCINE_CVX
   (CVX_ID, CVX_CODE, CVX_LABEL, VALID_START_DATE, USE_START_DATE, 
    USE_END_DATE, VALID_END_DATE, USE_MONTH_START, USE_MONTH_END, CONCEPT_TYPE)
 Values
   (121, '121', 'zoster', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 1440, 'vaccine');
COMMIT;

SET DEFINE OFF;
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (541, 2, 54);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (542, 2, 55);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (543, 2, 82);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (544, 3, 24);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (545, 4, 19);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (546, 5, 26);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (547, 17, 29);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (548, 6, 28);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (549, 6, 20);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (550, 6, 106);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (551, 6, 107);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (552, 6, 110);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (553, 13, 110);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (554, 26, 110);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (555, 6, 50);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (556, 15, 50);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (557, 6, 120);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (558, 15, 120);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (559, 26, 120);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (560, 6, 130);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (561, 26, 130);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (562, 6, 132);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (563, 13, 132);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (564, 15, 132);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (565, 26, 132);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (566, 6, 1);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (567, 6, 22);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (568, 15, 22);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (569, 6, 102);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (570, 13, 102);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (571, 15, 102);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (572, 10, 30);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (573, 12, 52);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (574, 12, 83);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (575, 12, 84);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (576, 12, 31);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (577, 12, 85);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (578, 12, 104);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (579, 13, 104);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (580, 13, 8);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (581, 13, 42);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (582, 13, 43);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (583, 13, 44);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (584, 13, 45);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (585, 14, 58);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (586, 11, 59);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (587, 15, 47);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (588, 15, 46);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (589, 15, 49);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (590, 15, 48);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (591, 15, 17);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (592, 13, 51);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (593, 15, 51);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (594, 16, 118);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (595, 16, 62);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (596, 16, 137);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (597, 17, 86);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (598, 17, 14);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (599, 17, 87);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (600, 9, 123);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (601, 7, 135);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (602, 7, 111);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (603, 7, 141);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (604, 7, 140);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (605, 7, 15);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (606, 7, 88);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (607, 7, 16);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (608, 26, 10);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (609, 18, 134);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (610, 18, 39);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (611, 19, 66);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (612, 20, 4);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (613, 29, 4);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (614, 20, 5);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (615, 21, 103);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (616, 21, 136);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (617, 21, 114);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (618, 21, 32);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (619, 21, 108);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (620, 22, 3);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (621, 22, 94);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (622, 33, 94);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (623, 23, 7);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (624, 8, 127);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (625, 8, 128);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (626, 8, 125);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (627, 8, 126);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (628, 26, 2);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (629, 24, 133);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (630, 24, 100);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (631, 25, 33);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (632, 26, 89);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (633, 27, 40);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (634, 27, 18);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (635, 27, 90);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (636, 17, 34);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (637, 28, 119);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (638, 28, 116);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (639, 28, 74);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (640, 28, 122);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (641, 17, 71);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (642, 29, 6);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (643, 23, 38);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (644, 29, 38);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (645, 30, 138);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (646, 30, 113);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (647, 30, 9);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (648, 30, 115);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (649, 31, 25);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (650, 31, 41);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (651, 31, 53);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (652, 31, 91);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (653, 31, 101);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (654, 32, 75);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (655, 32, 105);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (656, 33, 21);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (657, 34, 81);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (658, 34, 80);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (659, 34, 92);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (660, 35, 37);
Insert into DQA_VACCINE_CVX_GROUP
   (CVX_GROUP_ID, GROUP_ID, CVX_ID)
 Values
   (661, 36, 121);
COMMIT;

SET DEFINE OFF;
Insert into DQA_VACCINE_GROUP
   (GROUP_ID, GROUP_CODE, GROUP_LABEL, GROUP_STATUS)
 Values
   (2, 'ADENO', 'Adnovirus', 'Not Expected');
Insert into DQA_VACCINE_GROUP
   (GROUP_ID, GROUP_CODE, GROUP_LABEL, GROUP_STATUS)
 Values
   (3, 'ANTHRAX', 'Anthrax', 'Not Expected');
Insert into DQA_VACCINE_GROUP
   (GROUP_ID, GROUP_CODE, GROUP_LABEL, GROUP_STATUS)
 Values
   (4, 'BCG', 'BCG', 'Not Expected');
Insert into DQA_VACCINE_GROUP
   (GROUP_ID, GROUP_CODE, GROUP_LABEL, GROUP_STATUS)
 Values
   (5, 'cholera', 'Cholera', 'Not Expected');
Insert into DQA_VACCINE_GROUP
   (GROUP_ID, GROUP_CODE, GROUP_LABEL, GROUP_STATUS)
 Values
   (6, 'DTAP', 'DTaP', 'Expected');
Insert into DQA_VACCINE_GROUP
   (GROUP_ID, GROUP_CODE, GROUP_LABEL, GROUP_STATUS)
 Values
   (7, 'FLU', 'Influenza', 'Expected');
Insert into DQA_VACCINE_GROUP
   (GROUP_ID, GROUP_CODE, GROUP_LABEL, GROUP_STATUS)
 Values
   (8, 'H1N1 flu', 'H1N1-09', 'Not Expected');
Insert into DQA_VACCINE_GROUP
   (GROUP_ID, GROUP_CODE, GROUP_LABEL, GROUP_STATUS)
 Values
   (9, 'H5N1 flu', 'Influenza, H5N1-1203', 'Not Expected');
Insert into DQA_VACCINE_GROUP
   (GROUP_ID, GROUP_CODE, GROUP_LABEL, GROUP_STATUS)
 Values
   (10, 'HBIG', 'HBIG', 'Not Expected');
Insert into DQA_VACCINE_GROUP
   (GROUP_ID, GROUP_CODE, GROUP_LABEL, GROUP_STATUS)
 Values
   (11, 'Hep E', 'Hep E', 'Not Expected');
Insert into DQA_VACCINE_GROUP
   (GROUP_ID, GROUP_CODE, GROUP_LABEL, GROUP_STATUS)
 Values
   (12, 'HepA', 'Hep A', 'Expected');
Insert into DQA_VACCINE_GROUP
   (GROUP_ID, GROUP_CODE, GROUP_LABEL, GROUP_STATUS)
 Values
   (13, 'HepB', 'Hep B', 'Expected');
Insert into DQA_VACCINE_GROUP
   (GROUP_ID, GROUP_CODE, GROUP_LABEL, GROUP_STATUS)
 Values
   (14, 'HepC', 'Hep C', 'Not Expected');
Insert into DQA_VACCINE_GROUP
   (GROUP_ID, GROUP_CODE, GROUP_LABEL, GROUP_STATUS)
 Values
   (15, 'HIB', 'Hib', 'Expected');
Insert into DQA_VACCINE_GROUP
   (GROUP_ID, GROUP_CODE, GROUP_LABEL, GROUP_STATUS)
 Values
   (16, 'HPV', 'HPV', 'Recommended');
Insert into DQA_VACCINE_GROUP
   (GROUP_ID, GROUP_CODE, GROUP_LABEL, GROUP_STATUS)
 Values
   (17, 'IG', 'IG', 'Not Expected');
Insert into DQA_VACCINE_GROUP
   (GROUP_ID, GROUP_CODE, GROUP_LABEL, GROUP_STATUS)
 Values
   (18, 'Japanese encephalitis', 'Japanese Encephalitis', 'Not Expected');
Insert into DQA_VACCINE_GROUP
   (GROUP_ID, GROUP_CODE, GROUP_LABEL, GROUP_STATUS)
 Values
   (19, 'Lyme disease', 'Lyme Disease', 'Not Expected');
Insert into DQA_VACCINE_GROUP
   (GROUP_ID, GROUP_CODE, GROUP_LABEL, GROUP_STATUS)
 Values
   (20, 'MEASLES', 'Measles', 'Optional');
Insert into DQA_VACCINE_GROUP
   (GROUP_ID, GROUP_CODE, GROUP_LABEL, GROUP_STATUS)
 Values
   (21, 'MENING', 'Meningococcal', 'Recommended');
Insert into DQA_VACCINE_GROUP
   (GROUP_ID, GROUP_CODE, GROUP_LABEL, GROUP_STATUS)
 Values
   (22, 'MMR', 'MMR', 'Expected');
Insert into DQA_VACCINE_GROUP
   (GROUP_ID, GROUP_CODE, GROUP_LABEL, GROUP_STATUS)
 Values
   (23, 'MUMPS', 'Mumps', 'Optional');
Insert into DQA_VACCINE_GROUP
   (GROUP_ID, GROUP_CODE, GROUP_LABEL, GROUP_STATUS)
 Values
   (24, 'PneumoPCV', 'Pneumococcal', 'Expected');
Insert into DQA_VACCINE_GROUP
   (GROUP_ID, GROUP_CODE, GROUP_LABEL, GROUP_STATUS)
 Values
   (25, 'PneumoPPV', 'Pneumococcal', 'Optional');
Insert into DQA_VACCINE_GROUP
   (GROUP_ID, GROUP_CODE, GROUP_LABEL, GROUP_STATUS)
 Values
   (26, 'POLIO', 'Polio', 'Expected');
Insert into DQA_VACCINE_GROUP
   (GROUP_ID, GROUP_CODE, GROUP_LABEL, GROUP_STATUS)
 Values
   (27, 'RABIES', 'Rabies', 'Optional');
Insert into DQA_VACCINE_GROUP
   (GROUP_ID, GROUP_CODE, GROUP_LABEL, GROUP_STATUS)
 Values
   (28, 'ROTAVIRUS', 'Rotavirus', 'Recommended');
Insert into DQA_VACCINE_GROUP
   (GROUP_ID, GROUP_CODE, GROUP_LABEL, GROUP_STATUS)
 Values
   (29, 'RUBELLA', 'Rubella', 'Optional');
Insert into DQA_VACCINE_GROUP
   (GROUP_ID, GROUP_CODE, GROUP_LABEL, GROUP_STATUS)
 Values
   (30, 'Td', 'Tdap', 'Expected');
Insert into DQA_VACCINE_GROUP
   (GROUP_ID, GROUP_CODE, GROUP_LABEL, GROUP_STATUS)
 Values
   (31, 'TYPHOID', 'Typhoid', 'Optional');
Insert into DQA_VACCINE_GROUP
   (GROUP_ID, GROUP_CODE, GROUP_LABEL, GROUP_STATUS)
 Values
   (32, 'VACCINIA', 'Vaccinia (smallpox)', 'Optional');
Insert into DQA_VACCINE_GROUP
   (GROUP_ID, GROUP_CODE, GROUP_LABEL, GROUP_STATUS)
 Values
   (33, 'VARICELLA', 'Varicella', 'Expected');
Insert into DQA_VACCINE_GROUP
   (GROUP_ID, GROUP_CODE, GROUP_LABEL, GROUP_STATUS)
 Values
   (34, 'VEE', 'VEE', 'Optional');
Insert into DQA_VACCINE_GROUP
   (GROUP_ID, GROUP_CODE, GROUP_LABEL, GROUP_STATUS)
 Values
   (35, 'YELLOWFEVER', 'Yellow Fever', 'Optional');
Insert into DQA_VACCINE_GROUP
   (GROUP_ID, GROUP_CODE, GROUP_LABEL, GROUP_STATUS)
 Values
   (36, 'ZOSTER', 'Zoster', 'Optional');
COMMIT;

SET DEFINE OFF;
Insert into DQA_VACCINE_MVX
   (MVX_CODE, MVX_LABEL, VALID_START_DATE, USE_START_DATE, USE_END_DATE, 
    VALID_END_DATE)
 Values
   ('AB', 'Abbott Laboratories', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_MVX
   (MVX_CODE, MVX_LABEL, VALID_START_DATE, USE_START_DATE, USE_END_DATE, 
    VALID_END_DATE)
 Values
   ('ACA', 'Acambis, Inc', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_MVX
   (MVX_CODE, MVX_LABEL, VALID_START_DATE, USE_START_DATE, USE_END_DATE, 
    VALID_END_DATE)
 Values
   ('AD', 'Adams Laboratories, Inc.', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_MVX
   (MVX_CODE, MVX_LABEL, VALID_START_DATE, USE_START_DATE, USE_END_DATE, 
    VALID_END_DATE)
 Values
   ('ALP', 'Alpha Therapeutic Corporation', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_MVX
   (MVX_CODE, MVX_LABEL, VALID_START_DATE, USE_START_DATE, USE_END_DATE, 
    VALID_END_DATE)
 Values
   ('AR', 'Armour', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_MVX
   (MVX_CODE, MVX_LABEL, VALID_START_DATE, USE_START_DATE, USE_END_DATE, 
    VALID_END_DATE)
 Values
   ('AVB', 'Aventis Behring L.L.C.', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_MVX
   (MVX_CODE, MVX_LABEL, VALID_START_DATE, USE_START_DATE, USE_END_DATE, 
    VALID_END_DATE)
 Values
   ('AVI', 'Aviron', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_MVX
   (MVX_CODE, MVX_LABEL, VALID_START_DATE, USE_START_DATE, USE_END_DATE, 
    VALID_END_DATE)
 Values
   ('BA', 'Baxter Healthcare Corporation-inactive', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_MVX
   (MVX_CODE, MVX_LABEL, VALID_START_DATE, USE_START_DATE, USE_END_DATE, 
    VALID_END_DATE)
 Values
   ('BAH', 'Baxter Healthcare Corporation', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_MVX
   (MVX_CODE, MVX_LABEL, VALID_START_DATE, USE_START_DATE, USE_END_DATE, 
    VALID_END_DATE)
 Values
   ('BAY', 'Bayer Corporation', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_MVX
   (MVX_CODE, MVX_LABEL, VALID_START_DATE, USE_START_DATE, USE_END_DATE, 
    VALID_END_DATE)
 Values
   ('BP', 'Berna Products', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_MVX
   (MVX_CODE, MVX_LABEL, VALID_START_DATE, USE_START_DATE, USE_END_DATE, 
    VALID_END_DATE)
 Values
   ('BPC', 'Berna Products Corporation', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_MVX
   (MVX_CODE, MVX_LABEL, VALID_START_DATE, USE_START_DATE, USE_END_DATE, 
    VALID_END_DATE)
 Values
   ('BTP', 'Biotest Pharmaceuticals Corporation', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_MVX
   (MVX_CODE, MVX_LABEL, VALID_START_DATE, USE_START_DATE, USE_END_DATE, 
    VALID_END_DATE)
 Values
   ('MIP', 'Emergent BioDefense Operations Lansing', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_MVX
   (MVX_CODE, MVX_LABEL, VALID_START_DATE, USE_START_DATE, USE_END_DATE, 
    VALID_END_DATE)
 Values
   ('CSL', 'CSL Behring, Inc', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_MVX
   (MVX_CODE, MVX_LABEL, VALID_START_DATE, USE_START_DATE, USE_END_DATE, 
    VALID_END_DATE)
 Values
   ('CNJ', 'Cangene Corporation', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_MVX
   (MVX_CODE, MVX_LABEL, VALID_START_DATE, USE_START_DATE, USE_END_DATE, 
    VALID_END_DATE)
 Values
   ('CMP', 'Celltech Medeva Pharmaceuticals', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_MVX
   (MVX_CODE, MVX_LABEL, VALID_START_DATE, USE_START_DATE, USE_END_DATE, 
    VALID_END_DATE)
 Values
   ('CEN', 'Centeon L.L.C.', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_MVX
   (MVX_CODE, MVX_LABEL, VALID_START_DATE, USE_START_DATE, USE_END_DATE, 
    VALID_END_DATE)
 Values
   ('CHI', 'Chiron Corporation', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_MVX
   (MVX_CODE, MVX_LABEL, VALID_START_DATE, USE_START_DATE, USE_END_DATE, 
    VALID_END_DATE)
 Values
   ('CON', 'Connaught', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_MVX
   (MVX_CODE, MVX_LABEL, VALID_START_DATE, USE_START_DATE, USE_END_DATE, 
    VALID_END_DATE)
 Values
   ('DVC', 'DynPort Vaccine Company, LLC', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_MVX
   (MVX_CODE, MVX_LABEL, VALID_START_DATE, USE_START_DATE, USE_END_DATE, 
    VALID_END_DATE)
 Values
   ('EVN', 'Evans Medical Limited', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_MVX
   (MVX_CODE, MVX_LABEL, VALID_START_DATE, USE_START_DATE, USE_END_DATE, 
    VALID_END_DATE)
 Values
   ('GEO', 'GeoVax Labs, Inc.', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_MVX
   (MVX_CODE, MVX_LABEL, VALID_START_DATE, USE_START_DATE, USE_END_DATE, 
    VALID_END_DATE)
 Values
   ('SKB', 'GlaxoSmithKline', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_MVX
   (MVX_CODE, MVX_LABEL, VALID_START_DATE, USE_START_DATE, USE_END_DATE, 
    VALID_END_DATE)
 Values
   ('GRE', 'Greer Laboratories, Inc.', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_MVX
   (MVX_CODE, MVX_LABEL, VALID_START_DATE, USE_START_DATE, USE_END_DATE, 
    VALID_END_DATE)
 Values
   ('IAG', 'Immuno International AG', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_MVX
   (MVX_CODE, MVX_LABEL, VALID_START_DATE, USE_START_DATE, USE_END_DATE, 
    VALID_END_DATE)
 Values
   ('IUS', 'Immuno-U.S., Inc.', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_MVX
   (MVX_CODE, MVX_LABEL, VALID_START_DATE, USE_START_DATE, USE_END_DATE, 
    VALID_END_DATE)
 Values
   ('INT', 'Intercell Biomedical', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_MVX
   (MVX_CODE, MVX_LABEL, VALID_START_DATE, USE_START_DATE, USE_END_DATE, 
    VALID_END_DATE)
 Values
   ('KGC', 'Korea Green Cross Corporation', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_MVX
   (MVX_CODE, MVX_LABEL, VALID_START_DATE, USE_START_DATE, USE_END_DATE, 
    VALID_END_DATE)
 Values
   ('LED', 'Lederle', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_MVX
   (MVX_CODE, MVX_LABEL, VALID_START_DATE, USE_START_DATE, USE_END_DATE, 
    VALID_END_DATE)
 Values
   ('MBL', 'Massachusetts Biologic Laboratories', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_MVX
   (MVX_CODE, MVX_LABEL, VALID_START_DATE, USE_START_DATE, USE_END_DATE, 
    VALID_END_DATE)
 Values
   ('MA', 'Massachusetts Public Health Biologic Laboratories', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_MVX
   (MVX_CODE, MVX_LABEL, VALID_START_DATE, USE_START_DATE, USE_END_DATE, 
    VALID_END_DATE)
 Values
   ('MED', 'MedImmune, Inc.', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_MVX
   (MVX_CODE, MVX_LABEL, VALID_START_DATE, USE_START_DATE, USE_END_DATE, 
    VALID_END_DATE)
 Values
   ('MSD', 'Merck and Co., Inc.', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_MVX
   (MVX_CODE, MVX_LABEL, VALID_START_DATE, USE_START_DATE, USE_END_DATE, 
    VALID_END_DATE)
 Values
   ('IM', 'Merieux', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_MVX
   (MVX_CODE, MVX_LABEL, VALID_START_DATE, USE_START_DATE, USE_END_DATE, 
    VALID_END_DATE)
 Values
   ('MIL', 'Miles', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_MVX
   (MVX_CODE, MVX_LABEL, VALID_START_DATE, USE_START_DATE, USE_END_DATE, 
    VALID_END_DATE)
 Values
   ('NAB', 'NABI', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_MVX
   (MVX_CODE, MVX_LABEL, VALID_START_DATE, USE_START_DATE, USE_END_DATE, 
    VALID_END_DATE)
 Values
   ('NYB', 'New York Blood Center', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_MVX
   (MVX_CODE, MVX_LABEL, VALID_START_DATE, USE_START_DATE, USE_END_DATE, 
    VALID_END_DATE)
 Values
   ('NAV', 'North American Vaccine, Inc.', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_MVX
   (MVX_CODE, MVX_LABEL, VALID_START_DATE, USE_START_DATE, USE_END_DATE, 
    VALID_END_DATE)
 Values
   ('NOV', 'Novartis Pharmaceutical Corporation', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_MVX
   (MVX_CODE, MVX_LABEL, VALID_START_DATE, USE_START_DATE, USE_END_DATE, 
    VALID_END_DATE)
 Values
   ('NVX', 'Novavax, Inc.', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_MVX
   (MVX_CODE, MVX_LABEL, VALID_START_DATE, USE_START_DATE, USE_END_DATE, 
    VALID_END_DATE)
 Values
   ('OTC', 'Organon Teknika Corporation', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_MVX
   (MVX_CODE, MVX_LABEL, VALID_START_DATE, USE_START_DATE, USE_END_DATE, 
    VALID_END_DATE)
 Values
   ('ORT', 'Ortho-clinical Diagnostics', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_MVX
   (MVX_CODE, MVX_LABEL, VALID_START_DATE, USE_START_DATE, USE_END_DATE, 
    VALID_END_DATE)
 Values
   ('PD', 'Parkedale Pharmaceuticals', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_MVX
   (MVX_CODE, MVX_LABEL, VALID_START_DATE, USE_START_DATE, USE_END_DATE, 
    VALID_END_DATE)
 Values
   ('PWJ', 'PowderJect Pharmaceuticals', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_MVX
   (MVX_CODE, MVX_LABEL, VALID_START_DATE, USE_START_DATE, USE_END_DATE, 
    VALID_END_DATE)
 Values
   ('PRX', 'Praxis Biologics', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_MVX
   (MVX_CODE, MVX_LABEL, VALID_START_DATE, USE_START_DATE, USE_END_DATE, 
    VALID_END_DATE)
 Values
   ('JPN', 'The Research Foundation for Microbial Diseases of Osaka University (BIKEN)', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_MVX
   (MVX_CODE, MVX_LABEL, VALID_START_DATE, USE_START_DATE, USE_END_DATE, 
    VALID_END_DATE)
 Values
   ('PMC', 'sanofi pasteur', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_MVX
   (MVX_CODE, MVX_LABEL, VALID_START_DATE, USE_START_DATE, USE_END_DATE, 
    VALID_END_DATE)
 Values
   ('SCL', 'Sclavo, Inc.', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_MVX
   (MVX_CODE, MVX_LABEL, VALID_START_DATE, USE_START_DATE, USE_END_DATE, 
    VALID_END_DATE)
 Values
   ('SOL', 'Solvay Pharmaceuticals', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_MVX
   (MVX_CODE, MVX_LABEL, VALID_START_DATE, USE_START_DATE, USE_END_DATE, 
    VALID_END_DATE)
 Values
   ('SI', 'Swiss Serum and Vaccine Inst.', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_MVX
   (MVX_CODE, MVX_LABEL, VALID_START_DATE, USE_START_DATE, USE_END_DATE, 
    VALID_END_DATE)
 Values
   ('TAL', 'Talecris Biotherapeutics', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_MVX
   (MVX_CODE, MVX_LABEL, VALID_START_DATE, USE_START_DATE, USE_END_DATE, 
    VALID_END_DATE)
 Values
   ('USA', 'United States Army Medical Research and Material Command', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_MVX
   (MVX_CODE, MVX_LABEL, VALID_START_DATE, USE_START_DATE, USE_END_DATE, 
    VALID_END_DATE)
 Values
   ('VXG', 'VaxGen', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_MVX
   (MVX_CODE, MVX_LABEL, VALID_START_DATE, USE_START_DATE, USE_END_DATE, 
    VALID_END_DATE)
 Values
   ('WA', 'Wyeth-Ayerst', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_MVX
   (MVX_CODE, MVX_LABEL, VALID_START_DATE, USE_START_DATE, USE_END_DATE, 
    VALID_END_DATE)
 Values
   ('WAL', 'Wyeth', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_MVX
   (MVX_CODE, MVX_LABEL, VALID_START_DATE, USE_START_DATE, USE_END_DATE, 
    VALID_END_DATE)
 Values
   ('ZLB', 'ZLB Behring', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_MVX
   (MVX_CODE, MVX_LABEL, VALID_START_DATE, USE_START_DATE, USE_END_DATE, 
    VALID_END_DATE)
 Values
   ('OTH', 'Other manufacturer', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_MVX
   (MVX_CODE, MVX_LABEL, VALID_START_DATE, USE_START_DATE, USE_END_DATE, 
    VALID_END_DATE)
 Values
   ('UNK', 'Unknown manufacturer', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_MVX
   (MVX_CODE, MVX_LABEL, VALID_START_DATE, USE_START_DATE, USE_END_DATE, 
    VALID_END_DATE)
 Values
   ('AKR', 'Akorn, Inc', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_MVX
   (MVX_CODE, MVX_LABEL, VALID_START_DATE, USE_START_DATE, USE_END_DATE, 
    VALID_END_DATE)
 Values
   ('PFR', 'Pfizer, Inc', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_MVX
   (MVX_CODE, MVX_LABEL, VALID_START_DATE, USE_START_DATE, USE_END_DATE, 
    VALID_END_DATE)
 Values
   ('BRR', 'Barr Laboratories', TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
COMMIT;

SET DEFINE OFF;
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (2, 'ORIMUNE', 'OPV', 2, 'WAL', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('09/01/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('09/01/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (3, 'M-M-R II', 'MMR', 3, 'MSD', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (4, 'ATTENUVAX', 'measles', 5, 'MSD', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (5, 'MERUVAX II', 'rubella', 6, 'MSD', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (6, 'MUMPSVAX', 'mumps', 7, 'MSD', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('09/01/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('09/01/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (7, 'ENGERIX B-PEDS', 'Hep B, adolescent or pediatric', 8, 'SKB', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (8, 'RECOMBIVAX-PEDS', 'Hep B, adolescent or pediatric', 8, 'MSD', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (9, 'TD(GENERIC)', 'Td (adult), adsorbed', 9, 'MBL', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (10, 'IPOL', 'IPV', 10, 'PMC', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (11, 'PREVNAR 7', 'pneumococcal conjugate PCV 7', 100, 'WAL', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (12, 'TYPHIM VI', 'typhoid, ViCPs', 101, 'PMC', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (13, 'TWINRIX', 'Hep A-Hep B', 104, 'SKB', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (14, 'DAPTACEL', 'DTaP, 5 pertussis antigens', 106, 'PMC', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (15, 'PEDIARIX', 'DTaP-Hep B-IPV', 110, 'SKB', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (16, 'FLUMIST', 'influenza, live, intranasal', 111, 'MED', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (17, 'DECAVAC', 'Td (adult) preservative free', 113, 'PMC', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (18, 'MENACTRA', 'meningococcal MCV4P', 114, 'PMC', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (19, 'ADACEL', 'Tdap', 115, 'PMC', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (20, 'BOOSTRIX', 'Tdap', 115, 'SKB', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (21, 'ROTATEQ', 'rotavirus, pentavalent', 116, 'MSD', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (22, 'CERVARIX', 'HPV, bivalent', 118, 'SKB', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (23, 'ROTARIX', 'rotavirus, monovalent', 119, 'SKB', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (24, 'PENTACEL', 'DTaP-Hib-IPV', 120, 'PMC', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (25, 'ZOSTAVAX', 'zoster', 121, 'MSD', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (26, 'KINRIX', 'DTaP-IPV', 130, 'SKB', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (27, 'PREVNAR 13', 'Pneumococcal conjugate PCV 13', 133, 'PFR', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (28, 'PREVNAR 13', 'Pneumococcal conjugate PCV 13', 133, 'WAL', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (29, 'IXIARO', 'Japanese Encephalitis IM', 134, 'INT', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (30, 'FLUZONE-HIGH DOSE', 'Influenza, high dose seasonal', 135, 'PMC', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (31, 'MENVEO', 'Meningococcal MCV4O', 136, 'NOV', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (32, 'Afluria, preservative free', 'Influenza, seasonal, injectable, preservative free', 140, 'CSL', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (33, 'AGRIFLU', 'Influenza, seasonal, injectable, preservative free', 140, 'NOV', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (34, 'FLUARIX', 'Influenza, seasonal, injectable, preservative free', 140, 'SKB', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (35, 'FLUVIRIN-PRESERVATIVE FREE', 'Influenza, seasonal, injectable, preservative free', 140, 'NOV', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (36, 'FLUZONE-PRESERVATIVE FREE', 'Influenza, seasonal, injectable, preservative free', 140, 'PMC', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (37, 'AFLURIA', 'Influenza, seasonal, injectable', 141, 'CSL', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (38, 'FLULAVAL', 'Influenza, seasonal, injectable', 141, 'SKB', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (39, 'FLUVIRIN', 'Influenza, seasonal, injectable', 141, 'NOV', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (40, 'FLUZONE', 'Influenza, seasonal, injectable', 141, 'PMC', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (41, 'IMOVAX', 'rabies, intramuscular injection', 18, 'PMC', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (42, 'RABAVERT', 'rabies, intramuscular injection', 18, 'NOV', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (43, 'MYCOBAX', 'BCG', 19, 'PMC', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (44, 'TICE BCG', 'BCG', 19, 'OTC', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (45, 'ACEL-IMUNE', 'DTaP', 20, 'WAL', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('09/01/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('09/01/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (46, 'CERTIVA', 'DTaP', 20, 'NAV', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('09/01/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('09/01/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (47, 'INFANRIX', 'DTaP', 20, 'SKB', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (48, 'TRIPEDIA', 'DTaP', 20, 'PMC', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (49, 'VARIVAX', 'varicella', 21, 'MSD', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (50, 'TETRAMUNE', 'DTP-Hib', 22, 'WAL', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('09/01/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('09/01/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (51, 'BIOTHRAX', 'anthrax', 24, 'MIP', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (52, 'VIVOTIF BERNA', 'typhoid, oral', 25, 'BPC', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (53, 'DT(GENERIC)', 'DT (pediatric)', 28, 'PMC', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (54, 'MENOMUNE', 'meningococcal MPSV4', 32, 'PMC', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (55, 'PNEUMOVAX 23', 'pneumococcal polysaccharide PPV23', 33, 'MSD', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (56, 'TETANUS TOXOID (GENERIC)', 'tetanus toxoid, adsorbed', 35, 'PMC', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (57, 'YF-VAX', 'yellow fever', 37, 'PMC', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (58, 'BIAVAX II', 'rubella/mumps', 38, 'MSD', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('09/01/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('09/01/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (59, 'JE-VAX', 'Japanese encephalitis SC', 39, 'JPN', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (60, 'IMOVAX ID', 'rabies, intradermal injection', 40, 'PMC', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('08/18/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('08/18/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (61, 'RabAvert', 'rabies, intradermal injection', 40, 'CHI', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (62, 'ENGERIX-B-ADULT', 'Hep B, adult', 43, 'SKB', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (63, 'RECOMBIVAX-ADULT', 'Hep B, adult', 43, 'MSD', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (64, 'RECOMBIVAX-DIALYSIS', 'Hep B, dialysis', 44, 'MSD', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (65, 'PROHIBIT', 'Hib (PRP-D)', 46, 'PMC', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('09/01/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('09/01/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (66, 'HIBTITER', 'Hib (HbOC)', 47, 'WAL', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('08/18/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('08/18/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (67, 'ACTHIB', 'Hib (PRP-T)', 48, 'PMC', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (68, 'HIBERIX', 'Hib (PRP-T)', 48, 'SKB', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (69, 'OMNIHIB', 'Hib (PRP-T)', 48, 'SKB', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('09/01/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('09/01/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (70, 'PEDVAXHIB', 'Hib (PRP-OMP)', 49, 'MSD', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (71, 'TRIHIBIT', 'DTaP-Hib', 50, 'PMC', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (72, 'COMVAX', 'Hib-Hep B', 51, 'MSD', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (73, 'HAVRIX-ADULT', 'Hep A, adult', 52, 'SKB', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (74, 'VAQTA-ADULT', 'Hep A, adult', 52, 'MSD', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (75, 'TYPHOID-AKD', 'typhoid, parenteral, AKD (U.S. military)', 53, 'USA', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (76, 'GARDASIL', 'HPV, quadrivalent', 62, 'MSD', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (77, 'ACAM2000', 'vaccinia (smallpox)', 75, 'ACA', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('05/28/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (78, 'ACAM2000', 'vaccinia (smallpox)', 75, 'PMC', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (79, 'DRYVAX', 'vaccinia (smallpox)', 75, 'WAL', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('09/01/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('09/01/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (80, 'HAVRIX-PEDS', 'Hep A, ped/adol, 2 dose', 83, 'SKB', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (81, 'VAQTA-PEDS', 'Hep A, ped/adol, 2 dose', 83, 'MSD', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into DQA_VACCINE_PRODUCT
   (PRODUCT_ID, PRODUCT_NAME, PRODUCT_LABEL, CVX_ID, MVX_CODE, 
    VALID_START_DATE, USE_START_DATE, USE_END_DATE, VALID_END_DATE)
 Values
   (82, 'PROQUAD', 'MMRV', 94, 'MSD', 
    TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/1970 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('01/01/2100 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
COMMIT;
