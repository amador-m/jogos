package sudoku.modelo;

import java.util.List;
import sudoku.modeloTabuleiro.ModeloTabuleiro;

public class Jogadas {

    private final List<List<Espaco>> espacos;

    public Jogadas(List<List<Espaco>> espacos) {
        this.espacos = espacos;
    }

    public List<List<Espaco>> getEspacos() {
        return espacos;
    }

    public String getStatus() {
        for (List<Espaco> linha : espacos) { // ('linha' representa cada lista dentro da lista principal 'espacos'
            for (Espaco espaco : linha) { // será executado para cada elemento (espaco) da lista - bidimensional
                if (!espaco.isFixo() && espaco.getAtual() != null) {
                    return "INCOMPLETO";
                }
            }
        }

        for (List<Espaco> linha : espacos) { // no sudoku cada linha contém vários espaços
            for (Espaco espaco : linha) { 
                if (espaco.getAtual() == null) {
                    return "INCOMPLETO";
                }
            }
        }
        
        return "COMPLETO";
    }

    public boolean temErros() {
        if (getStatus().equals("NÃO INICIADO")) {
            return false;
        }
        for (List<Espaco> linha : espacos) {
            for (Espaco espaco : linha) {
                if (espaco.getAtual() != null && !espaco.getAtual().equals(espaco.getEsperado())) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean alterarValor(int col, int row, int valor) {
        Espaco espaco = espacos.get(col).get(row);
        if (espaco.isFixo()) {
            return false;
        }
        espaco.setAtual(valor);
        return true;
    }

    public boolean limparValor(int col, int row) {
        Espaco espaco = espacos.get(col).get(row);
        if (espaco.isFixo()) {
            return false;
        }
        espaco.limparEspaco();
        return true;
    }

    public void resetar() {
        for (List<Espaco> linha : espacos) {
            for (Espaco espaco : linha) {
                espaco.limparEspaco();
            }
        }
    }

    public boolean jogoFinalizado() {
        if (temErros()) {
            return false;
        }
        return getStatus().equals("COMPLETO");
    }

    public void mostrar() {
        Object[] valores = new Object[81]; // Array para armazenar os valores do tabuleiro
        int index = 0;
        // percorre cada linha e coluna do tabuleiro
        for (int i = 0; i < espacos.size(); i++) {
            for (int j = 0; j < espacos.get(i).size(); j++) {
                Espaco espaco = espacos.get(i).get(j);
                // se o valor atual for null, exibe um espaço em branco
                valores[index] = (espaco.getAtual() == null) ? " " : espaco.getAtual();
                index++;
            }
        }
        // Imprime o tabuleiro atualizado
        System.out.printf("\n"+ModeloTabuleiro.MODELO_TABULEIRO + "\n", valores);
    }
    
}