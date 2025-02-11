import zio.*
import zio.http.*

// ZIOAppDefaultを継承するとrunがエントリポイントになる
object MyServer extends ZIOAppDefault:
  val routes =
    Routes(
      Method.GET / Root -> handler(Response.text("Greetings at your service")),
      Method.GET / "greet" -> handler { (req: Request) =>
        val name = req.queryParamToOrElse("name", "World")
        Response.text(s"Hello $name!")
      },
      Method.GET / "justok"   -> Handler.ok,
      Method.GET / "justfail" -> Handler.failCause(Cause.fail("oops")),
      Method.GET / "user" / string("id") -> handler {
        (id: String, req: Request) =>
          Response.text(s"User $id")
      }, // ++でまとめられるのが良い
    ) ++ Json.routes ++ ZioEndpoint.routes ++ MiddlewareEndpoint.routes

  def run = Server
    .serve(
      routes.handleError(e => Response.internalServerError(s"ERROR: $e")),
    )                        // handleErrorしないとfailCauseを扱えないようになっている
    .provide(Server.default) // Server.defaultだとlocalhost:8080で起動する
end MyServer
