package ch.so.agi.oereb.searchservice;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ch.so.agi.oereb.searchservice.service.HibernateSearchService;

@EnableAutoConfiguration 
@Configuration
public class HibernateSearchConfiguration {
//    @Autowired
//    private EntityManager bentityManager;
//
//    @Bean
//    HibernateSearchService hibernateSearchService() {
//        HibernateSearchService hibernateSearchService = new HibernateSearchService(bentityManager);
//        hibernateSearchService.initializeHibernateSearch();
//        return hibernateSearchService;
//    }

}
