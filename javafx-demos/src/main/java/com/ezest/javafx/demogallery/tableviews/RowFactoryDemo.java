package com.ezest.javafx.demogallery.tableviews;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

public class RowFactoryDemo extends Application{
	
	StackPane root;
	
	@Override
	public void start(Stage stage) throws Exception {
		root = new StackPane();
		root.autosize();
		Scene scene = new Scene(root, Color.LINEN);
		//scene.getStylesheets().add("com/ezest/javafx/demogallery/tableviews/GhostColumnRemover.css");
		
		stage.setTitle("Row Factory Demo");
		stage.setWidth(700);
	    stage.setHeight(400);
	    stage.setScene(scene);
	    stage.show();
	    
	    configureTable();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@SuppressWarnings("unchecked")
	private void configureTable() {
		final ObservableList<MyTableViewDomain> data = FXCollections.observableArrayList(
				 new MyTableViewDomain("First Row","This is for check."),
				 new MyTableViewDomain("Second Row","This is for check.")
			 );
		
		TableView<MyTableViewDomain> tableView = new TableView<MyTableViewDomain>();
		tableView.getStyleClass().add("myTable");
		tableView.setItems(data);
		
		tableView.setRowFactory(new Callback<TableView<MyTableViewDomain>, TableRow<MyTableViewDomain>>() {
			@Override
			public TableRow<MyTableViewDomain> call(TableView<MyTableViewDomain> paramP) {
				return new TableRow<MyTableViewDomain>(){
					@Override
					protected void updateItem(MyTableViewDomain paramT,	boolean paramBoolean) {
						super.updateItem(paramT, paramBoolean);
						if(!isEmpty()){
							ContextMenu menu = new ContextMenu();
							MenuItem item1 = new MenuItem("one");
							MenuItem item2 = new MenuItem("two");
							menu.getItems().addAll(item1,item2);
							setContextMenu(menu);
						}
					}
				};
			}
		});
		
		TableColumn<MyTableViewDomain, String> column1 = new TableColumn<MyTableViewDomain, String>("Title");
		column1.setCellValueFactory(new PropertyValueFactory<MyTableViewDomain, String>("name"));
		
		TableColumn<MyTableViewDomain, String> column2 = new TableColumn<MyTableViewDomain, String>("Description");
		column2.setCellValueFactory(new PropertyValueFactory<MyTableViewDomain, String>("description"));
		column2.setCellFactory(new Callback<TableColumn<MyTableViewDomain,String>, TableCell<MyTableViewDomain,String>>() {
			@Override
			public TableCell<MyTableViewDomain, String> call(TableColumn<MyTableViewDomain, String> paramP) {
				return new TableCell<MyTableViewDomain, String>(){
					@Override
					protected void updateItem(String paramT, boolean paramBoolean) {
						super.updateItem(paramT, paramBoolean);
						if(!isEmpty()){
							setGraphic(new Label(paramT));
						}
					}
				};
			}
		});
		
		tableView.getColumns().addAll(column1, column2);
		this.root.getChildren().add(tableView);
	}

	
}


