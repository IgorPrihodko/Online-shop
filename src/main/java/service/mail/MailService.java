package service.mail;

import model.ConfirmationCode;

public interface MailService {
    void sendConfirmCode (ConfirmationCode code);
}
