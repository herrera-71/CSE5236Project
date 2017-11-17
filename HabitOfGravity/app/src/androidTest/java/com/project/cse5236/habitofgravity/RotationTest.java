package com.project.cse5236.habitofgravity;

import android.app.Activity;
import android.graphics.Rect;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Brent on 11/17/2017.
 */

public class RotationTest {
    @Test
    public void leftRotation() throws Exception {

        blockObject b = new blockObject(0, 500, 151, 151);
        b.RotateLeft(new Rect(500, 500, 151, 151));
        b.RotateLeft(new Rect(500, 500, 151, 151));
        b.RotateLeft(new Rect(500, 500, 151, 151));
        b.RotateLeft(new Rect(500, 500, 151, 151));
        Rect blockRect = b.getRectangle();
        assertEquals(0, blockRect.left);
        assertEquals(500, blockRect.top);
        assertEquals(0 + 151, blockRect.right);
        assertEquals(500 + 151, blockRect.bottom);
    }

    @Test
    public void rightRotation() throws Exception {
        blockObject b = new blockObject(0, 500, 151, 151);
        b.RotateRight(new Rect(500, 500, 151, 151));
        b.RotateRight(new Rect(500, 500, 151, 151));
        b.RotateRight(new Rect(500, 500, 151, 151));
        b.RotateRight(new Rect(500, 500, 151, 151));
        Rect blockRect = b.getRectangle();
        assertEquals(0, blockRect.left);
        assertEquals(500, blockRect.top);
        assertEquals(0 + 151, blockRect.right);
        assertEquals(500 + 151, blockRect.bottom);
    }

    @Test
    public void rotateRightLeft() throws Exception {
        blockObject b = new blockObject(0, 500, 151, 151);
        b.RotateRight(new Rect(500, 500, 151, 151));
        b.RotateLeft(new Rect(500, 500, 151, 151));
        Rect blockRect = b.getRectangle();
        assertEquals(0, blockRect.left);
        assertEquals(500, blockRect.top);
        assertEquals(0 + 151, blockRect.right);
        assertEquals(500 + 151, blockRect.bottom);
    }

    @Test
    public void rotateRightOnce() throws Exception
    {
        blockObject b = new blockObject(0, 0, 1000, 150);
        b.RotateRight(new Rect(500, 500, 151, 151));
        Rect blockRect = b.getRectangle();
        assertEquals(-350, blockRect.left);
        assertEquals(0, blockRect.top);
        assertEquals(650, blockRect.right);
        assertEquals(150, blockRect.bottom);
    }

    @Test
    public void rotateLeftOnce() throws Exception
    {
        blockObject b = new blockObject(-350, 0, 150, 1000);
        b.RotateLeft(new Rect(500, 500, 151, 151));
        Rect blockRect = b.getRectangle();
        assertEquals(0, blockRect.left);
        assertEquals(0, blockRect.top);
        assertEquals(150, blockRect.right);
        assertEquals(1000, blockRect.bottom);
    }
}
