package br.com.nexushub.domain;

public enum SubjectColor {

    RED("red"),
    BLUE("blue"),
    GREEN("green"),
    YELLOW("yellow"),
    ORANGE("orange"),
    PURPLE("purple"),
    PINK("pink"),
    BROWN("brown"),
    BLACK("black"),
    WHITE("white"),
    GRAY("gray");

    private String color;

    SubjectColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
