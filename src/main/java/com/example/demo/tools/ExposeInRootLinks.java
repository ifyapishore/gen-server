package com.example.demo.tools;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExposeInRootLinks {
    String rel();
}
