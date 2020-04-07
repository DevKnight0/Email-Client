package com.aankik.controller;

import com.aankik.EmailManager;
import com.aankik.view.ViewFactory;

public class BaseController {
    protected EmailManager emailManager;
    protected ViewFactory viewFactory;
    private String fxmlName;

    public BaseController(EmailManager emailManager, ViewFactory viewFactory, String fxmlName) {
        this.emailManager = emailManager;
        this.viewFactory = viewFactory;
        this.fxmlName = fxmlName;
    }
    public String getFxmlName() {
        return fxmlName;
    }

}
