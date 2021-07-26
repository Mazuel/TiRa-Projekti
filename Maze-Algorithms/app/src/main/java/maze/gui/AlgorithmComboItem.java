package maze.gui;

public class AlgorithmComboItem {
    AlgorithmOption algorithm;
    String label;
    
    public AlgorithmComboItem(AlgorithmOption algorithm, String label) {
        this.algorithm = algorithm;
        this.label = label;
    }

    public AlgorithmOption getAlgorithm() {
        return algorithm;
    }
    public void setAlgorithm(AlgorithmOption algorithm) {
        this.algorithm = algorithm;
    }
    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
    
}
