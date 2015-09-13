package com.github.charithe.grpc

import scala.annotation.StaticAnnotation
import scala.language.experimental.macros
import scala.reflect.macros.whitebox
import scala.reflect.runtime.{universe => ru}

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

      val myProto = c.typecheck(q"1.asInstanceOf[$protoClass]")
      val myType = myProto.tpe.asInstanceOf[Type]
      myType.members.foreach(s => println(s"${s.name} ==> ${s.isMethod}"))
      val myDescriptor = myType.member(TermName("getDescriptor"))
      println(myDescriptor)

      /*val descriptor = c.typecheck(q"${protoClass.toTermName}.getDescriptor()")
      val x = descriptor.tpe.asInstanceOf[MethodSymbol]
      val y =ru.runtimeMirror(this.getClass.getClassLoader).reflect(x)
      println(y)
*/
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
