package edu.wgu.d387_sample_code.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;


@Service
public class WelcomeMessageService {
    
    private final MessageSource messageSource;

    public WelcomeMessageService(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public void displayWelcomeMessage() {
        Thread englishThread = new Thread(() -> {
            String welcomeMessage = messageSource.getMessage("welcome.message", null, Locale.ENGLISH);
            System.out.println("English Thread: " + welcomeMessage);
        });

        Thread frenchThread = new Thread(() -> {
            String welcomeMessage = messageSource.getMessage("welcome.message", null, Locale.FRENCH);
            System.out.println("French Thread: " + welcomeMessage);
        });

        // Start the threads
        englishThread.start();
        frenchThread.start();
    }

}
