;              
CREATE USER IF NOT EXISTS ALT SALT 'ade5e5446b42f2db' HASH 'f5235ffae303ebce74abae185f00bd160cc7ddda2e9ff3c4164b2b042356d58c'; 
CREATE USER IF NOT EXISTS SA SALT '2567a67c39fcfcc6' HASH '894a8db82f0eed53461e978337107599a20b594e588a4cd3939e6866c3987448' ADMIN;
drop table IF EXISTS TRACE_VALUE;
drop table IF EXISTS AGENT_METRIC;
drop table IF EXISTS AGENT;
drop table IF EXISTS METRIC;
drop table IF EXISTS TRACE_TYPE;
drop table IF EXISTS HOST;
CREATE MEMORY TEMPORARY TABLE PUBLIC.AGENT(
    AGENT_ID INTEGER NOT NULL COMMENT 'The unqiue agent identifier',
    HOST_ID INTEGER NOT NULL COMMENT 'The id of the host this agent is running om.',
    NAME VARCHAR2(120) NOT NULL COMMENT 'The name of the agent.',
    FIRST_CONNECTED TIMESTAMP NOT NULL COMMENT 'The first time the agent was seen.',
    LAST_CONNECTED TIMESTAMP NOT NULL COMMENT 'The last time the agent connected.'
) NOT PERSISTENT; 
ALTER TABLE PUBLIC.AGENT ADD CONSTRAINT PUBLIC.AGENT_PK PRIMARY KEY(AGENT_ID); 
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.AGENT;    
CREATE MEMORY TEMPORARY TABLE PUBLIC.AGENT_METRIC(
    AGENT_METRIC_ID INTEGER NOT NULL COMMENT 'The agentMetric unique Id.',
    METRIC_ID INTEGER NOT NULL,
    AGENT_ID INTEGER NOT NULL,
    FIRST_SEEN TIMESTAMP NOT NULL COMMENT 'The first time this metric was seen for this agent.',
    LAST_SEEN DATE COMMENT 'The last time this metric was seen for this agent.'
) NOT PERSISTENT;              
ALTER TABLE PUBLIC.AGENT_METRIC ADD CONSTRAINT PUBLIC.AGENT_METRIC_PK PRIMARY KEY(AGENT_METRIC_ID);            
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.AGENT_METRIC;             
CREATE MEMORY TEMPORARY TABLE PUBLIC.HOST(
    HOST_ID INTEGER NOT NULL COMMENT 'The primary key for the host',
    NAME VARCHAR2(255) NOT NULL COMMENT 'The short or preferred host name',
    IP VARCHAR2(15) NOT NULL COMMENT 'The ip address of the host',
    FQN VARCHAR2(255) NOT NULL COMMENT 'The fully qualified name of the host',
    FIRST_CONNECTED TIMESTAMP NOT NULL COMMENT 'The first time the host was seen.',
    LAST_CONNECTED TIMESTAMP NOT NULL COMMENT 'The last time connected.'
) NOT PERSISTENT;      
ALTER TABLE PUBLIC.HOST ADD CONSTRAINT PUBLIC.HOST_PK PRIMARY KEY(HOST_ID);    
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.HOST;     
CREATE MEMORY TEMPORARY TABLE PUBLIC.TRACE_VALUE(
    AGENT_METRIC_ID INTEGER NOT NULL COMMENT 'The agent-metric-id for this value',
    VALUE_DATE TIMESTAMP NOT NULL COMMENT 'The effective date of this value.',
    VALUE NUMBER NOT NULL COMMENT 'The value of the trace'
) NOT PERSISTENT;             
ALTER TABLE PUBLIC.TRACE_VALUE ADD CONSTRAINT PUBLIC.TRACE_VALUE_PK PRIMARY KEY(AGENT_METRIC_ID, VALUE_DATE);  
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.TRACE_VALUE;              
CREATE MEMORY TEMPORARY TABLE PUBLIC.TRACE_TYPE(
    TYPE_ID SMALLINT NOT NULL COMMENT 'The unique id of the trace type.',
    TYPE_NAME VARCHAR2(30) COMMENT 'The name of the trace type'
) NOT PERSISTENT;            
ALTER TABLE PUBLIC.TRACE_TYPE ADD CONSTRAINT PUBLIC.TRACE_TYPE_PK PRIMARY KEY(TYPE_ID);        
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.TRACE_TYPE;               
CREATE MEMORY TEMPORARY TABLE PUBLIC.METRIC(
    METRIC_ID INTEGER NOT NULL COMMENT 'The unique id of the metric.',
    TYPE_ID SMALLINT NOT NULL,
    FULL_NAME VARCHAR2(200) COMMENT 'The full name of the metric',
    NAME VARCHAR2(60) COMMENT 'The point of the metric name',
    FIRST_SEEN TIMESTAMP NOT NULL COMMENT 'The first time this metric was seen',
    LAST_SEEN DATE COMMENT 'The last time this metric was seen'
) NOT PERSISTENT;       
ALTER TABLE PUBLIC.METRIC ADD CONSTRAINT PUBLIC.METRIC_PK PRIMARY KEY(METRIC_ID);              
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.METRIC;   
ALTER TABLE PUBLIC.AGENT ADD CONSTRAINT PUBLIC.AGENT_HOST_FK FOREIGN KEY(HOST_ID) REFERENCES PUBLIC.HOST(HOST_ID) NOCHECK;     
ALTER TABLE PUBLIC.METRIC ADD CONSTRAINT PUBLIC.METRIC_TRACE_TYPE_FK FOREIGN KEY(TYPE_ID) REFERENCES PUBLIC.TRACE_TYPE(TYPE_ID) NOCHECK;       
ALTER TABLE PUBLIC.AGENT_METRIC ADD CONSTRAINT PUBLIC.AGENT_METRIC_AGENT_FK FOREIGN KEY(AGENT_ID) REFERENCES PUBLIC.AGENT(AGENT_ID) NOCHECK;
ALTER TABLE PUBLIC.AGENT_METRIC ADD CONSTRAINT PUBLIC.AGENT_METRIC_METRIC_FK FOREIGN KEY(METRIC_ID) REFERENCES PUBLIC.METRIC(METRIC_ID) NOCHECK;