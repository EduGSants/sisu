package service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class leitura {
    public static List<Pessoa> inserirPessoas (String[] args) {
        List<Pessoa> candidatos = new ArrayList<>();
        try {
            BufferedReader input = new BufferedReader(new FileReader(args[0]));
            input.readLine();
            for(int i = 1; i < 5610; i++) {
                String line = input.readLine();
                String[] parts = line.split(",", 6);
                Pessoa a = new Pessoa(parts[0],parts[1], parts[2], Double.parseDouble(parts[4]), parts[3], Integer.parseInt(parts[5]));
                candidatos.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return candidatos;
    }
}
