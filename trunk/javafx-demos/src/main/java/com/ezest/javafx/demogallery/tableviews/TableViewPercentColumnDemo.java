package com.ezest.javafx.demogallery.tableviews;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TableViewPercentColumnDemo extends Application {

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		StackPane root = new StackPane();
		root.autosize();
		Scene scene = new Scene(root);
		stage.setTitle("TableView Auto Size Demo");
		stage.setWidth(700);
		stage.setHeight(400);
		stage.setScene(scene);
		stage.show();
		
		configureTable(root);
	}

	@SuppressWarnings("unchecked")
	private void configureTable(StackPane root) {

		final ObservableList<MyDomain> data = FXCollections.observableArrayList(
				new MyDomain("Apple","This is a fruit.","Red"),
				new MyDomain("Orange","This is also a fruit.","Orange"),
				new MyDomain("Potato","This is a vegetable.","Brown")
				);

		TableView<MyDomain> table = new TableView<MyDomain>();
		
		final TableColumn<MyDomain,String> col1 = new TableColumn<MyDomain,String>();
		col1.setCellValueFactory(new PropertyValueFactory<MyDomain,String>("name"));
		
		final TableColumn<MyDomain,String> col2 = new TableColumn<MyDomain,String>();
		col2.setCellValueFactory(new PropertyValueFactory<MyDomain,String>("description"));
		
		final TableColumn<MyDomain,String> col3 = new TableColumn<MyDomain,String>();
		col3.setCellValueFactory(new PropertyValueFactory<MyDomain,String>("color"));
		
		table.getColumns().addAll(col1,col2,col3);
		table.setItems(data);
		root.getChildren().add(table);
		
		//Procedure: #1
			col1.prefWidthProperty().bind(table.widthProperty().subtract(3).divide(100).multiply(30));
			col2.prefWidthProperty().bind(table.widthProperty().subtract(3).divide(100).multiply(40));
			col3.prefWidthProperty().bind(table.widthProperty().subtract(3).divide(100).multiply(30));
			
		//Procedure: #2
			/*table.widthProperty().addListener(new ChangeListener<Number>() {
				@Override
				public void changed(ObservableValue<? extends Number> arg0,	Number arg1, Number arg2) {
					double tableWidth = arg2.doubleValue()-3;
					col1.setPrefWidth((tableWidth/100)*30);
					col2.setPrefWidth((tableWidth/100)*40);
					col3.setPrefWidth((tableWidth/100)*30);
				}
			});*/
		
	}

	/**
	 * Domain Object.
	 */
	public class MyDomain{
		private SimpleStringProperty name = new SimpleStringProperty();
		private SimpleStringProperty description = new SimpleStringProperty();
		private SimpleStringProperty color = new SimpleStringProperty();
		public MyDomain(String name, String desc,String color){
			this.name.set(name);
			this.description.set(desc);
			this.color.set(color);
		}
		public String getDescription() {
			return description.get();
		}
		public SimpleStringProperty descriptionProperty(){
			return description;
		}
		public String getName() {
			return name.get();
		}
		public SimpleStringProperty nameProperty(){
			return name;
		}
		public String getColor() {
			return color.get();
		}
		public SimpleStringProperty colorProperty(){
			return color;
		}
	}
}
