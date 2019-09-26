package ch.so.agi.oereb.searchservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.so.agi.oereb.searchservice.model.Egrid;
import ch.so.agi.oereb.searchservice.repository.EgridRepository;

@Service
public class EgridService {
    @Autowired
    EgridRepository egridrepository;

    Egrid e1 = new Egrid();
    Egrid e2 = new Egrid();
    Egrid e3 = new Egrid();
    Egrid e4 = new Egrid();

    public void addEgrids() {
        e1.setEgrid("CH870679603216");            
        e2.setEgrid("CH857632820629");            
        e3.setEgrid("CH807306583219");            
        e4.setEgrid("CH208606783206");            

        egridrepository.save(e1);
        egridrepository.save(e2);
        egridrepository.save(e3);
        egridrepository.save(e4);

        System.out.println("Egrid have been added : " + egridrepository.findAll());
    }

}
