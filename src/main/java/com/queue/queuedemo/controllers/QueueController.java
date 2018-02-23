package com.queue.queuedemo.controllers;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.queue.queuedemo.domain.Message;




@RestController

public class QueueController {
	 @Autowired
		private RabbitTemplate rabbitTemplate;
	 
	  @RequestMapping(value = "/")
	    public String home(Model model) {
	        model.addAttribute(new Message());
	        //return "WEB-INF/jsp/home.jsp";
	        return "home";
	    }
	  // @Scheduled(fixedDelay = 1000, initialDelay = 500)
	    @RequestMapping(value = "/msgsend", method=RequestMethod.POST)
	    public String publish(@RequestBody Message message) {
	        // Send a message to the "messages" queue
	    	rabbitTemplate.convertAndSend("test", message.getValue());
	       // model.addAttribute("published", true);
	        return "send successful";
	    }

	    @RequestMapping(value = "/msgreceive", method=RequestMethod.GET)
	    public String get() {
	        // Receive a message from the "messages" queue
	        String message = (String)rabbitTemplate.receiveAndConvert("test");
	        if (message != null)
	        	return message;
	        else
	           return "not received";
	    }
	    
	    

	/*	@Scheduled(fixedDelay = 1000L)
		public void send() {
			this.rabbitTemplate.convertAndSend("foo", "hello");
	}*/
}
