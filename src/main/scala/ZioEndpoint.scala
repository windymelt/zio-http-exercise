import zio.*
import zio.http.*

object ZioEndpoint:
  val routes = Routes(
    Method.GET / "zio" -> handler {
      ZIO.succeed(Response.text("Hello ZIO!"))
    },
  )
