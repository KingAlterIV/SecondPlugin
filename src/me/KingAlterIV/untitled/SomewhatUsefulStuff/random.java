package me.kingalteriv.untitled.SomewhatUsefulStuff;
import java.util.Random;

public class random {

    public static Double Double(Integer min, Integer max)
    {
        Random r = new Random();
        return Double.valueOf(min.intValue() + (max.intValue() - min.intValue()) * r.nextDouble());
    }

    public static Integer Integer(Integer min, Integer max)
    {
        Random r = new Random();
        return Integer.valueOf(r.nextInt(max.intValue() - min.intValue() + 1) + min.intValue());
    }

}
