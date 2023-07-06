# Java-backend-example
create export .csv File from a database.
A Postgre database with more than 30000 positions is processed by a batch standalone application
(in package 'schnittstellen.jobs' the file 'TarifExportJob').
For each record with the unique key 'posnr' the requested data is collected and formatted
to be written into a .csv file. Each DB request caches max. 2000 records, a counter is being
hard-coded. Some logical conditions required due to the task description are checked. 
After processing, a Logfile is produced containing information how many consistant records were exported.
The coding style and use of classes is determined by guidelines to a large extent.
