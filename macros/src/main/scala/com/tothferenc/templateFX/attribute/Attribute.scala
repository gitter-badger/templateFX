package com.tothferenc.templateFX.attribute

import scala.language.experimental.macros
import scala.reflect.macros.Universe
import scala.reflect.macros.whitebox.Context
import scala.util.control.NonFatal

object Attribute {
  val key = "attributes"

  private case class MethodNotFoundException(tpe: String, method: String, cause: Throwable) extends Exception(s"Method $method was not found on $tpe", cause)

  def simple[Attr, Value](getterSetterName: String): Attribute[Attr, Value] = macro simpleImpl[Attr, Value]

  def simpleImpl[Attr: c.WeakTypeTag, Value: c.WeakTypeTag](c: Context)(getterSetterName: c.Expr[String]): c.Expr[Attribute[Attr, Value]] = {
    import c.universe._

    val attrType = weakTypeTag[Attr].tpe

    val Literal(Constant(getset: String)) = getterSetterName.tree
    val name = {
      val (firstChar, rest) = getset.splitAt(1)
      firstChar.toLowerCase + rest
    }
    val valType = weakTypeTag[Value].tpe
    val getter = TermName("get" + getset)
    val setter = TermName("set" + getset)
    val expr =
      q"""new Attribute[$attrType, $valType]{
					override def read(src: $attrType): $valType = src.$getter()
          override def unset(target: $attrType): Unit = target.$setter(null)
          override def set(target: $attrType, value: $valType): Unit = target.$setter(value)
          override def toString(): String = $name
				 }"""
    c.Expr[Attribute[Attr, Value]](expr)
  }

  def remote[Holder, Attr, Value](getterSetterName: String): Attribute[Attr, Value] = macro remoteImpl[Holder, Attr, Value]

  def remoteImpl[Holder: c.WeakTypeTag, Attr: c.WeakTypeTag, Value: c.WeakTypeTag](c: Context)(getterSetterName: c.Expr[String]): c.Expr[Attribute[Attr, Value]] = {
    import c.universe._

    val holderCompanion = weakTypeTag[Holder].tpe.typeSymbol.companion.name.toTermName

    val attrType = weakTypeTag[Attr].tpe

    val Literal(Constant(getset: String)) = getterSetterName.tree
    val name = {
      val (firstChar, rest) = getset.splitAt(1)
      firstChar.toLowerCase + rest
    }
    val valType = weakTypeTag[Value].tpe
    val getter = TermName("get" + getset)
    val setter = TermName("set" + getset)
    val expr =
      q"""new Attribute[$attrType, $valType] {
					override def read(src: $attrType): $valType = $holderCompanion.$getter(src)
          override def unset(target: $attrType): Unit = $holderCompanion.$setter(target, null)
          override def set(target: $attrType, value: $valType): Unit = $holderCompanion.$setter(target, value)
          override def toString(): String = $name
				 }"""
    c.Expr[Attribute[Attr, Value]](expr)
  }
}

abstract class Attribute[-FXType, AttrType] extends Unsettable[FXType] {

  def read(src: FXType): AttrType

  def set(target: FXType, value: AttrType): Unit
}