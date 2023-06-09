package com.first.todoapp.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.first.todoapp.persistence.entity.Task;
import com.first.todoapp.persistence.entity.TaskStatus;

public interface TaskRepository extends JpaRepository<Task,Long> {
	
	public List<Task> findAllByTaskStatus (TaskStatus status);
	
	@Modifying //query de actualizacion
	//Mediante query nativa
	@Query(value="UPDATE TASK SET FINISHED=true WHERE ID=:id", nativeQuery=true )
	public void markTaskAsFinished(@Param("id") Long id);

	@Query(value="SELECT * FROM TASK  WHERE FINISHED =:finished", nativeQuery=true )
	public List<Task> findFinishedTask(@Param("finished") Boolean finished);
	
	@Query(value="SELECT * FROM TASK WHERE ID=:id" , nativeQuery=true)
	public Task findTaskById(@Param("id") Long id);
		
		
		
	

}
