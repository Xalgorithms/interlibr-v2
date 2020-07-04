package v1.hooks.github

import javax.inject.Inject

import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter

class Router @Inject(ctlr: Controller) extends SimpleRouter {
  val prefix = "v1/hooks/github"

  override def routes: Routes = {
    case POST(p"/") => ctlr.event
  }
}
