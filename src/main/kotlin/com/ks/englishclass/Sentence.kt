package com.ks.englishclass

import java.io.Serializable
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotEmpty

@Entity
class Sentence : Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Long = 0

  @NotEmpty(message = "please fill in this")
  var s1: String = ""

  @NotEmpty(message = "please fill in this")
  var s2: String = ""

  constructor(s1: String, s2: String) {
    this.s1 = s1
    this.s2 = s2
  }

  override fun toString(): String {
    return "${this.s1}, ${this.s2}"
  }
}