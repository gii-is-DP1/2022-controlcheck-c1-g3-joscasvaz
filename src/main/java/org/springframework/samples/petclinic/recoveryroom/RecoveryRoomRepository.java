package org.springframework.samples.petclinic.recoveryroom;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RecoveryRoomRepository
extends CrudRepository<RecoveryRoom, Integer>{
	
    List<RecoveryRoom> findAll();
    Optional<RecoveryRoom> findById(int id);
    RecoveryRoom save(RecoveryRoom p);
    
    @Query(value = "SELECT rrt FROM RecoveryRoomType rrt")
    List<RecoveryRoomType> findAllRecoveryRoomTypes();
    
    @Query(value = "SELECT rrt FROM RecoveryRoomType rrt WHERE rrt.name LIKE:name")
    RecoveryRoomType getRecoveryRoomType(@Param("name") String name);
    
}
