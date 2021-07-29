package com.ks.englishclass

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SentenceRepository : JpaRepository<Sentence, Long> {
}