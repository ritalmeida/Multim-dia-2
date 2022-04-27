/*
* File: RLE/pixel.java
* Created at: 2/jun/2018 - 15:14:58
 */
package rle;

/**
 * @author Miguel Silva
 * @author Rafael Ferreira
 * @author Tiago Sá
 */
public class Pixel {

    int r;
    int g;
    int b;
    int quantidade = 1;

    public Pixel(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public Pixel(int r, int g, int b, int quantidade) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.quantidade = quantidade;
    }

    Pixel() {
    }

    public int compareTo(Pixel p) {
        if (this.r == p.r && this.g == p.g && this.b == p.b) {
            return 0;
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return "{" + "r=" + r + ", g=" + g + ", b=" + b + ", quantidade=" + quantidade + '}';
    }
}