package model.material;

public abstract class Material {

    @Override
    public boolean equals(Object obj) {
        if (this.getClass() == obj.getClass())
            return  true;

        return false;
    }
}
