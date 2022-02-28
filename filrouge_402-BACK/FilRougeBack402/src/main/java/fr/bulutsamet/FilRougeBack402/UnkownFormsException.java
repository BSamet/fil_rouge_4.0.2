package fr.bulutsamet.FilRougeBack402;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UnkownFormsException extends Exception {
    public UnkownFormsException(String s) {
        super(s);
    }
}
