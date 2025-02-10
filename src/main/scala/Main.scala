import zio.*
import zio.http.*

object GreetingServer extends ZIOAppDefault:
  val routes =
    Routes(
      Method.GET / Root -> handler(Response.text("Greetings at your service")),
      Method.GET / "greet" -> handler { (req: Request) =>
        val name = req.queryParamToOrElse("name", "World")
        Response.text(s"Hello $name!")
      },
      Method.GET / "justok" -> Handler.ok,
      Method.GET / "user" / string("id") -> handler {
        (id: String, req: Request) =>
          Response.text(s"User $id")
      },
    )

  def run = Server.serve(routes).provide(Server.default)
end GreetingServer
