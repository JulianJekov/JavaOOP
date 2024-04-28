package _04_InterfacesAndAbstraction.Exercise.CollectionHierarchy;

public class MyListImpl extends Collection implements MyList{
    @Override
    public String remove() {
        return this.items.remove(0);
    }

    @Override
    public int add(String item) {
        this.items.add(0,item);
        return 0;
    }

    @Override
    public int getUsed() {
        return this.items.size();
    }
}
