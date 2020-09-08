package pl.agh.student.pcmz.pracainzynierska.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.agh.student.pcmz.pracainzynierska.models.DeliveryAddress;

@Repository
public interface DeliveryAddressRepository extends JpaRepository<DeliveryAddress, Long> {

//    DeliveryAddress findTopById();

}
