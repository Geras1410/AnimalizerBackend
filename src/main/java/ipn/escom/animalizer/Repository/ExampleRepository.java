package ipn.escom.animalizer.Repository;

import ipn.escom.animalizer.entities.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ExampleRepository extends JpaRepository<Example, Integer> {
    List<Example> findByUser_Id(int id);
    Example findByIdAndUser_Id(int id, int userId);
}
