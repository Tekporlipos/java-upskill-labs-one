package com.amalitech.upskilling.week_one.lab_three.custom_class_loader;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CustomClassLoader extends ClassLoader {

    private final String directory;
    public CustomClassLoader(String directory, ClassLoader parent) {
        super(parent);
        this.directory = directory;
    }

    /**
     * Finds and loads a class with the specified name.
     *
     * @param className The fully qualified name of the desired class.
     * @return The resulting Class object.
     * @throws ClassNotFoundException If the class could not be found.
     */
    @Override
    protected Class<?> findClass(String className) throws ClassNotFoundException {
        try {
            byte[] classBytes = loadClassBytes(className);
            return defineClass(className, classBytes, 0, classBytes.length);
        } catch (IOException e) {
            throw new ClassNotFoundException("Failed to load class: " + className, e);
        }
    }

    /**
     * Loads the class file as a byte array.
     *
     * @param className The fully qualified name of the desired class.
     * @return The byte array representing the class file.
     * @throws IOException If an I/O error occurs.
     */
    private byte[] loadClassBytes(String className) throws IOException {
        String fileName = className.replace('.', File.separatorChar) + ".class";
        Path classPath = Paths.get(directory, fileName);
        return Files.readAllBytes(classPath);
    }
}

class Main {
    public static void main(String[] args) {
        String customDirectory = "src/main/java/com/amalitech/upskilling/week_one/lab_three/custom_class_loader";
        String className = "com.amalitech.upskilling.week_one.lab_three.custom_class_loader.Test";

        try {
            CustomClassLoader classLoader = new CustomClassLoader(customDirectory, Main.class.getClassLoader());
            Class<?> loadedClass = classLoader.loadClass(className);

            Object instance = loadedClass.getDeclaredConstructor().newInstance();
            Method method = loadedClass.getDeclaredMethod("printit");

            Object result = method.invoke(instance);

            System.out.println("Loaded class: " + loadedClass.getName());
            System.out.println("Loaded method: " + method.getName());
            System.out.println("Method result: " + result);

        } catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + e.getMessage());
        } catch (ReflectiveOperationException e) {
            System.out.println("Reflection error: " + e.getMessage());
        }
    }
}
