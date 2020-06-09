package com.github.triplet.gradle.play.tasks.internal.workers

import com.github.triplet.gradle.androidpublisher.PlayPublisher
import com.github.triplet.gradle.play.internal.PlayExtensionConfig
import com.github.triplet.gradle.play.internal.serviceAccountCredentialsOrDefault
import org.gradle.api.provider.Property
import org.gradle.workers.WorkAction
import org.gradle.workers.WorkParameters

internal abstract class PlayWorkerBase<T : PlayWorkerBase.PlayPublishingParams> : WorkAction<T> {
    protected val config = parameters.config.get()
    protected val publisher = PlayPublisher(
            config.serviceAccountCredentialsOrDefault,
            parameters.appId.get()
    )

    internal interface PlayPublishingParams : WorkParameters {
        val config: Property<PlayExtensionConfig>
        val appId: Property<String>
    }
}
