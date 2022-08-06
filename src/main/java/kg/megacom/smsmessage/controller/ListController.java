package kg.megacom.smsmessage.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import kg.megacom.smsmessage.model.MessageInfo;
import kg.megacom.smsmessage.service.MessageService;

import java.net.URL;
import java.util.ResourceBundle;

public class ListController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<MessageInfo> tblList;

    @FXML
    private TableColumn<String, MessageInfo> clmText;

    @FXML
    private TableColumn<String, MessageInfo> clmDate;

    @FXML
    private TableColumn<String, MessageInfo> clmSender;


    @FXML
    private TableColumn<Integer, MessageInfo> clmSenderId;

    private String phone;

    public void setData(String phone) {
        this.phone = phone;
        refreshTable();
    }

    @FXML
    void initialize() {
        clmText.setCellValueFactory(new PropertyValueFactory<>("text"));
        clmDate.setCellValueFactory(new PropertyValueFactory<>("msgDate"));
        clmSender.setCellValueFactory(new PropertyValueFactory<>("senderPhone"));
        clmSenderId.setCellValueFactory(new PropertyValueFactory<>("senderId"));
    }

    private void refreshTable() {
        tblList.setItems(FXCollections.observableList(MessageService.INSTANCE.showListsByPhone(this.phone)));
    }
}
