package benchmark

object Data {

  case class Friend(id: Int, name: String) extends Serializable

  case class Type(
    `_id`: String,
    `index`: Int,
    `guid`: String,
    `isActive`: Boolean,
    `balance`: String,
    `picture`: String,
    `age`: Int,
    `eyeColor`: String,
    `name`: String,
    `gender`: String,
    `company`: String,
    `email`: String,
    `phone`: String,
    `address`: String,
    `about`: String,
    `registered`: String,
    `latitude`: Double,
    `longitude`: Double,
    `tags`: List[String],
    `friends`: List[Friend],
    `greeting`: String,
    `favoriteFruit`: String
  ) extends Serializable

  val jsonBody =
    """
      |  {
      |    "_id": "576278df40bd978bc65ddb06",
      |    "index": 0,
      |    "guid": "e4c30481-84f3-4193-a83c-fe0435348774",
      |    "isActive": true,
      |    "balance": "$1,355.27",
      |    "picture": "http://placehold.it/32x32",
      |    "age": 26,
      |    "eyeColor": "green",
      |    "name": "Stewart Navarro",
      |    "gender": "male",
      |    "company": "NORSUP",
      |    "email": "stewartnavarro@norsup.com",
      |    "phone": "+1 (916) 543-2895",
      |    "address": "133 Stillwell Place, Richford, Wyoming, 2001",
      |    "about": "Eiusmod excepteur do esse minim nisi occaecat enim non dolor labore ipsum ut. Fugiat deserunt est pariatur pariatur. Laboris aute cillum tempor in exercitation laboris laboris fugiat velit enim ut ad. Ea labore commodo consectetur ut anim anim sint consectetur commodo.\r\n",
      |    "registered": "2015-05-01T05:24:06 -10:00",
      |    "latitude": -29.132033,
      |    "longitude": -58.249295,
      |    "tags": [
      |      "sunt",
      |      "Lorem",
      |      "adipisicing",
      |      "duis",
      |      "nulla",
      |      "sint",
      |      "fugiat"
      |    ],
      |    "friends": [
      |      {
      |        "id": 0,
      |        "name": "Yates Pruitt"
      |      },
      |      {
      |        "id": 1,
      |        "name": "Chen Henry"
      |      },
      |      {
      |        "id": 2,
      |        "name": "Miranda Vincent"
      |      }
      |    ],
      |    "greeting": "Hello, Stewart Navarro! You have 7 unread messages.",
      |    "favoriteFruit": "strawberry"
      |  }
    """.stripMargin

  val invalidJson = jsonBody.replaceFirst(""""phone": "+1 (916) 543-2895",""", """"phone"123,""")
}
