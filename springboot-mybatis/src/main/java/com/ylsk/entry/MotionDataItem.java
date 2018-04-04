package com.ylsk.entry;

/**
 * Created by gsj on 2018/3/17.
 */
public class MotionDataItem {
    //    [
    //    {"x":-0.209442138671875,"y":-1.2432861328125,"z":9.585494995117188,"timestamp":1521260728582},
    //    {"x":-0.1753387451171875,"y":-1.22772216796875,"z":9.70880126953125,"timestamp":1521260728587},
    //    {"x":-0.1250457763671875,"y":-1.2486724853515625,"z":9.81414794921875,"timestamp":1521260728592},
    //    {"x":-0.1483917236328125,"y":-1.2175445556640625,"z":9.84527587890625,"timestamp":1521260728602},
    //    {"x":-0.2136383056640625,"y":-1.2498626708984375,"z":9.657928466796875,"timestamp":1521260728606},
    //    {"x":-0.1884918212890625,"y":-1.238494873046875,"z":9.598663330078125,"timestamp":1521260728617},
    //    ]
    private double x;
    private double y;
    private double z;
    private long timestamp;

    private double absXYZ;
    private double absX;
    private double absY;
    private double absZ;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public double getAbsXYZ() {
        return absXYZ;
    }

    public void setAbsXYZ(double absXYZ) {
        this.absXYZ = absXYZ;
    }

    public double getAbsX() {
        return absX;
    }

    public void setAbsX(double absX) {
        this.absX = absX;
    }

    public double getAbsY() {
        return absY;
    }

    public void setAbsY(double absY) {
        this.absY = absY;
    }

    public double getAbsZ() {
        return absZ;
    }

    public void setAbsZ(double absZ) {
        this.absZ = absZ;
    }

    @Override
    public String toString() {
        return this.getX() + "," + this.getY() + "," + this.getZ() + " - " + this.getTimestamp() + " - " + this.getAbsX() + "," + this.getAbsY() + "," + this.getAbsZ() + ", |xyz| = " +this.getAbsXYZ();
    }
    public void cacuABS()
    {
        this.absX = Math.abs(x);
        this.absY = Math.abs(y);
        this.absZ = Math.abs(z);
        this.absXYZ = Math.sqrt(x*x+y*y+z*z);
    }


}
