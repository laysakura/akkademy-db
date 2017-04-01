package com.github.laysakura.akkademydb.modules

import com.github.laysakura.akkademydb.annotations.ClientId
import com.google.inject.{Provides, Singleton}
import com.twitter.inject.TwitterModule

class ClientIdModule(clientId: String) extends TwitterModule {

  @Provides
  @Singleton
  @ClientId
  def providesDefaultClientId(): String = clientId

}
