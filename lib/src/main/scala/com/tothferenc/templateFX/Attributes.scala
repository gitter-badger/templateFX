package com.tothferenc.templateFX

import java.lang
import com.tothferenc.templateFX.attribute.Attribute

import javafx.css.Styleable
import javafx.event.{ ActionEvent, EventHandler }
import javafx.scene.{ AccessibleRole, Node }
import javafx.scene.chart.Chart
import javafx.scene.control._
import javafx.scene.input.{ KeyEvent, MouseEvent }
import javafx.scene.layout.{ AnchorPane, ColumnConstraints, GridPane }

import scala.collection.convert.wrapAsScala._

object Attributes {
  private type SuperHandler[Whatever] = EventHandler[_ >: Whatever]

  object Anchor {

    case object top extends Attribute[Node, lang.Double] {
      override def read(src: Node): lang.Double = AnchorPane.getTopAnchor(src)

      override def set(target: Node, value: lang.Double): Unit = AnchorPane.setTopAnchor(target, value)

      override def unset(target: Node): Unit = AnchorPane.setTopAnchor(target, null)
    }

    case object bottom extends Attribute[Node, lang.Double] {
      override def read(src: Node): lang.Double = AnchorPane.getBottomAnchor(src)

      override def set(target: Node, value: lang.Double): Unit = AnchorPane.setBottomAnchor(target, value)

      override def unset(target: Node): Unit = AnchorPane.setBottomAnchor(target, null)
    }

    case object left extends Attribute[Node, lang.Double] {
      override def read(src: Node): lang.Double = AnchorPane.getLeftAnchor(src)

      override def set(target: Node, value: lang.Double): Unit = AnchorPane.setLeftAnchor(target, value)

      override def unset(target: Node): Unit = AnchorPane.setLeftAnchor(target, null)
    }

    case object right extends Attribute[Node, lang.Double] {
      override def read(src: Node): lang.Double = AnchorPane.getRightAnchor(src)

      override def set(target: Node, value: lang.Double): Unit = AnchorPane.setRightAnchor(target, value)

      override def unset(target: Node): Unit = AnchorPane.setRightAnchor(target, null)
    }
  }

  object Grid {

    case object columnConstraints extends Attribute[GridPane, List[ColumnConstraints]] {
      override def read(src: GridPane): List[ColumnConstraints] = src.getColumnConstraints.toList

      override def unset(target: GridPane): Unit = target.getStyleClass.clear()

      override def set(target: GridPane, value: List[ColumnConstraints]): Unit = target.getColumnConstraints.setAll(value: _*)
    }

    case object column extends Attribute[Node, lang.Integer] {
      override def read(src: Node): lang.Integer = GridPane.getColumnIndex(src)

      override def set(target: Node, value: lang.Integer): Unit = GridPane.setColumnIndex(target, value)

      override def unset(target: Node): Unit = GridPane.setColumnIndex(target, null)
    }

    case object row extends Attribute[Node, lang.Integer] {
      override def read(src: Node): lang.Integer = GridPane.getRowIndex(src)

      override def set(target: Node, value: lang.Integer): Unit = GridPane.setRowIndex(target, value)

      override def unset(target: Node): Unit = GridPane.setRowIndex(target, null)
    }
  }

  val text = Attribute.simple[Labeled, String]("Text")

  val title = Attribute.simple[Chart, String]("Title")

  case object styleClasses extends Attribute[Styleable, List[String]] {
    override def read(src: Styleable): List[String] = src.getStyleClass.toList

    override def unset(target: Styleable): Unit = target.getStyleClass.clear()

    override def set(target: Styleable, value: List[String]): Unit = target.getStyleClass.setAll(value: _*)
  }

  case object inputText extends Attribute[TextInputControl, String] {
    override def read(src: TextInputControl): String = src.getText

    override def unset(target: TextInputControl): Unit = target.setText(null)

    override def set(target: TextInputControl, value: String): Unit = target.setText(value)
  }

  case object onActionText extends Attribute[TextField, EventHandler[ActionEvent]] {
    override def read(src: TextField): EventHandler[ActionEvent] = src.getOnAction

    override def unset(target: TextField): Unit = target.setOnAction(null)

    override def set(target: TextField, value: EventHandler[ActionEvent]): Unit = target.setOnAction(value)
  }

  case object onActionButton extends Attribute[ButtonBase, EventHandler[ActionEvent]] {
    override def read(src: ButtonBase): EventHandler[ActionEvent] = src.getOnAction

    override def unset(target: ButtonBase): Unit = target.setOnAction(null)

    override def set(target: ButtonBase, value: EventHandler[ActionEvent]): Unit = target.setOnAction(value)
  }

  case object onKeyPressed extends Attribute[TextInputControl, SuperHandler[KeyEvent]] {
    override def read(src: TextInputControl): SuperHandler[KeyEvent] = src.getOnKeyPressed

    override def unset(target: TextInputControl): Unit = target.setOnKeyPressed(null)

    override def set(target: TextInputControl, value: SuperHandler[KeyEvent]): Unit = target.setOnKeyPressed(value)
  }

  val id = Attribute.simple[Node, String]("Id")

  val style = Attribute.simple[Node, String]("Style")

  val accessibleHelp = Attribute.simple[Node, String]("AccessibleHelp")

  val accessibleText = Attribute.simple[Node, String]("AccessibleText")

  val accessibleRole = Attribute.simple[Node, AccessibleRole]("AccessibleRole")

  case object onMouseClick extends Attribute[Node, EventHandler[_ >: MouseEvent]] {
    override def unset(target: Node): Unit = target.setOnMouseClicked(null)

    override def read(src: Node): EventHandler[_ >: MouseEvent] = src.getOnMouseClicked

    override def set(target: Node, value: EventHandler[_ >: MouseEvent]): Unit = target.setOnMouseClicked(value)
  }

}
