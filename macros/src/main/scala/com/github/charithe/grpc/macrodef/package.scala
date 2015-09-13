package com.github.charithe.grpc

import scala.annotation.StaticAnnotation
import scala.language.experimental.macros
import scala.reflect.macros.whitebox

/**
 * Created by cell on 26/08/15.
 */
package object macrodef {

  class ProtoToCaseClass[T] extends StaticAnnotation {
    def macroTransform(annottees: Any*) = macro GrpcMacros.protoToCaseClassImpl
  }

  object GrpcMacros {
    def protoToCaseClassImpl(c: whitebox.Context)(annottees: c.Expr[Any]*) = {
      import c.universe._
      val traitName = annottees.map(_.tree) match {
        case List(q"object $name") => name
        case _ => c.abort(c.enclosingPosition, "This annotation can only be used with objects")
      }

      val Apply(Select(q"new ${_}[${protoClass:TypeName}]()", _), _) = c.macroApplication

      val newClass = c.mirror.staticClass(protoClass.toString)
      println(newClass)

      //println(showRaw(c.macroApplication))
      val className = protoClass
      //val className = TypeName(s"${traitName}Test")
      c.Expr {
        q"""
            object $traitName {
              case class $className() {
                def sayHello() {
                  println("Hello")
                }
              }
            }
          """
      }
    }
  }

}
