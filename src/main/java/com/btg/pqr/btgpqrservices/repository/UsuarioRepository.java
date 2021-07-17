package com.btg.pqr.btgpqrservices.repository;



import com.btg.pqr.btgpqrservices.model.Usuario;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String>
{

}
