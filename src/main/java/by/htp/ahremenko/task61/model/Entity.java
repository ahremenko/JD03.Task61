package java.by.htp.ahremenko.task61.model;

import java.io.Serializable;

public abstract class Entity implements Serializable, Cloneable {
    private long id;
    public Entity() {
    }

    public Entity(long id) {
        this.id = id;
    }

    public final long getId() {
        return id;
    }

    public final void setId(long id) {
        this.id = id;
    }
}
