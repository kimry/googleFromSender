package com.rykim.telebot;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@RequiredArgsConstructor
@Component
public class TelegramHandler extends TelegramLongPollingBot {

    private final GoogleFormUtil googleFormUtil;

    @Override
    public void onUpdateReceived(Update update) {

        String message;

        message = update.getMessage().getText();

        System.out.println(message);

        SendMessage sendMessage = new SendMessage();

        sendMessage.setChatId("5314302958");
        if(googleFormUtil.submitGoolgeForm(message)){
            sendMessage.setText("전송완료");
        }else{
            sendMessage.setText("전송실패");
        }
        try{
            execute(sendMessage);
        }catch(Exception e)
        {
            System.out.println("텔레그램 전송 실패");
        }
    }

    @Override
    public String getBotToken() {
        return "5749160615:AAFSiWtT5KB_eBIF8MLOlO9HClSJ1GHnbiU";
    }

    @Override
    public String getBotUsername() {
        return "Cptestkimbot";
    }
}
