package com.sistema.controle;

import com.sistema.bean.DataHora;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

public class LogErro {

    DataHora dH = new DataHora();

    public LogErro(String textoArquivo) {
        FileWriter fileWriter = null;

        BufferedWriter bufferedWriter = null;
        try {
            fileWriter = new FileWriter("LogErro.txt", true);
            bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter pw = new PrintWriter(bufferedWriter);
            pw.println(textoArquivo + " " + dH.getDataFormatada() + " - " + dH.getHoraFormatada());
            bufferedWriter.flush();
            bufferedWriter.close();
            pw.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro : " + e, "Erro ", JOptionPane.ERROR_MESSAGE);
        }
    }
}
