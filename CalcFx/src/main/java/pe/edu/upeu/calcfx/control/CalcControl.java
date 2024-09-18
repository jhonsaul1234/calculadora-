package pe.edu.upeu.calcfx.control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pe.edu.upeu.calcfx.modelo.CalcTO;
import pe.edu.upeu.calcfx.servicio.CalcServiceI;

import java.util.List;
import java.util.Stack;

@Component
public class CalcControl {

    @Autowired
    CalcServiceI serviceI;

    @FXML
    TextField txtResultado;

    @FXML
    TableView tableView;

    @FXML
    TableColumn<CalcTO, String> cVal1, cVal2, cResult;

    @FXML
    TableColumn<CalcTO, Character> cOper;

    @FXML
    TableColumn<CalcTO, Void> cOpc;

    private ObservableList<CalcTO> calcTOList;
    private int indexEdit = -1;

    @FXML
    Button btn7, btn8, btn9, btnPotencia, btnRaiz, btnInv, btnPi, btnBinario;

    @FXML
    public void initialize() {
        anular();
    }

    int t = 0;

    @FXML
    public void accionButton(ActionEvent event) {
        Button button = (Button) event.getSource();
        switch (button.getId()) {
            case "btn7", "btn8", "btn9", "btn6", "btn5", "btn4", "btn3", "btn2", "btn1", "btn0": {
                escribirNumeros(button.getText());
            }
            break;
            case "btnSum", "btnMul", "btnRest", "btnDiv": {
                operador(button.getText());
            }
            break;
            case "btnIgual": {
                calcularResultado();
            }
            break;
            case "btnBorrar": {
                txtResultado.clear();
            }
            break;
            case "btnPotencia": {
                calcularPotencia();
            }
            break;
            case "btnRaiz": {
                calcularRaiz();
            }
            break;
            case "btnInv": {
                calcularInverso();
            }
            break;
            case "btnPi": {
                calcularPi();
            }
            break;
            case "btnBinario": {
                convertirBinario();
            }
            break;
        }
    }

    public void escribirNumeros(String valor) {
        txtResultado.appendText(valor);
    }

    public void operador(String valor) {
        txtResultado.appendText(" " + valor + " ");
    }

    public void calcularResultado() {
        String[] valores = txtResultado.getText().split(" ");
        double val1 = Double.parseDouble(valores[0]);
        double val2 = Double.parseDouble(valores[2]);
        switch (valores[1]) {
            case "+":
                txtResultado.setText(String.valueOf(val1 + val2));
                break;
            case "-":
                txtResultado.setText(String.valueOf(val1 - val2));
                break;
            case "/":
                txtResultado.setText(String.valueOf(val1 / val2));
                break;
            case "*":
                txtResultado.setText(String.valueOf(val1 * val2));
                break;
        }

        CalcTO to = new CalcTO();
        to.setNum1(String.valueOf(val1));
        to.setNum2(String.valueOf(val2));
        to.setOperador(valores[1].charAt(0));
        to.setResultado(String.valueOf(txtResultado.getText()));
        if (indexEdit != -1) {
            serviceI.actualizarResultados(to, indexEdit);
        } else {
            serviceI.guardarResultados(to);
        }
        indexEdit = -1;
        listaOper();
    }

    public void calcularPotencia() {
        String[] valores = txtResultado.getText().split(" ");
        double base = Double.parseDouble(valores[0]);
        double exponente = Double.parseDouble(valores[2]);
        txtResultado.setText(String.valueOf(Math.pow(base, exponente)));
    }

    public void calcularRaiz() {
        double valor = Double.parseDouble(txtResultado.getText());
        txtResultado.setText(String.valueOf(Math.sqrt(valor)));
    }

    public void calcularInverso() {
        double valor = Double.parseDouble(txtResultado.getText());
        txtResultado.setText(String.valueOf(1 / valor));
    }

    public void calcularPi() {
        txtResultado.setText(String.valueOf(Math.PI));
    }

    public void convertirBinario() {
        int valor = Integer.parseInt(txtResultado.getText());
        txtResultado.setText(Integer.toBinaryString(valor));
    }

    // Algoritmo de análisis léxico básico
    public double analizarExpresion(String expresion) {
        String[] tokens = expresion.split(" ");
        Stack<Double> valores = new Stack<>();
        Stack<Character> operadores = new Stack<>();

        for (String token : tokens) {
            if (token.matches("[0-9]+")) {
                valores.push(Double.parseDouble(token));
            } else if (token.matches("[+\\-*/^]")) {
                operadores.push(token.charAt(0));
            }
        }

        while (!operadores.isEmpty()) {
            double b = valores.pop();
            double a = valores.pop();
            char oper = operadores.pop();

            switch (oper) {
                case '+':
                    valores.push(a + b);
                    break;
                case '-':
                    valores.push(a - b);
                    break;
                case '*':
                    valores.push(a * b);
                    break;
                case '/':
                    valores.push(a / b);
                    break;
                case '^':
                    valores.push(Math.pow(a, b));
                    break;
            }
        }

        return valores.pop();
    }

    private void editOperCalc(CalcTO cal, int index) {
        txtResultado.setText(cal.getNum1() + " " + cal.getOperador() + " " + cal.getNum2());
        indexEdit = index;
    }

    private void deleteOperCalc(CalcTO cal, int index) {
        serviceI.eliminarResultados(index);
        listaOper();
    }

    private void addActionButtonsToTable() {
        Callback<TableColumn<CalcTO, Void>, TableCell<CalcTO, Void>> cellFactory = param -> new TableCell<>() {
            private final Button editButton = new Button("Edit");
            private final Button deleteButton = new Button("Delete");

            {
                editButton.getStyleClass().setAll("btn", "btn-success");
                editButton.setOnAction(event -> {
                    CalcTO cal = getTableView().getItems().get(getIndex());
                    editOperCalc(cal, getIndex());
                });

                deleteButton.getStyleClass().setAll("btn", "btn-danger");
                deleteButton.setOnAction(event -> {
                    CalcTO cal = getTableView().getItems().get(getIndex());
                    deleteOperCalc(cal, getIndex());
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    HBox buttons = new HBox(editButton, deleteButton);
                    buttons.setSpacing(10);
                    setGraphic(buttons);
                }
            }
        };
        cOpc.setCellFactory(cellFactory);
    }

    public void listaOper() {
        List<CalcTO> lista = serviceI.obtenerResultados();
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        cVal1.setCellValueFactory(new PropertyValueFactory<>("num1"));
        cVal1.setCellFactory(TextFieldTableCell.forTableColumn());

        cVal2.setCellValueFactory(new PropertyValueFactory<>("num2"));
        cVal2.setCellFactory(TextFieldTableCell.forTableColumn());

        cOper.setCellValueFactory(new PropertyValueFactory<>("Operador"));
        cOper.setCellFactory(ComboBoxTableCell.forTableColumn('+', '-', '/', '*'));

        cResult.setCellValueFactory(new PropertyValueFactory<>("Resultado"));
        cResult.setCellFactory(TextFieldTableCell.forTableColumn());

        addActionButtonsToTable();
        calcTOList = FXCollections.observableArrayList(serviceI.obtenerResultados());
        tableView.setItems(calcTOList);
    }

    @FXML
    public void iniciar() {
        activaDesacticaB(false);
    }

    @FXML
    public void anular() {
        activaDesacticaB(true);
    }

    public void activaDesacticaB(boolean indi) {
        btn7.setDisable(indi);
        btn8.setDisable(indi);
        btn9.setDisable(indi);
    }
}