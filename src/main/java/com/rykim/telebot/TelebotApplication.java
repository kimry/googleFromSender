package com.rykim.telebot;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import javax.annotation.PostConstruct;

@RequiredArgsConstructor
@SpringBootApplication
public class TelebotApplication {

	public static void main(String[] args) {
		SpringApplication.run(TelebotApplication.class, args);
	}

	private final TelegramHandler telegramHandler;

	@PostConstruct
	public void run(){
		try {
			TelegramBotsApi api = new  TelegramBotsApi(DefaultBotSession.class);
			api.registerBot(telegramHandler);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
}
