package ch.so.agi.oereb.searchservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.*;

@Indexed
@Entity
public class Egrid {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @Field(index=Index.YES, analyze=Analyze.YES, store=Store.YES)
    private String egrid;

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
}
