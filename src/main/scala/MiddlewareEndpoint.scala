import zio.*
import zio.http.*
import zio.Config.Secret

object MiddlewareEndpoint:
  val routes = Routes(
    Method.GET / "debug" -> handler {
      Response.text("this is just a response")
    } @@ Middleware.debug,
    Method.GET / "auth" -> handler {
      Response.text("this is just a response")
    } @@ Middleware.bearerAuth(sec => sec == Secret("foobar")),
  )
end MiddlewareEndpoint
