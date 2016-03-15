package com.springapp.mvc;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by pei.xu on 2015/12/14.
 */
public class TestList {

    @BeforeClass
    public static void beforeClass() {
        System.out.println("start");
    }

    @Test
    public void test() {
        System.out.print("test");
    }
}
