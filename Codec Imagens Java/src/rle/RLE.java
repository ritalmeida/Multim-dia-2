/*
* File: RLE/RLE.java
* Created at: 2/jun/2018 - 14:48:34
 */
package rle;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 * @author Miguel Silva
 * @author Rafael Ferreira
 * @author Tiago Sá
 */
public class RLE {

    public static ArrayList<Pixel> pixeis = new ArrayList();
    public static ArrayList<Pixel> contador = new ArrayList();
    public static ArrayList<Pixel> pixeisDiferentes = new ArrayList();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BufferedImage img = null;
        File f = null;

        //read image
        try {
            f = new File("/Users/Ritaa/Downloads/RLE/2018_RLE para imagens/src/rle/", "miniImagem.bmp");
            img = ImageIO.read(f);
        } catch (IOException e) {
            System.out.println(e);
        }
        // Armazena as cores
        int width = img.getWidth();
        int height = img.getHeight();
        int r, g, b, p, pos = -1, pos_menos_utilizada = 0, menos_utilizada = Integer.MAX_VALUE;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                p = img.getRGB(j, i);
                r = (p >> 16) & 0xff;
                g = (p >> 8) & 0xff;
                b = p & 0xff;
                Pixel pixel = new Pixel(r, g, b);

                // Se a cor anterior for igual incrementa
                int aux = pixeis.size();
                aux -= 1;
                if (!pixeis.isEmpty() && pixeis.get(aux).compareTo(pixel) == 0) {
                    pixeis.get(aux).quantidade += 1;
                } else { // se for diferente adiciona e incrementa a posição
                    pixeis.add(pixel);
                    pos++;
                }
            }
        }
        Pixel flag = flag();
        System.out.println("Flag: \n " + flag.r + " " + flag.g + " " + flag.b);

        // Imprime as nossas informações
        System.out.println("Esquema de bits: ");
        for (int i = 0; i < pixeis.size(); i++) {
            System.out.println(" " + pixeis.get(i).toString());
        }

        System.out.println("Imagem Comprimida: ");
        for (int i = 0; i < pixeis.size(); i++) {
            if (pixeis.get(i).quantidade > 3) {
                System.out.println("red");
                convertTobinary(flag.r);
                System.out.println("\ngreen");
                convertTobinary(flag.g);
                System.out.println("\nblue");
                convertTobinary(flag.b);
                System.out.println("\nQuantidade");
                convertTobinary(pixeis.get(i).quantidade);
                System.out.println("\nred");
                convertTobinary(pixeis.get(i).r);
                System.out.println("\ngreen");
                convertTobinary(pixeis.get(i).g);
                System.out.println("\nblue");
                convertTobinary(pixeis.get(i).b);
            } else {
                for (int j = 0; j < pixeis.get(i).quantidade; j++) {
                    System.out.println("\nred");
                    convertTobinary(pixeis.get(i).r);
                    System.out.println("\ngreen");
                    convertTobinary(pixeis.get(i).g);
                    System.out.println("\nblue");
                    convertTobinary(pixeis.get(i).b);
                }
            }
        }
        System.out.println("");

    }

    //Pesquisa de cor não existente
    public static Pixel flag() {
        //adiciona o primeiro pixel para ter algo com que comparar no proximo ciclo
        Pixel primeiro = pixeis.get(0);
        pixeisDiferentes.add(primeiro);

        //popula pixeisDiferentes com um de cada pixel diferente
        boolean existe = false;
        for (Pixel p : pixeis) {
            for (Pixel pd : pixeisDiferentes) {
                if (p.r == pd.r && p.g == pd.g && p.b == pd.b) {
                    existe = true;
                    break;
                }
            }
            if (existe == false) {
                pixeisDiferentes.add(p);
            }
            existe = false;
        }

        //escolha da flag
        Pixel novo = null;
        int flag = 0;

        for (int i = 0; i < 256; i++) {
            for (int j = 0; j < 256; j++) {
                for (int k = 0; k < 256; k++) {
                    novo = new Pixel(i, j, k);
                    for (Pixel t : pixeisDiferentes) {
                        //System.out.println(t.toString());
                        if (novo.compareTo(t) == 0) {
                            flag = 1;
                            break;
                        }
                        flag = 0;
                    }
                    if (flag == 0) {
                        break;
                    }
                }
                if (flag == 0) {
                    break;
                }
            }
            if (flag == 0) {
                break;
            }

        }
        return novo;
    }


    public static void convertTobinary(int n) {

        int[] binaryNum = new int[1000];

        // counter for binary array
        int i = 0;
        while (n > 0)
        {
            // storing remainder in binary array
            binaryNum[i] = n % 2;
            n = n / 2;
            i++;
        }

        // printing binary array in reverse order
        for (int j = i - 1; j >= 0; j--)
            System.out.print(binaryNum[j]);

        /*int binary;
        for (int i = 7, num = number; i >= 0; i--, num >>>= 1) {
            binary = num & 1;
            System.out.print("" + binary);
            if (i == 4) {
                System.out.print(" ");
            }
        }
        System.out.print(" ");*/
    }
}
