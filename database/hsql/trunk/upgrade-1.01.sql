ALTER TABLE dqa_potential_issue ADD COLUMN hl7_reference  VARCHAR(100);

UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='Blocked', target_object='General', target_field='authorization', issue_type='exception', field_value='', report_denominator='Message Count', table_id = NULL, hl7_reference = NULL WHERE issue_id='2';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='Blocked', target_object='General', target_field='configuration', issue_type='exception', field_value='', report_denominator='Message Count', table_id = NULL, hl7_reference = NULL WHERE issue_id='3';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='Blocked', target_object='General', target_field='parse', issue_type='exception', field_value='', report_denominator='Message Count', table_id = NULL, hl7_reference = NULL WHERE issue_id='4';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='Blocked', target_object='General', target_field='processing', issue_type='exception', field_value='', report_denominator='Message Count', table_id = NULL, hl7_reference = NULL WHERE issue_id='5';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='HL7', target_field='segment', issue_type='is unrecognized', field_value='', report_denominator='Message Count', table_id = NULL, hl7_reference = NULL WHERE issue_id='463';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='HL7', target_field='segment', issue_type='is invalid', field_value='', report_denominator='Message Count', table_id = NULL, hl7_reference = NULL WHERE issue_id='464';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='HL7', target_field='segments', issue_type='out of order', field_value='', report_denominator='Message Count', table_id = NULL, hl7_reference = NULL WHERE issue_id='452';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='HL7 MSH', target_field='accept ack type', issue_type='is deprecated', field_value='', report_denominator='Message Count', table_id = 40, hl7_reference = 'MSH-15' WHERE issue_id='415';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', target_object='HL7 MSH', target_field='accept ack type', issue_type='is ignored', field_value='', report_denominator='Message Count', table_id = 40, hl7_reference = 'MSH-15' WHERE issue_id='417';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='HL7 MSH', target_field='accept ack type', issue_type='is invalid', field_value='', report_denominator='Message Count', table_id = 40, hl7_reference = 'MSH-15' WHERE issue_id='416';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='HL7 MSH', target_field='accept ack type', issue_type='is missing', field_value='', report_denominator='Message Count', table_id = 40, hl7_reference = 'MSH-15' WHERE issue_id='6';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='HL7 MSH', target_field='accept ack type', issue_type='is unrecognized', field_value='', report_denominator='Message Count', table_id = 40, hl7_reference = 'MSH-15' WHERE issue_id='7';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='HL7 MSH', target_field='accept ack type', issue_type='is valued as', field_value='always', report_denominator='Message Count', table_id = 40, hl7_reference = 'MSH-15' WHERE issue_id='8';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='HL7 MSH', target_field='accept ack type', issue_type='is valued as', field_value='never', report_denominator='Message Count', table_id = 40, hl7_reference = 'MSH-15' WHERE issue_id='9';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='HL7 MSH', target_field='accept ack type', issue_type='is valued as', field_value='only on errors', report_denominator='Message Count', table_id = 40, hl7_reference = 'MSH-15' WHERE issue_id='10';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='HL7 MSH', target_field='alt character set', issue_type='is deprecated', field_value='', report_denominator='Message Count', table_id = 36, hl7_reference = 'MSH-20' WHERE issue_id='431';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', target_object='HL7 MSH', target_field='alt character set', issue_type='is ignored', field_value='', report_denominator='Message Count', table_id = 36, hl7_reference = 'MSH-20' WHERE issue_id='432';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='HL7 MSH', target_field='alt character set', issue_type='is invalid', field_value='', report_denominator='Message Count', table_id = 36, hl7_reference = 'MSH-20' WHERE issue_id='433';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='HL7 MSH', target_field='alt character set', issue_type='is missing', field_value='', report_denominator='Message Count', table_id = 36, hl7_reference = 'MSH-20' WHERE issue_id='434';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='HL7 MSH', target_field='alt character set', issue_type='is unrecognized', field_value='', report_denominator='Message Count', table_id = 36, hl7_reference = 'MSH-20' WHERE issue_id='435';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='HL7 MSH', target_field='app ack type', issue_type='is deprecated', field_value='', report_denominator='Message Count', table_id = NULL, hl7_reference = 'MSH-16' WHERE issue_id='418';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', target_object='HL7 MSH', target_field='app ack type', issue_type='is ignored', field_value='', report_denominator='Message Count', table_id = NULL, hl7_reference = 'MSH-16' WHERE issue_id='420';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='HL7 MSH', target_field='app ack type', issue_type='is invalid', field_value='', report_denominator='Message Count', table_id = NULL, hl7_reference = 'MSH-16' WHERE issue_id='419';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='HL7 MSH', target_field='app ack type', issue_type='is missing', field_value='', report_denominator='Message Count', table_id = NULL, hl7_reference = 'MSH-16' WHERE issue_id='410';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='HL7 MSH', target_field='app ack type', issue_type='is unrecognized', field_value='', report_denominator='Message Count', table_id = NULL, hl7_reference = 'MSH-16' WHERE issue_id='411';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='HL7 MSH', target_field='app ack type', issue_type='is valued as', field_value='always', report_denominator='Message Count', table_id = NULL, hl7_reference = 'MSH-16' WHERE issue_id='412';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='HL7 MSH', target_field='app ack type', issue_type='is valued as', field_value='never', report_denominator='Message Count', table_id = NULL, hl7_reference = 'MSH-16' WHERE issue_id='413';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='HL7 MSH', target_field='app ack type', issue_type='is valued as', field_value='only on errors', report_denominator='Message Count', table_id = NULL, hl7_reference = 'MSH-16' WHERE issue_id='414';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='HL7 MSH', target_field='character set', issue_type='is deprecated', field_value='', report_denominator='Message Count', table_id = NULL, hl7_reference = 'MSH-18' WHERE issue_id='426';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', target_object='HL7 MSH', target_field='character set', issue_type='is ignored', field_value='', report_denominator='Message Count', table_id = NULL, hl7_reference = 'MSH-18' WHERE issue_id='427';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='HL7 MSH', target_field='character set', issue_type='is invalid', field_value='', report_denominator='Message Count', table_id = NULL, hl7_reference = 'MSH-18' WHERE issue_id='428';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='HL7 MSH', target_field='character set', issue_type='is missing', field_value='', report_denominator='Message Count', table_id = NULL, hl7_reference = 'MSH-18' WHERE issue_id='429';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='HL7 MSH', target_field='character set', issue_type='is unrecognized', field_value='', report_denominator='Message Count', table_id = NULL, hl7_reference = 'MSH-18' WHERE issue_id='430';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='HL7 MSH', target_field='country code', issue_type='is deprecated', field_value='', report_denominator='Message Count', table_id = 35, hl7_reference = 'MSH-17' WHERE issue_id='421';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', target_object='HL7 MSH', target_field='country code', issue_type='is ignored', field_value='', report_denominator='Message Count', table_id = 35, hl7_reference = 'MSH-17' WHERE issue_id='422';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='HL7 MSH', target_field='country code', issue_type='is invalid', field_value='', report_denominator='Message Count', table_id = 35, hl7_reference = 'MSH-17' WHERE issue_id='423';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='HL7 MSH', target_field='country code', issue_type='is missing', field_value='', report_denominator='Message Count', table_id = 35, hl7_reference = 'MSH-17' WHERE issue_id='424';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='HL7 MSH', target_field='country code', issue_type='is unrecognized', field_value='', report_denominator='Message Count', table_id = 35, hl7_reference = 'MSH-17' WHERE issue_id='425';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='Blocked', target_object='HL7 MSH', target_field='encoding character', issue_type='is invalid', field_value='', report_denominator='Message Count', table_id = NULL, hl7_reference = 'MSH-2' WHERE issue_id='11';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='Blocked', target_object='HL7 MSH', target_field='encoding character', issue_type='is missing', field_value='', report_denominator='Message Count', table_id = NULL, hl7_reference = 'MSH-2' WHERE issue_id='12';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', target_object='HL7 MSH', target_field='encoding character', issue_type='is non-standard', field_value='', report_denominator='Message Count', table_id = NULL, hl7_reference = 'MSH-2' WHERE issue_id='13';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='HL7 MSH', target_field='message control id', issue_type='is missing', field_value='', report_denominator='Message Count', table_id = NULL, hl7_reference = 'MSH-10' WHERE issue_id='14';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', target_object='HL7 MSH', target_field='message date', issue_type='is in future', field_value='', report_denominator='Message Count', table_id = NULL, hl7_reference = 'MSH-7' WHERE issue_id='15';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', target_object='HL7 MSH', target_field='message date', issue_type='is invalid', field_value='', report_denominator='Message Count', table_id = NULL, hl7_reference = 'MSH-7' WHERE issue_id='16';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', target_object='HL7 MSH', target_field='message date', issue_type='is missing', field_value='', report_denominator='Message Count', table_id = NULL, hl7_reference = 'MSH-7' WHERE issue_id='17';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='HL7 MSH', target_field='message profile id', issue_type='is deprecated', field_value='', report_denominator='Message Count', table_id = 37, hl7_reference = 'MSH-21' WHERE issue_id='436';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', target_object='HL7 MSH', target_field='message profile id', issue_type='is ignored', field_value='', report_denominator='Message Count', table_id = 37, hl7_reference = 'MSH-21' WHERE issue_id='437';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='HL7 MSH', target_field='message profile id', issue_type='is invalid', field_value='', report_denominator='Message Count', table_id = 37, hl7_reference = 'MSH-21' WHERE issue_id='438';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='HL7 MSH', target_field='message profile id', issue_type='is missing', field_value='', report_denominator='Message Count', table_id = 37, hl7_reference = 'MSH-21' WHERE issue_id='439';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='HL7 MSH', target_field='message profile id', issue_type='is unrecognized', field_value='', report_denominator='Message Count', table_id = 37, hl7_reference = 'MSH-21' WHERE issue_id='440';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='HL7 MSH', target_field='message structure', issue_type='is missing', field_value='', report_denominator='Message Count', table_id = NULL, hl7_reference = 'MSH-9.3' WHERE issue_id='391';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='HL7 MSH', target_field='message structure', issue_type='is unrecognized', field_value='', report_denominator='Message Count', table_id = NULL, hl7_reference = 'MSH-9.3' WHERE issue_id='392';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='HL7 MSH', target_field='message trigger', issue_type='is missing', field_value='', report_denominator='Message Count', table_id = NULL, hl7_reference = 'MSH-9.2' WHERE issue_id='18';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', target_object='HL7 MSH', target_field='message trigger', issue_type='is unrecognized', field_value='', report_denominator='Message Count', table_id = NULL, hl7_reference = 'MSH-9.2' WHERE issue_id='19';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='HL7 MSH', target_field='message type', issue_type='is missing', field_value='', report_denominator='Message Count', table_id = NULL, hl7_reference = 'MSH-9.1' WHERE issue_id='20';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='Blocked', target_object='HL7 MSH', target_field='message type', issue_type='is unrecognized', field_value='', report_denominator='Message Count', table_id = NULL, hl7_reference = 'MSH-9.1' WHERE issue_id='21';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='Blocked', target_object='HL7 MSH', target_field='message type', issue_type='is unsupported', field_value='', report_denominator='Message Count', table_id = NULL, hl7_reference = 'MSH-9.1' WHERE issue_id='22';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='HL7 MSH', target_field='processing id', issue_type='is deprecated', field_value='', report_denominator='Message Count', table_id = 39, hl7_reference = 'MSH-11' WHERE issue_id='403';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', target_object='HL7 MSH', target_field='processing id', issue_type='is ignored', field_value='', report_denominator='Message Count', table_id = 39, hl7_reference = 'MSH-11' WHERE issue_id='404';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='HL7 MSH', target_field='processing id', issue_type='is invalid', field_value='', report_denominator='Message Count', table_id = 39, hl7_reference = 'MSH-11' WHERE issue_id='402';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='HL7 MSH', target_field='processing id', issue_type='is missing', field_value='', report_denominator='Message Count', table_id = 39, hl7_reference = 'MSH-11' WHERE issue_id='23';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='HL7 MSH', target_field='processing id', issue_type='is unrecognized', field_value='', report_denominator='Message Count', table_id = 39, hl7_reference = 'MSH-11' WHERE issue_id='401';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='HL7 MSH', target_field='processing id', issue_type='is valued as', field_value='debug', report_denominator='Message Count', table_id = 39, hl7_reference = 'MSH-11' WHERE issue_id='24';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='HL7 MSH', target_field='processing id', issue_type='is valued as', field_value='production', report_denominator='Message Count', table_id = 39, hl7_reference = 'MSH-11' WHERE issue_id='25';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='HL7 MSH', target_field='processing id', issue_type='is valued as', field_value='training', report_denominator='Message Count', table_id = 39, hl7_reference = 'MSH-11' WHERE issue_id='26';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='HL7 MSH', target_field='receiving application', issue_type='is invalid', field_value='', report_denominator='Message Count', table_id = NULL, hl7_reference = 'MSH-5' WHERE issue_id='29';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='HL7 MSH', target_field='receiving application', issue_type='is missing', field_value='', report_denominator='Message Count', table_id = NULL, hl7_reference = 'MSH-5' WHERE issue_id='30';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='HL7 MSH', target_field='receiving facility', issue_type='is invalid', field_value='', report_denominator='Message Count', table_id = NULL, hl7_reference = 'MSH-6' WHERE issue_id='31';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='HL7 MSH', target_field='receiving facility', issue_type='is missing', field_value='', report_denominator='Message Count', table_id = NULL, hl7_reference = 'MSH-6' WHERE issue_id='32';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='Blocked', target_object='HL7 MSH', target_field='segment', issue_type='is missing', field_value='', report_denominator='Message Count', table_id = NULL, hl7_reference = 'MSH' WHERE issue_id='33';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='HL7 MSH', target_field='sending application', issue_type='is invalid', field_value='', report_denominator='Message Count', table_id = NULL, hl7_reference = 'MSH-3' WHERE issue_id='34';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='HL7 MSH', target_field='sending application', issue_type='is missing', field_value='', report_denominator='Message Count', table_id = NULL, hl7_reference = 'MSH-3' WHERE issue_id='35';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='HL7 MSH', target_field='sending facility', issue_type='is invalid', field_value='', report_denominator='Message Count', table_id = NULL, hl7_reference = 'MSH-4' WHERE issue_id='36';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='HL7 MSH', target_field='sending facility', issue_type='is missing', field_value='', report_denominator='Message Count', table_id = NULL, hl7_reference = 'MSH-4' WHERE issue_id='37';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='HL7 MSH', target_field='version', issue_type='is missing', field_value='', report_denominator='Message Count', table_id = NULL, hl7_reference = 'MSH-12' WHERE issue_id='38';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='HL7 MSH', target_field='version', issue_type='is unrecognized', field_value='', report_denominator='Message Count', table_id = NULL, hl7_reference = 'MSH-12' WHERE issue_id='39';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='HL7 MSH', target_field='version', issue_type='is valued as', field_value='2.3.1', report_denominator='Message Count', table_id = NULL, hl7_reference = 'MSH-12' WHERE issue_id='40';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='HL7 MSH', target_field='version', issue_type='is valued as', field_value='2.4', report_denominator='Message Count', table_id = NULL, hl7_reference = 'MSH-12' WHERE issue_id='41';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='HL7 MSH', target_field='version', issue_type='is valued as', field_value='2.5', report_denominator='Message Count', table_id = NULL, hl7_reference = 'MSH-12' WHERE issue_id='42';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='Should', target_object='HL7 NK1', target_field='segment', issue_type='is missing', field_value='', report_denominator='NextOfKin Count', table_id = NULL, hl7_reference = 'NK1' WHERE issue_id='43';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='HL7 NK1', target_field='segment', issue_type='is repeated', field_value='', report_denominator='NextOfKin Count', table_id = NULL, hl7_reference = 'NK1' WHERE issue_id='44';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='HL7 NK1', target_field='set id', issue_type='is missing', field_value='', report_denominator='NextOfKin Count', table_id = NULL, hl7_reference = 'NK1-1' WHERE issue_id='368';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='HL7 OBX', target_field='segment', issue_type='is missing', field_value='', report_denominator='Observation Count', table_id = NULL, hl7_reference = 'OBX' WHERE issue_id='45';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='HL7 ORC', target_field='segment', issue_type='is missing', field_value='', report_denominator='Vaccination Count', table_id = NULL, hl7_reference = 'ORC' WHERE issue_id='46';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', target_object='HL7 ORC', target_field='segment', issue_type='is repeated', field_value='', report_denominator='Message Count', table_id = NULL, hl7_reference = 'ORC' WHERE issue_id='47';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='HL7 PD1', target_field='segment', issue_type='is missing', field_value='', report_denominator='Message Count', table_id = NULL, hl7_reference = 'PD1' WHERE issue_id='48';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='Blocked', target_object='HL7 PID', target_field='segment', issue_type='is missing', field_value='', report_denominator='Message Count', table_id = NULL, hl7_reference = 'PID' WHERE issue_id='49';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', target_object='HL7 PID', target_field='segment', issue_type='is repeated', field_value='', report_denominator='Message Count', table_id = NULL, hl7_reference = 'PID' WHERE issue_id='50';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='HL7 PV1', target_field='segment', issue_type='is missing', field_value='', report_denominator='Message Count', table_id = NULL, hl7_reference = 'PV1' WHERE issue_id='51';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', target_object='HL7 PV1', target_field='segment', issue_type='is repeated', field_value='', report_denominator='Message Count', table_id = NULL, hl7_reference = 'PV1' WHERE issue_id='400';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='HL7 RXA', target_field='admin sub id counter', issue_type='is missing', field_value='', report_denominator='Vaccination Count', table_id = NULL, hl7_reference = 'RXA' WHERE issue_id='390';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='HL7 RXA', target_field='give sub id', issue_type='is missing', field_value='', report_denominator='Vaccination Count', table_id = NULL, hl7_reference = 'RXA' WHERE issue_id='389';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='Can', target_object='HL7 RXA', target_field='segment', issue_type='is missing', field_value='', report_denominator='Message Count', table_id = NULL, hl7_reference = 'RXA' WHERE issue_id='52';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='Can', target_object='HL7 RXA', target_field='segment', issue_type='is repeated', field_value='', report_denominator='Message Count', table_id = NULL, hl7_reference = 'RXA' WHERE issue_id='53';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='HL7 RXR', target_field='segment', issue_type='is missing', field_value='', report_denominator='Vaccination Count', table_id = NULL, hl7_reference = 'RXR' WHERE issue_id='54';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='HL7 RXR', target_field='segment', issue_type='is repeated', field_value='', report_denominator='Vaccination Count', table_id = NULL, hl7_reference = 'RXR' WHERE issue_id='55';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Next-of-kin', target_field='address', issue_type='is different from patient address', field_value='', report_denominator='NextOfKin Count', table_id = NULL, hl7_reference = 'NK1-4' WHERE issue_id='56';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Next-of-kin', target_field='address', issue_type='is missing', field_value='', report_denominator='NextOfKin Count', table_id = NULL, hl7_reference = 'NK1-4' WHERE issue_id='57';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Next-of-kin', target_field='address city', issue_type='is invalid', field_value='', report_denominator='NextOfKin Count', table_id = NULL, hl7_reference = 'NK1-4.3' WHERE issue_id='58';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Next-of-kin', target_field='address city', issue_type='is missing', field_value='', report_denominator='NextOfKin Count', table_id = NULL, hl7_reference = 'NK1-4.3' WHERE issue_id='59';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Next-of-kin', target_field='address country', issue_type='is deprecated', field_value='', report_denominator='NextOfKin Count', table_id = 2, hl7_reference = 'NK1-4.6' WHERE issue_id='60';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', target_object='Next-of-kin', target_field='address country', issue_type='is ignored', field_value='', report_denominator='NextOfKin Count', table_id = 2, hl7_reference = 'NK1-4.6' WHERE issue_id='61';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Next-of-kin', target_field='address country', issue_type='is invalid', field_value='', report_denominator='NextOfKin Count', table_id = 2, hl7_reference = 'NK1-4.6' WHERE issue_id='62';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Next-of-kin', target_field='address country', issue_type='is missing', field_value='', report_denominator='NextOfKin Count', table_id = 2, hl7_reference = 'NK1-4.6' WHERE issue_id='63';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Next-of-kin', target_field='address country', issue_type='is unrecognized', field_value='', report_denominator='NextOfKin Count', table_id = 2, hl7_reference = 'NK1-4.6' WHERE issue_id='64';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Next-of-kin', target_field='address county', issue_type='is deprecated', field_value='', report_denominator='NextOfKin Count', table_id = 3, hl7_reference = 'NK1-4.9' WHERE issue_id='65';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', target_object='Next-of-kin', target_field='address county', issue_type='is ignored', field_value='', report_denominator='NextOfKin Count', table_id = 3, hl7_reference = 'NK1-4.9' WHERE issue_id='66';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Next-of-kin', target_field='address county', issue_type='is invalid', field_value='', report_denominator='NextOfKin Count', table_id = 3, hl7_reference = 'NK1-4.9' WHERE issue_id='67';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Next-of-kin', target_field='address county', issue_type='is missing', field_value='', report_denominator='NextOfKin Count', table_id = 3, hl7_reference = 'NK1-4.9' WHERE issue_id='68';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Next-of-kin', target_field='address county', issue_type='is unrecognized', field_value='', report_denominator='NextOfKin Count', table_id = 3, hl7_reference = 'NK1-4.9' WHERE issue_id='69';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Next-of-kin', target_field='address state', issue_type='is deprecated', field_value='', report_denominator='NextOfKin Count', table_id = 4, hl7_reference = 'NK1-4.4' WHERE issue_id='70';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', target_object='Next-of-kin', target_field='address state', issue_type='is ignored', field_value='', report_denominator='NextOfKin Count', table_id = 4, hl7_reference = 'NK1-4.4' WHERE issue_id='71';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Next-of-kin', target_field='address state', issue_type='is invalid', field_value='', report_denominator='NextOfKin Count', table_id = 4, hl7_reference = 'NK1-4.4' WHERE issue_id='72';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Next-of-kin', target_field='address state', issue_type='is missing', field_value='', report_denominator='NextOfKin Count', table_id = 4, hl7_reference = 'NK1-4.4' WHERE issue_id='73';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Next-of-kin', target_field='address state', issue_type='is unrecognized', field_value='', report_denominator='NextOfKin Count', table_id = 4, hl7_reference = 'NK1-4.4' WHERE issue_id='74';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Next-of-kin', target_field='address street', issue_type='is missing', field_value='', report_denominator='NextOfKin Count', table_id = NULL, hl7_reference = 'NK1-4.1' WHERE issue_id='75';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Next-of-kin', target_field='address street2', issue_type='is missing', field_value='', report_denominator='NextOfKin Count', table_id = NULL, hl7_reference = 'NK1-4.2' WHERE issue_id='76';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Next-of-kin', target_field='address type', issue_type='is deprecated', field_value='', report_denominator='NextOfKin Count', table_id = 5, hl7_reference = 'NK1-4.7' WHERE issue_id='395';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', target_object='Next-of-kin', target_field='address type', issue_type='is ignored', field_value='', report_denominator='NextOfKin Count', table_id = 5, hl7_reference = 'NK1-4.7' WHERE issue_id='396';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Next-of-kin', target_field='address type', issue_type='is invalid', field_value='', report_denominator='NextOfKin Count', table_id = 5, hl7_reference = 'NK1-4.7' WHERE issue_id='397';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Next-of-kin', target_field='address type', issue_type='is missing', field_value='', report_denominator='NextOfKin Count', table_id = 5, hl7_reference = 'NK1-4.7' WHERE issue_id='398';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Next-of-kin', target_field='address type', issue_type='is unrecognized', field_value='', report_denominator='NextOfKin Count', table_id = 5, hl7_reference = 'NK1-4.7' WHERE issue_id='399';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Next-of-kin', target_field='address zip', issue_type='is invalid', field_value='', report_denominator='NextOfKin Count', table_id = NULL, hl7_reference = 'NK1-4.5' WHERE issue_id='77';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Next-of-kin', target_field='address zip', issue_type='is missing', field_value='', report_denominator='NextOfKin Count', table_id = NULL, hl7_reference = 'NK1-4.5' WHERE issue_id='78';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='Can', target_object='Next-of-kin', target_field='name', issue_type='is missing', field_value='', report_denominator='NextOfKin Count', table_id = NULL, hl7_reference = 'NK1-2' WHERE issue_id='79';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='Can', target_object='Next-of-kin', target_field='name first', issue_type='is missing', field_value='', report_denominator='NextOfKin Count', table_id = NULL, hl7_reference = 'NK1-2.1' WHERE issue_id='80';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='Can', target_object='Next-of-kin', target_field='name last', issue_type='is missing', field_value='', report_denominator='NextOfKin Count', table_id = NULL, hl7_reference = 'NK1-2.2' WHERE issue_id='81';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Next-of-kin', target_field='phone number', issue_type='is incomplete', field_value='', report_denominator='NextOfKin Count', table_id = NULL, hl7_reference = 'NK1-5' WHERE issue_id='82';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Next-of-kin', target_field='phone number', issue_type='is invalid', field_value='', report_denominator='NextOfKin Count', table_id = NULL, hl7_reference = 'NK1-5' WHERE issue_id='83';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='Can', target_object='Next-of-kin', target_field='phone number', issue_type='is missing', field_value='', report_denominator='NextOfKin Count', table_id = NULL, hl7_reference = 'NK1-5' WHERE issue_id='84';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Next-of-kin', target_field='relationship', issue_type='is deprecated', field_value='', report_denominator='NextOfKin Count', table_id = 22, hl7_reference = 'NK1-3' WHERE issue_id='85';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', target_object='Next-of-kin', target_field='relationship', issue_type='is ignored', field_value='', report_denominator='NextOfKin Count', table_id = 22, hl7_reference = 'NK1-3' WHERE issue_id='86';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Next-of-kin', target_field='relationship', issue_type='is invalid', field_value='', report_denominator='NextOfKin Count', table_id = 22, hl7_reference = 'NK1-3' WHERE issue_id='87';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='Should', target_object='Next-of-kin', target_field='relationship', issue_type='is missing', field_value='', report_denominator='NextOfKin Count', table_id = 22, hl7_reference = 'NK1-3' WHERE issue_id='88';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Next-of-kin', target_field='relationship', issue_type='is not responsible party', field_value='', report_denominator='NextOfKin Count', table_id = 22, hl7_reference = 'NK1-3' WHERE issue_id='89';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Next-of-kin', target_field='relationship', issue_type='is unrecognized', field_value='', report_denominator='NextOfKin Count', table_id = 22, hl7_reference = 'NK1-3' WHERE issue_id='90';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Next-of-kin', target_field='SSN', issue_type='is missing', field_value='', report_denominator='NextOfKin Count', table_id = NULL, hl7_reference = 'NK1-33' WHERE issue_id='91';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Observation', target_field='value type', issue_type='is deprecated', field_value='', report_denominator='Vaccination Count', table_id = 45, hl7_reference = 'OBX-2' WHERE issue_id='470';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', target_object='Observation', target_field='value type', issue_type='is ignored', field_value='', report_denominator='Vaccination Count', table_id = 45, hl7_reference = 'OBX-2' WHERE issue_id='471';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Observation', target_field='value type', issue_type='is invalid', field_value='', report_denominator='Vaccination Count', table_id = 45, hl7_reference = 'OBX-2' WHERE issue_id='472';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Observation', target_field='value type', issue_type='is missing', field_value='', report_denominator='Vaccination Count', table_id = 45, hl7_reference = 'OBX-2' WHERE issue_id='473';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Observation', target_field='value type', issue_type='is unrecognized', field_value='', report_denominator='Vaccination Count', table_id = 45, hl7_reference = 'OBX-2' WHERE issue_id='474';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Observation', target_field='observation identifier code', issue_type='is deprecated', field_value='', report_denominator='Vaccination Count', table_id = 43, hl7_reference = 'OBX-3' WHERE issue_id='475';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', target_object='Observation', target_field='observation identifier code', issue_type='is ignored', field_value='', report_denominator='Vaccination Count', table_id = 43, hl7_reference = 'OBX-3' WHERE issue_id='476';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Observation', target_field='observation identifier code', issue_type='is invalid', field_value='', report_denominator='Vaccination Count', table_id = 43, hl7_reference = 'OBX-3' WHERE issue_id='477';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Observation', target_field='observation identifier code', issue_type='is missing', field_value='', report_denominator='Vaccination Count', table_id = 43, hl7_reference = 'OBX-3' WHERE issue_id='478';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Observation', target_field='observation identifier code', issue_type='is unrecognized', field_value='', report_denominator='Vaccination Count', table_id = 43, hl7_reference = 'OBX-3' WHERE issue_id='479';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Observation', target_field='observation value', issue_type='is missing', field_value='', report_denominator='Vaccination Count', table_id = NULL, hl7_reference = 'OBX-5' WHERE issue_id='480';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Observation', target_field='date time of observation', issue_type='is missing', field_value='', report_denominator='Vaccination Count', table_id = NULL, hl7_reference = 'OBX-14' WHERE issue_id='481';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Observation', target_field='date time of observation', issue_type='is invalid', field_value='', report_denominator='Vaccination Count', table_id = NULL, hl7_reference = 'OBX-14' WHERE issue_id='482';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='Should', target_object='Patient', target_field='address', issue_type='is missing', field_value='', report_denominator='Patient Count', table_id = NULL, hl7_reference = 'PID-11' WHERE issue_id='92';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', target_object='Patient', target_field='address city', issue_type='is invalid', field_value='', report_denominator='Patient Count', table_id = NULL, hl7_reference = 'PID-11.3' WHERE issue_id='93';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', target_object='Patient', target_field='address city', issue_type='is missing', field_value='', report_denominator='Patient Count', table_id = NULL, hl7_reference = 'PID-11.3' WHERE issue_id='94';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Patient', target_field='address country', issue_type='is deprecated', field_value='', report_denominator='Patient Count', table_id = 2, hl7_reference = 'PID-11.6' WHERE issue_id='95';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', target_object='Patient', target_field='address country', issue_type='is ignored', field_value='', report_denominator='Patient Count', table_id = 2, hl7_reference = 'PID-11.6' WHERE issue_id='96';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Patient', target_field='address country', issue_type='is invalid', field_value='', report_denominator='Patient Count', table_id = 2, hl7_reference = 'PID-11.6' WHERE issue_id='97';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Patient', target_field='address country', issue_type='is missing', field_value='', report_denominator='Patient Count', table_id = 2, hl7_reference = 'PID-11.6' WHERE issue_id='98';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Patient', target_field='address country', issue_type='is unrecognized', field_value='', report_denominator='Patient Count', table_id = 2, hl7_reference = 'PID-11.6' WHERE issue_id='99';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Patient', target_field='address county', issue_type='is deprecated', field_value='', report_denominator='Patient Count', table_id = 3, hl7_reference = 'PID-11.9' WHERE issue_id='100';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', target_object='Patient', target_field='address county', issue_type='is ignored', field_value='', report_denominator='Patient Count', table_id = 3, hl7_reference = 'PID-11.9' WHERE issue_id='101';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Patient', target_field='address county', issue_type='is invalid', field_value='', report_denominator='Patient Count', table_id = 3, hl7_reference = 'PID-11.9' WHERE issue_id='102';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Patient', target_field='address county', issue_type='is missing', field_value='', report_denominator='Patient Count', table_id = 3, hl7_reference = 'PID-11.9' WHERE issue_id='103';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Patient', target_field='address county', issue_type='is unrecognized', field_value='', report_denominator='Patient Count', table_id = 3, hl7_reference = 'PID-11.9' WHERE issue_id='104';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Patient', target_field='address state', issue_type='is deprecated', field_value='', report_denominator='Patient Count', table_id = 4, hl7_reference = 'PID-11.4' WHERE issue_id='105';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', target_object='Patient', target_field='address state', issue_type='is ignored', field_value='', report_denominator='Patient Count', table_id = 4, hl7_reference = 'PID-11.4' WHERE issue_id='106';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', target_object='Patient', target_field='address state', issue_type='is invalid', field_value='', report_denominator='Patient Count', table_id = 4, hl7_reference = 'PID-11.4' WHERE issue_id='107';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', target_object='Patient', target_field='address state', issue_type='is missing', field_value='', report_denominator='Patient Count', table_id = 4, hl7_reference = 'PID-11.4' WHERE issue_id='108';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', target_object='Patient', target_field='address state', issue_type='is unrecognized', field_value='', report_denominator='Patient Count', table_id = 4, hl7_reference = 'PID-11.4' WHERE issue_id='109';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', target_object='Patient', target_field='address street', issue_type='is missing', field_value='', report_denominator='Patient Count', table_id = NULL, hl7_reference = 'PID-11.1' WHERE issue_id='110';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='Can', target_object='Patient', target_field='address street2', issue_type='is missing', field_value='', report_denominator='Patient Count', table_id = NULL, hl7_reference = 'PID-11.2' WHERE issue_id='111';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='Can', target_object='Patient', target_field='address type', issue_type='is missing', field_value='', report_denominator='Patient Count', table_id = 5, hl7_reference = 'PID-11.7' WHERE issue_id='451';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Patient', target_field='address zip', issue_type='is invalid', field_value='', report_denominator='Patient Count', table_id = NULL, hl7_reference = 'PID-11.5' WHERE issue_id='112';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', target_object='Patient', target_field='address zip', issue_type='is missing', field_value='', report_denominator='Patient Count', table_id = NULL, hl7_reference = 'PID-11.5' WHERE issue_id='113';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Patient', target_field='alias', issue_type='is missing', field_value='', report_denominator='Patient Count', table_id = NULL, hl7_reference = 'PID-5' WHERE issue_id='114';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='Blocked', target_object='Patient', target_field='birth date', issue_type='is after submission', field_value='', report_denominator='Patient Count', table_id = NULL, hl7_reference = 'PID-7' WHERE issue_id='115';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='Blocked', target_object='Patient', target_field='birth date', issue_type='is in future', field_value='', report_denominator='Patient Count', table_id = NULL, hl7_reference = 'PID-7' WHERE issue_id='116';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='Blocked', target_object='Patient', target_field='birth date', issue_type='is invalid', field_value='', report_denominator='Patient Count', table_id = NULL, hl7_reference = 'PID-7' WHERE issue_id='117';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='Blocked', target_object='Patient', target_field='birth date', issue_type='is missing', field_value='', report_denominator='Patient Count', table_id = NULL, hl7_reference = 'PID-7' WHERE issue_id='118';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Patient', target_field='birth date', issue_type='is underage', field_value='', report_denominator='Patient Count', table_id = NULL, hl7_reference = 'PID-7' WHERE issue_id='119';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='Blocked', target_object='Patient', target_field='birth date', issue_type='is very long ago', field_value='', report_denominator='Patient Count', table_id = NULL, hl7_reference = 'PID-7' WHERE issue_id='120';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Patient', target_field='birth indicator', issue_type='is invalid', field_value='', report_denominator='Patient Count', table_id = NULL, hl7_reference = 'PID-24' WHERE issue_id='121';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='Should', target_object='Patient', target_field='birth indicator', issue_type='is missing', field_value='', report_denominator='Patient Count', table_id = NULL, hl7_reference = 'PID-24' WHERE issue_id='122';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Patient', target_field='birth order', issue_type='is invalid', field_value='', report_denominator='Patient Count', table_id = 7, hl7_reference = 'PID-25' WHERE issue_id='123';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='Should', target_object='Patient', target_field='birth order', issue_type='is missing', field_value='', report_denominator='Patient Count', table_id = 7, hl7_reference = 'PID-25' WHERE issue_id='124';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Patient', target_field='birth order', issue_type='is missing and multiple birth indicated', field_value='', report_denominator='Patient Count', table_id = 7, hl7_reference = 'PID-25' WHERE issue_id='125';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Patient', target_field='birth place', issue_type='is missing', field_value='', report_denominator='Patient Count', table_id = NULL, hl7_reference = 'PID-23' WHERE issue_id='126';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Patient', target_field='birth registry id', issue_type='is invalid', field_value='', report_denominator='Patient Count', table_id = NULL, hl7_reference = 'PID-3' WHERE issue_id='127';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Patient', target_field='birth registry id', issue_type='is missing', field_value='', report_denominator='Patient Count', table_id = NULL, hl7_reference = 'PID-3' WHERE issue_id='128';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Patient', target_field='class', issue_type='is deprecated', field_value='', report_denominator='Patient Count', table_id = 38, hl7_reference = 'PV1-2' WHERE issue_id='374';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', target_object='Patient', target_field='class', issue_type='is ignored', field_value='', report_denominator='Patient Count', table_id = 38, hl7_reference = 'PV1-2' WHERE issue_id='375';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Patient', target_field='class', issue_type='is invalid', field_value='', report_denominator='Patient Count', table_id = 38, hl7_reference = 'PV1-2' WHERE issue_id='376';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Patient', target_field='class', issue_type='is missing', field_value='', report_denominator='Patient Count', table_id = 38, hl7_reference = 'PV1-2' WHERE issue_id='377';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Patient', target_field='class', issue_type='is unrecognized', field_value='', report_denominator='Patient Count', table_id = 38, hl7_reference = 'PV1-2' WHERE issue_id='378';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='Blocked', target_object='Patient', target_field='death date', issue_type='is before birth', field_value='', report_denominator='Patient Count', table_id = NULL, hl7_reference = 'PID-29' WHERE issue_id='129';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='Blocked', target_object='Patient', target_field='death date', issue_type='is in future', field_value='', report_denominator='Patient Count', table_id = NULL, hl7_reference = 'PID-29' WHERE issue_id='130';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='Blocked', target_object='Patient', target_field='death date', issue_type='is invalid', field_value='', report_denominator='Patient Count', table_id = NULL, hl7_reference = 'PID-29' WHERE issue_id='131';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Patient', target_field='death date', issue_type='is missing', field_value='', report_denominator='Patient Count', table_id = NULL, hl7_reference = 'PID-29' WHERE issue_id='132';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Patient', target_field='death indicator', issue_type='is inconsistent', field_value='', report_denominator='Patient Count', table_id = NULL, hl7_reference = 'PID-30' WHERE issue_id='133';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Patient', target_field='death indicator', issue_type='is missing', field_value='', report_denominator='Patient Count', table_id = NULL, hl7_reference = 'PID-30' WHERE issue_id='134';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Patient', target_field='ethnicity', issue_type='is deprecated', field_value='', report_denominator='Patient Count', table_id = 14, hl7_reference = 'PID-22' WHERE issue_id='135';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', target_object='Patient', target_field='ethnicity', issue_type='is ignored', field_value='', report_denominator='Patient Count', table_id = 14, hl7_reference = 'PID-22' WHERE issue_id='136';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Patient', target_field='ethnicity', issue_type='is invalid', field_value='', report_denominator='Patient Count', table_id = 14, hl7_reference = 'PID-22' WHERE issue_id='137';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='Should', target_object='Patient', target_field='ethnicity', issue_type='is missing', field_value='', report_denominator='Patient Count', table_id = 14, hl7_reference = 'PID-22' WHERE issue_id='138';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Patient', target_field='ethnicity', issue_type='is unrecognized', field_value='', report_denominator='Patient Count', table_id = 14, hl7_reference = 'PID-22' WHERE issue_id='139';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Patient', target_field='gender', issue_type='is deprecated', field_value='', report_denominator='Patient Count', table_id = 19, hl7_reference = 'PID-8' WHERE issue_id='143';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', target_object='Patient', target_field='gender', issue_type='is ignored', field_value='', report_denominator='Patient Count', table_id = 19, hl7_reference = 'PID-8' WHERE issue_id='144';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', target_object='Patient', target_field='gender', issue_type='is invalid', field_value='', report_denominator='Patient Count', table_id = 19, hl7_reference = 'PID-8' WHERE issue_id='145';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='Blocked', target_object='Patient', target_field='gender', issue_type='is missing', field_value='', report_denominator='Patient Count', table_id = 19, hl7_reference = 'PID-8' WHERE issue_id='146';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', target_object='Patient', target_field='gender', issue_type='is unrecognized', field_value='', report_denominator='Patient Count', table_id = 19, hl7_reference = 'PID-8' WHERE issue_id='147';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Patient', target_field='guardian address', issue_type='is missing', field_value='', report_denominator='Patient Underage Count', table_id = NULL, hl7_reference = 'NK1-4' WHERE issue_id='148';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Patient', target_field='guardian address city', issue_type='is missing', field_value='', report_denominator='Patient Underage Count', table_id = NULL, hl7_reference = 'NK1-4.3' WHERE issue_id='149';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Patient', target_field='guardian address state', issue_type='is missing', field_value='', report_denominator='Patient Underage Count', table_id = 4, hl7_reference = 'NK1-4.4' WHERE issue_id='150';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Patient', target_field='guardian address street', issue_type='is missing', field_value='', report_denominator='Patient Underage Count', table_id = NULL, hl7_reference = 'NK1-4.1' WHERE issue_id='151';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Patient', target_field='guardian address zip', issue_type='is missing', field_value='', report_denominator='Patient Underage Count', table_id = NULL, hl7_reference = 'NK1-4.5' WHERE issue_id='152';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Patient', target_field='guardian name', issue_type='is missing', field_value='', report_denominator='Patient Underage Count', table_id = NULL, hl7_reference = 'NK1-2' WHERE issue_id='155';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Patient', target_field='guardian name', issue_type='is same as underage patient', field_value='', report_denominator='Patient Underage Count', table_id = NULL, hl7_reference = 'NK1-2' WHERE issue_id='156';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Patient', target_field='guardian name first', issue_type='is missing', field_value='', report_denominator='Patient Underage Count', table_id = NULL, hl7_reference = 'NK1-2.2' WHERE issue_id='153';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Patient', target_field='guardian name last', issue_type='is missing', field_value='', report_denominator='Patient Underage Count', table_id = NULL, hl7_reference = 'NK1-2.1' WHERE issue_id='154';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Patient', target_field='guardian responsible party', issue_type='is missing', field_value='', report_denominator='Patient Underage Count', table_id = NULL, hl7_reference = 'NK1' WHERE issue_id='157';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Patient', target_field='guardian phone', issue_type='is missing', field_value='', report_denominator='Patient Underage Count', table_id = NULL, hl7_reference = 'NK1-5' WHERE issue_id='158';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Patient', target_field='guardian relationship', issue_type='is missing', field_value='', report_denominator='Patient Underage Count', table_id = NULL, hl7_reference = 'NK1-3' WHERE issue_id='159';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Patient', target_field='immunization registry status', issue_type='is deprecated', field_value='', report_denominator='Patient Count', table_id = 24, hl7_reference = 'PD1-16' WHERE issue_id='160';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', target_object='Patient', target_field='immunization registry status', issue_type='is ignored', field_value='', report_denominator='Patient Count', table_id = 24, hl7_reference = 'PD1-16' WHERE issue_id='161';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Patient', target_field='immunization registry status', issue_type='is invalid', field_value='', report_denominator='Patient Count', table_id = 24, hl7_reference = 'PD1-16' WHERE issue_id='162';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Patient', target_field='immunization registry status', issue_type='is missing', field_value='', report_denominator='Patient Count', table_id = 24, hl7_reference = 'PD1-16' WHERE issue_id='163';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Patient', target_field='immunization registry status', issue_type='is unrecognized', field_value='', report_denominator='Patient Count', table_id = 24, hl7_reference = 'PD1-16' WHERE issue_id='164';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='Can', target_object='Patient', target_field='Medicaid number', issue_type='is invalid', field_value='', report_denominator='Patient Count', table_id = NULL, hl7_reference = 'PID-3' WHERE issue_id='167';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='Can', target_object='Patient', target_field='Medicaid number', issue_type='is missing', field_value='', report_denominator='Patient Count', table_id = NULL, hl7_reference = 'PID-3' WHERE issue_id='168';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Patient', target_field='middle name', issue_type='is missing', field_value='', report_denominator='Patient Count', table_id = NULL, hl7_reference = 'PID-5.3' WHERE issue_id='169';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Patient', target_field='middle name', issue_type='may be initial', field_value='', report_denominator='Patient Count', table_id = NULL, hl7_reference = 'PID-5.3' WHERE issue_id='170';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='Should', target_object='Patient', target_field='mother''s maiden name', issue_type='is missing', field_value='', report_denominator='Patient Count', table_id = NULL, hl7_reference = 'PID-6.1' WHERE issue_id='171';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='Should', target_object='Patient', target_field='name', issue_type='may be temporary newborn name', field_value='', report_denominator='Patient Count', table_id = NULL, hl7_reference = 'PID-5' WHERE issue_id='172';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='Should', target_object='Patient', target_field='name', issue_type='may be test name', field_value='', report_denominator='Patient Count', table_id = NULL, hl7_reference = 'PID-5' WHERE issue_id='173';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', target_object='Patient', target_field='name first', issue_type='is invalid', field_value='', report_denominator='Patient Count', table_id = NULL, hl7_reference = 'PID-5.2' WHERE issue_id='140';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', target_object='Patient', target_field='name first', issue_type='is missing', field_value='', report_denominator='Patient Count', table_id = NULL, hl7_reference = 'PID-5.2' WHERE issue_id='141';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Patient', target_field='name first', issue_type='may include middle initial', field_value='', report_denominator='Patient Count', table_id = NULL, hl7_reference = 'PID-5.2' WHERE issue_id='142';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', target_object='Patient', target_field='name last', issue_type='is invalid', field_value='', report_denominator='Patient Count', table_id = NULL, hl7_reference = 'PID-5.1' WHERE issue_id='165';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', target_object='Patient', target_field='name last', issue_type='is missing', field_value='', report_denominator='Patient Count', table_id = NULL, hl7_reference = 'PID-5.1' WHERE issue_id='166';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Patient', target_field='name type code', issue_type='is deprecated', field_value='', report_denominator='Patient Count', table_id = 21, hl7_reference = 'PID-5.7' WHERE issue_id='405';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', target_object='Patient', target_field='name type code', issue_type='is ignored', field_value='', report_denominator='Patient Count', table_id = 21, hl7_reference = 'PID-5.7' WHERE issue_id='406';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Patient', target_field='name type code', issue_type='is invalid', field_value='', report_denominator='Patient Count', table_id = 21, hl7_reference = 'PID-5.7' WHERE issue_id='407';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Patient', target_field='name type code', issue_type='is missing', field_value='', report_denominator='Patient Count', table_id = 21, hl7_reference = 'PID-5.7' WHERE issue_id='408';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Patient', target_field='name type code', issue_type='is unrecognized', field_value='', report_denominator='Patient Count', table_id = 21, hl7_reference = 'PID-5.7' WHERE issue_id='409';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Patient', target_field='phone', issue_type='is incomplete', field_value='', report_denominator='Patient Count', table_id = NULL, hl7_reference = 'PID-13' WHERE issue_id='174';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Patient', target_field='phone', issue_type='is invalid', field_value='', report_denominator='Patient Count', table_id = NULL, hl7_reference = 'PID-13' WHERE issue_id='175';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Patient', target_field='phone', issue_type='is missing', field_value='', report_denominator='Patient Count', table_id = NULL, hl7_reference = 'PID-13' WHERE issue_id='176';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Patient', target_field='phone tel use code', issue_type='is deprecated', field_value='', report_denominator='Patient Count', table_id = 41, hl7_reference = 'PID-13.2' WHERE issue_id='453';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', target_object='Patient', target_field='phone tel use code', issue_type='is ignored', field_value='', report_denominator='Patient Count', table_id = 41, hl7_reference = 'PID-13.2' WHERE issue_id='454';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Patient', target_field='phone tel use code', issue_type='is invalid', field_value='', report_denominator='Patient Count', table_id = 41, hl7_reference = 'PID-13.2' WHERE issue_id='455';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Patient', target_field='phone tel use code', issue_type='is missing', field_value='', report_denominator='Patient Count', table_id = 41, hl7_reference = 'PID-13.2' WHERE issue_id='456';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Patient', target_field='phone tel use code', issue_type='is unrecognized', field_value='', report_denominator='Patient Count', table_id = 41, hl7_reference = 'PID-13.2' WHERE issue_id='457';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Patient', target_field='phone tel equip code', issue_type='is deprecated', field_value='', report_denominator='Patient Count', table_id = 42, hl7_reference = 'PID-13.3' WHERE issue_id='458';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', target_object='Patient', target_field='phone tel equip code', issue_type='is ignored', field_value='', report_denominator='Patient Count', table_id = 42, hl7_reference = 'PID-13.3' WHERE issue_id='459';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Patient', target_field='phone tel equip code', issue_type='is invalid', field_value='', report_denominator='Patient Count', table_id = 42, hl7_reference = 'PID-13.3' WHERE issue_id='460';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Patient', target_field='phone tel equip code', issue_type='is missing', field_value='', report_denominator='Patient Count', table_id = 42, hl7_reference = 'PID-13.3' WHERE issue_id='461';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Patient', target_field='phone tel equip code', issue_type='is unrecognized', field_value='', report_denominator='Patient Count', table_id = 42, hl7_reference = 'PID-13.3' WHERE issue_id='462';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Patient', target_field='primary facility id', issue_type='is deprecated', field_value='', report_denominator='Patient Count', table_id = 13, hl7_reference = 'PD1-3.3' WHERE issue_id='177';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', target_object='Patient', target_field='primary facility id', issue_type='is ignored', field_value='', report_denominator='Patient Count', table_id = 13, hl7_reference = 'PD1-3.3' WHERE issue_id='178';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', target_object='Patient', target_field='primary facility id', issue_type='is invalid', field_value='', report_denominator='Patient Count', table_id = 13, hl7_reference = 'PD1-3.3' WHERE issue_id='179';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Patient', target_field='primary facility id', issue_type='is missing', field_value='', report_denominator='Patient Count', table_id = 13, hl7_reference = 'PD1-3.3' WHERE issue_id='180';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Patient', target_field='primary facility id', issue_type='is unrecognized', field_value='', report_denominator='Patient Count', table_id = 13, hl7_reference = 'PD1-3.3' WHERE issue_id='181';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Patient', target_field='primary facility name', issue_type='is missing', field_value='', report_denominator='Patient Count', table_id = NULL, hl7_reference = 'PD1-3.1' WHERE issue_id='182';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Patient', target_field='primary language', issue_type='is deprecated', field_value='', report_denominator='Patient Count', table_id = 20, hl7_reference = 'PID-15' WHERE issue_id='183';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', target_object='Patient', target_field='primary language', issue_type='is ignored', field_value='', report_denominator='Patient Count', table_id = 20, hl7_reference = 'PID-15' WHERE issue_id='184';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Patient', target_field='primary language', issue_type='is invalid', field_value='', report_denominator='Patient Count', table_id = 20, hl7_reference = 'PID-15' WHERE issue_id='185';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Patient', target_field='primary language', issue_type='is missing', field_value='', report_denominator='Patient Count', table_id = 20, hl7_reference = 'PID-15' WHERE issue_id='186';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Patient', target_field='primary language', issue_type='is unrecognized', field_value='', report_denominator='Patient Count', table_id = 20, hl7_reference = 'PID-15' WHERE issue_id='187';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Patient', target_field='primary physician id', issue_type='is deprecated', field_value='', report_denominator='Patient Count', table_id = 23, hl7_reference = 'PD1-4.1' WHERE issue_id='188';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', target_object='Patient', target_field='primary physician id', issue_type='is ignored', field_value='', report_denominator='Patient Count', table_id = 23, hl7_reference = 'PD1-4.1' WHERE issue_id='189';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Patient', target_field='primary physician id', issue_type='is invalid', field_value='', report_denominator='Patient Count', table_id = 23, hl7_reference = 'PD1-4.1' WHERE issue_id='190';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Patient', target_field='primary physician id', issue_type='is missing', field_value='', report_denominator='Patient Count', table_id = 23, hl7_reference = 'PD1-4.1' WHERE issue_id='191';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Patient', target_field='primary physician id', issue_type='is unrecognized', field_value='', report_denominator='Patient Count', table_id = 23, hl7_reference = 'PD1-4.1' WHERE issue_id='192';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Patient', target_field='primary physician name', issue_type='is missing', field_value='', report_denominator='Patient Count', table_id = NULL, hl7_reference = 'PD1-4.2' WHERE issue_id='193';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Patient', target_field='protection indicator', issue_type='is deprecated', field_value='', report_denominator='Patient Count', table_id = 16, hl7_reference = 'PD1-12' WHERE issue_id='194';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', target_object='Patient', target_field='protection indicator', issue_type='is ignored', field_value='', report_denominator='Patient Count', table_id = 16, hl7_reference = 'PD1-12' WHERE issue_id='195';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Patient', target_field='protection indicator', issue_type='is invalid', field_value='', report_denominator='Patient Count', table_id = 16, hl7_reference = 'PD1-12' WHERE issue_id='196';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Patient', target_field='protection indicator', issue_type='is missing', field_value='', report_denominator='Patient Count', table_id = 16, hl7_reference = 'PD1-12' WHERE issue_id='197';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Patient', target_field='protection indicator', issue_type='is unrecognized', field_value='', report_denominator='Patient Count', table_id = 16, hl7_reference = 'PD1-12' WHERE issue_id='198';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Patient', target_field='protection indicator', issue_type='is valued as', field_value='no', report_denominator='Patient Count', table_id = 16, hl7_reference = 'PD1-12' WHERE issue_id='199';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Patient', target_field='protection indicator', issue_type='is valued as', field_value='yes', report_denominator='Patient Count', table_id = 16, hl7_reference = 'PD1-12' WHERE issue_id='200';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Patient', target_field='publicity code', issue_type='is deprecated', field_value='', report_denominator='Patient Count', table_id = 17, hl7_reference = 'PD1-11' WHERE issue_id='201';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', target_object='Patient', target_field='publicity code', issue_type='is ignored', field_value='', report_denominator='Patient Count', table_id = 17, hl7_reference = 'PD1-11' WHERE issue_id='202';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Patient', target_field='publicity code', issue_type='is invalid', field_value='', report_denominator='Patient Count', table_id = 17, hl7_reference = 'PD1-11' WHERE issue_id='203';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Patient', target_field='publicity code', issue_type='is missing', field_value='', report_denominator='Patient Count', table_id = 17, hl7_reference = 'PD1-11' WHERE issue_id='204';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Patient', target_field='publicity code', issue_type='is unrecognized', field_value='', report_denominator='Patient Count', table_id = 17, hl7_reference = 'PD1-11' WHERE issue_id='205';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Patient', target_field='race', issue_type='is deprecated', field_value='', report_denominator='Patient Count', table_id = 18, hl7_reference = 'PID-10' WHERE issue_id='206';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', target_object='Patient', target_field='race', issue_type='is ignored', field_value='', report_denominator='Patient Count', table_id = 18, hl7_reference = 'PID-10' WHERE issue_id='207';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Patient', target_field='race', issue_type='is invalid', field_value='', report_denominator='Patient Count', table_id = 18, hl7_reference = 'PID-10' WHERE issue_id='208';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Patient', target_field='race', issue_type='is missing', field_value='', report_denominator='Patient Count', table_id = 18, hl7_reference = 'PID-10' WHERE issue_id='209';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Patient', target_field='race', issue_type='is unrecognized', field_value='', report_denominator='Patient Count', table_id = 18, hl7_reference = 'PID-10' WHERE issue_id='210';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Patient', target_field='registry id', issue_type='is missing', field_value='', report_denominator='Patient Count', table_id = NULL, hl7_reference = 'PID-3' WHERE issue_id='211';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Patient', target_field='registry id', issue_type='is unrecognized', field_value='', report_denominator='Patient Count', table_id = NULL, hl7_reference = 'PID-3' WHERE issue_id='212';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Patient', target_field='registry status', issue_type='is deprecated', field_value='', report_denominator='Patient Count', table_id = NULL, hl7_reference = 'PD1-16' WHERE issue_id='213';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', target_object='Patient', target_field='registry status', issue_type='is ignored', field_value='', report_denominator='Patient Count', table_id = NULL, hl7_reference = 'PD1-16' WHERE issue_id='214';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Patient', target_field='registry status', issue_type='is invalid', field_value='', report_denominator='Patient Count', table_id = NULL, hl7_reference = 'PD1-16' WHERE issue_id='215';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Patient', target_field='registry status', issue_type='is missing', field_value='', report_denominator='Patient Count', table_id = NULL, hl7_reference = 'PD1-16' WHERE issue_id='216';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Patient', target_field='registry status', issue_type='is unrecognized', field_value='', report_denominator='Patient Count', table_id = NULL, hl7_reference = 'PD1-16' WHERE issue_id='217';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Patient', target_field='SSN', issue_type='is invalid', field_value='', report_denominator='Patient Count', table_id = NULL, hl7_reference = 'PID-3' WHERE issue_id='218';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Patient', target_field='SSN', issue_type='is missing', field_value='', report_denominator='Patient Count', table_id = NULL, hl7_reference = 'PID-3' WHERE issue_id='219';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='Blocked', target_object='Patient', target_field='submitter id', issue_type='is missing', field_value='', report_denominator='Patient Count', table_id = NULL, hl7_reference = 'PID-3' WHERE issue_id='220';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Patient', target_field='submitter id authority', issue_type='is missing', field_value='', report_denominator='Patient Count', table_id = 11, hl7_reference = 'PID-3.4' WHERE issue_id='393';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Patient', target_field='submitter id type code', issue_type='is missing', field_value='', report_denominator='Patient Count', table_id = 12, hl7_reference = 'PID-3.5' WHERE issue_id='394';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', target_object='Patient', target_field='VFC effective date', issue_type='is before birth', field_value='', report_denominator='Patient Count', table_id = NULL, hl7_reference = 'PV1-20.2' WHERE issue_id='221';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', target_object='Patient', target_field='VFC effective date', issue_type='is in future', field_value='', report_denominator='Patient Count', table_id = NULL, hl7_reference = 'PV1-20.2' WHERE issue_id='222';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', target_object='Patient', target_field='VFC effective date', issue_type='is invalid', field_value='', report_denominator='Patient Count', table_id = NULL, hl7_reference = 'PV1-20.2' WHERE issue_id='223';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Patient', target_field='VFC effective date', issue_type='is missing', field_value='', report_denominator='Patient Count', table_id = NULL, hl7_reference = 'PV1-20.2' WHERE issue_id='224';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Patient', target_field='VFC status', issue_type='is deprecated', field_value='', report_denominator='Patient Count', table_id = 10, hl7_reference = 'PV1-20.1' WHERE issue_id='225';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', target_object='Patient', target_field='VFC status', issue_type='is ignored', field_value='', report_denominator='Patient Count', table_id = 10, hl7_reference = 'PV1-20.1' WHERE issue_id='226';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Patient', target_field='VFC status', issue_type='is invalid', field_value='', report_denominator='Patient Count', table_id = 10, hl7_reference = 'PV1-20.1' WHERE issue_id='227';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Patient', target_field='VFC status', issue_type='is missing', field_value='', report_denominator='Patient Count', table_id = 10, hl7_reference = 'PV1-20.1' WHERE issue_id='228';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Patient', target_field='VFC status', issue_type='is unrecognized', field_value='', report_denominator='Patient Count', table_id = 10, hl7_reference = 'PV1-20.1' WHERE issue_id='229';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Patient', target_field='WIC id', issue_type='is invalid', field_value='', report_denominator='Patient Count', table_id = NULL, hl7_reference = 'PID-3' WHERE issue_id='230';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Patient', target_field='WIC id', issue_type='is missing', field_value='', report_denominator='Patient Count', table_id = NULL, hl7_reference = 'PID-3' WHERE issue_id='231';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='action code', issue_type='is deprecated', field_value='', report_denominator='Vaccination Count', table_id = 25, hl7_reference = 'RXA-21' WHERE issue_id='232';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', target_object='Vaccination', target_field='action code', issue_type='is ignored', field_value='', report_denominator='Vaccination Count', table_id = 25, hl7_reference = 'RXA-21' WHERE issue_id='233';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', target_object='Vaccination', target_field='action code', issue_type='is invalid', field_value='', report_denominator='Vaccination Count', table_id = 25, hl7_reference = 'RXA-21' WHERE issue_id='234';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Vaccination', target_field='action code', issue_type='is missing', field_value='', report_denominator='Vaccination Count', table_id = 25, hl7_reference = 'RXA-21' WHERE issue_id='235';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='action code', issue_type='is unrecognized', field_value='', report_denominator='Vaccination Count', table_id = 25, hl7_reference = 'RXA-21' WHERE issue_id='236';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Vaccination', target_field='action code', issue_type='is valued as', field_value='add', report_denominator='Vaccination Count', table_id = 25, hl7_reference = 'RXA-21' WHERE issue_id='237';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Vaccination', target_field='action code', issue_type='is valued as', field_value='add or update', report_denominator='Vaccination Count', table_id = 25, hl7_reference = 'RXA-21' WHERE issue_id='238';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Vaccination', target_field='action code', issue_type='is valued as', field_value='delete', report_denominator='Vaccination Count', table_id = 25, hl7_reference = 'RXA-21' WHERE issue_id='239';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Vaccination', target_field='action code', issue_type='is valued as', field_value='update', report_denominator='Vaccination Count', table_id = 25, hl7_reference = 'RXA-21' WHERE issue_id='240';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='admin code', issue_type='is deprecated', field_value='', report_denominator='Vaccination Count', table_id = NULL, hl7_reference = 'RXA-5' WHERE issue_id='241';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', target_object='Vaccination', target_field='admin code', issue_type='is ignored', field_value='', report_denominator='Vaccination Count', table_id = NULL, hl7_reference = 'RXA-5' WHERE issue_id='242';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', target_object='Vaccination', target_field='admin code', issue_type='is invalid', field_value='', report_denominator='Vaccination Count', table_id = NULL, hl7_reference = 'RXA-5' WHERE issue_id='243';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', target_object='Vaccination', target_field='admin code', issue_type='is missing', field_value='', report_denominator='Vaccination Count', table_id = NULL, hl7_reference = 'RXA-5' WHERE issue_id='244';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='admin code', issue_type='is not specific', field_value='', report_denominator='Vaccination Admin Count', table_id = NULL, hl7_reference = 'RXA-5' WHERE issue_id='245';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='admin code', issue_type='is not vaccine', field_value='', report_denominator='Vaccination Count', table_id = NULL, hl7_reference = 'RXA-5' WHERE issue_id='246';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', target_object='Vaccination', target_field='admin code', issue_type='is unrecognized', field_value='', report_denominator='Vaccination Count', table_id = NULL, hl7_reference = 'RXA-5' WHERE issue_id='247';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', target_object='Vaccination', target_field='admin code', issue_type='is valued as', field_value='not administered', report_denominator='Vaccination Count', table_id = NULL, hl7_reference = 'RXA-5' WHERE issue_id='248';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='admin code', issue_type='is valued as', field_value='unknown', report_denominator='Vaccination Count', table_id = NULL, hl7_reference = 'RXA-5' WHERE issue_id='249';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='admin code table', issue_type='is missing', field_value='', report_denominator='Vaccination Count', table_id = NULL, hl7_reference = 'RXA-5' WHERE issue_id='483';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='admin code table', issue_type='is invalid', field_value='', report_denominator='Vaccination Count', table_id = NULL, hl7_reference = 'RXA-5' WHERE issue_id='484';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='admin code', issue_type='may be variation of previously reported codes', field_value='', report_denominator='Vaccination Count', table_id = NULL, hl7_reference = 'RXA-5' WHERE issue_id='250';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='admin date', issue_type='is after lot expiration date', field_value='', report_denominator='Vaccination Admin Count', table_id = NULL, hl7_reference = 'RXA-3' WHERE issue_id='251';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', target_object='Vaccination', target_field='admin date', issue_type='is after message submitted', field_value='', report_denominator='Vaccination Count', table_id = NULL, hl7_reference = 'RXA-3' WHERE issue_id='252';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', target_object='Vaccination', target_field='admin date', issue_type='is after patient death date', field_value='', report_denominator='Vaccination Count', table_id = NULL, hl7_reference = 'RXA-3' WHERE issue_id='253';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', target_object='Vaccination', target_field='admin date', issue_type='is after system entry date', field_value='', report_denominator='Vaccination Count', table_id = NULL, hl7_reference = 'RXA-3' WHERE issue_id='254';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', target_object='Vaccination', target_field='admin date', issue_type='is before birth', field_value='', report_denominator='Vaccination Count', table_id = NULL, hl7_reference = 'RXA-3' WHERE issue_id='255';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='admin date', issue_type='is before or after expected vaccine usage range', field_value='', report_denominator='Vaccination Count', table_id = NULL, hl7_reference = 'RXA-3' WHERE issue_id='256';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', target_object='Vaccination', target_field='admin date', issue_type='is before or after licensed vaccine range', field_value='', report_denominator='Vaccination Count', table_id = NULL, hl7_reference = 'RXA-3' WHERE issue_id='257';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='admin date', issue_type='is before or after when expected for patient age', field_value='', report_denominator='Vaccination Count', table_id = NULL, hl7_reference = 'RXA-3' WHERE issue_id='258';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', target_object='Vaccination', target_field='admin date', issue_type='is before or after when valid for patient age', field_value='', report_denominator='Vaccination Count', table_id = NULL, hl7_reference = 'RXA-3' WHERE issue_id='259';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', target_object='Vaccination', target_field='admin date', issue_type='is invalid', field_value='', report_denominator='Vaccination Count', table_id = NULL, hl7_reference = 'RXA-3' WHERE issue_id='260';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', target_object='Vaccination', target_field='admin date', issue_type='is missing', field_value='', report_denominator='Vaccination Count', table_id = NULL, hl7_reference = 'RXA-3' WHERE issue_id='261';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Vaccination', target_field='admin date', issue_type='is on 15th day of month', field_value='', report_denominator='Vaccination Count', table_id = NULL, hl7_reference = 'RXA-3' WHERE issue_id='262';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Vaccination', target_field='admin date', issue_type='is on first day of month', field_value='', report_denominator='Vaccination Count', table_id = NULL, hl7_reference = 'RXA-3' WHERE issue_id='263';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Vaccination', target_field='admin date', issue_type='is on last day of month', field_value='', report_denominator='Vaccination Count', table_id = NULL, hl7_reference = 'RXA-3' WHERE issue_id='264';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='admin date', issue_type='is reported late', field_value='', report_denominator='Vaccination Admin Count', table_id = NULL, hl7_reference = 'RXA-3' WHERE issue_id='265';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='admin date end', issue_type='is different from start date', field_value='', report_denominator='Vaccination Count', table_id = NULL, hl7_reference = 'RXA-3' WHERE issue_id='266';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Vaccination', target_field='admin date end', issue_type='is missing', field_value='', report_denominator='Vaccination Count', table_id = NULL, hl7_reference = 'RXA-3' WHERE issue_id='267';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='administered amount', issue_type='is invalid', field_value='', report_denominator='Vaccination Admin Count', table_id = NULL, hl7_reference = 'RXA-6' WHERE issue_id='268';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Vaccination', target_field='administered amount', issue_type='is missing', field_value='', report_denominator='Vaccination Admin Count', table_id = NULL, hl7_reference = 'RXA-6' WHERE issue_id='269';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Vaccination', target_field='administered amount', issue_type='is valued as', field_value='zero', report_denominator='Vaccination Admin Count', table_id = NULL, hl7_reference = 'RXA-6' WHERE issue_id='270';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Vaccination', target_field='administered amount', issue_type='is valued as', field_value='unknown', report_denominator='Vaccination Admin Count', table_id = NULL, hl7_reference = 'RXA-6' WHERE issue_id='271';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='administered unit', issue_type='is deprecated', field_value='', report_denominator='Vaccination Admin Count', table_id = 6, hl7_reference = 'RXA-7' WHERE issue_id='447';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', target_object='Vaccination', target_field='administered unit', issue_type='is ignored', field_value='', report_denominator='Vaccination Admin Count', table_id = 6, hl7_reference = 'RXA-7' WHERE issue_id='448';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='administered unit', issue_type='is invalid', field_value='', report_denominator='Vaccination Admin Count', table_id = 6, hl7_reference = 'RXA-7' WHERE issue_id='449';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Vaccination', target_field='administered unit', issue_type='is missing', field_value='', report_denominator='Vaccination Admin Count', table_id = 6, hl7_reference = 'RXA-7' WHERE issue_id='272';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='administered unit', issue_type='is unrecognized', field_value='', report_denominator='Vaccination Admin Count', table_id = 6, hl7_reference = 'RXA-7' WHERE issue_id='450';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='body route', issue_type='is deprecated', field_value='', report_denominator='Vaccination Admin Count', table_id = 8, hl7_reference = 'RXR-1' WHERE issue_id='273';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', target_object='Vaccination', target_field='body route', issue_type='is ignored', field_value='', report_denominator='Vaccination Admin Count', table_id = 8, hl7_reference = 'RXR-1' WHERE issue_id='274';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='body route', issue_type='is invalid', field_value='', report_denominator='Vaccination Admin Count', table_id = 8, hl7_reference = 'RXR-1' WHERE issue_id='275';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='body route', issue_type='is invalid for vaccine indicated', field_value='', report_denominator='Vaccination Admin Count', table_id = 8, hl7_reference = 'RXR-1' WHERE issue_id='276';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Vaccination', target_field='body route', issue_type='is missing', field_value='', report_denominator='Vaccination Admin Count', table_id = 8, hl7_reference = 'RXR-1' WHERE issue_id='277';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='body route', issue_type='is unrecognized', field_value='', report_denominator='Vaccination Admin Count', table_id = 8, hl7_reference = 'RXR-1' WHERE issue_id='278';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='body site', issue_type='is deprecated', field_value='', report_denominator='Vaccination Admin Count', table_id = 9, hl7_reference = 'RXR-2' WHERE issue_id='279';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', target_object='Vaccination', target_field='body site', issue_type='is ignored', field_value='', report_denominator='Vaccination Admin Count', table_id = 9, hl7_reference = 'RXR-2' WHERE issue_id='280';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='body site', issue_type='is invalid', field_value='', report_denominator='Vaccination Admin Count', table_id = 9, hl7_reference = 'RXR-2' WHERE issue_id='281';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='body site', issue_type='is invalid for vaccine indicated', field_value='', report_denominator='Vaccination Admin Count', table_id = 9, hl7_reference = 'RXR-2' WHERE issue_id='282';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Vaccination', target_field='body site', issue_type='is missing', field_value='', report_denominator='Vaccination Admin Count', table_id = 9, hl7_reference = 'RXR-2' WHERE issue_id='283';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='body site', issue_type='is unrecognized', field_value='', report_denominator='Vaccination Admin Count', table_id = 9, hl7_reference = 'RXR-2' WHERE issue_id='284';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='completion status', issue_type='is deprecated', field_value='', report_denominator='Vaccination Count', table_id = 26, hl7_reference = 'RXA-20' WHERE issue_id='285';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', target_object='Vaccination', target_field='completion status', issue_type='is ignored', field_value='', report_denominator='Vaccination Count', table_id = 26, hl7_reference = 'RXA-20' WHERE issue_id='286';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', target_object='Vaccination', target_field='completion status', issue_type='is invalid', field_value='', report_denominator='Vaccination Count', table_id = 26, hl7_reference = 'RXA-20' WHERE issue_id='287';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Vaccination', target_field='completion status', issue_type='is missing', field_value='', report_denominator='Vaccination Count', table_id = 26, hl7_reference = 'RXA-20' WHERE issue_id='288';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', target_object='Vaccination', target_field='completion status', issue_type='is unrecognized', field_value='', report_denominator='Vaccination Count', table_id = 26, hl7_reference = 'RXA-20' WHERE issue_id='289';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Vaccination', target_field='completion status', issue_type='is valued as', field_value='completed', report_denominator='Vaccination Count', table_id = 26, hl7_reference = 'RXA-20' WHERE issue_id='290';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', target_object='Vaccination', target_field='completion status', issue_type='is valued as', field_value='not administered', report_denominator='Vaccination Count', table_id = 26, hl7_reference = 'RXA-20' WHERE issue_id='291';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', target_object='Vaccination', target_field='completion status', issue_type='is valued as', field_value='partially administered', report_denominator='Vaccination Count', table_id = 26, hl7_reference = 'RXA-20' WHERE issue_id='292';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', target_object='Vaccination', target_field='completion status', issue_type='is valued as', field_value='refused', report_denominator='Vaccination Count', table_id = 26, hl7_reference = 'RXA-20' WHERE issue_id='293';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='confidentiality code', issue_type='is deprecated', field_value='', report_denominator='Vaccination Count', table_id = 27, hl7_reference = 'ORC-28' WHERE issue_id='294';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', target_object='Vaccination', target_field='confidentiality code', issue_type='is ignored', field_value='', report_denominator='Vaccination Count', table_id = 27, hl7_reference = 'ORC-28' WHERE issue_id='295';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='confidentiality code', issue_type='is invalid', field_value='', report_denominator='Vaccination Count', table_id = 27, hl7_reference = 'ORC-28' WHERE issue_id='296';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Vaccination', target_field='confidentiality code', issue_type='is missing', field_value='', report_denominator='Vaccination Count', table_id = 27, hl7_reference = 'ORC-28' WHERE issue_id='297';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='confidentiality code', issue_type='is unrecognized', field_value='', report_denominator='Vaccination Count', table_id = 27, hl7_reference = 'ORC-28' WHERE issue_id='298';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='confidentiality code', issue_type='is valued as', field_value='restricted', report_denominator='Vaccination Count', table_id = 27, hl7_reference = 'ORC-28' WHERE issue_id='299';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='CPT code', issue_type='is deprecated', field_value='', report_denominator='Vaccination Count', table_id = 28, hl7_reference = 'RXA-5' WHERE issue_id='300';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', target_object='Vaccination', target_field='CPT code', issue_type='is ignored', field_value='', report_denominator='Vaccination Count', table_id = 28, hl7_reference = 'RXA-5' WHERE issue_id='301';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='CPT code', issue_type='is invalid', field_value='', report_denominator='Vaccination Count', table_id = 28, hl7_reference = 'RXA-5' WHERE issue_id='302';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Vaccination', target_field='CPT code', issue_type='is missing', field_value='', report_denominator='Vaccination Count', table_id = 28, hl7_reference = 'RXA-5' WHERE issue_id='303';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='CPT code', issue_type='is unrecognized', field_value='', report_denominator='Vaccination Count', table_id = 28, hl7_reference = 'RXA-5' WHERE issue_id='304';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='CVX code', issue_type='is deprecated', field_value='', report_denominator='Vaccination Count', table_id = 29, hl7_reference = 'RXA-5' WHERE issue_id='305';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', target_object='Vaccination', target_field='CVX code', issue_type='is ignored', field_value='', report_denominator='Vaccination Count', table_id = 29, hl7_reference = 'RXA-5' WHERE issue_id='306';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='CVX code', issue_type='is invalid', field_value='', report_denominator='Vaccination Count', table_id = 29, hl7_reference = 'RXA-5' WHERE issue_id='307';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Vaccination', target_field='CVX code', issue_type='is missing', field_value='', report_denominator='Vaccination Count', table_id = 29, hl7_reference = 'RXA-5' WHERE issue_id='308';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='CVX code', issue_type='is unrecognized', field_value='', report_denominator='Vaccination Count', table_id = 29, hl7_reference = 'RXA-5' WHERE issue_id='309';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='CVX code and CPT code', issue_type='are inconsistent', field_value='', report_denominator='Vaccination Count', table_id = NULL, hl7_reference = 'RXA-5' WHERE issue_id='310';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='facility id', issue_type='is deprecated', field_value='', report_denominator='Vaccination Admin Count', table_id = 13, hl7_reference = 'RXA-11.4' WHERE issue_id='311';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', target_object='Vaccination', target_field='facility id', issue_type='is ignored', field_value='', report_denominator='Vaccination Admin Count', table_id = 13, hl7_reference = 'RXA-11.4' WHERE issue_id='312';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Vaccination', target_field='facility id', issue_type='is invalid', field_value='', report_denominator='Vaccination Admin Count', table_id = 13, hl7_reference = 'RXA-11.4' WHERE issue_id='313';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Vaccination', target_field='facility id', issue_type='is missing', field_value='', report_denominator='Vaccination Admin Count', table_id = 13, hl7_reference = 'RXA-11.4' WHERE issue_id='314';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Vaccination', target_field='facility id', issue_type='is unrecognized', field_value='', report_denominator='Vaccination Admin Count', table_id = 13, hl7_reference = 'RXA-11.4' WHERE issue_id='315';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Vaccination', target_field='facility name', issue_type='is missing', field_value='', report_denominator='Vaccination Admin Count', table_id = NULL, hl7_reference = 'RXA-11.4' WHERE issue_id='316';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='filler order number', issue_type='is deprecated', field_value='', report_denominator='Vaccination Count', table_id = NULL, hl7_reference = 'ORC-3' WHERE issue_id='379';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', target_object='Vaccination', target_field='filler order number', issue_type='is ignored', field_value='', report_denominator='Vaccination Count', table_id = NULL, hl7_reference = 'ORC-3' WHERE issue_id='380';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='filler order number', issue_type='is invalid', field_value='', report_denominator='Vaccination Count', table_id = NULL, hl7_reference = 'ORC-3' WHERE issue_id='381';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Vaccination', target_field='filler order number', issue_type='is missing', field_value='', report_denominator='Vaccination Count', table_id = NULL, hl7_reference = 'ORC-3' WHERE issue_id='382';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='filler order number', issue_type='is unrecognized', field_value='', report_denominator='Vaccination Count', table_id = NULL, hl7_reference = 'ORC-3' WHERE issue_id='383';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='financial eligibility code', issue_type='is deprecated', field_value='', report_denominator='Vaccination Admin Count', table_id = 10, hl7_reference = 'OBX-5' WHERE issue_id='465';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', target_object='Vaccination', target_field='financial eligibility code', issue_type='is ignored', field_value='', report_denominator='Vaccination Admin Count', table_id = 10, hl7_reference = 'OBX-5' WHERE issue_id='466';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', target_object='Vaccination', target_field='financial eligibility code', issue_type='is invalid', field_value='', report_denominator='Vaccination Admin Count', table_id = 10, hl7_reference = 'OBX-5' WHERE issue_id='467';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', target_object='Vaccination', target_field='financial eligibility code', issue_type='is missing', field_value='', report_denominator='Vaccination Admin Count', table_id = 10, hl7_reference = 'OBX-5' WHERE issue_id='468';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', target_object='Vaccination', target_field='financial eligibility code', issue_type='is unrecognized', field_value='', report_denominator='Vaccination Admin Count', table_id = 10, hl7_reference = 'OBX-5' WHERE issue_id='469';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='given by', issue_type='is deprecated', field_value='', report_denominator='Vaccination Admin Count', table_id = 23, hl7_reference = 'RXA-10' WHERE issue_id='317';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', target_object='Vaccination', target_field='given by', issue_type='is ignored', field_value='', report_denominator='Vaccination Admin Count', table_id = 23, hl7_reference = 'RXA-10' WHERE issue_id='318';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='given by', issue_type='is invalid', field_value='', report_denominator='Vaccination Admin Count', table_id = 23, hl7_reference = 'RXA-10' WHERE issue_id='319';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Vaccination', target_field='given by', issue_type='is missing', field_value='', report_denominator='Vaccination Admin Count', table_id = 23, hl7_reference = 'RXA-10' WHERE issue_id='320';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='given by', issue_type='is unrecognized', field_value='', report_denominator='Vaccination Admin Count', table_id = 23, hl7_reference = 'RXA-10' WHERE issue_id='321';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Vaccination', target_field='id', issue_type='is missing', field_value='', report_denominator='Vaccination Count', table_id = NULL, hl7_reference = 'ORC-3' WHERE issue_id='322';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Vaccination', target_field='id of receiver', issue_type='is missing', field_value='', report_denominator='Vaccination Count', table_id = NULL, hl7_reference = 'ORC-2' WHERE issue_id='323';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='id of receiver', issue_type='is unrecognized', field_value='', report_denominator='Vaccination Count', table_id = NULL, hl7_reference = 'ORC-2' WHERE issue_id='324';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Vaccination', target_field='id of sender', issue_type='is missing', field_value='', report_denominator='Vaccination Count', table_id = NULL, hl7_reference = 'ORC-3' WHERE issue_id='325';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='id of sender', issue_type='is unrecognized', field_value='', report_denominator='Vaccination Count', table_id = NULL, hl7_reference = 'ORC-3' WHERE issue_id='326';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='information source', issue_type='is administered but appears to historical', field_value='', report_denominator='Vaccination Count', table_id = NULL, hl7_reference = 'RXA-9' WHERE issue_id='327';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='information source', issue_type='is deprecated', field_value='', report_denominator='Vaccination Count', table_id = 30, hl7_reference = 'RXA-9' WHERE issue_id='328';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='information source', issue_type='is historical but appears to be administered', field_value='', report_denominator='Vaccination Count', table_id = NULL, hl7_reference = 'RXA-9' WHERE issue_id='329';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', target_object='Vaccination', target_field='information source', issue_type='is ignored', field_value='', report_denominator='Vaccination Count', table_id = 30, hl7_reference = 'RXA-9' WHERE issue_id='330';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', target_object='Vaccination', target_field='information source', issue_type='is invalid', field_value='', report_denominator='Vaccination Count', table_id = 30, hl7_reference = 'RXA-9' WHERE issue_id='331';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', target_object='Vaccination', target_field='information source', issue_type='is missing', field_value='', report_denominator='Vaccination Count', table_id = 30, hl7_reference = 'RXA-9' WHERE issue_id='332';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', target_object='Vaccination', target_field='information source', issue_type='is unrecognized', field_value='', report_denominator='Vaccination Count', table_id = 30, hl7_reference = 'RXA-9' WHERE issue_id='333';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Vaccination', target_field='information source', issue_type='is valued as', field_value='administered', report_denominator='Vaccination Count', table_id = 30, hl7_reference = 'RXA-9' WHERE issue_id='334';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Vaccination', target_field='information source', issue_type='is valued as', field_value='historical', report_denominator='Vaccination Count', table_id = 30, hl7_reference = 'RXA-9' WHERE issue_id='335';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='lot expiration date', issue_type='is invalid', field_value='', report_denominator='Vaccination Admin Count', table_id = NULL, hl7_reference = 'RXA-16' WHERE issue_id='336';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Vaccination', target_field='lot expiration date', issue_type='is missing', field_value='', report_denominator='Vaccination Admin Count', table_id = NULL, hl7_reference = 'RXA-16' WHERE issue_id='337';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='lot number', issue_type='is invalid', field_value='', report_denominator='Vaccination Admin Count', table_id = NULL, hl7_reference = 'RXA-15' WHERE issue_id='338';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='lot number', issue_type='is missing', field_value='', report_denominator='Vaccination Admin Count', table_id = NULL, hl7_reference = 'RXA-15' WHERE issue_id='339';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='manufacturer code', issue_type='is deprecated', field_value='', report_denominator='Vaccination Admin Count', table_id = 31, hl7_reference = 'RXA-17' WHERE issue_id='340';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', target_object='Vaccination', target_field='manufacturer code', issue_type='is ignored', field_value='', report_denominator='Vaccination Admin Count', table_id = 31, hl7_reference = 'RXA-17' WHERE issue_id='341';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='manufacturer code', issue_type='is invalid', field_value='', report_denominator='Vaccination Admin Count', table_id = 31, hl7_reference = 'RXA-17' WHERE issue_id='342';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='manufacturer code', issue_type='is missing', field_value='', report_denominator='Vaccination Admin Count', table_id = 31, hl7_reference = 'RXA-17' WHERE issue_id='343';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='manufacturer code', issue_type='is unrecognized', field_value='', report_denominator='Vaccination Admin Count', table_id = 31, hl7_reference = 'RXA-17' WHERE issue_id='344';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='order control code', issue_type='is deprecated', field_value='', report_denominator='Vaccination Count', table_id = 34, hl7_reference = 'ORC-1' WHERE issue_id='373';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', target_object='Vaccination', target_field='order control code', issue_type='is ignored', field_value='', report_denominator='Vaccination Count', table_id = 34, hl7_reference = 'ORC-1' WHERE issue_id='369';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='order control code', issue_type='is invalid', field_value='', report_denominator='Vaccination Count', table_id = 34, hl7_reference = 'ORC-1' WHERE issue_id='370';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Vaccination', target_field='order control code', issue_type='is missing', field_value='', report_denominator='Vaccination Count', table_id = 34, hl7_reference = 'ORC-1' WHERE issue_id='371';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='order control code', issue_type='is unrecognized', field_value='', report_denominator='Vaccination Count', table_id = 34, hl7_reference = 'ORC-1' WHERE issue_id='372';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Vaccination', target_field='order facility id', issue_type='is deprecated', field_value='', report_denominator='Vaccination Count', table_id = NULL, hl7_reference = 'ORC-21' WHERE issue_id='442';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', target_object='Vaccination', target_field='order facility id', issue_type='is ignored', field_value='', report_denominator='Vaccination Count', table_id = NULL, hl7_reference = 'ORC-21' WHERE issue_id='443';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='order facility id', issue_type='is invalid', field_value='', report_denominator='Vaccination Count', table_id = NULL, hl7_reference = 'ORC-21' WHERE issue_id='444';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Vaccination', target_field='order facility id', issue_type='is missing', field_value='', report_denominator='Vaccination Count', table_id = NULL, hl7_reference = 'ORC-21' WHERE issue_id='445';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='order facility id', issue_type='is unrecognized', field_value='', report_denominator='Vaccination Count', table_id = NULL, hl7_reference = 'ORC-21' WHERE issue_id='446';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Vaccination', target_field='order facility name', issue_type='is missing', field_value='', report_denominator='Vaccination Count', table_id = NULL, hl7_reference = 'ORC-21' WHERE issue_id='441';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='ordered by', issue_type='is deprecated', field_value='', report_denominator='Vaccination Admin Count', table_id = 23, hl7_reference = 'XCN-12' WHERE issue_id='345';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', target_object='Vaccination', target_field='ordered by', issue_type='is ignored', field_value='', report_denominator='Vaccination Admin Count', table_id = 23, hl7_reference = 'XCN-12' WHERE issue_id='346';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='ordered by', issue_type='is invalid', field_value='', report_denominator='Vaccination Admin Count', table_id = 23, hl7_reference = 'XCN-12' WHERE issue_id='347';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Vaccination', target_field='ordered by', issue_type='is missing', field_value='', report_denominator='Vaccination Admin Count', table_id = 23, hl7_reference = 'XCN-12' WHERE issue_id='348';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='ordered by', issue_type='is unrecognized', field_value='', report_denominator='Vaccination Admin Count', table_id = 23, hl7_reference = 'XCN-12' WHERE issue_id='349';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='placer order number', issue_type='is deprecated', field_value='', report_denominator='Vaccination Count', table_id = NULL, hl7_reference = 'ORC-2' WHERE issue_id='384';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', target_object='Vaccination', target_field='placer order number', issue_type='is ignored', field_value='', report_denominator='Vaccination Count', table_id = NULL, hl7_reference = 'ORC-2' WHERE issue_id='385';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='placer order number', issue_type='is invalid', field_value='', report_denominator='Vaccination Count', table_id = NULL, hl7_reference = 'ORC-2' WHERE issue_id='386';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Vaccination', target_field='placer order number', issue_type='is missing', field_value='', report_denominator='Vaccination Count', table_id = NULL, hl7_reference = 'ORC-2' WHERE issue_id='387';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='placer order number', issue_type='is unrecognized', field_value='', report_denominator='Vaccination Count', table_id = NULL, hl7_reference = 'ORC-2' WHERE issue_id='388';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='product', issue_type='is deprecated', field_value='', report_denominator='Vaccination Admin Count', table_id = 33, hl7_reference = 'RXA-5/RXA-17' WHERE issue_id='350';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', target_object='Vaccination', target_field='product', issue_type='is invalid', field_value='', report_denominator='Vaccination Admin Count', table_id = 33, hl7_reference = 'RXA-5/RXA-17' WHERE issue_id='351';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Vaccination', target_field='product', issue_type='is missing', field_value='', report_denominator='Vaccination Admin Count', table_id = 33, hl7_reference = 'RXA-5/RXA-17' WHERE issue_id='352';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='product', issue_type='is unrecognized', field_value='', report_denominator='Vaccination Admin Count', table_id = 33, hl7_reference = 'RXA-5/RXA-17' WHERE issue_id='353';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='recorded by', issue_type='is deprecated', field_value='', report_denominator='Vaccination Admin Count', table_id = 23, hl7_reference = 'ORC-10' WHERE issue_id='354';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', target_object='Vaccination', target_field='recorded by', issue_type='is ignored', field_value='', report_denominator='Vaccination Admin Count', table_id = 23, hl7_reference = 'ORC-10' WHERE issue_id='355';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='recorded by', issue_type='is invalid', field_value='', report_denominator='Vaccination Admin Count', table_id = 23, hl7_reference = 'ORC-10' WHERE issue_id='356';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Vaccination', target_field='recorded by', issue_type='is missing', field_value='', report_denominator='Vaccination Admin Count', table_id = 23, hl7_reference = 'ORC-10' WHERE issue_id='357';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='recorded by', issue_type='is unrecognized', field_value='', report_denominator='Vaccination Admin Count', table_id = 23, hl7_reference = 'ORC-10' WHERE issue_id='358';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', target_object='Vaccination', target_field='refusal reason', issue_type='conflicts completion status', field_value='', report_denominator='Vaccination Admin Count', table_id = 32, hl7_reference = 'RXA-18' WHERE issue_id='359';
UPDATE dqa_potential_issue SET default_action_code = 'W', change_priority='May', target_object='Vaccination', target_field='refusal reason', issue_type='is deprecated', field_value='', report_denominator='Vaccination Admin Count', table_id = 32, hl7_reference = 'RXA-18' WHERE issue_id='360';
UPDATE dqa_potential_issue SET default_action_code = 'S', change_priority='May', target_object='Vaccination', target_field='refusal reason', issue_type='is ignored', field_value='', report_denominator='Vaccination Admin Count', table_id = 32, hl7_reference = 'RXA-18' WHERE issue_id='361';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', target_object='Vaccination', target_field='refusal reason', issue_type='is invalid', field_value='', report_denominator='Vaccination Admin Count', table_id = 32, hl7_reference = 'RXA-18' WHERE issue_id='362';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Vaccination', target_field='refusal reason', issue_type='is missing', field_value='', report_denominator='Vaccination Admin Count', table_id = 32, hl7_reference = 'RXA-18' WHERE issue_id='363';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Vaccination', target_field='refusal reason', issue_type='is unrecognized', field_value='', report_denominator='Vaccination Admin Count', table_id = 32, hl7_reference = 'RXA-18' WHERE issue_id='364';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', target_object='Vaccination', target_field='system entry time', issue_type='is in future', field_value='', report_denominator='Vaccination Count', table_id = NULL, hl7_reference = 'RXA-22' WHERE issue_id='365';
UPDATE dqa_potential_issue SET default_action_code = 'E', change_priority='May', target_object='Vaccination', target_field='system entry time', issue_type='is invalid', field_value='', report_denominator='Vaccination Count', table_id = NULL, hl7_reference = 'RXA-22' WHERE issue_id='366';
UPDATE dqa_potential_issue SET default_action_code = 'A', change_priority='May', target_object='Vaccination', target_field='system entry time', issue_type='is missing', field_value='', report_denominator='Vaccination Count', table_id = NULL, hl7_reference = 'RXA-22' WHERE issue_id='367';

ALTER TABLE dqa_report_template ADD COLUMN test_case_script VARCHAR;

INSERT INTO dqa_keyed_setting(keyed_code, object_code, object_id, keyed_value) VALUES ('in.file.export.connection_script', 'APPLICATION', 1, 'Y');
INSERT INTO dqa_keyed_setting(keyed_code, object_code, object_id, keyed_value) VALUES ('in.file.export.connection_script', 'APPLICATION', 2, 'Y');
INSERT INTO dqa_keyed_setting(keyed_code, object_code, object_id, keyed_value) VALUES ('in.file.export.connection_script', 'APPLICATION', 3, 'N');
INSERT INTO dqa_keyed_setting(keyed_code, object_code, object_id, keyed_value) VALUES ('in.file.export.connection_script', 'APPLICATION', 4, 'Y');
INSERT INTO dqa_keyed_setting(keyed_code, object_code, object_id, keyed_value) VALUES ('in.file.export.connection_script', 'APPLICATION', 5, 'Y');
INSERT INTO dqa_keyed_setting(keyed_code, object_code, object_id, keyed_value) VALUES ('in.file.export.connection_script', 'APPLICATION', 6, 'N');
INSERT INTO dqa_keyed_setting(keyed_code, object_code, object_id, keyed_value) VALUES ('in.file.export.connection_script', 'APPLICATION', 7, 'Y');
INSERT INTO dqa_keyed_setting(keyed_code, object_code, object_id, keyed_value) VALUES ('in.file.export.connection_script', 'APPLICATION', 8, 'Y');
INSERT INTO dqa_keyed_setting(keyed_code, object_code, object_id, keyed_value) VALUES ('in.file.export.connection_script', 'APPLICATION', 9, 'N');
INSERT INTO dqa_keyed_setting(keyed_code, object_code, object_id, keyed_value) VALUES ('in.file.export.connection_script', 'APPLICATION', 10, 'Y');
INSERT INTO dqa_keyed_setting(keyed_code, object_code, object_id, keyed_value) VALUES ('in.file.export.connection_script', 'APPLICATION', 11, 'Y');
INSERT INTO dqa_keyed_setting(keyed_code, object_code, object_id, keyed_value) VALUES ('in.file.export.connection_script', 'APPLICATION', 12, 'Y');
INSERT INTO dqa_keyed_setting(keyed_code, object_code, object_id, keyed_value) VALUES ('in.file.export.connection_script', 'APPLICATION', 13, 'N');


INSERT INTO dqa_report_type(report_type_id, report_type_label) VALUES (3, 'Vacc Reporting Certification');
INSERT INTO dqa_report_type(report_type_id, report_type_label) VALUES (4, 'IIS Profile ');

INSERT INTO dqa_submitter_profile (profile_id, profile_code, profile_label, profile_status, org_id, data_format, transfer_priority, access_key, template_id) VALUES (257, 'TEMPLATE:IHS Certification', 'IHS Certification', 'Template', 1, 'HL7v2', 'Normal', 'template', 7);

INSERT INTO dqa_report_template(template_id, template_label, report_type_id, report_definition, base_profile_id) VALUES(7, 'IHS Certification', 3, '<dqa-scoring>
  <section name="completeness" weight="50">
    <section name="patient" weight="50">
      <section name="required" weight="14">
        <score label="Patient Id" hl7Reference="PID-3" denominator="patient count" numerator="Patient submitter id" weight="10" />
        <score label="First Name" hl7Reference="PID-5.2" denominator="patient count" numerator="Patient name first" weight="10" />
        <score label="Last Name" hl7Reference="PID-5.1" denominator="patient count" numerator="Patient name last" weight="10" />
        <score label="Birth Date" hl7Reference="PID-7" denominator="patient count" numerator="Patient birth date" weight="10" />
        <score label="Sex" hl7Reference="PID-8" denominator="patient count" numerator="Patient gender" weight="5" />
        <score label="Address" hl7Reference="PID-11" denominator="patient count" numerator="Patient address" weight="2">
          <score label="Street" hl7Reference="PID-1" denominator="patient count" numerator="Patient address street" weight="5" />
          <score label="City" hl7Reference="PID-3" denominator="patient count" numerator="Patient address city" weight="1" />
          <score label="State" hl7Reference="PID-4" denominator="patient count" numerator="Patient address state" weight="1" />
          <score label="Zip" hl7Reference="PID-5" denominator="patient count" numerator="Patient address zip" weight="1" />
        </score>
        <score label="Phone" hl7Reference="PID-13" denominator="patient count" numerator="Patient phone" weight="10" />
        <score label="Responsible Party" hl7Reference="NK1" denominator="patient count" numerator="Patient guardian responsible party" weight="1">
          <score label="First Name" hl7Reference="NK1-2.2" denominator="patient count" numerator="Patient guardian name first" weight="4" />
          <score label="Last Name" hl7Reference="NK1-2.1" denominator="patient count" numerator="Patient guardian name last" weight="4" />
          <score label="Same as Patient" hl7Reference="NK1-3" denominator="patient count" numerator="Patient guardian name is same as underage patient"
            weight="-8" />
          <score label="Relationship" hl7Reference="NK1-3" denominator="patient count" numerator="Patient guardian relationship" weight="1" />
        </score>
      </section>
      <section name="expected" weight="4">
        <score label="Middle Name" hl7Reference="PID-5.3" denominator="patient count" numerator="Patient middle name" weight="10" />
        <score label="Mother''s Maiden" hl7Reference="PID-6" denominator="patient count" numerator="Patient mother''s maiden name" weight="10" />
        <score label="Ethnicity" hl7Reference="PID-22" denominator="patient count" numerator="Patient ethnicity" weight="10" />
        <score label="Race" hl7Reference="PID-10" denominator="patient count" numerator="Patient race" weight="10" />
      </section>
      <section name="recommended" weight="2">
        <score label="Birth Indicator" hl7Reference="PID-24" denominator="patient count" numerator="Patient birth indicator" weight="10" />
      </section>
      <section name="optional" weight="0">
        <score label="Medicaid Id" hl7Reference="PID-3" denominator="patient count" numerator="Patient Medicaid number" weight="0" />
        <score label="SSN" hl7Reference="PID-3" denominator="patient count" numerator="Patient SSN" weight="0" />
        <score label="Alias" hl7Reference="PID-5" denominator="patient count" numerator="Patient alias" weight="0" />
        <score label="Primary Language" hl7Reference="PID-15" denominator="patient count" numerator="Patient primary language" weight="0" />
        <score label="Resp Party Address" hl7Reference="NK1-4" denominator="patient count" numerator="Patient guardian address" weight="0">
          <score label="Street" hl7Reference="NK1-4.1" denominator="patient count" numerator="Patient guardian address street" weight="0" />
          <score label="City" hl7Reference="NK1-4.2" denominator="patient count" numerator="Patient guardian address city" weight="0" />
          <score label="State" hl7Reference="NK1-4.3" denominator="patient count" numerator="Patient guardian address state" weight="0" />
          <score label="Zip" hl7Reference="NK1-4.4" denominator="patient count" numerator="Patient guardian address zip" weight="0" />
        </score>
        <score label="Resp Party Phone" hl7Reference="NK1-5" denominator="patient count" numerator="Patient guardian phone" weight="0" />
        <score label="Address County" hl7Reference="PID-11.6" denominator="patient count" numerator="Patient address county" weight="0" />
        <score label="Financial Class" hl7Reference="PV1-20" denominator="patient count" numerator="Patient VFC status" weight="0" />
        <score label="Registry Status" hl7Reference="PD1-16" denominator="patient count" numerator="Patient registry status" weight="0" />
        <score label="Primary Physician" hl7Reference="PD1-4" denominator="patient count" numerator="Patient primary physician id" weight="0">
          <score label="Name" hl7Reference="" denominator="patient count" numerator="Patient primary physician name" weight="0" />
        </score>
        <score label="Primary Facility" hl7Reference="PD1-3" denominator="patient count" numerator="Patient primary facility id" weight="0">
          <score label="Name" hl7Reference="" denominator="patient count" numerator="Patient primary facility name" weight="0" />
        </score>
        <score label="Patient Registry Id" hl7Reference="PID-3" denominator="patient count" numerator="Patient registry id" weight="0" />
        <score label="Protection Indicator" hl7Reference="PD1-12" denominator="patient count" numerator="Patient protection indicator" weight="0" />
        <score label="Publicity Indicator" hl7Reference="PD1-11" denominator="patient count" numerator="Patient publicity code" weight="0" />
      </section>
    </section>
    <section name="vaccination" weight="50">
      <section name="required" weight="14">
        <score label="Vaccination Date" hl7Reference="RXA-3" denominator="vaccination count" numerator="Vaccination admin date" weight="10" />
        <score label="Vaccination Code" hl7Reference="RXA-5" denominator="vaccination count" numerator="Vaccination admin code" weight="10" />
        <score label="CVX Code" hl7Reference="RXA-5" denominator="vaccination count" numerator="Vaccination CVX code" weight="5" />
        <score label="Information Source" hl7Reference="RXA-9" denominator="vaccination count" numerator="Vaccination information source"
          weight="10" />
        <score label="VFC Status" hl7Reference="OBX-5" denominator="vaccination admin count" numerator="Vaccination financial eligibility code"
          weight="10" />
        <score label="Lot Number" hl7Reference="RXA-15" denominator="vaccination admin count" numerator="Vaccination lot number" weight="10" />
        <score label="Manufacturer" hl7Reference="RXA-17" denominator="vaccination admin count" numerator="Vaccination manufacturer code"
          weight="10" />
        <score label="Vaccination Id" hl7Reference="ORC-3" denominator="vaccination count" numerator="Vaccination id" weight="10" />
      </section>
      <section name="expected" weight="4">
        <score label="Admin Amount" hl7Reference="RXA-6" denominator="vaccination admin count" numerator="Vaccination administered amount"
          weight="10">
          <score label="Missing Units" hl7Reference="RXA-7" denominator="vaccination admin count" numerator="Vaccination administered unit is missing"
            weight="-10" />
        </score>
        <score label="Completion Status" hl7Reference="RXA-20" denominator="vaccination admin count" numerator="Vaccination completion status"
          weight="10" />
        <score label="Action Code" hl7Reference="RXA-21" denominator="vaccination count" numerator="Vaccination action code" weight="0" />
      </section>
      <section name="recommended" weight="2">
        <score label="Facility Id" hl7Reference="RXA-11" denominator="vaccination admin count" numerator="Vaccination facility id" weight="0" />
        <score label="Given By Id" hl7Reference="RXA-10" denominator="vaccination admin count" numerator="Vaccination given by" weight="0" />
        <score label="Ordered By" hl7Reference="ORC-12" denominator="vaccination admin count" numerator="Vaccination ordered by" weight="0" />
        <score label="System Entry Date" hl7Reference="RXA-22" denominator="vaccination count" numerator="Vaccination system entry time" weight="0">
          <score label="In Future" hl7Reference="" denominator="vaccination count" numerator="Vaccination system entry time is in future"
            weight="-5" />
        </score>
      </section>
      <section name="optional" weight="0">
        <score label="Entered By" hl7Reference="ORC-10" denominator="vaccination count" numerator="Vaccination recorded by" weight="0" />
        <score label="Refusal Reason" hl7Reference="RXA-18" denominator="vaccination count" numerator="Vaccination refusal reason" weight="0" />
        <score label="Lot Expiration Date" hl7Reference="RXA-16" denominator="vaccination admin count" numerator="Vaccination lot expiration date"
          weight="0" />
        <score label="CPT Code" hl7Reference="RXA-5" denominator="vaccination count" numerator="Vaccination CPT code" weight="0" />
      </section>
    </section>
    <section name="vaccineGroup" weight="0">
      <section name="expected" weight="2">
        <section name="DTAP" weight="1" />
        <section name="HepB" weight="1" />
        <section name="POLIO" weight="1" />
        <section name="HIB" weight="1" />
        <section name="FLU" weight="1" />
        <section name="MMR" weight="1" />
        <section name="VARICELLA" weight="1" />
        <section name="PneumoPCV" weight="1" />
      </section>
      <section name="recommended" weight="1">
        <section name="HPV" weight="1" />
        <section name="ROTAVIRUS" weight="1" />
        <section name="Td" weight="1" />
        <section name="HepA" weight="1" />
      </section>
      <section name="optional" weight="0">
        <section name="RABIES" weight="0" />
      </section>
      <section name="unexpected" weight="-2">
        <section name="ANTHRAX" weight="1" />
      </section>
    </section>
  </section>
  <section name="quality" weight="40">
    <section name="errors" weight="28">
    </section>
    <section name="warnings" weight="12">
    </section>
  </section>
  <section name="timeliness" weight="0">
    <section name="early" weight="10" days="1">
    </section>
    <section name="onTime" weight="7" days="3">
    </section>
    <section name="late" weight="4" days="7">
    </section>
    <section name="veryLate" weight="1" days="30">
    </section>
    <section name="oldData" weight="0">
    </section>
  </section>
</dqa-scoring>', 257);

INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(2, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(3, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(4, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(5, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(463, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(464, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(452, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(415, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(417, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(416, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(6, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(7, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(8, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(9, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(10, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(431, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(432, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(433, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(434, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(435, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(418, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(420, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(419, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(410, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(411, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(412, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(413, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(414, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(426, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(427, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(428, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(429, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(430, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(421, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(422, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(423, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(424, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(425, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(11, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(12, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(13, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(14, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(15, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(16, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(17, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(436, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(437, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(438, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(439, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(440, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(391, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(392, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(18, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(19, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(20, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(21, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(22, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(403, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(404, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(402, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(23, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(401, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(24, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(25, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(26, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(29, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(30, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(31, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(32, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(33, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(34, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(35, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(36, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(37, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(38, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(39, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(40, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(41, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(42, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(43, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(44, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(368, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(45, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(46, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(47, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(48, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(49, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(50, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(51, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(400, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(390, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(389, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(52, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(53, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(54, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(55, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(56, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(57, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(58, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(59, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(60, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(61, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(62, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(63, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(64, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(65, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(66, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(67, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(68, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(69, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(70, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(71, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(72, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(73, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(74, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(75, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(76, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(395, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(396, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(397, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(398, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(399, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(77, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(78, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(79, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(80, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(81, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(82, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(83, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(84, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(85, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(86, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(87, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(88, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(89, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(90, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(91, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(470, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(471, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(472, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(473, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(474, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(475, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(476, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(477, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(478, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(479, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(480, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(481, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(482, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(92, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(93, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(94, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(95, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(96, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(97, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(98, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(99, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(100, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(101, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(102, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(103, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(104, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(105, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(106, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(107, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(108, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(109, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(110, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(111, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(451, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(112, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(113, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(114, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(115, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(116, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(117, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(118, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(119, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(120, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(121, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(122, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(123, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(124, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(125, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(126, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(127, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(128, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(374, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(375, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(376, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(377, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(378, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(129, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(130, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(131, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(132, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(133, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(134, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(135, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(136, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(137, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(138, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(139, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(143, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(144, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(145, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(146, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(147, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(148, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(149, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(150, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(151, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(152, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(155, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(156, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(153, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(154, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(157, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(158, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(159, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(160, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(161, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(162, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(163, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(164, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(167, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(168, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(169, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(170, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(171, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(172, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(173, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(140, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(141, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(142, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(165, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(166, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(405, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(406, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(407, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(408, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(409, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(174, 257, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(175, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(176, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(453, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(454, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(455, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(456, 257, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(457, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(458, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(459, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(460, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(461, 257, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(462, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(177, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(178, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(179, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(180, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(181, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(182, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(183, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(184, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(185, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(186, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(187, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(188, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(189, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(190, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(191, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(192, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(193, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(194, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(195, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(196, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(197, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(198, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(199, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(200, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(201, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(202, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(203, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(204, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(205, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(206, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(207, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(208, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(209, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(210, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(211, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(212, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(213, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(214, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(215, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(216, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(217, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(218, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(219, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(220, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(393, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(394, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(221, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(222, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(223, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(224, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(225, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(226, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(227, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(228, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(229, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(230, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(231, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(232, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(233, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(234, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(235, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(236, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(237, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(238, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(239, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(240, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(241, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(242, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(243, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(244, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(245, 257, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(246, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(247, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(248, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(249, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(483, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(484, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(250, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(251, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(252, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(253, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(254, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(255, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(256, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(257, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(258, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(259, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(260, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(261, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(262, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(263, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(264, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(265, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(266, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(267, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(268, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(269, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(270, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(271, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(447, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(448, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(449, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(272, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(450, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(273, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(274, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(275, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(276, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(277, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(278, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(279, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(280, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(281, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(282, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(283, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(284, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(285, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(286, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(287, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(288, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(289, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(290, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(291, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(292, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(293, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(294, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(295, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(296, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(297, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(298, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(299, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(300, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(301, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(302, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(303, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(304, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(305, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(306, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(307, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(308, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(309, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(310, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(311, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(312, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(313, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(314, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(315, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(316, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(379, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(380, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(381, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(382, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(383, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(465, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(466, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(467, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(468, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(469, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(317, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(318, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(319, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(320, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(321, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(322, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(323, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(324, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(325, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(326, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(327, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(328, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(329, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(330, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(331, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(332, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(333, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(334, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(335, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(336, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(337, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(338, 257, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(339, 257, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(340, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(341, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(342, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(343, 257, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(344, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(373, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(369, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(370, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(371, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(372, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(442, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(443, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(444, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(445, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(446, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(441, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(345, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(346, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(347, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(348, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(349, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(384, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(385, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(386, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(387, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(388, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(350, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(351, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(352, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(353, 257, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(354, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(355, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(356, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(357, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(358, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(359, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(360, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(361, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(362, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(363, 257, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(364, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(365, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(366, 257, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(367, 257, 'A', 0, 100);


UPDATE dqa_application SET primary_template_id = 7 WHERE application_id = 7;
UPDATE dqa_application SET primary_template_id = 7 WHERE application_id = 8;


INSERT INTO dqa_application (application_id, application_label, application_type, run_this) VALUES (14, 'ASIIS', 'Profile', '8');
UPDATE dqa_application SET primary_template_id = 8 WHERE application_id = 14;
INSERT INTO dqa_report_template(template_id, template_label, report_type_id, report_definition, base_profile_id) VALUES(8, 'ASIIS Profile', 4, '<dqa-scoring>
  <section name="completeness" weight="50">
    <section name="patient" weight="20">
      <section name="required" weight="10">
        <score label="Patient Id" denominator="patient count" numerator="Patient submitter id" weight="10" />
        <score label="First Name" denominator="patient count" numerator="Patient name first" weight="5" />
        <score label="Last Name" denominator="patient count" numerator="Patient name last" weight="5" />
        <score label="Possible Test Name" denominator="patient count" numerator="Patient name may be test name" weight="-5" />
        <score label="Possible Baby Name" denominator="patient count" numerator="Patient name may be temporary newborn name" weight="-10" />
        <score label="Birth Date" denominator="patient count" numerator="Patient birth date" weight="10" />
        <score label="Sex" denominator="patient count" numerator="Patient gender" weight="5" />
        <score label="Address" denominator="patient count" numerator="Patient address" weight="2">
          <score label="Street" denominator="patient count" numerator="Patient address street" weight="5" />
          <score label="City" denominator="patient count" numerator="Patient address city" weight="1" />
          <score label="State" denominator="patient count" numerator="Patient address state" weight="1" />
          <score label="Zip" denominator="patient count" numerator="Patient address zip" weight="1" />
        </score>
      </section>
      <section name="expected" weight="6">
        <score label="Middle Name" denominator="patient count" numerator="Patient middle name" weight="10" />
        <score label="Phone" denominator="patient count" numerator="Patient phone" weight="10" />
        <score label="Mother''s Maiden" denominator="patient count" numerator="Patient mother''s maiden name" weight="10" />
        <score label="SSN" denominator="patient count" numerator="Patient SSN" weight="10" />
        <score label="Responsible Party" denominator="patient count" numerator="Patient guardian responsible party" weight="1">
          <score label="First Name" denominator="patient count" numerator="Patient guardian name first" weight="4" />
          <score label="Last Name" denominator="patient count" numerator="Patient guardian name last" weight="4" />
          <score label="Same as Patient" denominator="patient count" numerator="Patient guardian name is same as underage patient" weight="-8" />
          <score label="Relationship" denominator="patient count" numerator="Patient guardian relationship" weight="1" />
        </score>
      </section>
      <section name="recommended" weight="4">
        <score label="Birth Indicator" denominator="patient count" numerator="Patient birth indicator" weight="10" />
        <score label="Ethnicity" denominator="patient count" numerator="Patient ethnicity" weight="10" />
        <score label="Race" denominator="patient count" numerator="Patient race" weight="10" />
        <score label="Medicaid Id" denominator="patient count" numerator="Patient Medicaid number" weight="5" />
      </section>
      <section name="optional" weight="0">
        <score label="Alias" denominator="patient count" numerator="Patient alias" weight="0" />
        <score label="Primary Language" denominator="patient count" numerator="Patient primary language" weight="0" />
        <score label="Resp Party Address" denominator="patient count" numerator="Patient guardian address is missing" weight="0">
          <score label="Street" denominator="patient count" numerator="Patient guardian address street" weight="0" />
          <score label="City" denominator="patient count" numerator="Patient guardian address city" weight="0" />
          <score label="State" denominator="patient count" numerator="Patient guardian address state" weight="0" />
          <score label="Zip" denominator="patient count" numerator="Patient guardian address zip" weight="0" />
        </score>
        <score label="Resp Party Phone" denominator="patient count" numerator="Patient guardian phone" weight="0" />
        <score label="Address County" denominator="patient count" numerator="Patient address county" weight="0" />
        <score label="Financial Class" denominator="patient count" numerator="Patient VFC status" weight="0" />
        <score label="Registry Status" denominator="patient count" numerator="Patient registry status" weight="0" />
        <score label="Primary Physician" denominator="patient count" numerator="Patient primary physician id" weight="0">
          <score label="Name" denominator="patient count" numerator="Patient primary physician name" weight="0" />
        </score>
        <score label="Primary Facility" denominator="patient count" numerator="Patient primary facility id" weight="0">
          <score label="Name" denominator="patient count" numerator="Patient primary facility name" weight="0" />
        </score>
        <score label="Patient Registry Id" denominator="patient count" numerator="Patient registry id" weight="0" />
        <score label="Protection Indicator" denominator="patient count" numerator="Patient protection indicator" weight="0" />
        <score label="Publicity Indicator" denominator="patient count" numerator="Patient publicity code" weight="0" />
      </section>
    </section>
    <section name="vaccination" weight="20">
      <section name="required" weight="10">
        <score label="Vaccination Date" denominator="vaccination count" numerator="Vaccination admin date" weight="40" />
        <score label="Vaccination Code" denominator="vaccination count" numerator="Vaccination admin code" weight="40">
          <score label="Not Specific" denominator="vaccination admin count" numerator="Vaccination admin code is not specific" weight="-10" />
          <score label="Not Vaccine" denominator="vaccination count" numerator="Vaccination admin code is not vaccine" weight="-40" />
          <score label="Valued as Unknown" denominator="vaccination count" numerator="Vaccination admin code is valued as unknown" weight="-40" />
        </score>
        <score label="Information Source" denominator="vaccination count" numerator="Vaccination information source" weight="40">
          <score label="May be Historical" denominator="vaccination count" numerator="Vaccination information source is administered but appears to historical"
            weight="-10" />
          <score label="May be Administered" denominator="vaccination count" numerator="Vaccination information source is historical but appears to be administered"
            weight="-10" />
        </score>
      </section>
      <section name="expected" weight="6">
        <score label="CVX Code" denominator="vaccination count" numerator="Vaccination CVX code" weight="20" />
        <score label="Lot Number" denominator="vaccination admin count" numerator="Vaccination lot number" weight="20" />
        <score label="Manufacturer" denominator="vaccination admin count" numerator="Vaccination manufacturer code" weight="20" />
        <score label="Admin Amount" denominator="vaccination admin count" numerator="Vaccination administered amount" weight="10">
          <score label="Missing Units" denominator="vaccination admin count" numerator="Vaccination administered unit is missing" weight="-2" />
        </score>
        <score label="Facility Id" denominator="vaccination admin count" numerator="Vaccination facility id" weight="20" />
      </section>
      <section name="recommended" weight="4">
        <score label="Action Code" denominator="vaccination count" numerator="Vaccination action code" weight="10" />
        <score label="Given By Id" denominator="vaccination admin count" numerator="Vaccination given by" weight="10" />
        <score label="Vaccination Id" denominator="vaccination count" numerator="Vaccination id" weight="10" />
        <score label="Completion Status" denominator="vaccination admin count" numerator="Vaccination completion status" weight="5" />
        <score label="System Entry Date" denominator="vaccination count" numerator="Vaccination system entry time" weight="5">
          <score label="In Future" denominator="vaccination count" numerator="Vaccination system entry time is in future" weight="-5" />
        </score>
      </section>
      <section name="optional" weight="0">
        <score label="Ordered By" denominator="vaccination admin count" numerator="Vaccination ordered by" weight="0" />
        <score label="Entered By" denominator="vaccination count" numerator="Vaccination recorded by" weight="0" />
        <score label="Refusal Reason" denominator="vaccination count" numerator="Vaccination refusal reason" weight="0" />
        <score label="Lot Expiration Date" denominator="vaccination admin count" numerator="Vaccination lot expiration date" weight="0" />
        <score label="CPT Code" denominator="vaccination count" numerator="Vaccination CPT code" weight="0" />
      </section>
    </section>
    <section name="vaccineGroup" weight="10">
      <section name="expected" weight="2">
        <section name="DTAP" weight="1" />
        <section name="HepB" weight="1" />
        <section name="POLIO" weight="1" />
        <section name="HIB" weight="1" />
        <section name="HepA" weight="1" />
        <section name="FLU" weight="1" />
        <section name="MMR" weight="1" />
        <section name="VARICELLA" weight="1" />
        <section name="PneumoPCV" weight="1" />
        <section name="Td" weight="1" />
      </section>
      <section name="recommended" weight="1">
      </section>
      <section name="optional" weight="0">
      </section>
      <section name="unexpected" weight="-2">
      </section>
    </section>
  </section>
  <section name="quality" weight="40">
    <section name="errors" weight="28">
    </section>
    <section name="warnings" weight="12">
    </section>
  </section>
  <section name="timeliness" weight="10">
    <section name="early" weight="10" days="2">
    </section>
    <section name="onTime" weight="9" days="7">
    </section>
    <section name="late" weight="7" days="30">
    </section>
    <section name="veryLate" weight="5" days="60">
    </section>
    <section name="oldData" weight="0">
    </section>
  </section>
</dqa-scoring>', 258);
INSERT INTO dqa_submitter_profile (profile_id, profile_code, profile_label, profile_status, org_id, data_format, transfer_priority, access_key, template_id) VALUES (258, 'TEMPLATE:ASIIS Profile', 'ASIIS Profile', 'Template', 1, 'HL7v2', 'Normal', 'template', 8);

INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(2, 258, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(3, 258, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(4, 258, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(5, 258, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(463, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(464, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(452, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(415, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(417, 258, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(416, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(6, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(7, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(8, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(9, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(10, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(431, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(432, 258, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(433, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(434, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(435, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(418, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(420, 258, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(419, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(410, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(411, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(412, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(413, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(414, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(426, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(427, 258, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(428, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(429, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(430, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(421, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(422, 258, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(423, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(424, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(425, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(11, 258, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(12, 258, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(13, 258, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(14, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(15, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(16, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(17, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(436, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(437, 258, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(438, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(439, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(440, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(391, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(392, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(18, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(19, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(20, 258, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(21, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(22, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(403, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(404, 258, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(402, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(23, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(401, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(24, 258, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(25, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(26, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(29, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(30, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(31, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(32, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(33, 258, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(34, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(35, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(36, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(37, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(38, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(39, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(40, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(41, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(42, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(43, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(44, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(368, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(45, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(46, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(47, 258, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(48, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(49, 258, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(50, 258, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(51, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(400, 258, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(390, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(389, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(52, 258, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(53, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(54, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(55, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(56, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(57, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(58, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(59, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(60, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(61, 258, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(62, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(63, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(64, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(65, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(66, 258, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(67, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(68, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(69, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(70, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(71, 258, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(72, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(73, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(74, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(75, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(76, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(395, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(396, 258, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(397, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(398, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(399, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(77, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(78, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(79, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(80, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(81, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(82, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(83, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(84, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(85, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(86, 258, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(87, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(88, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(89, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(90, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(91, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(470, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(471, 258, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(472, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(473, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(474, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(475, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(476, 258, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(477, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(478, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(479, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(480, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(481, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(482, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(92, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(93, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(94, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(95, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(96, 258, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(97, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(98, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(99, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(100, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(101, 258, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(102, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(103, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(104, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(105, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(106, 258, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(107, 258, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(108, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(109, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(110, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(111, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(451, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(112, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(113, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(114, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(115, 258, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(116, 258, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(117, 258, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(118, 258, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(119, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(120, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(121, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(122, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(123, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(124, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(125, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(126, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(127, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(128, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(374, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(375, 258, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(376, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(377, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(378, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(129, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(130, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(131, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(132, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(133, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(134, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(135, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(136, 258, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(137, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(138, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(139, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(143, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(144, 258, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(145, 258, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(146, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(147, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(148, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(149, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(150, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(151, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(152, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(155, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(156, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(153, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(154, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(157, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(158, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(159, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(160, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(161, 258, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(162, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(163, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(164, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(167, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(168, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(169, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(170, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(171, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(172, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(173, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(140, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(141, 258, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(142, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(165, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(166, 258, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(405, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(406, 258, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(407, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(408, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(409, 258, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(174, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(175, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(176, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(453, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(454, 258, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(455, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(456, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(457, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(458, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(459, 258, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(460, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(461, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(462, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(177, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(178, 258, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(179, 258, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(180, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(181, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(182, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(183, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(184, 258, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(185, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(186, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(187, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(188, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(189, 258, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(190, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(191, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(192, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(193, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(194, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(195, 258, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(196, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(197, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(198, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(199, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(200, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(201, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(202, 258, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(203, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(204, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(205, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(206, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(207, 258, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(208, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(209, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(210, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(211, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(212, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(213, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(214, 258, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(215, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(216, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(217, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(218, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(219, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(220, 258, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(393, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(394, 258, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(221, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(222, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(223, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(224, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(225, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(226, 258, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(227, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(228, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(229, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(230, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(231, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(232, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(233, 258, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(234, 258, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(235, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(236, 258, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(237, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(238, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(239, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(240, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(241, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(242, 258, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(243, 258, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(244, 258, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(245, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(246, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(247, 258, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(248, 258, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(249, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(483, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(484, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(250, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(251, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(252, 258, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(253, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(254, 258, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(255, 258, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(256, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(257, 258, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(258, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(259, 258, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(260, 258, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(261, 258, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(262, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(263, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(264, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(265, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(266, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(267, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(268, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(269, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(270, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(271, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(447, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(448, 258, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(449, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(272, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(450, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(273, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(274, 258, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(275, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(276, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(277, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(278, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(279, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(280, 258, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(281, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(282, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(283, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(284, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(285, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(286, 258, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(287, 258, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(288, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(289, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(290, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(291, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(292, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(293, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(294, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(295, 258, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(296, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(297, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(298, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(299, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(300, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(301, 258, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(302, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(303, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(304, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(305, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(306, 258, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(307, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(308, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(309, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(310, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(311, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(312, 258, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(313, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(314, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(315, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(316, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(379, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(380, 258, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(381, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(382, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(383, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(465, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(466, 258, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(467, 258, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(468, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(469, 258, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(317, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(318, 258, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(319, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(320, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(321, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(322, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(323, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(324, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(325, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(326, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(327, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(328, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(329, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(330, 258, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(331, 258, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(332, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(333, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(334, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(335, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(336, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(337, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(338, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(339, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(340, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(341, 258, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(342, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(343, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(344, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(373, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(369, 258, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(370, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(371, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(372, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(442, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(443, 258, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(444, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(445, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(446, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(441, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(345, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(346, 258, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(347, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(348, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(349, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(384, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(385, 258, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(386, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(387, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(388, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(350, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(351, 258, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(352, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(353, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(354, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(355, 258, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(356, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(357, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(358, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(359, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(360, 258, 'W', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(361, 258, 'S', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(362, 258, 'E', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(363, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(364, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(365, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(366, 258, 'A', 0, 100);
INSERT INTO dqa_potential_issue_status (issue_id, profile_id, action_code, expect_min, expect_max) VALUES(367, 258, 'A', 0, 100);
