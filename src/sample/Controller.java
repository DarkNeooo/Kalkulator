package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;

public class Controller {

    @FXML
    public TextField numberA;
    @FXML
    public Label operationLabel;
    @FXML
    public Label resultLabel;
    @FXML
    public TextField numberB;
    @FXML
    public ComboBox<NumeralSystem> numeralSystemChoice;
    @FXML
    public ComboBox<Operation> operationChoice;

    private String systemPattern, operationSymbol;
    private int systemBase;

    public void initialize(){


        ObservableList<NumeralSystem> systems =  FXCollections.observableArrayList(
                new NumeralSystem("dwójkowy","^0|[-]?[1]+[0-1]*$", 2),
                new NumeralSystem("trójkowy", "^0|[-]?[1-2]+[0-2]*$", 3),
                new NumeralSystem("czwórkowy", "^0|[-]?[1-3]+[0-3]*$", 4),
                new NumeralSystem("piątkowy", "^0|[-]?[1-4]+[0-4]*$", 5),
                new NumeralSystem("szóstkowy", "^0|[-]?[1-5]+[0-5]*$", 6),
                new NumeralSystem("siódemkowy", "^0|[-]?[1-6]+[0-6]*$", 7),
                new NumeralSystem("ósemkowy", "^0|[-]?[1-7]+[0-7]*$", 8),
                new NumeralSystem("dziewiątkowy", "^0|[-]?[1-8]+[0-8]*$", 9),
                new NumeralSystem("dziesiętny", "^0|[-]?[1-9]+[0-9]*$", 10)
        );

        numeralSystemChoice.setItems(systems);
        numeralSystemChoice.setConverter(new StringConverter<NumeralSystem>() {
            @Override
            public String toString(NumeralSystem object) {
                return object.getName();
            }

            @Override
            public NumeralSystem fromString(String string) {
                return null;
            }
        });

        ObservableList<Operation> operations = FXCollections.observableArrayList(
                new Operation("dodawanie", "+"),
                new Operation("odejmowanie", "-"),
                new Operation("mnożenie", "*"),
                new Operation("dzielenie", "/")
        );

        operationChoice.setItems(operations);
        operationChoice.setConverter(new StringConverter<Operation>() {
            @Override
            public String toString(Operation object) {
                return object.getName();
            }

            @Override
            public Operation fromString(String string) {
                return null;
            }
        });

        numeralSystemChoice.valueProperty().addListener((observable, oldValue, newValue) -> {
            if(numeralSystemChoice.getTooltip() != null){
                numeralSystemChoice.getStyleClass().remove("boxerror");
                numeralSystemChoice.setTooltip(null);
            }
            systemPattern = observable.getValue().getPattern();
            systemBase = observable.getValue().getBase();
        });

        operationChoice.valueProperty().addListener((observable, oldValue, newValue) -> {
            if(operationChoice.getTooltip() != null){
                operationChoice.getStyleClass().remove("boxerror");
                operationChoice.setTooltip(null);
            }
            operationLabel.setText(operationSymbol = observable.getValue().getSymbol());
        });

        numberA.textProperty().addListener((observable, oldValue, newValue) -> {
            if(numberA.getTooltip() != null){
                numberA.getStyleClass().remove("error");
                numberA.setTooltip(null);
            }
        });

        numberB.textProperty().addListener((observable, oldValue, newValue) -> {
            if(numberB.getTooltip() != null){
                numberB.getStyleClass().remove("error");
                numberB.setTooltip(null);
            }
        });

    }

    @FXML
    public void equalsBtnReleased(MouseEvent mouseEvent) {
        if(!numberA.getText().isEmpty()) {
            if(!numberB.getText().isEmpty()){
                if(numeralSystemChoice.getValue() != null){
                    if(operationChoice.getValue() != null){
                        String numberAText = numberA.getText();
                        if(numberAText.matches(systemPattern)){
                            String numberBText = numberB.getText();
                            if(numberBText.matches(systemPattern)){
                                switch (operationSymbol){
                                    case "+":
                                        resultLabel.setText(Operations.addNumbers(numberAText, numberBText, systemBase));
                                        break;
                                    case "-":
                                        resultLabel.setText(Operations.subtractNumbers(numberAText, numberBText, systemBase));
                                        break;
                                    case "*":
                                        resultLabel.setText(Operations.multiplyNumbers(numberAText, numberBText, systemBase));
                                        break;
                                    case "/":
                                        if(!numberBText.equals("0")) {
                                            resultLabel.setText(Operations.divideNumbers(numberAText, numberBText, systemBase));
                                        }else{
                                            numberB.getStyleClass().add("error");
                                            numberB.setTooltip(new Tooltip("Chcesz żebyśmy zginęli?!"));
                                        }
                                        break;
                                    default:
                                        break;
                                }
                            }else{
                                numberB.getStyleClass().add("error");
                                numberB.setTooltip(new Tooltip("Podana liczba nie jest zgodna z wybranym systemem liczbowym!"));
                            }
                        }else{
                            numberA.getStyleClass().add("error");
                            numberA.setTooltip(new Tooltip("Podana liczba nie jest zgodna z wybranym systemem liczbowym!"));
                        }
                    }else{
                        operationChoice.getStyleClass().add("boxerror");
                        operationChoice.setTooltip(new Tooltip("Wybierz operację!"));
                    }
                }else{
                    numeralSystemChoice.getStyleClass().add("boxerror");
                    numeralSystemChoice.setTooltip(new Tooltip("Wybierz system liczbowy!"));
                }
            }else{
                numberB.getStyleClass().add("error");
                numberB.setTooltip(new Tooltip("Podaj liczbę!"));
            }
        }else{
            numberA.getStyleClass().add("error");
            numberA.setTooltip(new Tooltip("Podaj liczbę!"));
        }
    }
}
