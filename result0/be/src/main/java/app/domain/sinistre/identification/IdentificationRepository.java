package app.domain.sinistre.identification;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IdentificationRepository extends JpaRepository<Identification, Long> {

            @Query(value = "select x from Identification x where ( :#{#query.libelleStatut} is null OR lower(x.libelleStatut) like lower(CONCAT('%',:#{#query.libelleStatut},'%')))")  
            Page<Identification> chercher(@Param("query") Identification identification, Pageable pageable);
}