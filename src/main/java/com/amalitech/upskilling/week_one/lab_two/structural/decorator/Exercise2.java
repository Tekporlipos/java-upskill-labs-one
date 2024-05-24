package com.amalitech.upskilling.week_one.lab_two.structural.decorator;

interface TextMessage {
    String text();
}

record SimpleTextMessage(String text) implements TextMessage {
}

abstract class TextMessageDecorator implements TextMessage {
    protected TextMessage decoratedTextMessage;

    public TextMessageDecorator(TextMessage decoratedTextMessage) {
        this.decoratedTextMessage = decoratedTextMessage;
    }

    @Override
    public String text() {
        return decoratedTextMessage.text();
    }
}

class EncryptedTextMessageDecorator extends TextMessageDecorator {
    public EncryptedTextMessageDecorator(TextMessage decoratedTextMessage) {
        super(decoratedTextMessage);
    }

    @Override
    public String text() {
        return "Encrypted(" + decoratedTextMessage.text() + ")";
    }
}

class CompressedTextMessageDecorator extends TextMessageDecorator {
    public CompressedTextMessageDecorator(TextMessage decoratedTextMessage) {
        super(decoratedTextMessage);
    }

    @Override
    public String text() {
        return "Compressed(" + decoratedTextMessage.text() + ")";
    }
}

class DecoratorPatternTextMessageExample {
    public static void main(String[] args) {
        TextMessage message = new SimpleTextMessage("Hello, World!");
        TextMessage encryptedMessage = new EncryptedTextMessageDecorator(message);
        TextMessage compressedAndEncryptedMessage = new CompressedTextMessageDecorator(encryptedMessage);

        System.out.println("Simple Message: " + message.text());
        System.out.println("Encrypted Message: " + encryptedMessage.text());
        System.out.println("Compressed and Encrypted Message: " + compressedAndEncryptedMessage.text());
    }
}

