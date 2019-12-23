package com.walle.reflections;

import static java.lang.System.out;

/**
 * @author: bbpatience
 * @date: 2019/12/20
 * @description: MethodParameterExamples
 **/
public class MethodParameterExamples {

    public class InnerClass {

    }

    enum Colors {
        RED, WHITE;
    }

    public static void main(String... args) {
        out.println("InnerClass:");
        MethodParameterSpy.printClassConstructors(InnerClass.class);

        out.println("enum Colors:");
        MethodParameterSpy.printClassConstructors(Colors.class);
        MethodParameterSpy.printClassMethods(Colors.class);
    }
}
