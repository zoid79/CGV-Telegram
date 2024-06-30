package com.example.cgv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;


@EnableScheduling
@SpringBootApplication
public class CgvApplication {


	private static final Logger logger = LoggerFactory.getLogger(CgvApplication.class);

	public static void main(String[] args){
	//	SpringApplication.run(CgvApplication.class, args);
	// 	TelebotController telebotController = new TelebotController();
		TheaterService theaterService = new TheaterService();
		theaterService.start();
	}
//
//	public static boolean checkForAvailableSeats() {
//		//test test = new test();
//		//TheaterService theaterService = new TheaterService();
//		return false;
//	}

}
