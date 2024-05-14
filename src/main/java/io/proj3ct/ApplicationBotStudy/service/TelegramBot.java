package io.proj3ct.ApplicationBotStudy.service;

import io.proj3ct.ApplicationBotStudy.config.BotConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class TelegramBot extends TelegramLongPollingBot {

    private final BotConfig config;

    @Autowired
    private ZayavkaService zayavkaService;

    public TelegramBot(BotConfig config) {
        this.config = config;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            switch (messageText.split(" ")[0]) {
                case "/start":
                    sendStartMessage(chatId);
                    break;
                case "/create_request":
                    createRequest(chatId);
                    break;
                case "/assign_executor":
                    assignExecutor(chatId);
                    break;
                case "На подключение":
                case "На починку":
                    handleRequestType(chatId, messageText);
                    break;
                default:
                    sendUnknownCommandMessage(chatId);
                    break;
            }
        }
    }

    private void sendStartMessage(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText("Выберите действие:");

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        message.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add("/create_request");
        keyboardFirstRow.add("/assign_executor");
        keyboard.add(keyboardFirstRow);

        replyKeyboardMarkup.setKeyboard(keyboard);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error("Error sending message: ", e);
        }
    }

    private void createRequest(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText("Выберите тип заявки:");

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        message.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add("На подключение");
        keyboardFirstRow.add("На починку");
        keyboard.add(keyboardFirstRow);

        replyKeyboardMarkup.setKeyboard(keyboard);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error("Error sending message: ", e);
        }
    }

    private void assignExecutor(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText("Введите ID заявки и ID исполнителя:");
        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error("Error sending message: ", e);
        }
    }

    private void handleRequestType(long chatId, String requestType) {
        // Обработка выбранного типа заявки
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText("Вы выбрали тип заявки: " + requestType);
        try {
            execute(message);
            // Пример данных для заявки
            String tariffName = "Пример тарифа";
            String clientName = "Пример клиента";
            Date date = new Date();
            String personalName = "Пример исполнителя";

            // Сохраняем заявку в базу данных
            zayavkaService.saveZayavka(tariffName, clientName, date, personalName);
        } catch (TelegramApiException e) {
            log.error("Error sending message: ", e);
        }
    }

    private void sendUnknownCommandMessage(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText("Неизвестная команда. Пожалуйста, используйте /create_request или /assign_executor.");
        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error("Error sending message: ", e);
        }
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }
}
