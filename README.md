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
CREATE OR REPLACE VIEW live.vw_egrid_search AS 
SELECT
    t_id AS id,
    egris_egrid AS egrid,
    egris_egrid AS displaytext, 
    LOWER(egris_egrid) AS searchtext
FROM
    live.dm01vch24lv95dliegenschaften_grundstueck 

UNION ALL 

SELECT
    grundstueck.t_id,
    grundstueck.egris_egrid AS egrid,
    CASE 
        WHEN grundbuchkreis.aname = gemeindegrenze.aname THEN 'GB-Nr. '||grundstueck.nummer||' '||grundbuchkreis.aname
        ELSE 'GB-Nr. '||grundstueck.nummer||' '||grundbuchkreis.aname||' ('||gemeindegrenze.aname||')' 
    END AS displaytext,
    CASE 
        WHEN grundbuchkreis.aname = gemeindegrenze.aname THEN 'gb nr '||LOWER(grundstueck.nummer)||' '||LOWER(grundbuchkreis.aname)
        ELSE 'gb nr '||LOWER(grundstueck.nummer)||' '||LOWER(grundbuchkreis.aname)||' ('||LOWER(gemeindegrenze.aname)||')' 
    END AS searchtext 
FROM
    live.dm01vch24lv95dliegenschaften_grundstueck AS grundstueck
    RIGHT JOIN live.dm01vch24lv95dliegenschaften_liegenschaft AS liegenschaft
    ON liegenschaft.liegenschaft_von = grundstueck.t_id
    LEFT JOIN 
    (
        SELECT
            gemeinde.aname,
            ST_Multi(ST_Union(gemgrenze.geometrie)) AS geometrie
        FROM
            live.dm01vch24lv95dgemeindegrenzen_gemeinde AS gemeinde
            LEFT JOIN live.dm01vch24lv95dgemeindegrenzen_gemeindegrenze AS gemgrenze
            ON gemeinde.t_id = gemgrenze.gemeindegrenze_von
        GROUP BY
            gemeinde.aname
    ) AS gemeindegrenze
    ON ST_Intersects(ST_PointOnSurface(liegenschaft.geometrie), gemeindegrenze.geometrie)
    LEFT JOIN live.so_g_v_0180822grundbuchkreise_grundbuchkreis AS grundbuchkreis
    ON ST_Intersects(ST_PointOnSurface(liegenschaft.geometrie), grundbuchkreis.perimeter)
;

ALTER TABLE live.vw_egrid_search OWNER TO admin;
GRANT ALL ON TABLE live.vw_egrid_search TO admin;
GRANT SELECT ON TABLE live.vw_egrid_search TO public;
GRANT SELECT ON TABLE live.vw_egrid_search TO ogc_server;
```
