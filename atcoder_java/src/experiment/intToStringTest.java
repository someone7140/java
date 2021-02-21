package experiment;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class intToStringTest {

    public static void main(String[] args){
        // Integer型のmapはOK
        Integer[] intArray = {1, 2, 3, 4};
        List<String> stringList = Arrays.stream(intArray).map(i -> i.toString()).collect(Collectors.toList());
        // int型のmapはNG
        //int[] intArray2 = {1, 2, 3, 4};
        //List<String> stringList2 = Arrays.stream(intArray2).map(i -> String.valueOf(i)).collect(Collectors.toList());
        // int型でmapToObjを使えばOK
        int[] intArray3 = {1, 2, 3, 4};
        List<String> stringList3 = Arrays.stream(intArray3).mapToObj(i -> ((Integer)i).toString()).collect(Collectors.toList());
    }

}
