package com.ezest.javafx.demogallery.tableviews;

import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TooltipBuilder;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CircleBuilder;
import javafx.stage.Stage;
import javafx.util.Callback;

public class RowFactoryAndOptimisationDemo extends Application {

	StackPane root;

	@Override
	public void start(Stage stage) throws Exception {
		root = new StackPane();
		root.autosize();
		Scene scene = new Scene(root, Color.LINEN);

		stage.setTitle("Row Factory Demo");
		stage.setWidth(500);
		stage.setHeight(300);
		stage.setScene(scene);
		stage.show();

		configureTable();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

	static int i = 0;
	static int k = 0;
	static int j = 0;

	@SuppressWarnings("unchecked")
	private void configureTable() {
		final ObservableList<RFDDomain> data = FXCollections.observableArrayList();
		for (int i = 0; i < 10000; i++) {
			data.add(new RFDDomain("First Row", "This is for check.", 1));
			data.add(new RFDDomain("Second Row", null, 2));
			data.add(new RFDDomain("Third Row", "This is for check.", 3));
			data.add(new RFDDomain("Fourth Row", "dil", 4));
		}

		TableView<RFDDomain> tableView = new TableView<RFDDomain>();
		tableView.getStyleClass().add("myTable");
		tableView.setItems(data);

		tableView.setRowFactory(new Callback<TableView<RFDDomain>, TableRow<RFDDomain>>() {
			@Override
			public TableRow<RFDDomain> call(TableView<RFDDomain> paramP) {
				return new TableRow<RFDDomain>() {
					@Override
					protected void updateItem(RFDDomain paramT, boolean paramBoolean) {
						super.updateItem(paramT, paramBoolean);
						if (!isEmpty()) {
							ContextMenu menu = new ContextMenu();
							MenuItem item1 = new MenuItem("one");
							MenuItem item2 = new MenuItem("two");
							menu.getItems().addAll(item1, item2);
							setContextMenu(menu);
						}
					}
				};
			}
		});

		TableColumn<RFDDomain, String> column1 = new TableColumn<RFDDomain, String>("Title");
		column1.setCellValueFactory(new PropertyValueFactory<RFDDomain, String>("name"));

		TableColumn<RFDDomain, String> column2 = new TableColumn<RFDDomain, String>("Description");
		column2.setCellValueFactory(new PropertyValueFactory<RFDDomain, String>("description"));
		column2.setCellFactory(new Callback<TableColumn<RFDDomain, String>, TableCell<RFDDomain, String>>() {
			@Override
			public TableCell<RFDDomain, String> call(TableColumn<RFDDomain, String> paramP) {
				return new TableCell<RFDDomain, String>() {
					@Override
					protected void updateItem(String paramT, boolean paramBoolean) {
						super.updateItem(paramT, paramBoolean);
						if (!isEmpty()) {
							// setGraphic(new Label(paramT));
							// System.out.println("Update : "+(RowFactoryDemo.i++));
							setText(paramT);

						} else {
							setText(null);
							// setGraphic(null);
						}
					}
				};
			}
		});

		TableColumn<RFDDomain, Number> column3 = new TableColumn<RFDDomain, Number>("Status");
		column3.setPrefWidth(55);
		column3.setCellValueFactory(new PropertyValueFactory<RFDDomain, Number>("status"));

		/*column3.setCellFactory(new Callback<TableColumn<RFDDomain, Number>, TableCell<RFDDomain, Number>>() {
			@Override
			public TableCell<RFDDomain, Number> call(TableColumn<RFDDomain, Number> paramP) {
				return new TableCell<RFDDomain, Number>() {

					@Override
					public void updateItem(final Number item, boolean empty) {
						super.updateItem(item, empty);
						if (!isEmpty()) {
							Tooltip toolTip = TooltipBuilder.create().build();
							Circle circle = CircleBuilder.create().radius(5).build();
							buildEpisodeStatusGrapic(item, circle, toolTip);
							setGraphic(circle);
							setTooltip(toolTip);
							System.out.println("Update : " + (RowFactoryDemo.i++));
						} else {
							setGraphic(null);
							setTooltip(null);
						}
					}
				};
			}
		});*/

		column3.setCellFactory(new Callback<TableColumn<RFDDomain, Number>, TableCell<RFDDomain, Number>>() {
			@Override
			public TableCell<RFDDomain, Number> call(TableColumn<RFDDomain, Number> paramP) {
				return new TableCell<RFDDomain, Number>() {
					final Tooltip toolTip = TooltipBuilder.create().build();
					final Circle circle = CircleBuilder.create().radius(5).build();
					{
						setAlignment(Pos.CENTER);
						System.out.println("             Const : " + (RowFactoryAndOptimisationDemo.k++));
					}

					@Override
					public void updateItem(final Number item, boolean empty) {
						super.updateItem(item, empty);
						if (!isEmpty()) {
							buildEpisodeStatusGrapic(item, circle, toolTip);
							System.out.println("Update : " + (RowFactoryAndOptimisationDemo.i++));
							setTooltip(toolTip);
							setGraphic(circle);

						} else {
							setTooltip(null);
							setGraphic(null);

						}
					}
				};
			}
		});

		TableColumn<RFDDomain, Button> column5 = new TableColumn<RFDDomain, Button>("Action");
		column5.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RFDDomain, Button>, ObservableValue<Button>>() {
			@Override
			public ObservableValue<Button> call(CellDataFeatures<RFDDomain, Button> paramP) {
				System.out.println("             							Button : " + (RowFactoryAndOptimisationDemo.j++));
				return new SimpleObjectProperty<Button>(new Button("Show"));
			}
		});

		TableColumn<RFDDomain, String> column4 = new TableColumn<RFDDomain, String>("Action");
		column4.setCellValueFactory(new PropertyValueFactory<RFDDomain, String>("name"));
		column4.setCellFactory(new Callback<TableColumn<RFDDomain, String>, TableCell<RFDDomain, String>>() {
			@Override
			public TableCell<RFDDomain, String> call(TableColumn<RFDDomain, String> paramP) {
				return new TableCell<RFDDomain, String>() {
					final Button btn = new Button("Show");
					{
						System.out.println("             							Button : " + (RowFactoryAndOptimisationDemo.j++));
					}

					@Override
					protected void updateItem(String paramT, boolean isEmpty) {
						super.updateItem(paramT, isEmpty);
						if (!isEmpty) {
							setGraphic(btn);
						} else {
							setGraphic(null);
						}
					}
				};
			}
		});

		tableView.getColumns().addAll(column1, column2, column3, column5);
		this.root.getChildren().add(tableView);
	}

	protected void buildEpisodeStatusGrapic(Number status, Circle circle, Tooltip toolTip) {
		String borderColor;
		String fillColor;
		String toolTipKey;

		switch (status.intValue()) {
		case 1:
			borderColor = "#F99100";
			fillColor = "#F99100";
			toolTipKey = "Medical.episode.overview.status.active";
			break;
		case 2:
			borderColor = "#2da4cc";
			fillColor = "#2da4cc";
			toolTipKey = "Medical.episode.overview.status.passiverelevant";
			break;
		case 3:
			borderColor = "#653300";
			fillColor = "#653300";
			toolTipKey = "Medical.episode.overview.status.passivenotrelevant";
			break;
		default:
			borderColor = "#000000";
			fillColor = "#ffffff";
			toolTipKey = "Medical.episode.overview.status.notpresent";
			break;
		}
		circle.setFill(Color.web(fillColor));
		circle.setStroke(Color.web(borderColor));
		toolTip.setText(toolTipKey);
	}

	/**
	 * Domain Model for this demo.
	 */
	public class RFDDomain {
		private SimpleStringProperty name = new SimpleStringProperty();
		private SimpleStringProperty description = new SimpleStringProperty();
		private SimpleIntegerProperty status = new SimpleIntegerProperty();

		public RFDDomain(String name, String desc, int status) {
			this.name.set(name);
			this.description.set(desc);
			this.status.set(status);
		}

		public String getDescription() {
			return description.get();
		}

		public SimpleStringProperty descriptionProperty() {
			return description;
		}

		public String getName() {
			return name.get();
		}

		public SimpleStringProperty nameProperty() {
			return name;
		}

		public int getStatus() {
			return status.get();
		}

		public SimpleIntegerProperty statusProperty() {
			return status;
		}
	}
}
