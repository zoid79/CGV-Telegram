package com.example.cgv;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.regex.Pattern;

@Service
public class TelebotService extends TelegramLongPollingBot {
    private static final String botToken = "6321012682:AAHij1TMvCmPiJpryXS1krbI3lHRaUDj0BI";
    private final String userToken = "6780066945";
    private final String pattern = "^(\\d{4})(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])$";
    private static final String errorMessage = "입력값에 오류가 있습니다. 실행가능한 명령어 start, end, 날짜입력 방식 = YYYYMMDD";
    TheaterService theaterService;

    public TelebotService() {
        this.theaterService = new TheaterService();
    }

    @Override
    public void onUpdateReceived(Update update) {
        SendMessage message = new SendMessage();
        String text = "";
        message.enableHtml(true);
        System.out.println(update.getMessage().getText());
        System.out.println(Pattern.matches(pattern,update.getMessage().getText()));
        if(Pattern.matches(pattern,update.getMessage().getText())){
            text = this.theaterService.changeDate(update.getMessage().getText());
        }else if(update.getMessage().getText().equals("start")){
            this.theaterService = new TheaterService();
            this.theaterService.start();
            text = "알림이를 시작합니다 일자 = "+this.theaterService.getDatePath();
        }else if(update.getMessage().getText().equals("end")){
            this.theaterService.end();
            text = "알림이를 종료합니다";
        }else{
            text = errorMessage;
        }
        message.setText(text);
        message.setChatId(update.getMessage().getChatId());
        try {
            execute(message);
        }catch (TelegramApiException e){
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return userToken;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}
