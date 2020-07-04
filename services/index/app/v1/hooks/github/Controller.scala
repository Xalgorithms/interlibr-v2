package v1.hooks.github

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

import play.api.mvc._

class Controller @Inject()(cc: ControllerComponents)(implicit ec: ExecutionContext)
    extends BaseController {

  def event: Action[AnyContent] = Builder.async { implicit req =>

  }
}
