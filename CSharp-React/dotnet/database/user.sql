-- ********************************************************************************
-- This script creates the database users and grants them the necessary permissions
-- ********************************************************************************

--CREATE USER weather_owner
--WITH PASSWORD 'finalcapstone';
--
--GRANT ALL
--ON ALL TABLES IN SCHEMA public
--TO weather_owner;
--
--GRANT ALL
--ON ALL SEQUENCES IN SCHEMA public
--TO weather_owner;

CREATE USER weather_appuser
WITH PASSWORD 'alwaysSunny';

GRANT SELECT, INSERT, UPDATE, DELETE
ON ALL TABLES IN SCHEMA public
TO weather_appuser;

GRANT USAGE, SELECT
ON ALL SEQUENCES IN SCHEMA public
TO weather_appuser;
