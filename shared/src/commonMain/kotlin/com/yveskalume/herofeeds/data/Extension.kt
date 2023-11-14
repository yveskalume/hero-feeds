package com.yveskalume.herofeeds.data

import com.yveskalume.herofeeds.data.local.Creator

val Creator.mediaList: Map<String,String?>
    get() {
        return mapOf(
            "Twitter/X" to this.twitter,
            "Hashnode" to this.hashnode,
            "Medium" to this.medium
        )
    }