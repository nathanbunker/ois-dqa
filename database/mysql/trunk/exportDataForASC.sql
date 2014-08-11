SELECT 'Patient',     mr.received_id, DATE_FORMAT(p.birth_date, '%Y%m%d') , 
                      DATE_FORMAT(mr.received_date, '%Y%m%d'), p.ethnicity_code, p.sex_code, 
       '*Vaccination', DATE_FORMAT(v.admin_date, '%Y%m%d'), v.admin_code_cvx, v.manufacturer_code, 
                      v.body_route_code, v.body_site_code, v.information_source_code, v.facility_id
FROM dqa_message_received mr, dqa_patient p, dqa_vaccination v
WHERE mr.profile_id = 1201 
  AND mr.received_id = p.received_id
  AND mr.received_id = v.received_id
LIMIT 10
INTO OUTFILE 'C:\\data\\Dropbox\\work\\product\\dqa\\in\\MCIR Standard\\submit\\test-import01.txt'
FIELDS TERMINATED BY '|';
