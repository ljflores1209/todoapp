package com.first.todoapp.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.first.todoapp.exceptions.ToDoExceptions;
import com.first.todoapp.mapper.TaskInDTOToTask;
import com.first.todoapp.persistence.entity.Task;
import com.first.todoapp.persistence.entity.TaskStatus;
import com.first.todoapp.persistence.repository.TaskRepository;
import com.first.todoapp.service.dto.TaskInDTO;

@Service
public class TaskService {

	private final TaskRepository repository;
	private final TaskInDTOToTask mapper;

	public TaskService(TaskRepository repository, TaskInDTOToTask mapper) {

		this.repository = repository;
		this.mapper = mapper;
	}

	public Task createTask(TaskInDTO taskInDTO) {
		Task task = mapper.map(taskInDTO);

		return this.repository.save(task);
	}

	public List<Task> findAll() {

		return this.repository.findAll();
	}

	public List<Task> findAllByTaskStatus(TaskStatus status) {

		return this.repository.findAllByTaskStatus(status);
	}

	@Transactional
	public void updetaTaskAsFinished(Long id) {
		Task task = this.repository.findTaskById(id);
		LocalDateTime timeNow = LocalDateTime.now();
		Optional<Task> optionalTask = this.repository.findById(id);
		
		if (optionalTask.isEmpty()) {

			throw new ToDoExceptions("Task not found pepe", HttpStatus.NOT_FOUND);

		}
		//definimos el tiempo para que la tarea sea LATE
		if (task.getCreatedDate().getMinute() < timeNow.getMinute()) {
			task.setTaskStatus(TaskStatus.LATE);
		}
		this.repository.markTaskAsFinished(id);
	}

	@Transactional
	public void deleteById(Long id) {
		Optional<Task> optionalTask = this.repository.findById(id);

		if (optionalTask.isEmpty()) {

			throw new ToDoExceptions("Task not found pepito", HttpStatus.NOT_FOUND);

		}
		this.repository.deleteById(id);
	}

	public List<Task> findFinishedTask(boolean finished) {

		return this.repository.findFinishedTask(finished);
	}

}
