package ch.so.agi.oereb.searchservice.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.so.agi.oereb.searchservice.model.Egrid;

@Service
public class HibernateSearchService {
    @Autowired
    private final EntityManager centityManager;

    @Autowired
    public HibernateSearchService(EntityManager entityManager) {
        super();
        this.centityManager = entityManager;
    }
    
    public void initializeHibernateSearch() {
        System.out.println("init search index");
        try {
            FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(centityManager);
            fullTextEntityManager.createIndexer().startAndWait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public List<Egrid> fuzzySearch(String searchTerm) {   
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(centityManager);
        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Egrid.class).get();
        
        
        org.apache.lucene.search.Query query = queryBuilder
                .keyword()
                .onField("egrid")
                .matching("CH870679603216")
                .createQuery();

        org.hibernate.search.jpa.FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(query, Egrid.class);
//        Query luceneQuery = qb.keyword().fuzzy().withEditDistanceUpTo(1).withPrefixLength(1).onFields("egrid")
//                .matching(searchTerm).createQuery();

//        javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Egrid.class);

        // execute search

        List<Egrid> egridList = null;
        try {
            egridList = jpaQuery.getResultList();
        } catch (NoResultException nre) {
            ;// do nothing

        }

        return egridList;


    }
}
