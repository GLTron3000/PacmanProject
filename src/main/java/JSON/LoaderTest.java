package JSON;

import entity.Entity;

import java.util.List;


class LoaderTest {

    @org.junit.jupiter.api.Test
    void loadlevel() {
        String path="test1";
        Loader loader = new Loader();
        List<Entity> entities= loader.loadlevel(path);
        System.out.print(entities);
    }
}