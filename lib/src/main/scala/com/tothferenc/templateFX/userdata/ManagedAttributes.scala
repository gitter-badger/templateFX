package com.tothferenc.templateFX.userdata

import com.tothferenc.templateFX.attribute.Attribute
import com.tothferenc.templateFX.attribute.RemovableFeature

import scala.collection.mutable

object ManagedAttributes {

  def getOrInit[Container: UserDataAccess](node: Container): mutable.Set[RemovableFeature[Container]] = {
    get(node).getOrElse {
      val set = new mutable.HashSet[RemovableFeature[Container]]
      ManagedAttributes.set(node, set)
      set
    }
  }

  def get[Container: UserDataAccess](node: Container): Option[mutable.Set[RemovableFeature[Container]]] = {
    UserData.get[Container, mutable.Set[RemovableFeature[Container]]](node, Attribute.key)
  }

  def set[Container: UserDataAccess](node: Container, attributes: mutable.Set[RemovableFeature[Container]]): Unit = {
    UserData.set(node, Attribute.key, attributes)
  }
}