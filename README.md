templateFX
==========

[![Build Status](https://travis-ci.org/tferi/templateFX.svg?branch=master)](https://travis-ci.org/tferi/templateFX)

TemplateFX is a JavaFX UI definition and reconcilation library, written in Scala. It is a proof of concept for bringing React.js-like functionality to the JVM.

TemplateFX allows its users to define arbitrary object graph templates with its functional API. These templates may be reconciled with another object graph, meaning that the library will make changes to the target until it conforms to the template.

This capability allows the library's users to describe the desired state of the UI in a declarative, typesafe manner. The template language is easy to use, and it allows users to effectively decouple UI-specific code from the architecture of the application.

To see how it works in practice, see the [example application](examples/src/main/scala/com/tothferenc/templateFX/examples/todo)!

Coming soon!
