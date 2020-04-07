package com.aankik.view;

public enum ColorTheme {
    DEFAULT,
    LIGHT,
    DARK;

    public static String getCssPath(ColorTheme colorTheme){
        switch (colorTheme) {
            case LIGHT:
                return "CSS/themeLight.css";
            case DEFAULT:
                return "CSS/themeDefault.css";
            case DARK:
                return "CSS/themeDark.css";
            default:
                return null;
        }
    }

    }