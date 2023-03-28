package com.example.project2.jobs;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.project2.entity.User;
import com.example.project2.repo.UserRepo;
import com.example.project2.service.EmailService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class BirthDateJob {
     @Autowired
     UserRepo userRepo;
     @Autowired
     EmailService emailService;
     
     //tim thang user co ngay sinh hom nay va gui mail chuc mung
     
     @Scheduled(cron = "0 * * * * *")
     public void sendEmailBirthdate() {
    	 System.out.println("Init birthdate job");
    	 //1.tim user co ngay sinh hom nay 
    	 Calendar cal = Calendar.getInstance();
    	 
//    	 cal.set(Calendar.HOUR, 0);
//    	 cal.set(Calendar.MINUTE, 0);
//    	 cal.set(Calendar.SECOND, 0);
//    	 cal.set(Calendar.MILLISECOND, 0);
//       +- ngay, gio
//    	 cal.add(0, 0);
    	 
//    	 Date now = cal.getTime();
//    	 log.info("" + now);
    	 
    	 List<User> users = userRepo.findByBirthdate(cal.get(Calendar.MONTH)+1,cal.get(Calendar.DATE));
    	 
    	 for(User user : users) {
    		 log.info(user.getName());
//    		 emailService.sendBirthdate("lehoanglongncth@gmail.com", user.getName());
    	 }
    	 
    	 //emailService.sendTest();
    	
    	 	 
     }
}
