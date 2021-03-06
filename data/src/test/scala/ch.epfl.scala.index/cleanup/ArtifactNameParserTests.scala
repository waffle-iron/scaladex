package ch.epfl.scala.index
package data
package cleanup

import model.release.{ReleaseCandidate, ScalaTarget, SemanticVersion}

import utest._

object ArtifactNameParserTest extends TestSuite{
  val tests = this{
    "parsing"-{
      "scalajs"-{
        assert(ArtifactNameParser("cats-core_sjs0.6_2.11") == Some((
          "cats-core",
          ScalaTarget(SemanticVersion(2, 11), Some(SemanticVersion(0, 6)))
        )))
      }
      "scala rc"-{
        assert(ArtifactNameParser("akka-remote-tests_2.11.0-RC4") == Some((
          "akka-remote-tests",
          ScalaTarget(SemanticVersion(2, 11, Some(0), preRelease = Some(ReleaseCandidate(4))))
        )))
      }
      "not using sbt convention"-{
        assert(ArtifactNameParser("sparrow") == None)
      }
      "special case"-{
        assert(ArtifactNameParser("banana_jvm_2.11") == Some((
          "banana_jvm",
          ScalaTarget(SemanticVersion(2, 11))
        )))
      }
    }
  }
}