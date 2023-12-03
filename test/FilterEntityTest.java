import entity.CommonFilter;

import java.util.ArrayList;

public class FilterEntityTest {

    @org.junit.Test
    public void testName() {
        CommonFilter filterTest = new CommonFilter("a");
        assert filterTest.getName().equals("a");
    }

    @org.junit.Test
    public void testSetSubFilter() {
        CommonFilter filterTest = new CommonFilter("a");
        CommonFilter filterTest2 = new CommonFilter("b");
        filterTest.setSubFilter(filterTest2);
        assert filterTest.getSubFilterNames().contains("b");
    }

    @org.junit.Test
    public void testSubFilterNames() {
        CommonFilter filterTest = new CommonFilter("a");
        CommonFilter filterTest2 = new CommonFilter("b");
        CommonFilter filterTest3 = new CommonFilter("c");
        filterTest.setSubFilter(filterTest2);
        filterTest.setSubFilter(filterTest3);
        ArrayList<String> filterList = new ArrayList<>();
        filterList.add("b");
        filterList.add("c");
        assert filterTest.getSubFilterNames().equals(filterList);
    }

    @org.junit.Test
    public void testGetSubFilter() {
        CommonFilter filterTest = new CommonFilter("a");
        CommonFilter filterTest2 = new CommonFilter("b");
        filterTest.setSubFilter(filterTest2);
        assert filterTest.getSubFilter("b").equals(filterTest2);
    }
}
