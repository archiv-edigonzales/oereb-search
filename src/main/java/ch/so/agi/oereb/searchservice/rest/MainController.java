package ch.so.agi.oereb.searchservice.rest;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ch.so.agi.oereb.searchservice.model.Egrid;
import ch.so.agi.oereb.searchservice.service.EgridService;
import ch.so.agi.oereb.searchservice.service.HibernateSearchService;

@RestController
public class MainController {
    private Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

    @Autowired
    private HibernateSearchService searchservice;

    @Autowired
    private EgridService egridservice;
    
    @GetMapping(path = "/reindex", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> reindex(){

        searchservice.initializeHibernateSearch();
        
        return ResponseEntity.ok("fubar");
    }


    @GetMapping(path = "/search/{text}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> search(@PathVariable("text") String text){
//      return ResponseEntity.ok(search.search(text));
        egridservice.addEgrids();
        
        //long count = egridservice.fubar();
        //logger.info(String.valueOf(count));

        List<Egrid> searchResults = null;
        try {
            searchResults = searchservice.fuzzySearch(text);
            logger.info(String.valueOf(searchResults.size()));
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
        }
    
        
        
        return ResponseEntity.ok(text);
    }

}
