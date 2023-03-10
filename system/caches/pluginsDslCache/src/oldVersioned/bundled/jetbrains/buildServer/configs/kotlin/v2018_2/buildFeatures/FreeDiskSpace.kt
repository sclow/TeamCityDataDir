package jetbrains.buildServer.configs.kotlin.v2018_2.buildFeatures

import jetbrains.buildServer.configs.kotlin.v2018_2.*

/**
 * Build feature ensuring certain [free disk space](https://www.jetbrains.com/help/teamcity/?Free+Disk+Space)
 * on the agent before the build by deleting files managed by the TeamCity agent.
 *
 * **Example.**
 * Cleanup up to 10GB of disk space on the agent before every build. Fail build if it's not possible to provide required disk space.
 * ```
 * freeDiskSpace {
 *   requiredSpace = "10gb"
 *   failBuild = true
 * }
 * ```
 *
 *
 * @see freeDiskSpace
 */
open class FreeDiskSpace() : BuildFeature() {

    init {
        type = "jetbrains.agent.free.space"
    }

    constructor(init: FreeDiskSpace.() -> Unit): this() {
        init()
    }

    /**
     * Amount of the free disk space to ensure. Without a suffix treated as bytes.
     * Supported suffixes are: kb, mb, gb, tb.
     */
    var requiredSpace by stringParameter("free-space-work")

    /**
     * Whether TeamCity should fail a build if sufficient disk space cannot be freed.
     */
    var failBuild by booleanParameter("free-space-fail-start", trueValue = "true", falseValue = "")

    override fun validate(consumer: ErrorConsumer) {
        super.validate(consumer)
    }
}


/**
 * Adds a [free disk space](https://www.jetbrains.com/help/teamcity/?Free+Disk+Space) check
 * to the build configuration or template
 *
 * **Example.**
 * Cleanup up to 10GB of disk space on the agent before every build. Fail build if it's not possible to provide required disk space.
 * ```
 * freeDiskSpace {
 *   requiredSpace = "10gb"
 *   failBuild = true
 * }
 * ```
 *
 *
 * @see FreeDiskSpace
 */
fun BuildFeatures.freeDiskSpace(init: FreeDiskSpace.() -> Unit): FreeDiskSpace {
    val result = FreeDiskSpace(init)
    feature(result)
    return result
}
