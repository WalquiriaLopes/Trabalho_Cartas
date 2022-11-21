import ij.IJ;
import ij.ImagePlus;
import nu.pattern.OpenCV;

import javax.swing.*;
import java.io.File;

public class Main {

    public static void main(String[] args) {

        String pathCartas = getPublicPathAsString();

        int contadorCartaMagica = 0;
        int contadorCartaArmadilha = 0;
        int contadorCartaMonstroComum = 0;
        int contadorCartaMonstroXYZ = 0;
        int contadorCartaMonstroRitual = 0;

        File diretorioPrincipal = new File(pathCartas);

        if(diretorioPrincipal.isDirectory()){
            File[] diretoriosCartas = diretorioPrincipal.listFiles();

            for (File diretorio: diretoriosCartas) {
                File[] files = diretorio.listFiles();

                for (File file: files) {
                    ImagePlus img = ij.IJ.openImage(file.getAbsolutePath());

                    int aux[] = img.getPixel(16,16);

                    int red, green, blue;

                    red = aux[0];
                    green = aux[1];
                    blue = aux[2];



                    if (red < 40 && green < 141 && blue > 126) {
                        contadorCartaMagica++;
                    } else if (red > 151 && green < 51 && blue < 117) {
                        contadorCartaArmadilha++;
                    } else if (red > 178 && green > 145 && blue < 97) {
                        contadorCartaMonstroComum++;
                    } else if (red < 40 && green < 40 && blue < 40) {
                        contadorCartaMonstroXYZ++;
                    } else if (red > 86 && green < 124 && blue < 176) {
                        contadorCartaMonstroRitual++;
                    }
                }
            }
        }

        String mensagem = "TOTAL CARTAS POR TIPO: \n" +
                "MAGICAS: " + contadorCartaMagica + "\n" +
                "ARMADILHA: " + contadorCartaArmadilha + "\n" +
                "MONSTRO COMUM: " + contadorCartaMonstroComum + "\n" +
                "MONSTRO XYZ: " + contadorCartaMonstroXYZ + "\n" +
                "MONSTRO RITUAL: " + contadorCartaMonstroRitual + "\n";



        JOptionPane.showMessageDialog(null, mensagem);
        System.exit(0);
    }

    public static String getPublicPathAsString() {
        String path = getAbsolutPath() + "Banco_de_Imagens" + File.separator;
        File file = new File(path);
        if (!file.isDirectory()) {
            file.mkdirs();
        }
        return path;
    }

    private static String getAbsolutPath() {
        String path = System.getProperty("user.dir") + File.separator;
        File file = new File(path);
        if (!file.isDirectory()) {
            file.mkdirs();
        }
        return path;
    }

}

