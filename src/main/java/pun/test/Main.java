package pun.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * DO WHAT YOU WANT POLICY
 */
public class Main {
    private static final Class<?>[] EMPTY_ARRAY = new Class[]{};
    private static final String CLASS_PREFIX = "pun.test.";

    public static void main(String[] args) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line = null;

        try {
            do {
                System.out.print("Algorithm Class: pun.test.");
                line = in.readLine();
                if (line == null) {
                    break;
                }
                runAlgorithmClass(CLASS_PREFIX + line);
            } while (true);
        } catch (IOException e) {
            // e.printStackTrace();
        }
    }

    private static void runAlgorithmClass(String className) {
        try {
            Class c = Class.forName(className);
            Method[] methods = c.getDeclaredMethods();

            List<Method> validMethods = new ArrayList<Method>();
            for (Method m : methods) {
                if (m.isAnnotationPresent(Algorithm.class)) {
                    if (!Modifier.isStatic(m.getModifiers())) {
                        throw new IllegalStateException("Method [" + m + "] must be static! " );
                    }
                    m.setAccessible(true);
                    m.invoke(null);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
