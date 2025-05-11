package sudoku.modelo;

public class Espaco {
    private Integer atual; // integer aceita valores nulos, int não
    private final int esperado; 
    private final boolean fixo;

    public Espaco(final int esperado, final boolean fixo) {
        this.esperado = esperado;
        this.fixo = fixo;
        if (fixo) {
            atual = esperado;
        }
    }

    public Integer getAtual() { 
        return atual; 
    }

    public void setAtual(final Integer atual) { 
        if (fixo) {
            return;
        }
        this.atual = atual;
    }

    public void limparEspaco() { 
        setAtual(null); // porque o integer permite
    }

    public int getEsperado() { 
        return esperado; 
    }

    public boolean isFixo() { 
        return fixo; 
    }
    
}