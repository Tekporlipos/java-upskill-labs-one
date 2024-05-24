package com.amalitech.upskilling.week_one.lab_two.structural.decorator;

interface Shape {
    void draw();
}

class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a Circle");
    }
}

class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a Rectangle");
    }
}

abstract class ShapeDecorator implements Shape {
    protected Shape decoratedShape;

    public ShapeDecorator(Shape decoratedShape) {
        this.decoratedShape = decoratedShape;
    }

    public void draw() {
        decoratedShape.draw();
    }
}

class ColoredShapeDecorator extends ShapeDecorator {
    private final String color;

    public ColoredShapeDecorator(Shape decoratedShape, String color) {
        super(decoratedShape);
        this.color = color;
    }

    @Override
    public void draw() {
        decoratedShape.draw();
        System.out.println("Color: " + color);
    }
}

class TransparentShapeDecorator extends ShapeDecorator {
    private final double transparency;

    public TransparentShapeDecorator(Shape decoratedShape, double transparency) {
        super(decoratedShape);
        this.transparency = transparency;
    }

    @Override
    public void draw() {
        decoratedShape.draw();
        System.out.println("Transparency: " + transparency);
    }
}

class DecoratorPatternShapeExample {
    public static void main(String[] args) {
        Shape circle = new Circle();
        Shape redCircle = new ColoredShapeDecorator(new Circle(), "Red");
        Shape transparentRedCircle = new TransparentShapeDecorator(new ColoredShapeDecorator(new Circle(), "Red"), 0.5);

        System.out.println("Simple Circle:");
        circle.draw();

        System.out.println("\nRed Circle:");
        redCircle.draw();

        System.out.println("\nTransparent Red Circle:");
        transparentRedCircle.draw();
    }
}
