package com.sai.javafx.mini_icon_animation;

import javafx.animation.*;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

/**
 * @author abi
 *         Date: 05.08.12
 *         Time: 07:45
 */
public class MiniIconAnimationButton extends StackPane {

  /**
   * Type of animation
   */
  public enum AnimationType { NONE, JUMP, BLINK };

  /**
   * The original button, all button methods will be delegated to this button
   */
  private Button button;

  /**
   * The additional mini icon which can be animated
   */
  private ImageView imageView;

  /**
   * The animation type of this button
   */
  private AnimationType type;

  /**
   * Extended button without an animation
   * @param text button text
   * @param graphic button icon
   * @param notifyImage mini icon
   */
  public MiniIconAnimationButton(String text, Node graphic, final ImageView notifyImage) {
    this(text, graphic, notifyImage, AnimationType.NONE);
  }

  /**
   * Extended button with an animation
   * @param text button text
   * @param graphic button icon
   * @param notifyImage mini iccon
   * @param type animation type fro mini icon
   */
  public MiniIconAnimationButton(String text, Node graphic, final ImageView notifyImage, final AnimationType type) {
    this.button = new Button(text, graphic);
    this.button.setTextAlignment(TextAlignment.CENTER);
    this.button.setContentDisplay(ContentDisplay.TOP);
    this.imageView = notifyImage;
    this.type = type;
    init();
  }

  /**
   * init the button
   */
  private void init() {
    stackControls();
    addImageViewSizeBindings();
    addAnimation();
  }

  /**
   * add the button to the background and the mini-icon to the front
   */
  private void stackControls() {
    StackPane.setAlignment(imageView, Pos.TOP_RIGHT);
    StackPane.setMargin(imageView, new Insets(4, 4, 4, 4)) ;
    getChildren().addAll(button, imageView);
  }

  /**
   * bind the size of the mini-icon to the button size
   */
  private void addImageViewSizeBindings() {
    final ReadOnlyDoubleProperty widthProperty = button.widthProperty();
    final ReadOnlyDoubleProperty heightProperty = button.heightProperty();

    final DoubleBinding widthBinding = widthProperty.divide(4.0);
    final DoubleBinding heightBinding = heightProperty.divide(4.0);
    imageView.setFitWidth(widthBinding.doubleValue());
    imageView.setFitHeight(heightBinding.doubleValue());

    widthBinding.addListener(new ChangeListener() {
      @Override
      public void changed(ObservableValue o, Object oldVal, Object newVal) {
        imageView.setFitWidth(widthBinding.doubleValue());
      }
    });

    heightBinding.addListener(new ChangeListener() {
      @Override
      public void changed(ObservableValue o, Object oldVal, Object newVal) {
        imageView.setFitHeight(heightBinding.doubleValue());
      }
    });
  }

  /**
   * add the animation
   */
  private void addAnimation() {
    switch (type) {
      case BLINK:
        addBlinkingAnimation();
        break;
      case JUMP:
        addJumpingAnimation();
        break;
      case NONE:
        // none is the default case
      default:
        // noting to animate
        break;
    }
  }

  /**
   * the jump animation changes the position of the mini-icon
   */
  private void addJumpingAnimation() {
    final TranslateTransition translateTransition =  new TranslateTransition(Duration.millis(200), imageView);
    final double start = 0.0;
    final double end = start - 4.0;
    translateTransition.setFromY(start);
    translateTransition.setToY(end);
    translateTransition.setCycleCount(-1);
    translateTransition.setAutoReverse(true);
    translateTransition.setInterpolator(Interpolator.EASE_BOTH);
    translateTransition.play();
  }

  /**
   * blinking animation changes the opacity of the mini-icon
   */
  private void addBlinkingAnimation() {
    final Timeline timeline = new Timeline();
    timeline.setCycleCount(Timeline.INDEFINITE);
    timeline.setAutoReverse(true);
    final KeyValue kv = new KeyValue(imageView.opacityProperty(), 0.0);
    final KeyFrame kf = new KeyFrame(Duration.millis(700), kv);
    timeline.getKeyFrames().add(kf);
    timeline.play();
  }


  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // delegation methods for button, this class should behave like a normal button                                     //
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  public void setDefaultButton(boolean value) {
    button.setDefaultButton(value);
  }

  public void setFont(Font value) {
    button.setFont(value);
  }

  public ObjectProperty<ContentDisplay> contentDisplayProperty() {
    return button.contentDisplayProperty();
  }

  public ContentDisplay getContentDisplay() {
    return button.getContentDisplay();
  }

  public Skin<?> getSkin() {
    return button.getSkin();
  }

  public Paint getTextFill() {
    return button.getTextFill();
  }

  public ObjectProperty<TextAlignment> textAlignmentProperty() {
    return button.textAlignmentProperty();
  }

  public ContextMenu getContextMenu() {
    return button.getContextMenu();
  }

  public ObjectProperty<Paint> textFillProperty() {
    return button.textFillProperty();
  }

  public boolean isCancelButton() {
    return button.isCancelButton();
  }

  public void setContextMenu(ContextMenu value) {
    button.setContextMenu(value);
  }

  public void setTextFill(Paint value) {
    button.setTextFill(value);
  }

  public StringProperty textProperty() {
    return button.textProperty();
  }

  public DoubleProperty graphicTextGapProperty() {
    return button.graphicTextGapProperty();
  }

  public OverrunStyle getTextOverrun() {
    return button.getTextOverrun();
  }

  public boolean isMnemonicParsing() {
    return button.isMnemonicParsing();
  }

  public void disarm() {
    button.disarm();
  }

  public Insets getLabelPadding() {
    return button.getLabelPadding();
  }

  public String getText() {
    return button.getText();
  }

  public ObjectProperty<OverrunStyle> textOverrunProperty() {
    return button.textOverrunProperty();
  }

  public ObjectProperty<ContextMenu> contextMenuProperty() {
    return button.contextMenuProperty();
  }

  public Tooltip getTooltip() {
    return button.getTooltip();
  }

  public void setTextAlignment(TextAlignment value) {
    button.setTextAlignment(value);
  }

  public BooleanProperty wrapTextProperty() {
    return button.wrapTextProperty();
  }

  public void setTextOverrun(OverrunStyle value) {
    button.setTextOverrun(value);
  }

  public void arm() {
    button.arm();
  }

  public void setUnderline(boolean value) {
    button.setUnderline(value);
  }

  public double getGraphicTextGap() {
    return button.getGraphicTextGap();
  }

  public void setMnemonicParsing(boolean value) {
    button.setMnemonicParsing(value);
  }

  public BooleanProperty cancelButtonProperty() {
    return button.cancelButtonProperty();
  }

  public boolean isArmed() {
    return button.isArmed();
  }

  public void setGraphicTextGap(double value) {
    button.setGraphicTextGap(value);
  }

  public Font getFont() {
    return button.getFont();
  }

  public ObjectProperty<Skin<?>> skinProperty() {
    return button.skinProperty();
  }

  public void setText(String value) {
    button.setText(value);
  }

  public ObjectProperty<EventHandler<ActionEvent>> onActionProperty() {
    return button.onActionProperty();
  }

  public BooleanProperty mnemonicParsingProperty() {
    return button.mnemonicParsingProperty();
  }

  public ReadOnlyObjectProperty<Insets> labelPaddingProperty() {
    return button.labelPaddingProperty();
  }

  public boolean isDefaultButton() {
    return button.isDefaultButton();
  }

  public void setGraphic(Node value) {
    button.setGraphic(value);
  }

  public void setOnAction(EventHandler<ActionEvent> value) {
    button.setOnAction(value);
  }

  public void setWrapText(boolean value) {
    button.setWrapText(value);
  }

  public TextAlignment getTextAlignment() {
    return button.getTextAlignment();
  }

  public BooleanProperty defaultButtonProperty() {
    return button.defaultButtonProperty();
  }

  public ReadOnlyBooleanProperty armedProperty() {
    return button.armedProperty();
  }

  public void setContentDisplay(ContentDisplay value) {
    button.setContentDisplay(value);
  }

  public void fire() {
    button.fire();
  }

  public boolean isUnderline() {
    return button.isUnderline();
  }

  public ObjectProperty<Node> graphicProperty() {
    return button.graphicProperty();
  }

  public ObjectProperty<Font> fontProperty() {
    return button.fontProperty();
  }

  public boolean isWrapText() {
    return button.isWrapText();
  }

  public void setCancelButton(boolean value) {
    button.setCancelButton(value);
  }

  public void setSkin(Skin<?> value) {
    button.setSkin(value);
  }

  public EventHandler<ActionEvent> getOnAction() {
    return button.getOnAction();
  }

  public ObjectProperty<Tooltip> tooltipProperty() {
    return button.tooltipProperty();
  }

  public void setTooltip(Tooltip value) {
    button.setTooltip(value);
  }

  public BooleanProperty underlineProperty() {
    return button.underlineProperty();
  }

  public Node getGraphic() {
    return button.getGraphic();
  }
}
