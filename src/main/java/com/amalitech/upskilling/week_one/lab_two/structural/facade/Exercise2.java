package com.amalitech.upskilling.week_one.lab_two.structural.facade;

record Email(String to, String subject, String body) {
}

class EmailComposer {
    public Email composeEmail(String to, String subject, String body) {
        return new Email(to, subject, body);
    }
}

class EmailSender {
    public void sendEmail(Email email) {
        System.out.println("Sending email to " + email.to() + " with subject: " + email.subject());
    }
}

class EmailManager {
    public void manageEmail(Email email) {
        System.out.println("Managing email: " + email.body());
    }
}

class EmailFacade {
    private final EmailComposer composer;
    private final EmailSender sender;
    private final EmailManager manager;

    public EmailFacade() {
        this.composer = new EmailComposer();
        this.sender = new EmailSender();
        this.manager = new EmailManager();
    }

    public void sendEmail(String to, String subject, String body) {
        Email email = composer.composeEmail(to, subject, body);
        sender.sendEmail(email);
        manager.manageEmail(email);
    }
}

class FacadePatternEmailExample {
    public static void main(String[] args) {
        EmailFacade emailFacade = new EmailFacade();
        emailFacade.sendEmail("recipient@example.com", "Subject Line", "Email Body Content");
    }
}
