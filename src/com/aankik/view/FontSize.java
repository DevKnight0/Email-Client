package com.aankik.view;

import javafx.scene.text.Font;

public enum FontSize {
    SMALL,
    MEDIUM,
    LARGE;

    public static String getCssPath(FontSize fontSize){
        switch (fontSize) {
            case MEDIUM:
                return "CSS/fontMedium.css";
            case LARGE:
                return "CSS/fontBig.css";
            case SMALL:
                return "CSS/fontSmall.css";
            default:
                return null;
        }
    }
    }