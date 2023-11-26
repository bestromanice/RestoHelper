package com.example.restohelper;

import static org.junit.Assert.*;

import com.example.restohelper.models.DishModel;

import org.junit.Test;

public class DishModelTest {

    DishModel d = new DishModel("", "testName", 100, "testDescription", "");

    @Test
    public void dishModel_getName() {
        assertEquals("testName", d.getName());
    }

    @Test
    public void dishModel_getPrice() {
        assertEquals(100, d.getPrice());
    }

    @Test
    public void dishModel_getDescription() {
        assertEquals("testDescription", d.getDescription());
    }
}
