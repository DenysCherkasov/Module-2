package com.cherkasov.exceptions;

import java.io.IOException;

public class IncorrectLineException extends IOException {
    public IncorrectLineException(String s) {
        super(s);
    }


}
