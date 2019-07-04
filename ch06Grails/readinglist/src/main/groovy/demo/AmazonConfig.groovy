package demo

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix="amazon")
class AmazonConfig {

  String associateId
  
  def getAssociateId() {
    associateId
  }
}
