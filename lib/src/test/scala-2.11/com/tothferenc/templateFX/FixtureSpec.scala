package com.tothferenc.templateFX

import javafx.embed.swing.JFXPanel
import javafx.scene.Node
import javafx.scene.control.Label
import javafx.scene.layout.BorderPane

import com.tothferenc.templateFX.attributes._
import com.tothferenc.templateFX.fixtures._
import com.tothferenc.templateFX.Api._
import com.tothferenc.templateFX.base.Fixture
import com.tothferenc.templateFX.base.Template
import org.specs2.mutable.Specification

class FixtureSpec extends Specification {

	val fixtures = List(borderTop, borderRight, borderBottom, borderLeft, borderCenter)

	new JFXPanel()

	def lt(s: String): Template[Label] = leaf[Label](text ~ s)

	def bind(fixture: Fixture[BorderPane, Node]) = fixture ~~ lt(fixture.toString)

	"BorderPane" should {
		"be instantiated with a node in the requested place" in {

			val bp: BorderPane = leaf[BorderPane](bind(borderLeft)).build()
			bp.getLeft.asInstanceOf[Label].getText === "left"
			fixtures.filterNot(_ == borderLeft).forall(_.read(bp) == null)
		}
	}
}
