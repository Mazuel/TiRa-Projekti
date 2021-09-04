package maze.gui;

public class AlgorithmComboItem {
    private AlgorithmOption algorithm;
    private String label;

    public AlgorithmComboItem(AlgorithmOption algorithm, String label) {
        this.algorithm = algorithm;
        this.label = label;
    }

    /**
     * @return AlgorithmOption
     */
    public AlgorithmOption getAlgorithm() {
        return algorithm;
    }

    /**
     * @param algorithm
     */
    public void setAlgorithm(AlgorithmOption algorithm) {
        this.algorithm = algorithm;
    }

    /**
     * @return String
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return label;
    }

}
