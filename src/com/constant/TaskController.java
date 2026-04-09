package com.constant;

import com.jdk8.Task;

@Restcontroller

public class TaskController {
	
	@Autowired
	TaskService taskservice;
	
	@PostMapping
	public  void saveTask(Task task) {
		
		taskservice.save(task);
	}

}
