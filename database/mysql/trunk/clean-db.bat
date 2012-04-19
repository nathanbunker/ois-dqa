@echo off

echo Will connect to database and drop DQA table and start again, press any key to continue
pause 

mysql -u%1 -p%2 < create-database-only.sql
mysql -u%1 -p%2 < initial_v1.sql
mysql -u%1 -p%2 < upgrade-1.01.sql
