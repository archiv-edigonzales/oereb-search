package ch.so.agi.oereb.searchservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.*;

@Indexed
@Entity
@Table(name="vw_egrid")
public class Egrid {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    //@Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES)
    private String egrid;
    
    @Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES)
    private String searchtext;
    
    private String displaytext;
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEgrid() {
        return egrid;
    }

    public void setEgrid(String egrid) {
        this.egrid = egrid;
    }

    public String getSearchtext() {
        return searchtext;
    }

    public void setSearchtext(String searchtext) {
        this.searchtext = searchtext;
    }

    public String getDisplaytext() {
        return displaytext;
    }

    public void setDisplaytext(String displaytext) {
        this.displaytext = displaytext;
    }
}


/*
 * id
 * searchText, e.g. rötistrasse 4 4500 solothurn / gb-nr 404 messen balm bei messen baurecht / CH0389898348
 * displayText e.g. Rötistrasse 4, 4500 Solothurn
 * egrid
 */
