package com.btg.pqr.btgpqrservices.repository;



import com.btg.pqr.btgpqrservices.model.Pqr;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrqRepository extends MongoRepository<Pqr, String>
{

}
