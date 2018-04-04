package org.upstart.r1.objects.items;

import org.upstart.r1.objects.AbstractObject;

import java.io.IOException;

public class InventoryObject extends AbstractObject{
    public InventoryObject(String imgPath, String name) throws IOException {
        super(imgPath);
        this.setName(name);
    }
}
