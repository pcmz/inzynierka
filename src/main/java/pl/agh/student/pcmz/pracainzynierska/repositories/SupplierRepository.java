package pl.agh.student.pcmz.pracainzynierska.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.agh.student.pcmz.pracainzynierska.models.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
