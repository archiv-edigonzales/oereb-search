# oereb-search

Use `sogis:oereb-db-data` for developing:
```
docker run --rm --name oereb-db-data -p 54321:5432 --hostname primary \
-e PG_DATABASE=oereb -e PG_LOCALE=de_CH.UTF-8 -e PG_PRIMARY_PORT=5432 -e PG_MODE=primary \
-e PG_USER=admin -e PG_PASSWORD=admin \
-e PG_PRIMARY_USER=repl -e PG_PRIMARY_PASSWORD=repl \
-e PG_ROOT_PASSWORD=secret \
-e PG_WRITE_USER=gretl -e PG_WRITE_PASSWORD=gretl \
-e PG_READ_USER=ogc_server -e PG_READ_PASSWORD=ogc_server \
-e PGDATA=/tmp/primary \
sogis/oereb-db-data:latest
```

View:
```
CREATE OR REPLACE VIEW live.vw_egrid AS 
SELECT
    t_id AS id,
    egris_egrid AS egrid,
    LOWER(egris_egrid) AS searchtext,
    egris_egrid AS displaytext
FROM
    live.dm01vch24lv95dliegenschaften_grundstueck 
;
```
