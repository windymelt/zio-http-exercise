import zio.*
import zio.http.*

object Json:
  case class User(id: String, age: Int)
  object User:
    import zio.json.*
    given JsonCodec[User] = DeriveJsonCodec.gen[User]

  val routes = Routes(
    Method.GET / "json" -> handler {
      import zio.json.*
      // ここの中身はただのStringなのでcirceを使ってもいい
      // Response.jsonは単にcontent-typeをapplication/jsonにしているだけ
      Response.json(User("John", 42).toJson)
    },
  )
end Json
