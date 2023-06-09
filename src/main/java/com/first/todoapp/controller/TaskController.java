package com.first.todoapp.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.first.todoapp.persistence.entity.Task;
import com.first.todoapp.persistence.entity.TaskStatus;
import com.first.todoapp.service.TaskService;
import com.first.todoapp.service.dto.TaskInDTO;

@RestController
@RequestMapping("/tasks")
public class TaskController {

	private final TaskService taskService;

	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}

	@PostMapping
	public Task crateTask(@RequestBody TaskInDTO taskInDTO) {

		return this.taskService.createTask(taskInDTO);
	}

	@GetMapping
	public List<Task> findAll() {

		return this.taskService.findAll();
	}

	@GetMapping("/status/{status}")
	public List<Task> findAllByStatus(@PathVariable("status") TaskStatus status) {

		return this.taskService.findAllByTaskStatus(status);
	}

	// Se usa patch para hacer una actualizacion puntual
	@PatchMapping("/mark_as_finished/{id}")
	public ResponseEntity<Void> markAsFinished(@PathVariable("id") Long id) {

		this.taskService.updetaTaskAsFinished(id);

		return ResponseEntity.noContent().build(); // nos enviara un error 204 avisando en caso de que no encuentra
													// tarea con ese Id.
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {

		this.taskService.deleteById(id);

		return ResponseEntity.noContent().build(); // nos enviara un error 204 avisando.
	}

	@GetMapping("/find_finished_task")
	public List<Task> findFinishedTask(boolean finished) {

		return this.taskService.findFinishedTask(finished);
	}

}
